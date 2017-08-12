package windmill.graphics.model;

import windmill.math.Vector3f;

/**
 * Created by ElsE on 11.08.2017.
 */
public class Vertex {

    //this class represents a Vertex

    public static final int SIZE = 6;

    private Vector3f pos;
    private Vector3f normal;

    public Vertex(float x,float y,float z, Vector3f normal){
       this.pos=(new Vector3f(x,y,z));
       this.normal=normal;
    }

    public Vertex(Vector3f pos,Vector3f normal){
        this.pos= pos;
        this.normal=normal;
    }


    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public void setNormal(Vector3f normal) {
        this.normal = normal;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
