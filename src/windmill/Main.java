package windmill;


import static java.lang.System.err;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;

import org.lwjgl.glfw.GLFWErrorCallback;

import org.lwjgl.opengl.GL;
import windmill.entity.Entity;
import windmill.graphics.MasterRenderer;
import windmill.graphics.Window;
import windmill.graphics.model.Model;
import windmill.math.Vector3f;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ElsE on 11.08.2017.
 */
public class Main {

    private Window window;
    private MasterRenderer renderer;
    private boolean running = false;

    //TEMP

    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public Main(){
            init();
            renderer = new MasterRenderer();
            Model model = Model.loadModel("turbine.obj");
            Random random = new Random();

        for (int i = 0; i<50;i++){
            entities.add(new Entity(new Vector3f(random.nextInt(100)-50,random.nextInt(100)-50,random.nextInt(100)),model));
        }
    }

    private void input (){
        glfwPollEvents();
        if (window.shouldClose()){ stop();};
    }
    private void update(){

    }

    private void render(){

        for (int i = 0; i<entities.size();i++){
            renderer.processEntity(entities.get(i));
        }
        renderer.render();
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
