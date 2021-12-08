package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sevenSegmentSearch {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day8/input_day8");
        Scanner scanner = new Scanner(input);
        String line;
        int part1 = 0;
        int part2 = 0;

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String output = line.split(" \\| ")[1];
            String pattern = line.split(" \\| ")[0];
            part1 += part1(output);
            part2 += part2(pattern, output);
        }

        System.out.println("part 1: " + part1);
        System.out.println("part 2: " + part2);
    }

    private static int part1(String output) {
        String[] outputArr = output.split(" ");
        int counter = 0;
        for (String val : outputArr) {
            if (val.length() == 2 || val.length() == 3 || val.length() == 4 || val.length() == 7) {
                counter++;
            }
        }
        return counter;
    }

    private static int part2(String pattern, String output) {
        String[] outputArr = output.split(" ");
        StringBuilder intSeq = new StringBuilder();
        Map<String, Integer> fullPattern = recognizePattern(pattern);
        for (String val : outputArr) {
            char[] valArray = val.toCharArray();
            Arrays.sort(valArray);
            String sortedVal = new String(valArray);
            intSeq.append(fullPattern.get(sortedVal));
        }
        return Integer.parseInt(String.valueOf(intSeq));
    }

    private static Map<String, Integer> recognizePattern(String pattern) {
        String[] patternArr = pattern.split(" ");
        String[] one = null;
        String[] four = null;
        String nine = null;
        Map<String, Integer> numCodes = new HashMap<>();
        for (String s : patternArr) {
            char[] tempArr = s.toCharArray();
            Arrays.sort(tempArr);
            s = new String(tempArr);
            if (s.length() == 2) {
                one = s.split("");
                numCodes.put(s, 1);
            } else if (s.length() == 3) {
                numCodes.put(s, 7);
            } else if (s.length() == 4) {
                four = s.split("");
                numCodes.put(s, 4);
            } else if (s.length() == 7) {
                numCodes.put(s, 8);
            } else if (s.length() == 5) {
                numCodes.put(s, -1);
            } else if (s.length() == 6) {
                numCodes.put(s, -2);
            }
        }

        for (Map.Entry<String, Integer> num : numCodes.entrySet()) {
            if (num.getValue() == -1) {
                boolean containsOne = true;
                for (String s : one) {
                    if (!num.getKey().contains(s)) {
                        containsOne = false;
                        break;
                    }
                }
                if (containsOne) {
                    num.setValue(3);
                } else if (nine != null) {
                    boolean nineContainsFive = true;
                    String[] posFive = num.getKey().split(" ");
                    for (String s : posFive) {
                        if (!nine.contains(s)) {
                            nineContainsFive = false;
                            break;
                        }
                    }
                    if (nineContainsFive) {
                        num.setValue(5);
                    }
                } else if (numCodes.containsValue(3) && numCodes.containsValue(5)) {
                    num.setValue(2);
                }
            } else if (num.getValue() == -2) {
                boolean containsFour = true;
                for (String s : four) {
                    if (!num.getKey().contains(s)) {
                        containsFour = false;
                        break;
                    }
                }
                if (containsFour) {
                    num.setValue(9);
                    nine = num.getKey();
                } else {
                    boolean containsOne = true;
                    for (String s : one) {
                        if (!num.getKey().contains(s)) {
                            containsOne = false;
                            break;
                        }
                    }
                    if (containsOne) {
                        num.setValue(0);
                    } else {
                        num.setValue(6);
                    }
                }
            }
        }
        if (!numCodes.containsValue(5)) {
            for (Map.Entry<String, Integer> num : numCodes.entrySet()) {
                if (num.getValue() == -1) {
                    boolean nineContainsFive = true;
                    String[] posFive = num.getKey().split("");
                    for (String s : posFive) {
                        if (!nine.contains(s)) {
                            nineContainsFive = false;
                            break;
                        }
                    }
                    if (nineContainsFive) {
                        num.setValue(5);
                    } else {
                        num.setValue(2);
                    }
                }
            }
        }
        return numCodes;
    }
}
