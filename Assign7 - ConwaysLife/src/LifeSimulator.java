public class LifeSimulator {
    int sizeX;
    int sizeY;
    boolean[][] grid;

    public LifeSimulator(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        grid = new boolean[sizeY][sizeX];
        for (int r = 0; r < sizeY; r++) {
            for (int c = 0; c < sizeX; c++) {
                grid[r][c] = false;
            }
        }
    }


    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public boolean getCell(int x, int y) {
        return grid[y][x];
    }

    public void insertPattern(Pattern pattern, int startX, int startY) {
        for (int y = startY; y < startY + pattern.getSizeY(); y++) {
            for (int x = startX; x < startX + pattern.getSizeX(); x++) {
                if(x - startX < getSizeX() && y - startY < getSizeY()){
                    grid[y][x] = pattern.getCell(x - startX, y - startY);
                }
            }
        }
    }

    public void update() {
        boolean[][] updated = new boolean[getSizeY()][getSizeX()];
        for (int r = 0; r < getSizeY(); r++) {
            for (int c = 0; c < getSizeX(); c++) {
                int count = 0;
                if ((c - 1 >= 0) && (r - 1 >= 0)) {
                    if (getCell(c - 1, r - 1)) {
                        count += 1;
                    }
                }
                if (c - 1 >= 0){
                    if (getCell(c - 1, r)) {
                        count += 1;
                    }
                }
                if ((c - 1 >= 0) && (r + 1 <= getSizeY() - 1)) {
                    if (getCell(c - 1, r + 1)) {
                        count += 1;
                    }
                }
                if (r - 1 >= 0) {
                    if (getCell(c, r - 1)) {
                        count += 1;
                    }
                }
                if ((r + 1 <= getSizeY() - 1)) {
                    if (getCell(c, r + 1)) {
                        count += 1;
                    }
                }
                if ((c + 1 <= getSizeX() - 1) && (r - 1 >= 0)){
                    if (getCell(c + 1, r - 1)) {
                        count += 1;
                    }
                }
                if (c + 1 <= getSizeX() - 1){
                    if (getCell(c + 1, r)) {
                        count += 1;
                    }
                }
                if ((c + 1 <= getSizeX() - 1) && (r + 1 <= getSizeY() - 1)) {
                    if (getCell(c + 1, r + 1)) {
                        count += 1;
                    }
                }
                if ((getCell(c, r)) && (count < 2)){
                    updated[r][c] = false;
                }
                if((getCell(c, r)) && (count == 2) || (count == 3)){
                    updated[r][c] = true;
                }
                if((getCell(c, r)) && (count > 3)){
                    updated[r][c] = false;
                }
                if (!getCell(c, r) && (count == 3)) {
                    updated[r][c] = true;
                }
            }

        }
        grid = updated;
    }
}