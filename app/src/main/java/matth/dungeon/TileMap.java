package matth.dungeon;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class TileMap {

    Utility utility;
    int size;
    ArrayList<ArrayList<LevelTile>> levelMap;


    TileMap(Utility utility, int size) {
        this.utility = utility;
        this.size = size;
        initLevel();
    }


    public void buildMap() {
        ConstraintLayout map = ((Activity)utility.getCon()).findViewById(R.id.mapDisp);
        ConstraintSet set = new ConstraintSet();

        int mapWidth = (int)(utility.getScreenWidth() * 0.8);
        int mapHeight = mapWidth;

        map.setVisibility(View.VISIBLE);

        map.getLayoutParams().height = mapHeight;
        map.getLayoutParams().width = mapWidth;
        map.setX((utility.getScreenWidth()/2) - map.getLayoutParams().width/2 );
        map.setY((utility.getScreenHeight()/2) - map.getLayoutParams().height/2);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                ImageView image = new ImageView(utility.getCon());
                image.setId(View.generateViewId());
                ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);


                if (((LevelTile)((ArrayList)levelMap.get(i)).get(j)).getType() == 1) {
                    image.setImageResource(utility.getCon().getResources().getIdentifier("wall", "drawable", utility.getCon().getPackageName()));
                }
                else if (((LevelTile)((ArrayList)levelMap.get(i)).get(j)).getType() == 0) {
                    image.setImageResource(utility.getCon().getResources().getIdentifier("empty", "drawable", utility.getCon().getPackageName()));
                }

                image.setLayoutParams(lp);
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image.getLayoutParams().width = (mapWidth/size) -1;
                image.getLayoutParams().height = (mapHeight/size) -1;
                image.setX((int)(mapWidth * ((float)i/size)));
                image.setY((int)(mapHeight * ((float)j/size)));
                Log.d("test", Integer.toString(size));

                map.addView(image);
                set.clone(map);
                set.connect(image.getId(), ConstraintSet.TOP, map.getId(), ConstraintSet.TOP, 0);
                set.applyTo(map);
            }
        }
    }

    private void initLevel() {
        levelMap = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            levelMap.add(i, new ArrayList<LevelTile>());
            for (int j = 0; j < size; j++) {
                levelMap.get(i).add(j, genTile());
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Log.d("test", Integer.toString(((LevelTile)((ArrayList)levelMap.get(i)).get(j)).getType()));
            }
        }
    }

    private LevelTile genTile() {

        double rand = Math.random() * 100;
        LevelTile current = new LevelTile(-1);

        if (rand > 50) {
            //wall
            current.setType(1);
        }
        else {
            //open tile
            current.setType(0);
        }

        return current;
    }
}
