package matth.dungeon.PostGameScreens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import matth.dungeon.R;
import matth.dungeon.Utility.FileUtility;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        FileUtility.deleteFiles(this);
    }

    @Override
    public void onBackPressed() {
        //prevent back press
    }
}
