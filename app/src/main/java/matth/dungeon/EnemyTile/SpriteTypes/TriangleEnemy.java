package matth.dungeon.EnemyTile.SpriteTypes;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;

public class TriangleEnemy extends Enemy{

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
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - damage);
        deleteAll();
    }

    @Override
    public void movePattern() {
        getSprite().setRotation(getSprite().getRotation() + 10);
        EnemyUtility.moveImage(getSprite(), getX() + velocityX, getY() + velocityY);
    }

    @Override
    public void setUpdateDestinationDelay() {
        super.destinationUpdateDelay = 20;
    }

    @Override
    public void setHealth() {
        super.health = 60;
    }

    @Override
    public void setSpriteName() {
        super.spriteName = "triangle_enemy";
    }

    @Override
    public void setVelocity() {
        super.velocity = 15;
    }

    @Override
    public void setDamage() {
        super.damage = 30;
    }

    @Override
    public void takeDamage(float x) {
        super.takeDamage(x);
        getSprite().setX((float)Math.random() * mainUtility.getScreenWidth() - getSprite().getMeasuredWidth());
        getSprite().setY((float)mainUtility.getScreenHeight()/10);
    }
}
//TODO spamming move into a wall will glitch