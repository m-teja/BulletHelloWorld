package matth.dungeon;

import android.content.Context;
import android.widget.ImageView;

public class PlayerSprite {

    private static String IMAGE_NAME = "player_sprite";

    public ImageView playerImage;

    private Context con;

    PlayerSprite(Context con) {
        this.con = con;
    }
}
