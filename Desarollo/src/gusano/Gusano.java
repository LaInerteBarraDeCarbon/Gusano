package gusano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase que resuelve el ejercicio del virus de gusano. <br>
 */
public class Gusano extends EjercicioOIA {
	/**
	 * Matriz de Floyd. <br>
	 */
	private int[][] matrizFloyd;
	/**
	 * Cantidad de nodos del grafo. <br>
	 */
	private int cantidadNodos;
	/**
	 * Matriz de adyacencia del grafo. <br>
	 */
	private int[][] matrizAdyacencia;
	/**
	 * Lista de máquinas infectadas y el tiempo que tardó en llegar el virus.
	 * <br>
	 */
	private Map<Integer, Integer> maquinasInfectadas = new HashMap<Integer, Integer>();
	/**
	 * Máquina que desató el virus. <br>
	 */
	int maquinaOriginal;

	public Gusano(final File entrada, final File salida) {
		super(entrada, salida);
		try {
			this.leerArchivo(super.entrada);
		} catch (IOException e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		this.matrizFloyd = new Floyd(this.matrizAdyacencia).getMatrizFloyd();
		int contador = 0;
		int i = 0;
		Floyd floyd = new Floyd(matrizAdyacencia);
		floyd.mostarMatriz();
		while (i < this.cantidadNodos && contador != maquinasInfectadas.size()) {
			contador = 0;
			for (Iterator<Map.Entry<Integer, Integer>> iterator = maquinasInfectadas.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry<Integer, Integer> entry = iterator.next();
				if (this.matrizFloyd[i][entry.getKey()] == entry.getValue()) {
					contador++;
				}
			}
			i++;
		}
		this.maquinaOriginal = i;
	}

	/**
	 * Lee el archivo de entrada. <br>
	 * 
	 * @param archivo
	 *            Archivo de entrada. <br>
	 * @throws IOException
	 *             Si el archivo no existe, tira error. <br>
	 */
	private void leerArchivo(final File archivo) throws IOException {
		try {
			Scanner sc = new Scanner(archivo);
			int nodoOrigen;
			int nodoFin;
			int pesoArista;
			int nodoActual;
			int maquinaFin;
			int tiempo;
			int cantidadAristas = sc.nextInt();
			Set<Integer> nodos = new HashSet<Integer>();
			if (cantidadAristas > 30000) {
				this.limiteSuperado("cantidad de enlaces de red");
			}
			for (int j = 0; j < cantidadAristas; j++) {
				nodoActual = sc.nextInt();
				if (cantidadAristas > 1000 || cantidadAristas < 1) {
					this.limiteSuperado("nodo origen" + (j + 1));
				}
				nodos.add(nodoActual);
				sc.nextInt();
				nodoActual = sc.nextInt();
				if (cantidadAristas > 1000 || cantidadAristas < 1) {
					this.limiteSuperado("nodo fin" + (j + 1));
				}
				nodos.add(nodoActual);
			}
			sc.close();
			this.cantidadNodos = nodos.size();
			this.matrizAdyacencia = new int[this.cantidadNodos][this.cantidadNodos];
			sc = new Scanner(archivo);
			sc.nextInt();
			for (int i = 0; i < cantidadAristas; i++) {
				nodoOrigen = sc.nextInt();
				pesoArista = sc.nextInt();
				if (cantidadAristas > 50 || cantidadAristas < 1) {
					this.limiteSuperado("peso arista" + (i + 1));
				}
				nodoFin = sc.nextInt();
				this.matrizAdyacencia[nodoOrigen - 1][nodoFin - 1] = pesoArista;
				this.matrizAdyacencia[nodoFin - 1][nodoOrigen - 1] = pesoArista;
			}
			int cantidadMaquinasInfectadas = sc.nextInt();
			if (cantidadAristas > 50 || cantidadAristas < 1) {
				this.limiteSuperado("cantidad de maquinas infectadas");
			}
			for (int x = 0; x < cantidadMaquinasInfectadas; x++) {
				maquinaFin = sc.nextInt();
				tiempo = sc.nextInt();
				this.maquinasInfectadas.put(maquinaFin - 1, tiempo);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Graba el resultado del ejercicio. <br>
	 */
	public void grabarArchivo() {
		PrintWriter salida;
		try {
			salida = new PrintWriter(new FileWriter(super.salida));
			salida.println(this.maquinaOriginal);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Indica que supera el límite aceptado. <br>
	 */
	private void limiteSuperado(final String text) {
		throw new ArithmeticException("Cantidad superada de " + text + ".");
	}
}
