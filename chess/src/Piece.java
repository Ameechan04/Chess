import java.util.ArrayList;
import java.util.HashMap;

public abstract class Piece {
    public char type;
    public String fname; //fullname
    public char id;
    private boolean alive;
    private String colour;
    private char rPos;
    private char cPos;
    private int value;

    public Piece(String fname, char c, char r, String colour, char type, int value) {
       this.fname = fname;
        this.type = type;
        setAlive(true);
        this.cPos = c;
        this.rPos = r;
        setColour(colour);
        this.value = value;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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
        Piece p = arr.get(index);
        System.out.println(arr.get(index).getColour() + " " + arr.get(index).getFname() + " has been killed");
        this.setAlive(false);
        arr.remove(index);
        for (Piece piece : arr) {
            if (piece == p) {
                return false;
            }
        }
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

    public boolean checkPieceInWayDiagonal(ArrayList<Piece> arr, char targetR, char targetC) {
        //1. find all the spaces in between the targetR and targetC, and the current r and current c
        char tempC = targetC;
        char tempR = targetR;
        int distance = Math.abs(targetR - this.getrPos());
        char[] betweenRs = new char[distance];
        char[] betweenCs = new char[distance];
        int index = 0;
        for (int i = 0; i < distance; i++) {
            if (targetR > this.getrPos() && targetC > this.getcPos()) {
                if ((tempC < targetC && tempR < targetR) && (tempC >= 'A' && tempC <= 'H' && tempR >= '1' && tempR <= '8')) {
                    betweenRs[index] = tempR;
                    betweenCs[index] = tempC;
                    index++;
                }
                tempR--;
                tempC--;
            } else if (targetR > this.getrPos() && targetC < this.getcPos()) {
                if ((tempC > targetC && tempR < targetR) && (tempC >= 'A' && tempC <= 'H' && tempR >= '1' && tempR <= '8')) {
                    betweenRs[index] = tempR;
                    betweenRs[index] = tempR;
                    betweenCs[index] = tempC;
                    index++;
                }
                tempR--;
                tempC++;
            } else if (targetR < this.getrPos() && targetC > this.getcPos()) {
                if ((tempC < targetC && tempR > targetR) && (tempC >= 'A' && tempC <= 'H' && tempR >= '1' && tempR <= '8')) {
                    betweenRs[index] = tempR;
                    betweenCs[index] = tempC;
                    index++;
                }
                tempR++;
                tempC--;
            } else if (targetR < this.getrPos() && targetC < this.getcPos()) {
                if ((tempC > targetC && tempR > targetR) && (tempC >= 'A' && tempC <= 'H' && tempR >= '1' && tempR <= '8')) {
                    betweenRs[index] = tempR;
                    betweenCs[index] = tempC;
                    index++;
                }
                tempR++;
                tempC++;
            }
        }
        //2. check if any existing piece is on a flagged square

            for (Piece p : arr) {
                for (int i = 0; i < betweenCs.length; i++) {
                    if (p.getrPos() == betweenRs[i] && p.getcPos() == betweenCs[i]) {
                    //    System.out.println("The movement is blocked as a piece is in the way on the intended diagonal");
                        return false;
                    }
                }

            }
        return true;
    }

    public boolean checkIsDiagonal(char r, char c) {

        return Math.abs(c - this.getcPos()) == Math.abs(r - this.getrPos());
    }
    public boolean attack(char c, char r, ArrayList<Piece> arr) {
        /*
        //if the piece to attack equals the same as the arrays first element, it is the same colour and cannot attack
        if (this.getColour().equals(arr.getFirst().getColour())) {
            return false;
        }
        //opposite colour
        int index = 0;
        for (Piece p : arr) {
            if(p.getcPos() == c && p.getrPos() == r) {
                System.out.println("The " + this.getType() + " has killed the " + p.getType());
                p.killed(arr,index);

                return true;
            }
        }

         */

        //if the column is one left or right AND the row is in front
        //System.out.println("ATTACK?");
        int index = 0;
        if (this.getColour().equals(arr.getFirst().getColour())) {
            return false;
        }

            for (Piece piece:arr) {
                if (piece.getcPos() == c && piece.getrPos() == r) {
                    System.out.println("The " + this.getType() + " has killed the " + piece.getType());
                    killed(arr, index);
                    return true;
                }
                index++;
            }

        return false;

    }








}
