package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class TriangleEnemy extends Enemy{

    private final float STARTING_HEALTH = 60;
    private final int DAMAGE = 30;
    private final String SPRITE_NAME = "triangle_enemy";
    private final int DESTINATION_DELAY = 20;
    private final int VELOCITY = 15;

    public TriangleEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void delete() {

    }

    @Override
    public void init() {
        runUpdateDestination.run();
        runUpdatePlayerPosition.run();
        move.run();
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
        deleteAll();
    }

    @Override
    public void movePattern() {
        EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY() + velocityY);
    }

    @Override
    public void setUpdateDestinationDelay() {
        super.destinationUpdateDelay = DESTINATION_DELAY;
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
}
