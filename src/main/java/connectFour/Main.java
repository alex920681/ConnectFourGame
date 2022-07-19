package connectFour;

/**
 * The main class of the game ConnectFour.
 * Rules of the game :
 * 1. The vertical board is composed of seven columns and six rows. Initially, all positions are empty.
 * 2. Players introduce discs at the top of the columns. The disc falls to the bottom of the column if the column is empty.
 * Future discs introduced to the same column will stack over previous ones.
 * 3. It is a two-person game. Player 1 uses red ('R') and Player 2 uses green ('G').
 * Players take alternate turns, inserting one disc each time.
 * 4. When no more discs can be inserted, the game finishes, and it is considered a draw.
 * 5. If a player inserts a disc and connects more than three discs of his color in a straight vertical,
 * horizontal or diagonal line, then that player wins.
 */
public class Main {
    public static void main(String[] args) {
        new ConnectFour(new BoardBuilderImpl()).play();
    }
}
