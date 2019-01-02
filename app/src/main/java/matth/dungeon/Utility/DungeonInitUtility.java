package matth.dungeon.Utility;

import android.os.Bundle;

public class DungeonInitUtility {

    private Bundle extras;

    private boolean loadSave = false;
    private boolean loadPlayer = false;
    private boolean deleteCurrentTile = false;

    public DungeonInitUtility(Bundle extras) {
        this.extras = extras;
    }

    public boolean loadSave() {
        return loadSave;
    }

    public void setLoadSave(boolean loadSave) {
        this.loadSave = loadSave;
    }

    public boolean loadPlayer() {
        return loadPlayer;
    }

    public void setLoadPlayer(boolean loadPlayer) {
        this.loadPlayer = loadPlayer;
    }

    public boolean deleteCurrentTile() {
        return deleteCurrentTile;
    }

    public void setDeleteCurrentTile(boolean deleteCurrentTile) {
        this.deleteCurrentTile = deleteCurrentTile;
    }
}
