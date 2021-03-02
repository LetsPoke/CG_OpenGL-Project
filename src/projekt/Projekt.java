package projekt;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import lenz.opengl.Texture;

public class Projekt extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private ShaderProgram shaderProgram2;
	private float angle, angle2, angle3, angle4;
	float bla = 0.0f;

	Matrix4 projectionsM = new Matrix4(2f,100f);
	Matrix4 form1Klein1 = new Matrix4();
	Matrix4 form1Groﬂ = new Matrix4();
	Matrix4 form1Klein2 = new Matrix4();
	Matrix4 form1Klein3 = new Matrix4();
	Matrix4 form1Klein4 = new Matrix4();
	Matrix4 BaseMatrix = new Matrix4();
	Matrix4 form1Klein12 = new Matrix4();
	Matrix4 form1Klein31 = new Matrix4();
	Matrix4 form2 = new Matrix4();

	private int vaoID ;
	private int vaoID2 ;
	private int texID;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		shaderProgram2 = new ShaderProgram("projekt");

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		formcoords();
		formcoords2();

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren

		int viewLoc = glGetUniformLocation(shaderProgram.getId(), "projectionsM");
		glUniformMatrix4fv(viewLoc,false, projectionsM.getValuesAsArray());
	}

	protected void formcoords(){

		glUseProgram(shaderProgram.getId());

		float[] form = {

				-1.0f,0.0f,0.0f, //A
				0.0f,-1.0f,0.0f, //B
				0.0f,0.0f,1.0f, //E

				0.0f,-1.0f,0.0f, //B
				1.0f,0.0f,0.0f, //C
				0.0f,0.0f,1.0f, //E

				1.0f,0.0f,0.0f, //C
				0.0f,1.0f,0.0f, //D
				0.0f,0.0f,1.0f, //E

				0.0f,1.0f,0.0f, //D
				-1.0f,0.0f,0.0f, //A
				0.0f,0.0f,1.0f, //E

				0.0f,-1.0f,0.0f, //B
				-1.0f,0.0f,0.0f, //A
				0.0f,0.0f,-1.0f,//-E

				1.0f,0.0f,0.0f, //C
				0.0f,-1.0f,0.0f, //B
				0.0f,0.0f,-1.0f,//-E

				0.0f,1.0f,0.0f, //D
				1.0f,0.0f,0.0f, //C
				0.0f,0.0f,-1.0f,//-E

				-1.0f,0.0f,0.0f, //A
				0.0f,1.0f,0.0f, //D
				0.0f,0.0f,-1.0f,//-E
		};

		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		int vboID = glGenBuffers();

		//copy our vertices array in a buffer for OpenGL to use
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, form, GL_STATIC_DRAW);
		//then set the vertex attributes pointers
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		glBindVertexArray(vaoID);
		//color stuff below
		glBufferData(GL_ARRAY_BUFFER, form, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,false, 0, 8);
		glEnableVertexAttribArray(1);
		glBindVertexArray(vaoID);
	}
	protected void formcoords2(){

		glUseProgram(shaderProgram2.getId());

		float[] form2 = {

				-0.5f, -0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f,  0.5f, -0.5f,
				0.5f,  0.5f, -0.5f,
				-0.5f,  0.5f, -0.5f,
				-0.5f, -0.5f, -0.5f,

				-0.5f, -0.5f,  0.5f,
				0.5f, -0.5f,  0.5f,
				0.5f,  0.5f,  0.5f,
				0.5f,  0.5f,  0.5f,
				-0.5f,  0.5f,  0.5f,
				-0.5f, -0.5f,  0.5f,

				-0.5f,  0.5f,  0.5f,
				-0.5f,  0.5f, -0.5f,
				-0.5f, -0.5f, -0.5f,
				-0.5f, -0.5f, -0.5f,
				-0.5f, -0.5f,  0.5f,
				-0.5f,  0.5f,  0.5f,

				0.5f,  0.5f,  0.5f,
				0.5f,  0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f, -0.5f,  0.5f,
				0.5f,  0.5f,  0.5f,

				-0.5f, -0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f, -0.5f,  0.5f,
				0.5f, -0.5f,  0.5f,
				-0.5f, -0.5f,  0.5f,
				-0.5f, -0.5f, -0.5f,

				-0.5f,  0.5f, -0.5f,
				0.5f,  0.5f, -0.5f,
				0.5f,  0.5f,  0.5f,
				0.5f,  0.5f,  0.5f,
				-0.5f,  0.5f,  0.5f,
				-0.5f,  0.5f, -0.5f
		};

		vaoID2 = glGenVertexArrays();
		glBindVertexArray(vaoID2);
		int vboID2 = glGenBuffers();

		//copy our vertices array in a buffer for OpenGL to use
		glBindBuffer(GL_ARRAY_BUFFER, vboID2);
		glBufferData(GL_ARRAY_BUFFER, form2, GL_STATIC_DRAW);
		//then set the vertex attributes pointers
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		//glBindVertexArray(vaoID2);
		//color stuff below
//		glBufferData(GL_ARRAY_BUFFER, form2, GL_STATIC_DRAW);
//		glVertexAttribPointer(1, 3, GL_FLOAT,false, 0, 8);
//		glEnableVertexAttribArray(1);
//		glBindVertexArray(vaoID2);

		float[] textureCoords = {
				  0.0f, 0.0f,
				  1.0f, 0.0f,
				  1.0f, 1.0f,
				  1.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 0.0f,

				  0.0f, 0.0f,
				  1.0f, 0.0f,
				  1.0f, 1.0f,
				  1.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 0.0f,

				  1.0f, 0.0f,
				  1.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 0.0f,
				  1.0f, 0.0f,

				  1.0f, 0.0f,
				  1.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 1.0f,
				  0.0f, 0.0f,
				  1.0f, 0.0f,

				  0.0f, 1.0f,
				  1.0f, 1.0f,
				  1.0f, 0.0f,
				  1.0f, 0.0f,
				  0.0f, 0.0f,
				  0.0f, 1.0f,

				  0.0f, 1.0f,
				  1.0f, 1.0f,
				  1.0f, 0.0f,
				  1.0f, 0.0f,
				  0.0f, 0.0f,
				  0.0f, 1.0f
		};

		int vboId3 = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId3);
		glBufferData(GL_ARRAY_BUFFER, textureCoords, GL_STATIC_DRAW);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(2);

		Texture texture = new Texture("texture2.jpg", 8, true);
		texID = texture.getId();
		glBindTexture(GL_TEXTURE_2D, texID);
		glGenerateMipmap(GL_TEXTURE_2D);
		//edit this for a different texture filtering mode
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST_MIPMAP_NEAREST);

		glBindVertexArray(vaoID2);
	}

	@Override
	public void update() {
		// Transformation durchf¸hren (Matrix anpassen)
		BaseMatrix = new Matrix4();
		BaseMatrix.translate(0.0f,0.0f,0.0f);

		angle += 0.05f;

		form1Groﬂ = new Matrix4();
		form1Groﬂ.multiply(BaseMatrix);
		form1Groﬂ.rotateY(angle);
		form1Groﬂ.translate(0.0f,0.0f,-4.0f);

		form1Klein1 = new Matrix4();
		form1Klein1.multiply(form1Groﬂ);
		form1Klein1.scale(0.33f);
		form1Klein1.rotateX(angle/2);
		form1Klein1.rotateZ(0.75f);
		form1Klein1.translate(0.0f,0.0f,-4f);

		form1Klein12 = new Matrix4();
		form1Klein12.multiply(form1Groﬂ);
		form1Klein12.scale(0.33f);
		form1Klein12.rotateX(angle/2);
		form1Klein12.rotateZ(-0.75f);
		form1Klein12.translate(0.0f,0.0f,-4f);

		form1Klein2 = new Matrix4();
		form1Klein2.multiply(form1Groﬂ);
		form1Klein2.scale(0.33f);
		form1Klein2.rotateY(angle/2);
		form1Klein2.translate(0.0f,0.0f,-4f);

		form1Klein3 = new Matrix4();
		form1Klein3.multiply(form1Groﬂ);
		form1Klein3.scale(0.33f);
		form1Klein3.rotateX(-angle/2);
		form1Klein3.rotateZ(-0.75f);
		form1Klein3.translate(0.0f,0.0f,-4f);

		form1Klein31 = new Matrix4();
		form1Klein31.multiply(form1Groﬂ);
		form1Klein31.scale(0.33f);
		form1Klein31.rotateX(-angle/2);
		form1Klein31.rotateZ(0.75f);
		form1Klein31.translate(0.0f,0.0f,-4f);

		form1Klein4 = new Matrix4();
		form1Klein4.multiply(form1Groﬂ);
		form1Klein4.scale(0.33f);
		form1Klein4.rotateY(-angle/2);
		form1Klein4.translate(0.0f,0.0f,-4f);

		form2 = new Matrix4();
		form2.scale(5.5f);
		form2.rotateY(-angle/4);
		form2.translate(0.0f,-9.0f,-15.0f);

	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		// Matrix an Shader ¸bertragen

		int loc= glGetUniformLocation(shaderProgram.getId(), "transMat");
		int loc2= glGetUniformLocation(shaderProgram2.getId(), "transMat");

		glBindVertexArray(vaoID);


		glUniformMatrix4fv(loc,false, form1Groﬂ.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein1.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein2.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein3.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein4.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein12.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glUniformMatrix4fv(loc,false, form1Klein31.getValuesAsArray());
		glDrawArrays(GL_TRIANGLES, 0, 24);

		glBindVertexArray(vaoID2);

		glUseProgram(shaderProgram2.getId());
		glUniformMatrix4fv(loc2,false, form2.getValuesAsArray());
		glBindTexture(GL_TEXTURE_2D, texID);
		glDrawArrays(GL_TRIANGLES, 0, 36);

		// VAOs zeichnen
	}

	@Override
	public void destroy() {
	}
}
