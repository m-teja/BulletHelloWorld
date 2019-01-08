package matth.dungeon.GameUI;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import matth.dungeon.R;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class StatsDisplay {

    private MainUtility mainUtility;

    StatsDisplay(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
        init();
    }

    private void init() {
        ConstraintLayout cl = ((Activity) mainUtility.getCon()).findViewById(R.id.statsDisp);
        int width = (int) (mainUtility.getScreenWidth() * 0.8);
        cl.getLayoutParams().width = width;
        cl.getLayoutParams().height = width;
        cl.setX(mainUtility.getScreenWidth()/2 - width/2);
        cl.setY(mainUtility.getScreenHeight()/2 - width/2);
    }

    private void getInfo() {
        PlayerInfoPassUtility playerInfoPassUtility = FileUtility.loadPlayer(mainUtility.getCon());

        ((TextView) ((Activity) mainUtility.getCon()).findViewById(R.id.healthDisp)).setText("Health: " + Float.toString(playerInfoPassUtility.getHealth()));
        ((TextView) ((Activity) mainUtility.getCon()).findViewById(R.id.maxHealthDisp)).setText("Max Health: " + Float.toString(playerInfoPassUtility.getMaxHealth()));
        ((TextView) ((Activity) mainUtility.getCon()).findViewById(R.id.levelDisp)).setText("Level: " + Integer.toString(playerInfoPassUtility.getLevel()));

    }

    void toggle() {
        if (((Activity) mainUtility.getCon()).findViewById(R.id.statsDisp).getVisibility() == View.GONE) {
            ((Activity) mainUtility.getCon()).findViewById(R.id.statsDisp).setVisibility(View.VISIBLE);
            getInfo();
        }
        else {
            ((Activity) mainUtility.getCon()).findViewById(R.id.statsDisp).setVisibility(View.GONE);
        }
    }
}
