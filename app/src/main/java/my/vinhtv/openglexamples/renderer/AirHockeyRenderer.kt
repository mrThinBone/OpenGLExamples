package my.vinhtv.openglexamples.renderer

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import my.vinhtv.openglexamples.R
import my.vinhtv.openglexamples.util.ShaderHelper
import my.vinhtv.openglexamples.util.TextResourceReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by vinh.trinh on 3/15/2018.
 */
class AirHockeyRenderer(private val context: Context): GLSurfaceView.Renderer {

    private val tableVerticesWithTriangles: Array<Float> = arrayOf(
            /** Triangle 1 */
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,
            /** Triangle 2*/
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,

            /** Middle line */
            -0.5f, 0f,
            0.5f, 0f,

            /** Mallets */
            0f, -0.25f,
            0f, 0.25f)
    // we define a point as (x, y) so component count is 2
    private val POSITION_COMPONTENT_COUNT = 2

    // copying memory from Java's memory heap to the Native Memory Heap
    private val BYTES_PER_FLOAT: Int = 4
    private val vertexData: FloatBuffer = ByteBuffer.allocateDirect(tableVerticesWithTriangles.size * BYTES_PER_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer().put(tableVerticesWithTriangles.toFloatArray())

    private var program: Int = 0

    // location of a uniform (simple_fragment_shader.glsl)
    private val U_COLOR = "u_Color"
    private var uColorLocation:Int = 0

    // location of an attribute (simple_vertex_shader.glsl)
    private val A_POSITION = "a_Position"
    private var aPositionLocation: Int = 0

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        // red, blue, green, alpha
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        val vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader)
        val fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader)
        val vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource)
        val fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource)
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader)
        if (program != 0 && ShaderHelper.validProgram(program)) {
            glUseProgram(program)

            // getting the location of a uniform
            uColorLocation = glGetUniformLocation(program, U_COLOR)
            // getting the location of an attribute
            aPositionLocation = glGetAttribLocation(program, A_POSITION)

            // associating an array of vertex data with an attribute so that OpenGL knows where
            // to get data for the attribute a_Position
            vertexData.position(0)
            // glVertexAttribPointer (reference: page 50 of ebook)
            glVertexAttribPointer(aPositionLocation, POSITION_COMPONTENT_COUNT, GL_FLOAT, false, 0, vertexData)

            // enabling the vertex array
            glEnableVertexAttribArray(aPositionLocation)
        }
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

        /** drawing to the screen */
        // drawing the table
        glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f)
        // starting as 0, and read in 6 vertices
        glDrawArrays(GL_TRIANGLES, 0, 6)

        // drawing the dividing line
        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f)
        glDrawArrays(GL_LINES, 6, 2)

        // drawing the mallets as points
        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)
        glDrawArrays(GL_POINTS, 8, 1)
        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f)
        glDrawArrays(GL_POINTS, 9, 1)
    }

}