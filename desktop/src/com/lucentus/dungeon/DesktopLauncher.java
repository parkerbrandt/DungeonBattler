package com.lucentus.dungeon;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.lucentus.dungeon.DungeonBattler;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	/*
	 * Static Class Members
	 */
	public static final int WINDOW_HEIGHT = 1080;
	public static final int WINDOW_WIDTH = 1920;


	/**
	 * Start of Desktop Program Logic
	 * @param arg unused
	 *
	 * TODO: Add in settings file and read
	 */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(WINDOW_WIDTH, WINDOW_HEIGHT);
		config.setForegroundFPS(60);
		config.setTitle("DungeonBattler");
		config.useVsync(true);
		new Lwjgl3Application(new DungeonBattler(), config);
	}
}
