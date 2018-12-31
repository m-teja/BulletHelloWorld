package matth.dungeon.GameUI;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.R;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class TileMap {

    private MainUtility mainUtility;
    private int size;
    private int tunnelLength;
    private int tunnelNum;
    private ArrayList<ArrayList<LevelTile>> levelMap;
    private PlayerInfoPassUtility playerInfoPassUtility;

    private int playerCol;
    private int playerRow;


    TileMap(MainUtility mainUtility, int size, boolean saveFile, boolean fromEnemyEvent) {
        this.mainUtility = mainUtility;
        this.size = size;
        tunnelLength = (int) (size * 0.7);
        tunnelNum = (int) (size * 2);
        initLevel(saveFile, fromEnemyEvent);
    }

    public void buildMap() {
        ConstraintLayout map = ((Activity) mainUtility.getCon()).findViewById(R.id.mapDisp);
        map.removeAllViews();
        ConstraintSet set = new ConstraintSet();

        int mapWidth = (int) (mainUtility.getScreenWidth() * 0.8);
        int mapHeight = mapWidth;
        map.getLayoutParams().height = mapHeight;
        map.getLayoutParams().width = mapWidth;
        map.setX((mainUtility.getScreenWidth() / 2) - map.getLayoutParams().width / 2);
        map.setY((mainUtility.getScreenHeight() / 2) - map.getLayoutParams().height / 2);

        ImageView image;
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        int width = (int) Math.ceil((float) mapWidth / size);
        int height = (int) Math.ceil((float) mapHeight / size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                image = new ImageView(mainUtility.getCon());
                image.setId(View.generateViewId());
                image.setImageResource(mainUtility.getCon().getResources().getIdentifier(getImageType(i, j), "drawable", mainUtility.getCon().getPackageName()));
                image.setLayoutParams(lp);
                image.getLayoutParams().width = width;
                image.getLayoutParams().height = height;
                image.setX((int) (mapWidth * ((float) i / size)));
                image.setY((int) (mapHeight * ((float) j / size)));

                map.addView(image);
                set.clone(map);
                set.connect(image.getId(), ConstraintSet.TOP, map.getId(), ConstraintSet.TOP, 0);

            }
        }
        set.applyTo(map);
    }

    //TODO move to leveltile
    private String getImageType(int col, int row) {

        if (getTile(col, row).getType() == LevelTile.PLAYER_POS) {
            return LevelTile.PLAYER_POS_IMAGE;
        }

        switch (getTile(col, row).getEvent()) {
            case LevelTile.ENEMY_EVENT:
                return LevelTile.ENEMY_EVENT_IMAGE;
            case LevelTile.ITEM_EVENT:
                return LevelTile.ITEM_EVENT_IMAGE;
        }

        switch (getTile(col, row).getType()) {
            case LevelTile.EMPTY:
                return LevelTile.EMPTY_IMAGE;
            case LevelTile.WALL:
                return LevelTile.WALL_IMAGE;
            case LevelTile.END_POS:
                return LevelTile.END_POS_IMAGE;
            default:
                break;
        }

        return "";
    }

    public LevelTile getTile(int col, int row) {
        return levelMap.get(col).get(row);
    }

    private void setTileEvent(int col, int row, int event) {
        levelMap.get(col).get(row).setEvent(event);
    }

    private void initLevel(boolean saveFile, boolean fromEnemyEvent) {
        levelMap = new ArrayList<>();

        if (!saveFile) {
            for (int i = 0; i < size; i++) {
                levelMap.add(i, new ArrayList<LevelTile>());
                for (int j = 0; j < size; j++) {
                    levelMap.get(i).add(j, new LevelTile(LevelTile.WALL));
                }
            }
            createLevel();
            //set all outer tiles to wall
            for (int i = 0; i < size; i++) {
                getTile(i, 0).setType(LevelTile.WALL);
                getTile(i, size -1).setType(LevelTile.WALL);
            }

            for (int i = 0; i < size; i++) {
                getTile(0, i).setType(LevelTile.WALL);
                getTile(size - 1, i).setType(LevelTile.WALL);
            }
            gen();
        }
        else {
            loadLevel(fromEnemyEvent);
        }
        FileUtility.saveMap(mainUtility.getCon(), levelMap);
    }

    private void loadLevel(boolean fromEnemyEvent) {
        levelMap = FileUtility.loadMap(mainUtility.getCon());
        playerInfoPassUtility = FileUtility.loadPlayer(mainUtility.getCon());
        getPos();

        if (fromEnemyEvent) {
            setTileEvent(playerCol, playerRow, LevelTile.NO_EVENT);
        }

        checkTile();
    }

    private void createLevel() {

        int currentRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
        int currentCol = (int) Math.floor(Math.random() * (size - 1)) + 1;

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

            while (currentLength < randLength && !(currentRow <= 1 && randDir == 0 || currentCol >= size - 2 && randDir == 1 || currentRow >= size - 2 && randDir == 2 || currentCol <= 1 && randDir == 3)) {

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

    private void gen() {
        genStart();
        genEnd();
        genEnemies();
        genItems();
        genPlayerInfo();
    }

    private void genEnemies() {
        for (int i = 0; i < size/5; i++) {
            int enemyCol;
            int enemyRow;

            do {
                enemyCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
                enemyRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            }
            while (cannotGen(enemyCol, enemyRow));

            getTile(enemyCol, enemyRow).setEvent(LevelTile.ENEMY_EVENT);

            int numEnemies = (int)(Math.random() * 4) + 1;

            for (int j = 0; j < numEnemies; j++) {
                int randEnemy = (int)(Math.random() * LevelTile.ENEMY_TYPES.length);
                getTile(enemyCol, enemyRow).setEnemy(randEnemy);
            }

        }
    }

    private void genItems() {
        for (int i = 0; i < size/4; i++) {
            int itemCol;
            int itemRow;

            do {
                itemCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
                itemRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            }
            while (cannotGen(itemCol, itemRow));

            getTile(itemCol, itemRow).setEvent(LevelTile.ITEM_EVENT);
        }
    }

    private void genStart() {

        do {
            playerRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            playerCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
        }
        while (cannotGen(playerCol, playerRow));

        getTile(playerCol, playerRow).setType(LevelTile.EMPTY);
        getTile(playerCol, playerRow).setType(LevelTile.PLAYER_POS);
    }

    private void genEnd() {
        int col;
        int row;

        do {
            row = (int) Math.floor(Math.random() * (size - 1)) + 1;
            col = (int) Math.floor(Math.random() * (size - 1)) + 1;
        }
        while(cannotGen(col, row));

        getTile(col, row).setType(LevelTile.END_POS);
    }

    private void genPlayerInfo() {
        playerInfoPassUtility = new PlayerInfoPassUtility();
        FileUtility.savePlayer(playerInfoPassUtility, mainUtility.getCon());
    }

    private boolean cannotGen(int col, int row) {
        if (!(getTile(col, row).getType() == LevelTile.EMPTY && getTile(playerCol, playerRow).getEvent() == LevelTile.NO_EVENT)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setPlayerPos(int col, int row) {
        getTile(playerCol, playerRow).setType(LevelTile.EMPTY);
        playerCol = col;
        playerRow = row;
        checkTile();
        getTile(col, row).setType(LevelTile.PLAYER_POS);
        FileUtility.saveMap(mainUtility.getCon(), levelMap);
    }

    public int[] getPos() {

        for (int i = 0; i < levelMap.size(); i++) {
            for (int j = 0; j < levelMap.get(i).size(); j++) {
                if (levelMap.get(i).get(j).getType() == LevelTile.PLAYER_POS) {
                    playerCol = i;
                    playerRow = j;
                    break;
                }
            }
        }
        int[] pos = {playerCol, playerRow};
        return pos;
    }

    private void checkTile() {

        if (getTile(playerCol, playerRow).getType() == LevelTile.END_POS) {
            //temp boss square
            int[] boss = {0, 0, 1};
            Intent intent = new Intent(mainUtility.getCon(), EnemyEventActivity.class);
            intent.putExtra("enemies", boss);
            mainUtility.getCon().startActivity(intent);
        }

        if (getTile(playerCol, playerRow).getEvent() == LevelTile.ENEMY_EVENT) {
            Intent intent = new Intent(mainUtility.getCon(), EnemyEventActivity.class);
            intent.putExtra("enemies", getTile(playerCol, playerRow).getEnemies());
            mainUtility.getCon().startActivity(intent);
        }


    }
}
