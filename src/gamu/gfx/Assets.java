package gamu.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32,height = 32;
	
	public static Font font28;
	
	public static BufferedImage dirt,grass,stone,tree,rock,water,wood,ghost,tnt;
	public static BufferedImage[] player_down;
	public static BufferedImage[] player_up;
	public static BufferedImage[] player_left;
	public static BufferedImage[] player_right;
	public static BufferedImage[] player_stand;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] attack_Up, attack_Down, attack_Left, attack_Right;
	public static BufferedImage inventoryScreen, lose, win;

	public static void init() {
		
		font28 = FontLoader.loadFont("Res/Fonts/slkscr.ttf", 28);
		
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/Sheet.png"));
		SpriteSheet playable = new SpriteSheet(ImageLoader.loadImage("/Textures/Player.png"));
		SpriteSheet envi = new SpriteSheet(ImageLoader.loadImage("/Textures/Envirouments.png"));
		SpriteSheet startBtn = new SpriteSheet(ImageLoader.loadImage("/Textures/StartBtn.png"));
		
		inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
		ghost = ImageLoader.loadImage("/Textures/ghost01.png");
		stone = ImageLoader.loadImage("/Textures/wall.png");
		lose = ImageLoader.loadImage("/Textures/Lose.png");
		win = ImageLoader.loadImage("/Textures/Win.png");
		
		water = ImageLoader.loadImage("/Textures/water.png");
		
		btn_start = new BufferedImage[2];
		btn_start[0] = startBtn.crop(0, 0, width * 2, height);
		btn_start[1] = startBtn.crop(0, height, width * 2, height);
		
		
		
		player_down = new BufferedImage[3];
		
		player_down[0] = playable.crop(0, 0, width, height);
		player_down[1] = playable.crop(width, 0, width, height);
		player_down[2] = playable.crop(width * 2, 0, width, height);
		
		player_up = new BufferedImage[3];
		
		player_up[0] = playable.crop(0, height * 3, width, height);
		player_up[1] = playable.crop(width, height * 3, width, height);
		player_up[2] = playable.crop(width * 2, height * 3, width, height);
	
		player_left = new BufferedImage[3];
		
		player_left[0] = playable.crop(0, height , width, height);
		player_left[1] = playable.crop(width, height, width, height);
		player_left[2] = playable.crop(width * 2, height , width, height);
		
		player_right = new BufferedImage[3];
		
		player_right[0] = playable.crop(0, height * 2, width, height);
		player_right[1] = playable.crop(width, height * 2, width, height);
		player_right[2] = playable.crop(width * 2, height * 2, width, height);
		
		player_stand = new BufferedImage[2];
		player_stand[0] = playable.crop(width, 0, width, height);
		player_stand[1] = playable.crop(width, 0, width, height);
		
		attack_Up = new BufferedImage[2];
		attack_Up[0] = playable.crop(width * 3, height * 3, width, height);
		attack_Up[1] = playable.crop(0, height * 3, width, height);
		
		attack_Down = new BufferedImage[2];
		attack_Down[0] = playable.crop(width * 3, 0, width, height);
		attack_Down[1] = playable.crop(0, 0, width, height);
		
		attack_Left = new BufferedImage[2];
		attack_Left[0] = playable.crop(width * 3, height , width, height);
		attack_Left[1] = playable.crop(0, height , width, height);
		
		attack_Right = new BufferedImage[2];
		attack_Right[0] = playable.crop(width * 3, height * 2, width, height);
		attack_Right[1] = playable.crop(0, height * 2, width, height);
		
		
		

		dirt = sheet.crop(0, 0, width, height);
		grass = sheet.crop(width , 0, width, height);
		
		wood = envi.crop(width * 2, 0, width, height);
		tnt = sheet.crop(width * 3, 0, width, height);
		tree =  envi.crop(0, 0, width, height);
		rock = envi.crop(width, 0, width, height);
	
		
		
	}
	
}
