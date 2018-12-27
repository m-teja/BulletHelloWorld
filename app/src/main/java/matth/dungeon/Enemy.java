package matth.dungeon;


import android.widget.ImageView;

public class Enemy {

    public final int ANIMATION_DELAY = 15;

    public MainUtility mainUtility;
    public EnemyUtility enemyUtility;
    int health;
    String spriteName;
    String projectileName;

    private ImageView sprite;


    public Enemy( MainUtility mainUtility, EnemyUtility enemyUtility) {
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
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

    public void init() {

    }

    public void delete() {

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
}
