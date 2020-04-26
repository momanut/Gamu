package gamu.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import gamu.Handler;
import gamu.states.State;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	
	public int score = 0;
	
	
	public ItemManager(Handler handler) {
		
		this.handler = handler;
		items = new ArrayList<Item>();
		
	}
	public void update() {
		
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item i = it.next();
			i.update();
			if(i.isPickedUp()) {
				score += 10;
				it.remove();
			}
			if(score >= 250) {
				State.setState(handler.getGame().winState);
			}
		}
		
		
	}
	
	public void render(Graphics g) {
		
		for(Item i : items)
			i.render(g);
		
	}
	
	public void addItem(Item i) {
		i.setHandler(handler);
		items.add(i);
	}
	
	//getter
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
