package tombchips.avalimod.util;

import org.antlr.v4.runtime.misc.NotNull;

public class Maths {
    public static final int
            BITMASK8 = 0xff,
            BITMASK16 = 0x10000;

    private Maths() {
    }

    public static <T> T get(@NotNull T[] array, int index) {
        return array[index >= array.length ? 0 : index];
    }
}
