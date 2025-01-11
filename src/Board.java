public class Board {

    private final int size;
    private final Piece[][] grid;

    public Board(int size){
        this.size = size;
        this.grid = new Piece[size][size];
    }

    boolean addPiece(PlayingPiece playingPiece, int row,int col){
        if(grid[row][col] != null)
            return false;
        grid[row][col] = playingPiece.getPiece();
        return true;
    }

    void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] != null) {
                    System.out.print(" " + grid[i][j] + " ");
                } else {
                    System.out.print("   ");
                }
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < size - 1) {
                for (int j = 0; j < size; j++) {
                    System.out.print("---");
                    if (j < size - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }


    boolean checkSpace(){
        boolean flag = false;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid[i][j] == null){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    boolean checkWinner() {
        return checkRows() || checkColumns() || checkLeftDiagonal() || checkRightDiagonal();
    }

    private boolean checkRows() {
        for (int i = 0; i < size; i++) {
            boolean flag = true;
            for (int j = 1; j < size; j++) {
                if (grid[i][j] == null || !grid[i][j].equals(grid[i][j - 1])) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < size; i++) {
            boolean flag = true;
            for (int j = 1; j < size; j++) {
                if (grid[j][i] == null || !grid[j][i].equals(grid[j - 1][i])) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    private boolean checkLeftDiagonal() {
        boolean flag = true;
        for (int i = 1; i < size; i++) {
            if (grid[i][i] == null || !grid[i][i].equals(grid[i - 1][i - 1])) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private boolean checkRightDiagonal() {
        boolean flag = true;
        for (int i = 1; i < size; i++) {
            if (grid[i][size - 1 - i] == null || !grid[i][size - 1 - i].equals(grid[i - 1][size - i])) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
