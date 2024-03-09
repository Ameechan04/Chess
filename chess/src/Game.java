import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner input;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    ArrayList<Player> players;
    int turn;
    /*black is represented as boolean true, white is boolean false*/
    Board board;
    public Game() {
        players = new ArrayList<>();
        players.add(new Player("User1", "white"));
        players.add(new Player("User2", "black"));
        turn = 1;


        newGame();
     //  movePiece();
      //  board.updateBoard();
       // board.printBoard();


    }

    public static void main(String[] args) {
        input = new Scanner(System.in);
        Game game = new Game();
    }
    public boolean movePiece(char C, char R, char type, String player) {
        String in;
        char ID = 0;
        if (type == 'P'|| type == 'R' ||type == 'B'|| type == 'K') {
            System.out.println("Enter which " + type + " to move: ");
            in = input.nextLine();
            ID = in.charAt(0);
        }
        if (player.equals("white")) {
          for (int i = 0; i < board.getWhitePieces().size(); i++){
                if (board.getWhitePieces().get(i).getType() == type) {
                  //  System.out.println("MATCH FOUND");
                    if (board.getWhitePieces().get(i).getId() == ID) {
                        return board.getWhitePieces().get(i).move(C,R,board.getWhitePieces(),board.getBlackPieces());
                        //return true;
                    }
                }
          }
          System.out.println("An invalid piece was chosen as type");
          return false;

        } else { //not white player
            for (int i = 0; i < board.getBlackPieces().size(); i++){
                if (board.getBlackPieces().get(i).getType() == type) {
                    //  System.out.println("MATCH FOUND");
                    if (board.getBlackPieces().get(i).getId() == ID) {
                        return board.getBlackPieces().get(i).move(C,R,board.getWhitePieces(),board.getBlackPieces());
                    }
                }
            }
        }
        System.out.println("An invalid piece was chosen as type");
        return false;

       }

    public void nextTurn(String player) {

        turn++;
        boolean endTurn = false;
        String location;
       do {
            System.out.println("Enter the piece you want to move or type exit");
           location = input.nextLine().toUpperCase();
/*
           if (location.equals("exit")) {
               exit = true;
           }
           */
           char type = location.charAt(0);
            System.out.println("Enter where you want to move to CR");
            location = input.nextLine();


            char C = location.charAt(0);
            char R = location.charAt(1);
            if (movePiece(C,R,type, player)) {
                endTurn = true;
           } else {
                System.out.println("Something went wrong...");
            }
        } while (!endTurn);
        return;

    }

    public void newGame() {
        boolean done = false;
        int who = 0;
        System.out.println(ANSI_RED + "\uD83D\uDC51 CHESS \uD83D\uDC51 ") ;   // alternative way "CHESS");
       System.out.println(ANSI_RESET);
        board = new Board();

        do {
            players.get(who).printDetails();
            board.printBoard();
            nextTurn(players.get(who).getColour());
            who = Math.abs(who - 1);
            board.updateBoard();



        } while (!done);

    }

}
