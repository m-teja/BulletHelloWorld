package matth.dungeon.EnemyTile.ProjectileTypes;

public interface ProjectileBehaviour {

    void init();
    void delete();
    void outOfBounds();
    void initCheck();
    void movePattern();

    void setDamage();
    void setProjectileName();
}
