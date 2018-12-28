package matth.dungeon.EnemyTile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.CircleEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.EnemyTile.SpriteTypes.SquareEnemy;
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
            temp = extras.getIntArray("enemies");

            //have to add new for loop for each enemy, so should probably optimize
            for (int i = 0; i < temp[0]; i++) {
                enemies.add(new SquareEnemy(mainUtility, enemyUtility));
            }

            for (int i = 0; i < temp[1]; i++) {
                enemies.add(new CircleEnemy(mainUtility, enemyUtility));
            }
        }
    }

    private void spawnPlayer() {
        playerSprite = new PlayerSprite(mainUtility.getCon());
        playerSprite.setPlayerImage(mainUtility.addImage(LAYOUT_NAME, PlayerSprite.IMAGE_NAME, mainUtility.getScreenWidth()/2, mainUtility.getScreenHeight()/4));
    }

    private void spawnEnemies() {

        int distance = (int)((float) mainUtility.getScreenWidth()/(enemies.size() + 1));
        for (int i = 0; i < enemies.size(); i++) {

            ((Enemy)enemies.get(i)).spawnSprite((i + 1) * distance, 200, null, null);
            ((Enemy)enemies.get(i)).setX(((Enemy) enemies.get(i)).getX() - playerSprite.getPlayerImage().getMeasuredWidth());
            ((Enemy)enemies.get(i)).init();

        }
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
        super.onBackPressed();
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