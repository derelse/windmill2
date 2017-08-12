package windmill;

import windmill.graphics.model.Model;
import windmill.math.Vector3f;

/**
 * Created by ElsE on 12.08.2017.
 */
public class Entity {

    //this class represents Items in the view

    private Model model;
    private String name;
    private Vector3f pos;


    public Entity(Vector3f pos, Model model, String name){
        this.pos= pos;
        this.model=model;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
