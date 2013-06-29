package com.luzi82.libgdxtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextScreen extends LTScreen {

	private SpriteBatch batch;

	private BitmapFont font;
	private BitmapFontCache fontCache;

	@Override
	protected void alloc() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		font = new BitmapFont();
		fontCache = new BitmapFontCache(font);
		fontCache.setColor(0, 0, 0, 1);
		fontCache.setText("HelloWorld", w / 2, h / 2);
	}

	@Override
	protected void free() {
		batch.dispose();
		font.dispose();
		fontCache.dispose();
		batch = null;
		font = null;
		fontCache = null;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		fontCache.draw(batch);
		batch.end();
	}

}
