package com.ibu.edu.ba.gfhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture background;
    public static Texture texture;

    // Goku flying state still pictures

    public static Texture gokuFlying1;
    public static Texture gokuFlying2;
    public static Texture gokuFlying3;
    public static Texture gokuFlying4;


    public static TextureRegion myBg, grass;

    public static Animation gokuAnimation;
    public static TextureRegion gF1, gF2, gF3, gF4;

    public static TextureRegion skullUp, skullDown, bar;

    public static Sound dead;
    public static Sound theme;

    public static void load() {

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        background = new Texture(Gdx.files.internal("data/background.jpg"));
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);


        myBg = new TextureRegion(background, 0, 0,640, 480);
        myBg.flip(false, true);


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

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);



        TextureRegion[] gokuState = { gF1, gF2, gF3, gF4 };
        gokuAnimation = new Animation(0.06f, gokuState);
        gokuAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);

        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        theme = Gdx.audio.newSound(Gdx.files.internal("data/theme.wav"));

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        background.dispose();
        gokuFlying1.dispose();
        gokuFlying2.dispose();
        gokuFlying3.dispose();
        gokuFlying4.dispose();

    }

}
