import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.playersystem.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonEnemy;

    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with EnemyPlugin;

}