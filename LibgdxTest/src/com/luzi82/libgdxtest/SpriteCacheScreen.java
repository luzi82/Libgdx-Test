package com.luzi82.libgdxtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteCacheScreen extends LTScreen {

	private OrthographicCamera camera;
	// private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private SpriteCache spriteCache;
	int cacheId;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteCache.setProjectionMatrix(camera.combined);
		spriteCache.begin();
		spriteCache.draw(cacheId);
		spriteCache.end();
		// batch.setProjectionMatrix(camera.combined);
		// batch.begin();
		// sprite.draw(batch);
		// batch.end();
	}

	public void alloc() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h / w);
		// batch = new SpriteBatch();
		spriteCache = new SpriteCache();

		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);

		sprite = new Sprite(region);
		sprite.setSize(0.8f, 0.8f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		spriteCache.beginCache();
		spriteCache.add(sprite);
		cacheId = spriteCache.endCache();
	}

	public void free() {
		spriteCache.dispose();
		spriteCache = null;
		texture.dispose();
		texture = null;
		camera = null;
		sprite = null;
		cacheId = -1;
	}
}
