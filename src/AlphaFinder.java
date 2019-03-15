
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 This is my own work: Michael Mussler 
 11/9/2015
 Program to practice recursion 
 */
public class AlphaFinder {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("test.in"));
        int num = file.nextInt();
        System.out.println("Alpha Output");
        for (int i = 0; i < num; i++) {
            if (isAlpha(file.next())) {
                System.out.println("YES");

            } else {
                System.out.println("NO");
            }

        }
        System.out.println("End of Output");
    }
//checks to see if a charley is followed by a bravo 

    public static boolean isAlpha(String s) {
        //uns through the string backwards and checks till it finds a B or R
        int i = s.length() - 1;
        for (; i > 1 && s.charAt(i) != 'L' && s.charAt(i) != 'Y'; i--);
        if (isCharley(s.substring(0, i + 1))) {
            if (isBravo(s.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }

    //Bravo --> Step 1: B[A...A]<Bravo>
    //          R[A...A]<Bravo>
    //          Step 2:B[A...A]V
    //          R[A...A]V        
    public static boolean isBravo(String s) {
        //Checks to see if the legnth is less than 3
        if (s.length() < 3) {
            return false;
        }
        //checks to see if the first letter is B and R
        if (s.charAt(0) != 'B' && s.charAt(0) != 'R') {
            return false;
        }
        //checks to see if the second letter is A
        if (s.charAt(1) != 'A') {
            return false;
        }
        int i = 1;
        for (; i < s.length() && s.charAt(i) == 'A'; i++);
        if (i == s.length()) {
            return false;
        }
        if (i == s.length() - 1 && s.charAt(i) == 'V') {
            return true;
        }
        return isBravo(s.substring(i));
    }

    //Charley --> Step 1: CY
    //            Step 2: CH<charlie>L
    //            Step 3: C<Bravo>L
    public static boolean isCharley(String s) {
        // check to see if first characters are C and Y
        if (s.charAt(0) == 'C' && s.charAt(1) == 'Y') {
            return true;
        }
        //check to see if length is less than 5
        if (s.length() < 5) {
            return false;
        }

        //check to see if it starts with C
        if (s.charAt(0) != 'C') {
            return false;
        }
        //checks to see if it starts with CH
        if (s.charAt(0) == 'C') {
            if (s.charAt(1) == 'H') //if it starts with CH it is followed by charlie
            {
                if (isCharley(s.substring(2))) {
                    //checks to se if all is followed by L
                    if (s.charAt(s.length() - 1) == 'L') {
                        return true;
                    }
                }
            }
        }

            //we already checked to see if the first letter is C so now we check to 
        //see if it is followed by Bravo
        if (isBravo(s.substring(1,s.length()-1))) {
            //checks to see if Bravo is followed by L
            if (s.charAt(s.length() - 1) == 'L') {
                return true;
            }
        }

        return false;
    }

}
