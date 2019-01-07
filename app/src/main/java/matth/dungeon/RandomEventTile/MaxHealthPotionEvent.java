package matth.dungeon.RandomEventTile;

import android.content.Context;
import android.util.Log;

import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class MaxHealthPotionEvent extends RandomEvent {
    public MaxHealthPotionEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return "Your maximum health has been increased";
    }

    @Override
    public void immediateEffect() {
        playerInfoPassUtility.setMaxHealth(playerInfoPassUtility.getMaxHealth() + 50);
        FileUtility.savePlayer(playerInfoPassUtility, con);

        displayLeaveButton();
    }
}
