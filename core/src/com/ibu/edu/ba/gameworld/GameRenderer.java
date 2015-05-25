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
import com.ibu.edu.ba.gameobjects.Grass;
import com.ibu.edu.ba.gameobjects.Pipe;
import com.ibu.edu.ba.gameobjects.ScrollHandler;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;
    private int gameWidth;

    //Game objects
    private Goku goku;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3, pipe4;

    //Game assets
    private TextureRegion myBg, grass;
    private Animation gokuAnimation;
    private TextureRegion gF1, gF2, gF3, gF4;
    private TextureRegion skullUp, skullDown, bar;

    public GameRenderer(GameWorld world, int gameWidth, int gameHeight, int midPointY) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.midPointY = midPointY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, gameWidth, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        // Call helper methods to initialize instance variables
        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        goku = myWorld.getGoku();
        scroller = myWorld.getScroller();
        frontGrass = scroller.getFrontGrass();
        backGrass = scroller.getBackGrass();
        pipe1 = scroller.getPipe1();
        pipe2 = scroller.getPipe2();
        pipe3 = scroller.getPipe3();
        pipe4 = scroller.getPipe4();
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

    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        // Temporary code! Sorry about the mess :)
        // I will fix this when I finish the Pipe class.

        batcher.draw(skullUp, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe1.getX() - 1,
                pipe1.getY() + pipe1.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe2.getX() - 1,
                pipe2.getY() + pipe2.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe3.getX() - 1,
                pipe3.getY() + pipe3.getHeight() + 45, 24, 14);

        batcher.draw(skullUp, pipe4.getX() - 1,
                pipe4.getY() + pipe4.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, pipe4.getX() - 1,
                pipe4.getY() + pipe4.getHeight() + 45, 24, 14);
    }

    private void drawPipes() {
        // Temporary code! Sorry about the mess :)
        // I will fix this when I finish the Pipe class.
        batcher.draw(bar, pipe1.getX(), pipe1.getY(), pipe1.getWidth(),
                pipe1.getHeight());
        batcher.draw(bar, pipe1.getX(), pipe1.getY() + pipe1.getHeight() + 45,
                pipe1.getWidth(), midPointY + 66 - (pipe1.getHeight() + 45));

        batcher.draw(bar, pipe2.getX(), pipe2.getY(), pipe2.getWidth(),
                pipe2.getHeight());
        batcher.draw(bar, pipe2.getX(), pipe2.getY() + pipe2.getHeight() + 45,
                pipe2.getWidth(), midPointY + 66 - (pipe2.getHeight() + 45));

        batcher.draw(bar, pipe3.getX(), pipe3.getY(), pipe3.getWidth(),
                pipe3.getHeight());
        batcher.draw(bar, pipe3.getX(), pipe3.getY() + pipe3.getHeight() + 45,
                pipe3.getWidth(), midPointY + 66 - (pipe3.getHeight() + 45));

        batcher.draw(bar, pipe4.getX(), pipe4.getY(), pipe4.getWidth(),
                pipe4.getHeight());
        batcher.draw(bar, pipe4.getX(), pipe4.getY() + pipe4.getHeight() + 45,
                pipe4.getWidth(), midPointY + 66 - (pipe4.getHeight() + 45));
    }

    public void render(float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);

        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 300, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 300, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(myBg, 0, 0, 140, 100);

        drawGrass();

        drawPipes();
        batcher.enableBlending();

        drawSkulls();

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
}