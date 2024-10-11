import java.util.ArrayList;

public class Queen extends Piece{
    public Queen (char c, char r, String colour) {
        super("Queen", c,r,colour,'♕',9);
    }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        if (!moveOnGrid(c, r)) {
            System.out.println("Move is off the board");
            return false;
        }

        //can move in diagonal lines & straight lines: combination of both bishop and rook movement and validation
        boolean diagonalMove = (c != this.getcPos() && r != this.getrPos()) && checkIsDiagonal(r, c);
        boolean straightMove = c == this.getcPos() || r == this.getrPos();

        if (!diagonalMove && !straightMove) {
            //moving in neither a straight or diagonal
            System.out.println("Move failed, the queen can only move in diagonal or straight lines");
            return false;
        }





        if (diagonalMove) {
            if (!checkPieceInWayDiagonal(whiteP, r, c) || !checkPieceInWayDiagonal(blackP, r, c)) {
                System.out.println("there is a piece in the way!");
                return false;
            }
        } else {
            if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
                return false;
            }
            if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
                return false;
            }

        }



        if (!isSpotEmpty(whiteP, blackP, c, r)) {
            if (attack(c, r, whiteP) || attack(c,r,blackP)) {
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

    @Override
    public boolean addToLegalMove(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP, boolean zoneMode) {
        if (!moveOnGrid(c, r)) {

            return false;
        }

        //can move in diagonal lines & straight lines: combination of both bishop and rook movement and validation
        boolean diagonalMove = (c != this.getcPos() && r != this.getrPos()) && checkIsDiagonal(r, c);
        boolean straightMove = c == this.getcPos() || r == this.getrPos();

        if (!diagonalMove && !straightMove) {
            //moving in neither a straight or diagonal
            return false;
        }

        if (diagonalMove) {
            if (!checkPieceInWayDiagonal(whiteP, r, c) || !checkPieceInWayDiagonal(blackP, r, c)) {
                return false;
            }
        } else {
            if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
                return false;
            }
            if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
                return false;
            }

        }




        return spotChecker(whiteP, blackP, c, r, zoneMode);

    }
    @Override
    public void addInitialZones(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares = legalMoves(whiteP, blackP, true);
        removeSelfZone();
    }



}
