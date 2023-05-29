import dk.sdu.mmmi.cbse.commonBullet.BulletSPI;
module Bullet {
    requires Common;
    requires CommonBullet;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
    provides BulletSPI with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
}