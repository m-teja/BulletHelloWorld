package matth.dungeon.EnemyTile.ProjectileTypes;

import android.os.Handler;

import matth.dungeon.Utility.PlayerUtility;
import matth.dungeon.Utility.MainUtility;

public class ClassicPattern {

    private int spawnDelay = 500;

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    private Handler spawnProjectile = new Handler();

    public ClassicPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    public void init() {
        spawn.run();
    }

    private Runnable spawn = new Runnable() {
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
