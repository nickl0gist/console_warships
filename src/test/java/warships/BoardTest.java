package warships;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoardTest {

    Board board;

    @BeforeMethod
    public void setUp(){
        board = new Board(Constants.BOARD_SIZE);
    }

    @DataProvider(name = "nearbyShips")
        public Object[][] getShipsAction() {
        return new Object[][] {
                {Ship.FOUR_MASTED,4,4,false, Ship.THREE_MASTED,3,5,true},
                {Ship.FOUR_MASTED,4,4,false, Ship.TWO_MASTED,6,5,false},
                {Ship.FOUR_MASTED,4,4,false, Ship.THREE_MASTED,6,1,true},
                {Ship.FOUR_MASTED,4,4,false, Ship.THREE_MASTED,8,3,false},
           };
       }
    @Test(dataProvider = "nearbyShips")
    public void cannot_place_ships_near_each_other(Ship ship1, int x1, int y1, boolean isH1,
                                                   Ship ship2,int x2, int y2, boolean isH2) {
        board.placeShip(ship1, x1, y1, isH1);
        Assertions.assertThat(board.placeShip(ship2, x2, y2, isH2)).isFalse();
    }

    @Test
    public void can_place_ship_on_a_board(){
        board.placeShip(Ship.FOUR_MASTED, 0,0, true);
        board.placeShip(Ship.THREE_MASTED, 9,0, true);
        board.placeShip(Ship.TWO_MASTED, 9,8, true);
        board.placeShip(Ship.ONE_MASTED, 0,9, false);
        System.out.println(board.toString());
    }


    @Test
    public void cannot_place_ship_out_of_board(){
        board.placeShip(Ship.FOUR_MASTED, 10,10, false);
        board.placeShip(Ship.FOUR_MASTED, -5,-5, false);
      //  board.placeShip(Ship.FOUR_MASTED, 4,4, false);
        System.out.println(board.toString());

    }

}
