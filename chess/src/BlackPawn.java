import java.util.ArrayList;
import java.util.Iterator;

public class BlackPawn extends Piece{
    public BlackPawn (char c) {
        super("Pawn",c, '7', "black",'♙',1);
    }



        public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
            if (!moveOnGrid(c, r)) {
                System.out.println("Move is off the board");
                return false;
            }

            if (!isSpotEmpty(whiteP,blackP,c,r)) {
                if (attack(c, r,whiteP)) {
                    setcPos(c);
                    setrPos(r);
                    System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
                    promote(blackP,c);
                    return true;
                }
                System.out.println("The spot is already taken!");
                return false;

            } else {
                if (!(this.getrPos() != r && this.getcPos() == c)) {
                    System.out.println("Pawns can only travel front or diagonal when attacking");
                    return false; }
                if ((this.getrPos() - r != 1 && !(this.getrPos() - r == 2 && this.getrPos() == '7'))) {

                    System.out.println("Pawns can only travel one space forward unless on the starting position");
                    return false;
                }

                if (!checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
                    return false;
                }



                setcPos(c);
                setrPos(r);
                System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
                promote(blackP,c);
                return true;
            }
        }




        public boolean attack(char c, char r, ArrayList<Piece> whiteP) {
            //if the column is one left or right AND the row is 1 below
            //System.out.println("ATTACK?");
            int index = 0;
            if ((c == (this.getcPos() + 1) || (c == this.getcPos() - 1)) && (r == (this.getrPos() - 1))) {
                //   System.out.println("Diagonal move attempt: ");
                for (Piece whitePiece:whiteP) {
                    if (whitePiece.getcPos() == c && whitePiece.getrPos() == r) {
                        killed(whiteP, index);
                        return true;
                    }
                    index++;
                }
            }
            return false;
        }

        public void promote(ArrayList<Piece> arr, char c) {
            Iterator<Piece> iterator = arr.iterator();

            while (iterator.hasNext()) {
                Piece p = iterator.next();
                if (p.getrPos() == this.getrPos() && p.getcPos() == this.getcPos() && this.getrPos() == '1') {
                    iterator.remove(); // Remove the current element safely
                    arr.add(new Queen(c, '1', "black"));
                    System.out.println("PROMOTED TO QUEEN");
                    // prom = true;
                    break;
                }
            }

        }
    public ArrayList<String> pawnZone(ArrayList<Piece> blackP, ArrayList<Piece> whiteP) {
        ArrayList<String> zone = new ArrayList<>();

        //don't need to check off the board because if it reaches the end of the board it gets promoted
        if (!((char) (this.getcPos() + 1) > 'H')) {
            zone.add("" + (char) (this.getcPos() + 1) + (char) (this.getrPos() + 1) );
        }

        if (!((char) (this.getcPos() - 1) < 'A')) {
            zone.add((char) (this.getcPos()  - 1) + "" + (char) (this.getrPos() + 1));
        }



        return zone;



    }
    public void addInitialZones(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares = pawnZone(blackP, whiteP);
        removeSelfZone();


    }

    @Override
    public void updatePieceZone(ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        pieceZoneSquares.clear();
        pieceZoneSquares = pawnZone(whiteP, blackP);
        removeSelfZone();
    }

}


