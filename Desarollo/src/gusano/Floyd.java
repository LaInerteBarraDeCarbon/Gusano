package gusano;

public class Floyd {
	/**
	 * MatrizAdyacencia de ponderación. <br>
	 */
	private int[][] matrizPonderacion;
	/**
	 * Tamaño de la matriz.
	 * <p>
	 * Es la cantidad total de nodos del grafo. <br>
	 */
	private int tamaño;

	/**
	 * Crea una matriz de ponderación en la que se muestran todas las posibles
	 * uniones entre nodos. <br>
	 * 
	 * @param matrizAdyacencia
	 *            MatrizAdyacencia de adyacencia de un grafo. <br>
	 */
	public Floyd(final int[][] matrizAdyacencia) {
		for (int i = 0; i < matrizAdyacencia.length; i++) {
			for (int j = 0; j < matrizAdyacencia.length; j++) {
				if (matrizAdyacencia[i][j] == 0) {
					matrizAdyacencia[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		this.tamaño = matrizAdyacencia.length;
		this.matrizPonderacion = matrizAdyacencia;
		for (int k = 0; k < this.tamaño; k++) {
			for (int i = 0; i < this.tamaño; i++) {
				for (int j = 0; j < this.tamaño; j++) {
					if (matrizAdyacencia[i][k] != Integer.MAX_VALUE && matrizAdyacencia[k][j] != Integer.MAX_VALUE
							&& Math.min(matrizAdyacencia[i][j],
									matrizAdyacencia[i][k] + matrizAdyacencia[k][j]) != matrizAdyacencia[i][j]) {
						matrizPonderacion[i][j] = matrizAdyacencia[i][k] + matrizAdyacencia[k][j];
						matrizPonderacion[j][i] = matrizAdyacencia[i][k] + matrizAdyacencia[k][j];
					}
				}
			}
		}
	}

	/**
	 * Devuelve la matriz de ponderación. <br>
	 * 
	 * @return MatrizAdyacencia de ponderación. <br>
	 */
	public int[][] getMatrizFloyd() {
		return this.matrizPonderacion;
	}

	/**
	 * Muestra la matriz de clausura transitiva. <br>
	 */
	public void mostarMatriz() {
		System.out.println("Matriz de ponderación:\n");
		System.out.print(" ");
		for (int v = 0; v < this.tamaño; v++) {
			System.out.print("\t" + (v + 1));
		}
		System.out.println();
		for (int v = 0; v < this.tamaño; v++) {
			System.out.print((v + 1));
			for (int w = 0; w < this.tamaño; w++) {
				if (this.matrizPonderacion[v][w] == 0 || v == w || this.matrizPonderacion[v][w] == Integer.MAX_VALUE) {
					System.out.print("\t" + '\u221e');
				} else {
					System.out.print("\t" + this.matrizPonderacion[v][w]);
				}
			}
			System.out.println();
		}
		System.out.print("\n");
	}

}
