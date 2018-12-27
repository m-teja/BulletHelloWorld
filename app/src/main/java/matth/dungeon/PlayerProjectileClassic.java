package matth.dungeon;

public class PlayerProjectileClassic extends PlayerProjectile implements  ProjectileBehaviour {

    private final float DAMAGE = 10;

    PlayerProjectileClassic(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
        super.damage = DAMAGE;
    }

    public void init() {

    }

    public void delete() {

    }
}
