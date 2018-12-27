package matth.dungeon;

import android.os.Handler;
import android.util.Log;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    private final int STARTING_HEALTH = 100;
    private final String SPRITE_NAME = "square_enemy";
    private final String PROJECTILE_NAME = "square_projectile";

    private final int DESTINATION_DELAY = 1000;

    private Handler moveSprite = new Handler();
    private Handler updatePlayerPosition = new Handler();

    private float destinationX;
    private float destinationY;
    private boolean terminated = false;
    private int velocity = 10;
    private float velocityX;
    private float velocityY;

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
        moveSprite = null;
        updatePlayerPosition = null;
        super.getSprite().setImageBitmap(null);
    }

    private void calcVelocity() {

        float differenceX = destinationX - super.getX();
        float differenceY = destinationY - super.getY();

        if (differenceX != 0 && differenceY != 0) {
            float differenceXY = (float)Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

            float cosAngle = (float)Math.acos(differenceY/differenceXY);
            float sinAngle = (float)Math.asin(differenceX/differenceXY);

            velocityY = (float)(Math.cos(cosAngle) * velocity);
            velocityX = (float)(Math.sin(sinAngle) * velocity);
        }
        else if (differenceX == 0) {
            velocityX = 0;
            if (destinationY > super.getY()) {
                velocityY = velocity;
            }
            else {
                velocityY = -velocity;
            }
        }
        else {
            velocityY = 0;
            if (destinationX > super.getX()) {
                velocityX = velocity;
            }
            else {
                velocityX = -velocity;
            }
        }

    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {

            EnemyUtility.moveImage(SquareEnemy.super.getSprite(), SquareEnemy.super.getX() + velocityX, SquareEnemy.super.getY() + velocityY);

            if (enemyUtility.checkPlayerOverlap(SquareEnemy.super.getSprite())) {
                terminated = true;
            }

            if (!terminated) {
                moveSprite.postDelayed(move, SquareEnemy.super.ANIMATION_DELAY);
            }
            else {
                delete();
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
                delete();
            }

        }
    };


}
