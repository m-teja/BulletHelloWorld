package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void delete() {

    }

    @Override
    public void init() {
        runUpdatePlayerPosition.run();
        runUpdateDestination.run();
        move.run();
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
        deleteAll();
    }

    @Override
    public void movePattern() {
        EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY() + velocityY);
    }

    @Override
    public void setUpdateDestinationDelay() {
        super.destinationUpdateDelay = 800;
    }

    @Override
    public void setHealth() {
        super.health = 30;
    }

    @Override
    public void setSpriteName() {
        super.spriteName = "square_enemy";
    }

    @Override
    public void setVelocity() {
        super.velocity = 15;
    }

    @Override
    public void setDamage() {
        super.damage = 10;
    }
}
