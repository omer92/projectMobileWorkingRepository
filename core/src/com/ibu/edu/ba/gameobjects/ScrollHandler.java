package com.ibu.edu.ba.gameobjects;

/**
 * Created by Omer on 11.5.2015.
 */
import com.ibu.edu.ba.gameworld.GameWorld;
import com.ibu.edu.ba.gfhelpers.AssetLoader;

public class ScrollHandler {

    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3, pipe4;
    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 39;

    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        frontGrass = new Grass(0, yPos, 300, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 300, 11,
                SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED,
                yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,
                yPos);
        pipe4 = new Pipe(pipe3.getTailX() + PIPE_GAP, 0, 22, 50, SCROLL_SPEED,
                yPos);
    }

    public void update(float delta) {
        // Update our objects
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);
        pipe4.update(delta);

        // Check if any of the pipes are scrolled left,
        // and reset accordingly
        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe4.getTailX() + PIPE_GAP);

        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);

        }else if (pipe4.isScrolledLeft()){
            pipe4.reset((pipe3.getTailX() + PIPE_GAP));
        }

        // Same with grass
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());

        }
    }

    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
        pipe4.stop();
    }

    public boolean collides(Goku goku) {

        if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < goku.getX()
                    + goku.getWidth()) {
            addScore(1);
            pipe1.setScored(true);
            AssetLoader.coin.play();
        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < goku.getX()
            + goku.getWidth()) {
            addScore(1);
            pipe2.setScored(true);
            AssetLoader.coin.play();
        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < goku.getX()
            + goku.getWidth()) {
            addScore(1);
            pipe3.setScored(true);
            AssetLoader.coin.play();
        }  else if (!pipe4.isScored()
                && pipe4.getX() + (pipe4.getWidth() / 2) < goku.getX()
            + goku.getWidth()) {
            addScore(1);
            pipe4.setScored(true);
            AssetLoader.coin.play();
        }

        return (pipe1.collides(goku) || pipe2.collides(goku) || pipe3
                .collides(goku) || pipe4.collides(goku));
    }
    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

    public Pipe getPipe4() { return pipe4; }

}