package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public abstract class PlayerProjectile extends Projectile {

    PlayerUtility playerUtility;

    private Handler moveProjectile = new Handler();

    PlayerProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility);
        this.playerUtility = playerUtility;
        initCheck();
    }

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

    public abstract void init();

    public void delete() {
        moveProjectile.removeCallbacksAndMessages(null);
    }

    public abstract void effect(Enemy enemy);

    public abstract void movePattern();

    public void initCheck() {
        super.initCheck();
    }

}
