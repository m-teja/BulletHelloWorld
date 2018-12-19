package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Level;

public class DungeonActivity extends AppCompatActivity {

    ArrayList<ArrayList<LevelTile>> levelMap;

    int size = 5; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        initLevel();
    }

    private void initLevel() {
        levelMap = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            levelMap.add(i, new ArrayList<LevelTile>());
            for (int j = 0; j < size; j++) {
                levelMap.get(i).add(j, new LevelTile(j));
            }
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Log.d("test", ((LevelTile)((ArrayList)levelMap.get(i)).get(j)).test());
            }
        }

    }

}
