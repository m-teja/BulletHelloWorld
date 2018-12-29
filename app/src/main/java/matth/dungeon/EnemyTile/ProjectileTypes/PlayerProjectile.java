package matth.dungeon.EnemyTile.ProjectileTypes;

import android.widget.ImageView;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.EnemyTile.SpriteTypes.Enemy;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public abstract class PlayerProjectile extends Projectile {

    PlayerUtility playerUtility;

    PlayerProjectile(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility);
        this.playerUtility = playerUtility;
        initCheck();
    }

    public abstract void init();

    public abstract void delete();

    public abstract void effect(Enemy enemy);

    public void initCheck() {
        super.initCheck();
    }

}
