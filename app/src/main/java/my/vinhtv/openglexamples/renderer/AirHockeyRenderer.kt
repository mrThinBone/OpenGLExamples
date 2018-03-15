package my.vinhtv.openglexamples.renderer

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Created by vinh.trinh on 3/15/2018.
 */
class AirHockeyRenderer: GLSurfaceView.Renderer {

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