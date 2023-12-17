package edu.hw11;

import java.io.FileOutputStream;
import java.io.IOException;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public final class Task3 {

    private Task3() {
    }

    public static class FibonacciGenerator {

        @SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava"})
        public static void main(String[] args) throws IOException {
            byte[] byteCode = generateFibonacciClassByteCode();
            try (FileOutputStream fos = new FileOutputStream(
                "/Users/daniltarasov/backend-java/backend-java/src/main/java/edu/hw11/FibonacciClass.class")) {
                fos.write(byteCode);
            }

            FibonacciClass fibonacciInstance = new FibonacciClass();
            long result = fibonacciInstance.fib(1);
            System.out.println("Fibonacci(1) = " + result);
        }

        @SuppressWarnings("MultipleStringLiterals")
        private static byte[] generateFibonacciClassByteCode() {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "fib", null, "java/lang/Object", null);

            // Создаем конструктор
            MethodVisitor constructorMv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            constructorMv.visitCode();
            constructorMv.visitVarInsn(Opcodes.ALOAD, 0);
            constructorMv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            constructorMv.visitInsn(Opcodes.RETURN);
            constructorMv.visitMaxs(1, 1);
            constructorMv.visitEnd();

//            public long fib(int);
//            Code:
//            0: iload_1
//            1: iconst_1
//            2: if_icmpgt     8
//            5: iload_1
//            6: i2l
//            7: lreturn
//            8: aload_0
//            9: iload_1
//            10: iconst_1
//            11: isub
//            12: invokevirtual #7                  // Method fib:(I)J
//            15: aload_0
//            16: iload_1
//            17: iconst_2
//            18: isub
//            19: invokevirtual #7                  // Method fib:(I)J
//            22: ladd
//            23: lreturn


            // Создаем метод fib
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "fib", "(I)J", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.ICONST_1);
            Label label1 = new Label();
            mv.visitJumpInsn(Opcodes.IFEQ, label1);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.I2L);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitLabel(label1);


            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitJumpInsn(Opcodes.IF_ICMPEQ, new Label());
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_1);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciCalculator", "calculateFibonacci", "(I)J", false);
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitInsn(Opcodes.ICONST_2);
            mv.visitInsn(Opcodes.ISUB);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibonacciCalculator", "calculateFibonacci", "(I)J", false);
            mv.visitInsn(Opcodes.LADD);
            mv.visitInsn(Opcodes.LRETURN);



            mv.visitMaxs(2, 1);
            mv.visitEnd();

            cw.visitEnd();
            return cw.toByteArray();
        }

        public static class FibonacciClass {
            public long fib(int n) {
                return 0; // Заглушка, так как метод будет перезаписан сгенерированным кодом
            }
        }
    }


}
