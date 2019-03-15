
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is my own work: Michael Mussler 9/9/2015 2D array making a castle
 * defense
 */
public class CastleDefense {

    private static double[][] grid;
    private static double row = 0.0, col = 0.0;
    

    public static void main(String[] args) throws FileNotFoundException {
        double rows = 0;
        double columns = 0;
        double counters = 0;

        Scanner file = new Scanner(new File("data.in"));

        //reads doubles from file and puts in array list
        ArrayList<Double> List = new ArrayList<Double>();

        
        while (file.hasNext()) {
            List.add(file.nextDouble());
            counters += 1;

            //Puts arryList into 1d array
            double array[] = new double[List.size()];
            for (int b = 0; b < List.size(); b++) {
                array[b] = List.get(b);

            }
            // calculates number of rows and columns

            if (counters % (Math.sqrt(counters)) == 0) {
                rows = (int) Math.sqrt(counters);
                columns = (int) Math.sqrt(counters);

            } else {
                for (int c = (int) Math.sqrt(counters); c > 0; c--) {
                    rows = c;
                    columns = counters / c;
                    c = -1;
                }

            }
        }
        

        System.out.println(grid.toString());
        System.out.println("The weakest 2 X 2 on the castle begins at " + row + " , " + col +" with an average"+
"strength of");
        
    }

    

    public CastleDefense(int numRows, int numCols, double[] scan) {
        row = numRows;
        col = numCols;
        this.grid = new double[numRows][numCols];
        for (int c = 0; c < numRows; c++) {
            if (c % 2 == 0) {
                for (int b = 0; b < numCols; b++) {
                    grid[c][b] = scan[c + b];
                }
            }
            if (c % 2 != 0) {
                for (int b = numRows; b >= 0; b--) {
                    grid[c][b] = scan[c + b];

                }
            }

        }
    }

    public double getAverage(int startRow, int endRow, int startCol, int endCol) {
        double sum = 0;
        double avg = 0;
        for (int c = (startRow - 1); c <= (endRow - 1); c++) {
            for (int b = (startCol - 1); b < (endRow - 1); b++) {
                sum += sum + grid[c][b];
                avg += 1;

            }
        }

        return sum / avg;
    }

    public String toString() {
        String result = "";
        for(int row = 0; row < grid.length; row++) {
     for(int col = 0; col < grid[row].length; col++) {
        result = " " + grid[row][col];
            }
            result = "/n";
        }
        return result;
    }

    public void findWeakest() {

        double minimum = getAverage(0, 1, 0, 1);
        for (int c = 1; c < row - 1; c++) {
            for (int b = 1; b < col - 1; b++) {
                if (minimum > getAverage(c, c + 1, b, b + 1)) {
                    minimum = getAverage(c, c + 1, b, b + 1);
                    row = c;
                    col = b;

                }
            }

        }

    }
}
