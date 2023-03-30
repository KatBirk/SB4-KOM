package dk.sdu.mmmi.cbse.main;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();

		cfg.setTitle("Asteroids");
		cfg.setWindowSizeLimits(1000, 800, 1900, 1080);
		
		new Lwjgl3Application(new Game(), cfg);
		
	}
	
}
