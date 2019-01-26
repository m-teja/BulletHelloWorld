package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleBossEnemy extends CircleEnemy {

    private final float STARTING_HEALTH = 400;

    public CircleBossEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void init() {
        super.init();
        initBossHealth();
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
    }

    @Override
    public void setDamage() {
        super.damage = 1;
    }

    @Override
    public void setHealth() {
        super.health = STARTING_HEALTH;
    }

    @Override
    public void takeDamage(float x) {
        super.takeDamage(x, STARTING_HEALTH);
    }

    @Override
    public void spawnSprite(float x, float y, Integer width, Integer height) {
        super.spawnSprite(x, y, mainUtility.getScreenWidth()/6, mainUtility.getScreenWidth()/6);
    }
}
