package matth.dungeon;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
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

        ImageView sprite = utility.addImage("enemyLay", "square_enemy", x, y);

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
        ConstraintLayout map = ((Activity) utility.getCon()).findViewById(R.id.enemyLay);

        ConstraintSet set = new ConstraintSet();
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        ImageView projectile = new ImageView(utility.getCon());
        projectile.setId(View.generateViewId());

        projectile.setImageResource(utility.getCon().getResources().getIdentifier(projectileName, "drawable", utility.getCon().getPackageName()));
        projectile.setLayoutParams(lp);
        projectile.setX(x);
        projectile.setY(y);

        if (width != null) {
            projectile.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectile.getLayoutParams().width = width;
        }

        if (height != null) {
            projectile.setScaleType(ImageView.ScaleType.CENTER_CROP);
            projectile.getLayoutParams().height = height;
        }

        map.addView(projectile);
        set.clone(map);
        set.connect(projectile.getId(), ConstraintSet.TOP, map.getId(), ConstraintSet.TOP, 0);
        set.applyTo(map);

        return projectile;
    }

}
