package matth.dungeon.EnemyTile.ProjectileTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class PlayerProjectileClassic extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;
    private final int VELOCITY = 25;

    PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    @Override
    public void init() {
        move.run();
    }

    @Override
    public void movePattern() {
        PlayerUtility.moveImage(getProjectileImage(), getX(), getY() - VELOCITY);
    }

    @Override
    public void effect(Enemy enemy) {
        enemy.takeDamage(DAMAGE);
        delete();
    }

    @Override
    public void setDamage() {
        super.damage = DAMAGE;
    }

    @Override
    public void setProjectileName() {
        super.projectileName = PROJECTILE_NAME;
    }
}
//TODO comment everything