package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleProjectile extends EnemyProjectile {

    private final String PROJECTILE_NAME = "circle_projectile";
    private final float DAMAGE = 10;
    private final int VELOCITY = 25;

    public CircleProjectile(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void init() {
        move.run();
    }

    @Override
    public void movePattern() {
        EnemyUtility.moveImage(getProjectileImage(), getX(), getY() + VELOCITY);
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
        deleteAll();
    }

    @Override
    public void setDamage() {
        super.damage = DAMAGE;
    }

    @Override
    public void setProjectileName() {
        super.projectileName = PROJECTILE_NAME;
    }

    @Override
    public void delete() {

    }
}
