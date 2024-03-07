public abstract class Piece {
    public char type;
    private boolean alive;
    private boolean colour;
    private char xPos;
    private char yPos;
    private int value;

    public Piece(char[][] board, char x, char y, boolean black) {
        setAlive(true);
        setxPos(x);
        setyPos(y);
        setColour(black);
        addPieceToBoard(board);

    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public boolean move(char x, char y){
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

    public boolean isColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }

    public char getxPos() {
        return xPos;
    }

    public void setxPos(char xPos) {
        this.xPos = xPos;
    }

    public char getyPos() {
        return yPos;
    }

    public void setyPos(char yPos) {
        this.yPos = yPos;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void printInfo() {

        System.out.println(type + " " + value + " alive: " + alive + " black: " + colour + " Position: " + xPos + yPos) ;
    }

    public boolean moveOnGrid(char x, char y) {
        if (x >= 'A' && x <= 'H' && y >= '1' && y <= '8') {
            return true;
        }
        return false;
    }

    /*add a piece to the board array, and arrayLists*/
    public void addPieceToBoard(char[][] board) {
        int x,y;
        x = xPos - 65;
        //convert to ASCII, then do 8 - converted
        y = 8 - (yPos - '0');
        board[x][y] = this.getType();
    }



}
