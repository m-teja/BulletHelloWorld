package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileHoming;
import matth.dungeon.Utility.MainUtility;
import matth.dungeon.Utility.PlayerUtility;

public class HomingPattern extends Pattern {

    private MainUtility mainUtility;
    private PlayerUtility playerUtility;

    public HomingPattern (MainUtility mainUtility, PlayerUtility playerUtility) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    public HomingPattern (MainUtility mainUtility, PlayerUtility playerUtility, int level) {
        this.mainUtility = mainUtility;
        this.playerUtility = playerUtility;
    }

    @Override
    public void init() {
        spawn.run();
    }

    @Override
    public void spawnPattern() {
        PlayerProjectileHoming left = new PlayerProjectileHoming(mainUtility, playerUtility);
        left.spawnProjectile(playerUtility.getPlayerSprite().getX(), playerUtility.getPlayerSprite().getY() - playerUtility.getPlayerSprite().getPlayerImage().getMeasuredHeight(), null, null);
        left.init();

        PlayerProjectileHoming right = new PlayerProjectileHoming(mainUtility, playerUtility);
        right.spawnProjectile(playerUtility.getPlayerSprite().getX() + playerUtility.getPlayerSprite().getPlayerImage().getMeasuredWidth(), playerUtility.getPlayerSprite().getY() - playerUtility.getPlayerSprite().getPlayerImage().getMeasuredHeight(), null, null);
        right.setX((int) (right.getX() - right.getWidth()/2));
        right.init();
    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 1500;
    }

    @Override
    public void delete() {

    }
}
