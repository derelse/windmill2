package windmill.graphics.model;

import windmill.math.Vector3f;

/**
 * Created by ElsE on 11.08.2017.
 */
public class Vertex {

    public static final int SIZE = 3;

    private Vector3f pos;

    public Vertex(float x,float y,float z){
       this.pos=(new Vector3f(x,y,z));
    }

    public Vertex(Vector3f pos){
        this.pos= pos;
    }


    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
