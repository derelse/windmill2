package windmill.graphics;



import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFWVidMode;


/**
 * Created by ElsE on 11.08.2017.
 */
public class Window {

    private long windowHandle;

    public Window(int width, int height, String title){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
        glfwWindowHint(GLFW_DECORATED, GL_TRUE);
        glfwWindowHint(GLFW_FOCUSED, GL_TRUE);

       windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        int x = (vidmode.width()-width) /2;
        int y = (vidmode.height()-height) /2;
        glfwSetWindowPos(windowHandle,x,y);

        glfwMakeContextCurrent(windowHandle);
    }

    public void dispose(){
        glfwDestroyWindow(windowHandle);
    }

    public void hide(){
        glfwHideWindow(windowHandle);
    }

    public void show(){
        glfwShowWindow(windowHandle);
    }

    public void setTitle(String title){
        glfwSetWindowTitle(windowHandle,title);
    }

    public void render(){
        glfwSwapBuffers(windowHandle);
    }
    public boolean shouldClose(){
        return glfwWindowShouldClose(windowHandle);


    }
}
