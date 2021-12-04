package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class giantSquid {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("part 1: " + bingo(true));
        System.out.println("part 2: " + bingo(false));
    }

    private static boolean bingoLoop(List<String> board, int index, int end, int step) {
        int counter = 0;
        for (int j = index; j <= end; j += step) {
            if (board.get(j).equals("-1")) {
                counter++;
                if (counter == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkBingo(List<String> board) {
        for (int i = 0; i < 5 * 5; i++) {
            switch (i) {
                case 0 -> {
                    if (bingoLoop(board, 0, 4, 1)) {
                        return true;
                    }
                    if (bingoLoop(board, 0, 20, 5)) {
                        return true;
                    }
                }
                case 1 -> {
                    if (bingoLoop(board, 1, 21, 5)) {
                        return true;
                    }
                }
                case 2 -> {
                    if (bingoLoop(board, 2, 22, 5)) {
                        return true;
                    }
                }
                case 3 -> {
                    if (bingoLoop(board, 3, 23, 5)) {
                        return true;
                    }
                }
                case 4 -> {
                    if (bingoLoop(board, 4, 24, 5)) {
                        return true;
                    }
                }
                case 5 -> {
                    if (bingoLoop(board, 5, 9, 1)) {
                        return true;
                    }
                }
                case 10 -> {
                    if (bingoLoop(board, 10, 14, 1)) {
                        return true;
                    }
                }
                case 15 -> {
                    if (bingoLoop(board, 15, 19, 1)) {
                        return true;
                    }
                }
                case 20 -> {
                    if (bingoLoop(board, 20, 24, 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int bingo(boolean win) throws FileNotFoundException {
        File numbers = new File("src/day4/input_day4_numbers");
        Scanner numScanner = new Scanner(numbers);
        String num = numScanner.nextLine();
        String[] numArray = num.split(",");

        File boards = new File("src/day4/input_day4_boards");
        Scanner boardScanner = new Scanner(boards);
        String boardRow;

        List<String> currentBoard = new ArrayList<>();
        List<String> nums = new ArrayList<>(Arrays.asList(numArray));

        int turns = 0;
        int bestTurns = 999;
        int worstTurns = -1;
        int answer = 0;

        while (boardScanner.hasNext()) {
            for (int i = 0; i < 5; i++) {
                boardRow = boardScanner.nextLine();
                String[] boardNums = boardRow.split(" ");
                for (String s : boardNums) {
                    if (!s.equals("")) {
                        currentBoard.add(s);
                    }
                }
            }
            if (boardScanner.hasNext()) {
                boardRow = boardScanner.nextLine();
            }

            turns = 0;
            for (String checkNum : nums) {
                turns++;
                for (int i = 0; i < currentBoard.size(); i++) {
                    if (checkNum.equals(currentBoard.get(i))) {
                        currentBoard.set(i, "-1");
                    }
                    if (checkBingo(currentBoard) & turns >= 5) {
                        if (win) {
                            if (turns < bestTurns) {
                                answer = 0;
                                bestTurns = turns;
                                for (String cell : currentBoard) {
                                    if (!cell.equals("-1")) {
                                        answer += Integer.parseInt(cell);
                                    }
                                }
                                currentBoard.clear();
                                answer = answer * Integer.parseInt(checkNum);
                            } else {
                                currentBoard.clear();
                                break;
                            }
                        } else {
                            if (turns > worstTurns) {
                                answer = 0;
                                worstTurns = turns;
                                for (String cell : currentBoard) {
                                    if (!cell.equals("-1")) {
                                        answer += Integer.parseInt(cell);
                                    }
                                }
                                currentBoard.clear();
                                answer = answer * Integer.parseInt(checkNum);
                            } else {
                                currentBoard.clear();
                                break;
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}
