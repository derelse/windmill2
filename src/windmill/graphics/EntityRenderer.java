package windmill.graphics;

import windmill.Entity;
import windmill.graphics.model.Model;
import windmill.graphics.model.Vertex;
import windmill.graphics.shader.BasicShader;
import windmill.math.Transform;

import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by ElsE on 12.08.2017.
 */


public class EntityRenderer {
    //this class renders single Entities at a time

    private float fanRotation= 0;

    private BasicShader shader;

    public EntityRenderer(BasicShader shader){
        this.shader= shader;

    }

    public void render(HashMap<Model,ArrayList<Entity>> entities){

        for (Model model : entities.keySet()){
            loadModel(model);

            for (Entity entity : entities.get(model)){
                loadInstance(entity);

                glColor3f(1,1,0);
                glDrawElements(GL_TRIANGLES, model.getSize(), GL_UNSIGNED_INT, 0);
            }
            unloadModel();
        }

    }

    private void loadModel(Model model){



        glBindBuffer(GL_ARRAY_BUFFER, model.getVbo());
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, model.getIbo());
        glBindBuffer(GL_3D_COLOR_TEXTURE, model.getIbo());

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(0,3, GL_FLOAT, false, Vertex.SIZE *4,0);
        glVertexAttribPointer(1,3, GL_FLOAT, false, Vertex.SIZE *4,12);
        glVertexAttribPointer(2,4, GL_FLOAT, false, Vertex.SIZE *4,24);

    }

    private void unloadModel(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindBuffer(GL_ARRAY_BUFFER,0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    private void loadInstance(Entity entity){
        if (entity.getName() == "fan"){
            fanRotation +=0.05f;
            glColor3f(1,1,fanRotation);
            shader.updateWorldMatrix(Transform.getTransformation(entity.getPos(),fanRotation,0,0,1f ));

        }
        else {
            shader.updateWorldMatrix(Transform.getTransformation(entity.getPos(),0,0,0,1f ));
        }


    }

    private void rotateAroundCenter(int angle){


    }
}
