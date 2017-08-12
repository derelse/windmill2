package windmill.graphics;

import windmill.Camera;
import windmill.Entity;
import windmill.graphics.model.Model;
import windmill.graphics.shader.BasicShader;
import windmill.graphics.shader.Shader;
import windmill.math.Matrix4f;
import windmill.math.Transform;
import windmill.math.Vector3f;


import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by ElsE on 11.08.2017.
 */


public class MasterRenderer {

    //this class does the main render stuff

    private BasicShader basicShader;

    private EntityRenderer entityRenderer;

    private Matrix4f projectionMatrix;

    private HashMap<Model,ArrayList<Entity>> entities = new HashMap<Model,ArrayList<Entity>>();

    public MasterRenderer(){
        init();
        basicShader = new BasicShader();

        entityRenderer = new EntityRenderer(basicShader);
        updateProjection(70, 1280, 720, 0.1f, 1000f);


    }

    public void render(Camera camera, Vector3f sun){

        Matrix4f viewMatrix = Transform.getViewMatrix(camera);

        prepare();

        basicShader.bind();
        basicShader.updateViewMatrix(viewMatrix);
        basicShader.updateSun(sun);
        entityRenderer.render(entities);


        Shader.unbind();
        entities.clear();

    }

    public void processEntity(Entity entity){
      Model model = entity.getModel();
      ArrayList<Entity> batch = entities.get(model);
      if(batch == null){
          batch = new ArrayList<Entity>();
          entities.put(model,batch);
      }

      batch.add(entity);
    }


    private void prepare(){
        glClearColor(0.0f,0.0f,0.0f,1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private void init(){
        glEnable(GL_DEPTH_TEST);

    }

    public void cleanup(){

    }

    private void updateProjection(float fov, int width, int height, float zNear, float zFar){
        projectionMatrix = Transform.getPerspectiveProjection(fov, width,height,zNear,zFar);

        basicShader.bind();
        basicShader.updateProjectionMatrix(projectionMatrix);
        Shader.unbind();
    }


}
