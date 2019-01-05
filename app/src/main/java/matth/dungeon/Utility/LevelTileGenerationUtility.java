package matth.dungeon.Utility;

import android.content.Context;
import android.service.quicksettings.Tile;

import java.util.ArrayList;

import matth.dungeon.GameUI.Inventory;
import matth.dungeon.GameUI.LevelTile;
import matth.dungeon.GameUI.TileMap;

public class LevelTileGenerationUtility {

    private MainUtility mainUtility;
    private ArrayList<ArrayList<LevelTile>> levelMap = new ArrayList<>();

    public LevelTileGenerationUtility(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    public ArrayList<ArrayList<LevelTile>> initLevel(int size, DungeonInitUtility dungeonInitUtility) {

        if (!dungeonInitUtility.loadSave()) {
            initMap(size);
            genEnemies();
            genItems();
            genEnd();
            genStart();
        }
        else {
            loadLevel(dungeonInitUtility);
        }

        if (!dungeonInitUtility.loadSave() && !dungeonInitUtility.loadPlayer()) {
            genPlayerInfo(mainUtility.getCon());
        }

        FileUtility.saveMap(mainUtility.getCon(), levelMap);
        return levelMap;
    }

    private void initMap(int size) {
        for (int i = 0; i < size; i++) {
            levelMap.add(i, new ArrayList<LevelTile>());
            for (int j = 0; j < size; j++) {
                levelMap.get(i).add(j, new LevelTile(LevelTile.WALL));
            }
        }
        createLevel();
        //set all outer tiles to wall
        for (int i = 0; i < levelMap.size(); i++) {
            getTile(i, 0).setType(LevelTile.WALL);
            getTile(i, levelMap.size() -1).setType(LevelTile.WALL);
        }

        for (int i = 0; i < levelMap.size(); i++) {
            getTile(0, i).setType(LevelTile.WALL);
            getTile(levelMap.size() - 1, i).setType(LevelTile.WALL);
        }
    }

    private void loadLevel(DungeonInitUtility dungeonInitUtility) {
        levelMap = FileUtility.loadMap(mainUtility.getCon());
        int pos[] = getPos();
        if (dungeonInitUtility.deleteCurrentTile()) {
            getTile(pos[0], pos[1]).setEvent(LevelTile.EMPTY);
            getTile(pos[0], pos[1]).setType(LevelTile.PLAYER_POS);
        }
    }

    private void createLevel() {

        int tunnelLength = (int) (levelMap.size() * 0.7);
        int tunnelNum = (int) (levelMap.size() * 2);

        int currentRow = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
        int currentCol = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;

        int lastDir = -10;
        int randDir;

        int randLength;
        int currentLength;

        while (tunnelNum > 0 && tunnelLength > 0) {
            do {
                randDir = (int) (Math.random() * 4);
            }
            while (randDir == lastDir || Math.abs(randDir - lastDir) == 2);

            randLength = (int) Math.ceil(Math.random() * tunnelLength);
            currentLength = 0;

            while (currentLength < randLength && !(currentRow <= 1 && randDir == 0 || currentCol >= levelMap.size() - 2 && randDir == 1 || currentRow >= levelMap.size() - 2 && randDir == 2 || currentCol <= 1 && randDir == 3)) {

                getTile(currentCol, currentRow).setType(LevelTile.EMPTY);

                if (randDir == 0) {
                    currentRow--;
                }
                else if (randDir == 1) {
                    currentCol++;
                }
                else if (randDir == 2) {
                    currentRow++;
                }
                else if (randDir == 3) {
                    currentCol--;
                }
                currentLength++;

            }
            if (currentLength > 0) {
                lastDir = randDir;
                tunnelNum--;
            }
        }
    }

    private void genEnemies() {
        for (int i = 0; i < levelMap.size()/5; i++) {
            int enemyCol;
            int enemyRow;

            do {
                enemyCol = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
                enemyRow = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
            }
            while (cannotGen(enemyCol, enemyRow));

            getTile(enemyCol, enemyRow).setEvent(LevelTile.ENEMY_EVENT);

            int numEnemies = (int)(Math.random() * 4) + 1;

            for (int j = 0; j < numEnemies; j++) {
                //TODO change enemy generation
                int randEnemy = (int)(Math.random() * 2);
                getTile(enemyCol, enemyRow).setEnemy(randEnemy);
            }

        }
    }

    private void genItems() {
        for (int i = 0; i < levelMap.size()/4; i++) {
            int itemCol;
            int itemRow;

            do {
                itemCol = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
                itemRow = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
            }
            while (cannotGen(itemCol, itemRow));

            getTile(itemCol, itemRow).setEvent(LevelTile.ITEM_EVENT);
            getTile(itemCol, itemRow).setRandomEvent(LevelTileGenerationUtility.genRandomEvent());
        }
    }

    public void genStart() {

        int playerRow;
        int playerCol;

        do {
            playerRow = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
            playerCol = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
        }
        while (cannotGen(playerCol, playerRow));

        getTile(playerCol, playerRow).setType(LevelTile.PLAYER_POS);
    }

    public void genEnd() {
        int col;
        int row;

        do {
            row = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
            col = (int) Math.floor(Math.random() * (levelMap.size() - 1)) + 1;
        }
        while(cannotGen(col, row));

        getTile(col, row).setEvent(LevelTile.END_POS);
    }

    public void genPlayerInfo(Context con) {
        PlayerInfoPassUtility playerInfoPassUtility = new PlayerInfoPassUtility();
        FileUtility.savePlayer(playerInfoPassUtility, con);

        Inventory inventory = new Inventory();
        FileUtility.saveInventory(inventory, con);
    }

    private boolean cannotGen(int col, int row) {
        return !(getTile(col, row).getType() == LevelTile.EMPTY && getTile(col, row).getEvent() == LevelTile.NO_EVENT);
    }

    private int[] getPos() {

        int col = 1;
        int row = 1;

        for (int i = 0; i < levelMap.size(); i++) {
            for (int j = 0; j < levelMap.get(i).size(); j++) {
                if (levelMap.get(i).get(j).getType() == LevelTile.PLAYER_POS) {
                    col = i;
                    row = j;
                    break;
                }
            }
        }
        int[] pos = {col, row};
        return pos;
    }

    private static int genRandomEvent() {

        return (int)(Math.random() * LevelTile.RANDOM_EVENT_TYPES.length);
    }

    private LevelTile getTile(int col, int row) {
        return levelMap.get(col).get(row);
    }
}
