public class PatternBlock extends Pattern {
    int sizeX = 2;
    int sizeY = 2;
    boolean [][] block = {{true, true}, {true, true}}
    ;

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
    public boolean getCell(int x, int y) {
        return block[y][x];
    }
}