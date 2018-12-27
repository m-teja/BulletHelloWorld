package matth.dungeon;

import android.content.Context;
import android.widget.ImageView;

public class PlayerSprite {

    public static String IMAGE_NAME = "player_sprite";

    private ImageView playerImage;

    private Context con;

    PlayerSprite(Context con) {
        this.con = con;
    }

    public void initProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        ClassicPattern classicPattern = new ClassicPattern(mainUtility, playerUtility);
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
}
