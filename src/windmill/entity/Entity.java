package windmill.entity;

import windmill.graphics.model.Model;
import windmill.math.Vector3f;

/**
 * Created by ElsE on 12.08.2017.
 */
public class Entity {

    private Model model;

    private Vector3f pos;


    public Entity(Vector3f pos, Model model){
        this.pos= pos;
        this.model=model;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }
}
