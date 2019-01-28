package matth.dungeon.Transitions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

abstract class TransitionBase {

    private Context con;

    TransitionBase(Context con) {
        this.con = con;
    }

    void change() {

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!((Activity) con).isDestroyed()) {
                    Intent intent = new Intent(con, getActivityName());
                    con.startActivity(intent);
                    ((Activity) con).finish();
                }
            }
        }, getDelay());
    }

    abstract int getDelay();
    abstract Class getActivityName();


}
