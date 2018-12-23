package matth.dungeon;

import android.widget.ImageView;

public class Enemy {

    public Utility utility;
    public int health;
    public String spriteImage;
    public String projectileImage;

    public Enemy( Utility utility) {
        this.utility = utility;
    }
}
