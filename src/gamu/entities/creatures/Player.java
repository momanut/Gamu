package gamu.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamu.Game;
import gamu.Handler;
import gamu.entities.Entity;
import gamu.gfx.Animation;
import gamu.gfx.Assets;
import gamu.inventory.Inventory;
import gamu.states.State;

public class Player extends Creature{
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight, animStand;
	private Animation atAniD,atAniU,atAniL,atAniR;

	//Attack timer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

	//Check Attack

	private boolean atU, atD, atL, atR;
	
	//Inventory
	private Inventory inventory;
	
	//Score 
	public int score = 0;
	
	
	public Player(Handler handler,float x, float y) {
		
		
		super(handler,x, y, Creature.Default_Creature_Width,Creature.Default_Creature_Height);
	
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		setPlayer(true);
		
		//Animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		animStand = new Animation(500, Assets.player_stand);
		
		atAniD = new Animation(2000, Assets.attack_Down);
		atAniU = new Animation(2000, Assets.attack_Up);
		atAniL = new Animation(2000, Assets.attack_Left);
		atAniR = new Animation(2000, Assets.attack_Right);
	
		inventory = new Inventory(handler);
		
	}

	@Override
	public void update() {
		
		//Animations
		animDown.update();
		animUp.update();
		animLeft.update();
		animRight.update();
		
		atAniD.update();
		atAniU.update();
		atAniL.update();
		atAniR.update();
		
		//Move		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		//Attack
		checkAttacks();
		//Inventory
		inventory.update();
		
		
	} 
	
	private void checkAttacks() {
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		if(inventory.isActive())
			return;
		
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = 20;
		
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().aUp) {
			
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + arSize;	
	
		
			atU = true;
			
		}else if(handler.getKeyManager().aDown) {
			
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;
		
		
			atD = true;
			
		}else if(handler.getKeyManager().aLeft) {
			
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize /2;
	

			atL = true;
				
		}else if(handler.getKeyManager().aRight) {
			
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize /2;
	
		
			atR = true;
						
		}else {
			
			return;
			
		}
		
		attackTimer = 0;
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}			
		}
		
	}
	
	@Override
	public void die() {
		System.out.println("You lose");
		State.setState(handler.getGame().endState);
	}
	
	private void getInput() {
		if(inventory.isActive())
			return;

		
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width, height, null);
		
	
		/*
		//Check collision
		g.setColor(Color.red);
		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), 
				bounds.width, bounds.height);
		*/
	}
	public void postRender(Graphics g) {
		inventory.render(g);
		
	}
	
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if(atU == true) {
			atU = false;
	
			return atAniU.getCurrentFrame();
		}else if(atD == true) {
			atD = false;
	
			return atAniD.getCurrentFrame();
			
		}else if(atR == true) {
		
			atR = false;
			return atAniR.getCurrentFrame();
		}else if(atL == true) {
			atL = false;
			
			return atAniL.getCurrentFrame();
		}	
		
		if(xMove < 0) {
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}else if(yMove < 0) {
			return animUp.getCurrentFrame();
		}else if(xMove == 0 && yMove == 0) {
			return  animStand.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}		

		
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
