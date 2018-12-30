package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public class ClassicPattern extends Pattern {

    private int spawnDelay = 250;
    //change later depending on situation

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    private Handler spawnProjectile = new Handler();

    public ClassicPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
        super();
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    @Override
    public void stop() {
        spawnProjectile.removeCallbacksAndMessages(null);
    }

    @Override
    public void init() {
        spawn.run();
    }

    public Runnable spawn = new Runnable() {
        @Override
        public void run() {

            PlayerProjectileClassic playerProjectileClassic = new PlayerProjectileClassic(mainUtility, playerUtility);
            playerProjectileClassic.spawnProjectile(playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2, playerUtility.getPlayerSprite().getY(), null, null);
            //TODO fix centering issue

            playerProjectileClassic.init();
            spawnProjectile.postDelayed(spawn, spawnDelay);
        }
    };
}
//TODO make initCheck into utility