package matth.dungeon.GameUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import matth.dungeon.R;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.MainUtility;

public class DungeonActivity extends AppCompatActivity {

    private PlayerInfoPassUtility playerInfoPassUtility = null;
    private boolean loadSave = false;
    private boolean loadPlayer = false;

    MainUtility utility;
    TileMap tileMap;
    Player player;

    int size = 15; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);
        getBundle();

        utility = new MainUtility(this);
        createTileMap();
        initPlayer();
        updateText();

    }

    private void getBundle() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            loadSave = extras.getBoolean(MainUtility.LOAD_SAVED);
            loadPlayer = extras.getBoolean(MainUtility.LOAD_PLAYER);
        }
    }

    private void initPlayer() {
        player = new Player(tileMap, playerInfoPassUtility);
    }

    private void createTileMap() {
        tileMap = new TileMap(utility, size, loadSave, loadPlayer);
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

        //get the tile in the form of { up, right, down, left}
        LevelTile info[] = {(tileMap.getTile(col, row - 1))
                , (tileMap.getTile(col + 1, row))
                , (tileMap.getTile(col, row + 1))
                , (tileMap.getTile(col - 1, row))};

        TextView up = findViewById(R.id.upInfo);
        TextView right = findViewById(R.id.rightInfo);
        TextView down = findViewById(R.id.downInfo);
        TextView left = findViewById(R.id.leftInfo);

        up.setText(TextLines.getLine(info[0], 0));
        right.setText(TextLines.getLine(info[1], 1));
        down.setText(TextLines.getLine(info[2], 2));
        left.setText(TextLines.getLine(info[3], 3));

        TextLines.animateText(up, right, down, left);

        checkArrows(info);
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
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveUp();
        updateText();
    }

    public void moveRight(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveRight();
        updateText();
    }

    public void moveDown(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveDown();
        updateText();
    }

    public void moveLeft(View view) {
        findViewById(R.id.mapDisp).setVisibility(View.GONE);
        player.moveLeft();
        updateText();
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