package com.ibu.edu.ba.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.ibu.edu.ba.TweenAccessors.Value;
import com.ibu.edu.ba.TweenAccessors.ValueAccessor;
import com.ibu.edu.ba.gfhelpers.AssetLoader;
import com.ibu.edu.ba.gfhelpers.InputHandler;
import com.ibu.edu.ba.ui.SimpleButton;

public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private int midPointY;

    //Game objects
    private Goku goku;
    private ScrollHandler scroller;
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3, pipe4;

    //Game assets
    private TextureRegion myBg, grass, gF2, skullUp, skullDown, bar, ready,
            myTitle, gameOver, highScore, scoreboard, star, noStar, retry;
    private Animation gokuAnimation;

    // Tween stuff
    private TweenManager manager;
    private Value alpha = new Value();

    // Buttons
    private List<SimpleButton> menuButtons;
    private Color transitionColor;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        myWorld = world;

        this.midPointY = midPointY;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 220, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();

        transitionColor = new Color();
        prepareTransition(255, 255, 255, .5f);

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
        gF2 = AssetLoader.gF2;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
        ready = AssetLoader.ready;
        myTitle = AssetLoader.myTitle;
        gameOver = AssetLoader.gameOver;
        highScore = AssetLoader.highScore;
        scoreboard = AssetLoader.scoreboard;
        retry = AssetLoader.retry;
        star = AssetLoader.star;
        noStar = AssetLoader.noStar;
    }

    private void drawGrass() {
        // Draw the grass
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {

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

    private void drawGokuCentered(float runTime) {
        batcher.draw(gokuAnimation.getKeyFrame(runTime), 59, goku.getY() - 15,
                goku.getWidth() / 2.0f, goku.getHeight() / 2.0f,
                goku.getWidth(), goku.getHeight(), 1, 1, goku.getRotation());
    }

    private void drawGoku(float runTime) {

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

    }

    private void drawMenuUI() {
        batcher.draw(myTitle, 136 / 2, midPointY - 80,
                myTitle.getRegionWidth() / 1.2f, myTitle.getRegionHeight() / 1.2f);


        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }

    }

    private void drawScoreboard() {
        batcher.draw(scoreboard, 62, midPointY - 30, 97, 37);

        batcher.draw(noStar, 65, midPointY - 15, 10, 10);
        batcher.draw(noStar, 77, midPointY - 15, 10, 10);
        batcher.draw(noStar, 89, midPointY - 15, 10, 10);
        batcher.draw(noStar, 101, midPointY - 15, 10, 10);
        batcher.draw(noStar, 113, midPointY - 15, 10, 10);

        if (myWorld.getScore() > 2) {
            batcher.draw(star, 113, midPointY - 15, 10, 10);
        }

        if (myWorld.getScore() > 17) {
            batcher.draw(star, 101, midPointY - 15, 10, 10);
        }

        if (myWorld.getScore() > 50) {
            batcher.draw(star, 89, midPointY - 15, 10, 10);
        }

        if (myWorld.getScore() > 80) {
            batcher.draw(star, 77, midPointY - 15, 10, 10);
        }

        if (myWorld.getScore() > 120) {
            batcher.draw(star, 65, midPointY - 15, 10, 10);
        }

        int length = ("" + myWorld.getScore()).length();

        AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(),
                145 - (2 * length), midPointY - 20);

        int length2 = ("" + AssetLoader.getHighScore()).length();
        AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
                145 - (2.5f * length2), midPointY - 3);

    }

    private void drawRetry() {
        batcher.draw(retry, 80, midPointY + 10, 66, 14);
    }

    private void drawReady() {
        batcher.draw(ready, 80, midPointY - 50, 68, 14);
    }

    private void drawGameOver() {
        batcher.draw(gameOver, 65, midPointY - 50, 92, 14);
    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                110 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                110 - (3 * length), midPointY - 83);
    }

    private void drawHighScore() {
        batcher.draw(highScore, 80, midPointY - 50, 96, 14);
    }


    public void render(float delta, float runTime) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);


        // Draw Grass
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 220, 11);

        // Draw Dirt
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 220, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(myBg, 0, 0, 220, 170);

        drawPipes();

        batcher.enableBlending();
        drawSkulls();

        if (myWorld.isRunning()) {
            drawGoku(runTime);
            drawScore();
        } else if (myWorld.isReady()) {
            drawGoku(runTime);
            drawReady();
        } else if (myWorld.isMenu()) {
            drawGokuCentered(runTime);
            drawMenuUI();
        } else if (myWorld.isGameOver()) {
            drawScoreboard();
            drawGoku(runTime);
            drawGameOver();
            drawRetry();
        } else if (myWorld.isHighScore()) {
            drawScoreboard();
            drawGoku(runTime);
            drawHighScore();
            drawRetry();
        }

        drawGrass();

        batcher.end();
        drawTransition(delta);

    }

    public void prepareTransition(int r, int g, int b, float duration) {
        transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
        alpha.setValue(1);
        Tween.registerAccessor(Value.class, new ValueAccessor());
        manager = new TweenManager();
        Tween.to(alpha, -1, duration).target(0)
                .ease(TweenEquations.easeOutQuad).start(manager);
    }

    private void drawTransition(float delta) {
        if (alpha.getValue() > 0) {
            manager.update(delta);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(transitionColor.r, transitionColor.g,
                    transitionColor.b, alpha.getValue());
            shapeRenderer.rect(0, 0, 220, 300);
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);

        }
    }

}