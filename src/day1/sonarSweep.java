package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class sonarSweep {
    public static void main(String[] args) throws FileNotFoundException {
        int inc = 0;
        File input = new File("src/day1/input_day1");
        Scanner scanner = new Scanner(input);
        int data2 = scanner.nextInt();
        while(scanner.hasNext()) {
            int data = scanner.nextInt();
            if (data > data2) {
                inc++;
            }
            data2 = data;
        }
        System.out.println(inc);
    }
}
