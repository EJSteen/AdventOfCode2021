package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class smokeBasin {
    private static int[][] board;
    private static final int dimension = 100;

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("src/day9/input_day9");
        Scanner scanner = new Scanner(input);
        String line;
        board = new int[dimension][dimension];
        int y = -1, x;

        while (scanner.hasNext()) {
            line = scanner.nextLine();
            x = -1;
            y++;
            String[] lineArr = line.split("");
            for (String s : lineArr) {
                x++;
                board[y][x] = Integer.parseInt(s);
            }
        }
        System.out.println("part 1: " + findLowPoints());
        System.out.println("part 2: " + findBasin());
    }

    private static int findLowPoints() {
        int points = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int point = board[j][i];
                if (isLowPoint(j, i)) {
                    points += point + 1;
                }
            }
        }
        return points;
    }

    private static int findBasin() {
        int first = 0;
        int second = 0;
        int third = 0;
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (isLowPoint(x, y)) {
                    Map<int[], Integer> neighbours = getNeighbours(x, y);
                    int size = findBasinSize(neighbours, board[x][y], -1);
                    if (size > first) {
                        third = second;
                        second = first;
                        first = size;
                    } else if (size > second) {
                        third = second;
                        second = size;
                    } else if (size > third) {
                        third = size;
                    }
                }
            }
        }
        return first * second * third;
    }

    private static int findBasinSize(Map<int[], Integer> neighbours, int point, int size) {
        for (Map.Entry<int[], Integer> neighbour : neighbours.entrySet()) {
            if (neighbour.getValue() != 9 & neighbour.getValue() > point) {
                board[neighbour.getKey()[0]][neighbour.getKey()[1]] = 9;
                size = findBasinSize(getNeighbours(neighbour.getKey()[0], neighbour.getKey()[1]), neighbour.getValue(), size);
                size++;
            }
        }
        return size;
    }

    private static Map<int[], Integer> getNeighbours(int x, int y) {
        Map<int[], Integer> neighbours = new HashMap<>();
        if (y > 0) {
            int[] coord = new int[2];
            coord[0] = x;
            coord[1] = y - 1;
            neighbours.put(coord, board[x][y - 1]);
        }
        if (y < dimension - 1) {
            int[] coord = new int[2];
            coord[0] = x;
            coord[1] = y + 1;
            neighbours.put(coord, board[x][y + 1]);
        }
        if (x > 0) {
            int[] coord = new int[2];
            coord[0] = x - 1;
            coord[1] = y;
            neighbours.put(coord, board[x - 1][y]);
        }
        if (x < dimension - 1) {
            int[] coord = new int[2];
            coord[0] = x + 1;
            coord[1] = y;
            neighbours.put(coord, board[x + 1][y]);
        }
        return neighbours;
    }

    private static boolean isLowPoint(int x, int y) {
        int point = board[x][y];
        Map<int[], Integer> neighbours = getNeighbours(x, y);
        boolean lowPoint = true;
        for (Map.Entry<int[], Integer> neighbour : neighbours.entrySet()) {
            if (neighbour.getValue() <= point) {
                lowPoint = false;
                break;
            }
        }
        return lowPoint;
    }


}
