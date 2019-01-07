package matth.dungeon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import matth.dungeon.GameUI.DungeonActivity;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkSavedAvailable();
    }

    private void checkSavedAvailable() {
        if (FileUtility.loadMap(this) != null) {
            Button button = findViewById(R.id.playSavedButton);
            button.setVisibility(View.VISIBLE);
        }
    }

    public void changeToGame(View view) {
        Intent intent = new Intent(this, DungeonActivity.class);
        intent.putExtra(MainUtility.LOAD_SAVED, false);
        intent.putExtra(MainUtility.LOAD_PLAYER, false);
        startActivity(intent);
    }

    public void changeToSavedGame(View view) {
        Intent intent = new Intent(this, DungeonActivity.class);
        intent.putExtra(MainUtility.LOAD_SAVED, true);
        intent.putExtra(MainUtility.LOAD_PLAYER, true);
        startActivity(intent);
    }
}
