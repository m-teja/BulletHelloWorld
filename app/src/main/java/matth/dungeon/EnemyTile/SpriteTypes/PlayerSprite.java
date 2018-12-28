package matth.dungeon.EnemyTile.SpriteTypes;

import android.content.Context;
import android.widget.ImageView;

import matth.dungeon.EnemyTile.ProjectileTypes.ClassicPattern;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public class PlayerSprite {

    public static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;
    private int health = 100;
    //change later to depend on situation

    private Context con;

    public PlayerSprite(Context con) {
        this.con = con;
    }

    public void initProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        ClassicPattern classicPattern = new ClassicPattern(mainUtility, playerUtility);
        classicPattern.init();
        // update this to check for which pattern
    }

    public void setPlayerImage(ImageView image) {
        playerImage = image;
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
        this.health = health;
    }
}
