package matth.dungeon.RandomEventTile;

import android.content.Context;

import matth.dungeon.Utility.PlayerInfoPassUtility;

public class MaxHealthPotionEvent extends RandomEvent {
    public MaxHealthPotionEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return null;
    }
}
