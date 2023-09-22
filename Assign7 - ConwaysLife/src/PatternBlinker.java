public class PatternBlinker extends Pattern {
    int sizeX = 1;
    int sizeY = 3;
    boolean [][] blinker = {{true}, {true}, {true}};

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    public boolean getCell(int x, int y) {
        return blinker[y][x];
    }
}