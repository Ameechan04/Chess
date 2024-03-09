public class Player {


    String name;
    String colour;

    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void printDetails(){
        System.out.println(getName() + ": " + getColour() + ", YOUR TURN");

    }



}
