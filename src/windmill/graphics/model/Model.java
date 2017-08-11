package windmill.graphics.model;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL15.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ElsE on 11.08.2017.
 */
public class Model {

    private int vbo;
    private int ibo;
    private int size;

    public Model(){
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }

    public void bufferVertices(Vertex[] vertices, int[] indices){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);

        for (Vertex vertex : vertices){
            buffer.put(vertex.getPos().x);
            buffer.put(vertex.getPos().y);
            buffer.put(vertex.getPos().z);
        }
        buffer.flip();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER,0);

        IntBuffer ibuffer = BufferUtils.createIntBuffer(indices.length);
        ibuffer.put(indices);
        ibuffer.flip();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, ibuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        size = indices.length;
    }

    public int getIbo(){
        return ibo;
    }

    public int getVbo(){
        return vbo;
    }
    public int getSize(){
        return size;
    }

    public static Model loadModel(String fileName){

        Model res = new Model();
        ArrayList<Vertex> vertices =new ArrayList<Vertex>();
        ArrayList<Integer> indices = new ArrayList<Integer>();
        BufferedReader reader = null;

        try{
            reader = new BufferedReader(new FileReader("./res/models/"+fileName));
            String line;
            while ((line = reader.readLine()) != null){
                if (line.startsWith("v ")){
                    String[] values = line.split(" ");
                    vertices.add(new Vertex(Float.parseFloat(values[1]),Float.parseFloat(values[2]),Float.parseFloat(values[3])));
                } else if (line.startsWith("f ")){
                    String[] values = line.split(" ");
                    indices.add(Integer.parseInt(values[1]) -1);
                    indices.add(Integer.parseInt(values[2]) -1);
                    indices.add(Integer.parseInt(values[3]) -1);
                }

            }
            reader.close();

        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        Vertex[] v = new Vertex[vertices.size()];
        for (int i=0;i<v.length;i++){
            v[i] = vertices.get(i);
        }
        int[] in = new int[indices.size()];
        for (int j = 0; j<in.length; j++){
            in[j] = indices.get(j);
        }
        res.bufferVertices(v,in);

        return res;
    }
}
