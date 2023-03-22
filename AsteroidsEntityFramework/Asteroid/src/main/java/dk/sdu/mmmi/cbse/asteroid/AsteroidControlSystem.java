package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonAsteroid.Asteroid;

//Equivalent to 'AsteroidProcessor.java'

public class AsteroidControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            int numPoints = 12;
            float speed = (float) Math.random() * 10f + 20f;

            movingPart.setSpeed(speed);
            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid,numPoints);
        }
    }

    private void updateShape(Entity entity,int numPoints) {
        float[] shapex = new float[numPoints];
        float[] shapey = new float[numPoints];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        /*shapex[0] = (float) (x + Math.cos(radians) * 15);
        shapey[0] = (float) (y + Math.sin(radians) * 15);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 15);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 15);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 15);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 15);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 30);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 14);

        shapex[4] = (float) (x + Math.cos(radians-3*Math.PI) * 20);
        shapey[4] = (float) (y + Math.sin(radians-3*Math.PI) * 35);*/
        float angle = 0;
        float radius = entity.getRadius();
        for (int i = 0; i<numPoints; i++){
            shapex[i] = x + (float) Math.cos(angle + radians) * radius;
            shapey[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f /numPoints;
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}

