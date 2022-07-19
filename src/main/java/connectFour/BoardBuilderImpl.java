package connectFour;

/**
 * Creates an empty board at the start of the game
 */
public class BoardBuilderImpl implements BoardBuilder {
    @Override
    public BoardValue[][] build(int rows , int columns) {
        return new BoardValue[rows][columns];
    }
}
