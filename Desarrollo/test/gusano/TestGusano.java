package gusano;

import java.io.File;

import org.junit.Test;

public class TestGusano {
	private static String archivoIn = "Preparacion de Prueba/Lote de Prueba/Entrada/";
	private static String archivoOut = "Ejecuccion de Prueba/Salida Obtenida/";

	@Test
	public void testEnunciado() {
		Gusano gusano = new Gusano(new File(archivoIn + "00_Enunciado.in"), new File(archivoOut + "00_Enunciado.out"));
		gusano.resolver();
		gusano.grabarArchivo();
	}
}
