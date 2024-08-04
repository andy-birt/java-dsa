import java.util.HashMap;

class Streng {
    String s;

    public Streng(String s) {
        this.s = s;
    }

    // O(n) Time
    // O(n) Space?
    public String StringCompression() {
        StringBuilder r = new StringBuilder();

        char[] copyToArray = s.toLowerCase().toCharArray();

        HashMap<Character, Integer> chars = new HashMap<Character, Integer>();

        for (char c: copyToArray) {
            int val = chars.getOrDefault(c, 0);
            
            if (val == 0) {
                chars.put(c, 1);
            } else {
                chars.replace(c, ++val);
            }
        }

        chars.forEach((c, i) -> {
            r.append(c.toString()+i.toString());
        });

        return r.toString();
    }
    public static void main(String[] args) {
        Streng[] tests = {
            new Streng("aaabbcc")
        };

        for (Streng t: tests) {
            System.out.println(t.StringCompression());
        }
    }
}