package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.BouncePattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectile;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileHoming;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileOrbit;
import matth.dungeon.R;
import matth.dungeon.Utility.PlayerInfoPassUtility;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

import static matth.dungeon.EnemyTile.EnemyEventActivity.LAYOUT_NAME;
import static matth.dungeon.EnemyTile.EnemyEventActivity.exitLose;

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
//        ClassicPattern classicPattern = new ClassicPattern(mainUtility, playerUtility, 3);
//        classicPattern.init();

//        BouncePattern bouncePattern = new BouncePattern(mainUtility, playerUtility);
//        bouncePattern.init();

        PlayerProjectileHoming test = new PlayerProjectileHoming(mainUtility, playerUtility);
        test.spawnProjectile(1000, 1000, null, null);
        test.init();
        // update this to check for which pattern
    }

    public void setPlayerImage() {
       playerImage = mainUtility.addImage(LAYOUT_NAME, PlayerSprite.IMAGE_NAME, mainUtility.getScreenWidth()/2, (float)(mainUtility.getScreenHeight()/1.5));
    }

    private void initHealthBar() {
        ImageView healthBar = ((Activity)mainUtility.getCon()).findViewById(R.id.healthBar);
        healthBar.setX(0);
        healthBar.setY(0);
        healthBar.getLayoutParams().width = mainUtility.getScreenWidth();
        healthBar.getLayoutParams().height = 0;
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
        healthBar.getLayoutParams().height = mainUtility.getScreenHeight() - (int)(mainUtility.getScreenHeight() * (health/maxHealth));
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
