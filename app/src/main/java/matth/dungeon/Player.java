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
}
