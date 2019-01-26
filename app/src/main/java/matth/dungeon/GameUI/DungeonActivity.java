package matth.dungeon.GameUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import matth.dungeon.R;
import matth.dungeon.Utility.DungeonInitUtility;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class DungeonActivity extends AppCompatActivity {

    private DungeonInitUtility dungeonInitUtility;
    private InventoryDisplay inventoryDisplay;
    private StatsDisplay statsDisplay;

    MainUtility mainUtility;
    TileMap tileMap;

    int size = 15; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        getBundle();

        mainUtility = new MainUtility(this);
        inventoryDisplay = new InventoryDisplay(mainUtility);
        statsDisplay = new StatsDisplay(mainUtility);
        createTileMap();
        updateText(tileMap.getPos());

    }

    private void getBundle() {

        Bundle extras = getIntent().getExtras();
        dungeonInitUtility = new DungeonInitUtility(extras);

        if (extras != null) {
            boolean loadSave = extras.getBoolean(MainUtility.LOAD_SAVED);
            boolean loadPlayer = extras.getBoolean(MainUtility.LOAD_PLAYER);
            boolean deleteCurrentTile = extras.getBoolean(MainUtility.DELETE_CURRENT_TILE);

            dungeonInitUtility.setLoadSave(loadSave);
            dungeonInitUtility.setLoadPlayer(loadPlayer);
            dungeonInitUtility.setDeleteCurrentTile(deleteCurrentTile);
        }
    }

    private void createTileMap() {
        tileMap = new TileMap(mainUtility, size, dungeonInitUtility);
    }

    public void toggleMap (View view) {
        //TODO move this to tilemap
        if (findViewById(R.id.mapDisp).getVisibility() == View.GONE) {
            tileMap.buildMap();
            findViewById(R.id.mapDisp).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
    }

    public void toggleInventory (View view) {
        inventoryDisplay.toggleInventory();
    }

    public void toggleStats(View view) {
        statsDisplay.toggle();
    }

    private void updateText(int[] playerPos) {
        //get the tile in the form of { up, right, down, left}
        LevelTile info[] = {(tileMap.getTile(playerPos[0], playerPos[1] - 1))
                , (tileMap.getTile(playerPos[0] + 1, playerPos[1]))
                , (tileMap.getTile(playerPos[0], playerPos[1] + 1))
                , (tileMap.getTile(playerPos[0] - 1, playerPos[1]))};

        checkArrows(info);

        TextView up = findViewById(R.id.upInfo);
        TextView right = findViewById(R.id.rightInfo);
        TextView down = findViewById(R.id.downInfo);
        TextView left = findViewById(R.id.leftInfo);

        up.setText(TextLines.getLine(info[0], 0));
        right.setText(TextLines.getLine(info[1], 1));
        down.setText(TextLines.getLine(info[2], 2));
        left.setText(TextLines.getLine(info[3], 3));

        TextLines.animateText(up, right, down, left);

    }

    private void checkArrows(LevelTile info[]) {

        if (info[0].getType() == LevelTile.WALL) {
            findViewById(R.id.arrowUp).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.arrowUp).setVisibility(View.VISIBLE);
        }

        if (info[1].getType() == LevelTile.WALL) {
            findViewById(R.id.arrowRight).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.arrowRight).setVisibility(View.VISIBLE);
        }

        if (info[2].getType() == LevelTile.WALL) {
            findViewById(R.id.arrowDown).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.arrowDown).setVisibility(View.VISIBLE);
        }

        if (info[3].getType() == LevelTile.WALL) {
            findViewById(R.id.arrowLeft).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.arrowLeft).setVisibility(View.VISIBLE);
        }
    }

    public void moveUp(View view) {

        if (!isFinishing()) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
            int pos[] = tileMap.getPos();
            pos[1]--;
            tileMap.setPlayerPos(pos[0], pos[1]);
            updateText(pos);
        }
    }

    public void moveRight(View view) {

        if (!isFinishing()) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
            int pos[] = tileMap.getPos();
            pos[0]++;
            tileMap.setPlayerPos(pos[0], pos[1]);
            updateText(pos);
        }
    }

    public void moveDown(View view) {

        if (!isFinishing()) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
            int pos[] = tileMap.getPos();
            pos[1]++;
            tileMap.setPlayerPos(pos[0], pos[1]);
            updateText(pos);
        }
    }

    public void moveLeft(View view) {

        if (!isFinishing()) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
            int pos[] = tileMap.getPos();
            pos[0]--;
            tileMap.setPlayerPos(pos[0], pos[1]);
            updateText(pos);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (findViewById(R.id.mapDisp).getVisibility() == View.VISIBLE) {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        //prevent back press
    }
}