package matth.dungeon;

import android.widget.ImageView;

public class Enemy {

    public Utility utility;
    public int health;
    public String spriteName;
    public String projectileName;

    public Enemy( Utility utility) {
        this.utility = utility;
    }

}
