import java.util.ArrayList;

public abstract class Piece {
    public char type;
    private boolean alive;
    private boolean colour;
    private char rPos;
    private char cPos;
    private int value;

    public Piece(char[][] board, char c, char r, boolean black, char type) {
        this.type = type;
        setAlive(true);
        this.cPos = c;
        this.rPos = r;
        setColour(black);
       // addPieceToBoard(board);

    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }


     public boolean move(char c, char r, ArrayList<Piece> whiteP, ArrayList<Piece> blackP){
        return false;
    }



    public boolean killed() {
        return false;
    }



    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }

    public char getrPos() {
        return rPos;
    }

    public void setrPos(char xPos) {
        this.rPos = xPos;
    }

    public char getcPos() {
        return cPos;
    }

    public void setcPos(char yPos) {
        this.cPos = yPos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void printInfo() {

        System.out.println(type + " " + value + " alive: " + alive + " black: " + colour + " Position: " + cPos + rPos) ;
    }

    public boolean moveOnGrid(char c, char r) {
        if (c >= 'A' && c <= 'H' && r >= '1' && r <= '8') {
            return true;
        }
        System.out.println("Move is off the board");
        return false;
    }


    public boolean checkEmptySpot(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces, char moveToC, char moveToR) {
        for (int i = 0; i < whitePieces.size(); i++) {
            if (whitePieces.get(i).getcPos() == moveToC && whitePieces.get(i).getrPos() == moveToR ) {
                if (this.getColour()) { //if colour = true aka it is black
                    removeFromList(whitePieces, i);
                    return true;
                } else { //if it is white and trying to move where a white piece already is
                    System.out.println("The space is already occupied by one of your pieces!");
                    return false;
                }
            }
        }
        for (int i = 0; i < blackPieces.size(); i++) {
            if (blackPieces.get(i).getcPos() == moveToC && blackPieces.get(i).getrPos() == moveToR ) {
                if (!(this.getColour())) { //if colour = false aka it is white
                    removeFromList(blackPieces, i);
                    return true;
                } else { //if it is black and trying to move where a black piece already is
                    System.out.println("The space is already occupied by one of your pieces!");
                    return false;
                }
            }
        }
        return true; //if it has reached here the spot is empty

    }

    public ArrayList<Piece> removeFromList(ArrayList<Piece> pieces, int index) {
        System.out.println("The " + this.type + " has killed the " + pieces.get(index).getType());
        pieces.remove(index);
        return pieces;

    }




}
