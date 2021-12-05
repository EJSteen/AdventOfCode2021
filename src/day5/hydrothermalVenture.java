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
            Integer[] coord1 = new Integer[2];
            Integer[] coord2 = new Integer[2];
            coord1[0] = Integer.parseInt(coords[0].split(",")[0]); coord1[1] = Integer.parseInt(coords[0].split(",")[1]);
            coord2[0] = Integer.parseInt(coords[1].split(",")[0]); coord2[1] = Integer.parseInt(coords[1].split(",")[1]);
            if (coord1[0].equals(coord2[0])) {
                if (coord1[1] > coord2[1]) {
                    insertValue(coord1, coord2, board, true);
                } else {
                    insertValue(coord2, coord1, board, true);
                }
            }
            if (coord1[1].equals(coord2[1])) {
                if (coord1[0] > coord2[0]) {
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

    private static void insertValue(Integer[] high, Integer[] low, Map<String, Integer> board, boolean first) {
        if (first) {
            for (int i = low[1]; i <= high[1]; i++) {
                verticalBoardInsert(high[0], i, board);
            }
        } else {
            for (int i = low[0]; i <= high[0]; i++) {
                verticalBoardInsert(i, high[1], board);
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

    private static void insertDiagonal(Integer[] coord1, Integer[] coord2, Map<String, Integer> board) {
        int diff0 = coord1[0] - coord2[0];
        int diff0Pos = diff0 < 0 ? diff0 * -1 : diff0;
        int diff1 = coord1[1] - coord2[1];
        int diff1Pos = diff1 < 0 ? diff1 * -1 : diff1;
        if (diff0Pos == diff1Pos) {
            int newCoord0;
            int newCoord1;
            if (diff0 > 0 & diff1 > 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = coord2[0] + i;
                    newCoord1 = coord2[1] + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 < 0 & diff1 < 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = coord1[0] + i;
                    newCoord1 = coord1[1] + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 < 0 & diff1 > 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = coord1[0] + i;
                    newCoord1 = coord1[1] - i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            } else if (diff0 > 0 & diff1 < 0) {
                for (int i = 0; i <= diff0Pos; i++) {
                    newCoord0 = coord1[0] - i;
                    newCoord1 = coord1[1] + i;
                    diagonalBoardInsert(newCoord0, newCoord1, board);
                }
            }
        }
    }
}
