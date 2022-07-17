package ConnectFour;

public class BoardBuilderImpl implements BoardBuilder {
    @Override
    public BoardValue[][] build(int rows , int columns) {
        return new BoardValue[rows][columns];
    }
}
