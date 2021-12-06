package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.LongStream;

public class Lanternfish {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day6/input_day6");
        Scanner scanner = new Scanner(input);
        String data = scanner.nextLine();
        String[] dataArr = data.split(",");

        long[] lotsOFish = new long[9];
        for (String s : dataArr) {
            lotsOFish[Integer.parseInt(s)]++;
        }

        System.out.println("Part 1: " + fishSex(lotsOFish, 80));
        System.out.println("Part 2: " + fishSex(lotsOFish, 256));

    }

    private static long fishSex(long[] lotsOFish, int simDays) {
        for (int i = 0; i < simDays; i++) {
            long babyCounter = lotsOFish[0];
            System.arraycopy(lotsOFish, 1, lotsOFish, 0, lotsOFish.length - 1);
            lotsOFish[6] += babyCounter;
            lotsOFish[8] = babyCounter;
        }
        return LongStream.of(lotsOFish).sum();
    }
}
