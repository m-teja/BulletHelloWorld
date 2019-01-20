package matth.dungeon.RandomEventTile;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import matth.dungeon.R;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public abstract class RandomEvent {

    PlayerInfoPassUtility playerInfoPassUtility;
    Context con;

    RandomEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        this.playerInfoPassUtility = playerInfoPassUtility;
        this.con = con;

    }

    public abstract String textDescription();
    public abstract void immediateEffect();

    public void setText() {
        TextView description = ((Activity) con).findViewById(R.id.eventInfo);
        description.setText(textDescription());
    }

    void displayLeaveButton() {
        Button leave = ((Activity) con).findViewById(R.id.eventToDungeonButton);
        leave.setVisibility(View.VISIBLE);
    }

}
