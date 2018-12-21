package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DungeonActivity extends AppCompatActivity {

    Utility utility;
    TileMap tileMap;
    Player player;
    TextLines textLines;

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

    private void updateText() {
        int[] playerPos = player.getPlayerPos();
        int col = playerPos[0];
        int row = playerPos[1];

        //get the type of tile in the form of { up, right, down, left}
        int info[] = {(tileMap.getTile(col, row - 1)).getType()
                , (tileMap.getTile(col + 1, row)).getType()
                , (tileMap.getTile(col, row + 1)).getType()
                , (tileMap.getTile(col - 1, row)).getType()};


    }

    public void moveUp(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveUp();
    }

    public void moveRight(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveRight();
    }

    public void moveDown(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveDown();
    }

    public void moveLeft(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveLeft();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (findViewById(R.id.mapDisp).getVisibility() == View.VISIBLE) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
        return false;
    }
}