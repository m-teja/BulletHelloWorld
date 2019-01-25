package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class PlayerProjectileClassic extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;
    private int velocity = 25;

    private int direction = -1;
    private float angle = -1;

    public PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    public PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility, int direction) {
        super(mainUtility, playerUtility);
        this.direction = direction;
    }
    public PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility, float angle) {
        super(mainUtility, playerUtility);
        this.angle = angle;
    }

    @Override
    public void init() {
        move.run();
    }

    @Override
    public void movePattern() {

        if (direction == -1 || direction == 0) {
            PlayerUtility.moveImage(getProjectileImage(), getX(), getY() - velocity);
        }
        else if (direction == 1) {
            PlayerUtility.moveImage(getProjectileImage(), getX() + velocity, getY());
        }
        else if (direction == 2) {
            PlayerUtility.moveImage(getProjectileImage(), getX(), getY() + velocity);
        }
        else if (direction == 3) {
            PlayerUtility.moveImage(getProjectileImage(), getX() - velocity, getY());
        }
    }

    @Override
    public void effect(Enemy enemy) {
        enemy.takeDamage(damage);
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

    public void setDamage(float damage) {
        super.damage = damage;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
//TODO comment everything