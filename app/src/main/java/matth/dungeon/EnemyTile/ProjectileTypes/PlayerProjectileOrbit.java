package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class PlayerProjectileOrbit extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_orbit";
    private final float DAMAGE = 10;
    private final int VELOCITY = 25;

    private final int RADIUS = 100;

    public PlayerProjectileOrbit(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    @Override
    public void effect(Enemy enemy) {
        enemy.takeDamage(damage);
        delete();
    }

    @Override
    public void init() {
        move.run();
    }

    @Override
    public void movePattern() {
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
