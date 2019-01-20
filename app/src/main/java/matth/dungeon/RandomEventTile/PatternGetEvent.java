package matth.dungeon.RandomEventTile;

import android.content.Context;

import matth.dungeon.Utility.PlayerInfoPassUtility;

public class PatternGetEvent extends RandomEvent {

    public PatternGetEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
    }

    @Override
    public String textDescription() {
        return null;
    }

    @Override
    public void immediateEffect() {
        setText();

    }

    private void addRandomPattern() {

    }
}
