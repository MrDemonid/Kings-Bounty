
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class MapRender {

    private static final int MAP_TILE_WIDTH = 32;
    private static final int MAP_TILE_HEIGHT = 32;
    
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private final SpriteBatch batch;

    private final TextureRegion[] map_tiles;

    private final TextureRegion txPeasant;
    private final TextureRegion txCrossbowman;
    private final TextureRegion txSniper;
    private final TextureRegion txRobber;
    private final TextureRegion txSpearman;
    private final TextureRegion txWizard;
    private final TextureRegion txMonk;
    

    Map map;

    public MapRender(Map map) {

        this.map = map;
        map.makeMap();
        batch = new SpriteBatch();
        font = new BitmapFont();
        // настраиваем камеру и размер карты
        camera = new OrthographicCamera();
        camera.setToOrtho(false, map.getWidth()*MAP_TILE_WIDTH, map.getHeight()*MAP_TILE_HEIGHT);
        camera.update();
        
        // подгружаем текстуры
        Texture texture = new Texture(Gdx.files.internal("tiles.png"));
        TextureRegion[][] tiles = TextureRegion.split(texture, 64, 64);
        map_tiles = new TextureRegion[5];
        System.arraycopy(tiles[0], 0, map_tiles, 0, 5);
        txPeasant       = new TextureRegion(tiles[1][0]);
        txCrossbowman   = new TextureRegion(tiles[1][1]);
        txSniper        = new TextureRegion(tiles[1][2]);
        txRobber        = new TextureRegion(tiles[1][3]);
        txSpearman      = new TextureRegion(tiles[1][4]);
        txMonk          = new TextureRegion(tiles[1][5]);
        txWizard        = new TextureRegion(tiles[1][6]);

    }


    public void render()
    {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();

        renderMap();
        
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 8, 20);
        batch.end();
    }

    private void renderMap()
    {

        for (int y = 0, yy = map.getHeight()-1; y < map.getHeight(); y++, yy--)
        {
            for (int x = 0; x < map.getWidth(); x++)
            {
                int tile = map.getTile(x, yy);
                if (tile >= 0 && tile < map_tiles.length)
                    batch.draw(map_tiles[tile], x*MAP_TILE_WIDTH, y*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
                else
                    batch.draw(map_tiles[0], x*MAP_TILE_WIDTH, y*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
            }
        }
    }

    /**
     * Изменение размеров окна
     * @param width не существенно, мы ставим свою ширину
     * @param height не существенно, мы ставим свою ширину
     */
    public void resize(int width, int height)
    {
//        GameLogger.info(String.format("render.resize(%d, %d)", width, height));
        camera.viewportWidth = map.getWidth()*MAP_TILE_WIDTH;
        camera.viewportHeight = map.getHeight()*MAP_TILE_HEIGHT;
        camera.update();
    }

    /**
     * Освобождаем память
     */
    public void dispose()
    {
        font.dispose();
        batch.dispose();
        for (TextureRegion mapTile : map_tiles)
            mapTile.getTexture().dispose();
        txPeasant.getTexture().dispose();
        txCrossbowman.getTexture().dispose();
        txSniper.getTexture().dispose();
        txRobber.getTexture().dispose();
        txSpearman.getTexture().dispose();
        txMonk.getTexture().dispose();
        txWizard.getTexture().dispose();
    }

}

