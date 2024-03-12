
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.behavior.TeamType;
import com.mygdx.game.person.PersonBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MapRender {

    private enum Dir {
        LEFT, RIGHT;
    }
    
    private static final int MAP_TILE_WIDTH = 64;
    private static final int MAP_TILE_HEIGHT = 64;
    
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private final SpriteBatch batch;

    private final TextureRegion[] map_tiles;

    private final HashMap<String, TextureRegion> txPersons;


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
        // для персонажей
        txPersons = new HashMap<>();
        txPersons.put("Peasant", new TextureRegion(tiles[1][0]));
        txPersons.put("Crossbowman", new TextureRegion(tiles[1][1]));
        txPersons.put("Sniper",  new TextureRegion(tiles[1][2]));
        txPersons.put("Robber", new TextureRegion(tiles[1][3]));
        txPersons.put("Spearman", new TextureRegion(tiles[1][4]));
        txPersons.put("Monk", new TextureRegion(tiles[1][5]));
        txPersons.put("Wizard", new TextureRegion(tiles[1][6]));
    }


    public void render()
    {
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();

        renderMap();
        renderTeams();
        
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

    private void renderTeams()
    {
        renderOneTeam(map.teams.getTeam(TeamType.RED), Dir.LEFT);
        renderOneTeam(map.teams.getTeam(TeamType.BLUE), Dir.RIGHT);
    }

    private void renderOneTeam(ArrayList<PersonBase> team, Dir dir)
    {
        for (PersonBase p : team)
        {
            TextureRegion texture = txPersons.get(p.getClass().getSimpleName());
            if (texture != null)
            {
                if (dir == Dir.LEFT)
                    batch.draw(texture, p.getPositionX()*MAP_TILE_WIDTH, p.getPositionY()*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
                else {
                    texture.flip(true,false);
                    batch.draw(texture, p.getPositionX()*MAP_TILE_WIDTH, p.getPositionY()*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
                    texture.flip(true,false);
                }
            } else {
                Console.println(p.getClass().getSimpleName());
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

        txPersons.get("Peasant").getTexture().dispose();
        txPersons.get("Crossbowman").getTexture().dispose();
        txPersons.get("Sniper").getTexture().dispose();
        txPersons.get("Robber").getTexture().dispose();
        txPersons.get("Spearman").getTexture().dispose();
        txPersons.get("Monk").getTexture().dispose();
        txPersons.get("Wizard").getTexture().dispose();
    }

}

