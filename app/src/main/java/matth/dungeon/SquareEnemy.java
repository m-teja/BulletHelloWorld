package matth.dungeon;

import android.widget.ImageView;

public class SquareEnemy extends Enemy {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square_enemy";
    public final String PROJECTILE_NAME = "square_projectile";


    public SquareEnemy(Utility utility) {
        super(utility);
        super.health = STARTING_HEALTH;
        super.spriteName = SPRITE_NAME;

    }


}
