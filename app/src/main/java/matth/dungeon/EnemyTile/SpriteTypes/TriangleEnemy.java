package matth.dungeon.EnemyTile.SpriteTypes;

public class TriangleEnemy extends Enemy{

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

    }

    @Override
    public void movePattern() {

    }

    @Override
    public void setUpdateDestinationDelay() {

    }

    @Override
    public void setHealth() {

    }

    @Override
    public void setSpriteName() {

    }

    @Override
    public void setVelocity() {

    }
}
