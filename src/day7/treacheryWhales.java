package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class treacheryWhales {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day7/input_day7");
        Scanner scanner = new Scanner(input);
        String data = scanner.nextLine();
        int[] crabs = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(crabs);

        System.out.println("part 1: " + part1(crabs));
        System.out.println("part 2: " + part2(crabs));
    }

    private static int part1(int[] crabs) {
        int median = (crabs.length % 2 == 1) ? crabs[(crabs.length + 1) / 2 - 1] :
                (crabs[crabs.length / 2 - 1] + crabs[crabs.length / 2]) / 2;

        int fuelSpent = 0;
        for (Integer crab : crabs) {
            fuelSpent += (median <= crab) ? crab - median : median - crab;
        }
        return fuelSpent;
    }

    private static int part2(int[] crabs) {
        int fuelSpent = 0, steps, sum = 0;
            int avg = (int) Math.round(Arrays.stream(crabs).average().getAsDouble() - 1);
            for (Integer crab : crabs) {
                steps = (avg <= crab) ? crab - avg : avg - crab;
                for (int j = 1; j <= steps; j++) {
                    sum += j;
                }
                fuelSpent += sum;
                sum = 0;
            }

        return fuelSpent;
    }
}
