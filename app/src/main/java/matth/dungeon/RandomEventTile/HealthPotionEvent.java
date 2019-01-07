package matth.dungeon.RandomEventTile;

import android.content.Context;

import matth.dungeon.Utility.PlayerInfoPassUtility;

public class HealthPotionEvent extends RandomEvent {

    public HealthPotionEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return null;
    }
}
