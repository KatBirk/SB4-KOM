module Core {
    //Asteroids internal modules
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    requires CommonAsteroid;

    requires java.desktop;
    requires gdx.backend.lwjgl;
    requires gdx;

    //Asteroids SPIs
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
}