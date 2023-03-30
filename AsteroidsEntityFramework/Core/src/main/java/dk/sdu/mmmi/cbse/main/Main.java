package dk.sdu.mmmi.cbse.main;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();

		int width = 1900;
		int height = 800;
		cfg.setTitle("Asteroids");
		cfg.setWindowSizeLimits(width, height, width, height);
		cfg.setWindowedMode(width, height);
		
		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
