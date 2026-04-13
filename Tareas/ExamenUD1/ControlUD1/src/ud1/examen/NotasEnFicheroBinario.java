package ud1.examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class NotasEnFicheroBinario {
	public static final String RUTA_FICHERO_BIN = "notas.bin";
	
	public static void main(String[] args) {
		final int NUM_ALUMNOS = 21;
		// Sitios del 1 al 21 como código de control:
		int[] sitios = IntStream.rangeClosed(1, NUM_ALUMNOS).toArray();
		double[] notas;
		
		File file = new File(RUTA_FICHERO_BIN);
		
		if (!file.exists() || !file.canRead()) {
			creaFicheroBinNotasRandom(RUTA_FICHERO_BIN,sitios);
		}
		
		// AQUÍ TU PARTE PARA MODIFICAR LA NOTA DEL ALUMNO EN TU PUESTO RESTANDO 1
		// ASÍ EL PUESTO 1 SERÁ LA POSICIÓN 0 DEL ARRAY/FICHERO
		// EN EL FICHERO VA UN int CON EL NÚMERO DEL PUESTO Y A CONTINUACIÓN UN double CON LA NOTA
		// Y ASÍ 21 VECES (21 REGISTROS)
		
		
		
		notas = leeFicheroBin(RUTA_FICHERO_BIN,sitios);
		System.out.println(Arrays.toString(notas));
		
		System.out.printf("Un int ocupa %d bytes\n",Integer.BYTES);
		System.out.printf("Un double ocupa %d bytes\n",Double.BYTES);
	}
	
	public static double[] leeFicheroBin(String RUTA, final int[] sitios) {
		double[] notas = new double[sitios.length];
		
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(RUTA)))
		{
			int contador = 1;
			while (contador<=sitios.length) {
				int sitio = entrada.readInt();
				double nota = entrada.readDouble();
				if (sitio==contador) notas[contador-1] = nota;
				else {
					notas[contador] = 0;
					System.err.println("Leído sitio incorrecto " + sitio + " en vez de " + contador);
				}
				contador++;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado " + RUTA);
		} catch (IOException e) {
		System.err.println("Error leyendo de fichero " + RUTA);
		
		}
		return notas;
	}
	
	public static boolean creaFicheroBinNotasRandom(String RUTA, final int[] sitios) {
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(RUTA)))
		{
			Random random = new Random();
			for (int sitio : sitios) {
				salida.writeInt(sitio);
				salida.writeDouble(  random.nextInt(101)/10.0 );
			}

		} catch (FileNotFoundException e) {
			System.err.println("Fichero no encontrado " + RUTA);
			return false;
		} catch (IOException e) {
			System.err.println("Error escribiendo en fichero " + RUTA);
			return false;
		}
		return true;
	}

}
