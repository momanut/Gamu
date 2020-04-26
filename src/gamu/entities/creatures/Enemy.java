package gamu.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import gamu.Handler;
import gamu.entities.Entity;
import gamu.gfx.Assets;

public class Enemy extends Creature{

	float distance, diffX, diffY;
	private Handler handler;	
	protected float velX, velY = speed;
	private int choose = 0;
	private int timer = 60;
	private Random r = new Random();
	
	
	public Enemy(Handler handler,float x, float y) {
		super(handler,x, y, Creature.Default_Creature_Width,Creature.Default_Creature_Height);
		
		this.handler = handler;
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		this.x = x;
		this.y = y;
		
				
		
		
	}

	@Override
	public void update() {
		
		x += velX;
		y += velY;
		
		if(timer <= 0) {
			choose = r.nextInt(5);
			timer = 30;
		}
		
		diffX = x - handler.getWorld().getEntityManager().getPlayer().getX() - width;
		diffY = y - handler.getWorld().getEntityManager().getPlayer().getY() - height;
		distance = (float)(Math.sqrt((x - handler.getWorld().getEntityManager().getPlayer().getX())
				* (x - handler.getWorld().getEntityManager().getPlayer().getX()) 
				+ (y - handler.getWorld().getEntityManager().getPlayer().getY()) 
				* (y - handler.getWorld().getEntityManager().getPlayer().getY())));
				
		if(distance < 200) {
			velX = ((-1 / distance) * diffX);
			velY = ((-1 / distance) * diffY);
		}else if(choose == 0){
			velX = (r.nextInt(1 + 1 - 1) - 1);
		}else if(choose == 1) {
			velY = (r.nextInt(1 + 1 - 1) - 1);
		}else{
			velX = 0;
			velY = 0;
		}
		
		checkAttacks();
		timer--;
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.ghost, (int) (x  - handler.getGameCamera().getxOffset() ), (int)(y - handler.getGameCamera().getyOffset() ), width,height,null);
		
	}

	@Override
	public void die() {
		
		
		
	}
	private void checkAttacks() {
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		
		ar.width = arSize;
		ar.height = arSize;
		ar.x = (int)(x);
		ar.y = (int)(y);
				
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar) && e.isPlayer()) {
				e.hurt(1);
				return;
			}			
		}		
		
	}
	
	

}
