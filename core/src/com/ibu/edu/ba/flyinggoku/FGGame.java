package com.ibu.edu.ba.flyinggoku;

import com.badlogic.gdx.Game;
import com.ibu.edu.ba.screens.SplashScreen;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class FGGame extends Game {

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose(){
        super.dispose();
        AssetLoader.dispose();
    }
}