package matth.dungeon.RandomEventTile;

import android.content.Context;

import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class HealthPotionEvent extends RandomEvent {

    public HealthPotionEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return "Your health has been restored";
    }

    @Override
    public void immediateEffect() {
        playerInfoPassUtility.setHealth(playerInfoPassUtility.getMaxHealth());
        FileUtility.savePlayer(playerInfoPassUtility, con);
    }
}
