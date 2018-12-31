package matth.dungeon.EnemyTile.SpriteTypes;

import android.widget.ImageView;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class SquareBossEnemy extends SquareEnemy {

    private final float STARTING_HEALTH = 500;
    private final int DAMAGE = 1;
    private final String SPRITE_NAME = "square_enemy";
    private final int DESTINATION_DELAY = 800;
    private final int VELOCITY = 15;

    private ImageView healthBar;
    private ImageView maxHealthBar;

    public SquareBossEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void init() {
        super.init();
        ImageView healths[] = initBossHealth();
        healthBar = healths[0];
        maxHealthBar = healths[1];
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
    }

    @Override
    public void setHealth() {
        super.health = STARTING_HEALTH;
    }

    @Override
    public void setSpriteName() {
        super.spriteName = SPRITE_NAME;
    }

    @Override
    public void setVelocity() {
        super.velocity = VELOCITY;
    }

    @Override
    public void takeDamage(float x) {
        super.takeDamage(x);
        changeBossHealth(healthBar, maxHealthBar, STARTING_HEALTH);
    }

    @Override
    public void spawnSprite(int x, int y, Integer width, Integer height) {
        super.spawnSprite(x, y, mainUtility.getScreenWidth()/8, mainUtility.getScreenWidth()/8);
    }

}
