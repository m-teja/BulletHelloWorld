package matth.dungeon;

import android.content.Context;

public class Utility {

    Context con;

    Utility(Context con) {
        this.con = con;
    }

    public int getScreenHeight() {
        return con.getResources().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return con.getResources().getDisplayMetrics().widthPixels;
    }
}
