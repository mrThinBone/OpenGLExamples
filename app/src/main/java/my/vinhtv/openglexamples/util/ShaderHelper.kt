package my.vinhtv.openglexamples.util

import android.opengl.GLES20.*
import android.util.Log

/**
 * Created by vinh.trinh on 3/15/2018.
 */
object ShaderHelper {

    private val TAG = "ShaderHelper"

    fun compileVertexShader(shaderCode: String) = compileShader(GL_VERTEX_SHADER, shaderCode)

    fun compileFragmentShader(shaderCode: String) = compileShader(GL_FRAGMENT_SHADER, shaderCode)

    private fun compileShader(type: Int, shaderCode: String): Int {
        val shaderObjectId = glCreateShader(type)
        if(shaderObjectId == 0) {
            Log.e(TAG, "Could not create new shader")
            return 0
        }
        // uploading & compiling the shader source code
        glShaderSource(shaderObjectId, shaderCode)
        glCompileShader(shaderObjectId)
        val compileStatus = IntArray(1)
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0)
        Log.d(TAG, "Result of compiling: ${glGetShaderInfoLog(shaderObjectId)}")

        if(compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId)
            Log.e(TAG, "Compilation of shader failed")
            return 0
        }
        return shaderObjectId
    }

    fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
        // create a new program object and attaching shaders
        val programObjectId = glCreateProgram()
        if(programObjectId == 0) {
            Log.e(TAG, "Could not create new program")
            return 0
        }
        glAttachShader(programObjectId, vertexShaderId)
        glAttachShader(programObjectId, fragmentShaderId)

        // linking the program
        glLinkProgram(programObjectId)
        val linkStatus = IntArray(1)
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)
        Log.d(TAG, "Result of linking program: ${glGetProgramInfoLog(programObjectId)}")

        if(linkStatus[0] == 0) {
            glDeleteProgram(programObjectId)
            Log.e(TAG, "Linking of program failed")
            return 0
        }
        return programObjectId
    }

    fun validProgram(programObjectId: Int): Boolean {
        glValidateProgram(programObjectId)

        val validateStatus = IntArray(1)
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0)
        Log.d(TAG, "Results of validating programs: ${validateStatus[0]}\n Log: ${glGetProgramInfoLog(programObjectId)}")
        return validateStatus[0] != 0
    }
}