import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(char c, char r, String colour) {
        super("Rook", c, r, colour, '♖', 5);

    }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        // ArrayList<String> threateningSquares = legalMoves(whiteP,blackP);

        for (String s : pieceZoneSquares) {
            System.out.println("Squares in zone of control: " + s);
        }

        if (!moveOnGrid(c, r)) {
            System.out.println("Move is off the board");
            return false;
        }

        //can only move in straight lines
        if (!(c == this.getcPos() || r == this.getrPos())) {
            //moving in a not straight
            System.out.println("Move failed, the rook can only move in straight lines");
            return false;
        }

        /*
        if(!checkPieceInWayStraight(whiteP,r,c) || !checkPieceInWayStraight(blackP,r,c)) {
            return false;
        }

         */

        if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
            System.out.println("The movement is blocked");
            return false;
        }
        if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
            System.out.println("The movement is blocked");
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


            //need to ensure it isn't moving through pieces

            setcPos(c);
            setrPos(r);
            System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());

            ArrayList<String> movesAfterTurn = legalMoves(whiteP, blackP, false);

            for (String s : movesAfterTurn) {
                System.out.println("Legal move available: " + s);
            }


            if (attackingKing(movesAfterTurn, whiteP, blackP)) {
                King k = getKing(whiteP, blackP);
                k.setInCheck(true);
            }
            return true;
        }
    }


    /*return an arrayList of all the legal moves with the current piece
     * this will act as the zone of control for the piece
     */


    @Override
    public boolean addToLegalMove(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP, boolean zoneMode) {
        if (!moveOnGrid(c, r)) {

            return false;
        }

        //can only move in straight lines
        if (!(c == this.getcPos() || r == this.getrPos())) {
            //moving in a not straight
            return false;
        }

        if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
            return false;
        }
        if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
            return false;
        }
        return spotChecker(whiteP, blackP, c, r, zoneMode);
    }

    public void addInitialZones(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares = (legalMoves(whiteP, blackP, true));
        removeSelfZone();

    }




}






