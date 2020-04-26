package gamu.states;

import java.awt.Color;
import java.awt.Graphics;

import gamu.Handler;
import gamu.entities.creatures.Player;
import gamu.gfx.Assets;
import gamu.gfx.Text;

public class EndState extends State{


	public EndState(Handler handler) {
		super(handler);
		
	
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.lose, 0, 0, handler.getWidth(), handler.getHeight(), null);
		/*
		Text.drawString(g, "LOSE GAME" , 250, 150,true, Color.BLACK, Assets.font28);
		Text.drawString(g, "Score : " + Integer.toString(handler.getWorld().getItemManager().score), 250, 100,true, Color.BLACK, Assets.font28);
		*/
	}

	
	
}
