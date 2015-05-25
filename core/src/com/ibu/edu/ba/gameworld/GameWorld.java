package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */

import com.ibu.edu.ba.gameobjects.Goku;
import com.ibu.edu.ba.gameobjects.ScrollHandler;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class GameWorld {

    private Goku goku;
    private ScrollHandler scroller;
    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        goku = new Goku(24, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        goku.update(delta);
        scroller.update(delta);

        if (scroller.collides(goku) && isAlive) {
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }
    }

    public Goku getGoku() {
        return goku;

    }
    public ScrollHandler getScroller() {
        return scroller;
    }
}