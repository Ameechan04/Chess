import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String WHITE_BOLD = "\033[1;97m"; // WHITE
    public static final String ANSI_BLUE = "\u001B[34m";

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";
    ArrayList<Piece> livingPieces = new ArrayList<Piece>();
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    ArrayList<Piece> blackPieces = new ArrayList<Piece>();

    ArrayList deadPieces = new ArrayList<Piece>();
    ArrayList players = new ArrayList<Player>();
    int piecesRemaining;
    char[][] board;
    public Board() {
        createBoard();
        //printBoard();
        populateBoard();
        updateBoard();
    //printBoard();


    }
    public void printBoard() {
        boolean coloured = false;
        System.out.println(ANSI_GREEN_BACKGROUND + "  A  B  C  D  E  F  G  H  " + ANSI_RESET);
        int n = 8;
        for (int r = 0; r < 8; r++) {
            if (coloured) {
                System.out.print(ANSI_WHITE_BACKGROUND);
                coloured = false;
            } else {
                System.out.print(ANSI_BLACK_BACKGROUND);
                coloured = true;
            }
            if (r == 7) {
                System.out.print(ANSI_RESET);
            }
            System.out.print(ANSI_GREEN_BACKGROUND + n + " ");
            for (int c = 0; c < 8; c++) {
                 if (coloured) {
                    System.out.print(ANSI_WHITE_BACKGROUND);
                    coloured = false;
                } else {
                    System.out.print(BLACK_BACKGROUND_BRIGHT);
                    coloured = true;
                }

                 if (board[r][c] == ' ') {
                     System.out.print(board[r][c] + "  ");
                 } else {
                     printPiece(r,c);
                 }
                 System.out.print(ANSI_RESET);
                 System.out.print(ANSI_RESET);
            }
            n--;
            System.out.println();
            System.out.print(ANSI_RESET);
        }
    }

    public void printPiece(int r, int c){
        for (Piece whitePiece : whitePieces) {
            if (convertRow(whitePiece.getrPos()) == r && convertColumn(whitePiece.getcPos()) == c) {

                    System.out.print(WHITE_BOLD + " " + whitePiece.getType() + " ");
                break;
            }
        }
        for (Piece blackPiece : blackPieces) {
            if (convertRow(blackPiece.getrPos()) == r && convertColumn(blackPiece.getcPos()) == c) {
                System.out.print(BLACK_BOLD + " " + blackPiece.getType() + " ");
                break;
            }
        }
    }

    private void createBoard() {
        board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }


    }



    private void populateBoard() {
        whitePieces.add(new Rook('A','1', "white"));
        whitePieces.add(new Rook('H','1', "white"));
        whitePieces.add(new Knight('B', '1', "white"));
        whitePieces.add(new Knight('G', '1', "white"));
        whitePieces.add(new Bishop('C', '1', "white"));
        whitePieces.add(new Bishop('F', '1', "white"));
        whitePieces.add(new King('E', '1', "white"));
        whitePieces.add(new Queen('D', '1', "white"));

        blackPieces.add(new Rook('A','8', "black"));
        blackPieces.add(new Rook('H','8', "black"));
       blackPieces.add(new Knight('B', '8', "black"));
        blackPieces.add(new Knight('G', '8', "black"));
        blackPieces.add(new Bishop('C', '8', "black"));
        blackPieces.add(new Bishop('F', '8', "black"));
        blackPieces.add(new King('E', '8', "black"));
        blackPieces.add(new Queen('D', '8', "black"));

       // blackPieces.add(new Rook('B','3', "black", '2'));
        char currentChar = 'A';

       for (int i = 0; i < 8; i++) {

            whitePieces.add(new WhitePawn(currentChar));
            blackPieces.add(new BlackPawn(currentChar));
            currentChar = (char) (currentChar + 1);

        }


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

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public void setWhitePieces(ArrayList<Piece> whitePieces) {
        this.whitePieces = whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

    public void setBlackPieces(ArrayList<Piece> blackPieces) {
        this.blackPieces = blackPieces;
    }

    /*reads in the two piece arrays and adds their letters and current positions to the board*/
    public void updateBoard() {
        //reset board before adding new pieces
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c] = ' ';
            }
        }

        for (Piece blackPiece : blackPieces) {
            int c = convertColumn(blackPiece.getcPos());
            int r = convertRow(blackPiece.getrPos());
            board[r][c] = blackPiece.getType();
        }
        for (Piece whitePiece : whitePieces) {
            int c = convertColumn(whitePiece.getcPos());
            int r = convertRow(whitePiece.getrPos());
            board[r][c] = whitePiece.getType();
        }
    }

    public int convertColumn(char x) {
       //System.out.println(x + "->" + (x - 65));
        return x - 65;
    }

    public int convertRow(char y) {
      //System.out.println(y + "->" + ( 8 - (y - '0')));
        return 8 - (y - '0');
    }
    public void printArray() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] == ' ') {
                    System.out.print('#');
                } else {
                    System.out.print(board[r][c]);
                }
            }
            System.out.println();
        }

    }



}
