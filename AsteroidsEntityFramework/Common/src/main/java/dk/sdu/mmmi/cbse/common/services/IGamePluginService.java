package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * Pre-condition: Game is started and plugin has not yet been loaded
     * Post-condition: All entities have been loaded into the game
     * @param gameData holds the data about the game state from the previous game tick
     * @param world holds data about the Entity state from the previous game tick
     */
    void start(GameData gameData, World world);

    /**
     * Pre-condition: Entities have been loaded into the game and there are plugins to remove
     * Post-condition: Entities are removed from the game
     * @param gameData holds the data about the game state from the previous game tick
     * @param world holds data about the Entity state from the previous game tick
     */
    void stop(GameData gameData, World world);
}
