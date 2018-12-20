package matth.dungeon;

public class Player {

    private TileMap tileMap;

    private int playerRow;
    private int playerCol;

    Player(TileMap tileMap) {
        this.tileMap = tileMap;
        initPlayer();
    }

    private void initPlayer() {
        int playerStart[] = tileMap.genStart();
        tileMap.genEnd();
        playerCol = playerStart[0];
        playerRow = playerStart[1];
    }

    public void moveUp() {
        if (!(tileMap.getTile(playerCol, playerRow - 1).getType() == tileMap.WALL)) {
            playerRow--;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveRight() {
        if (!(tileMap.getTile(playerCol + 1, playerRow).getType() == tileMap.WALL)) {
            playerCol++;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveDown() {
        if (!(tileMap.getTile(playerCol, playerRow + 1).getType() == tileMap.WALL)) {
            playerRow++;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveLeft() {
        if (!(tileMap.getTile(playerCol - 1, playerRow).getType() == tileMap.WALL)) {
            playerCol--;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }
}
