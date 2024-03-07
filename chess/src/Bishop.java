import java.util.ArrayList;

public class Bishop  extends Piece{

        public Bishop (char[][]board, char c, char r, boolean black) {
            super(board,c,r,black,'B');
            setValue(3);
            setType('B');
        }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        if (moveOnGrid(c,r) && (checkEmptySpot(whiteP, blackP, c, r))) {
            setcPos(c);
            setrPos(r);
            System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
            return true;
        }

        return false;
    }


}
