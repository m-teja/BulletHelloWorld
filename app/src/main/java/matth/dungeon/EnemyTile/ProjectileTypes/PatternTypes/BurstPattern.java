package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import android.os.Handler;

import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class BurstPattern extends Pattern {

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;
    private Handler burstHandler = new Handler();

    public BurstPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
        super(mainUtility);
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    @Override
    public void init() {
        spawn.run();
    }

    @Override
    public void spawnPattern() {

        burstHandler.postDelayed(new Runnable() {

            private int counter = 0;

            @Override
            public void run() {
                PlayerProjectileClassic p = new PlayerProjectileClassic(mainUtility, playerUtility);
                p.spawnProjectile(playerUtility.getPlayerSprite().getX(), playerUtility.getPlayerSprite().getY(), null, null);
                p.setX((int)(p.getX() - p.getWidth()/2 + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2));
                p.init();

                counter++;

                if (counter < 5) {
                    burstHandler.postDelayed(this, 100);
                }
            }
        }, 100);

    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 1500;
    }

    @Override
    public void delete() {
        burstHandler.removeCallbacksAndMessages(null);
    }
}
