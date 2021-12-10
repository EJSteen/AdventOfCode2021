package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class syntaxScoring {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day10/input_day10");
        Scanner scanner = new Scanner(input);
        String line;
        int part1 = 0;
        List<Long> part2 = new ArrayList<>();
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            String[] lineArr = line.split("");
            part1 += checkSyntax(lineArr);
            if (completeLines(lineArr) != 0) {
                part2.add(completeLines(lineArr));
            }
        }

        Collections.sort(part2);
        System.out.println(part2);
        long middleScore = part2.get((part2.size() / 2));

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + middleScore);
    }

    private static long completeLines(String[] lineArr) {
        List<String> openBrackets = new ArrayList<>();
        long points = 0;
        if (isCorrupted(lineArr)) {
            return 0;
        } else {
            for (String s : lineArr) {
                if (s.equals("(") || s.equals("[") || s.equals("{") || s.equals("<")) {
                    openBrackets.add(s);
                } else {
                    switch (s) {
                        case ")" -> {
                            if (openBrackets.get(openBrackets.size() - 1).equals("(")) {
                                openBrackets.remove(openBrackets.size() - 1);
                            }
                        }
                        case "]" -> {
                            if (openBrackets.get(openBrackets.size() - 1).equals("[")) {
                                openBrackets.remove(openBrackets.size() - 1);
                            }
                        }
                        case "}" -> {
                            if (openBrackets.get(openBrackets.size() - 1).equals("{")) {
                                openBrackets.remove(openBrackets.size() - 1);
                            }
                        }
                        case ">" -> {
                            if (openBrackets.get(openBrackets.size() - 1).equals("<")) {
                                openBrackets.remove(openBrackets.size() - 1);
                            }
                        }
                    }
                }
            }
            if (!openBrackets.isEmpty()) {
                for (int i = openBrackets.size() - 1; i >= 0; i--) {
                    switch (openBrackets.get(i)) {
                        case "(" -> {
                            points = points * 5;
                            points++;
                        }
                        case "[" -> {
                            points = points * 5;
                            points += 2;
                        }
                        case "{" -> {
                            points = points * 5;
                            points += 3;
                        }
                        case "<" -> {
                            points = points * 5;
                            points += 4;
                        }
                    }
                }
            }
        }
        return points;
    }

    private static boolean isCorrupted(String[] lineArr) {
        return checkSyntax(lineArr) != 0;
    }

    private static int checkSyntax(String[] lineArr) {
        List<String> openBrackets = new ArrayList<>();
        for (String s : lineArr) {
            if (s.equals("(") || s.equals("[") || s.equals("{") || s.equals("<")) {
                openBrackets.add(s);
            } else {
                switch (s) {
                    case ")" -> {
                        if (openBrackets.get(openBrackets.size() - 1).equals("(")) {
                            openBrackets.remove(openBrackets.size() - 1);
                        } else {
                            return 3;
                        }
                    }
                    case "]" -> {
                        if (openBrackets.get(openBrackets.size() - 1).equals("[")) {
                            openBrackets.remove(openBrackets.size() - 1);
                        } else {
                            return 57;
                        }
                    }
                    case "}" -> {
                        if (openBrackets.get(openBrackets.size() - 1).equals("{")) {
                            openBrackets.remove(openBrackets.size() - 1);
                        } else {
                            return 1197;
                        }
                    }
                    case ">" -> {
                        if (openBrackets.get(openBrackets.size() - 1).equals("<")) {
                            openBrackets.remove(openBrackets.size() - 1);
                        } else {
                            return 25137;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
