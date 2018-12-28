package matth.dungeon.GameUI;

import android.widget.TextView;

public class TextLines {


    public static String getLine(LevelTile tile, int direction) {

        String result = "";

        if (tile.getType() == LevelTile.EMPTY) {
            result += "There is an empty space";
        }
        else if (tile.getType() == LevelTile.WALL) {
            result += "There is a wall";
        }
        else if (tile.getType() == LevelTile.END_POS) {
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

        if (tile.getEvent() == LevelTile.NO_EVENT) {
            result += "\n Nothing of interest here";
        }
        else if (tile.getEvent() == LevelTile.ENEMY_EVENT) {
            result += "\n Enemies spotted here";
        }
        else if (tile.getEvent() == LevelTile.ITEM_EVENT) {
            result += "\n An item of interest here";
        }

        return result;
    }

    public static void animateText(TextView up, TextView right, TextView down, TextView left) {

        up.setAlpha(0.0f);
        up.animate().alpha(1.0f).setListener(null).setDuration(500);

        right.setAlpha(0.0f);
        right.animate().alpha(1.0f).setListener(null).setDuration(700);

        down.setAlpha(0.0f);
        down.animate().alpha(1.0f).setListener(null).setDuration(900);

        left.setAlpha(0.0f);
        left.animate().alpha(1.0f).setListener(null).setDuration(1100);
    }
}
