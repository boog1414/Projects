public class PatternAcorn extends Pattern{
    int sizeX = 7;
    int sizeY = 3;
    boolean [][] acorn = {{ false, true, false, false, false, false, false}
            ,{ false, false, false, true, false, false, false}
            ,{true, true, false, false, true, true, true}};

    public int getSizeX(){
        return sizeX;
    }
    public int getSizeY(){
        return sizeY;
    }
    public boolean getCell(int x, int y){
        return acorn[y][x];
    }
}