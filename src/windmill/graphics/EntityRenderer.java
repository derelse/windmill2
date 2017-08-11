package windmill.graphics;

import windmill.entity.Entity;
import windmill.graphics.model.Model;
import windmill.graphics.model.Vertex;
import windmill.graphics.shader.BasicShader;
import windmill.math.Transform;
import windmill.math.Vector3f;

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

    private BasicShader shader;

    public EntityRenderer(BasicShader shader){
        this.shader= shader;

    }

    public void render(HashMap<Model,ArrayList<Entity>> entities){

        for (Model model : entities.keySet()){
            loadModel(model);

            for (Entity entity : entities.get(model)){
                loadInstance(entity);
                glDrawElements(GL_TRIANGLES, model.getSize(), GL_UNSIGNED_INT, 0);
            }
            unloadModel();
        }

    }

    private void loadModel(Model model){

        glBindBuffer(GL_ARRAY_BUFFER, model.getVbo());
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, model.getIbo());

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0,3, GL_FLOAT, false, Vertex.SIZE *4,0);

    }

    private void unloadModel(){
        glDisableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER,0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

    }

    private void loadInstance(Entity entity){
        shader.updateWorldMatrix(Transform.getTransformation(entity.getPos(),0,90,0,1f ));

    }
}
