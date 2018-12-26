package matth.dungeon;

import android.os.Handler;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square_enemy";
    public final String PROJECTILE_NAME = "square_projectile";

    private Handler moveSprite = new Handler();
    private Handler updatePlayerPosition = new Handler();

    private float destinationX;
    private float destinationY;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
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

    private Runnable move = new Runnable() {
        @Override
        public void run() {

        }
    };

    private Runnable runUpdatePlayerPosition = new Runnable() {
        @Override
        public void run() {
            destinationX = SquareEnemy.super.enemyUtility.getPlayerSprite().getX();
            destinationY = SquareEnemy.super.enemyUtility.getPlayerSprite().getY();
        }
    };


}
