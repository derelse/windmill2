package windmill;

/*Credit for the Model of the windmill :
--------------------------------------------------------------------------------

Thank you for downloading "Windmill" by ahedov
        Released under
        Creative Commons Attribution, Share Alike 3.0
        Downloaded from http://www.blendswap.com/blends/view/69174

        The following is important licensing information, please keep this file for
        archival and future reference when working with the file. It's important that
        you know, understand and follow any requirements stated in this file regarding
        the usage of the materials included.

        Some parts of this file are marked with [#], the corresponding numbered note is
        at the end of the file.

        --------------------------------------------------------------------------------
        ################################################################################

        VERY IMPORTANT LICENSE INFORMATION:

        This blend has been released under
        Creative Commons Attribution, Share Alike 3.0

        This means that you can use it for any purpose you see fit, even commercially,
        as long as you respect these requirements:

        --You MUST give credit to ahedov.


        ################################################################################
        --------------------------------------------------------------------------------

        ABOUT THE BLEND:

        Ready for Blender 2.67
        Published on: 2013-07-12 07:02:21

 */


import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;

import org.lwjgl.opengl.GL;
import windmill.graphics.MasterRenderer;
import windmill.graphics.Window;
import windmill.graphics.model.Model;
import windmill.math.Vector3f;

import java.util.ArrayList;

/**
 * Created by ElsE on 11.08.2017.
 */

public class Main {

    private Window window;
    private Camera camera;
    private Input input;
    private MasterRenderer renderer;
    private boolean running = false;

    //TEMP

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Main(){
            init();
            renderer = new MasterRenderer();
            input = new Input(window);
            camera = new Camera();

            Model model = Model.loadModel("windmill_fan.obj");
            Model model2 = Model.loadModel("windmill_base.obj");

            entities.add(new Entity(new Vector3f(0,0,0),model,"fan"));
            entities.add(new Entity(new Vector3f(0,0,0),model2,"base"));

       /* for (int i = 0; i<50;i++){
            entities.add(new Entity(new Vector3f(random.nextInt(10),random.nextInt(10),random.nextInt(10)),model));
        } */
    }

    private void input (){
        glfwPollEvents();
        if (window.shouldClose()){ stop();}

        float mov_amt = 0.25f;           //movement
        float dx = 0;
        float dy = 0;
        float dz = 0;

        if (input.keys[GLFW_KEY_W]) dz += mov_amt;
        if (input.keys[GLFW_KEY_S]) dz -= mov_amt;
        if (input.keys[GLFW_KEY_A]) dx -= mov_amt;
        if (input.keys[GLFW_KEY_D]) dx += mov_amt;
        if (input.keys[GLFW_KEY_Z]) dy += mov_amt;
        if (input.keys[GLFW_KEY_X]) dy -= mov_amt;

        camera.move(dx,dy,dz);

        float rot_amt = 1f;



        if (input.keys[GLFW_KEY_UP]) camera.rotateX(-rot_amt);
        if (input.keys[GLFW_KEY_DOWN]) camera.rotateX(rot_amt);
        if (input.keys[GLFW_KEY_LEFT]) camera.rotateY(-rot_amt);
        if (input.keys[GLFW_KEY_RIGHT]) camera.rotateY(rot_amt);



    }
    private void update(){

    }

    private void render(){

        for (int i = 0; i<entities.size();i++){
            renderer.processEntity(entities.get(i));
        }
        renderer.render(camera, new Vector3f(-1,2,-1).normalized());
        window.render();

    }


    private void run(){
        long lastTime=System.nanoTime();        //constant Update Rate
        long curTime = lastTime;                //not really necessary
        long diff = 0;

        long timer = System.currentTimeMillis();

        double ns = 1000000000/60.0;
        double delta = 0.0;

        double dfps = 1000000000/60.0;          //fps cap also not necessary
        double d    = 0.0;

        int fps = 0;
        int ups = 0;

        while (running){
        curTime = System.nanoTime();
        diff = curTime-lastTime;
        delta += diff/ ns;
        d += diff / dfps;
        lastTime = curTime;



        while (delta >= 1.0){
            input();
            update();
            ups++;
            delta--;
        }

        if (d >= 1.0){
            render();
            fps++;
            d = 0.0;
        }


        if (System.currentTimeMillis() > timer +1000){
            window.setTitle("Windmill --  ups: " + ups + "  |  fps: " + fps);
            ups = 0;
            fps = 0;
            timer += 1000;

        }
        }

        cleanUp();
    }

    public void start(){
        if (running) return;
        running = true;
        run();
    }
    public void stop(){
        if (!running) return;
        running = false;
    }
    private void init(){
        GLFWErrorCallback.createPrint(System.err).set();
        glfwInit();
        window = new Window(1280,720, "Windmill");
        GL.createCapabilities();
    }

    private void cleanUp(){
        window.hide();
        renderer.cleanup();
        window.dispose();

    }

    public static void main(String[] args){

       Main main = new Main();

       main.start();
    }
}
