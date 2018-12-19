package matth.dungeon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class DungeonActivity extends AppCompatActivity {

    Utility utility;
    TileMap tileMap;

    int size = 15; //temp variable, will change for each level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        utility = new Utility(this);
        tileMap = new TileMap(utility, size);

    }

    public void toggleMap (View view) {

        if (findViewById(R.id.mapDisp).getVisibility() == View.GONE) {
            tileMap.buildMap();
        }
        else {
            findViewById(R.id.mapDisp).setVisibility(View.GONE);
        }
    }

}