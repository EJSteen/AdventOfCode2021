package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class hydrothermalVenture {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day5/input_day5");
        Scanner scanner = new Scanner(input);
        Map<String, Integer> board = new HashMap<>();
        int counter = 0;

        while (scanner.hasNext()) {
            String data = scanner.nextLine();
            String[] coords = data.split(" -> ");
            String[] coord1 = coords[0].split(",");
            String[] coord2 = coords[1].split(",");
            if (coord1[0].equals(coord2[0])) {
                if (Integer.parseInt(coord1[1]) > Integer.parseInt(coord2[1])) {
                    insertValue(coord1, coord2, board, true);
                } else {
                    insertValue(coord2, coord1, board, true);
                }
            }
            if (coord1[1].equals(coord2[1])) {
                if (Integer.parseInt(coord1[0]) > Integer.parseInt(coord2[0])) {
                    insertValue(coord1, coord2, board, false);
                } else {
                    insertValue(coord2, coord1, board, false);
                }
            } else {
                insertDiagonal(coord1, coord2, board);
            }

        }
        for (Map.Entry<String, Integer> entry : board.entrySet()) {
            if (entry.getValue() > 1) {
                counter++;
            }
        }
        System.out.println(counter);
    }

    private static void insertValue(String[] high, String[] low, Map<String, Integer> board, boolean first) {
        if (first) {
            for (int i = Integer.parseInt(low[1]); i <= Integer.parseInt(high[1]); i++) {
                verticalBoardInsert(Integer.parseInt(high[0]), i, board);
            }
        } else {
            for (int i = Integer.parseInt(low[0]); i <= Integer.parseInt(high[0]); i++) {
                verticalBoardInsert(i, Integer.parseInt(high[1]), board);
            }
        }
    }

    private static void verticalBoardInsert(int newCoord0, int newCoord1, Map<String, Integer> board) {
        String newCoord;
        newCoord = newCoord0 + "," + newCoord1;
        if (board.containsKey(newCoord)) {
            board.put(newCoord, board.get(newCoord) + 1);
        } else {
            board.put(newCoord, 1);
        }
    }

    private static void diagonalBoardInsert(int newCoord0, int newCoord1, Map<String, Integer> board) {
        String newCoord;
        newCoord = newCoord0 + "," + newCoord1;
        if (board.containsKey(newCoord)) {
            board.put(newCoord, board.get(newCoord) + 1);
        } else {
            board.put(newCoord, 1);
        }
    }

    private static void insertDiagonal(String[] coord1, String[] coord2, Map<String, Integer> board) {
        int diff0 = Integer.parseInt(coord1[0]) - Integer.parseInt(coord2[0]);
        int diff0Pos = diff0 < 0 ? diff0 * -1 : diff0;
        int diff1 = Integer.parseInt(coord1[1]) - Integer.parseInt(coord2[1]);
        int diff1Pos = diff1 < 0 ? diff1 * -1 : diff1;
        if (diff0Pos == diff1Pos) {
            int newCoord0;
            int newCoord1;
            if (diff0 > 0 & diff1 > 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = Integer.parseInt(coord2[0]) + i;
                    newCoord1 = Integer.parseInt(coord2[1]) + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 < 0 & diff1 < 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = Integer.parseInt(coord1[0]) + i;
                    newCoord1 = Integer.parseInt(coord1[1]) + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 < 0 & diff1 > 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = Integer.parseInt(coord1[0]) + i;
                    newCoord1 = Integer.parseInt(coord1[1]) - i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 > 0 & diff1 < 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = Integer.parseInt(coord1[0]) - i;
                    newCoord1 = Integer.parseInt(coord1[1]) + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            }
        }
    }
}
