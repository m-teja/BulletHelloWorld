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

    public void setPlayerImage(ImageView image) {
        playerImage = image;
    }
}
