package matth.dungeon;


import android.widget.ImageView;

public class Enemy {

    private MainUtility mainUtility;
    private EnemyUtility enemyUtility;
    int health;
    String spriteName;
    String projectileName;

    private ImageView sprite;
    private int x;
    private int y;


    public Enemy( MainUtility mainUtility, EnemyUtility enemyUtility) {
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public ImageView spawnSprite(int x, int y, Integer width, Integer height) {

        this.x = x;
        this.y = y;
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

    public ImageView spawnProjectile(int x, int y, Integer width, Integer height) {

        ImageView projectile = mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, projectileName, x, y);

        if (width != null) {
            projectile.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectile.getLayoutParams().width = width;
        }

        if (height != null) {
            projectile.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectile.getLayoutParams().height = height;
        }

        return projectile;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        sprite.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        sprite.setY(y);
    }
}
