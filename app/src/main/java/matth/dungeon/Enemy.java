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
        ConstraintLayout map = ((Activity) utility.getCon()).findViewById(R.id.enemyLay);

        map.getLayoutParams().height = utility.getScreenHeight();
        map.getLayoutParams().width = utility.getScreenWidth();
        map.setX(0);
        map.setY(0);

        ConstraintSet set = new ConstraintSet();

        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        ImageView sprite = new ImageView(utility.getCon());
        sprite.setId(View.generateViewId());

        sprite.setImageResource(utility.getCon().getResources().getIdentifier(spriteName, "drawable", utility.getCon().getPackageName()));
        sprite.setLayoutParams(lp);
        sprite.setX(x);
        sprite.setY(y);



        if (width != null) {
            sprite.setScaleType(ImageView.ScaleType.CENTER_CROP);
            sprite.getLayoutParams().width = width;
        }

        if (height != null) {
            sprite.setScaleType(ImageView.ScaleType.CENTER_CROP);
            sprite.getLayoutParams().height = height;
        }

        map.addView(sprite);
        set.clone(map);
        set.connect(sprite.getId(), ConstraintSet.TOP, map.getId(), ConstraintSet.TOP, 0);
        set.applyTo(map);

        return sprite;

    }

//    public ImageView spawnProjectile(int x, int y) {
//
//    }

}
