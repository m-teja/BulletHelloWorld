package matth.dungeon.RandomEventTile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import matth.dungeon.GameUI.DungeonActivity;
import matth.dungeon.GameUI.LevelTile;
import matth.dungeon.GameUI.TileMap;
import matth.dungeon.R;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class RandomEventActivity extends AppCompatActivity {

    LevelTile levelTile;
    RandomEvent randomEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_event);
        getTile();
        getEvent(getPlayerInfoPassUtility());
        initRandomEvent();
    }

    private PlayerInfoPassUtility getPlayerInfoPassUtility() {
        return FileUtility.loadPlayer(this);
    }

    private void getTile() {
        ArrayList<ArrayList<LevelTile>> levelMap = FileUtility.loadMap(this);
        int pos[] = TileMap.getPos(levelMap);
        levelTile = levelMap.get(pos[0]).get(pos[1]);
    }

    private void getEvent(PlayerInfoPassUtility playerInfoPassUtility) {
        Class<?> event = levelTile.getRandomEvent();
        Class eventArgs[] = new Class[2];
        eventArgs[0] = PlayerInfoPassUtility.class;
        eventArgs[1] = Context.class;

        try {
            randomEvent = (RandomEvent)event.getDeclaredConstructor(eventArgs).newInstance(playerInfoPassUtility, this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initRandomEvent() {
        randomEvent.setText();
        randomEvent.immediateEffect();
    }

    public void changeToDungeon(View view) {
        Intent intent = new Intent(this, DungeonActivity.class);
        intent.putExtra(MainUtility.LOAD_SAVED, true);
        intent.putExtra(MainUtility.DELETE_CURRENT_TILE, true);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //prevent back button
    }


}
