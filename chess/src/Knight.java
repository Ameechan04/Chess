import java.util.ArrayList;

public class Knight extends Piece {


    public Knight(char c, char r, String colour) {
        super("Knight", c, r, colour, '♘', 3);
    }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        ArrayList<String> legalMoves = new ArrayList<>();
        legalMoves.add("test");
        if (!moveOnGrid(c, r)) {
            System.out.println("Move is off the board");
            return false;
        }

        if (!validLmove(c, r)) {
            System.out.println("The knight can only move in an L shape, 2 up and 1 across or 1 up and 2 across");
            return false;

        }

        if (!isSpotEmpty(whiteP, blackP, c, r)) {
            if (attack(c, r, whiteP) || attack(c, r, blackP)) {
                System.out.println("ATTACK SUCCESS");
                setcPos(c);
                setrPos(r);
                System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
                return true;

            }
            System.out.println("The spot is not empty");
            return false;
        }
        setcPos(c);
        setrPos(r);
        System.out.println("The Knight has been moved to " + this.getcPos() + this.getrPos());

        ArrayList<String> movesAfterTurn = legalMoves(whiteP,blackP, false);

        for (String s : movesAfterTurn) {
            System.out.println("Legal move available: " + s);
        }

        return true;
    }

    private boolean validLmove(char c, char r) {
        int rowDiff = Math.abs(r - this.getrPos());
        int colDiff = Math.abs(c - this.getcPos());

        // Check if the move is L-shaped: two squares in one direction and one square in another
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);


    }


    @Override
    public boolean addToLegalMove(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP, boolean zoneMode) {
        if (!moveOnGrid(c, r)) {

            return false;
        }

        //can only move in L shapes
        if (!validLmove(c, r)) {
            return false;
        }


       return spotChecker(whiteP, blackP, c, r, zoneMode);
    }

    @Override
    public void addInitialZones(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares = legalMoves(whiteP, blackP, true);
        removeSelfZone();

    }
}



