package matth.dungeon.EnemyTile.ProjectileTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class PlayerProjectileClassic extends PlayerProjectile implements  ProjectileBehaviour {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;
    private final int ANIMATION_DELAY = 15;
    private final int VELOCITY = 25;

    private Handler moveProjectile = new Handler();

    PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
        super.damage = DAMAGE;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {
        move.run();
    }

    public void delete() {
        Log.d("test", "projectile terminated");
        terminated = true;
        moveProjectile.removeCallbacksAndMessages(null);
        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getProjectileImage());
    }

    public void outOfBounds() {
        if (super.getProjectileImage().getY() < 0) {
            delete();
        }
        if (super.getProjectileImage().getY() > mainUtility.getScreenHeight()) {
            delete();
        }
        if (super.getProjectileImage().getX() < 0) {
            delete();
        }
        if (super.getProjectileImage().getY() > mainUtility.getScreenHeight()) {
            delete();
        }
    }

    public void effect(Enemy enemy) {
        enemy.takeDamage(DAMAGE);
        delete();
    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {
            PlayerUtility.moveImage(PlayerProjectileClassic.super.getProjectileImage(), PlayerProjectileClassic.super.getX(), PlayerProjectileClassic.super.getY() - VELOCITY);
            outOfBounds();
            playerUtility.enemyOverlap(PlayerProjectileClassic.this);

            if (!terminated) {
                moveProjectile.postDelayed(move, ANIMATION_DELAY);
            }
        }
    };
}
//TODO comment everything