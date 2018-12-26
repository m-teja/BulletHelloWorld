package matth.dungeon;

import android.os.Handler;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square_enemy";
    public final String PROJECTILE_NAME = "square_projectile";

    private Handler moveSprite;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        init();
    }

    public void init() {
        moveSprite = new Handler();
        move.run();
    }

    public void delete() {

    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {

        }
    };


}
