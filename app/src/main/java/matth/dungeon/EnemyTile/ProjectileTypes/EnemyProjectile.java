package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public abstract class EnemyProjectile extends Projectile {

    EnemyUtility enemyUtility;

    EnemyProjectile(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        this.enemyUtility = enemyUtility;
        initCheck();
    }

    public abstract void effect();

    Runnable move = new Runnable() {
        @Override
        public void run() {
            movePattern();
            outOfBounds();

            if (enemyUtility.checkPlayerOverlap(projectileImage)) {
                effect();
            }

            if (!terminated) {
                moveProjectile.postDelayed(move, ANIMATION_DELAY);
            }
        }
    };
}
