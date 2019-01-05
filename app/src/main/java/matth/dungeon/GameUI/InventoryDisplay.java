package matth.dungeon.GameUI;

import android.app.Activity;
import android.view.View;

import matth.dungeon.R;
import matth.dungeon.Utility.MainUtility;

public class InventoryDisplay {

    private MainUtility mainUtility;

    public InventoryDisplay(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    public void toggleInventory() {
        if (((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).getVisibility() == View.GONE) {
            ((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).setVisibility(View.VISIBLE);
        }
        else {
            ((Activity) mainUtility.getCon()).findViewById(R.id.invDisp).setVisibility(View.GONE);
        }
    }

    private void buildInv() {

    }
}
