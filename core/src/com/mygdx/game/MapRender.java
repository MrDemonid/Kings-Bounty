
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.behavior.TeamType;
import com.mygdx.game.person.PersonBase;

import java.util.ArrayList;
import java.util.HashMap;


public class MapRender {

    private static final int MAP_TILE_WIDTH = 64;
    private static final int MAP_TILE_HEIGHT = 64;
    
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private final SpriteBatch batch;
    private float gameTime;

    private final TextureRegion[] map_tiles;
    private final HashMap<String, Animation<TextureRegion>> activePerson;

    private final HashMap<String, TextureRegion> txPersons;
    private final TextureRegion shotImage;
    private final TextureRegion kickImage;
    
    Map map;

    
    public MapRender(Map map) {

        this.map = map;
        map.makeMap();
        gameTime = 0.0f;
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
        txPersons.put("Peasant",        new TextureRegion(tiles[1][0]));
        txPersons.put("Crossbowman",    new TextureRegion(tiles[1][1]));
        txPersons.put("Sniper",         new TextureRegion(tiles[1][2]));
        txPersons.put("Robber",         new TextureRegion(tiles[1][3]));
        txPersons.put("Spearman",       new TextureRegion(tiles[1][4]));
        txPersons.put("Monk",           new TextureRegion(tiles[1][5]));
        txPersons.put("Wizard",         new TextureRegion(tiles[1][6]));
        // для активного персонажа
        activePerson = new HashMap<>();
        activePerson.put("Peasant",     new Animation<>(0.2f, tiles[1][0], tiles[2][0]));
        activePerson.put("Crossbowman", new Animation<>(0.2f, tiles[1][1], tiles[2][1]));
        activePerson.put("Sniper",      new Animation<>(0.2f, tiles[1][2], tiles[2][2]));
        activePerson.put("Robber",      new Animation<>(0.2f, tiles[1][3], tiles[2][3]));
        activePerson.put("Spearman",    new Animation<>(0.2f, tiles[1][4], tiles[2][4]));
        activePerson.put("Monk",        new Animation<>(0.2f, tiles[1][5], tiles[2][5]));
        activePerson.put("Wizard",      new Animation<>(0.2f, tiles[1][6], tiles[2][6]));

        shotImage = new TextureRegion(tiles[0][5]);
        kickImage = new TextureRegion(tiles[0][6]);
    }

    public static int getMapTileWidth() { return MAP_TILE_WIDTH; }
    public static int getMapTileHeight() { return MAP_TILE_HEIGHT; }

    public void render(float delta)
    {
        gameTime += delta;
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();

        renderMap();
        renderTeams(gameTime);
        renderShots();
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
     * Отрисовка команд
     *
     * @param time Общее время игры
     */
    private void renderTeams(float time)
    {

        ArrayList<TeamPerson> team = map.teams.allPersons;
        for (TeamPerson p : team)
        {
            PersonBase person = p.person;
            String className = person.getClass().getSimpleName();
            TextureRegion frame;
            if (p.active)
            {
                frame = (TextureRegion) activePerson.get(className).getKeyFrame(time, true);
            } else {
                frame = txPersons.get(className);
            }

            if (frame != null)
            {
                if (p.team == TeamType.RED)
                    batch.draw(frame, person.getPositionX()*MAP_TILE_WIDTH, person.getPositionY()*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
                else {
                    frame.flip(true,false);
                    batch.draw(frame, person.getPositionX()*MAP_TILE_WIDTH, person.getPositionY()*MAP_TILE_HEIGHT, MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
                    frame.flip(true,false);
                }
            } else {
                System.out.println("renderTeams(): unknown class: " + p.getClass().getSimpleName());
            }
        }
    }

    private void renderShots()
    {
        if (Map.shot != null)
        {
            batch.draw(shotImage, Map.shot.getCurX(), Map.shot.getCurY(), MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
        }
        if (Map.kick != null)
        {
            batch.draw(kickImage, Map.kick.getCurX(), Map.kick.getCurY(), MAP_TILE_WIDTH, MAP_TILE_HEIGHT);
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
        txPersons.get("SelPeasant").getTexture().dispose();
        txPersons.get("SelCrossbowman").getTexture().dispose();
        txPersons.get("SelSniper").getTexture().dispose();
        txPersons.get("SelRobber").getTexture().dispose();
        txPersons.get("SelSpearman").getTexture().dispose();
        txPersons.get("SelMonk").getTexture().dispose();
        txPersons.get("SelWizard").getTexture().dispose();

        shotImage.getTexture().dispose();
        kickImage.getTexture().dispose();
    }

}

