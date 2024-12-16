package net.happiness.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ShapeUtil {

    public static double roundDouble(double number, int scale) {
        return BigDecimal.valueOf(number)
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private ShapeUtil() {}

}
