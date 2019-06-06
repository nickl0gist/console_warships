package warships;

public class Point {

    //pola
    private int x;
    private int y;
    private Integer status;

    public Point() {}

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //metody
    //(1,1)
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    //[e1, e2]
    public int[] getXY() {
        int[] xy = {x, y};
        return xy;
    }

    public void printXYArray() {
        int[] xy = getXY();
        System.out.print("[" + xy[0] + ", " + xy[1] + "]");
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(int xOther, int yOther) {
        return Math.sqrt(Math.pow(xOther-this.x, 2) + Math.pow(yOther-this.y, 2));
    }

    public double distance(Point other) {
        return distance(other.getX(), other.getY());
    }

    public double distance() {
        return distance(0, 0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
