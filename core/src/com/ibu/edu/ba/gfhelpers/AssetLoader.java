package com.ibu.edu.ba.gfhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture background;

    // Goku flying state still pictures

    public static Texture gokuFlying1;
    public static Texture gokuFlying2;
    public static Texture gokuFlying3;
    public static Texture gokuFlying4;
    public static Texture texture, logoTexture;

    public static TextureRegion Mylogo, myTitle, myBg, grass,
            skullUp, skullDown, bar, playButtonUp, playButtonDown,
            ready, gameOver, highScore, scoreboard, star, noStar, retry;
    public static TextureRegion gF1, gF2, gF3, gF4;


    public static Animation gokuAnimation;


    public static Sound dead, fly, coin, fall, ReadySetGo, RightLetsDoIt, ThatWasPrityFun;
    public static Music theme;

    public static BitmapFont font, shadow, whiteFont;

    private static Preferences prefs;

    public static void load() {

        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        Mylogo = new TextureRegion(logoTexture, 0, 0, 861, 240);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        ready = new TextureRegion(texture, 59, 83, 34, 7);
        ready.flip(false, true);

        retry = new TextureRegion(texture, 59, 110, 33, 7);
        retry.flip(false, true);

        gameOver = new TextureRegion(texture, 59, 92, 46, 7);
        gameOver.flip(false, true);

        scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
        scoreboard.flip(false, true);

        star = new TextureRegion(texture, 152, 70, 10, 10);
        noStar = new TextureRegion(texture, 165, 70, 10, 10);

        star.flip(false, true);
        noStar.flip(false, true);

        highScore = new TextureRegion(texture, 59, 101, 48, 7);
        highScore.flip(false, true);

        myTitle = new TextureRegion(texture, 0, 55, 145, 26);
        myTitle.flip(false, true);

        background = new Texture(Gdx.files.internal("data/background.jpg"));
        background.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        myBg = new TextureRegion(background, 0, 0, 640, 480);
        myBg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        gokuFlying1 = new Texture(Gdx.files.internal("data/goku1.png"));
        gokuFlying1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        gF1 = new TextureRegion(gokuFlying1, 0, 0, 81, 62);
        gF1.flip(false, true);

        gokuFlying2 = new Texture(Gdx.files.internal("data/goku2.png"));
        gokuFlying2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        gF2 = new TextureRegion(gokuFlying2, 0, 0, 81, 62);
        gF2.flip(false, true);

        gokuFlying3 = new Texture(Gdx.files.internal("data/goku3.png"));
        gokuFlying3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        gF3 = new TextureRegion(gokuFlying3, 0, 0, 81, 62);
        gF3.flip(false, true);

        gokuFlying4 = new Texture(Gdx.files.internal("data/goku4.png"));
        gokuFlying4.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        gF4 = new TextureRegion(gokuFlying4, 0, 0, 81, 62);
        gF4.flip(false, true);

        TextureRegion[] gokuState = {gF1, gF2, gF3, gF4};
        gokuAnimation = new Animation(0.06f, gokuState);
        gokuAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        theme = Gdx.audio.newMusic(Gdx.files.internal("data/theme.wav"));
        theme.play();
        theme.setLooping(true);
        theme.setVolume(0.5f);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        fly = Gdx.audio.newSound(Gdx.files.internal("data/fly.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        fall = Gdx.audio.newSound(Gdx.files.internal("data/fall.wav"));
        ReadySetGo = Gdx.audio.newSound(Gdx.files.internal("data/ReadySetGo.wav"));
        RightLetsDoIt = Gdx.audio.newSound(Gdx.files.internal("data/RightLetsDoIt.wav"));
        ThatWasPrityFun = Gdx.audio.newSound(Gdx.files.internal("data/ThatWasPrityFun.wav"));


        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);

        whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
        whiteFont.getData().setScale(.1f, -.1f);

        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Create (or retrieve exciting) preferences file
        prefs = Gdx.app.getPreferences("FlyingGoku");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        background.dispose();
        texture.dispose();
        gokuFlying1.dispose();
        gokuFlying2.dispose();
        gokuFlying3.dispose();
        gokuFlying4.dispose();


        theme.dispose();

        dead.dispose();
        fly.dispose();
        coin.dispose();
        RightLetsDoIt.dispose();
        ReadySetGo.dispose();
        ThatWasPrityFun.dispose();

        font.dispose();
        shadow.dispose();
    }

}
