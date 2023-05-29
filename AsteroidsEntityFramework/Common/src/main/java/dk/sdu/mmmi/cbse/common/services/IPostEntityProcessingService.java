package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * Pre-condition: IEntityProcessingServices have been processed
         * Post-condition: Conditions of entities have been acted upon and processed
         * @param gameData holds the data about the game state from the previous game tick
         * @param world holds data about the Entity state from the previous game tick
         */
        void process(GameData gameData, World world);
}
