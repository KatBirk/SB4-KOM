package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonAsteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonBullet.Bullet;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData mockedGameData;
    @BeforeEach
    void setUp() {
        this.collisionDetector = new CollisionDetector();
        this.mockedGameData = mock(GameData.class);
    }
    @Test
    void bulletAndAsteroidAreHit() {
        Entity mockedAsteroid = mockedEntity(Asteroid.class, "1", 0, 0, 2,2f);
        Entity mockedBullet1 = mockedEntity(Bullet.class,"2", 0, 0, 2,2f);

        World mockedWorld = mockedWorld(mockedBullet1, mockedAsteroid);

        when(mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockedBullet1));
        when(mockedWorld.getEntities(Asteroid.class)).thenReturn(List.of(mockedAsteroid));

        collisionDetector.process(mockedGameData, mockedWorld);

        //Hit when bullet and asteroid
        ((LifePart) verify(mockedAsteroid.getPart(LifePart.class), atLeastOnce())).setIsHit(true);
        ((LifePart) verify(mockedBullet1.getPart(LifePart.class), atLeastOnce())).setIsHit(true);

        //Not dead
        assertFalse(((LifePart)(mockedBullet1.getPart(LifePart.class))).isDead());
        assertFalse(((LifePart)(mockedAsteroid.getPart(LifePart.class))).isDead());

        //World contains entities
        assertTrue(mockedWorld.getEntities().contains(mockedAsteroid));
        assertTrue(mockedWorld.getEntities().contains(mockedBullet1));
    }

    @Test
    void bulletAndPlayerNotHit(){
        Entity mockedBullet1 = mockedEntity(Bullet.class,"3", 5, 5, 2,2f);
        Entity mockedPlayer = mockedEntity(Player.class,"4", 5, 5, 2,2f);

        World mockedWorld = mockedWorld(mockedBullet1, mockedPlayer);

        when(mockedWorld.getEntities(Bullet.class)).thenReturn(List.of(mockedBullet1));
        when(mockedWorld.getEntities(Player.class)).thenReturn(List.of(mockedPlayer));

        collisionDetector.process(mockedGameData, mockedWorld);

        //Not hit when bullet and player
        ((LifePart) verify(mockedBullet1.getPart(LifePart.class), never())).setIsHit(anyBoolean());
        ((LifePart) verify(mockedPlayer.getPart(LifePart.class), never())).setIsHit(anyBoolean());

        //Not dead
        assertFalse(((LifePart)(mockedBullet1.getPart(LifePart.class))).isDead());
        assertFalse(((LifePart)(mockedPlayer.getPart(LifePart.class))).isDead());

        assertTrue(mockedWorld.getEntities().contains(mockedBullet1));
        assertTrue(mockedWorld.getEntities().contains(mockedPlayer));
    }


    @Test
    void collides(){
        Entity mockedEntity1 = mockedEntity(Player.class,"1", 0, 0, 2, 2f);
        Entity mockedEntity2 = mockedEntity(Asteroid.class,"2", 0, 0, 2, 2f);
        Entity mockedEntity3 = mockedEntity(Bullet.class,"3", 5, 5, 2, 2f);
        Entity mockedEntity4 = mockedEntity(Player.class,"4", 5, 5, 2, 2f);
        assertFalse(collisionDetector.collides(mockedEntity1, mockedEntity3));
        assertFalse(collisionDetector.collides(mockedEntity1, mockedEntity4));
        assertTrue(collisionDetector.collides(mockedEntity1, mockedEntity2));
        assertTrue(collisionDetector.collides(mockedEntity3, mockedEntity4));
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

    private static World mockedWorld(Entity mockedEntity1, Entity mockedEntity2) {
        World mockWorld = mock(World.class);

        List<Entity> entityList = List.of(mockedEntity1, mockedEntity2);
        when(mockWorld.getEntities()).thenReturn(entityList);

        doAnswer(mocked -> {
            if (mockWorld.getEntities().equals(List.of(mocked))) {
                when(mockWorld.getEntities()).thenReturn(List.of());
                return null;
            }
            when(mockWorld.getEntities()).thenReturn(List.of(mockedEntity1));
            return null;
        }).when(mockWorld).removeEntity(mockedEntity2);

        doAnswer(mocked -> {
            if (mockWorld.getEntities().equals(List.of(mocked))) {
                when(mockWorld.getEntities()).thenReturn(List.of());
                return null;
            }

            when(mockWorld.getEntities()).thenReturn(List.of(mockedEntity2));
            return null;
        }).when(mockWorld).removeEntity(mockedEntity1);

        return mockWorld;
    }
}