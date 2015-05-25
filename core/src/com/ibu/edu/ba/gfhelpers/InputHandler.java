package com.ibu.edu.ba.gfhelpers;

/**
 * Created by Omer on 6.5.2015.
 */


import com.badlogic.gdx.InputProcessor;
import com.ibu.edu.ba.gameobjects.Goku;
import com.ibu.edu.ba.gameworld.GameWorld;


public class InputHandler implements InputProcessor  {

    private Goku myGoku;
    private GameWorld myWorld;

    // Ask for a reference to the Goku when InputHandler is created.
    public InputHandler(GameWorld myWorld) {
        // myGoku now represents the gameWorld's goku.
        this.myWorld = myWorld;
        myGoku = myWorld.getGoku();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (myWorld.isReady()) {
            myWorld.start();
        }

        myGoku.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READY
            myWorld.restart();
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
