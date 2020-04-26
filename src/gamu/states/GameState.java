package gamu.states;

import java.awt.Graphics;

import gamu.Game;
import gamu.Handler;
import gamu.entities.creatures.Player;
import gamu.entities.statics.Tree;
import gamu.gfx.Assets;
import gamu.tiles.Tile;
import gamu.worlds.World;

public class GameState extends State{
	
	
	private World world;

	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);	
		
		
	}

	@Override
	public void update() {
		
		world.update();	
	
		 
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
	
		
		
	}

	
	
}
