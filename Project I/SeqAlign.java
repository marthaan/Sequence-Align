import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeqAlign {

    // gap penalty delta = 30
    private static final int DELTA = 30; 
    
    // DP solution
    public int opt1(String inputFileName, String outputFileName) {
        ArrayList<String> strings1 = StringGenerator(inputFileName);
        String X1 = strings1.get(0);        // seq. of symbols --> x[n] = a letter
        String Y1 = strings1.get(1);        // seq. of symbols --> y[m] = a letter

        int n1 = X1.length();
        int m1 = Y1.length();

        // construct 2D opt array, w/ each cell holding the cost of opt1(i, j)
        int opt1[][] = new int[m1 + 1][n1 + 1];

        // initialize 1st row and 1st col --> can prob combine these if make opt square
        for (int i = 0; i <= m1; i++) {
            opt1[0][i] = i * DELTA;
        }

        for (int j = 0; j <= n1; j++) {
            opt1[j][0] = j * DELTA;
        }

        // use recurrence formula 1
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                opt1[i][j] = Math.min(opt1[i - 1][j - 1] + getAlpha(X1.charAt(i), Y1.charAt(j)), 
                    opt1[i - 1][j] + DELTA, 
                    opt1[i][j - 1] + DELTA);
            }
        }

        return opt1[m1][n1];
    }

    // DP + D&C solution
    public int opt2(String inputFileName, String outputFileName) {
        ArrayList<String> strings2 = StringGenerator(inputFileName);
        String X2 = strings2.get(0);        // seq. of symbols --> x[n] = a letter
        String Y2 = strings2.get(1);        // seq. of symbols --> y[m] = a letter

        int n2 = X2.length();
        int m2 = Y2.length();

        // divide X in half
        // need to use DP to find the opt. split pt. in Y
        // same recurrence formula as opt1?? not given in notes

        return opt2[m2][n2];
    }

    // input string generator fxn
    private ArrayList<String> StringGenerator(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        String X = "";
        String Y = "";

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            String s0 = fileScanner.next();
            StringBuilder stringBuilderX = new StringBuilder(s0);

            while (fileScanner.hasNextInt()) {
                int n = fileScanner.nextInt();

                stringBuilderX.insert(n + 1, stringBuilderX);
            }

            X = stringBuilderX.toString();
        
            String t0 = fileScanner.next();
            StringBuilder stringBuilderY = new StringBuilder(t0);

            while (fileScanner.hasNextInt()) {
                int t = fileScanner.nextInt();

                stringBuilderY.insert(t + 1, stringBuilderY);
            }

            Y = stringBuilderY.toString();

        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: file input file does not exist.");
        }
        
        strings.add(X);
        strings.add(Y);

        return strings;
    }

    // helper fxn to get mismatch cost of (X[i], Y[j])
    private int getAlpha(char i, char j) {
        // could be a better way to do this with a 2D array
        // but I found it to be more annoying lol
        if (i == j) { return 0; }
        else if (i == 'A' && j == 'C' || i == 'C' && j == 'A') { return 110; }
        else if (i == 'A' && j == 'G' || i == 'G' && j == 'A') { return 48; }
        else if (i == 'A' && j == 'T' || i == 'T' && j == 'A') { return 94; }
        else if (i == 'C' && j == 'G' || i == 'G' && j == 'C') { return 118; }
        else if (i == 'C' && j == 'T' || i == 'T' && j == 'C') { return 48; }
        else if (i == 'G' && j == 'T' || i == 'T' && j == 'G') { return 110; }
        else { return 0; }
    }
}
