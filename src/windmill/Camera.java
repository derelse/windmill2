package windmill;

import org.lwjgl.system.CallbackI;
import windmill.math.Vector3f;

/**
 * Created by ElsE on 12.08.2017.
 */
public class Camera {

    private static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;

    private Vector3f forward;
    private Vector3f up;

    public Camera(){

        this(new Vector3f(0,0,0));

    }

    public Camera(Vector3f pos){
        this.pos = pos;

        forward = new Vector3f(0,0,1);
        up = new Vector3f(0,1,0);
    }

    public void move(float dx,float dy,float dz){


        Vector3f relXAxis = yAxis.cross(forward).normalized();      //relative movement
        Vector3f relZAxis = relXAxis.cross(yAxis).normalized();

        float x = relXAxis.x * dx + relZAxis.x *dz;
        float y = dy;
        float z = relXAxis.z * dx + relZAxis.z *dz;

        pos.x += x;
        pos.y += y;
        pos.z += z;

    }

    public void rotateY(float angle){
        Vector3f horAxis = yAxis.cross(forward).normalized();
        forward = forward.rotate(yAxis, angle);
        up = forward.cross(horAxis).normalized();

    }

    public void rotateX(float angle){
        Vector3f horAxis = yAxis.cross(forward).normalized();
        forward = forward.rotate(horAxis, angle);
        up = forward.cross(horAxis).normalized();

    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }



    public Vector3f getUp() {
        return up;
    }


}
