package matth.dungeon.GameUI;

import java.io.Serializable;

public class LevelTile implements Serializable {

    //Tile types
    public static final int EMPTY = 0;
    public static final String EMPTY_IMAGE = "empty";
    public static final int WALL = 1;
    public static final String WALL_IMAGE = "wall";
    public static final int PLAYER_POS = 2;
    public static final String PLAYER_POS_IMAGE = "player";

    //Event types
    public static final int END_POS = 3;
    public static final String END_POS_IMAGE = "end";
    public static final int NO_EVENT = 4;
    public static final int ENEMY_EVENT = 5;
    public static final String ENEMY_EVENT_IMAGE = "enemy";
    public static final int ITEM_EVENT = 6;
    public static final String ITEM_EVENT_IMAGE = "item";

    public static final String[] ENEMY_TYPES = {"square", "circle", "bossSquare"};
    public static final String[] RANDOM_EVENT_TYPES = {"healthPotion", "maxHealthPotion"};

    private int[] enemies = new int[ENEMY_TYPES.length];
    private int randomEvent;

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

    public void setEnemy(int type) {
        enemies[type]++;
    }

    public int[] getEnemies() {
        return enemies;
    }

    public int getRandomEvent() {
        return randomEvent;
    }

    public void setRandomEvent(int randomEvent) {
        this.randomEvent = randomEvent;
    }
}
