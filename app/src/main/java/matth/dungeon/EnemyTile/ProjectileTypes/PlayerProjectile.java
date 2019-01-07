package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public abstract class PlayerProjectile extends Projectile {

    PlayerUtility playerUtility;

    PlayerProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
        this.playerUtility = playerUtility;
        initCheck();
    }

    public abstract void effect(Enemy enemy);

    Runnable move = new Runnable() {
        @Override
        public void run() {
            movePattern();
            outOfBounds();
            playerUtility.enemyOverlap(PlayerProjectile.this);

            if (!terminated) {
                moveProjectile.postDelayed(move, ANIMATION_DELAY);
            }
        }
    };

}
