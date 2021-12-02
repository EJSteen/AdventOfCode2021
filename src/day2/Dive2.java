package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dive2 {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day2/input_day2");
        Scanner scanner = new Scanner(input);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        while(scanner.hasNext()) {
            String data = scanner.nextLine();
            String[] arrData = data.split(" ");
            int amount = Integer.parseInt(arrData[1]);
            switch (arrData[0]) {
                case "up" -> aim -= amount;
                case "down" -> aim += amount;
                case "forward" -> {
                    horizontal += amount;
                    depth += aim != 0 ? amount*aim : depth;
                }

            }
        }
        System.out.println(depth * horizontal);
    }
}
