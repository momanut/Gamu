package gamu.states;

import java.awt.Color;
import java.awt.Graphics;

import gamu.Game;
import gamu.Handler;
import gamu.gfx.Assets;
import gamu.ui.ClickListener;
import gamu.ui.UIImageButton;
import gamu.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(250 , 150, 128, 64,Assets.btn_start, new ClickListener(){

			@Override
			public void onClick() {
				
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
				
			}
		}));
	}
	
	@Override
	public void update() {
		
		uiManager.update();
		
	}

	@Override
	public void render(Graphics g) {
		
		uiManager.render(g);
		
	}
	
	

}
