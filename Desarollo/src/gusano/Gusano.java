package gusano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Gusano extends EjercicioOIA {
	private Floyd matriz;
	private int cantidadNodos;
	private int[][] matrizAdyacencia;
	private Map<Integer, Integer> maquinasInfectadas = new HashMap<Integer, Integer>();

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

	}

	public void leerArchivo(final File path) throws IOException {
		try {
			Scanner sc = new Scanner(path);
			int nodoOrigen;
			int nodoFin;
			int pesoArista;
			int nodoActual;
			int cantidadAristas = sc.nextInt();
			Set<Integer> nodos = new LinkedHashSet<Integer>();
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
			sc = new Scanner(path);
			for (int i = 0; i < cantidadAristas; i++) {
				nodoOrigen = sc.nextInt();
				pesoArista = sc.nextInt();
				if (cantidadAristas > 50 || cantidadAristas < 1) {
					this.limiteSuperado("peso arista" + (i + 1));
				}
				nodoFin = sc.nextInt();
				this.matrizAdyacencia[nodoOrigen][nodoFin] = pesoArista;
			}
			sc.close();
		} catch (FileNotFoundException e) {
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
