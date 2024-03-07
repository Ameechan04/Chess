import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    Board board = new Board();
    Piece tester = new Rook(board.getBoard(), 'A', '1', false);

    @Test
    void testMoveOnBoard() {
        assertFalse(tester.moveOnGrid('0', '1'));
        assertFalse(tester.moveOnGrid('A', '9'));
        assertTrue(tester.moveOnGrid('A', '1'));
        assertTrue(tester.moveOnGrid('H', '8'));
    }
}