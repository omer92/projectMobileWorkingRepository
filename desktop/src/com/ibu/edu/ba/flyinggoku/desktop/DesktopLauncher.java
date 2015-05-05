package com.ibu.edu.ba.flyinggoku.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ibu.edu.ba.flyinggoku.FGGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Flying Goku";
        config.width = 272;
        config.height = 408;
		new LwjglApplication(new FGGame(), config);
	}
}
