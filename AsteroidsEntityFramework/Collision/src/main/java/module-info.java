import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;

    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionDetector;
}