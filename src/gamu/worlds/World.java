package gamu.worlds;

import java.awt.Graphics;
import java.util.Random;

import gamu.Game;
import gamu.Handler;
import gamu.entities.EntityManager;
import gamu.entities.creatures.Enemy;
import gamu.entities.creatures.Player;
import gamu.entities.statics.Rock;
import gamu.entities.statics.Tree;
import gamu.items.ItemManager;
import gamu.tiles.Tile;
import gamu.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height ;	
	private int spawnX, spawnY;
	private int[][] tiles;
	
	private Random r = new Random();
	
	//Entities
	private EntityManager entityManager;
	
	//Item
	private ItemManager itemManager;
	
 
	
	public World(Handler handler ,String path) {
		
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		int choose; 
		
		for(int i = 0; i < 30; i++) {
			choose = r.nextInt(2);
			if(choose == 1) {
				entityManager.addEntity(new Tree(handler, r.nextInt(1150 - 100) + 100, r.nextInt(1000 - 100) + 100));
			}else {
				entityManager.addEntity(new Rock(handler, r.nextInt(1150 - 100) + 100, r.nextInt(1000 - 100) + 100));
			}
		}	
		
		for(int i = 0; i < 15; i++) {
			entityManager.addEntity(new Enemy(handler, r.nextInt(1100 - 200) + 200, r.nextInt(1100 - 200) + 200));
		}		
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
			
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void update() {
		
		itemManager.update();
		entityManager.update();
		
	}
		
	public void render(Graphics g) {
		
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset() / Tile.TileWidth);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TileWidth + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TileHeight) ;
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) /Tile.TileHeight + 1);
		
		for(int y = yStart; y < yEnd ;y++) {
			for(int x = xStart; x < xEnd ; x++) {
				getTile(x,y).render(g, (int)(x * Tile.TileWidth - handler.getGameCamera().getxOffset()),
								(int)(y * Tile.TileHeight - handler.getGameCamera().getyOffset()) );
			}
		}
		//Items
		itemManager.render(g);
		
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x,int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
	
		tiles = new int[width][height];
		for(int y = 0;y < height;y++) {
			for(int x = 0; x < width;x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y*width) + 4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
