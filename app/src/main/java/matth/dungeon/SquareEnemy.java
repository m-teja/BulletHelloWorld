package matth.dungeon;

import android.os.Handler;
import android.util.Log;

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
    private int velocity = 5;
    private int velocityX;
    private int velocityY;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
    }

    public void init() {
        runUpdatePlayerPosition.run();
        move.run();
    }

    public void delete() {
        moveSprite.removeCallbacksAndMessages(null);
        updatePlayerPosition.removeCallbacksAndMessages(null);
    }

    private void calcVelocity() {
        if (destinationX >= SquareEnemy.super.getX()) {
            velocityX = velocity;
        }
        else {
            velocityX = -velocity;
        }

        if (destinationY >= SquareEnemy.super.getY()) {
            velocityY = velocity;
        }
        else {
            velocityY = -velocity;
        }
    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {

            EnemyUtility.moveImage(SquareEnemy.super.getSprite(), SquareEnemy.super.getX() + velocityX, SquareEnemy.super.getY() + velocityY);

            if (!terminated) {
                moveSprite.postDelayed(move, SquareEnemy.super.ANIMATION_DELAY);
            }
            else {
                moveSprite.removeCallbacksAndMessages(null);
            }
        }
    };

    private Runnable runUpdatePlayerPosition = new Runnable() {
        @Override
        public void run() {
            destinationX = SquareEnemy.super.enemyUtility.getPlayerSprite().getX();
            destinationY = SquareEnemy.super.enemyUtility.getPlayerSprite().getY();

            calcVelocity();

            if (!terminated) {
                updatePlayerPosition.postDelayed(runUpdatePlayerPosition, DESTINATION_DELAY);
            }
            else {
                updatePlayerPosition.removeCallbacksAndMessages(null);
            }

        }
    };


}
