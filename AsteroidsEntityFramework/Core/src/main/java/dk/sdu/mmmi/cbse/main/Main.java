package dk.sdu.mmmi.cbse.main;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfig.class);
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
		int width = 1900;
		int height = 800;
		config.setTitle("Asteroids");
		config.setWindowSizeLimits(width, height, width, height);
		config.setWindowedMode(width, height);
		
		new Lwjgl3Application(context.getBean(Game.class), config);
		
	}
	
}
