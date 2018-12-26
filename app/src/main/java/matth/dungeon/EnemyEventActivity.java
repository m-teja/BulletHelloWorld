package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.ArrayList;

public class EnemyEventActivity extends AppCompatActivity {

    public static String LAYOUT_NAME = "enemyLay";

    PlayerSprite playerSprite;

    private ArrayList<Object> enemies;
    private MainUtility mainUtility;
    private EnemyUtility enemyUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        mainUtility = new MainUtility(this);
        getTileInfo();
        spawnPlayer();
        spawnEnemies();

        enemyUtility = new EnemyUtility(playerSprite);

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
}
//TODO fix centering all imageviews after spawn