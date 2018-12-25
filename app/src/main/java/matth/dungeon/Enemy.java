package matth.dungeon;


import android.widget.ImageView;

public class Enemy {

    public Utility utility;
    public int health;
    public String spriteName;
    public String projectileName;

    public Enemy( Utility utility) {
        this.utility = utility;
    }

    public ImageView spawnSprite(int x, int y, Integer width, Integer height) {

        ImageView sprite = utility.addImage("enemyLay", spriteName, x, y);

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

        ImageView projectile = utility.addImage("enemyLay", projectileName, x, y);

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

}
