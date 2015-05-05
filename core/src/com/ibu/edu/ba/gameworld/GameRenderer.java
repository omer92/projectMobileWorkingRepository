package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */
import com.badlogic.gdx.Gdx;

public class GameRenderer {

    private GameWorld myWorld;

    public GameRenderer(GameWorld world){
        myWorld = world;
    }

    public void render() {
        Gdx.app.log("GameRenderer", "render");
    }
}