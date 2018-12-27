package matth.dungeon;

public interface ProjectileBehaviour {

    public void init();
    public void delete();
    public void effect(Enemy enemy);
    public void outOfBounds();
    public void initCheck();
}
