package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import matth.dungeon.R;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class SquareBossEnemy extends SquareEnemy {

    private final float STARTING_HEALTH = 500;

    private boolean spawned = false;
    private boolean speed = false;

    public SquareBossEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void init() {
        super.init();
        initBossHealth();
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void setDamage() {
        super.damage = 1;
    }

    @Override
    public void setHealth() {
        super.health = 500;
    }

    @Override
    public void setVelocity() {
        super.velocity = 7;
    }

    @Override
    public void takeDamage(float x) {
        super.takeDamage(x, STARTING_HEALTH);

        if (health/STARTING_HEALTH <= 0.5 && !spawned) {
            spawnMore();
        }
        if (health/STARTING_HEALTH <= 0.75 && !speed) {
            speed = true;
            super.velocity *= 3;
            super.destinationUpdateDelay = super.destinationUpdateDelay/2;
        }
    }

    @Override
    public void spawnSprite(float x, float y, Integer width, Integer height) {
        super.spawnSprite(x, y, mainUtility.getScreenWidth()/6, mainUtility.getScreenWidth()/6);
    }

    private void spawnMore() {
        stopMoving();
        spawned = true;
        final Handler spawnMoreDelay = new Handler();
        spawnMoreDelay.postDelayed(new Runnable() {
            int counter = 0;

            @Override
            public void run() {

                if (!terminated) {
                    enemyUtility.addEnemy(new SquareEnemy(mainUtility, enemyUtility), getX() - getSprite().getLayoutParams().width, getY(), null, null);
                    enemyUtility.addEnemy(new SquareEnemy(mainUtility, enemyUtility), getX() + 2*getSprite().getLayoutParams().width, getY(), null, null);
                }
                if (counter < 2 && !terminated) {
                    counter++;
                    spawnMoreDelay.postDelayed(this, 1000);
                }
                else {
                    startMoving();
                }
            }
        }, 1000);

    }

}
