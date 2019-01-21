package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.widget.ImageView;

import matth.dungeon.R;
import matth.dungeon.Utility.MainUtility;

public class PlayerHealthBar {

    private MainUtility mainUtility;

    PlayerHealthBar(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
    }

    void initHealthBar(float health) {
        ImageView healthBar = ((Activity)mainUtility.getCon()).findViewById(R.id.healthBar);
        healthBar.setX(0);
        healthBar.setY(0);
        healthBar.getLayoutParams().width = mainUtility.getScreenWidth();
        healthBar.getLayoutParams().height = 0;

        ImageView backgroundHealthBar = ((Activity) mainUtility.getCon()).findViewById(R.id.backgroundHealthBar);
        backgroundHealthBar.setX(0);
        backgroundHealthBar.setY(0);
        backgroundHealthBar.getLayoutParams().width = mainUtility.getScreenWidth();
        backgroundHealthBar.getLayoutParams().height = mainUtility.getScreenHeight();

        updateHealthBar(health);
    }

    void updateHealthBar(float health) {
        ImageView healthBar = ((Activity)mainUtility.getCon()).findViewById(R.id.healthBar);

        float healthBarHeight = health % 100;
        if (healthBarHeight == 0.0) {
            healthBarHeight = 100;
        }
        healthBarHeight = 100 - healthBarHeight;

        healthBar.getLayoutParams().height = (int)(mainUtility.getScreenHeight() * (healthBarHeight/100));

    }

}
//TODO change to just gray and black