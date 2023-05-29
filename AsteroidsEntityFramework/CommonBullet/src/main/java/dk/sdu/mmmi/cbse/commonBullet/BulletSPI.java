package dk.sdu.mmmi.cbse.commonBullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface BulletSPI {
    /**
     * Pre-condition: An entity that can fire a bullet has been loaded into the game and user wants bullet to appear
     * Post-condition: A bullet has been created with the start position of the shooter entity
     * @param source: Holds information about the entity from which the bullet is fired
     * @param gameData: Holds the data about the game state from the previous game tick
     * @return
     */
    Entity createBullet(Entity source, GameData gameData);
}
