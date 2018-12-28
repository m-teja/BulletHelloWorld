package matth.dungeon.Utility;

import android.graphics.Rect;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;

public class EnemyUtility {

    private PlayerSprite playerSprite;
    private ArrayList<Object> enemies;

    public EnemyUtility(PlayerSprite playerSprite, ArrayList<Object> enemies) {
        this.playerSprite = playerSprite;
        this.enemies = enemies;
    }

    public static void moveImage(ImageView image, float x, float y) {
        image.setX(x);
        image.setY(y);
    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }

    public boolean checkPlayerOverlap(ImageView image) {
        Rect playerRect = new Rect();
        Rect imageRect = new Rect();

        playerSprite.getPlayerImage().getHitRect(playerRect);
        image.getHitRect(imageRect);

        if (playerRect.intersect(imageRect)) {
            return true;
        }
        else {
            return false;
        }
    }


}
