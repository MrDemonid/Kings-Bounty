package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameLogger;
import com.mygdx.game.Map;
import com.mygdx.game.MapRender;

public class GameScreen extends BaseScreen{

    static final int MAP_WIDTH = 16;
    static final int MAP_HEIGHT = 16;

    Map map;
    MapRender render;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        map = new Map(MAP_WIDTH, MAP_HEIGHT);
        render = new MapRender(map);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1.0f);
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        map.update(delta);
        render.render();
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        render.resize(width, height);
    }

    @Override
    public void dispose() {
        render.dispose();
    }
}
