package matth.dungeon.Utility;

import java.io.Serializable;
import java.util.ArrayList;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.BouncePattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.BurstPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.CannonPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.HomingPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.Pattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PlayerProjectileClassic;
import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;
import matth.dungeon.GameUI.LevelTile;

public class PlayerInfoPassUtility implements Serializable {

    private boolean debug = true;

    private final float DEFAULT_HEALTH = 100;
    private final float DEFAULT_MAX_HEALTH = 100;
    private final Class DEFAULT_PATTERN = ClassicPattern.class;
    private final int DEFAULT_LEVEL = 1;

    private float health;
    private float maxHealth;

    private ArrayList<Class<?>> unlockedPatterns;
    private int level;

    public PlayerInfoPassUtility() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.unlockedPatterns = new ArrayList<>();
        this.unlockedPatterns.add(DEFAULT_PATTERN);

        if (debug) {
            this.unlockedPatterns.add(BouncePattern.class);
            this.unlockedPatterns.add(HomingPattern.class);
            this.unlockedPatterns.add(BurstPattern.class);
            this.unlockedPatterns.add(CannonPattern.class);
        }

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

    public boolean addPattern(Class pattern) {

        for (int i = 0; i < unlockedPatterns.size(); i++) {
            if (unlockedPatterns.get(i) == pattern) {
                return false;
            }
        }
        this.unlockedPatterns.add(pattern);
        return true;
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
