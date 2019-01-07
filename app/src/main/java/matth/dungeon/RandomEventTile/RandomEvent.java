package matth.dungeon.RandomEventTile;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import matth.dungeon.R;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public abstract class RandomEvent {

    PlayerInfoPassUtility playerInfoPassUtility;
    Context con;

    public RandomEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        this.playerInfoPassUtility = playerInfoPassUtility;
        this.con = con;
    }

    public abstract String textDescription();

    public void setText() {
        TextView description = ((Activity) con).findViewById(R.id.patternDescription);
        description.setText(textDescription());
    }

}
