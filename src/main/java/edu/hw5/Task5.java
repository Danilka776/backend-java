package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {

    private Task5() {
    }

    public static Boolean validCarPlate(String plate) {
        Pattern pattern = Pattern.compile("[АВЕКМНОРСТУХABEKMHOPCTYX]\\d{3}[АВЕКМНОРСТУХABEKMHOPCTYX]{2}\\d{2,3}");
        Matcher matcher = pattern.matcher(plate);

        return matcher.matches();
    }


}
