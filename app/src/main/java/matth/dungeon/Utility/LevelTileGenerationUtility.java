package matth.dungeon.Utility;

import matth.dungeon.GameUI.LevelTile;

public class LevelTileGenerationUtility {

    public static String getImageType(LevelTile levelTile) {
        if (levelTile.getType() == LevelTile.PLAYER_POS) {
            return LevelTile.PLAYER_POS_IMAGE;
        }

        switch (levelTile.getEvent()) {
            case LevelTile.ENEMY_EVENT:
                return LevelTile.ENEMY_EVENT_IMAGE;
            case LevelTile.ITEM_EVENT:
                return LevelTile.ITEM_EVENT_IMAGE;
            case LevelTile.END_POS:
                return LevelTile.END_POS_IMAGE;
        }

        switch (levelTile.getType()) {
            case LevelTile.EMPTY:
                return LevelTile.EMPTY_IMAGE;
            case LevelTile.WALL:
                return LevelTile.WALL_IMAGE;
            default:
                break;
        }

        return "";
    }

    public static int genRandomEvent() {

        return (int)(Math.random() * LevelTile.RANDOM_EVENT_TYPES.length);
    }
}
