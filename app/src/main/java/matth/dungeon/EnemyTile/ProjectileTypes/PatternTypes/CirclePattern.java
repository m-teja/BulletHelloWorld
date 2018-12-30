package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;

import matth.dungeon.EnemyTile.ProjectileTypes.CircleProjectile;
import matth.dungeon.EnemyTile.SpriteTypes.CircleEnemy;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CirclePattern extends Pattern {

    private int spawnDelay = 300;
    private boolean terminated = false;

    private EnemyUtility enemyUtility;
    private MainUtility mainUtility;
    private CircleEnemy circleEnemy;

    private Handler spawnProjectile = new Handler();

    public CirclePattern(MainUtility mainUtility, EnemyUtility enemyUtility, CircleEnemy circleEnemy) {
        super();
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
        this.circleEnemy = circleEnemy;
    }
    @Override
    public void stop() {
        terminated = true;
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
            circleProjectile.spawnProjectile(circleEnemy.getX() + circleEnemy.getSprite().getMeasuredWidth()/2, circleEnemy.getY() + circleEnemy.getSprite().getMeasuredHeight(), null, null);
            circleProjectile.init();

            if (!terminated) {
                spawnProjectile.postDelayed(spawn, spawnDelay);
            }

        }
    };
}
