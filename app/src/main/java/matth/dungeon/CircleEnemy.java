package matth.dungeon;

public class CircleEnemy extends Enemy {

    public final int STARTING_HEALTH = 150;
    public final String SPRITE_NAME = "circle";
    public final String PROJECTILE_NAME = "circle_projectile";

    public CircleEnemy(Utility utility) {
        super(utility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
    }
}
