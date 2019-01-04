package matth.dungeon.Utility;

import java.io.Serializable;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.GameUI.Inventory;

public class PlayerInfoPassUtility implements Serializable {

    public static final String ENEMY_TO_DUNGEON_INFO = "enemyToDungeon";

    private final float DEFAULT_HEALTH = 100;
    private final float DEFAULT_MAX_HEALTH = 100;
    private final int DEFAULT_PATTERN = Pattern.CLASSIC_PATTERN;
    private final int DEFAULT_LEVEL = 1;

    private Inventory inventory;

    private float health;
    private float maxHealth;

    private int pattern;
    private int level;

    public PlayerInfoPassUtility(PlayerSprite playerSprite, Inventory inventory) {
        this.health = playerSprite.getHealth();
        this.maxHealth = playerSprite.getMaxHealth();
        this.inventory = inventory;
    }

    public PlayerInfoPassUtility() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.pattern = DEFAULT_PATTERN;
        this.level = DEFAULT_LEVEL;
        this.inventory = new Inventory();
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPattern() {
        return pattern;
    }

    public int getLevel() {
        return level;
    }
}
