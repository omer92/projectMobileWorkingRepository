package com.ibu.edu.ba.gameworld;

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
    private float runTime = 0;
    private int midPointY;
    private GameRenderer renderer;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY) {
        currentState = GameState.MENU;
        this.midPointY = midPointY;
        goku = new Goku(43, midPointY - 5, 22, 17);
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

        case RUNNING:
            updateRunning(delta);
            break;
        default:
                break;
        }
    }

    private void updateReady(float delta) {
        goku.updateReady(runTime);
        scroller.updateReady(delta);
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
            renderer.prepareTransition(255, 255, 255, .3f);

        }

        if (Intersector.overlaps(goku.getBoundingCircle(), ground)) {
            if (goku.isAlive()) {
                AssetLoader.dead.play();
                renderer.prepareTransition(255, 255, 255, .3f);

                goku.die();
            }
            scroller.stop();
            goku.decelerate();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
                AssetLoader.ThatWasPrityFun.play();
            }
        }

    }


    public Goku getGoku() {
        return goku;
    }

    public int getMidPointY() {
        return midPointY;
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

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void ready() {
        currentState = GameState.READY;
        renderer.prepareTransition(0, 0, 0, 1f);
    }

    public void restart() {
        score = 0;
        goku.onRestart(midPointY - 5);
        scroller.onRestart();
        ready();
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void setRenderer(GameRenderer renderer) {
        this.renderer = renderer;
    }
}