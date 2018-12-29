package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleEnemy extends Enemy implements EnemyBehaviour {

    private final int STARTING_HEALTH = 50;
    private final String SPRITE_NAME = "circle_enemy";
    private final String PROJECTILE_NAME = "circle_projectile";

    private int destinationX;
    private int destinationY;


    public CircleEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        init();
    }

    public void init() {

    }

    public void delete() {

    }

}
