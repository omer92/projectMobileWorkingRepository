package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.ibu.edu.ba.gameobjects.Goku;
import com.ibu.edu.ba.gameobjects.ScrollHandler;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class GameWorld {

    private Goku goku;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;

    private int midPointY;

    private GameState currentState;

    public enum GameState {
        READY, RUNNING, GAMEOVER;
    }

    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        this.midPointY = midPointY;
        goku = new Goku(43, midPointY - 5, 17, 12);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;

        case RUNNING:
        default:
        updateRunning(delta);
        break;
        }
    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }
        goku.update(delta);
        scroller.update(delta);

        if (scroller.collides(goku) && goku.isAlive()) {
            scroller.stop();
            goku.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(goku.getBoundingCircle(), ground)) {
            scroller.stop();
            goku.die();
            goku.decelerate();
        }
    }

    public Goku getGoku() {
        return goku;
    }
    public ScrollHandler getScroller() {
        return scroller;
    }
    public int getScore() {
        return score;
    }
    public void addScore(int increment) {
        score += increment;
    }
    public boolean isReady() {
        return currentState == GameState.READY;
    }
    public void start() {
         this.currentState = GameState.RUNNING;
    }
    public void restart() {
        currentState = GameState.READY;
        score = 0;
        goku.onRestart(midPointY -5);
        scroller.onRestart();
        currentState = GameState.READY;
    }
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}