package matth.dungeon;

public class PlayerProjectileClassic extends PlayerProjectile implements  ProjectileBehaviour {

    private final String PROJECTILE_NAME = "projectile_classic";
    private final float DAMAGE = 10;

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
}
