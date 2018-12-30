package matth.dungeon.EnemyTile.ProjectileTypes;

import matth.dungeon.EnemyTile.SpriteTypes.Enemy;

public interface ProjectileBehaviour {

    void init();
    void delete();
    void outOfBounds();
    void initCheck();
    void movePattern();
    void effect(Enemy enemy);

    void setDamage();
    void setProjectileName();
}
