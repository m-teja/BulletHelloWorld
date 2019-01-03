package matth.dungeon.EnemyTile.ProjectileTypes;

import android.util.Log;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class PlayerProjectileOrbit extends PlayerProjectile {

    private final String PROJECTILE_NAME = "projectile_orbit";
    private final float DAMAGE = 10;
    private final int VELOCITY = 25;

    private float currentPlayerProjectileDifferenceX;
    private float currentPlayerProjectileDifferenceY;

    private float radius = 0;

    public PlayerProjectileOrbit(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility, playerUtility);
    }

    @Override
    public void effect(Enemy enemy) {
        enemy.takeDamage(damage);
        delete();
    }

    @Override
    public void init() {
        move.run();
        calcRadius();
    }

    @Override
    public void movePattern() {

        //TODO make this work

        float playerCenterX = playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getLayoutParams().width/2;
        float playerCenterY = playerUtility.getPlayerSprite().getY() + playerUtility.getPlayerSprite().getPlayerImage().getLayoutParams().height/2;

        PlayerUtility.moveImage(getProjectileImage(), currentPlayerProjectileDifferenceX + playerCenterX - getProjectileImage().getLayoutParams().width/2, currentPlayerProjectileDifferenceY + playerCenterY - getProjectileImage().getLayoutParams().height);

    }

    @Override
    public void setDamage() {
        super.damage = DAMAGE;
    }

    @Override
    public void setProjectileName() {
        super.projectileName = PROJECTILE_NAME;
    }

    private void calcRadius() {

        float playerCenterX = playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getLayoutParams().width/2;
        float playerCenterY = playerUtility.getPlayerSprite().getY() + playerUtility.getPlayerSprite().getPlayerImage().getLayoutParams().height/2;

        float projectileCenterX = getX() + getProjectileImage().getLayoutParams().width/2;
        float projectileCenterY = getY() + getProjectileImage().getLayoutParams().height/2;

        float differenceX = projectileCenterX - playerCenterX;
        float differenceY = projectileCenterY - playerCenterY;

        radius = (float)Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));

Log.d("testP", Float.toString(radius));
Log.d("testP", Float.toString(differenceX));
        currentPlayerProjectileDifferenceX = differenceX;
        currentPlayerProjectileDifferenceY = differenceY;
    }
}
