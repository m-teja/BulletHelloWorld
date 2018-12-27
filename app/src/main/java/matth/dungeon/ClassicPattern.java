package matth.dungeon;

import android.os.Handler;

public class ClassicPattern {

    private int spawnDelay = 2000;

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    private Handler spawnProjectile = new Handler();

    ClassicPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
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
            playerProjectileClassic.spawnProjectile(playerUtility.getPlayerSprite().getX(), playerUtility.getPlayerSprite().getY(), null, null);
            playerProjectileClassic.init();
            spawnProjectile.postDelayed(spawn, spawnDelay);
        }
    };


}
