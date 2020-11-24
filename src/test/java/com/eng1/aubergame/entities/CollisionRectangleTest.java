package com.eng1.aubergame.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class CollisionRectangleTest {

    //@Test
    //public void isColliding() {
    //}

    @Test
    public void isCollidingUp() {
        CollisionRectangle testPlayerHitbox = new CollisionRectangle(3, 3, 2, 4);
        CollisionRectangle testWallHitbox = new CollisionRectangle(1, 0, 8, 4);
        boolean intersecting = testPlayerHitbox.isCollidingUp(testWallHitbox);

        assertEquals(intersecting, true);
    }

    @Test
    public void isCollidingDown() {
        CollisionRectangle testPlayerHitbox = new CollisionRectangle(3, 0, 2, 4);
        CollisionRectangle testWallHitbox = new CollisionRectangle(1, 3, 6, 4);
        boolean intersecting = testPlayerHitbox.isCollidingDown(testWallHitbox);

        assertEquals(intersecting, true);
    }

    @Test
    public void isCollidingLeft() {
        CollisionRectangle testPlayerHitbox = new CollisionRectangle(4, 1, 3, 3);
        CollisionRectangle testWallHitbox = new CollisionRectangle(1, 0, 4, 6);
        boolean intersecting = testPlayerHitbox.isCollidingLeft(testWallHitbox);

        assertEquals(intersecting, true);
    }

    @Test
    public void isCollidingRight() {
        CollisionRectangle testPlayerHitbox = new CollisionRectangle(2, 1, 3, 3);
        CollisionRectangle testWallHitbox = new CollisionRectangle(4, 0, 4, 6);
        boolean intersecting = testPlayerHitbox.isCollidingRight(testWallHitbox);

        assertEquals(intersecting, true);
    }
}