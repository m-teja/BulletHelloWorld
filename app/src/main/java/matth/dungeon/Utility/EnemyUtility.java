package matth.dungeon.Utility;

import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.GameUI.DungeonActivity;

public class EnemyUtility {

    private PlayerSprite playerSprite;
    private ArrayList<Object> enemies;

    public EnemyUtility(PlayerSprite playerSprite) {
        this.playerSprite = playerSprite;
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

    public void checkDone() {
        for (int i = 0; i < enemies.size(); i++) {
            if ( !((Enemy)enemies.get(i)).isTerminated() ) {
                return;
            }
        }

        EnemyEventActivity.exitWin(playerSprite.getCon());
    }

    public void setEnemies(ArrayList<Object> enemies) {
        this.enemies = enemies;
    }

}
