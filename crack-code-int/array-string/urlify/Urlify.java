class Strang {
    private String s;

    public Strang(String s) {
        this.s = s;
    }

    public String Urlify() {
        char[] input = s.toCharArray();
        String output = "";
        for (char i: input) {
            if ((int)i == 32) {
                output += "%20";
            } else {
                output += i;
            }
        }
        return output;
    }
    public static void main(String[] args) {
        Strang[] tests = {
            new Strang("hey there"),
            new Strang("rubber baby buggy bumper"),
            new Strang("java is fun")
        };
        for (Strang test: tests) {
            System.out.println(test.Urlify());
        }
    }
}