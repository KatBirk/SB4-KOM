module Bullet {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.mmmi.cbse.commonBullet.BulletSPI;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.bullet.BulletControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.bullet.BulletPlugin;
}