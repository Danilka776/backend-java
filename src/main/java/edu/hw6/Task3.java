package edu.hw6;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public final class Task3 {
    private Task3() {
    }

    private static final int MASK = 0xFF;

    public interface AbstractFilter extends DirectoryStream.Filter<Path> {

        boolean accept(Path path, BasicFileAttributes attrs) throws IOException;

        default boolean accept(Path path) throws IOException {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            return accept(path, attrs);
        }

        default AbstractFilter and(AbstractFilter other) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return AbstractFilter.this.accept(path, attrs) && other.accept(path, attrs);
                }
            };
        }

        default AbstractFilter or(AbstractFilter other) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return AbstractFilter.this.accept(path, attrs) || other.accept(path, attrs);
                }
            };
        }

        default AbstractFilter negate() {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return !AbstractFilter.this.accept(path, attrs);
                }
            };
        }

        AbstractFilter REGULAR_FILE = new AbstractFilter() {
            @Override
            public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                return attrs.isRegularFile();
            }
        };

        AbstractFilter READABLE = new AbstractFilter() {
            @Override
            public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                return Files.isReadable(path);
            }
        };

        static AbstractFilter sizeGreaterThan(long size) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return attrs.size() > size;
                }
            };
        }

        static AbstractFilter magicNumber(int... magicBytes) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    if (magicBytes.length == 0) {
                        return true;
                    }

                    try {
                        byte[] fileBytes = Files.readAllBytes(path);
                        for (int i = 0; i < magicBytes.length; i++) {
                            if (fileBytes.length <= i || (fileBytes[i] & MASK) != magicBytes[i]) {
                                return false;
                            }
                        }
                        return true;
                    } catch (IOException e) {
                        return false;
                    }
                }
            };
        }

        static AbstractFilter globMatches(String glob) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return path.getFileName().toString().matches(globToRegex(glob));
                }
            };
        }

        static AbstractFilter regexContains(String regex) {
            return new AbstractFilter() {
                @Override
                public boolean accept(Path path, BasicFileAttributes attrs) throws IOException {
                    return path.toString().matches(regex);
                }
            };
        }

        static String globToRegex(String glob) {
            StringBuilder regex = new StringBuilder("^");
            for (char c : glob.toCharArray()) {
                switch (c) {
                    case '*':
                        regex.append(".*");
                        break;
                    case '?':
                        regex.append(".");
                        break;
                    case '.':
                    case '(':
                    case ')':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                    case '\\':
                    case '+':
                    case '|':
                    case '^':
                    case '$':
                        regex.append("\\").append(c);
                        break;
                    default:
                        regex.append(c);
                        break;
                }
            }
            regex.append("$");
            return regex.toString();
        }

    }

}



