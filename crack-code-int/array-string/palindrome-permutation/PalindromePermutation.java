class Streeng {
    String s;

    public Streeng(String s) {
        this.s = s;
    }

    public boolean PalindromePermutation() {

        int odds = 0;
        int slength = s.length();
        String st = s.replace(" ", "").toLowerCase();
        
        if (slength < 2 || slength == 2 && st.charAt(0) == st.charAt(1)) {
            return true;
        } else if (slength == 2 && st.charAt(0) != st.charAt(1)) {
            return false;
        }

        int[] chars = new int[256];
        for (char c: st.toCharArray()) {
            chars[(int)c]++;
        }

        for (int i: chars) {
            if (i % 2 != 0) {
                odds++;
            }
        }

        return odds <= 1;
    }
    public static void main(String[] args) {
        Streeng[] tests = {
            new Streeng("fOeinFNKDS"),
            new Streeng("taco cat"),
            new Streeng("racecar"),
            new Streeng("null"),
            new Streeng("aabbccdd"),
            new Streeng("aabbccdde"),
            new Streeng("ccaaerr")
        };

        for (Streeng t: tests) {
            System.out.println(t.PalindromePermutation());
        }
    }
}