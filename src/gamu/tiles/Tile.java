package gamu.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//Static Stuff
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile waterTile = new WaterTile(3);

	
	
	//Class
	
	public static final int TileWidth = 64,TileHeight = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	
	public Tile(BufferedImage texture,int id) {
		
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g,int x,int y) {
		g.drawImage(texture, x, y, TileWidth,TileHeight,null);
	}
	
	public boolean isSolid() {
		return false;
	}

	
	public int getId() {
		return id;
	}

}
