import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";
    ArrayList livingPieces = new ArrayList<Piece>();
    ArrayList deadPieces = new ArrayList<Piece>();
    ArrayList players = new ArrayList<Player>();
    int piecesRemaining;
    char[][] board;
    public Board() {
        createBoard();
        printBoard();
        populateBoard();
        printBoard();
        /*
        rook.printInfo();
        board = rook.addPieceToBoard(board);
        printBoard();
        rook.move('Z', '1');
        rook.printInfo();
        */

    }
    public void printBoard() {
        boolean coloured = false;
        System.out.println(ANSI_GREEN_BACKGROUND + "  A B C D E F G H " + ANSI_RESET);
        int n = 8;
        for (int i = 0; i < 8; i++) {
            if (coloured) {
                System.out.print(ANSI_WHITE_BACKGROUND);
                coloured = false;
            } else {
                System.out.print(ANSI_BLACK_BACKGROUND);
                coloured = true;
            }
            if (i == 7) {
                System.out.print(ANSI_RESET);
            }
            System.out.print(ANSI_GREEN_BACKGROUND + n + " ");
            for (int j = 0; j < 8; j++) {
                 if (coloured) {
                    System.out.print(ANSI_WHITE_BACKGROUND);
                    coloured = false;
                } else {
                    System.out.print(ANSI_BLACK_BACKGROUND);
                    coloured = true;
                }
                System.out.print(board[i][j] + " ");
                System.out.print(ANSI_RESET);
            }
            n--;
            System.out.println();
            System.out.print(ANSI_RESET);
        }
    }

    private void createBoard() {
        board = new char[10][10];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }


    }

    private void populateBoard() {
        Rook rook = new Rook(board,'A','1', false);
    }
    public ArrayList getLivingPieces() {
        return livingPieces;
    }

    public void setLivingPieces(ArrayList livingPieces) {
        this.livingPieces = livingPieces;
    }

    public ArrayList getDeadPieces() {
        return deadPieces;
    }

    public void setDeadPieces(ArrayList deadPieces) {
        this.deadPieces = deadPieces;
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList players) {
        this.players = players;
    }

    public int getPiecesRemaining() {
        return piecesRemaining;
    }

    public void setPiecesRemaining(int piecesRemaining) {
        this.piecesRemaining = piecesRemaining;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }




}
