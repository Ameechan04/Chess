import java.util.ArrayList;

public class Knight extends Piece{


    public Knight (char c, char r, String colour,char ID) {
        super(c,r,colour,'k', ID);
        setValue(5);
        //setType('R');
    }

    public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP) {
        if (moveOnGrid(c,r) && (isSpotEmpty(whiteP, blackP, c, r))) {
            setcPos(c);
            setrPos(r);
            System.out.println("The piece has been moved to " + this.getcPos() + this.getrPos());
            return true;
        }

        return false;
    }

}