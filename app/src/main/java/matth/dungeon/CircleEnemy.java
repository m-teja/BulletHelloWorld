package matth.dungeon;

public class CircleEnemy extends Enemy {

    public final int STARTING_HEALTH = 150;
    public final String SPRITE_NAME = "circle";
    public final String PROJECTILE_NAME = "circle_proj";

    public CircleEnemy(Utility utility) {
        super(utility);
    }
}
