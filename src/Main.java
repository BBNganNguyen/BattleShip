import domain.BoardGame;
import domain.Player;
import service.BattleService;
import service.PlayerService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to battleship game");
        System.out.println();

        int noOfShips = 2;
        int shipLength = 3;

        PlayerService playerService = new PlayerService();

        //Step 1: create a board game
        BoardGame boardGame = new BoardGame(10, 10);
        BoardGame computerrBoardGame = new BoardGame(10, 10);

        //Step 2: deploy player
        Player player = new Player("playerA", noOfShips, shipLength ,boardGame, new BoardGame());
        playerService.deployPlayerShips(player);
        System.out.println(player.getName().toUpperCase() + "'s ocean map: ");
        player.getBoardGame().showOceanMap();

        //Step 3: deploy computer
        Player computer = new Player("Computer", noOfShips, shipLength ,computerrBoardGame, new BoardGame());
        playerService.deployPlayerShips(computer);
        System.out.println(computer.getName().toUpperCase() + "'s ocean map: ");
        computer.getBoardGame().showOceanMap();

        //Step 4: battle
        BattleService battleService = new BattleService();
        Player winner = battleService.battle(player, computer);

        //Step 5: Winner
        System.out.println("The winner is: " + winner.getName());

    }

}
