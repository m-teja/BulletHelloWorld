package matth.dungeon.GameUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import matth.dungeon.EnemyTile.EnemyEventActivity;
import matth.dungeon.R;
import matth.dungeon.RandomEventTile.RandomEventActivity;
import matth.dungeon.Transitions.ToEnemyEventActivity;
import matth.dungeon.Transitions.ToEnemyEventTransition;
import matth.dungeon.Utility.DungeonInitUtility;
import matth.dungeon.Utility.FileUtility;
import matth.dungeon.Utility.LevelTileGenerationUtility;
import matth.dungeon.Utility.MainUtility;

public class TileMap {

    private MainUtility mainUtility;
    private LevelTileGenerationUtility levelTileGenerationUtility;

    //change size per level maybe
    private ArrayList<ArrayList<LevelTile>> levelMap;

    TileMap(MainUtility mainUtility, int size, DungeonInitUtility dungeonInitUtility) {
        this.mainUtility = mainUtility;
        this.levelTileGenerationUtility = new LevelTileGenerationUtility(mainUtility);
        init(size, dungeonInitUtility);
    }

    private void init(int size, DungeonInitUtility dungeonInitUtility) {
        levelMap = levelTileGenerationUtility.initLevel(size, dungeonInitUtility);
        int pos[] = getPos();
        checkTile(pos[0], pos[1]);
    }

    void buildMap() {
        ConstraintLayout map = ((Activity) mainUtility.getCon()).findViewById(R.id.mapDisp);

        //TODO remove only affected views
        map.removeAllViews();
        ConstraintSet set = new ConstraintSet();

        int mapLength = (int) (mainUtility.getScreenWidth() * 0.8);
        map.getLayoutParams().height = mapLength;
        map.getLayoutParams().width = mapLength;
        map.setX((int)(mainUtility.getScreenWidth() / 2.0 - map.getLayoutParams().width / 2.0));
        map.setY((int)(mainUtility.getScreenHeight() / 2.0 - map.getLayoutParams().height / 2.0));

        ImageView image;
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        int width = (int) Math.ceil((float) mapLength / levelMap.size());
        int height = (int) Math.ceil((float) mapLength / levelMap.size());

        for (int i = 0; i < levelMap.size(); i++) {
            for (int j = 0; j < levelMap.size(); j++) {

                if (getTile(i, j).getType() != LevelTile.WALL) {
                    image = new ImageView(mainUtility.getCon());
                    image.setId(View.generateViewId());
                    image.setImageResource(mainUtility.getCon().getResources().getIdentifier(getImageType(getTile(i, j)), "drawable", mainUtility.getCon().getPackageName()));
                    image.setLayoutParams(lp);
                    image.getLayoutParams().width = width;
                    image.getLayoutParams().height = height;
                    image.setX((int) (mapLength * ((float) i / levelMap.size())));
                    image.setY((int) (mapLength * ((float) j / levelMap.size())));
                    map.addView(image);
                }
            }
        }
        set.clone(map);
        set.applyTo(map);
    }

    private String getImageType(LevelTile levelTile) {
        if (levelTile.getType() == LevelTile.PLAYER_POS) {
            return LevelTile.PLAYER_POS_IMAGE;
        }

        switch (levelTile.getEvent()) {
            case LevelTile.ENEMY_EVENT:
                return LevelTile.ENEMY_EVENT_IMAGE;
            case LevelTile.ITEM_EVENT:
                return LevelTile.ITEM_EVENT_IMAGE;
            case LevelTile.END_POS:
                return LevelTile.END_POS_IMAGE;
        }

        switch (levelTile.getType()) {
            case LevelTile.EMPTY:
                return LevelTile.EMPTY_IMAGE;
            case LevelTile.WALL:
                return LevelTile.WALL_IMAGE;
            default:
                break;
        }

        return "";
    }

    void setPlayerPos(int col, int row) {

        if (getTile(col, row).getType() != LevelTile.WALL) {
            int pos[] = getPos();
            getTile(pos[0], pos[1]).setType(LevelTile.EMPTY);
            getTile(col, row).setType(LevelTile.PLAYER_POS);
            FileUtility.saveMap(mainUtility.getCon(), levelMap);
            checkTile(col, row);
        }
        //TODO fix array out of bounds when tapping too fast
        //TODO save player position with player info
    }

    int[] getPos() {

        int col = 1;
        int row = 1;

        for (int i = 0; i < levelMap.size(); i++) {
            for (int j = 0; j < levelMap.get(i).size(); j++) {
                if (levelMap.get(i).get(j).getType() == LevelTile.PLAYER_POS) {
                    col = i;
                    row = j;
                    break;
                }
            }
        }
        return new int[]{col, row};
    }

    private void checkTile(int col, int row) {

        if (getTile(col, row).getEvent() == LevelTile.END_POS) {
            Intent intent = new Intent(mainUtility.getCon(), ToEnemyEventActivity.class);
            mainUtility.getCon().startActivity(intent);
            ((Activity) mainUtility.getCon()).overridePendingTransition(R.anim.enter_enemy_tile_animation, R.anim.exit_enemy_tile_animation);
            ((Activity) mainUtility.getCon()).finish();
        }

        if (getTile(col, row).getEvent() == LevelTile.ENEMY_EVENT) {
            Intent intent = new Intent(mainUtility.getCon(), ToEnemyEventActivity.class);
            mainUtility.getCon().startActivity(intent);
            ((Activity) mainUtility.getCon()).overridePendingTransition(R.anim.enter_enemy_tile_animation, R.anim.exit_enemy_tile_animation);
            ((Activity) mainUtility.getCon()).finish();
        }

        if (getTile(col, row).getEvent() == LevelTile.ITEM_EVENT) {
            Intent intent = new Intent(mainUtility.getCon(), RandomEventActivity.class);
            mainUtility.getCon().startActivity(intent);
            ((Activity) mainUtility.getCon()).overridePendingTransition(R.anim.enter_default_animation, R.anim.exit_default_animation);
            ((Activity) mainUtility.getCon()).finish();
        }

    }

    public static int[] getPos(Context con) {
        ArrayList<ArrayList<LevelTile>> levelMap = FileUtility.loadMap(con);

        int col = 1;
        int row = 1;

        for (int i = 0; i < levelMap.size(); i++) {
            for (int j = 0; j < levelMap.get(i).size(); j++) {
                if (levelMap.get(i).get(j).getType() == LevelTile.PLAYER_POS) {
                    col = i;
                    row = j;
                    break;
                }
            }
        }
        return new int[]{col, row};
    }

    LevelTile getTile(int col, int row) {
        return levelMap.get(col).get(row);
    }
}
