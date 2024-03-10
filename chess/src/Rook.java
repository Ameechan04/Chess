import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(char c, char r, String colour) {
       super("Rook",c,r,colour,'R',5);
           }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        if (!moveOnGrid(c, r)) {
            return false;
        }

        //can only move in straight lines
        if (!(c == this.getcPos() || r == this.getrPos())) {
            //moving in a not straight
            System.out.println("Move failed, the rook can only move in straight lines");
            return false;
        }


        if (!checkRowAbove(whiteP, r, c) || !checkRowAbove(blackP, r, c) || !checkRowBelow(whiteP, r, c) || !checkRowBelow(blackP, r, c)) {
            return false;
        }
        if (!checkColumnRight(whiteP, r, c) || !checkColumnRight(blackP, r, c) || !checkColumnLeft(whiteP, r, c) || !checkColumnLeft(blackP, r, c)) {
            return false;
        }




        if (!isSpotEmpty(whiteP, blackP, c, r)) {
            if (attack(c, r, whiteP) || attack(c,r,blackP)) {
                setcPos(c);
                setrPos(r);
                System.out.println("The " + this.type + " has been moved to " + this.getcPos() + this.getrPos());
                return true;
            }
            System.out.println("The spot is already taken!");
            return false;

        } else {


            //need to ensure it isn't moving through pieces

            setcPos(c);
            setrPos(r);
            System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
            return true;
        }
    }
}






