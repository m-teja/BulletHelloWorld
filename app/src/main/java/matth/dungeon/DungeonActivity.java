package matth.dungeon;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.logging.Level;

public class DungeonActivity extends AppCompatActivity {

    ArrayList<ArrayList<LevelTile>> levelMap;
    Utility utility;

    int size = 5; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        initLevel();
        utility = new Utility(this);
    }

    public void toggleMap (View view) {

        if (findViewById(R.id.mapDisp).getVisibility() == View.GONE) {
            buildMap();
        }
        else {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
    }

    public void buildMap() {
        ConstraintLayout map = findViewById(R.id.mapDisp);
        ConstraintSet set = new ConstraintSet();
        int mapHeight = (int)(utility.getScreenHeight() * 0.6);
        int mapWidth = (int)(utility.getScreenWidth() * 0.8);

        map.setVisibility(View.VISIBLE);

        map.getLayoutParams().height = mapHeight;
        map.getLayoutParams().width = mapWidth;
        map.setX((utility.getScreenWidth()/2) - map.getLayoutParams().width/2 );
        map.setY((utility.getScreenHeight()/2) - map.getLayoutParams().height/2);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                ImageView image = new ImageView(this);
                image.setId(View.generateViewId());
                ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);


                if (((LevelTile)((ArrayList)levelMap.get(i)).get(j)).getType() == 1) {
                    image.setImageResource(getResources().getIdentifier("wall", "drawable", getPackageName()));
                }
                else if (((LevelTile)((ArrayList)levelMap.get(i)).get(j)).getType() == 0) {
                    image.setImageResource(getResources().getIdentifier("empty", "drawable", getPackageName()));
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
