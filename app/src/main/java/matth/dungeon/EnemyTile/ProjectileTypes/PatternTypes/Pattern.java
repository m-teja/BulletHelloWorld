package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;
import android.support.annotation.CallSuper;

import matth.dungeon.EnemyTile.ProjectileTypes.Projectile;
import matth.dungeon.Utility.MainUtility;

public abstract class Pattern implements PatternBehaviour {

    private int CHECK_DELAY = 20;
    int spawnDelay;
    private boolean terminated = false;
    private Handler check = new Handler();
    private Handler spawnProjectileDelay = new Handler();

    Pattern(MainUtility mainUtility) {
        getSpawnDelay();
        initCheck();
    }

    public abstract void init();
    public abstract void spawnPattern();
    public abstract void getSpawnDelay();

    public final void deleteAll() {
        delete();
        spawnProjectileDelay.removeCallbacksAndMessages(null);
    }

    public abstract void delete();

    private void initCheck() {

        Handler start = new Handler();
        start.postDelayed(new Runnable() {
            @Override
            public void run() {
                runCheck.run();
            }
        }, 500);
    }

    Runnable spawn = new Runnable() {
        @Override
        public void run() {

            spawnPattern();
            if (!terminated) {
                spawnProjectileDelay.postDelayed(spawn, spawnDelay);
            }

        }
    };

    private Runnable runCheck = new Runnable() {
        @Override
        public void run() {

            if (MainUtility.isActive()) {
                check.postDelayed(runCheck, CHECK_DELAY);
            }
            else {
                deleteAll();
                terminated = true;
                check.removeCallbacksAndMessages(null);
            }

        }
    };
}
