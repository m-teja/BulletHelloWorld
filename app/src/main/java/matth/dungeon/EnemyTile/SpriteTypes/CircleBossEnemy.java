package matth.dungeon.EnemyTile.SpriteTypes;

import android.os.Handler;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleBossEnemy extends CircleEnemy {

    private final float STARTING_HEALTH = 400;

    private boolean spawned = false;
    private boolean disappeared = false;

    public CircleBossEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
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
    public void setDamage() {
        super.damage = 1;
    }

    @Override
    public void setHealth() {
        super.health = STARTING_HEALTH;
    }

    @Override
    public void takeDamage(float x) {
        super.takeDamage(x, STARTING_HEALTH);

        if (!disappeared && health/STARTING_HEALTH < 0.75) {
            disappear();
        }

        if (!spawned && health/STARTING_HEALTH < 0.5) {
            reappear();
            spawnMore();
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
                    enemyUtility.addEnemy(new CircleEnemy(mainUtility, enemyUtility), getX(), getY() + (float)(Math.random() * mainUtility.getScreenHeight()/10), null, null);
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

    private void disappear() {
        disappeared = true;
        getSprite().setAlpha(0.0f);
    }

    private void reappear() {
        getSprite().setAlpha(1.0f);
    }
}
