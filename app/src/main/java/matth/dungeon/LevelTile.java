package matth.dungeon;

public class LevelTile {

    //Tile types
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int PLAYER_POS = 2;
    public static final int END_POS = 3;

    //Event types
    public static final int NO_EVENT = 0;
    public static final int ENEMY_EVENT = 1;
    public static final int ITEM_EVENT = 2;

    private int type;
    private int event;
    private boolean visited;

    LevelTile(int type) {
        this.type = type;
        this.event = NO_EVENT;
        visited = false;
    }

    public int getType() {
        return type;
    }

    public void setType(int num) {
        type = num;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getEvent() {
        return event;
    }
}
