package warships;

import java.util.Scanner;

public class GameEngine {

    Player p1, p2;
    Scanner sc;

    public GameEngine(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        sc = new Scanner(System.in);
    }

    /**
     * @param p - instance of Player class
     * method is used for placing ships for particular player which has to be given as parameter p
     * This method use placeShip(Ship ship, int x, int itemQty, Player p)
     */
    public void shipPlacing(Player p){
        System.out.println("Hello, " + p.getName() + ". It's time to place your ships.");
        placeShip(Ship.FOUR_MASTED, Ship.FOUR_MASTED.getItemQty(), p);
        placeShip(Ship.THREE_MASTED, Ship.THREE_MASTED.getItemQty(), p);
        placeShip(Ship.TWO_MASTED, Ship.TWO_MASTED.getItemQty(), p);
        placeShip(Ship.ONE_MASTED, Ship.ONE_MASTED.getItemQty(), p);
    }

    /**
     * Method is used by shipPlacing(Player p) for placing set of Ships recursively for each certain type of Ships
     * which has to be given as parameter ship
     * @param ship - type of Ship
     * @param itemQty - qty of Ships to be placed
     * @param p - The player instance. In players game board ships will be placed
     */
    private void placeShip(Ship ship, int itemQty, Player p){
        if (itemQty > 0){
            boolean isPlaced;
        do{
            System.out.println("Give me row and column number for the head of your " + ship.getMastQty() + "-Masted ship in such format 5"+Constants.DIVIDER+"5");
            System.out.println(p.getBoard().toString());
            String coordinates = sc.next();
            String[] arr = coordinates.split(Constants.DIVIDER,2);
            System.out.println("Do you want to place it horizontally [1] or vertically [0]?");
            isPlaced = p.getBoard().placeShip(ship, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), sc.nextInt() == 1);
        }while(!isPlaced);
        placeShip(ship, --itemQty, p);
        }
    }

    /**
     * @param userAsker - singleton of userAsker which asks player to do shot and
     * @return player which will take enough points to win the game. The game ends after method finished .
     * @throws InterruptedException
     */
    public Player startFight(UserAsker userAsker) throws InterruptedException {

        Player p;
        boolean turn = true;

        p1.setEnemyBoard(p2.getBoard());
        p2.setEnemyBoard(p1.getBoard());
        System.out.println("The game ends when any player will take " + getPointsQtyToWin() + " points");
        System.out.println("Let's make a first shot. ");

        while (p1.getPoints() < getPointsQtyToWin() &&
                p2.getPoints() < getPointsQtyToWin()){

            p = turn ? p1 : p2;
            preShot(p);
            if(shot(p, userAsker)){
                System.out.println("Good shot. Make one more!");
            } else {
                System.out.println("Next time you should aim better!");
                turn = !turn;
            }
            Thread.sleep(1000);
        }

        return (p1.getPoints() == getPointsQtyToWin()) ? p1 : p2;
    }

    /**
     * Prints messages before Player makes his shot.
     * @param p - Player instance which makes a shot.
     */
    private void preShot(Player p){
        StringBuilder str = new StringBuilder();
        str.append(p.getName());
        str.append(" it's your turn \n");
        str.append("Now you have " + p.getPoints() + " points \n");
        str.append("Your board: \n");
        str.append(p.getBoard().toString() + "\n");
        str.append("The board of your enemy \n");
        str.append(p.enemyBoardToString());
        System.out.println(str);
    }

    /**
     * @param p - Player instance
     * @param userAsker - userAsker is for asking user to make a shot by providing of coordinates in format
     *                  [row + DIVIDER + column]. The provided String splits to String array of 2 elements.
     *                  Each element parsed to int.
     * @return True - is any mast of enemy was hited, False - if wasn't
     */
    private boolean shot(Player p, UserAsker userAsker){
        String[] arr;
        do{
            arr = userAsker.ask(p.getName() + " Provide coordinates of your shot [row" + Constants.DIVIDER
                                           + "column] between 0 and " + Constants.BOARD_SIZE).split(Constants.DIVIDER,2);

        }while(!((0 <= Integer.parseInt(arr[0])) && (Integer.parseInt(arr[0]) < Constants.BOARD_SIZE) &&
                (0 <= Integer.parseInt(arr[1])) && (Integer.parseInt(arr[1]) < Constants.BOARD_SIZE)));

        return p.shot(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
    }

    /**
     * @return  - int, Qty of points to win the game
     */
    private int getPointsQtyToWin(){
        return Ship.FOUR_MASTED.getItemQty()*Ship.FOUR_MASTED.getMastQty()+
                Ship.THREE_MASTED.getItemQty()*Ship.THREE_MASTED.getMastQty()+
                Ship.TWO_MASTED.getItemQty()*Ship.TWO_MASTED.getMastQty()+
                Ship.ONE_MASTED.getItemQty()*Ship.ONE_MASTED.getMastQty();
    }

}



