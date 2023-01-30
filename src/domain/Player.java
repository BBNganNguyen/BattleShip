package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Player {
    private String name;
    private BoardGame boardGame;
    private BoardGame enemyBoardGame;

    private int noOfShips;
    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    private List<Ship> ships = new ArrayList<>();
    private int shipLength;

    public Player(String name, int noOfShips, int shipLength, BoardGame boardGame, BoardGame enemyBoardGame) {
        this.name = name;
        this.boardGame = boardGame;
        this.enemyBoardGame = enemyBoardGame;
        this.noOfShips = noOfShips;
        this.shipLength = shipLength;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void setShipLength(int shipLength) {
        this.shipLength = shipLength;
    }

    public int getNoOfShips() {
        return noOfShips;
    }

    public void setNoOfShips(int noOfShips) {
        this.noOfShips = noOfShips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public void setBoardGame(BoardGame boardGame) {
        this.boardGame = boardGame;
    }

    public BoardGame getEnemyBoardGame() {
        return enemyBoardGame;
    }

    public void setEnemyBoardGame(BoardGame enemyBoardGame) {
        this.enemyBoardGame = enemyBoardGame;
    }


    public void addShips(int i, Set<Point> shipCoordinates) {
        this.ships.add(new Ship(i,shipCoordinates));
    }


}
