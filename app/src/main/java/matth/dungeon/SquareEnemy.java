package matth.dungeon;

import android.widget.ImageView;

public class SquareEnemy extends Enemy {

    public final int STARTING_HEALTH = 100;
    public final String SPRITE_NAME = "square";
    public final String PROJECTILE_NAME = "square_proj";


    public SquareEnemy(Utility utility) {

        super(utility);

    }

}
