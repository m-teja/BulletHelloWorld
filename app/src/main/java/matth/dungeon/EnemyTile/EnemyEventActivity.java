package matth.dungeon.EnemyTile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.GameUI.DungeonActivity;
import matth.dungeon.GameUI.LevelTile;
import matth.dungeon.GameUI.TileMap;
import matth.dungeon.PostGameScreens.GameOver;
import matth.dungeon.R;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.PlayerUtility;

public class EnemyEventActivity extends AppCompatActivity {

    public static String LAYOUT_NAME = "enemyLay";

    PlayerSprite playerSprite;

    private ArrayList<Enemy> enemies = new ArrayList<>();
    private MainUtility mainUtility;
    private EnemyUtility enemyUtility;
    private PlayerUtility playerUtility;

    private LevelTile levelTile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        mainUtility = new MainUtility(this);
        spawnPlayer();
        enemyUtility = new EnemyUtility(playerSprite, this);

        getTileInfo();
        spawnEnemies();

        playerUtility = new PlayerUtility(enemies, playerSprite);
        playerSprite.initProjectile(mainUtility, playerUtility);
    }

    private void getTileInfo() {

        ArrayList<ArrayList<LevelTile>> levelMap = FileUtility.loadMap(mainUtility.getCon());
        int pos[] = TileMap.getPos(levelMap);

        levelTile = levelMap.get(pos[0]).get(pos[1]);
        ArrayList<Class> enemyClasses = levelTile.getEnemies();
        ArrayList<Class> bossClasses = levelTile.getBosses();

        //get normal enemies
        for (int i = 0; i < enemyClasses.size(); i++) {
            Class<?> classType = enemyClasses.get(i);

            try {
                enemies.add((Enemy) classType.getDeclaredConstructor(MainUtility.class, EnemyUtility.class).newInstance(mainUtility, enemyUtility));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        //get bosses
        for (int i = 0; i < bossClasses.size(); i++) {
            Class<?> classType = bossClasses.get(i);

            try {
                enemies.add((Enemy) classType.getDeclaredConstructor(MainUtility.class, EnemyUtility.class).newInstance(mainUtility, enemyUtility));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        enemyUtility.setEnemies(enemies);
    }

    private void spawnPlayer() {
        PlayerInfoPassUtility playerInfoPassUtility = FileUtility.loadPlayer(this);
        playerSprite = new PlayerSprite(mainUtility, playerInfoPassUtility);
    }

    private void spawnEnemies() {
        enemyUtility.spawnAllEnemies(mainUtility.getScreenWidth());
    }

    public void exitWin() {
        Intent intent = new Intent(mainUtility.getCon(), DungeonActivity.class);
        PlayerInfoPassUtility playerInfoPassUtility = FileUtility.loadPlayer(mainUtility.getCon());

        if (playerInfoPassUtility != null) {
            playerInfoPassUtility.setHealth(playerSprite.getHealth());
            playerInfoPassUtility.setMaxHealth(playerSprite.getMaxHealth());

            FileUtility.savePlayer(playerInfoPassUtility, playerSprite.getCon());
            intent.putExtra(MainUtility.LOAD_PLAYER, true);
            intent.putExtra(MainUtility.DELETE_CURRENT_TILE, true);

            if (checkBoss()) {
                intent.putExtra(MainUtility.LOAD_SAVED, false);
            }
            else {
                intent.putExtra(MainUtility.LOAD_SAVED, true);
            }

            playerSprite.getCon().startActivity(intent);
        }
    }

    private boolean checkBoss() {
        return levelTile.getEvent() == LevelTile.END_POS;
    }

    public static void exitLose(Context con) {
        Intent intent = new Intent(con, GameOver.class);
        con.startActivity(intent);
    }

    //TODO change depending on enemy and projectile behaviour
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
        MainUtility.setActive(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainUtility.setActive(true);
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
        MainUtility.setActive(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        //mainUtility.setActive(false);
    }
}

//TODO fix centering all imageviews after spawn