package matth.dungeon;

public class LevelTile {

    int type;

    LevelTile(int type) {
        this.type = type;
    }

    public String test() {
        return Integer.toString(type);
    }
}
