package gamu.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import gamu.Handler;
import gamu.gfx.Assets;
import gamu.items.Item;
import gamu.tiles.Tile;

public class Tree extends StaticEntity{
	
	public Tree(Handler handler,float x, float y) {
		super(handler, x, y, Tile.TileWidth, Tile.TileHeight * 2);
		
		bounds.x = 5;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 30;
		bounds.height = (int)(height - height / 1.5f) ;
		
	}

	@Override
	public void update() {
		
		
	}
	
	@Override
	public void die(){
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int)x, (int)y));
	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Assets.tree, (int) (x  - handler.getGameCamera().getxOffset() ), (int)(y - handler.getGameCamera().getyOffset() ), width,height,null);
		
		//checkCollision
		/* 
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), 
				bounds.width, bounds.height);
		*/
	}

}
