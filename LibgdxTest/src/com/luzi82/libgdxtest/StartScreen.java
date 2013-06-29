package com.luzi82.libgdxtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StartScreen implements Screen {

	public LTGame ltGame;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	public StartScreen(LTGame ltGame) {
		this.ltGame = ltGame;
	}

	@Override
	public void resume() {
		alloc();
	}

	@Override
	public void dispose() {
		free();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		free();
	}

	// @Override
	// public void resume() {
	// }

	// @Override
	// public void render(float delta) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		alloc();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		free();
	}

	boolean mAllocDone = false;

	public void alloc() {
		if (mAllocDone)
			return;

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		mAllocDone = true;
	}

	public void free() {
		if (!mAllocDone)
			return;
		batch.dispose();
		texture.dispose();
		camera = null;
		batch = null;
		texture = null;
		sprite = null;

		mAllocDone = false;
	}
}
