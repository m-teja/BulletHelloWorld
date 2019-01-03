package matth.dungeon.EnemyTile.ProjectileTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.R;
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
    Handler moveProjectile = new Handler();

    Projectile(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
        setDamage();
        setProjectileName();
    }

    public abstract void init();
    public abstract void movePattern();
    public abstract void setDamage();
    public abstract void setProjectileName();

    @CallSuper
    public void delete() {
        Log.d("test", "projectile terminated");
        terminated = true;
        moveProjectile.removeCallbacksAndMessages(null);

        deleteImage();
    }

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

    public void spawnProjectile(float x, float y, boolean invert) {
        projectileImage = mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, projectileName, x, y);

        if (invert) {
            projectileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            int temp = getWidth();
            projectileImage.getLayoutParams().width = getHeight();
            projectileImage.getLayoutParams().height = temp;
        }
    }

    private void deleteImage() {
        ConstraintLayout cl = ((Activity)mainUtility.getCon()).findViewById(R.id.enemyLay);
        cl.removeView(projectileImage);
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
                check.removeCallbacksAndMessages(null);
            }
        }
    };

    public ImageView getProjectileImage() {
        return projectileImage;
    }

    public float getX() {
        return projectileImage.getX();
    }

    public float getY() {
        return projectileImage.getY();
    }

    public void setX(int x) {
        projectileImage.setX(x);
    }

    public void setY(int y) {
        projectileImage.setY(y);
    }

    public int getWidth() {
        projectileImage.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return projectileImage.getMeasuredWidth();
    }

    public int getHeight() {
        projectileImage.measure(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        return projectileImage.getMeasuredHeight();
    }
}
