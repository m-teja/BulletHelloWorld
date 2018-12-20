package matth.dungeon;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TileMap {

    final int EMPTY = 0;
    final int WALL = 1;

    private Utility utility;
    int size;
    int tunnelLength;
    int tunnelNum;
    private ArrayList<ArrayList<LevelTile>> levelMap;


    TileMap(Utility utility, int size) {
        this.utility = utility;
        this.size = size;
        tunnelLength = (int) (size * 0.8);
        tunnelNum = (int) (size * 0.8);
        initLevel();
    }


    public void buildMap() {
        ConstraintLayout map = ((Activity) utility.getCon()).findViewById(R.id.mapDisp);
        ConstraintSet set = new ConstraintSet();

        int mapWidth = (int) (utility.getScreenWidth() * 0.8);
        int mapHeight = mapWidth;
        map.setVisibility(View.VISIBLE);
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


                if (((LevelTile) ((ArrayList) levelMap.get(i)).get(j)).getType() == 1) {
                    image.setImageResource(utility.getCon().getResources().getIdentifier("wall", "drawable", utility.getCon().getPackageName()));
                } else if (((LevelTile) ((ArrayList) levelMap.get(i)).get(j)).getType() == 0) {
                    image.setImageResource(utility.getCon().getResources().getIdentifier("empty", "drawable", utility.getCon().getPackageName()));
                }

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

    private void initLevel() {
        levelMap = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            levelMap.add(i, new ArrayList<LevelTile>());
            for (int j = 0; j < size; j++) {
                levelMap.get(i).add(j, new LevelTile(1));
            }
        }
        createLevel();
        //set all outer tiles to wall
        for (int i = 0; i < size; i++) {
            levelMap.get(i).get(0).setType(1);
            levelMap.get(i).get(size - 1).setType(1);
        }

        for (int i = 0; i < size; i++) {
            levelMap.get(0).get(i).setType(1);
            levelMap.get(size - 1).get(i).setType(1);
        }

    }

    private void createLevel() {

        int currentRow = (int) Math.floor(Math.random() * (size - 1)) + 1;
        int currentCol = (int) Math.floor(Math.random() * (size - 1)) + 1;

        int lastDir = -1;
        int randDir;

        int randLength;
        int currentLength = 0;

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
                Log.d("test", "yes" + Integer.toString(randDir));

                levelMap.get(currentCol).get(currentRow).setType(EMPTY);

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
            if (currentLength < randLength) {
                lastDir = randDir;
                tunnelNum--;
            }

        }
    }


    private LevelTile genTile() {

        double rand = Math.random() * 100;
        LevelTile current = new LevelTile(-1);

        if (rand > 50) {
            //wall
            current.setType(WALL);
        } else {
            //open tile
            current.setType(EMPTY);
        }

        return current;
    }
}
