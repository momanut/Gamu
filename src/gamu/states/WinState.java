package gamu.states;

import java.awt.Color;
import java.awt.Graphics;

import gamu.Handler;
import gamu.gfx.Assets;
import gamu.gfx.Text;

public class WinState extends State{

	public WinState(Handler handler) {
		super(handler);
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Assets.win, 0, 0, handler.getWidth(), handler.getHeight(), null);
		
	}

}
