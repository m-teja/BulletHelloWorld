package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.R;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

import static matth.dungeon.EnemyTile.EnemyEventActivity.LAYOUT_NAME;

public class PlayerSprite {

    public static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;

    private float maxHealth = 100;
    private float health = 100;
    //change later to depend on situation

    private MainUtility mainUtility;

    public PlayerSprite(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
        initHealthBar();
        setPlayerImage();
    }

    public void initProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        ClassicPattern classicPattern = new ClassicPattern(mainUtility, playerUtility);
        classicPattern.init();
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
    }

    public void setHealth(float health) {
        Log.d("test", Float.toString(health));
        this.health = health;
        updateHealthBar();
    }

    private void updateHealthBar() {
        ImageView healthBar = ((Activity)mainUtility.getCon()).findViewById(R.id.healthBar);
        healthBar.getLayoutParams().height = mainUtility.getScreenHeight() - (int)(mainUtility.getScreenHeight() * ((float)health/maxHealth));
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


}
