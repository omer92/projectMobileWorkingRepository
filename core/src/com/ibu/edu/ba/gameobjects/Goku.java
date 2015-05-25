package com.ibu.edu.ba.gameobjects;

/**
 * Created by Omer on 6.5.2015.
 */

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


public class Goku {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // For handling goku rotation
    private int width;
    private int height;

    private Circle boundingCircle;

    public Goku(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 360);
        boundingCircle = new Circle();
    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 100) {
            velocity.y = 100;
        }

        position.add(velocity.cpy().scl(delta));

        // Setting the circle
        boundingCircle.set(position.x + 9, position.y + 6, 6.5f);

        // Rotate counterclockwise
        if (velocity.y < 0) {
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }

        // Rotate clockwise
        if (isFalling()) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }

        }

    }



    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFly() {
        return velocity.y > 70;
    }

    public void onClick() {
        velocity.y = -100;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public Circle getBoundingCircle() { return boundingCircle; }

}