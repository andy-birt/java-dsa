class Stringy {
    // Given two strings, write a method to decide if one is a permutation of the other
    String s1;
    String s2;

    public Stringy(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    // I can probably do something I used in the isUnique function more efficiently
    // O(n) Time = O(n) + O(n) + O(256)
    // O(1)? Space = 2 arrays at 256 elements each O(512)
    public boolean CheckPermutation() {
        
        int[] asci = new int[128], ascii = new int[128];

        for (int n = 0; n < s1.length(); n++) {
            int i = s1.toUpperCase().charAt(n);
            asci[i]++;
        }

        for (int n = 0; n < s2.length(); n++) {
            int i = s2.toUpperCase().charAt(n);
            ascii[i]++;
        }

        for (int n = 0; n < asci.length; n++) {
            if (asci[n] != ascii[n]) {
                return false;
            }
        }

        return true;
    }

    public boolean CheckPermutationBubbleSort() {
        // Check equality of each sorted string.
        return BubbleSort(s1).equals(BubbleSort(s2));
    }

    // The simplest and most inefficient (probably) way to sort something
    // O(n^2) Time - A for loop of i to n and a nested loop of j to n
    // O(n) Space - Using an array the size of string s
    public static String BubbleSort(String s) {
        char[] r = s.toLowerCase().toCharArray();
        for (int i = 0; i < r.length; i++) {
            for (int j = i+1; j < r.length; j++) {
                if (r[i] > r[j]) {
                    char temp = r[i];
                    r[i] = r[j];
                    r[j] = temp;
                }
            }
        }
        return new String(r);
    }

    public static void main(String[] args) {
        Stringy[] tests = new Stringy[]{
            new Stringy("Motherinlaw", "Womanhitler"), // True
            new Stringy("Cheater", "Teacher"), // True
            new Stringy("HeLp", "PlEh"), // True
            new Stringy("do not", "do that"), // False
            new Stringy("banana", "orange"), // False
            new Stringy("", " ") // False
        };
        
        // System.out.println(test.CheckPermutationBubbleSort());

        for (Stringy test : tests) {
            System.out.println(test.CheckPermutation());
        }
    }
}