public class Game {
    /*black is represented as boolean true, white is boolean false*/
    Board board;
    public Game() {
        board = new Board();
       // movePiece();
        board.updateBoard();
        board.printBoard();


    }

    public static void main(String[] args) {
        Game game = new Game();
    }
    public void movePiece() {
       board.getBlackPieces().getFirst().move('A', '7',board.whitePieces,board.blackPieces);
       System.out.println(board.getBlackPieces().getFirst().getcPos() + "" + board.getBlackPieces().getFirst().getrPos());
        board.updateBoard();
       System.out.println( board.getBoard()[0][1]);
        board.printArray();
       }

    public void nextTern() {

    }

    public void newGame() {

    }

}
