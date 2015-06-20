package com.ibu.edu.ba.gameobjects;

/**
 * Created by Omer on 11.5.2015.
 */
public class Grass extends Scrollable {

    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }

}