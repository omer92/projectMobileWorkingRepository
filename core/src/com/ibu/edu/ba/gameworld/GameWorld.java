package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */

import com.ibu.edu.ba.gameobjects.Goku;

public class GameWorld {
    private Goku goku;

    public GameWorld(int midPointY) {
        goku = new Goku(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        goku.update(delta);
    }

    public Goku getGoku() {
        return goku;

    }
}