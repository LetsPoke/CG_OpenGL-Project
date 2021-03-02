package projekt;

//Alle Operationen �ndern das Matrixobjekt selbst und geben das eigene Matrixobjekt zur�ck
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	float[][] matrixArray;

	public Matrix4() {
		// TODO mit der Identit�tsmatrix initialisieren
		matrixArray = new float[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1},
		};

	}

	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
		this.matrixArray = copy.matrixArray.clone();
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzuf�gen

		float a = 0, b = 0;
		//a = (far + near) / (near - far);
		//b = (2*far * near) / (near - far);

		a = (-far -near) / (far-near);
		b = ((-2*near)*far) / (far-near);

		this.matrixArray = new float[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,a,b},
				{0,0,-1,0},
		};
	}

	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einf�gen
		float[][] newMatrixArray = new float[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				for(int k = 0; k < 4; k++){
					newMatrixArray[i][j] += other.matrixArray[i][k] * this.matrixArray[k][j];
				}
			}
		}
		this.matrixArray = newMatrixArray;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.matrixArray = new float[][]{
				{1,0,0,x},
				{0,1,0,y},
				{0,0,1,z},
				{0,0,0,1},
		};
		this.multiply(newMat);
		return this;
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichm��ige Skalierung um Faktor "uniformFactor" zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		float s = uniformFactor;
		newMat.matrixArray = new float[][]{
				{s,0,0,0},
				{0,s,0,0},
				{0,0,s,0},
				{0,0,0,1},
		};
		this.multiply(newMat);
		return this;
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichf�rmige Skalierung zu this hinzuf�gen
		Matrix4 newMat = new Matrix4();
		newMat.matrixArray = new float[][]{
				{sx,0,0,0},
				{0,sy,0,0},
				{0,0,sz,0},
				{0,0,0,1},
		};
		this.multiply(newMat);
		return this;
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzuf�gen
		float cos = (float)Math.cos(angle);
		float sin = (float)Math.sin(angle);

		Matrix4 newMat = new Matrix4();
		newMat.matrixArray = new float[][]{
				{1,0,0,0},
				{0,cos,-sin,0},
				{0,sin,cos,0},
				{0,0,0,1},
		};
		this.multiply(newMat);
		return this;
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzuf�gen
		float cos = (float)Math.cos(angle);
		float sin = (float)Math.sin(angle);

		Matrix4 newMat = new Matrix4();
		newMat.matrixArray = new float[][]{
				{cos,0,-sin,0},
				{0,1,0,0},
				{sin,0,cos,0},
				{0,0,0,1},
		};
		this.multiply(newMat);

		return this;
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzuf�gen
		float cos = (float)Math.cos(angle);
		float sin = (float)Math.sin(angle);

		Matrix4 newMat = new Matrix4();
		newMat.matrixArray = new float[][]{
				{cos,-sin,0,0},
				{sin,cos,0,0},
				{0,0,1,0},
				{0,0,0,1},
		};
		this.multiply(newMat);

		return this;
	}

	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gef�llt) herausgeben
		float[] values = new float[16];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				values[i*4 + j] = this.matrixArray[j][i];
			}
		}
		return values;
	}
}
