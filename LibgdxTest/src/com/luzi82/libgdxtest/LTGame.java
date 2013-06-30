package com.luzi82.libgdxtest;

import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class LTGame extends Game implements InputProcessor {

	Screen[] mScreenAry;
	int mCurrentScreenIdx = 0;

	// StartScreen mStartScreen;

	@Override
	public void create() {
		LinkedList<Screen> screenList = new LinkedList<Screen>();

		screenList.add(new StartScreen());
		screenList.add(new TextScreen());
		screenList.add(new SpriteCacheScreen());
		screenList.add(new AnimationSpriteScreen());

		mScreenAry = screenList.toArray(new Screen[0]);

		setScreen(mScreenAry[mCurrentScreenIdx]);

		Gdx.input.setInputProcessor(this);
	}

	public void next() {
		++mCurrentScreenIdx;
		mCurrentScreenIdx %= mScreenAry.length;
		setScreen(mScreenAry[mCurrentScreenIdx]);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		next();
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
