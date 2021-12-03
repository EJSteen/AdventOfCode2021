package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class binaryDiagnostic2 {
    private static final int inputLength = 12;

    private static int searchNum(boolean oxygen) throws FileNotFoundException {
        StringBuilder bitCrit = new StringBuilder();
        ArrayList<String> followsCrit = new ArrayList<>();
        for (int i = 0; i < inputLength; i++) {
            File input = new File("src/day3/input_day3");
            Scanner scanner = new Scanner(input);
            String data = scanner.nextLine();
            followsCrit.clear();
            int counter = 0,
                    zero = 0,
                    one = 0;
            while (scanner.hasNext()) {
                if (data.startsWith(bitCrit.toString())) {
                    counter++;
                    if (data.charAt(i) == '0') {
                        zero++;
                    } else if (data.charAt(i) == '1') {
                        one++;
                    }
                    followsCrit.add(data);
                }
                data = scanner.nextLine();
            }

            if (counter == 1) {
                return (Integer.parseInt(followsCrit.get(0),2));
            }

            if (oxygen) {
                bitCrit.append(one >= zero ? 1 : 0);
            } else {
                bitCrit.append(one >= zero ? 0 : 1);
            }

            if (i == 11) {
                for (String s : followsCrit) {
                    if (s.startsWith(bitCrit.toString())) {
                        return (Integer.parseInt(s, 2));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int oxyGen = searchNum(true);
        int Co2Scrub = searchNum(false);
        System.out.println("answer: " + oxyGen * Co2Scrub);
    }
}
