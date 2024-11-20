import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SeqAlignDP {

    // gap pentaly delta = 30
    private static final int DELTA = 30; 
    
    // DP solution
    public int implement1(String inputFileName, String outputFileName) {
        ArrayList<String> strings = StringGenerator(inputFileName);
        String stringX = strings.get(0);        // seq. of symbols --> x[n] = a letter
        String stringY = strings.get(1);        // seq. of symbols --> y[m] = a letter

        int n = stringX.length();
        int m = stringY.length();

        // construct 2D opt array, w/ each cell holding the cost of opt1(i, j)
        int opt[][] = new int[m + 1][n + 1];

        // initialize 1st row and 1st col --> can prob combine these if make opt square
        for (int i = 0; i <= m; i++) {
            opt[0][i] = i * DELTA;
        }

        for (int j = 0; j <= n; j++) {
            opt[j][0] = j * DELTA;
        }

        // use recurrence formula 1
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                opt[i][j] = Math.min(opt1(i - 1, j - 1) + getAlpha(i, j), 
                    opt1(i - 1, j) + DELTA, 
                    opt (i, j - 1) + DELTA);
            }
        }

        return opt[m][n];
    }

    // DP + D&C solution
    public String implement2(String inputFileName, String outputFileName) {
        
        return "";
    }

    // input string generator fxn
    private ArrayList<String> StringGenerator(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        String x = "";
        String y = "";

        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            String s0 = fileScanner.next();

            while (fileScanner.hasNextInt()) {
                int n = fileScanner.nextInt();

                StringBuilder stringBuilderX = new StringBuilder(s0);
                String newStringX = stringBuilderX.toString();
            }
        
            String t0 = fileScanner.next();

            while (fileScanner.hasNextInt()) {
                int t = fileScanner.nextInt();

                StringBuilder stringBuilderY = new StringBuilder(t0);
                stringBuilderY.insert(t + 1, t0);
                String newStringY = stringBuilderY.toString();
            }

            y = newStringY;
            }
            catch (FileNotFoundException e){
                System.out.println("ERROR: file input file does not exist.");
            }
        
        return strings;
    }

    // helper fxn to get mismatch cost of (i, j)
    private int getAlpha(int i, int j) {
        /*
         * // mismatch costs alpha_pq --> 
                // "pair" = AA, CC, GG, TT
                // true for either order of pair (i.e., AC = CA)
            private static final int PAIR = 0;
            private static final int AC = 110;
            private static final int AG = 48;
            private static final int AT = 94;
            private static final int CG = 118;
            private static final int CT = 48;
            private static final int GT = 110;
         */

        int[][] alphas = new int[4][4];
        
        for 

        return 0;
    }
}
