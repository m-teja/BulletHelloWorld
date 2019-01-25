package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.CirclePattern;
import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class CircleEnemy extends Enemy {


    private final int STARTING_HEALTH = 50;
    private final String SPRITE_NAME = "circle_enemy";
    private final int VELOCITY = 8;
    private final int DESTINATION_DELAY = 800;
    private final int DAMAGE = 2;

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
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
        deleteAll();
    }

    @Override
    public void movePattern() {
        EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY());
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
    public void setUpdateDestinationDelay() {
        super.destinationUpdateDelay = DESTINATION_DELAY;
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
