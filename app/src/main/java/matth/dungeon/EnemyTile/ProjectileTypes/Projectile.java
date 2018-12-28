package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;

import matth.dungeon.Utility.MainUtility;

public class Projectile {

    private final int CHECK_DELAY = 20;

    public MainUtility mainUtility;
    public String projectileName;

    private Handler check = new Handler();
    public boolean terminated;

    Projectile(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    public void initCheck() {

        Handler start = new Handler();
        start.postDelayed(new Runnable() {
            @Override
            public void run() {
                runCheck.run();
            }
        }, 500);
    }

    public void delete() {

    }

    private Runnable runCheck = new Runnable() {
        @Override
        public void run() {

            if (MainUtility.isActive()) {
                check.postDelayed(runCheck, CHECK_DELAY);
            }
            else {
                delete();
                //terminated = true;
                check.removeCallbacksAndMessages(null);
            }

        }
    };
}
