package windmill.graphics.shader;

import windmill.math.Matrix4f;
import windmill.math.Vector3f;

/**
 * Created by ElsE on 11.08.2017.
 */
public class BasicShader extends Shader {

    private static final String VERTEX_FILE = "basicVertex.vs";
    private static final String FRAGMENT_FILE = "basicFragment.fs";

    public BasicShader(){
        super();

        addVertexShader(loadShader(VERTEX_FILE));
        addFragmentShader(loadShader(FRAGMENT_FILE));
        compileShader();

        addUniform("projectionMatrix");
        addUniform("worldMatrix");
        addUniform("viewMatrix");
        addUniform("sun");

    }

    @Override
    public void bindAttributes(){
        bindAttribute(0, "position");
    }

    public void updateProjectionMatrix(Matrix4f projectionMatrix){
        setUniform("projectionMatrix", projectionMatrix);
    }

    public void updateSun(Vector3f sun){
        setUniform("sun", sun);
    }

    public void updateViewMatrix(Matrix4f viewMatrix){
        setUniform("viewMatrix", viewMatrix);

    }

    public void updateWorldMatrix(Matrix4f worldMatrix){
        setUniform("worldMatrix", worldMatrix);
    }
}
