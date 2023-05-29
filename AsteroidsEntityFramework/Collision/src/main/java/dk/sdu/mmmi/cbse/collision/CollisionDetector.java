package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.commonBullet.Bullet;
import dk.sdu.mmmi.cbse.commonplayer.Player;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            for (Entity collisionDetection : world.getEntities()) {
                LifePart entityLife = entity.getPart(LifePart.class);

                if (entity.getID().equals(collisionDetection.getID())) {
                    continue;
                }

                if (entity instanceof Player && collisionDetection instanceof Bullet) {
                    continue;
                }

                if (this.collides(entity, collisionDetection)) {
                    entityLife.setIsHit(true);
                    entityLife.process(gameData, entity);
                    if (entityLife.isDead()) {
                        world.removeEntity(entity);
                    }
                }
            }
        }
    }


    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx = entMov.getX() - entMov2.getX();
        float dy = entMov.getY() - entMov2.getY();
        float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }
}
