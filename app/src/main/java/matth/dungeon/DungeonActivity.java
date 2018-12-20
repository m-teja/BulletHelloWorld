package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class DungeonActivity extends AppCompatActivity {

    Utility utility;
    TileMap tileMap;
    Player player;

    int size = 15; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        utility = new Utility(this);
        tileMap = new TileMap(utility, size);
        player = new Player(tileMap);

    }

    public void toggleMap (View view) {
        if (findViewById(R.id.mapDisp).getVisibility() == View.GONE) {
            tileMap.buildMap();
            findViewById(R.id.mapDisp).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
    }

    public void moveUp(View view) {
        player.moveUp();
    }

    public void moveRight(View view) {
        player.moveRight();
    }

    public void moveDown(View view) {
        player.moveDown();
    }

    public void moveLeft(View view) {
        player.moveLeft();
    }

}