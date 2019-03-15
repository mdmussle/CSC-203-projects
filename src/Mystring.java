
/**
 * This is my own work: Michael Mussler java program Mystring 
 * 9/3/2015
 *
 */
public class Mystring {
    public static void main(String[] args) {
        System.out.println();
    }

    private char[] strChars;
    private int curr_length;

    public Mystring() {
        this.curr_length = 0;
        this.strChars = null;
    }

    public Mystring(String str) {
        this.strChars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            this.strChars[i] = str.charAt(i);
        }
        this.curr_length = str.length();
    }

    public Mystring(Mystring str) {
        this.strChars = new char[str.curr_length];
        for (int i = 0; i < str.curr_length; i++) {
            this.strChars[i] = str.strChars[i];
        }
        this.curr_length = str.curr_length;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.curr_length; i++) {
            result += this.strChars[i];
        }
        return result;
    }
//makes sure array is big enough
    private void ensureCapacity() {
        if (this.strChars == null) {
            strChars = new char[1];
        } else if (this.curr_length == this.strChars.length) {
            //must make a bigger array
            char[] copy = new char[this.curr_length * +1];
            for (int i = 0; i < this.curr_length; i++) {
                copy[i] = this.strChars[i];
            }
            this.strChars = copy;
        }
    }

    public Mystring concat(Mystring s) {
        Mystring result = new Mystring();
        result.curr_length = this.curr_length + s.curr_length;
        result.strChars = new char[result.curr_length];
        for (int i = 0; i < this.curr_length; i++) {
            result.strChars[i] = this.strChars[i];
        }
        for (int i = 0; i < s.curr_length; i++) {
            result.strChars[this.curr_length + i] = s.strChars[i];
        }
        return result;
    }

    public Mystring substring(int m, int n) {
        Mystring result = new Mystring();
        result.strChars = new char[n - m];
        for (int i = 0; i < (n - m); i++) {
            result.strChars[i] = this.strChars[m + i];
        }
        result.curr_length = n - m;
        return result;
    }

    public boolean equals(Mystring str) {
        if (this.curr_length != str.curr_length) {
            return false;
        }
        for (int i = 0; i < this.curr_length; i++) {
            if (this.strChars[i] != str.strChars[i]) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(Mystring str) {
        for (int i = 0; i <= this.curr_length - str.curr_length; i++) {
            if (str.equals(this.substring(i, i + str.curr_length))) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Mystring str) {
        for (int i = this.curr_length - str.curr_length; i >= 0; i--) {
            if (str.equals(this.substring(i, i + str.curr_length))) {
                return i;
            }
        }
        return -1;
    }
//compare 2 arrays
    public int compareTo(Mystring str) {
        for (int i = 0; i < curr_length; i++) {
            if (this.strChars[i] < str.strChars[i]) {
                return -1;

            }
            if (this.strChars[i] > str.strChars[i]) {
                return 1;
            }
        }
        return 0;
    }
// get the location of a place in array
    public char get(int index) {

        return strChars[index];

    }
//changes all characters in array to upper case
    public Mystring toUpper() {
        Mystring result = new Mystring(this);
        for (int i = 0; i < this.curr_length; i++) {
            if (result.strChars[i] >= 'a' && result.strChars[i] <= 'z') {
                result.strChars[i] -= 32;

            }

        }
        return result;
    }
//changes all characters in array to lower case
    public Mystring toLower() {
        Mystring result = new Mystring(this);
        for (int i = 0; i < this.curr_length; i++) {
            if (result.strChars[i] >= 'A' && result.strChars[i] <= 'Z') {
                result.strChars[i] += 32;
            }
        }
        return result;
    }
//gets the length of array
    public int length() {
        return curr_length;
    }

    public Mystring concat(String str) {
        Mystring result = new Mystring();
        Mystring f = new Mystring(str);

        result.curr_length = this.curr_length + f.curr_length;
        result.strChars = new char[result.curr_length];

        for (int i = 0; i < this.curr_length; i++) {
            result.strChars[i] = this.strChars[i];
        }
        for (int i = 0; i < f.curr_length; i++) {
            result.strChars[this.curr_length + i] = f.strChars[i];
        }
        return result;

    }

    public Mystring substring(int m) {
        Mystring result = new Mystring();

        result.strChars = new char[this.curr_length];
        result.curr_length = strChars.length - m;

        for (int i = 0; i < this.strChars.length - m; i++) {
            result.strChars[i] = this.strChars[i + m];
        }

        return result;

    }

}
