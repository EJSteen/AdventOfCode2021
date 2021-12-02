package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sonarSweep2 {
    public static void main(String[] args) throws FileNotFoundException {
        int inc = 0;
        File input = new File("src/day1/input_day1");
        Scanner scanner = new Scanner(input);
        int data1 = scanner.nextInt();
        int data2 = scanner.nextInt();
        int data3 = scanner.nextInt();
        while(scanner.hasNext()) {
            int combData1 = data1 + data2 + data3;
            data1 = data2;
            data2 = data3;
            data3 = scanner.nextInt();
            int combData2 = data1 + data2 + data3;
            if (combData2 > combData1) {
                inc++;
            }
        }
        System.out.println(inc);
    }
}
