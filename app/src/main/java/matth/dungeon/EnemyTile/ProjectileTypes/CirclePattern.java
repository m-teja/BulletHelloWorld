package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class CirclePattern extends Pattern {

    private int spawnDelay = 300;

    private EnemyUtility enemyUtility;
    private MainUtility mainUtility;

    private Handler spawnProjectile = new Handler();

    public CirclePattern(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super();
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
    }
    @Override
    public void stop() {
        spawnProjectile.removeCallbacksAndMessages(null);
    }

    @Override
    public void init() {
        spawn.run();
    }

    public Runnable spawn = new Runnable() {
        @Override
        public void run() {

            CircleProjectile circleProjectile = new CircleProjectile(mainUtility, enemyUtility);
            circleProjectile.spawnProjectile(enemyUtility.getPlayerSprite().getX() + enemyUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2, enemyUtility.getPlayerSprite().getY(), null, null);
            circleProjectile.init();

            spawnProjectile.postDelayed(spawn, spawnDelay);
        }
    };
}
