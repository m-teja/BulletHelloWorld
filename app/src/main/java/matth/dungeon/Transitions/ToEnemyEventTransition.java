package matth.dungeon.Transitions;

import android.content.Context;

import matth.dungeon.EnemyTile.EnemyEventActivity;

public class ToEnemyEventTransition extends TransitionBase {

    ToEnemyEventTransition(Context con) {
        super(con);
    }

    @Override
    int getDelay() {
        return 1000;
    }

    @Override
    Class getActivityName() {
        return EnemyEventActivity.class;
    }
}
