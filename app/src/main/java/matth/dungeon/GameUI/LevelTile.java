package matth.dungeon.GameUI;

import java.io.Serializable;
import java.util.ArrayList;

import matth.dungeon.EnemyTile.SpriteTypes.CircleEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.SquareBossEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.SquareEnemy;
import matth.dungeon.EnemyTile.SpriteTypes.TriangleEnemy;
import matth.dungeon.RandomEventTile.HealthPotionEvent;
import matth.dungeon.RandomEventTile.MaxHealthPotionEvent;
import matth.dungeon.RandomEventTile.PatternGetEvent;
import matth.dungeon.RandomEventTile.TakeDamageEvent;

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

    public static final ArrayList<Class<?>> ENEMY_TYPES = new ArrayList<>();
    private static final ArrayList<Class<?>> ENEMY_BOSS_TYPES = new ArrayList<>();
    public static final ArrayList<Class<?>> RANDOM_EVENT_TYPES = new ArrayList<>();

    private ArrayList<Class> enemies = new ArrayList<>();
    private ArrayList<Class> bosses = new ArrayList<>();
    private Class randomEvent;

    private int type;
    private int event;
    private boolean visited;

    public LevelTile(int type) {
        this.type = type;
        this.event = NO_EVENT;
        visited = false;
        initEnemies();
        initRandomEvents();
    }

    private void initRandomEvents() {
        RANDOM_EVENT_TYPES.add(HealthPotionEvent.class);
        RANDOM_EVENT_TYPES.add(MaxHealthPotionEvent.class);
        RANDOM_EVENT_TYPES.add(PatternGetEvent.class);
        RANDOM_EVENT_TYPES.add(TakeDamageEvent.class);
    }

    private void initEnemies() {
        ENEMY_TYPES.add(SquareEnemy.class);
        ENEMY_TYPES.add(CircleEnemy.class);
        ENEMY_TYPES.add(TriangleEnemy.class);

        ENEMY_BOSS_TYPES.add(SquareBossEnemy.class);
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

    public void addEnemy(int enemy) {
        enemies.add(ENEMY_TYPES.get(enemy));
    }

    public ArrayList<Class> getEnemies() {
        return enemies;
    }

    public void addBoss(int boss) {
        bosses.add(ENEMY_BOSS_TYPES.get(boss));
    }

    public ArrayList<Class> getBosses() {
        return bosses;
    }

    public void setRandomEvent(int randomEvent) {
        this.randomEvent = RANDOM_EVENT_TYPES.get(randomEvent);
    }

    public Class getRandomEvent() {
        return randomEvent;
    }
}
