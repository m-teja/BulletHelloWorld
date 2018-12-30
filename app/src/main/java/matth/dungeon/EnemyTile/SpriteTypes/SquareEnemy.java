package matth.dungeon.EnemyTile.SpriteTypes;

import android.app.Activity;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.util.Log;

import matth.dungeon.Utility.EnemyUtility;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.R;

public class SquareEnemy extends Enemy implements EnemyBehaviour {

    private final float STARTING_HEALTH = 30;
    private final int DAMAGE = 10;
    private final String SPRITE_NAME = "square_enemy";
    private final int DESTINATION_DELAY = 800;
    private final int VELOCITY = 15;

    public SquareEnemy(MainUtility mainUtility, EnemyUtility enemyUtility) {
        super(mainUtility, enemyUtility);
    }

    @Override
    public void init() {
        runUpdatePlayerPosition.run();
        runUpdateDestination.run();
        move.run();
    }

    @Override
    public void effect() {
        enemyUtility.getPlayerSprite().setHealth(enemyUtility.getPlayerSprite().getHealth() - DAMAGE);
        delete();
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
