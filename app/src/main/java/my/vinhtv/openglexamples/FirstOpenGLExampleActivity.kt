package my.vinhtv.openglexamples

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import my.vinhtv.openglexamples.renderer.FirstOpenGLRenderer

/**
 * Created by vinh.trinh on 3/15/2018.
 */
class FirstOpenGLExampleActivity: AppCompatActivity() {
    private var glSurfaceView: GLSurfaceView? = null
    private var rendererSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glSurfaceView = GLSurfaceView(this)
        glSurfaceView?.setEGLContextClientVersion(2)
        glSurfaceView?.setRenderer(FirstOpenGLRenderer())
        rendererSet = true
        setContentView(glSurfaceView)
    }

    override fun onPause() {
        super.onPause()
        if(rendererSet) glSurfaceView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        if(rendererSet) glSurfaceView?.onResume()
    }
}