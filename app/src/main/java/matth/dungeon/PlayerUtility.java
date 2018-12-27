package matth.dungeon;

import android.graphics.Rect;
import android.widget.ImageView;

import java.util.ArrayList;

public class PlayerUtility {

    ArrayList<Object> enemies;

    PlayerUtility(ArrayList<Object> enemies) {
        this.enemies = enemies;
    }

    public static void moveImage(ImageView image, float x, float y) {
        image.setX(x);
        image.setY(y);
    }

    public ArrayList<Object> enemyOverlap(ImageView projectile) {
//        for (int i = 0; i < enemies.size(); i++) {
//            if (checkOverlap((Enemy)enemies.get(i), projectile)) {
//
//            }
//        }
    }

    private boolean checkOverlap(Enemy currentEnemy, ImageView projectile) {
        Rect projectileRect = new Rect();
        Rect currentEnemyRect = new Rect();

        projectile.getHitRect(projectileRect);
        currentEnemy.getSprite().getHitRect(currentEnemyRect);

        if (projectileRect.intersect(currentEnemyRect)) {
            return true;
        }
        else {
            return false;
        }
    }
}
