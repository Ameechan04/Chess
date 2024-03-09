public class King extends Piece{
    boolean inCheck;
    final String  icon = "\uD83D\uDC51";
    public King (char c, char r, String colour) {
        super(c,r,colour,'*', '1');
        setValue(0);
    }

    public String getIcon() {
        return icon;
    }
}
