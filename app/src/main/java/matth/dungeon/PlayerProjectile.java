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

    public void spawnProjectile(float x, float y, Integer width, Integer height) {

        projectileImage = super.mainUtility.addImage(EnemyEventActivity.LAYOUT_NAME, projectileName, x, y);

        if (width != null) {
            projectileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectileImage.getLayoutParams().width = width;
        }

        if (height != null) {
            projectileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectileImage.getLayoutParams().height = height;
        }
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
