package matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes;

import matth.dungeon.Utility.MainUtility;

public class CannonPattern extends Pattern {


    CannonPattern(MainUtility mainUtility) {
        super(mainUtility);
    }

    @Override
    public void init() {
        spawn.run();
    }

    @Override
    public void spawnPattern() {

    }

    @Override
    public void getSpawnDelay() {
        super.spawnDelay = 2000;
    }

    @Override
    public void delete() {

    }
}
