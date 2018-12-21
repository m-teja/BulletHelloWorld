package matth.dungeon;

public class TextLines {


    public static String getLine(int tileType) {
        if (tileType == TileMap.EMPTY) {
            return "There is an empty space";
        }
        else if (tileType == TileMap.WALL) {
            return "There is a wall";
        }
        else if (tileType == TileMap.END_POS) {
            return "You see the end";
        }

        return "you should not see this";
    }
}
