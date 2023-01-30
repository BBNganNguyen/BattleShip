package service;

import domain.BoardGame;
import domain.Player;
import domain.Point;
import java.util.Scanner;

public class BattleService {

    //return winner
    public Player battle(Player player, Player computer) {
        do {

            //player turn
            playerTurn(player, computer);
            computerTurn(computer, player);

            //computer turn

        } while (!player.getBoardGame().getShipCoordinates().isEmpty()
                && !computer.getBoardGame().getShipCoordinates().isEmpty());
        return player.getBoardGame().getShipCoordinates().isEmpty() ?
                computer : player;
    }

    public void computerTurn(Player computer, Player player) {
        BoardGame enemyBoardGame = computer.getEnemyBoardGame();
        System.out.println(computer.getName().toUpperCase() + "'s turn:");
        System.out.println("Computer's enemy ocean map: ");
        enemyBoardGame.showOceanMap();
        Point attackPoint = null;
        do {
            attackPoint = generateAttachPoint();

            if (!isInvalidInput(enemyBoardGame, attackPoint.getX(), attackPoint.getY())) {
                if (player.getBoardGame().getShipCoordinates().contains(attackPoint)) {
                    enemyBoardGame.markAttachPoint(attackPoint, "H");
                    player.getBoardGame().getShipCoordinates().remove(attackPoint);
                } else {
                    enemyBoardGame.markAttachPoint(attackPoint, "M");
                    enemyBoardGame.addShipCoordinate(attackPoint);
                }
            }
        } while (isInvalidInput(enemyBoardGame, attackPoint.getX(), attackPoint.getY()));

    }

    public void playerTurn(Player player, Player computer) {
        BoardGame enemyBoardGame = player.getEnemyBoardGame();
        System.out.println(player.getName().toUpperCase() + "'s turn:");
        System.out.println("Look at your enemy ocean map before you guess: ");
        enemyBoardGame.showOceanMap();

        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();

            if (!isInvalidInput(enemyBoardGame, x, y)) {
                Point attackPoint = new Point(x, y);
                if (computer.getBoardGame().getShipCoordinates().contains(attackPoint)) {
                    enemyBoardGame.markAttachPoint(attackPoint, "H");
                    computer.getBoardGame().getShipCoordinates().remove(attackPoint);
                } else {
                    enemyBoardGame.markAttachPoint(attackPoint, "M");
                    enemyBoardGame.addShipCoordinate(attackPoint);
                }
            }

        } while (isInvalidInput(enemyBoardGame, x, y)); //keep scanning input until get valid input

    }

    private boolean isInvalidInput(BoardGame enemyBoardGame, int x, int y) {
        return x < 0 || x >= enemyBoardGame.getRows()
                || y < 0 || y >= enemyBoardGame.getCols();
    }


    private Point generateAttachPoint() {
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        return new Point(x, y);
    }


}
