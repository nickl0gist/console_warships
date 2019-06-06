package warships;

public class Player {

    private String name;
    private Board board;
    private Board enemyBoard;
    private int points;

    public Player() {
        this.board = new Board(Constants.BOARD_SIZE);
        this.name = "test";
    }

    public Player(String name) {
        this();
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public String enemyBoardToString(){
        return enemyBoard.toString().replaceAll(Constants.MAST_SQUARE, Constants.EMPTY_SQUARE);
    }

    public boolean shot(int row, int col) {
        for (int j = 0; j < Constants.BOARD_SIZE; j++) {
            if(j == row){
                for (int i = 0; i < Constants.BOARD_SIZE; i++) {
                    if(i == col){
                        switch (enemyBoard.getBoard()[j][i]){
                            case 0:
                                enemyBoard.getBoard()[j][i] = 4;
                                return false;
                            case 1:
                                enemyBoard.getBoard()[j][i] = 2;
                                points++;
                                return true;
                            default:
                                return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setEnemyBoard(Board enemyBoard) {
        this.enemyBoard = enemyBoard;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
