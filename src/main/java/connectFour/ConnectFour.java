package connectFour;


import java.util.Scanner;
import java.util.function.Function;

/**
 * Class with methods responsible for the main logic of the game :
 * play()
 * checkFullBoard(int counter)
 * checkWinner(int row, int column)
 * checkWinner(int row, int column)
 * countSameDisc(int row, int column, Function<Coordinates, Coordinates> getNextCoordinates)
 * isCoordinatesValid(int row, int column)
 * checkHorizontalWinner(int row, int column)
 * checkVerticalWinner(int row, int column)
 * checkDiagonalDescWinner(int row, int column)
 * checkDiagonalAscWinner(int row, int column)
 * printBoard()
 * askPlayerForNewDisc(BoardValue disc)
 * dropDisc(int column, BoardValue disc)
 */
public class ConnectFour {

    private final BoardValue[][] board;
    private final int conditionOfWin = 4;
    private final int numberOfColumns;
    private final int numberOfRows;

    public ConnectFour(BoardBuilder boardBuilder) {
        this.board = boardBuilder.build(6, 7);
        this.numberOfColumns = board[0].length;
        this.numberOfRows = board.length;
    }

    /**
     * Entry point of the game
     */
    public void play() {
        BoardValue activePlayer = BoardValue.RED;
        int counterOfFilling = 0;
        boolean continueGame = true;

        System.out.println("********************************");
        System.out.println("Welcome to the game CollectFour!");
        System.out.println("Red Player moves first");
        printBoard();

        while (continueGame) {
            Coordinates newCoordinates = askPlayerForNewDisc(activePlayer);
            printBoard();
            counterOfFilling++;

            boolean isWinner = checkWinner(newCoordinates.getRow(), newCoordinates.getColumn());
            if (isWinner) {
                if (activePlayer == BoardValue.RED) {
                    System.out.println("Player 1 [RED] wins!");
                } else System.out.println("Player 2 [GREEN] wins!");
                continueGame = false;
            } else if (checkFullBoard(counterOfFilling)) {
                System.out.println("The board is full, Draw!");
                continueGame = false;
            }
            activePlayer = (activePlayer == BoardValue.RED) ? BoardValue.GREEN : BoardValue.RED;
        }
    }

    /**
     * Checks if is there free space on the board
     */
    private boolean checkFullBoard(int counter) {
        return counter >= numberOfRows * numberOfColumns;
    }

    /**
     * Check if there is a winner
     */
    public boolean checkWinner(int row, int column) {
        return checkHorizontalWinner(row, column) || checkVerticalWinner(row, column) ||
                checkDiagonalAscWinner(row, column) || checkDiagonalDescWinner(row, column);
    }


    /**
     * Counting the number of identical discs in a given direction
     *
     * @param getNextCoordinates Lambda expression defining the direction and step of movement on the board
     */
    private int countSameDisc(int row, int column, Function<Coordinates, Coordinates> getNextCoordinates) {
        BoardValue disc = board[row][column];
        int counter = 0;
        Coordinates currentCoordinates = new Coordinates(row, column);
        currentCoordinates = getNextCoordinates.apply(currentCoordinates);

        while (isCoordinatesValid(currentCoordinates.getRow(), currentCoordinates.getColumn())) {
            if (board[currentCoordinates.getRow()][currentCoordinates.getColumn()] != disc) {
                break;
            }
            counter++;
            currentCoordinates = getNextCoordinates.apply(currentCoordinates);
        }
        return counter;
    }

    /**
     * Checking that the coordinate does not go beyond the board
     */
    public boolean isCoordinatesValid(int row, int column) {
        if ((row < 0 || row >= numberOfRows) || (column < 0 || column >= numberOfColumns)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checking the horizontal relative to the placed disc
     */
    private boolean checkHorizontalWinner(int row, int column) {
        int counter = 1;
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow(), x.getColumn() + 1));
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow(), x.getColumn() - 1));
        return counter >= conditionOfWin;
    }

    /**
     * Checking the vertical relative to the placed disc
     */
    private boolean checkVerticalWinner(int row, int column) {
        int counter = 1;
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() + 1, x.getColumn()));
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() - 1, x.getColumn()));
        return counter >= conditionOfWin;
    }

    /**
     * Checking the descending diagonal relative to the placed disc
     */
    private boolean checkDiagonalDescWinner(int row, int column) {
        int counter = 1;
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() + 1, x.getColumn() + 1));
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() - 1, x.getColumn() - 1));
        return counter >= conditionOfWin;
    }

    /**
     * Checking the ascending diagonal relative to the placed disc
     */
    private boolean checkDiagonalAscWinner(int row, int column) {
        int counter = 1;
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() - 1, x.getColumn() + 1));
        counter += countSameDisc(row, column, x -> new Coordinates(x.getRow() + 1, x.getColumn() - 1));
        return counter >= conditionOfWin;
    }

    /**
     * Game board printing
     */
    private void printBoard() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (board[i][j] == BoardValue.RED) {
                    System.out.print("|R");
                }
                if (board[i][j] == BoardValue.GREEN) {
                    System.out.print("|G");
                }
                if (board[i][j] == null) {
                    System.out.print("| ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Request in which column the player wants to put the disc
     * and re-request if it was not possible to put the disk
     * @param disc Color of the current Player
     */
    private Coordinates askPlayerForNewDisc(BoardValue disc) {
        int row;
        int numOfColumn;
        String message;
        if (disc == BoardValue.RED)
            message = "Player 1 [RED] choose column (1-" + numberOfColumns + "): ";
        else
            message = "Player 2 [GREEN] choose column (1-" + numberOfColumns + "): ";

        do {
            System.out.print(message);
            Scanner scanner = new Scanner(System.in);
            numOfColumn = scanner.nextInt() - 1;
            row = dropDisc(numOfColumn, disc);
            if (row == -1) {
                System.out.println("You can't put in this column. Please select again");
            }
        } while (row == -1);

        return new Coordinates(row, numOfColumn);
    }

    /**
     * Throws the disk and returns the line number. If it didn't work, it returns -1
     */
    public int dropDisc(int column, BoardValue disc) {
        if (column + 1 > numberOfColumns || column < 0 || board[0][column] != null) {
            return -1;
        }

        for (int i = numberOfRows - 1; i >= 0; i--) {
            if (board[i][column] == null) {
                board[i][column] = disc;
                return i;
            }
        }
        return -1;
    }
}
