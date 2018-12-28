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
        runCheck.run();
    }

    private Runnable runCheck = new Runnable() {
        @Override
        public void run() {

            if (MainUtility.isActive()) {
                check.postDelayed(runCheck, CHECK_DELAY);
            }
            else {
                terminated = true;
                check.removeCallbacksAndMessages(null);
            }

        }
    };

    //TODO make periodic runnable check for active activity for all projectiles
}
