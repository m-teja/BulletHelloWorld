package matth.dungeon;

import android.os.Handler;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square_enemy";
    public final String PROJECTILE_NAME = "square_projectile";

    private final int DESTINATION_DELAY = 1000;

    private Handler moveSprite = new Handler();
    private Handler updatePlayerPosition = new Handler();

    private float destinationX;
    private float destinationY;
    private boolean terminated = false;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        init();
    }

    public void init() {
        runUpdatePlayerPosition.run();
    }

    public void delete() {
        moveSprite.removeCallbacksAndMessages(null);
        updatePlayerPosition.removeCallbacksAndMessages(null);
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

            if (!terminated) {
                updatePlayerPosition.postDelayed(runUpdatePlayerPosition, DESTINATION_DELAY);
            }
            else {
                updatePlayerPosition.removeCallbacksAndMessages(null);
            }

        }
    };


}
