package matth.dungeon.RandomEventTile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import matth.dungeon.GameUI.LevelTile;
import matth.dungeon.GameUI.TileMap;
import matth.dungeon.R;
import matth.dungeon.Utility.FileUtility;

public class RandomEventActivity extends AppCompatActivity {

    LevelTile levelTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_event);
        getTile();
        getEvent();
    }

    private void getTile() {
        ArrayList<ArrayList<LevelTile>> levelMap = FileUtility.loadMap(this);
        int pos[] = TileMap.getPos(levelMap);
        levelTile = levelMap.get(pos[0]).get(pos[1]);
    }

    private void getEvent() {
        Class event = levelTile.getRandomEvent();
    }


}
