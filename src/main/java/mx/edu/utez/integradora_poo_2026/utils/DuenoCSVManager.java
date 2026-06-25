package mx.edu.utez.integradora_poo_2026.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DuenoCSVManager {

    private static final String FILE_PATH = "DUENO_BD.csv";

    public static List<String[]> readCSV() {
        List<String[]> todasLasLineas = new ArrayList<>();
        File archivo = new File(FILE_PATH);

        // Si el archivo no existe, lo creamos vacío de una vez en lugar de lanzar error
        if (!archivo.exists()) {
            try {
                File carpeta = archivo.getParentFile();
                if (carpeta != null && !carpeta.exists()) {
                    carpeta.mkdirs();
                }
                archivo.createNewFile();
                System.out.println("Archivo CSV inicializado por primera vez en: " + archivo.getAbsolutePath());
                return todasLasLineas; // Devuelve la lista vacía de forma segura
            } catch (IOException e) {
                System.err.println("No se pudo inicializar el archivo CSV: " + e.getMessage());
                return todasLasLineas;
            }
        }

        // Si ya existe, se lee normalmente
        try (CSVReader reader = new CSVReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            todasLasLineas = reader.readAll();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo en la ruta especificada: " + e.getMessage());
        } catch (CsvException e) {
            System.err.println("Error en el formato del CSV: " + e.getMessage());
        }
        return todasLasLineas;
    }

    public static void addToCSV(String[] nuevosDatos) {
        File archivo = new File(FILE_PATH);
        try {
            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo CSV creado por primera vez en: " + archivo.getAbsolutePath());
            }
            // El 'true' al final indica que se va a adjuntar (append) al archivo existente
            try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, StandardCharsets.UTF_8, true))) {
                writer.writeNext(nuevosDatos);
                writer.flush();
                System.out.println("Registro agregado correctamente.");
            }
        } catch (IOException e) {
            System.err.println("Error crítico al manejar el archivo CSV: " + e.getMessage());
        }
    }


    public static boolean updateRow(String id, String[] nuevosDatos) {
        List<String[]> todasLasLineas = readCSV();
        boolean encontrado = false;

        for (int i = 0; i < todasLasLineas.size(); i++) {
            String[] fila = todasLasLineas.get(i);
            // Validamos que la fila tenga elementos y que el ID coincida en la primera columna (posición 0)
            if (fila.length > 0 && fila[0].trim().equals(id.trim())) {
                todasLasLineas.set(i, nuevosDatos); // Reemplazamos los datos viejos por los nuevos
                encontrado = true;
                break; // Detener la búsqueda ya que los IDs deben ser únicos
            }
        }

        if (encontrado) {
            writeAllToCSV(todasLasLineas);
            System.out.println("Registro con ID " + id + " editado correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID: " + id);
        }

        return encontrado;
    }


    public static boolean deleteRow(String id) {
        List<String[]> todasLasLineas = readCSV();
        boolean encontrado = false;

        for (int i = 0; i < todasLasLineas.size(); i++) {
            String[] fila = todasLasLineas.get(i);
            if (fila.length > 0 && fila[0].trim().equals(id.trim())) {
                todasLasLineas.remove(i); // Removemos la fila de la lista en memoria
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            writeAllToCSV(todasLasLineas);
            System.out.println("Registro con ID " + id + " borrado correctamente.");
        } else {
            System.out.println("No se encontró ningún registro con el ID: " + id);
        }

        return encontrado;
    }


    private static void writeAllToCSV(List<String[]> lineas) {
        File archivo = new File(FILE_PATH);
        // Aquí NO usamos 'true' en el FileWriter para que limpie el archivo viejo y escriba la nueva lista
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivo, StandardCharsets.UTF_8))) {
            writer.writeAll(lineas);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error al escribir los cambios en el archivo CSV: " + e.getMessage());
        }
    }
}
