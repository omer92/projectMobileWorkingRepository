package com.ibu.edu.ba.gfhelpers;

/**
 * Created by Omer on 6.5.2015.
 */

import com.badlogic.gdx.InputProcessor;
import com.ibu.edu.ba.gameobjects.Goku;

public class InputHandler implements InputProcessor {

    private Goku myGoku;

    // Ask for a reference to the Goku when InputHandler is created.
    public InputHandler(Goku goku) {
        // myGoku now represents the gameWorld's goku.
        myGoku = goku;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myGoku.onClick();
        return true; // Return true to say we handled the touch.
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
