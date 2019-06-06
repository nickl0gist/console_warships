package warships;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class WarshipTest {

    Player player;

    @BeforeMethod
    public void setUp(){
        player = new Player();
    }

    @Test
    public void ship_is_placed_on_board() {
        player.getBoard().placeShip(Ship.FOUR_MASTED, 5, 7, false);
//        //assertions
        Assertions.assertThat(player.getBoard().getShipSet().size()).isEqualTo(1);
    }

    @DataProvider(name = "ships")
    public Object[][] ships_to_place() {
        return new Object[][] {
                {Ship.FOUR_MASTED, 0, 0,false},
                {Ship.THREE_MASTED, 1, 5, true},
                {Ship.THREE_MASTED, 7, 5, false},
                {Ship.TWO_MASTED, 0, 9, true},
                {Ship.TWO_MASTED, 6, 3, true},
                {Ship.TWO_MASTED, 8, 5, false},
                {Ship.ONE_MASTED, 2, 2, false},
                {Ship.ONE_MASTED, 3, 4, true},
                {Ship.ONE_MASTED, 2, 7, false},
                {Ship.ONE_MASTED, 9, 0, true}
        };
    }

    @Test(dataProvider = "ships")
    public void places_ship_for_valid_XY(Ship ship, int x, int y, boolean isH) {
        player.getBoard().placeShip(ship, x, y, isH);
    }

    @Ignore
    @Test(dataProvider = "", expectedExceptions = IllegalArgumentException.class)
    public void cannot_place_ship_for_invalid_XY() {
    }

    @DataProvider(name = "validX2Y2")
    public Object[][] validPositions() {
        return new Object[][] {
                {Ship.FOUR_MASTED, -1, 0,false},
                {Ship.THREE_MASTED, 15, 5, true},
                {Ship.THREE_MASTED, 18, 5, false},
                {Ship.TWO_MASTED, 100, 9, true},
                {Ship.TWO_MASTED, 6, -33, true},
                {Ship.TWO_MASTED, 80, 155, false},
                {Ship.ONE_MASTED, 200, 2, false},
                {Ship.ONE_MASTED, 3, 44, true},
                {Ship.ONE_MASTED, -250, 7, false},
                {Ship.ONE_MASTED, -89, 0, true}
        };
    }

    @Test(dataProvider = "validX2Y2")
    public void calulcate_fine_x_y_position(Ship ship, int x, int y, boolean isH) {
        player.getBoard().placeShip(ship, x, y, isH);
    }

    @DataProvider(name = "shipsStats")
    public Object[][] getShipsTypes() {
        return new Object[][] {
                {Ship.FOUR_MASTED, 4},
                {Ship.THREE_MASTED, 3},
                {Ship.TWO_MASTED, 2},
                {Ship.ONE_MASTED, 1},
        };
    }

    @Test(dataProvider = "shipsStats")
    public void ships_of_different_types_has_according_attributes(Ship ship, int masts) {
        Assertions.assertThat(ship.getMastQty()).isEqualTo(masts);
    }

}
