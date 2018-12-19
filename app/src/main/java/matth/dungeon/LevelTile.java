package matth.dungeon;

public class LevelTile {

    private int type;

    LevelTile(int type) {
        this.type = type;
    }

    public String test() {
        return Integer.toString(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int num) {
        type = num;
    }
}
