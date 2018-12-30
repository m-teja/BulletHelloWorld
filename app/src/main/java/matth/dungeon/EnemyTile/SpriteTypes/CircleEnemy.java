package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.EnemyTile.ProjectileTypes.CirclePattern;
import matth.dungeon.R;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleEnemy extends Enemy implements EnemyBehaviour {

    private final int STARTING_HEALTH = 50;
    private final String SPRITE_NAME = "circle_enemy";
    private final String PROJECTILE_NAME = "circle_projectile";
    private final int VELOCITY = 8;
    private final int DESTINATION_DELAY = 800;
    private final int DAMAGE = 2;

    private CirclePattern circlePattern;

    private Handler updateDestination = new Handler();
    private Handler moveSprite = new Handler();

    public CircleEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;
        super.projectileName = PROJECTILE_NAME;
        super.velocity = VELOCITY;
        super.destinationY = 200;
    }

    public void init() {
        runUpdateDestination.run();
        move.run();
        circlePattern = new CirclePattern(mainUtility, enemyUtility);
        circlePattern.init();
    }

    public void delete() {
        Log.d("test", "circle terminated");
        terminated = true;
        updateDestination.removeCallbacksAndMessages(null);
        circlePattern.stop();

        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(super.getSprite());
    }

    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
    }

    @Override
    void calcVelocity() {

        float differenceX = destinationX - getX();

        if (differenceX > 0) {
            velocityX = velocity;
        }
        else {
            velocityX = -velocity;
        }
    }

    private Runnable runUpdateDestination = new Runnable() {
        @Override
        public void run() {
            destinationX = enemyUtility.getPlayerSprite().getX();

            calcVelocity();

            if (!terminated) {
                updateDestination.postDelayed(runUpdateDestination, DESTINATION_DELAY);

            }
        }
    };

    private Runnable move = new Runnable() {
       @Override
       public void run() {
           EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY());

           if (enemyUtility.checkPlayerOverlap(getSprite())) {
               effect();
               delete();
           }

           if (!terminated) {
               moveSprite.postDelayed(move, ANIMATION_DELAY);
           }

       }
    };

}
