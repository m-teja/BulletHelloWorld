package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;

public abstract class Projectile implements ProjectileBehaviour {

    private final int CHECK_DELAY = 20;
    final int ANIMATION_DELAY = 15;

    float damage;
    boolean terminated;

    MainUtility mainUtility;
    String projectileName;
    ImageView projectileImage;

    private Handler check = new Handler();

    Projectile(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    public abstract void init();
    public abstract void delete();
    public abstract void movePattern();
    public abstract void effect(Enemy enemy);

    public void outOfBounds() {
        if (projectileImage.getY() < 0) {
            delete();
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            delete();
        }
        if (projectileImage.getX() < 0) {
            delete();
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            delete();
        }
    }

    public void spawnProjectile(float x, float y, Integer width, Integer height) {

        projectileImage = mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, projectileName, x, y);

        if (width != null) {
            projectileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectileImage.getLayoutParams().width = width;
        }

        if (height != null) {
            projectileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectileImage.getLayoutParams().height = height;
        }
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
                delete();
                //terminated = true;
                check.removeCallbacksAndMessages(null);
            }

        }
    };

    public ImageView getProjectileImage() {
        return projectileImage;
    }

    public void setX(float x) {
        projectileImage.setX(x);
    }

    public void setY(float y) {
        projectileImage.setY(y);
    }

    public float getX() {
        return projectileImage.getX();
    }

    public float getY() {
        return projectileImage.getY();
    }
}
