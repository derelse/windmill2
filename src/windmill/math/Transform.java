package windmill.math;

import windmill.Camera;

/**
 * Created by ElsE on 11.08.2017.
 */
public class Transform {

    public static Matrix4f getPerspectiveProjection (float fov, int width, int height, float zNear, float zFar){
        return new Matrix4f().initPerspective(fov,width,height,zNear,zFar);
    }

    public static Matrix4f getTransformation(Vector3f translation, float rX, float rY, float rZ, float scale){


        Matrix4f translationMatrix = new Matrix4f().initTranslation(translation);
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rX,rY,rZ);
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale);

        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }

    public static Matrix4f getRotTransformation(Vector3f translation, float rX, float rY, float rZ, float scale){
        Matrix4f translationMatrix = new Matrix4f().initTranslation(translation);
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rX,rY,rZ);
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale);

        return rotationMatrix.mul(translationMatrix.mul(scaleMatrix));
    }


    public static Matrix4f getViewMatrix(Camera camera){

        Vector3f pos = camera.getPos();

        Matrix4f translationMatrix = new Matrix4f().initTranslation(-pos.x,-pos.y,-pos.z);
        Matrix4f rotationMatrix = new Matrix4f().initRotation(camera.getForward(),camera.getUp());

        return rotationMatrix.mul(translationMatrix);
    }
}
