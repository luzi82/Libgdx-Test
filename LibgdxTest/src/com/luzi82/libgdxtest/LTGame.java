package com.luzi82.libgdxtest;

import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class LTGame extends Game {

	Screen[] mScreenAry;
	int mCurrentScreenIdx = 0;

	// StartScreen mStartScreen;

	@Override
	public void create() {
		LinkedList<Screen> screenList = new LinkedList<Screen>();

		screenList.add(new StartScreen(this));

		mScreenAry = screenList.toArray(new Screen[0]);

		setScreen(mScreenAry[mCurrentScreenIdx]);
	}

	public void next() {
		++mCurrentScreenIdx;
		mCurrentScreenIdx %= mScreenAry.length;
		setScreen(mScreenAry[mCurrentScreenIdx]);
	}

}
