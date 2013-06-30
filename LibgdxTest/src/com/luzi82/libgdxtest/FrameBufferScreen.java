package com.luzi82.libgdxtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class FrameBufferScreen extends LTScreen {

	private SpriteBatch batch;
	private OrthographicCamera screenCamera;
	private Texture texture;
	private Sprite sprite;
	private FrameBuffer frameBuffer;
	private Sprite frameBufferSprite;
	private OrthographicCamera frameBufferCamera;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		sprite.rotate(delta * 30);
		frameBufferSprite.rotate(-delta * 10);

		frameBuffer.begin();
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(frameBufferCamera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
		frameBuffer.end();

		batch.setProjectionMatrix(screenCamera.combined);
		batch.begin();
		frameBufferSprite.draw(batch);
		batch.end();
	}

	public void alloc() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		batch = new SpriteBatch();

		screenCamera = new OrthographicCamera(w, h);

		frameBuffer = new FrameBuffer(Format.RGBA8888, 512, 512, false);
		frameBufferCamera = new OrthographicCamera(frameBuffer.getWidth(),
				frameBuffer.getHeight());

		frameBufferSprite = new Sprite(frameBuffer.getColorBufferTexture());
		frameBufferSprite.flip(false, true);
		frameBufferSprite.setSize(256, 256);
		frameBufferSprite.setOrigin(frameBufferSprite.getWidth() / 2,
				frameBufferSprite.getHeight() / 2);
		frameBufferSprite.setPosition(-frameBufferSprite.getWidth() / 2,
				-frameBufferSprite.getHeight() / 2);

		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

		sprite = new Sprite(region);
		sprite.setSize(frameBuffer.getWidth(), frameBuffer.getHeight());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		// frameBufferSprite.setOrigin(frameBufferSprite.getWidth() / 2,
		// frameBufferSprite.getHeight() / 2);
	}

	public void free() {
		batch.dispose();
		batch = null;
		texture.dispose();
		texture = null;
		frameBuffer.dispose();
		frameBuffer = null;
		sprite = null;
	}
}
