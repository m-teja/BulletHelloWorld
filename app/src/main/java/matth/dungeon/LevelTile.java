package matth.dungeon;

public class LevelTile {

    private int type;
    private int event;
    private boolean visited;

    LevelTile(int type) {
        this.type = type;
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
