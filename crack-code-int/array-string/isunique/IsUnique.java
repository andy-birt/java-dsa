class IsUnique {
    // Implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structures?
    String s;

    public IsUnique(String s) {
        this.s = s;
    }

    // I think this does it? 
    // O(n) Time - Determined by the length of String s
    // O(1) Space - Uses an array with a fixed size to represent each character code in ASCII
    public boolean isUnique() {
        boolean[] uniqueCharsFound = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            int charCode = (int)s.toUpperCase().charAt(i);
            if (uniqueCharsFound[charCode]) {
                return false;
            }
            uniqueCharsFound[charCode] = true;
        }
        return true;
    }
    public static void main(String[] args) {
        // Change this or add as many test cases as you'd like
        IsUnique test = new IsUnique("Aa");
        System.out.println(test.isUnique());
    }
}