package converter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Converter {
    private List<ConversionPair> table;

    public Converter() {
        this.table = new LinkedList<>();
        fillConversions();
    }

    public String convertToRoman(int arabicNumber) {
        ConversionPair cp = getFirstPairForArabic(arabicNumber);
        if (cp.getArabic() == 0) return cp.getRoman();
        return cp.getRoman() + convertToRoman(arabicNumber - cp.getArabic());
    }

    public int convertToArabic(String romanNumber) {
        ConversionPair cp = getFirstPairForRoman(romanNumber);
        if (cp.getRoman().isEmpty()) return cp.getArabic();
        return cp.getArabic() + convertToArabic(romanNumber.substring(cp.getRoman().length(), romanNumber.length()));
    }

    private void fillConversions() {
        this.table.add(new ConversionPair(0, ""));
        this.table.add(new ConversionPair(1, "I"));
        this.table.add(new ConversionPair(4, "IV"));
        this.table.add(new ConversionPair(5, "V"));
        this.table.add(new ConversionPair(9, "IX"));
        this.table.add(new ConversionPair(10, "X"));
        this.table.add(new ConversionPair(49, "XL"));
        this.table.add(new ConversionPair(50, "L"));
        this.table.add(new ConversionPair(90, "XC"));
        this.table.add(new ConversionPair(100, "C"));
        this.table.add(new ConversionPair(400, "CD"));
        this.table.add(new ConversionPair(500, "D"));
        this.table.add(new ConversionPair(900, "CM"));
        this.table.add(new ConversionPair(1000, "M"));
        Collections.sort(this.table, (o1, o2) -> o2.getArabic() - o1.getArabic());
    }

    private ConversionPair getFirstPairForArabic(int arabicNumber) {
        return this.table.stream().filter(p -> p.getArabic() <= arabicNumber).findFirst().get();
    }

    private ConversionPair getFirstPairForRoman(String romanNumber) {
        return this.table.stream().filter(p -> romanNumber.startsWith(p.getRoman())).findFirst().get();
    }

    private class ConversionPair {
        private int arabic;
        private String roman;

        public ConversionPair(int arabic, String roman) {
            this.arabic = arabic;
            this.roman = roman;
        }

        public int getArabic() {
            return arabic;
        }

        public String getRoman() {
            return roman;
        }
    }
}
