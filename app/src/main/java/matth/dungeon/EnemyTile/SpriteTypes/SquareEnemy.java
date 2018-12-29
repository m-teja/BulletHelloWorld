package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    private final float STARTING_HEALTH = 30;
    private final int DAMAGE = 10;
    private final String SPRITE_NAME = "square_enemy";
    private final String PROJECTILE_NAME = "square_projectile";
    private final int DESTINATION_DELAY = 800;
    private final int VELOCITY = 15;

    private Handler moveSprite = new Handler();


    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        super.velocity = VELOCITY;
        super.destinationDelay = DESTINATION_DELAY;
    }

    public void init() {
        runUpdatePlayerPosition.run();
        move.run();
    }

    public void delete() {
        Log.d("test", "square terminated");
        terminated = true;
        super.delete();
        moveSprite.removeCallbacksAndMessages(null);


        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getSprite());
    }

    private void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
    }

    private Runnable move = new Runnable() {
        @Override
        public void run() {

            EnemyUtility.moveImage(SquareEnemy.super.getSprite(), SquareEnemy.super.getX() + velocityX, SquareEnemy.super.getY() + velocityY);

            if (enemyUtility.checkPlayerOverlap(SquareEnemy.super.getSprite())) {
                effect();
                delete();
            }

            if (!SquareEnemy.super.terminated) {
                moveSprite.postDelayed(move, SquareEnemy.super.ANIMATION_DELAY);
            }
        }
    };

}
