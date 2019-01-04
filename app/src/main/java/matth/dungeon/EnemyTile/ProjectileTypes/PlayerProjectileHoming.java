package matth.dungeon.EnemyTile.ProjectileTypes;

import android.util.Log;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.GameUI.Player;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class PlayerProjectileHoming extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_homing";
    private final float DAMAGE = 10;
    private final int VELOCITY = 10;

    private Enemy enemy = null;

    public PlayerProjectileHoming(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    @Override
    public void effect(Enemy enemy) {
        enemy.takeDamage(damage);
        delete();
    }

    @Override
    public void init() {
        calcNearEnemy();
        move.run();
    }

    @Override
    public void movePattern() {
        if (!terminated && !enemy.isTerminated()) {
            float vel[] = calcVelocity(enemy.getX(), enemy.getY(), VELOCITY);
            PlayerUtility.moveImage(getProjectileImage(), getX() + vel[0], getY() + vel[1]);
        }
        else if (!terminated){
            calcNearEnemy();
        }

    }

    @Override
    public void setDamage() {
        super.damage = DAMAGE;
    }

    @Override
    public void setProjectileName() {
        super.projectileName = PROJECTILE_NAME;
    }
    private void calcNearEnemy() {
        enemy = playerUtility.getClosestEnemy(this);
        if (enemy == null) {
            terminated = true;
            delete();
        }
    }

}
