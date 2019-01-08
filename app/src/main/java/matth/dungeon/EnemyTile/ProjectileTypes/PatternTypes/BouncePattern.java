package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileBounce;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class BouncePattern extends Pattern {

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    public BouncePattern(MainUtility mainUtility, PlayerUtility playerUtility) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    public BouncePattern(MainUtility mainUtility, PlayerUtility playerUtility, int level) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    @Override
    public void init() {
        spawn.run();
    }

    @Override
    public void spawnPattern() {
        PlayerProjectileBounce playerProjectileBounce = new PlayerProjectileBounce(mainUtility, playerUtility);
        playerProjectileBounce.spawnProjectile(playerUtility.getPlayerSprite().getX(), playerUtility.getPlayerSprite().getY(), null, null);
        //center projectile
        playerProjectileBounce.setX((int)(playerProjectileBounce.getX() - playerProjectileBounce.getWidth()/2 + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth()/2));
        playerProjectileBounce.init();
    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 400;
    }

    @Override
    public void delete() {


    }
}
