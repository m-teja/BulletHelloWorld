package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;

import matth.dungeon.Utility.MainUtility;

public abstract class Pattern implements PatternBehaviour {

    private int CHECK_DELAY = 20;
    private Handler check = new Handler();

    Pattern() {
        initCheck();
    }

    public abstract void stop();
    public abstract void init();

    public void initCheck() {

        Handler start = new Handler();
        start.postDelayed(new Runnable() {
            @Override
            public void run() {
                runCheck.run();
            }
        }, 500);
    }

    private Runnable runCheck = new Runnable() {
        @Override
        public void run() {

            if (MainUtility.isActive()) {
                check.postDelayed(runCheck, CHECK_DELAY);
            }
            else {
                stop();
                //terminated = true;
                check.removeCallbacksAndMessages(null);
            }

        }
    };
}
