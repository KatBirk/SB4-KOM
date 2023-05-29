package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.SPILocator;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {

    public ModuleConfig() {
    }

    @Bean
    public Game game(){
        return new Game(gamePluginServices(), entityProcessingServices(), postEntityProcessingServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices (){
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
}
