package warships;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Integer[][] board;
    private int qtyOfRowsAndCols;
    private List<Ship> shipSet;


    public Board(int qtyOfRowsAndCols) {
        shipSet = new ArrayList<>();
        this.qtyOfRowsAndCols = qtyOfRowsAndCols;
        this.board = new Integer[qtyOfRowsAndCols][qtyOfRowsAndCols];
        for (int i = 0; i < qtyOfRowsAndCols; i++) {
            for (int j = 0; j < qtyOfRowsAndCols; j++) {
                board[i][j] = 0;
            }
        }

    }

    /**
     * method to place very left and top mast of the ship into particular place of a board array,
     * the rest of masts will be placed to the right or below depending on direction (isHorisontal)
     * @param ship - ship Enum
     * @param row - int index of row.
     *                if isHorisontal = true
     *            If row is bigger than board size, row value will be changed acc. to
     *            qtyOfRowsAndCols-1. In case if row is less than size, it will be changed
     *            to 0;
     *                if isHorisontal = false
     *            If row is bigger than (qty of rows)-(size of ship) it will be changed to
     *            (qty of rows)-(size of ship). if less - the value will be changed to 0;
     *
     * @param col - int index of column
     * @param isHorisontal - boolean, defines direction of ship
     *           (true) - if ship should be placed horizontally,
     *           (false) - vertically
     */
    public boolean placeShip(Ship ship, int row, int col, boolean isHorisontal) {

        if(this.shipSet.size() >= Constants.SHIP_QTY){
            return false;
        }

        col = (col < 0) ? 0 : col;
        row = (row < 0) ? 0 : row;

        if(isHorisontal){
            return placeShipHorizontally(ship, row, col);
        } else {
            return placeShipVertically(ship, row, col);
        }
    }

    private boolean placeShipHorizontally(Ship ship, int row, int col){
        col = (col > qtyOfRowsAndCols-ship.getMastQty()) ? qtyOfRowsAndCols-ship.getMastQty() : col;
        row = (row > qtyOfRowsAndCols-1) ? qtyOfRowsAndCols-1 : row;
        for (int i = 0; i < qtyOfRowsAndCols; i++) {
            if( i == row){ //find row
                for (int j = 0; j < qtyOfRowsAndCols; j++) {
                    if (j == col){ //find col
                        if (board[i][j] == 0 && isItPossibleToPlace(ship, col, row, true)){
                            this.shipSet.add(ship);
                            board[i][j] = 1;
                            for (int k = j+1; k < ship.getMastQty() + j; k++) {
                                board[i][k] = 1;
                            }
                            return true;
                        } else {
                            System.out.println("The ship cannot be placed here, try another cell");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean placeShipVertically(Ship ship, int row, int col){
        row = (row > qtyOfRowsAndCols-ship.getMastQty()) ? qtyOfRowsAndCols-ship.getMastQty() : row;
        col = (col > qtyOfRowsAndCols-1) ? qtyOfRowsAndCols-1 : col;
        for (int i = 0; i < qtyOfRowsAndCols; i++) {
            if( i == row ){ //find row
                for (int j = 0; j < qtyOfRowsAndCols; j++) {
                    if (j == col){ //find col
                        if (board[i][j] == 0 && isItPossibleToPlace(ship, col, row, false)){
                            this.shipSet.add(ship);
                            board[i][j] = 1;
                            for (int k = i+1; k < ship.getMastQty() + i; k++) {
                                board[k][j] = 1;
                            }
                            return true;
                        } else {
                            System.out.println("The ship cannot be placed here, try another cell");
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * isItPossibleToPlace is for checking of possibility to place particular ship on a board
     * depending on other already placed ships
     * @param ship - Ship entity
     * @param col - index of column to place head of the Ship
     * @param row - index of row to place head of the Ship
     * @param isHorisontal - is it Horizontally or Vertically placed
     * @return booolean.
     */
    private boolean isItPossibleToPlace(Ship ship, int col, int row, boolean isHorisontal) {
            if( isHorisontal ){
                for (int i = row -1; i <= row + 1; i++) {// row
                    if(i < 0 || i > qtyOfRowsAndCols-1)
                        continue;
                    for (int j = col-1; j <= col+ship.getMastQty(); j++) { // column
                        if(j < 0 || j > qtyOfRowsAndCols - 1)
                            continue;
                        if(board[i][j] != 0)
                            return false;
                    }
                }
                return true;
            } else {
                for (int i = row -1; i <= row+ship.getMastQty(); i++) {// row
                    if(i < 0 || i > qtyOfRowsAndCols-1)
                        continue;
                    for (int j = col -1; j <= col+1; j++) { // column
                        if(j < 0 || j > qtyOfRowsAndCols - 1)
                            continue;
                        if(board[i][j] != 0)
                            return false;
                    }
                }
            }
            return true;
    }

    public List<Ship> getShipSet() {
        return shipSet;
    }

    /**
     * @return string of current state of a board in 2d
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("    \u24EA \u2460 \u2461 \u2462 \u2463 \u2464 \u2465 \u2466 \u2467 \u2468 \n"); //"    0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | \n"
        for (int r = 0; r < qtyOfRowsAndCols; r++) {
            str.append(r + " | ");
            for (int j = 0; j < qtyOfRowsAndCols; j++) {
                if(board[r][j] == 1){
                    str.append(Constants.MAST_SQUARE); //
                } else if (board[r][j] == 2){
                    str.append(Constants.HITTED_SQUARE); //
                } else if (board[r][j] == 3){
                    str.append(Constants.SINKED_SQUARE); //
                } else if (board[r][j] == 4){
                    str.append(Constants.MISSED_SQUARE);
                } else {
                    str.append(Constants.EMPTY_SQUARE); //
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    public Integer[][] getBoard() {
        return board;
    }
}
