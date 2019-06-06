package warships;

import java.util.Scanner;

public class TheGame {

    public static void main(String[] args) throws InterruptedException {

        UserAsker asker = UserAsker.getInstance();
        Scanner sc = new Scanner(System.in);
        asker.init(sc, System.out);

        System.out.println("1st Player what is your name?");
        Player player1 = new Player(sc.next());

        System.out.println("2nd Player what is your name?");
        Player player2 = new Player(sc.next());

        GameEngine ge = new GameEngine(player1, player2);

        ge.shipPlacing(player1);
        System.out.println(player1.getBoard());
        Thread.sleep(3000);
        clearScreen();

        System.out.println("Now it's time to place ships for the second player");
        ge.shipPlacing(player2);
        System.out.println(player2.getBoard());
        Thread.sleep(3000);
        clearScreen();

        System.out.println( "Congratulation! The winner is " + ge.startFight(asker).getName());
    }

    /**
     * To avoid spying to enemies board this method prints 100 empty lines
     * to simulate console clear command.
     */
    public static void clearScreen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * Instead of manually creation of players it is possible
     * to use this static methods. It will create 2 players and fill
     * their game board with standard set of Ships
     * @return array of 2 Players
     */
    public static Player[] simulatePlayers(){
        Player player1 = new Player("Harry Potter");
        Player player2 = new Player("Bilbo Begins");
        player1.getBoard().placeShip(Ship.FOUR_MASTED, 0,0, true);
        player1.getBoard().placeShip(Ship.THREE_MASTED, 2,0, true);
        player1.getBoard().placeShip(Ship.THREE_MASTED, 4,0, true);
        player1.getBoard().placeShip(Ship.TWO_MASTED, 6,0, true);
        player1.getBoard().placeShip(Ship.TWO_MASTED, 8,0, true);
        player1.getBoard().placeShip(Ship.TWO_MASTED, 1,9, true);
        player1.getBoard().placeShip(Ship.ONE_MASTED, 3,9, true);
        player1.getBoard().placeShip(Ship.ONE_MASTED, 5,9, true);
        player1.getBoard().placeShip(Ship.ONE_MASTED, 7,9, true);
        player1.getBoard().placeShip(Ship.ONE_MASTED, 9,9, true);

        player2.getBoard().placeShip(Ship.FOUR_MASTED, 0,0, false);
        player2.getBoard().placeShip(Ship.THREE_MASTED, 0,2, false);
        player2.getBoard().placeShip(Ship.THREE_MASTED, 0,4, false);
        player2.getBoard().placeShip(Ship.TWO_MASTED, 0,6, false);
        player2.getBoard().placeShip(Ship.TWO_MASTED, 0,8, false);
        player2.getBoard().placeShip(Ship.TWO_MASTED, 9,1, false);
        player2.getBoard().placeShip(Ship.ONE_MASTED, 9,3, false);
        player2.getBoard().placeShip(Ship.ONE_MASTED, 9,5, false);
        player2.getBoard().placeShip(Ship.ONE_MASTED, 9,7, false);
        player2.getBoard().placeShip(Ship.ONE_MASTED, 9,9, false);

        return new Player[] {player1, player2};
    }
}
