package com.luzi82.libgdxtest;

import com.badlogic.gdx.Game;

public class LTGame extends Game {

	StartScreen mStartScreen;

	@Override
	public void create() {
		mStartScreen = new StartScreen();

		setScreen(mStartScreen);
	}

}
