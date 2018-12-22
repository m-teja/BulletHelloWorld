package matth.dungeon;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TileMap {



    private Utility utility;
    private int size;
    private int tunnelLength;
    private int tunnelNum;
    private ArrayList<ArrayList<LevelTile>> levelMap;

    private int playerCol;
    private int playerRow;


    TileMap(Utility utility, int size) {
        this.utility = utility;
        this.size = size;
        tunnelLength = (int) (size * 0.7);
        tunnelNum = (int) (size * 2);
        initLevel();
    }

    public void buildMap() {
        ConstraintLayout map = ((Activity) utility.getCon()).findViewById(R.id.mapDisp);
        map.removeAllViews();
        ConstraintSet set = new ConstraintSet();

        int mapWidth = (int) (utility.getScreenWidth() * 0.8);
        int mapHeight = mapWidth;
        map.getLayoutParams().height = mapHeight;
        map.getLayoutParams().width = mapWidth;
        map.setX((utility.getScreenWidth() / 2) - map.getLayoutParams().width / 2);
        map.setY((utility.getScreenHeight() / 2) - map.getLayoutParams().height / 2);

        ImageView image;
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        int width = (int) Math.ceil((float) mapWidth / size);
        int height = (int) Math.ceil((float) mapHeight / size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                image = new ImageView(utility.getCon());
                image.setId(View.generateViewId());
                image.setImageResource(utility.getCon().getResources().getIdentifier(getImageType(i, j), "drawable", utility.getCon().getPackageName()));
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

    private String getImageType(int col, int row) {

        if (getTile(col, row).getEvent() == LevelTile.ENEMY_EVENT) {
            return "enemy";
        }
        else if (getTile(col, row).getEvent() == LevelTile.ITEM_EVENT) {
            return "item";
        }

        if (levelMap.get(col).get(row).getType() == LevelTile.EMPTY) {
            return "empty";
        }
        else if (levelMap.get(col).get(row).getType() == LevelTile.WALL) {
            return "wall";
        }
        else if (levelMap.get(col).get(row).getType() == LevelTile.PLAYER_POS) {
            return "player";
        }
        else if (levelMap.get(col).get(row).getType() == LevelTile.END_POS) {
            return "end";
        }
        return "";
    }

    public LevelTile getTile(int col, int row) {
        return levelMap.get(col).get(row);
    }

    private void initLevel() {
        levelMap = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            levelMap.add(i, new ArrayList<LevelTile>());
            for (int j = 0; j < size; j++) {
                levelMap.get(i).add(j, new LevelTile(LevelTile.WALL));
            }
        }
        createLevel();
        //set all outer tiles to wall
        for (int i = 0; i < size; i++) {
            levelMap.get(i).get(0).setType(LevelTile.WALL);
            levelMap.get(i).get(size - 1).setType(LevelTile.WALL);
        }

        for (int i = 0; i < size; i++) {
            levelMap.get(0).get(i).setType(LevelTile.WALL);
            levelMap.get(size - 1).get(i).setType(LevelTile.WALL);
        }


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

                Log.d("test", Integer.toString(currentCol));
                Log.d("test", Integer.toString(currentRow));

                levelMap.get(currentCol).get(currentRow).setType(LevelTile.EMPTY);
                Log.d("test", Integer.toString(levelMap.get(currentCol).get(currentRow).getType()));

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

    public void genEnemies() {
        for (int i = 0; i < size/5; i++) {
            int enemyCol;
            int enemyRow;

            do {
                enemyCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
                enemyRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            }
            while (getTile(enemyCol, enemyRow).getType() != LevelTile.EMPTY && getTile(enemyCol, enemyRow).getEvent() == LevelTile.NO_EVENT);;

            getTile(enemyCol, enemyRow).setEvent(LevelTile.ENEMY_EVENT);
        }
    }

    public void genItems() {
        for (int i = 0; i < size/4; i++) {
            int itemCol;
            int itemRow;

            do {
                itemCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
                itemRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            }
            while (getTile(itemCol, itemRow).getType() != LevelTile.EMPTY && getTile(itemCol, itemRow).getEvent() == LevelTile.NO_EVENT);

            getTile(itemCol, itemRow).setEvent(LevelTile.ITEM_EVENT);
        }
    }

    public void setPlayerPos(int col, int row) {
        levelMap.get(playerCol).get(playerRow).setType(LevelTile.EMPTY);
        levelMap.get(col).get(row).setType(LevelTile.PLAYER_POS);
        playerCol = col;
        playerRow = row;
    }

    public int[] genStart() {

        do {
            playerRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
            playerCol = (int) Math.floor(Math.random() * (size - 1)) + 1;
        }
        while(levelMap.get(playerCol).get(playerRow).getType() == LevelTile.WALL);

        levelMap.get(playerCol).get(playerRow).setType(LevelTile.PLAYER_POS);
        int result[] = {playerCol, playerRow};
        return result;
    }

    public void genEnd() {
        int col;
        int row;

        do {
            row = (int) Math.floor(Math.random() * (size - 1)) + 1;
            col = (int) Math.floor(Math.random() * (size - 1)) + 1;
        }
        while(levelMap.get(col).get(row).getType() == LevelTile.WALL || levelMap.get(col).get(row).getType() == LevelTile.PLAYER_POS);

        levelMap.get(col).get(row).setType(LevelTile.END_POS);
    }
}
