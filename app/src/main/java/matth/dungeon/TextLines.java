package matth.dungeon;

public class TextLines {


    public static String getLine(int tileType, int direction) {

        String result = "";

        if (tileType == TileMap.EMPTY) {
            result += "There is an empty space";
        }
        else if (tileType == TileMap.WALL) {
            result += "There is a wall";
        }
        else if (tileType == TileMap.END_POS) {
            result += "You see the end";
        }

        if (direction == 0) {
            result += " in front of you";
        }
        else if (direction == 1) {
            result += " to your right";
        }
        else if (direction == 2) {
            result += " behind you";
        }
        else if (direction == 3) {
            result += " to your left";
        }



        return result;
    }
}
