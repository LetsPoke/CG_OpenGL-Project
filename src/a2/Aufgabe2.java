package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// folgende Zeile l�d automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] form = {
							-0.75f, -0.75f, 1.0f, 0.0f, 0.0f,
							0.75f, -0.75f, 0.0f, 1.0f, 0.0f,
							0.0f, 0.75f, 0.0f, 0.0f, 1.0f
		};
//		float[] form2 = {
//				-0.5f, -0.5f, 0.0f,
//				0.5f, -0.5f, 0.0f,
//				0.0f, 0.5f, 0.0f
//		};
		int vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		int vboID = glGenBuffers();

		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, form, GL_STATIC_DRAW);

		glVertexAttribPointer(0, 2, GL_FLOAT, false, 20, 0);
		glEnableVertexAttribArray(0);
		glBindVertexArray(vaoID);

		glBufferData(GL_ARRAY_BUFFER, form, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,false, 20, 8);
		glEnableVertexAttribArray(1);
		glBindVertexArray(vaoID);

	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfl�che leeren

		// hier vorher erzeugte VAOs zeichnen
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}

	@Override
	public void destroy() {
	}
}
