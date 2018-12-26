package matth.dungeon;

import android.widget.ImageView;

public class ImageUtility {

    private PlayerSprite playerSprite;

    ImageUtility(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public static void moveImage(ImageView image, int x, int y) {
        image.setX(x);
        image.setY(y);
    }


}
