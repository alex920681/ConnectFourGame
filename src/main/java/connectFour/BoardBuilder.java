package connectFour;

/**
 * Determines the state of the board at the start of the game
 */
public interface BoardBuilder {
    BoardValue[][] build(int rows, int columns);
}
