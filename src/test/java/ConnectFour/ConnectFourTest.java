package ConnectFour;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectFourTest {


    class BoardBuilderTestImpl implements BoardBuilder {
        private BoardValue[][] board;

        public BoardBuilderTestImpl(BoardValue[][] board) {
            this.board = board;
        }

        @Override
        public BoardValue[][] build(int rows, int columns) {
            return board;
        }
    }
    @Test
        // checkHorizonteWinner()
    void checkWinnerHorizonte1() {BoardValue[][] board = new BoardValue[6][7];
        board[5][0] = BoardValue.Red;
        board[5][1] = BoardValue.Red;
        board[5][2] = BoardValue.Red;
        board[5][3] = BoardValue.Red;
        board[5][4] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertTrue(connectFour.checkWinner(5, 2));
    }

    @Test
        // checkHorizonteWinner()
    void checkWinnerHorizonte2() {BoardValue[][] board = new BoardValue[6][7];
        board[5][0] = BoardValue.Red;
        board[5][1] = BoardValue.Green;
        board[5][2] = BoardValue.Red;
        board[5][3] = BoardValue.Red;
        board[5][4] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.checkWinner(5, 2));
    }

    @Test
        // checkVerticalWinner
    void checkWinnerVertical1() {BoardValue[][] board = new BoardValue[6][7];
        board[2][1] = BoardValue.Red;
        board[3][1] = BoardValue.Red;
        board[4][1] = BoardValue.Red;
        board[5][1] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertTrue(connectFour.checkWinner(3, 1));
    }
    @Test
        // checkVerticalWinner
    void checkWinnerVertical2() {BoardValue[][] board = new BoardValue[6][7];
        board[2][1] = BoardValue.Red;
        board[3][1] = BoardValue.Red;
        board[4][1] = BoardValue.Red;
        board[5][1] = BoardValue.Green;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.checkWinner(3, 1));
    }

    @Test
        // checkForDiagonalDescWinner
    void checkWinnerDiagonalDesc1() {BoardValue[][] board = new BoardValue[6][7];
        board[2][1] = BoardValue.Red;
        board[3][2] = BoardValue.Red;
        board[4][3] = BoardValue.Red;
        board[5][4] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertTrue(connectFour.checkWinner(3, 2));
    }

    @Test
        // checkForDiagonalDescWinner
    void checkWinnerDiagonalDesc2() {BoardValue[][] board = new BoardValue[6][7];
        board[2][1] = BoardValue.Green;
        board[3][2] = BoardValue.Red;
        board[4][3] = BoardValue.Red;
        board[5][4] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.checkWinner(3, 2));
    }

    @Test
        //checkForDiagonalAscWinner
    void checkWinnerDiagonalAsc1() {BoardValue[][] board = new BoardValue[6][7];
        board[5][2] = BoardValue.Red;
        board[4][3] = BoardValue.Red;
        board[3][4] = BoardValue.Red;
        board[2][5] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertTrue(connectFour.checkWinner(2, 5));
    }

    @Test
        //checkForDiagonalAscWinner
    void checkWinnerDiagonalAsc2() {BoardValue[][] board = new BoardValue[6][7];
        board[5][2] = BoardValue.Red;
        board[4][3] = BoardValue.Red;
        board[3][4] = BoardValue.Green;
        board[2][5] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.checkWinner(2, 5));
    }

    @Test
    void isCoordinatesValid() {BoardValue[][] board = new BoardValue[6][7];
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertTrue(connectFour.isCoordinatesValid(2, 5));
    }
    @Test
    void isCoordinatesValid2() {BoardValue[][] board = new BoardValue[6][7];
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.isCoordinatesValid(-1, 5));
    }
    @Test
    void isCoordinatesValid3() {BoardValue[][] board = new BoardValue[6][7];
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertFalse(connectFour.isCoordinatesValid(2, 9));
    }

    @Test
    void dropDisc1() {BoardValue[][] board = new BoardValue[6][7];
        board[4][1] = BoardValue.Red;
        board[5][1] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertEquals(3 , connectFour.dropDisc(1, BoardValue.Red));
    }
    @Test
    void dropDisc2() {BoardValue[][] board = new BoardValue[6][7];
        board[4][1] = BoardValue.Red;
        board[5][1] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertEquals(5 , connectFour.dropDisc(0, BoardValue.Red));
    }

    @Test
    void dropDisc3() {BoardValue[][] board = new BoardValue[6][7];
        board[4][1] = BoardValue.Red;
        board[5][1] = BoardValue.Red;
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        assertEquals(-1 , connectFour.dropDisc(8, BoardValue.Red));
    }

    @Test
    void dropDisc4() {BoardValue[][] board = new BoardValue[2][2];
        ConnectFour connectFour = new ConnectFour(new BoardBuilderTestImpl(board));
        board[1][1] = BoardValue.Red;
        board[1][0] = BoardValue.Red;
        board[0][1] = BoardValue.Red;
        board[0][0] = BoardValue.Red;
        assertEquals(-1 , connectFour.dropDisc(1, BoardValue.Red));
    }
}
