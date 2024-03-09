import java.util.ArrayList;

public class Bishop  extends Piece{


        public Bishop(char c, char r, String colour, char ID) {
            super(c, r, colour, 'B', ID);
            setValue(3);
        }

        public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
            if (!moveOnGrid(c, r)) {
                return false;
            }

            //can only move in diagonal lines
            if (!(c != this.getcPos() && r != this.getrPos())) {
                //moving in a not diagonal
                System.out.println("Move failed, the bishop can only move in diagonal lines");
                return false;
            }

            if (!checkIsDiagonal(r, c)) {
                System.out.println("Again, not a diagonal mate");
                return false;
            }

            /*
            if (r > this.getrPos())
            if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
                return false;
            }
            if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
                return false;
            }

             */


            if (!isSpotEmpty(whiteP, blackP, c, r)) {
               /* if (attack(c, r, whiteP) || attack(c,r,blackP)) {
                    setcPos(c);
                    setrPos(r);
                    System.out.println("The " + this.type + " has been moved to " + this.getcPos() + this.getrPos());
                    return true;
                //} */
                System.out.println("The spot is already taken!");
                return false;

            } else {


                //need to ensure it isn't moving through pieces
                    /*
                if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
                    return false;
                }
                if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
                    return false;
                }
                */
                setcPos(c);
                setrPos(r);
                System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
                return true;
            }
        }


        public boolean attack(char c, char r, ArrayList<Piece> arr) {
            if (this.getColour().equals(arr.getFirst().getColour())) {
                return false;
            }
            //opposite colour
            int index = 0;
            for (Piece p : arr) {
                if(p.getcPos() == c && p.getrPos() == r) {
                    System.out.println("The rook has killed the " + p.getType());
                    p.killed(arr,index);

                    return true;
                }
            }
            return false;

        }
    }