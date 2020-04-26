package gamu.entities.creatures;

import gamu.Game;
import gamu.Handler;
import gamu.entities.Entity;

import gamu.states.State;
import gamu.tiles.Tile;

public abstract class Creature extends Entity{

	public static final float Default_Speed = 3.0f;
	public static final int Default_Creature_Width = 64,
							Default_Creature_Height = 64;
		
	
	protected float speed;
	protected float xMove,yMove;
	
	
	public Creature(Handler handler,float x, float y,int width,int height) {
		
		super(handler,x, y,width,height);
		
		
		speed = Default_Speed;
		xMove = 0;
		yMove = 0;
	
	}

	public void move() {
		
		if(!checkEntityCollisions(xMove,0f))
			moveX();
		if(!checkEntityCollisions(0,yMove))
			moveY();	
		
	}
	
	public void moveX() {		
		if(xMove > 0) {
			//moving right
			int tx = (int)(x + xMove + bounds.x + bounds.width)/Tile.TileWidth;
			
			if(!collisionWithTile(tx,(int)(y + bounds.y)/ Tile.TileHeight) &&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/ Tile.TileHeight)) {
				x += xMove;
			}else {
				x = tx * Tile.TileWidth - bounds.x - bounds.width - 1;
			}
			
		}else if(xMove < 0) {
			//moving left
			int tx = (int)(x + xMove + bounds.x )/Tile.TileWidth;
			
			if(!collisionWithTile(tx,(int)(y + bounds.y)/ Tile.TileHeight) &&
					!collisionWithTile(tx,(int)(y+bounds.y + bounds.height)/ Tile.TileHeight)) {
				x += xMove;
			}else {
				x = tx * Tile.TileWidth + Tile.TileWidth - bounds.x;
			}
			
		}
	}
	public void moveY() {
		if(yMove < 0) {//up
			
			int ty = (int)(y + yMove + bounds.y) / Tile.TileHeight;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TileWidth, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TileWidth, ty)	) {
				y += yMove;
			}else {
				y = ty * Tile.TileHeight + Tile.TileHeight - bounds.y;
				
			}
			
		}else if(yMove > 0) {//down
			int ty = (int)(y + yMove + bounds.y + bounds.height) / Tile.TileHeight;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TileWidth, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TileWidth, ty)	) {
				y += yMove;
			}else {
				y = ty * Tile.TileHeight - bounds.y - bounds.height - 1;
			}
			
		}
	}
	
	protected boolean collisionWithTile(int x,int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	//Getter Setter
	
	public float getxMove() {
		return xMove;
	}


	public void setxMove(float xMove) {
		this.xMove = xMove;
	}


	public float getyMove() {
		return yMove;
	}


	public void setyMove(float yMove) {
		this.yMove = yMove;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public float getSpeed() {
		return speed;
	}

  
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	

	
	
}
