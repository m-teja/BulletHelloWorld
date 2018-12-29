package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleEnemy extends Enemy implements EnemyBehaviour {

    private final int STARTING_HEALTH = 50;
    private final String SPRITE_NAME = "circle_enemy";
    private final String PROJECTILE_NAME = "circle_projectile";
    private final int VELOCITY = 8;
    private final int DESTINATION_DELAY = 20;

    public CircleEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        super.velocity = VELOCITY;
        super.destinationDelay = DESTINATION_DELAY;
        init();

    }

    public void init() {

    }

    public void delete() {
        super.delete();
    }

   // private Runnable

}
