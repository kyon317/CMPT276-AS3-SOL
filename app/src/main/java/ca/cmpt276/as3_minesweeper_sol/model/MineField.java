package ca.cmpt276.as3_minesweeper_sol.model;

/**
 * Game state for the mine sweeper game
 */
public class MineField {
    private int numMinesTotal = 0;
    private int numScans = 0;
    private int numCols;
    private int numRows;
    private Cell[][] cells;


    public MineField(int numRows, int numCols, int numMines) {
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Number rows and columns out of bounds.");
        }

        this.numRows = numRows;
        this.numCols = numCols;
        this.numMinesTotal = numMines;
        this.cells = new Cell[numRows][numCols];

        fillCellsWithNoMines();
        populateMines(numMines);

    }

    private void fillCellsWithNoMines() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(false);
            }
        }
    }

    private void populateMines(int numMines) {
        if (numMines > numRows * numCols) {
            throw new IllegalArgumentException("Game board too small for number of mines.");
        }
        int numMinesPlaced = 0;
        while (numMinesPlaced < numMinesTotal) {
            int row = (int) (Math.random() * numRows);
            int col = (int) (Math.random() * numCols);

            // Place a mine if it does not already have one.
            if (!cells[row][col].isHasMine()) {
                cells[row][col] = new Cell(true);
                numMinesPlaced++;
            }
        }
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public boolean hasVisibleMine(int row, int col) {
        validateRowCol(row, col);
        return cells[row][col].isHasVisibleMine();
    }

    public boolean hasBeenScanned(int row, int col) {
        validateRowCol(row, col);
        return cells[row][col].isHasBeenScanned();
    }

    public int getNumMinesScanned(int scanRow, int scanCol) {
        validateRowCol(scanRow, scanCol);
        int count = 0;
        for (int col = 0; col < numCols; col++) {
            if (cells[scanRow][col].isHasHiddenMine()) {
                count++;
            }
        }
        for (int row = 0; row < numRows; row++) {
            if (cells[row][scanCol].isHasHiddenMine()) {
                count++;
            }
        }
        return count;
    }

    public void investigateCell(int row, int col) {
        validateRowCol(row, col);
        Cell cell = cells[row][col];
        if (cell.isHasHiddenMine()) {
            cell.investigateCell();
        }
        else if (!cell.isHasBeenScanned()) {
            numScans++;
            cell.investigateCell();
        }
    }

    private void validateRowCol(int row, int col) {
        if (row < 0 || row >= cells.length) {
            throw new IllegalArgumentException("Row out of bounds for game board.");
        }
        if (col < 0 || col >= cells[0].length) {
            throw new IllegalArgumentException("Col out of bounds for game board.");
        }
    }

    public int getNumMinesFound() {
        int numMinesFound = 0;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell.isHasVisibleMine()) {
                    numMinesFound++;
                }
            }
        }
        return numMinesFound;
    }

    public int getNumMinesTotal() {
        return numMinesTotal;
    }

    public int getNumScans() {
        return numScans;
    }

    public boolean hasUserWon() {
        return numMinesTotal == getNumMinesFound();
    }
}
