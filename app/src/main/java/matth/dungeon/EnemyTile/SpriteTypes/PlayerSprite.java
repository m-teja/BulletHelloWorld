package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.HomingPattern;
import matth.dungeon.R;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.PlayerUtility;

import static matth.dungeon.EnemyTile.EnemyEventActivity.LAYOUT_NAME;

public class PlayerSprite implements Serializable {

    public static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;

    private float maxHealth = 100;
    private float health = 100;

    private boolean terminated = false;

    private MainUtility mainUtility;

    public PlayerSprite(MainUtility mainUtility, PlayerInfoPassUtility playerInfoPassUtility) {
        this.mainUtility = mainUtility;
        getPlayerInfo(playerInfoPassUtility);
        initHealthBar();
        setPlayerImage();
    }

    private void getPlayerInfo(PlayerInfoPassUtility playerInfoPassUtility) {
        maxHealth = playerInfoPassUtility.getMaxHealth();
        health = playerInfoPassUtility.getHealth();

    }

    public void initProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        ClassicPattern classicPattern = new ClassicPattern(mainUtility, playerUtility, 2);
        classicPattern.init();

//        BouncePattern bouncePattern = new BouncePattern(mainUtility, playerUtility);
//        bouncePattern.init();

//        HomingPattern homingPattern = new HomingPattern(mainUtility, playerUtility);
//        homingPattern.init();
        // update this to check for which pattern
    }

    private void setPlayerImage() {
       playerImage = mainUtility.addImage(LAYOUT_NAME, PlayerSprite.IMAGE_NAME, mainUtility.getScreenWidth()/2, (float)(mainUtility.getScreenHeight()/1.5));
    }

    private void initHealthBar() {
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

        updateHealthBar();
    }

    public void setHealth(float health) {
        Log.d("test", Float.toString(health));
        this.health = health;
        updateHealthBar();
        if (health <= 0 && !terminated) {
            terminated = true;
            lose();
        }
    }

    private void lose() {
        EnemyEventActivity.exitLose(getCon());
    }

    private void updateHealthBar() {
        ImageView healthBar = ((Activity)mainUtility.getCon()).findViewById(R.id.healthBar);

        float healthBarHeight = health % 100;
        if (healthBarHeight == 0.0) {
            healthBarHeight = 100;
        }

        healthBar.getLayoutParams().height = (int)(mainUtility.getScreenHeight() * (healthBarHeight/100));
        colourHealthBar();
    }

    private void colourHealthBar() {
        ImageView healthBar = ((Activity) mainUtility.getCon()).findViewById(R.id.healthBar);
        ImageView backgroundHealthBar = ((Activity) mainUtility.getCon()).findViewById(R.id.backgroundHealthBar);

        if (health <= 100) {
            backgroundHealthBar.setColorFilter(getCon().getResources().getColor(R.color.black));
            healthBar.setColorFilter(getCon().getResources().getColor(R.color.gray));
        }
        else if (health > 100 && health <= 200) {
            backgroundHealthBar.setColorFilter(getCon().getResources().getColor(R.color.gray));
            healthBar.setColorFilter(getCon().getResources().getColor(R.color.red));
        }
        else if (health > 200 && health <= 300) {
            backgroundHealthBar.setColorFilter(getCon().getResources().getColor(R.color.red));
            healthBar.setColorFilter(getCon().getResources().getColor(R.color.orange));
        }
        else if (health > 300 && health <= 400) {
            backgroundHealthBar.setColorFilter(getCon().getResources().getColor(R.color.orange));
            healthBar.setColorFilter(getCon().getResources().getColor(R.color.green));
        }
        else if (health > 400 && health <= 500) {
            backgroundHealthBar.setColorFilter(getCon().getResources().getColor(R.color.green));
            healthBar.setColorFilter(getCon().getResources().getColor(R.color.purple));
        }
    }

    public ImageView getPlayerImage() {
        return playerImage;
    }

    public float getX() {
        return playerImage.getX();
    }

    public float getY() {
        return playerImage.getY();
    }

    public void setX(float x) {
        playerImage.setX(x);
    }

    public void setY(float y) {
        playerImage.setY(y);
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public Context getCon() {
        return mainUtility.getCon();
    }


}
