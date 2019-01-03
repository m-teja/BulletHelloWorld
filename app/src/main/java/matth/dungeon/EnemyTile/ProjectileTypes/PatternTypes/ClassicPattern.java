package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectile;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public class ClassicPattern extends Pattern {

    //change later depending on situation

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    private int level;

    public ClassicPattern(MainUtility mainUtility, PlayerUtility playerUtility, int level) {
        super();
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
        this.level = level;
    }

    @Override
    public void init() {
        Log.d("test", "init");
        spawn.run();
    }

    @Override
    public void spawnPattern() {

        switch (level) {
            case 3:
                PlayerProjectile right = new PlayerProjectileClassic(mainUtility, playerUtility, 1);
                right.spawnProjectile(playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getWidth(), playerUtility.getPlayerSprite().getY(), null, null);
                //center projectile
                right.getProjectileImage().setRotation(90);
                right.setY((int)(right.getY() - right.getHeight()/2 + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredHeight()/2));
                right.init();
            case 2:
                PlayerProjectile down = new PlayerProjectileClassic(mainUtility, playerUtility, 2);
                down.spawnProjectile(playerUtility.getPlayerSprite().getX(),playerUtility.getPlayerSprite().getY() + playerUtility.getPlayerSprite().getPlayerImage().getLayoutParams().height, null, null);
                //center projectile
                down.setX((int)(down.getX() - down.getWidth()/2 + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2));
                down.init();
                //FALL THROUGH
            case 1:
                PlayerProjectileClassic up = new PlayerProjectileClassic(mainUtility, playerUtility);
                up.spawnProjectile(playerUtility.getPlayerSprite().getX(), playerUtility.getPlayerSprite().getY(), null, null);
                //center projectile
                up.setX((int)(up.getX() - up.getWidth()/2 + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2));
                up.init();

            default:
                break;
        }

    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 250;
    }
}
//TODO make initCheck into utility