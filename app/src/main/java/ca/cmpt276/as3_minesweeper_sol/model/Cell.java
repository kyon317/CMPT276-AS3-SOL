package ca.cmpt276.as3_minesweeper_sol.model;

/**
 * Manage state for a single cell in the game board.
 */
public class Cell {
    private final boolean hasMine;
    private boolean hasBeenScanned = false;
    private boolean hasVisibleMine = false;

    public Cell(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void investigateCell() {
        if (hasVisibleMine) {
            hasBeenScanned = true;
        } else if (hasMine) {
            hasVisibleMine = true;
        } else {
            hasBeenScanned = true;
        }
    }
    public boolean isHasBeenScanned() {
        return hasBeenScanned;
    }

    public boolean isHasVisibleMine() {
        return hasVisibleMine;
    }

    public boolean isHasHiddenMine() {
        return hasMine && !hasVisibleMine;
    }
}
