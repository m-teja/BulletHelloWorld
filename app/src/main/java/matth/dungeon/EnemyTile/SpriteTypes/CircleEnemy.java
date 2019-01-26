package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.CirclePattern;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleEnemy extends Enemy {

    private CirclePattern circlePattern;


    CircleEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    public void init() {
        runUpdatePlayerPosition.run();
        runUpdateDestination.run();
        move.run();
        circlePattern = new CirclePattern(mainUtility, enemyUtility, this);
        circlePattern.init();
    }

    @Override
    public void delete() {
        circlePattern.deleteAll();
    }

    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
        deleteAll();
    }

    @Override
    public void movePattern() {
        EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY());
    }

    @Override
    public void setHealth() {
        super.health = 50;
    }

    @Override
    public void setSpriteName() {
        super.spriteName = "circle_enemy";
    }

    @Override
    public void setVelocity() {
        super.velocity = 8;
    }

    @Override
    public void setDamage() {
        super.damage = 3;
    }

    @Override
    public void setUpdateDestinationDelay() {
        super.destinationUpdateDelay = 800;
    }

    @Override
    void calcVelocity() {

        float differenceX = destinationX - getX();

        if (differenceX > 0) {
            velocityX = velocity;
        }
        else {
            velocityX = -velocity;
        }
    }
}
