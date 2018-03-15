package my.vinhtv.openglexamples.renderer

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by vinh.trinh on 3/15/2018.
 */
class AirHockeyRenderer: GLSurfaceView.Renderer {

    private val tableVertices: Array<Float> = arrayOf(0f, 0f,
            0f, 14f,
            9f, 14f,
            9f, 14f,
            0f, 0f,
            9f, 0f)

    private val tableVerticesWithTriangles: Array<Pair<Float, Float>> = arrayOf(
            Pair(0f, 0f), Pair(0f, 14f), Pair(9f, 14f), /** Triangle 1 */
            Pair(9f, 14f), Pair(0f, 0f), Pair(9f, 0f) /** Triangle 2*/
    )

    private val middleLine: Array<Pair<Float, Float>> = arrayOf(
            Pair(0f, 7f), Pair(9f, 7f)
    )

    private val mallets: Array<Pair<Float, Float>> = arrayOf(
            Pair(4.5f, 2f), Pair(4.5f, 12f)
    )

    // copying memory from Java's memory heap to the Native Memory Heap
    private val BYTES_PER_FLOAT: Int = 4
    private val vertexData: FloatBuffer = ByteBuffer.allocateDirect(tableVerticesWithTriangles.size * 2 * BYTES_PER_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()

    constructor() {
        vertexData.put(tableVertices.toFloatArray())
    }

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        // red, blue, green, alpha
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f)
    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        /** tell OpenGL the size of surface it has available for rendering */
        // set the OpenGL viewport to fill the entire surface
        glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(p0: GL10?) {
        /** this will wipe out all colors on the screen and fill the screen with
         * color previously defined by our call to glClearColor() */
        // clear the rendering surface
        glClear(GL_COLOR_BUFFER_BIT)
    }

}