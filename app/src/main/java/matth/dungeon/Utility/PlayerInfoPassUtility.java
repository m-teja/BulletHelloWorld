package matth.dungeon.Utility;

import java.io.Serializable;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;

public class PlayerInfoPassUtility implements Serializable {

    private final float DEFAULT_HEALTH = 100;
    private final float DEFAULT_MAX_HEALTH = 100;
    private final int DEFAULT_PATTERN = Pattern.CLASSIC_PATTERN;
    private final int DEFAULT_LEVEL = 1;

    private float health;
    private float maxHealth;

    private int pattern;
    private int level;

    public PlayerInfoPassUtility(PlayerSprite playerSprite) {
        this.health = playerSprite.getHealth();
        this.maxHealth = playerSprite.getMaxHealth();
    }

    public PlayerInfoPassUtility() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.pattern = DEFAULT_PATTERN;
        this.level = DEFAULT_LEVEL;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
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
