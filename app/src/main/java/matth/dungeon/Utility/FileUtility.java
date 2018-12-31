package matth.dungeon.Utility;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import matth.dungeon.GameUI.LevelTile;

public class FileUtility {

    private static final String MAP_FILE = "game.map";

    public static void saveMap(Context con, ArrayList<ArrayList<LevelTile>> map) {

        try {
            FileOutputStream fos = con.openFileOutput(MAP_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<LevelTile>> loadMap(Context con) {
        ArrayList<ArrayList<LevelTile>> map = null;

        try {
            FileInputStream fis = con.openFileInput(MAP_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (ArrayList<ArrayList<LevelTile>>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
