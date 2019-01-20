package matth.dungeon.RandomEventTile;

import android.content.Context;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.BouncePattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.ClassicPattern;
import matth.dungeon.EnemyTile.ProjectileTypes.PatternTypes.HomingPattern;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.PlayerInfoPassUtility;

public class PatternGetEvent extends RandomEvent {

    private static final ArrayList<Class> possiblePatterns = new ArrayList<>();
    private String text;

    public PatternGetEvent(PlayerInfoPassUtility playerInfoPassUtility, Context con) {
        super(playerInfoPassUtility, con);
        initPatterns();
    }

    @Override
    public String textDescription() {
        return text;
    }

    @Override
    public void immediateEffect() {
        addRandomPattern();
        setText();

    }

    private void initPatterns() {
        possiblePatterns.add(BouncePattern.class);
        possiblePatterns.add(ClassicPattern.class);
        possiblePatterns.add(HomingPattern.class);
    }

    private void addRandomPattern() {
        Class classType = possiblePatterns.get((int)(Math.random() * possiblePatterns.size()));

        boolean success = playerInfoPassUtility.addPattern(classType);

        if (success) {
            text = "You have received a new pattern";
        }
        else {
            text = "fix this later me";
        }

        FileUtility.savePlayer(playerInfoPassUtility, con);
        setText();
        displayLeaveButton();
    }
}
