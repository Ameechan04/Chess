import java.util.ArrayList;

public class Bishop  extends Piece{


        public Bishop(char c, char r, String colour) {
            super("Bishop",c,r,colour,'♗',3);
            setValue(3);
        }

        public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
            if (!moveOnGrid(c, r)) {
                System.out.println("Move is off the board");
                return false;
            }

            //can only move in diagonal lines
            if (!(c != this.getcPos() && r != this.getrPos()) || !checkIsDiagonal(r, c)) {
                //moving in a not diagonal
                System.out.println("Move failed, the bishop can only move in diagonal lines");
                return false;
            }





            if (!checkPieceInWayDiagonal(whiteP,r,c) || !checkPieceInWayDiagonal(blackP,r,c)) {
                System.out.println("there is a piece in the way!");
                return false;
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
                System.out.println("The bishop has been moved to " + this.getcPos() + this.getrPos());
                return true;
            }
        }

    @Override
    public boolean addToLegalMove(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP, boolean zoneMode) {
        if (!moveOnGrid(c, r)) {

            return false;
        }

        //can only move in diagonal lines
        if (!(c != this.getcPos() && r != this.getrPos()) || !checkIsDiagonal(r, c)) {
            //moving in a not diagonal
            return false;
        }
        if (!checkPieceInWayDiagonal(whiteP,r,c) || !checkPieceInWayDiagonal(blackP,r,c)) {
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