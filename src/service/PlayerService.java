package service;

import domain.BoardGame;
import domain.Player;
import domain.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerService {


    public void deployPlayerShips(Player player) {
        if (player.getName() == "Computer") {
            deployComputerShips(player);
        } else {
            deployNormalPlayerShips(player);
        }
    }

    private void deployComputerShips(Player player) {
        int i = 1;
        do {
            int shipLength = player.getShipLength();

            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            Point headPoint = new Point(x, y);

            Set<Point> validTailsCoordinates = calculateShipTailCoordinate(headPoint, player, shipLength);

            if (!validTailsCoordinates.isEmpty()) {
                Point tailPoint = validTailsCoordinates
                        .stream()
                        .findFirst().get();

                Set<Point> shipCoordinates = calculateShipCoordinates(headPoint, tailPoint);
                if (Collections.disjoint(player.getBoardGame().getShipCoordinates(), shipCoordinates)) {

                    player.getBoardGame().addShipCoordinate(shipCoordinates);
                    loadShipCoordinatesToPlayerGrid(shipCoordinates, player, i);
                    i++;
                }
            }

        } while (i <= player.getNoOfShips());
    }

    public void deployNormalPlayerShips(Player player) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDeploy your ships:");

        BoardGame playerBoardGame = player.getBoardGame();

        for (int i = 1; i <= player.getNoOfShips();) {

            int shipLength = player.getShipLength();
            boolean isInvalid = true;

            System.out.print("Enter X coordinate for your " + i + " head's ship: ");
            int x = input.nextInt();

            System.out.print("Enter Y coordinate for your " + i + " head's ship: ");
            int y = input.nextInt();
            isInvalid = isInvalidInput(playerBoardGame, x, y);

            Point headShip = new Point(x, y);
            if(!isInvalid) {
                Set<Point> validShipTailCoordinate = calculateShipTailCoordinate(headShip, player, shipLength);
                printValidShipTailCoordinates(validShipTailCoordinate);
                System.out.print("Enter X coordinate for your " + i + " tail's ship: ");
                int m = input.nextInt();

                System.out.print("Enter Y coordinate for your " + i + " tail's ship: ");
                int n = input.nextInt();
                isInvalid = isInvalidInput(playerBoardGame, x, y);

                Point tailShip = new Point(m, n);

                //validate ship coordinate
                if(validShipTailCoordinate.contains(tailShip)) {
                    Set<Point> shipCoordinates = calculateShipCoordinates(headShip, tailShip);
                    if (!isInvalid) {
                        if (Collections.disjoint(player.getBoardGame().getShipCoordinates(), shipCoordinates)) {
                            player.addShips(i, shipCoordinates);
                            player.getBoardGame().addShipCoordinate(shipCoordinates);
                            loadShipCoordinatesToPlayerGrid(shipCoordinates, player, i);
                            i++;
                        } else {
                            System.out.println("Your new ship is crossed other ship, please input again");
                        }
                    }
                } else {
                    System.out.println("Your input for tail ship is not valid, please input again");
                }


            }
        }
    }

    private boolean isInvalidInput(BoardGame enemyBoardGame, int x, int y) {
        return x < 0 || x >= enemyBoardGame.getRows()
                || y < 0 || y >= enemyBoardGame.getCols();
    }

    private void loadShipCoordinatesToPlayerGrid(Set<Point> shipCoordinates, Player player, int shipNumber) {
        player.getBoardGame().addShipCoordinateToGrid(shipCoordinates, shipNumber);
    }

    private void printValidShipTailCoordinates(Set<Point> validShipTailCoordinate) {
        System.out.println("Your tail ship should be in these : ");
        String printObject = validShipTailCoordinate
                .stream()
                .map(point -> "{" + point.getX() + ":" + point.getY() + "}")
                .collect(Collectors.joining(","));
        System.out.println(printObject);

    }

    private Set<Point> calculateShipCoordinates(Point headShip, Point tailShip) {
        Set<Point> shipCoordinates = new HashSet<>();
        //the ship is horizontal head's x == tail's
        //else the ship should be vertical
        if (headShip.getX() == tailShip.getX()) {
            int minY = Math.min(headShip.getY(), tailShip.getY());
            int maxY = Math.max(headShip.getY(), tailShip.getY());
            for (int i = minY; i <= maxY; i++) {
                shipCoordinates.add(new Point(headShip.getX(), i));
            }
        } else {
            int maxX = Math.max(headShip.getX(), tailShip.getX());
            int minX = Math.min(headShip.getX(), tailShip.getX());
            for (int i = minX; i <= maxX; i++) {
                shipCoordinates.add(new Point(i, headShip.getY()));
            }
        }
        return shipCoordinates;
    }


    //Calculate the tail of ship
    //For example Head of Ship has coordinate (3,2)  and length = 2 -> TailShip can be 4 coordinate belows:
    //(1,2),(5,),(3,0),(3,4)
    private Set<Point> calculateShipTailCoordinate(Point point, Player player, int shipLength) {
        Set<Point> validTailShipCoordinates = new HashSet<>();
        if (point.getX() + shipLength >= 0
                && point.getX() + shipLength < player.getBoardGame().getRows()) {
            validTailShipCoordinates.add(new Point(point.getX() + shipLength - 1, point.getY()));
        }
        if (point.getX() - shipLength + 1 >= 0
                && point.getX() - shipLength < player.getBoardGame().getRows()) {
            validTailShipCoordinates.add(new Point(point.getX() - shipLength + 1, point.getY()));
        }
        if (point.getY() + shipLength >= 0
                && point.getY() + shipLength < player.getBoardGame().getCols()) {
            validTailShipCoordinates.add(new Point(point.getX(), point.getY() + shipLength - 1));
        }
        if (point.getY() - shipLength >= 0
                && point.getY() - shipLength < player.getBoardGame().getCols()) {
            validTailShipCoordinates.add(new Point(point.getX(), point.getY() - shipLength + 1));
        }
        return validTailShipCoordinates;
    }


}
