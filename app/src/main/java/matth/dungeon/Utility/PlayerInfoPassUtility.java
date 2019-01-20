package matth.dungeon.Utility;

import java.io.Serializable;
import java.util.ArrayList;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;

public class PlayerInfoPassUtility implements Serializable {

    private final float DEFAULT_HEALTH = 400;
    private final float DEFAULT_MAX_HEALTH = 400;
    private final Class DEFAULT_PATTERN = ClassicPattern.class;
    private final int DEFAULT_LEVEL = 1;

    private float health;
    private float maxHealth;

    private ArrayList<Class<?>> unlockedPatterns;
    private int level;

    public PlayerInfoPassUtility(PlayerSprite playerSprite) {
        this.health = playerSprite.getHealth();
        this.maxHealth = playerSprite.getMaxHealth();
    }

    public PlayerInfoPassUtility() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.unlockedPatterns = new ArrayList<>();
        this.unlockedPatterns.add(DEFAULT_PATTERN);
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

    public void setPattern(Class pattern) {
        this.unlockedPatterns.add(pattern);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Class<?>> getUnlockedPatterns() {
        return unlockedPatterns;
    }

    public int getLevel() {
        return level;
    }
}
