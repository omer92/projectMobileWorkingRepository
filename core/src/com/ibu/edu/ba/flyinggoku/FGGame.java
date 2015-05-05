package com.ibu.edu.ba.flyinggoku;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.ibu.edu.ba.screens.GameScreen;

public class FGGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("ZBGame", "created");
        setScreen(new GameScreen());
    }
}