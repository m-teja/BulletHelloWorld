package matth.dungeon.GameUI;

import android.app.Activity;
import android.view.View;

import matth.dungeon.R;
import matth.dungeon.Utility.MainUtility;

public class StatsDisplay {

    private MainUtility mainUtility;

    StatsDisplay(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    void toggle() {
        if (((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).getVisibility() == View.GONE) {
            ((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).setVisibility(View.VISIBLE);
        }
        else {
            ((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).setVisibility(View.GONE);
        }
    }
}
