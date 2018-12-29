package matth.dungeon.EnemyTile.SpriteTypes;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.ProjectileTypes.ClassicPattern;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

import static matth.dungeon.EnemyTile.EnemyEventActivity.LAYOUT_NAME;

public class PlayerSprite {

    public static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;

    private int health = 100;
    //change later to depend on situation

    private MainUtility mainUtility;

    public PlayerSprite(MainUtility mainUtility) {
        this.mainUtility = mainUtility;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        Log.d("test", Integer.toString(health));
        this.health = health;
        updateHealthBar();
    }

    private void updateHealthBar() {

    }
}
