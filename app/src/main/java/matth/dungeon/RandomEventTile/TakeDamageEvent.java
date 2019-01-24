package matth.dungeon.RandomEventTile;

import android.content.Context;

import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class TakeDamageEvent extends RandomEvent {

    private String text = "";

    TakeDamageEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return text;
    }

    @Override
    public void immediateEffect() {
        int damageTaken = (int)(Math.random() * 15) + 30;

        playerInfoPassUtility.setHealth(playerInfoPassUtility.getHealth() - damageTaken);
        FileUtility.savePlayer(playerInfoPassUtility, con);
        text = "You took " + Integer.toString(damageTaken) + " damage" + " \ndue to some reason that will be added later";
        setText();
        displayLeaveButton();

        //TODO check for negative health
    }
}
