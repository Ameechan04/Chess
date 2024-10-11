import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static Scanner input;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    ArrayList<Player> players;
    int turn;
    /*black is represented as boolean true, white is boolean false*/
    Board board;
    public Game() {
        MainMenu();

    }

    public void MainMenu(){
        System.out.print(ANSI_BLUE);
        printChess();
        System.out.println(" █▄ ▄█ ▄▀▄ █ █▄ █   █▄ ▄█ ██▀ █▄ █ █ █\n" +
                " █ ▀ █ █▀█ █ █ ▀█   █ ▀ █ █▄▄ █ ▀█ ▀▄█\n");
        System.out.println(ANSI_BLUE +"OPTIONS:");
        System.out.println("NEW GAME");
        System.out.println("CREDITS");
        System.out.println("EXIT");
        System.out.print("Enter 'new','exit' or 'credits': ");
        System.out.print(ANSI_RESET);
        String in;
        boolean valid = false;

        do {
            in = (input.nextLine()).toUpperCase();

            switch (in)  {
                case "NEW":
                    valid = true;
                    players = new ArrayList<>();
                    System.out.println(ANSI_BLUE +"Enter player 1's name: ");
                    System.out.print(ANSI_RESET);
                    in = (input.nextLine());
                    players.add(new Player(in, "white"));
                    System.out.println(ANSI_BLUE +"Enter player 2's name: ");
                    System.out.print(ANSI_RESET);
                    in = (input.nextLine());
                    players.add(new Player(in, "black"));
                    System.out.print(ANSI_RESET);
                    newGame();
                    break;
                case "EXIT":
                    valid = true;
                    System.out.println("Goodbye.");
                    System.exit(0);
                    break;

                default:
                    System.out.println(ANSI_RED + "Enter new or exit!");
                    valid = false;
            }
        } while (!valid);




    }

    public void printChess(){
        System.out.println(" ___  _ _  ___  ___  ___ \n" +
                "|  _]| | || __]/ __]/ __]\n" +
                "| [__|   || _] \\__ \\\\__ \\\n" +
                "`___/|_|_||___][___/[___/\n");

         System.out.println("         _                                        \n" +
                            "|_ \\/   |_| _  _|  _ _       |V| _  _  _ |_  _  _ \n" +
                            "|_)/    | || |(_| | (/_\\^/   | |(/_(/_(_ | |(_|| |\n");
    }

    public static void main(String[] args) {
        input = new Scanner(System.in);
        Game game = new Game();
    }
    public boolean movePiece(Piece piece) {
        String location;
        boolean valid = false;
        char C = '0';
        char R = '0';
        do {
            System.out.println(ANSI_BLUE + "Enter where you want to move the " + piece.getColour() + " " + piece.getFname() + " e.g. A4");
            location = input.nextLine();
            System.out.print(ANSI_RESET);
            if (location.length() == 2) {

                    C = location.charAt(0);
                R = location.charAt(1);

                if (C < 'A' || C > 'H' || R < '1' || R > '8') {
                    System.out.println(ANSI_RED + "The location entered must be from A to H, and from 1 to 8");
                    valid = false;
                } else {
                    valid = true;
                }

            } else {
                System.out.println(ANSI_RED + "The location was an invalid format: only enter the letter and the number");
                valid = false;
            }


        } while (!valid);
        String in;
        char ID = 0;
        int index = 0;
        //match current piece with a piece in the arrays, we want to remove it, move it on the board and add it back
        if (piece.getColour().equals("white")) {
            for (Piece arrayP : board.getWhitePieces()) {
                if (piece == arrayP) {
                   // System.out.println("MATCH FOUND");
                    break;
                } else {
                    index++;
                }
            }
        } else {
            for (Piece arrayP : board.getBlackPieces()) {
                if (piece == arrayP) {
                   // System.out.println("MATCH FOUND");
                    break;
                } else {
                    index++;
                }
            }
        }



        if (!piece.move(C,R,board.getWhitePieces(),board.getBlackPieces())) {
            System.out.println("Move failed");
            return false;
        } else {
            //board.addToSquaresAttacked(piece.getColour());
            //board.printSquaresAttacked();
            return true;
        }



       }

    public void nextTurn(Player player) {
        Piece piece = null;
        boolean valid = false;
        turn++;
        boolean endTurn = false;
        String location;

        char existingC = '#';
        char existingR = '#';
        King k = null;

       do {
           do {


               if (player.getColour().equals("white")) {
                   for (Piece whiteP : board.getWhitePieces()) {
                       if (whiteP.getFname().equals("King")) {
                           k = (King) whiteP;
                           System.out.println("White king is set as k");

                       }
                   }
               } else {
                   for (Piece blackP : board.getBlackPieces()) {
                       if (blackP.getFname().equals("King")) {
                           k = (King) blackP;
                           System.out.println("Black king is set as k");
                       }
                   }
               }
                board.checkIfChecked(player.getColour());
                if (k == null) {
                    System.err.println("error - the king was null. If you are seeing this then there is a bug");
                }
               if (k.isInCheck()) {
                   System.out.println("The king must be moved out of check!");
                   /*
                   if (k.getLegalMoves().isEmpty()) {
                        System.out.println("The king cannot be moved, CHECKMATE!");
                        break;
                    } else {
                       endTurn = true;
                       break;

                }

                    */
               }



               System.out.println(ANSI_BLUE + "Enter the location of the piece you want to move e.g A1");
               System.out.print(ANSI_RESET);

               location = input.nextLine().toUpperCase();

               if (location.equals("EXIT")) {
                   endTurn = true;
                   break;
               }

               if (location.length() != 2) {
                   System.out.println(ANSI_RED + "The location was an invalid format: only enter the letter and the number");
                   valid = false;
               } else {
                   existingC = location.charAt(0);
                   existingR = location.charAt(1);
                   if (existingC < 'A' || existingC > 'H' || existingR < '1' || existingR > '8') {
                       System.out.println("Enter a column from A to H and a row from 1 to 8");
                       System.out.print(ANSI_RESET);
                       valid = false;
                   } else {
                       if (player.getColour().equals("white")) {
                           for(Piece whiteP:board.getWhitePieces()){
                               if (whiteP.getcPos() == existingC && whiteP.getrPos() == existingR) {
                                   piece = whiteP;
                                   valid = true;
                               }
                           }
                       } else {
                           for(Piece blackP:board.getBlackPieces()){
                               if (blackP.getcPos() == existingC && blackP.getrPos() == existingR) {
                                   piece = blackP;
                                   valid = true;
                               }
                           }


                   }
               }



               }
               if (piece == null) {
                   System.out.println(ANSI_RED + "Could not find a " + player.getColour() + " piece on the selected square!");
                   System.out.print(ANSI_RESET);
                   valid = false;
               }
           } while (!valid);



            if (movePiece(piece)) {
                board.updateZonesOfControl();
                board.printZoneOfControls(board.getWhitePieces());
               // board.printZoneOfControl();
                endTurn = true;
           } else {
                System.out.println("Something went wrong...");
            }
        } while (!endTurn);
        return;

    }

    public void newGame() {
        System.out.println(ANSI_RED + "IF YOU ARE PLAYING IN TERMINAL PLEASE ENTER YES OTHERWISE THE GRAPHICAL DISPLAY WILL NOT WORK");
        System.out.println(ANSI_BLUE + "Enter y/n");
        System.out.print(ANSI_RESET);
        String term = input.nextLine().toLowerCase();
        boolean done = false;
        int who = 0;
        board = new Board();
        {
            do {
                players.get(who).printDetails();
                if (term.equals("y")) {
                    board.printBoardTerminal();
                } else{
                    board.printBoard();
                }
                nextTurn(players.get(who));
                who = Math.abs(who - 1); //THIS LINE ENSURES THE CURRENT PLAYER FLIP FLOPS BETWEEN BLACK AND WHITE
                board.updateBoard();
            } while (!done);
        }

    }


}
