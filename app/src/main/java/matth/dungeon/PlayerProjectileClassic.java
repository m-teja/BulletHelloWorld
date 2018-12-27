package matth.dungeon;

import android.os.Handler;

public class PlayerProjectileClassic extends PlayerProjectile implements  ProjectileBehaviour {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;
    private final int ANIMATION_DELAY = 15;
    private final int VELOCITY = 5;

    private Handler moveProjectile = new Handler();


    PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
        super.damage = DAMAGE;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {

    }

    public void delete() {

    }

    public void effect(Enemy enemy) {
        enemy.takeDamage(DAMAGE);
        delete();
    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {
            PlayerUtility.moveImage(PlayerProjectileClassic.super.getProjectileImage(), PlayerProjectileClassic.super.getX(), PlayerProjectileClassic.super.getY() + VELOCITY);
            playerUtility.enemyOverlap(PlayerProjectileClassic.this);

            moveProjectile.postDelayed(move, ANIMATION_DELAY);
        }
    };
}
