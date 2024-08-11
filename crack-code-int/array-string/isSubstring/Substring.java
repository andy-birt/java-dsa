class Substring {
    
    String s1;
    String s2;
    String sConcat;
    
    public Substring(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.sConcat = s2.concat(s2);
    }

    // O(n^m) - Not sure... Need to revisit this at some point
    public boolean isSubstring() {
        char[] s1Chars = s1.toCharArray();
        char[] sConcatChars = sConcat.toCharArray();
        int count = 0;
        int j = 0;
        for (int i = 0; i < s1Chars.length; i++) {
            while(s1Chars[i] != sConcatChars[j] && j < sConcatChars.length-1) {
                j++;
            }

            if(s1Chars[i] == sConcatChars[j]) {
                count++;
                j++;
            }
        }

        return s1Chars.length == count;
    }

    public static void main(String[] args) {
        Substring[] tests = {
            new Substring("watermelon", "ermelonwat"),
            new Substring("bonbonfire", "bonfirebon"),
            new Substring("test", "tset"),
            new Substring("new", "doo")
        };
        
        for (Substring test: tests) {
            System.out.println(test.isSubstring());
        }
    }
}