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
        playerRow--;
        tileMap.setPlayerPos(playerCol, playerRow);
    }

    public void moveRight() {
        playerCol++;
        tileMap.setPlayerPos(playerCol, playerRow);
    }

    public void moveDown() {
        playerRow++;
        tileMap.setPlayerPos(playerCol, playerRow);

    }

    public void moveLeft() {
        playerCol--;
        tileMap.setPlayerPos(playerCol, playerRow);
    }
}
