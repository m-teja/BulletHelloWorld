package matth.dungeon;

import android.widget.ImageView;

public class PlayerProjectile extends Projectile {

    public float damage;

    public PlayerUtility playerUtility;
    private ImageView projectileImage;

    String projectileName;

    PlayerProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility);
        super.projectileName = this.projectileName;
        this.playerUtility = playerUtility;
    }

    public ImageView spawnProjectile(int x, int y, Integer width, Integer height) {

        ImageView projectile = super.mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, super.projectileName, x, y);

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

    public ImageView getProjectileImage() {
        return projectileImage;
    }

    public float getX() {
        return projectileImage.getX();
    }

    public float getY() {
        return projectileImage.getY();
    }

    public void init() {

    }

    public void delete() {

    }

    public void effect(Enemy enemy) {

    }
}
