package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class binaryDiagnostic {
    private static int inputLength = 12;
    public static void main(String[] args) throws FileNotFoundException {
        char[] gamma = new char[inputLength];
        StringBuilder stringGamma = new StringBuilder();
        StringBuilder stringEpsilon = new StringBuilder();
        for (int i = 0; i < inputLength; i++) {
            File input = new File("src/day3/input_day3");
            Scanner scanner = new Scanner(input);
            String data = scanner.nextLine();
            int zero = 0;
            int one = 0;
            while (scanner.hasNext()) {
                if (data.charAt(i) == '0') {
                    zero++;
                } else if (data.charAt(i) == '1') {
                    one++;
                }
                data = scanner.nextLine();
            }
            gamma[i] = zero > one ? '0' : '1';
            scanner.close();
        }

        for (int k = 0; k < inputLength; k++) {
            if (gamma[k] == '0') {
                stringGamma.append(0);
                stringEpsilon.append(1);
            } else {
                stringGamma.append(1);
                stringEpsilon.append(0);
            }
        }

        int intGamma = Integer.parseInt(stringGamma.toString(),2);
        int intEpsilon = Integer.parseInt(stringEpsilon.toString(),2);
        System.out.println("answer: " + intGamma*intEpsilon);

    }
}
