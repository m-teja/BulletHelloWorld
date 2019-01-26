package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleBossEnemy extends CircleEnemy {

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

}
