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
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.R;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.PlayerUtility;

import static matth.dungeon.EnemyTile.EnemyEventActivity.LAYOUT_NAME;

public class PlayerSprite implements Serializable {

    private static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;
    private PlayerHealthBar playerHealthBar;

    private float maxHealth = 100;
    private float health = 100;
    private Pattern pattern;

    private boolean terminated = false;

    private MainUtility mainUtility;

    public PlayerSprite(MainUtility mainUtility, PlayerInfoPassUtility playerInfoPassUtility) {
        this.mainUtility = mainUtility;
        getPlayerInfo(playerInfoPassUtility);
        playerHealthBar = new PlayerHealthBar(mainUtility);
        playerHealthBar.initHealthBar(health);
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

    public void setHealth(float health) {
        Log.d("test", Float.toString(health));
        this.health = health;
        playerHealthBar.updateHealthBar(health);

        if (health <= 0 && !terminated) {
            terminated = true;
            lose();
        }
    }

    private void lose() {
        EnemyEventActivity.exitLose(getCon());
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
