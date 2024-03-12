package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.BountyGame;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) throws UnsupportedEncodingException {
//		GameLogger.init(DesktopLauncher.class.getName());
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(512, 512);
		config.useVsync(true);
		config.setForegroundFPS(20);
		config.setTitle("Kings-Bounty");
		new Lwjgl3Application(new BountyGame(), config);
	}
}