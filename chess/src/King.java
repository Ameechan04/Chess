import java.util.ArrayList;

public class King extends Piece {
    boolean inCheck;
    final String icon = "\uD83D\uDC51";

    public King(char c, char r, String colour) {
        super("King", c, r, colour, '♔', 0);
        setValue(0);
        inCheck = false;
    }

    public boolean isInCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck) {
        System.out.println("King is in check bro");
        this.inCheck = inCheck;
    }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        if (!moveOnGrid(c, r)) {
            System.out.println("Move is off the board");
            return false;
        }

        if (!((Math.abs(r - this.getrPos()) == 1 && c == this.getcPos()) ||
                (Math.abs(c - this.getcPos()) == 1 && r == this.getrPos()) ||
                (Math.abs(r - this.getrPos()) == 1 && Math.abs(c - this.getcPos()) == 1))) {
            System.out.println("Move failed, the King can only move one space in diagonal or straight lines");
            return false;
        }

        if (!isSpotEmpty(whiteP, blackP, c, r)) {
            if (attack(c, r, whiteP) || attack(c, r, blackP)) {
                setcPos(c);
                setrPos(r);
                System.out.println("The " + this.getFname() + " has been moved to " + this.getcPos() + this.getrPos());
                return true;
            }
            System.out.println("The spot is already taken!");
            return false;

        } else {
            setcPos(c);
            setrPos(r);
            System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
            return true;
        }
    }

    public boolean addToLegalMove(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP, boolean zoneMode) {
        if (!moveOnGrid(c, r)) {

            return false;
        }

        if (this.getcPos() > c + 1 || this.getcPos() < c - 1 || this.getrPos() > r + 1 || this.getrPos() < r - 1) {
            //then this is not a valid move as it is trying to move more than one place
            return false;
        }



        //can only move in straight lines and diagonals by 1, and needs to check all opponents legal moves to ensure it doesn't move into check



        return spotChecker(whiteP, blackP, c, r, zoneMode);
    }

    public void addInitialZones(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares = legalMoves(whiteP, blackP, true);
        removeSelfZone();


    }
}