package com.luzi82.libgdxtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class LTScreen implements Screen {

	@Override
	public abstract void render(float delta);

	int w = -1;
	int h = -1;

	@Override
	public void resize(int width, int height) {
		System.err.println("resize");
		if (!mAllocDone)
			return;
		if ((width == w) && (height == h))
			return;
		System.err.println("w=" + width + " h" + height);
		w = width;
		h = height;
		System.err.println("free");
		free();
		System.err.println("alloc");
		alloc();
	}

	@Override
	public void show() {
		System.err.println("show");
		_alloc();
	}

	@Override
	public void hide() {
		System.err.println("hide");
		_free();
	}

	@Override
	public void pause() {
		System.err.println("pause");
		_free();
	}

	@Override
	public void resume() {
		System.err.println("resume");
		_alloc();
	}

	@Override
	public void dispose() {
		System.err.println("dispose");
		_free();
	}

	private boolean mAllocDone = false;

	private void _alloc() {
		if (mAllocDone)
			return;
		System.err.println("alloc");
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		alloc();
		mAllocDone = true;
	}

	private void _free() {
		if (!mAllocDone)
			return;
		System.err.println("free");
		w = -1;
		h = -1;
		free();
		mAllocDone = false;
	}

	protected abstract void alloc();

	protected abstract void free();

}
