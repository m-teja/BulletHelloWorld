package matth.dungeon.EnemyTile.ProjectileTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.R;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public abstract class Projectile implements ProjectileBehaviour {

    private final int CHECK_DELAY = 20;
    final int ANIMATION_DELAY = 15;

    float damage;
    boolean terminated;

    MainUtility mainUtility;
    private EnemyUtility enemyUtility = null;
    private PlayerUtility playerUtility = null;
    String projectileName;
    ImageView projectileImage;

    private float playerX = 0;
    private float playerY = 0;

    private Handler check = new Handler();
    private Handler updatePlayer = new Handler();
    Handler moveProjectile = new Handler();

    Projectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
        setDamage();
        setProjectileName();
    }

    Projectile(MainUtility mainUtility, EnemyUtility enemyUtility) {
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
        setDamage();
        setProjectileName();
    }


    public abstract void init();
    public abstract void movePattern();
    public abstract void setDamage();
    public abstract void setProjectileName();
    public abstract void delete();

    final void deleteAll() {
        delete();
        Log.d("test", "projectile terminated");
        terminated = true;
        moveProjectile.removeCallbacksAndMessages(null);
        updatePlayer.removeCallbacksAndMessages(null);

        deleteImage();
    }

    public void outOfBounds() {
        if (projectileImage.getY() < 200) {
            deleteAll();
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            deleteAll();
        }
        if (projectileImage.getX() < 0) {
            deleteAll();
        }
        if (projectileImage.getY() > mainUtility.getScreenHeight()) {
            deleteAll();
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

    float[] calcVelocity(float destinationX, float destinationY, int velocity) {

        float velocityX;
        float velocityY;

        float differenceX = destinationX - getX();
        float differenceY = destinationY - getY();

        if (differenceX != 0 && differenceY != 0) {
            float differenceXY = (float)Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

            float cosAngle = (float)Math.acos(differenceY/differenceXY);
            float sinAngle = (float)Math.asin(differenceX/differenceXY);

            velocityY = (float)(Math.cos(cosAngle) * velocity);
            velocityX = (float)(Math.sin(sinAngle) * velocity);
        }
        else if (differenceX == 0) {
            velocityX = 0;
            if (destinationY > getY()) {
                velocityY = velocity;
            }
            else {
                velocityY = -velocity;
            }
        }
        else  {
            velocityY = 0;
            if (destinationX > getX()) {
                velocityX = velocity;
            }
            else {
                velocityX = -velocity;
            }
        }
        float vel[] = { velocityX, velocityY};
        return vel;

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

    Runnable runUpdatePlayer = new Runnable() {
        @Override
        public void run() {
            if (enemyUtility != null) {
                playerX = enemyUtility.getPlayerSprite().getX();
                playerY = enemyUtility.getPlayerSprite().getY();
            }
            else if (playerUtility != null) {
                playerX = playerUtility.getPlayerSprite().getX();
                playerY = playerUtility.getPlayerSprite().getY();
            }

            if (!terminated) {
                updatePlayer.postDelayed(runUpdatePlayer, CHECK_DELAY);
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

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }
}
