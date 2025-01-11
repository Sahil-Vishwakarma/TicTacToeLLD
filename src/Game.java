import java.util.*;

public class Game {
    Deque<Player> players;
    private Board board;

    public Game() {
        initializeGame();
    }

    void initializeGame(){
        players = new ArrayDeque<>();
        players.add(new Player("John",new PlayingPiece(Piece.O)));
        players.add(new Player("Cody",new PlayingPiece(Piece.X)));
        board = new Board(3);
    }

    void startGame(){
        boolean isWinner = false;
        Player player = null;
        while(!isWinner){
            player = players.getFirst();
            players.removeFirst();
            players.addLast(player);
            board.printBoard();
            List<Integer> rowCol = getRowCol(player, false);

            if(!board.checkSpace()) break;
            while(!board.addPiece(player.playingPiece(), rowCol.get(0), rowCol.get(1))){
                rowCol = getRowCol(player, true);
            }
            isWinner = board.checkWinner();

        }
        if(isWinner)
            System.out.println("Winner is : " + player.name() );
        else System.out.println("Game Tied");
    }

    private List<Integer> getRowCol(Player player, boolean isTryAgain) {
        Scanner scanner = new Scanner(System.in);
        String msg = showMessage(isTryAgain,player);
        System.out.println(msg);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(row);
        list.add(col);
        return list;
    }

    String showMessage( boolean isTryAgain, Player player){
        return !isTryAgain ? ("Player: " + player.name() + ", Enter row and column: ") :
                ("Incorrect Row Col, Enter row and column: ");
    }
}
