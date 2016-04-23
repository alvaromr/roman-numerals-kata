package test;

import converter.Converter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private Converter converter;
    private static Map<Integer, String> arabicToRoman;
    private static Map<String, Integer> romanToArabic;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Using a LinkedHashMap to preserve insertion order:
        arabicToRoman = new LinkedHashMap<>();
        arabicToRoman.put(0, "");
        arabicToRoman.put(1, "I");
        arabicToRoman.put(5, "V");
        arabicToRoman.put(10, "X");
        arabicToRoman.put(2, "II");
        arabicToRoman.put(3, "III");
        arabicToRoman.put(4, "IV");
        arabicToRoman.put(6, "VI");
        arabicToRoman.put(7, "VII");
        arabicToRoman.put(8, "VIII");
        arabicToRoman.put(9, "IX");
        arabicToRoman.put(50, "L");
        arabicToRoman.put(49, "XL");
        arabicToRoman.put(100, "C");
        arabicToRoman.put(90, "XC");
        arabicToRoman.put(500, "D");
        arabicToRoman.put(1000, "M");
        arabicToRoman.put(900, "CM");
        arabicToRoman.put(1990, "MCMXC");
        arabicToRoman.put(2451, "MMCDLI");
        arabicToRoman.put(999, "CMXCIX");

        // Inverse transformations Map:
        romanToArabic = arabicToRoman.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey,
                        (x, y) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
    }

    @Before
    public void setUp() {
        converter = new Converter();
    }

    @Test
    public void testDirectConversions() {
        for (Integer arabic : arabicToRoman.keySet())
            shouldConvertArabicToRoman(arabic, arabicToRoman.get(arabic));
    }

    @Test
    public void testInverseConversions() {
        for (String roman : romanToArabic.keySet())
            shouldConvertRomanToArabic(roman, romanToArabic.get(roman));
    }

    private void shouldConvertArabicToRoman(int fromArabic, String toRoman) {
        assertEquals(fromArabic + " was not converted to " + toRoman, toRoman, converter.convertToRoman(fromArabic));
    }

    private void shouldConvertRomanToArabic(String fromRoman, int toArabic) {
        assertEquals(fromRoman + " was not converted to " + fromRoman, toArabic, converter.convertToArabic(fromRoman));
    }
}
