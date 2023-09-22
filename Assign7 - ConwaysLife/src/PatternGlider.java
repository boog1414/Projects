public class PatternGlider extends Pattern {
    int sizeX = 3;
    int sizeY = 3;
    boolean [][] glider = {{false, false, true}, {true, false, true}, {false, true, true}};

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }
    public boolean getCell(int x, int y) {
        return glider[y][x];
    }
}