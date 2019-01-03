package matth.dungeon.Utility;

import android.content.Intent;
import android.graphics.Rect;
import android.widget.ImageView;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectile;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.GameUI.DungeonActivity;

public class PlayerUtility {

    private ArrayList<Object> enemies;
    private PlayerSprite playerSprite;

    public PlayerUtility(ArrayList<Object> enemies, PlayerSprite playerSprite) {
        this.enemies = enemies;
        this.playerSprite = playerSprite;
    }

    public static void moveImage(ImageView image, float x, float y) {
        image.setX(x);
        image.setY(y);
    }

    public void enemyOverlap(PlayerProjectile playerProjectile) {

        for (int i = 0; i < enemies.size(); i++) {
            if ( !((Enemy)enemies.get(i)).isTerminated() && checkOverlap((Enemy)enemies.get(i), playerProjectile.getProjectileImage())) {
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

    public Enemy getClosestEnemy(PlayerProjectile playerProjectile) {

        float differenceTotal = 99999;
        int enemyIndex = -1;

        for (int i = 0; i < enemies.size(); i++) {

            if ( !((Enemy)enemies.get(i)).isTerminated() ) {
                float differenceX = Math.abs(((Enemy)enemies.get(i)).getX() - playerProjectile.getX());
                float differenceY = Math.abs(((Enemy)enemies.get(i)).getY() - playerProjectile.getY());

                if (differenceX + differenceY < differenceTotal) {
                    differenceTotal = differenceX + differenceY;
                    enemyIndex = i;
                }
            }
        }
        if (enemyIndex == -1) {
            return null;
        }
        else {
            return ((Enemy) enemies.get(enemyIndex));
        }

    }

    public PlayerSprite getPlayerSprite() {
        return playerSprite;
    }
}
