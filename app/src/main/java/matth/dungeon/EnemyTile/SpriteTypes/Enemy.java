package matth.dungeon.EnemyTile.SpriteTypes;


import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class Enemy implements EnemyBehaviour {

    final int ANIMATION_DELAY = 15;
    private final int CHECK_DELAY = 20;

    MainUtility mainUtility;
    EnemyUtility enemyUtility;
    float health;
    String spriteName;
    String projectileName;
    boolean terminated;

    private ImageView sprite;
    private Handler check = new Handler();


    public Enemy( MainUtility mainUtility, EnemyUtility enemyUtility) {
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
        this.terminated = false;
        initCheck();
    }

    public ImageView getSprite() {
        return sprite;
    }

    public ImageView spawnSprite(int x, int y, Integer width, Integer height) {

        sprite = mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, spriteName, x, y);

        if (width != null) {
            sprite.setScaleType(ImageView.ScaleType.CENTER_CROP);
            sprite.getLayoutParams().width = width;
        }

        if (height != null) {
            sprite.setScaleType(ImageView.ScaleType.CENTER_CROP);
            sprite.getLayoutParams().height = height;
        }
        return sprite;
    }

    public void initCheck() {
        Handler start = new Handler();
        start.postDelayed(new Runnable() {
            @Override
            public void run() {
                runCheck.run();
            }
        }, 500);
    }

    private Runnable runCheck = new Runnable() {
        @Override
        public void run() {

            if (MainUtility.isActive()) {
                check.postDelayed(runCheck, CHECK_DELAY);
            }
            else {
                terminated = true;

                check.removeCallbacksAndMessages(null);
            }

        }
    };

    public void init() {

    }

    public void delete() {

    }

    public boolean isTerminated() {
        return terminated;
    }

    public float getX() {
        return sprite.getX();
    }

    public void setX(float x) {
        sprite.setX(x);
    }

    public float getY() {
        return sprite.getY();
    }

    public void setY(float y) {
        sprite.setY(y);
    }

    public void takeDamage(float x) {
        health -= x;

        if (health <= 0) {
            delete();
        }
    }


}
