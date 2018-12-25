package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class EnemyEventActivity extends AppCompatActivity {

    PlayerSprite playerSprite;

    int enemies[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        Utility utility = new Utility(this);

        getTileInfo();
        spawnEnemies();
        SquareEnemy test = new SquareEnemy(utility);
        test.spawnSprite(100, 100, null, null);

        test.spawnProjectile(200, 200, null, null);
    }

    private void getTileInfo() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            enemies = extras.getIntArray("enemies");
        }
    }

    private void spawnEnemies() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return false;
    }
}
