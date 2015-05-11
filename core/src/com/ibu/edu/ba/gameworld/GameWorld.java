package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */

import com.ibu.edu.ba.gameobjects.Goku;
import com.ibu.edu.ba.gameobjects.ScrollHandler;

public class GameWorld {

    private Goku goku;
    private ScrollHandler scroller;

    public GameWorld(int midPointY) {
        goku = new Goku(18, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta) {
        goku.update(delta);
        scroller.update(delta);
    }

    public Goku getGoku() {
        return goku;

    }
    public ScrollHandler getScroller() {
        return scroller;
    }
}