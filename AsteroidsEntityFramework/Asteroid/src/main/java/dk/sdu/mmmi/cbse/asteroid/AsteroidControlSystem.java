package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonAsteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonAsteroid.AsteroidSplitter;


public class AsteroidControlSystem implements IEntityProcessingService {
    AsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();
    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);

            int numPoints = 12;
            float speed = (float) Math.random() * 10f + 20f;

            if (lifePart.getLife() == 1){
                numPoints = 8;
                speed = (float) Math.random() * 30f + 70f;
            } else if (lifePart.getLife() == 2){
                numPoints = 10;
                speed = (float) Math.random() * 10f + 50f;
            }

            movingPart.setSpeed(speed);
            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            if (lifePart.isIsHit()){
                asteroidSplitter.createSplitAsteroid(asteroid, world);
            }
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

