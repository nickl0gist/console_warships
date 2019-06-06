package warships;

public enum Ship {
    FOUR_MASTED (4, 1),
    THREE_MASTED(3, 2),
    TWO_MASTED(2, 3),
    ONE_MASTED(1, 4);

    private final int mastQty;
    private final int itemQty;

    /**
     * @return Qty of masts of particular ship type.
     */
    public int getMastQty() {
        return mastQty;
    }

    /**
     * @return Qty of certain type of ships to be placed on a board
     */
    public int getItemQty() {
        return itemQty;
    }

    Ship(int mastQty, int itemQty) {
        this.mastQty = mastQty;
        this.itemQty = itemQty;
    }

}
