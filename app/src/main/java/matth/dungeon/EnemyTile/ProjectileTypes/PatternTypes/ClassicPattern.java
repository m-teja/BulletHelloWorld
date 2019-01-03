package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public class ClassicPattern extends Pattern {

    //change later depending on situation

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    public ClassicPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
        super();
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    @Override
    public void init() {
        Log.d("test", "init");
        spawn.run();
    }

    @Override
    public void spawnPattern() {
        PlayerProjectileClassic playerProjectileClassic = new PlayerProjectileClassic(mainUtility, playerUtility);
        playerProjectileClassic.spawnProjectile(playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2, playerUtility.getPlayerSprite().getY(), null, null);
        playerProjectileClassic.setX((int)(playerProjectileClassic.getX() - playerProjectileClassic.getWidth()/2));

        //TODO fix centering issue
        playerProjectileClassic.init();
    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 250;
    }
}
//TODO make initCheck into utility