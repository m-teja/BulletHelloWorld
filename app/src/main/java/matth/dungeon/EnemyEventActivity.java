package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class EnemyEventActivity extends AppCompatActivity {

    int enemies[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_event);

        getTileInfo();
        for (int i = 0; i < enemies.length; i++) {
            Log.d("test", LevelTile.ENEMY_TYPES[i] + enemies[i]);
        }
    }

    private void getTileInfo() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            enemies = extras.getIntArray("enemies");
        }
    }
}
