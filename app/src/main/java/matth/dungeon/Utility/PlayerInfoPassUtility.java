package matth.dungeon.Utility;

import java.io.Serializable;

import matth.dungeon.EnemyTile.SpriteTypes.PlayerSprite;

public class PlayerInfoPassUtility implements Serializable {

    public static final String ENEMY_TO_DUNGEON_INFO = "enemyToDungeon";

    private final float DEFAULT_HEALTH = 100;
    private final float DEFAULT_MAX_HEALTH = 100;

    private float health;
    private float maxHealth;

    public PlayerInfoPassUtility(PlayerSprite playerSprite) {
        this.health = playerSprite.getHealth();
        this.maxHealth = playerSprite.getMaxHealth();
    }

    public PlayerInfoPassUtility(int health, int maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public PlayerInfoPassUtility() {
        this.health = DEFAULT_HEALTH;
        this.maxHealth = DEFAULT_MAX_HEALTH;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }
}
