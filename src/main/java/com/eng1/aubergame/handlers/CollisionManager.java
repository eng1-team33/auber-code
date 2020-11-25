package com.eng1.aubergame.handlers;

import com.eng1.aubergame.entities.CollisionRectangle;

import java.util.ArrayList;

public class CollisionManager {

    public ArrayList<CollisionRectangle> collisionRectangles = new ArrayList<>();

    public CollisionManager(){
        CollisionRectangle rect1 = new CollisionRectangle(0, 0, 2607, 4219);
        CollisionRectangle rect2 = new CollisionRectangle(0, 3252, 7500, 996);
        CollisionRectangle rect3 = new CollisionRectangle(0, 0, 7500, 967);
        CollisionRectangle rect4 = new CollisionRectangle(4893, 0, 2607, 4219);
        CollisionRectangle rect5 = new CollisionRectangle(2865, 0, 249, 1093);
        CollisionRectangle rect6 = new CollisionRectangle(3501, 0, 498, 1093);
        CollisionRectangle rect7 = new CollisionRectangle(4383, 0, 123, 1093);
        CollisionRectangle rect8 = new CollisionRectangle(4767, 1354, 2733, 249);
        CollisionRectangle rect9 = new CollisionRectangle(4767, 2238, 2733, 249);
        CollisionRectangle rect10 = new CollisionRectangle(0, 2112, 2733, 372);
        CollisionRectangle rect11 = new CollisionRectangle(0, 2874, 2733, 117);
        CollisionRectangle rect12 = new CollisionRectangle(2868, 2874, 372, 117);
        CollisionRectangle rect13 = new CollisionRectangle(2994, 2745, 120, 123);
        CollisionRectangle rect14 = new CollisionRectangle(4386, 2745, 123, 504);
        CollisionRectangle rect15 = new CollisionRectangle(4260, 2874, 372, 117);
        CollisionRectangle rect16 = new CollisionRectangle(3501, 2745, 498, 504);
        CollisionRectangle rect17 = new CollisionRectangle(3375, 2874, 750, 120);
        CollisionRectangle rect18 = new CollisionRectangle(4386, 1228, 120, 624);
        CollisionRectangle rect19 = new CollisionRectangle(4260, 1351, 372, 252);
        CollisionRectangle rect20 = new CollisionRectangle(4257, 2238, 375, 246);
        CollisionRectangle rect21 = new CollisionRectangle(4383, 1983, 123, 627);
        CollisionRectangle rect22 = new CollisionRectangle(2610, 1351, 504, 375);
        CollisionRectangle rect23 = new CollisionRectangle(2865, 1225, 252, 126);
        CollisionRectangle rect24 = new CollisionRectangle(3117, 1351, 126, 252);
        CollisionRectangle rect25 = new CollisionRectangle(2991, 1729, 126, 126);
        CollisionRectangle rect26 = new CollisionRectangle(2865, 2109, 126, 378);
        CollisionRectangle rect27 = new CollisionRectangle(2991, 1983, 126, 633);
        CollisionRectangle rect28 = new CollisionRectangle(3117, 2238, 126, 249);
        CollisionRectangle rect29 = new CollisionRectangle(3372, 2238, 756, 249);
        CollisionRectangle rect30 = new CollisionRectangle(3498, 2109, 504, 504);
        CollisionRectangle rect31 = new CollisionRectangle(3498, 1983, 123, 126);
        CollisionRectangle rect32 = new CollisionRectangle(3879, 1983, 123, 126);
        CollisionRectangle rect33 = new CollisionRectangle(3372, 1351, 126, 252);
        CollisionRectangle rect34 = new CollisionRectangle(3999, 1348, 126, 255);
        CollisionRectangle rect35 = new CollisionRectangle(3498, 1225, 504, 504);
        CollisionRectangle rect36 = new CollisionRectangle(3498, 1729, 123, 126);
        CollisionRectangle rect37 = new CollisionRectangle(3879, 1729, 123, 126);

        this.add(rect1);
        this.add(rect2);
        this.add(rect3);
        this.add(rect4);
        this.add(rect5);
        this.add(rect6);
        this.add(rect7);
        this.add(rect8);
        this.add(rect9);
        this.add(rect10);
        this.add(rect11);
        this.add(rect12);
        this.add(rect13);
        this.add(rect14);
        this.add(rect15);
        this.add(rect16);
        this.add(rect17);
        this.add(rect18);
        this.add(rect19);
        this.add(rect20);
        this.add(rect21);
        this.add(rect22);
        this.add(rect23);
        this.add(rect24);
        this.add(rect25);
        this.add(rect26);
        this.add(rect27);
        this.add(rect28);
        this.add(rect29);
        this.add(rect30);
        this.add(rect31);
        this.add(rect32);
        this.add(rect33);
        this.add(rect34);
        this.add(rect35);
        this.add(rect36);
        this.add(rect37);

    }

    public void add(CollisionRectangle collisionRectangle) {
        this.collisionRectangles.add(collisionRectangle);
    }

    public int canMove(CollisionRectangle hitbox){
        for(CollisionRectangle object : this.collisionRectangles){
            if(object.isCollidingUp(hitbox)){
                return 1;
            }
            if(object.isCollidingRight(hitbox)){
                return 2;
            }
            if(object.isCollidingDown(hitbox)){
                return 3;
            }
            if(object.isCollidingLeft(hitbox)){
                return 4;
            }
        }
        return 0;
    }
}
