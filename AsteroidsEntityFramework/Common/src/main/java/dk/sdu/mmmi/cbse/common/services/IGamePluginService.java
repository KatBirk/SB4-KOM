package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * Start: Embeds the plugins (like Entities) into the game
     * Stop: Removes the plugins (like Entities) from the game
     * @param gameData holds the data about the game state from the previous game tick
     * @param world holds data about the Entity state from the previous game tick
     */
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
