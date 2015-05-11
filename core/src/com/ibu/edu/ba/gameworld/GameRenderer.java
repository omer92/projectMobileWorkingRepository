package com.ibu.edu.ba.gameworld;

/**
 * Created by Omer on 6.5.2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ibu.edu.ba.gameobjects.Goku;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    private Goku goku;

    private TextureRegion myBg, grass;
    private Animation gokuAnimation;
    private TextureRegion gF1, gF2, gF3, gF4;
    private TextureRegion skullUp, skullDown, bar;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        //Draw Background color
        //shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        //shapeRenderer.rect(0, 0, 136, midPointY + 66);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);

        // Draw Dirt
        //shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        //shapeRenderer.rect(0, midPointY + 77, 136, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(myBg, 0, 0, 140, 100);

        batcher.enableBlending();

        if (goku.shouldntFly()) {
            batcher.draw(gF2, goku.getX(), goku.getY(),
                    goku.getWidth() / 2.0f, goku.getHeight() / 2.0f,
                    goku.getWidth(), goku.getHeight(), 1, 1, goku.getRotation());

        } else {
            batcher.draw(gokuAnimation.getKeyFrame(runTime), goku.getX(),
                    goku.getY(), goku.getWidth() / 2.0f,
                    goku.getHeight() / 2.0f, goku.getWidth(), goku.getHeight(),
                    1, 1, goku.getRotation());
        }

        batcher.end();

    }

    private void initGameObjects() {
        goku = myWorld.getGoku();
    }

    private void initAssets() {
        myBg = AssetLoader.myBg;
        grass = AssetLoader.grass;
        gokuAnimation = AssetLoader.gokuAnimation;
        gF1 = AssetLoader.gF1;
        gF2 = AssetLoader.gF2;
        gF3 = AssetLoader.gF3;
        gF4 = AssetLoader.gF4;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }
}