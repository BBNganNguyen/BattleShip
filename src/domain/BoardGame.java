package domain;

import java.util.HashSet;
import java.util.Set;

public class BoardGame {
    public static int DEFAULT_COLS = 10;
    public static int DEFAULT_ROWS = 10;
    private String[][] grid = new String[DEFAULT_COLS][DEFAULT_ROWS];

    private Set<Point> shipCoordinates = new HashSet<>();
    private int rows;
    private int cols;

    public BoardGame() {
        this.rows = DEFAULT_ROWS;
        this.cols = DEFAULT_COLS;
    }

    public BoardGame(int rows, int cols) {
        if (rows != 0 && cols != 0) {
            this.rows = rows;
            this.cols = cols;
            this.grid = new String[rows][cols];
        } else {
            new BoardGame();
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }


    public void addShipCoordinateToGrid(Set<Point> shipCoordinates, int shipNumber) {
        shipCoordinates.forEach(
                point -> {
                    grid[point.getX()][point.getY()] = String.valueOf(shipNumber);
                }
        );
    }

    public void markAttachPoint(Point point, String mark) {
        grid[point.getX()][point.getY()] = String.valueOf(mark);
    }

    public Set<Point> addShipCoordinate(Set<Point> points) {
        this.shipCoordinates.addAll(points);
        return shipCoordinates;
    }

    public Set<Point> addShipCoordinate(Point points) {
        this.shipCoordinates.add(points);
        return shipCoordinates;
    }

    public Set<Point> getShipCoordinates() {
        return shipCoordinates;
    }

    public void showOceanMap() {
        //print the cols title;
        System.out.print("  ");
        for (int i = 0; i < this.getCols(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = grid[i][j] != null ? grid[i][j] : " ";
                if (j == 0) {
                    System.out.print(i + "|" + grid[i][j] + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
