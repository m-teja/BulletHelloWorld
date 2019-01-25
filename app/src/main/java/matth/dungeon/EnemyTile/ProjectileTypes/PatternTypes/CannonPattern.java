package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class CannonPattern extends Pattern {

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    CannonPattern(MainUtility mainUtility, PlayerUtility playerUtility) {
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

        int size = playerUtility.getPlayerSprite().getPlayerImage().getWidth();

        PlayerProjectileClassic p = new PlayerProjectileClassic(mainUtility, playerUtility, 2);
        p.spawnProjectile(playerUtility.getPlayerSprite().getX(),
                playerUtility.getPlayerSprite().getY() + size,
                size, size);
        p.setDamage(50);
        p.setVelocity(15);
        p.init();

    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 2000;
    }

    @Override
    public void delete() {

    }
}
