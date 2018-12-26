package matth.dungeon;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square_enemy";
    public final String PROJECTILE_NAME = "square_projectile";

    public SquareEnemy(MainUtility utility) {
        super(utility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;

    }

    public void init() {

    }

    public void delete() {

    }


}
