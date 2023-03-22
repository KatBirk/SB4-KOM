package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * The process method is called everytime an action or any logic happens after the loading
         * of the Entity, like collision.
         * @param gameData holds the data about the game state from the previous game tick
         * @param world holds data about the Entity state from the previous game tick
         */
        void process(GameData gameData, World world);
}
