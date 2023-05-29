package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {
    /**
     * Pre-condition: Game is running and entities have been loaded into the game, and
     * a tick has passed since last call
     * Post-condition: Entities have been processed
     * @param gameData holds the data about the game state from the previous game tick
     * @param world holds data about the Entity state from the previous game tick
     */

    void process(GameData gameData, World world);
}
