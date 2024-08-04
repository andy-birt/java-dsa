// A simple string builder
public class StringBuilder {
    
    // This is our string to build on
    String s;

    // With no value s is initialized as an empty string
    public StringBuilder() {
        this.s = "";
    }

    // Passing in a string will initialize our string using that value
    public StringBuilder(String s) {
        this.s = s;
    }

    // This will add to our existing string
    // Seems like the most efficient way to do this
    public void append(String input) {
        this.s = s + input;
    }

    public void prepend(String input) {
        this.s = input + s;
    }

    // Our value string to display;
    public String toString() {
        return s;
    }

    public static void concatInLoop() {
        String e = "";

        for (int i = 0; i < 10; i++) {
            e += i;
        }

        System.out.println(e + ": concatInLoop()");
    }
    
    public static void main(String[] args) {
        StringBuilder testEmpty = new StringBuilder();
        StringBuilder test = new StringBuilder("this ");

        StringBuilder[] tests = {
            testEmpty,
            test
        };

        // Test initialization
        for (StringBuilder t: tests) {
            System.out.println(t.toString());
        }

        // Append value to test
        test.append("is a test");

        System.out.println(test.toString());

    }
}