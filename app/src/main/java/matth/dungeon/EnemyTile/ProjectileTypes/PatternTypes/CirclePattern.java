package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;

import matth.dungeon.EnemyTile.ProjectileTypes.CircleProjectile;
import matth.dungeon.EnemyTile.SpriteTypes.CircleEnemy;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CirclePattern extends Pattern {

    private EnemyUtility enemyUtility;
    private MainUtility mainUtility;
    private CircleEnemy circleEnemy;

    public CirclePattern(MainUtility mainUtility, EnemyUtility enemyUtility, CircleEnemy circleEnemy) {
        super();
        this.mainUtility = mainUtility;
        this.enemyUtility = enemyUtility;
        this.circleEnemy = circleEnemy;
    }

    @Override
    public void init() {
        spawn.run();
    }

    @Override
    public void spawnPattern() {
        CircleProjectile circleProjectile = new CircleProjectile(mainUtility, enemyUtility);
        circleProjectile.spawnProjectile(circleEnemy.getX() + circleEnemy.getSprite().getMeasuredWidth()/2, circleEnemy.getY() + circleEnemy.getSprite().getMeasuredHeight(), null, null);
        circleProjectile.setX((int)(circleProjectile.getX() - circleProjectile.getWidth()/2));

        circleProjectile.init();
    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 300;
    }
}
