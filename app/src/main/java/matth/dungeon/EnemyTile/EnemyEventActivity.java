package matth.dungeon.EnemyTile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.CircleEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.EnemyTile.SpriteTypes.SquareBossEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.SquareEnemy;
import matth.dungeon.GameUI.DungeonActivity;
import matth.dungeon.PostGameScreens.GameOver;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class EnemyEventActivity extends AppCompatActivity {

    public static String LAYOUT_NAME = "enemyLay";

    PlayerSprite playerSprite;

    private ArrayList<Object> enemies;
    private MainUtility mainUtility;
    private EnemyUtility enemyUtility;
    private PlayerUtility playerUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        mainUtility = new MainUtility(this);
        spawnPlayer();
        enemyUtility = new EnemyUtility(playerSprite);

        getTileInfo();
        spawnEnemies();

        playerUtility = new PlayerUtility(enemies, playerSprite);
        playerSprite.initProjectile(mainUtility, playerUtility);
    }

    private void getTileInfo() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int[] temp;

            enemies = new ArrayList<>();
            enemyUtility.setBoss(extras.getBoolean(MainUtility.BOSS));
            temp = extras.getIntArray(MainUtility.ENEMIES);

            //have to add new for loop for each enemy, so should probably optimize
            for (int i = 0; i < temp[0]; i++) {
                Log.d("test", "square");
                enemies.add(new SquareEnemy(mainUtility, enemyUtility));
            }

            for (int i = 0; i < temp[1]; i++) {
                Log.d("test", "circle");
                enemies.add(new CircleEnemy(mainUtility, enemyUtility));
            }

            for (int i = 0; i < temp[2]; i++) {
                Log.d("test", "squareBoss");
                enemies.add(new SquareBossEnemy(mainUtility, enemyUtility));
            }
            enemyUtility.setEnemies(enemies);
        }
    }

    private void spawnPlayer() {
        PlayerInfoPassUtility playerInfoPassUtility = FileUtility.loadPlayer(this);
        playerSprite = new PlayerSprite(mainUtility, playerInfoPassUtility);
    }

    private void spawnEnemies() {
        enemyUtility.spawnAllEnemies(mainUtility.getScreenWidth());
    }

    public static void exitWin(PlayerSprite playerSprite, boolean boss) {
        Intent intent = new Intent(playerSprite.getCon(), DungeonActivity.class);

        PlayerInfoPassUtility playerInfoPassUtility = new PlayerInfoPassUtility(playerSprite);
        FileUtility.savePlayer(playerInfoPassUtility, playerSprite.getCon());
        intent.putExtra(MainUtility.LOAD_PLAYER, true);
        intent.putExtra(MainUtility.DELETE_CURRENT_TILE, true);

        if (boss) {
            intent.putExtra(MainUtility.LOAD_SAVED, false);
        }
        else {
            intent.putExtra(MainUtility.LOAD_SAVED, true);
        }


        playerSprite.getCon().startActivity(intent);
    }

    public static void exitLose(Context con) {
        Intent intent = new Intent(con, GameOver.class);
        con.startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                playerSprite.setX(x - playerSprite.getPlayerImage().getMeasuredWidth());
                playerSprite.setY(y - 3*playerSprite.getPlayerImage().getMeasuredHeight());
                break;

            case MotionEvent.ACTION_MOVE:
                playerSprite.setX(x - playerSprite.getPlayerImage().getMeasuredWidth());
                playerSprite.setY(y - 3*playerSprite.getPlayerImage().getMeasuredWidth());
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainUtility.setActive(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainUtility.setActive(true);
    }

    @Override
    public void onBackPressed() {
       // prevent back press
       // super.onBackPressed();
        //mainUtility.setActive(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainUtility.setActive(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        //mainUtility.setActive(false);
    }
}

//TODO fix centering all imageviews after spawn