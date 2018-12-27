package matth.dungeon;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;

public class PlayerProjectileClassic extends PlayerProjectile implements  ProjectileBehaviour {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;
    private final int ANIMATION_DELAY = 15;
    private final int VELOCITY = 10;

    private Handler moveProjectile = new Handler();
    private boolean terminated = false;


    PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
        super.damage = DAMAGE;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {
        move.run();
    }

    public void delete() {

        moveProjectile.removeCallbacksAndMessages(null);
        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getProjectileImage());
    }

    public void effect(Enemy enemy) {
        enemy.takeDamage(DAMAGE);
        terminated = true;
    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {
            PlayerUtility.moveImage(PlayerProjectileClassic.super.getProjectileImage(), PlayerProjectileClassic.super.getX(), PlayerProjectileClassic.super.getY() - VELOCITY);
            playerUtility.enemyOverlap(PlayerProjectileClassic.this);

            if (!terminated) {
                moveProjectile.postDelayed(move, ANIMATION_DELAY);
            }
            else {
                delete();
            }

        }
    };
}
