package warships;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.mockito.Mockito.*;


public class WarshipsGameTest {

    Player player1;
    Player player2;

    @BeforeMethod
    public void setUp(){
        player1 = new Player("Harry");
        player2 = new Player("Bob");
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

        player1.setEnemyBoard(player2.getBoard());
        player2.setEnemyBoard(player1.getBoard());
    }

    @Test
    public void print_2boards_for_player1_with_hidden_enemies_ships(){

        String test = "    ⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ \n" +
                "0 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "1 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "2 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "3 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "4 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "5 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "6 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "7 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "8 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "9 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n";
        Assertions.assertThat(player1.enemyBoardToString()).isEqualTo(test);
    }

    @Test
    public void make_shot_and_miss_target(){
        player1.shot(9,4);
        String test = "    ⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ \n" +
                "0 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "1 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "2 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "3 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "4 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "5 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "6 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "7 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "8 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "9 | ⬜ ⬜ ⬜ ⬜ ⊡ ⬜ ⬜ ⬜ ⬜ ⬜ \n";
        String test2 = "    ⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ \n" +
                "0 | ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ \n" +
                "1 | ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ \n" +
                "2 | ■ ⬜ ■ ⬜ ■ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "3 | ■ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "4 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "5 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "6 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "7 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "8 | ⬜ ■ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "9 | ⬜ ■ ⬜ ■ ⊡ ■ ⬜ ■ ⬜ ■ \n";
        Assertions.assertThat(player1.enemyBoardToString()).isEqualTo(test);
        Assertions.assertThat(player2.getBoard().toString()).isEqualTo(test2);
    }

    @Test
    public void make_shot_and_hit_target(){
        player1.shot(0,0);
        String test = "    ⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ \n" +
                "0 | ▧ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "1 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "2 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "3 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "4 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "5 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "6 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "7 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "8 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "9 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n";
        String test2 = "    ⓪ ① ② ③ ④ ⑤ ⑥ ⑦ ⑧ ⑨ \n" +
                "0 | ▧ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ \n" +
                "1 | ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ \n" +
                "2 | ■ ⬜ ■ ⬜ ■ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "3 | ■ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "4 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "5 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "6 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "7 | ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "8 | ⬜ ■ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ ⬜ \n" +
                "9 | ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ ⬜ ■ \n";
        Assertions.assertThat(player1.enemyBoardToString()).isEqualTo(test);
        Assertions.assertThat(player2.getBoard().toString()).isEqualTo(test2);
        Assertions.assertThat(player1.getPoints()).isEqualTo(1);
    }

    @Test
    public void all_players_ships_are_placed() {
        Assertions.assertThat(player1.getBoard().getShipSet().size()).isEqualTo(Constants.SHIP_QTY);
        Assertions.assertThat(player2.getBoard().getShipSet().size()).isEqualTo(Constants.SHIP_QTY);
    }

    @Test
    public void player_get_next_turn_for_hitting_other_player() {
        System.out.println(player1.getBoard().toString());
        System.out.println(player1.enemyBoardToString());
    }

    /**
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * The Mockito used to simulate answer from the player with coordinates of the shot.
     * As soon as method shot() is private in GameEngine.class I used reflection for this particular method
     * to make it private and be able use this method directly.
     */
    @Test
    public void player_misses_shot_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GameEngine ge = new GameEngine(player1, player2);
        UserAsker asker = mock(UserAsker.class);
        asker.init(new Scanner(System.in), System.out);
        when(asker.ask(player1.getName() + " Provide coordinates of your shot [row" + Constants.DIVIDER
                + "column] between 0 and " + Constants.BOARD_SIZE)).thenReturn("5/5");
        Method shot_OfGameEngine = GameEngine.class.getDeclaredMethod("shot", Player.class, UserAsker.class);
        shot_OfGameEngine.setAccessible(true);
        Boolean result = (Boolean) shot_OfGameEngine.invoke(ge, player1, asker);
        Assertions.assertThat(result).isFalse();
    }

    /**
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * Same as player_misses_shot_test()
     */
    @Test
    public void player_hits_the_target_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        GameEngine ge = new GameEngine(player1, player2);
        UserAsker asker = mock(UserAsker.class);
        asker.init(new Scanner(System.in), System.out);
        when(asker.ask(player1.getName() + " Provide coordinates of your shot [row" + Constants.DIVIDER
                + "column] between 0 and " + Constants.BOARD_SIZE)).thenReturn("0/0");
        Method shot_OfGameEngine = GameEngine.class.getDeclaredMethod("shot", Player.class, UserAsker.class);
        shot_OfGameEngine.setAccessible(true);
        Boolean result = (Boolean) shot_OfGameEngine.invoke(ge, player1, asker);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void player_wins() throws InterruptedException {
        UserAsker asker = mock(UserAsker.class);
        Player player = mock(Player.class);
        GameEngine ge = new GameEngine(player, player2);
        asker.init(new Scanner(System.in), System.out);
        //when(asker.ask(player.getName() + " Provide coordinates of your shot [row" + Constants.DIVIDER
        //        + "column] between 0 and " + Constants.BOARD_SIZE)).thenReturn("0/0");
        when(player.getPoints()).thenReturn(20);
        //when(player.getName()).thenReturn("test");
        Assertions.assertThat(ge.startFight(asker).getName()).isEqualTo(player.getName());

    }

}
