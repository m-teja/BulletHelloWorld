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
        super.damage = DAMAGE;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {
        move.run();
    }

    public void delete() {
        Log.d("test", "projectile terminated");
        terminated = true;
        super.delete();

        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getProjectileImage());
    }

    public void effect(Enemy enemy) {
        enemy.takeDamage(DAMAGE);
        delete();
    }

    @Override
    public void movePattern() {
        PlayerUtility.moveImage(getProjectileImage(), getX(), getY() - VELOCITY);
    }
}
//TODO comment everything