package matth.dungeon;

import android.graphics.Rect;
import android.widget.ImageView;

import java.util.ArrayList;

public class PlayerUtility {

    private ArrayList<Object> enemies;
    private PlayerSprite playerSprite;

    PlayerUtility(ArrayList<Object> enemies, PlayerSprite playerSprite) {
        this.enemies = enemies;
        this.playerSprite = playerSprite;
    }

    public static void moveImage(ImageView image, float x, float y) {
        image.setX(x);
        image.setY(y);
    }

    public void enemyOverlap(PlayerProjectile playerProjectile) {
        for (int i = 0; i < enemies.size(); i++) {
            if (checkOverlap((Enemy)enemies.get(i), playerProjectile.getProjectileImage())) {

                playerProjectile.effect((Enemy)enemies.get(i));
            }
        }
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

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }
}
