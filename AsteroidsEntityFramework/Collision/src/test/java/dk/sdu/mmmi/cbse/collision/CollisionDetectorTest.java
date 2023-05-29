package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonAsteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonBullet.Bullet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;


class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData mockedGameData;
    private World mockedWorld;
    @BeforeEach
    void setUp() {
        this.collisionDetector = new CollisionDetector();
        this.mockedGameData = mock(GameData.class);
        this.mockedWorld = mock(World.class);
    }
    @Test
    void process() {
        Entity mockedAsteroid = mockedEntity(Asteroid.class, "1", 2, 3, 0,3);
        Entity mockedBullet = mockedEntity(Bullet.class,"2", 3, 3, 10,3);
        when(this.mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockedBullet));
        when(this.mockedWorld.getEntities(Asteroid.class)).thenReturn(List.of(mockedAsteroid));

        collisionDetector.process(mockedGameData, mockedWorld);

        verify((LifePart)mockedAsteroid.getPart(LifePart.class), atLeastOnce()).setIsHit(true);
    }

    Entity mockedEntity(Class<? extends Entity> entityType, String id, float xPos, float yPox,int life,float radius ){
        Entity entity = mock(entityType);
        LifePart lifePart = mock (LifePart.class);
        PositionPart positionPart = mock(PositionPart.class);

        when(entity.getRadius()).thenReturn(radius);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);
        when(entity.getPart(PositionPart.class)).thenReturn(positionPart);
        when(entity.getID()).thenReturn(id);
        when(positionPart.getX()).thenReturn(xPos);
        when(positionPart.getY()).thenReturn(yPox);
        when(lifePart.getLife()).thenReturn(life);

        return entity;

    }
}