import java.util.ArrayList;

public abstract class Piece {
    public char type;
    public char id;
    private boolean alive;
    private String colour;
    private char rPos;
    private char cPos;
    private int value;

    public Piece(char c, char r, String colour, char type, char id) {
        this.id = id;
        this.type = type;
        setAlive(true);
        this.cPos = c;
        this.rPos = r;
        setColour(colour);

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



    public boolean killed(ArrayList<Piece> arr, int index) {
        System.out.println(this.getColour() + " " + this.getType() + " has been killed");
        this.setAlive(false);
        arr.remove(index);
        return true;
    }



    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
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

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
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



    public boolean isSpotEmpty(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces, char moveToC, char moveToR) {
        for (Piece whitePiece:whitePieces) {
            if (whitePiece.getrPos() == moveToR && whitePiece.getcPos() == moveToC) {
                return false;
            }
        }
        for (Piece blackPiece:blackPieces) {
            if (blackPiece.getrPos() == moveToR && blackPiece.getcPos() == moveToC) {
                return false;
            }
        }
        return true;


    }


    public boolean checkRowAbove(ArrayList<Piece> arr, char r, char c) {
        for (Piece p: arr) {
             if (p.getcPos() == c) {
                if (this.getrPos() < p.getrPos() && r > p.getrPos()) {
                    System.out.println("The movement is blocked as a piece is above it");
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkRowBelow(ArrayList<Piece> arr, char r, char c) {
        for (Piece p: arr) {
            if (p.getcPos() == c) {
                if (this.getrPos() > p.getrPos() && r < p.getrPos()) {
                    System.out.println("The movement is blocked as a piece is below it");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkColumnRight(ArrayList<Piece> arr, char r, char c) {
        for (Piece p: arr) {
            //if same row
            if (p.getrPos() == r) {
                //if our current column is left of the piece and our destination is to its right
                if (this.getcPos() < p.getcPos() && c > p.getcPos()) {
                    System.out.println("The movement is blocked as a piece is in the way on the right");
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkColumnLeft(ArrayList<Piece> arr, char r, char c) {
        for (Piece p: arr) {
            //if same row
            if (p.getrPos() == r) {
                //if our current column is left of the piece and our destination is to its right
                if (this.getcPos() > p.getcPos() && c < p.getcPos()) {
                    System.out.println("The movement is blocked as a piece is in the way on the left");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDiagonalUpperRight(ArrayList<Piece> arr, char r, char c) {
        for (Piece p: arr) {
            //if same row
            if (p.getrPos() == r) {
                //if our current column is left of the piece and our destination is to its right
                if (this.getcPos() > p.getcPos() && c < p.getcPos()) {
                    System.out.println("The movement is blocked as a piece is in the way on the left");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkIsDiagonal(char r, char c) {

        if (Math.abs(c - this.getcPos()) == Math.abs(r - this.getrPos())) {
            System.out.println("Diagonal is valid");
            return true;
        }
        System.out.println("invalid diagonal");
        return false;
    }








}
