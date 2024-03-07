public class Rook extends Piece{
    public Rook (char[][]board, char x, char y, boolean black) {
        super(board,x,y,black);
        setValue(5);
        setType('R');
    }

    public boolean move(char x, char y) {
        if (moveOnGrid(x,y)) {
            setxPos(x);
            setyPos(y);
            return true;
        }

        return false;
    }

    private boolean emptySpot(char x, char y) {

        return true;
    }
}
