package matth.dungeon.Utility;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import matth.dungeon.GameUI.LevelTile;

public class FileUtility {

    private static final String MAP_FILE = "MapGame";

    public static void saveMap(Context con, ArrayList<ArrayList<LevelTile>> map) {

        try {
            FileOutputStream fos = con.openFileOutput(MAP_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
