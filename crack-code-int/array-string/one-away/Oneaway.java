class Streang {
    String s1;
    String s2;

    public Streang(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // O(n) Time where n is the length of the shortest string
    public boolean OneAway() {
        int awayFromBeginning = Math.abs(s1.length() - s2.length());
        if (awayFromBeginning > 1) {
            return false;
        }

        int shorty = Math.min(s1.length(), s2.length());
        int away = 0;
        for (int i = 0; i < shorty; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                away++;
            }
        }
        
        return away + awayFromBeginning <= 1;
    }
    public static void main(String[] args) {
        Streang[] tests = {
            new Streang("pale", "peles"),
            new Streang("pale", "pales"),
            new Streang("heeble", "hooble"),
            new Streang("burp", "burp")
        };

        for (Streang t: tests) {
            System.out.println(t.OneAway());
        }
    }
}