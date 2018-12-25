package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class EnemyEventActivity extends AppCompatActivity {

    public static String LAYOUT_NAME = "enemyLay";

    PlayerSprite playerSprite;

    private ArrayList<Object> enemies;
    private Utility utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        utility = new Utility(this);

        getTileInfo();
        spawnPlayer();
        spawnEnemies();
        SquareEnemy test = new SquareEnemy(utility);
        test.spawnSprite(100, 100, null, null);

        test.spawnProjectile(200, 200, null, null);
    }

    private void getTileInfo() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int[] temp;

            enemies = new ArrayList<>();
            temp = extras.getIntArray("enemies");

            //have to add new for loop for each enemy, so should probably optimize
            for (int i = 0; i < temp[0]; i++) {
                enemies.add(new SquareEnemy(utility));
            }

            for (int i = 0; i < temp[1]; i++) {
                enemies.add(new CircleEnemy(utility));
            }
        }

    }

    private void spawnPlayer() {
        playerSprite = new PlayerSprite(utility.getCon());
        playerSprite.setPlayerImage(utility.addImage(LAYOUT_NAME, PlayerSprite.IMAGE_NAME, utility.getScreenWidth()/2, utility.getScreenHeight()/4));
    }

    private void spawnEnemies() {

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
