package matth.dungeon.GameUI;

import android.util.Log;

import matth.dungeon.Utility.PlayerInfoPassUtility;

public class Player {

    private TileMap tileMap;
    private PlayerInfoPassUtility playerInfoPassUtility;

    private int playerRow;
    private int playerCol;

    private float maxHealth;
    private float health;

    Player(TileMap tileMap, PlayerInfoPassUtility playerInfoPassUtility) {
        this.tileMap = tileMap;
        this.playerInfoPassUtility = playerInfoPassUtility;
        initPlayer();
        getInfo();
    }

    private void initPlayer() {
        int playerStart[] = tileMap.getPos();

        playerCol = playerStart[0];
        playerRow = playerStart[1];
    }

    private void getInfo() {
        if (playerInfoPassUtility != null) {
            maxHealth = playerInfoPassUtility.getMaxHealth();
            health = playerInfoPassUtility.getHealth();
            Log.d("health", Float.toString(health));
        }
    }

    public int[] getPlayerPos() {
        int[] playerPos = {playerCol, playerRow};
        return playerPos;
    }

    public void moveUp() {
        if (!(tileMap.getTile(playerCol, playerRow - 1).getType() == LevelTile.WALL)) {
            playerRow--;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveRight() {
        if (!(tileMap.getTile(playerCol + 1, playerRow).getType() == LevelTile.WALL)) {
            playerCol++;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveDown() {
        if (!(tileMap.getTile(playerCol, playerRow + 1).getType() == LevelTile.WALL)) {
            playerRow++;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }

    public void moveLeft() {
        if (!(tileMap.getTile(playerCol - 1, playerRow).getType() == LevelTile.WALL)) {
            playerCol--;
            tileMap.setPlayerPos(playerCol, playerRow);
        }
    }
}
