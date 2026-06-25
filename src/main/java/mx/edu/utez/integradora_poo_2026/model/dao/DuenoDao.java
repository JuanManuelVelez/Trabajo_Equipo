package mx.edu.utez.integradora_poo_2026.model.dao;

import mx.edu.utez.integradora_poo_2026.model.Dueno;
import mx.edu.utez.integradora_poo_2026.model.Mascota;
import mx.edu.utez.integradora_poo_2026.utils.DuenoCSVManager;

import java.util.ArrayList;
import java.util.List;

public class DuenoDao implements Dao<Dueno, Integer>{
    @Override
    public boolean create(Dueno entidad) {
        String[] nuevosDatos = empaquetarDatos(entidad);
        DuenoCSVManager.addToCSV(nuevosDatos);
        return true;
    }

    @Override
    public List<Dueno> getAll() {
        List<Dueno> listaDuenos = new ArrayList<>();
        List<String[]> lineasCsv = DuenoCSVManager.readCSV();
        for (String[] fila : lineasCsv) {
            if (fila.length >= 7) {
                try {
                    if (fila[0].equalsIgnoreCase("id")) continue;
                    listaDuenos.add(desempaquetarDatos(fila));
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear el ID del dueño: " + fila[0]);
                }
            }
        }
        return listaDuenos;
    }

    @Override
    public Dueno getById(Integer id) {
        List<String[]> lineasCsv = DuenoCSVManager.readCSV();
        for (String[] fila : lineasCsv) {
            if (fila.length >= 7 && fila[0].trim().equals(String.valueOf(id))) {
                return desempaquetarDatos(fila);
            }
        }
        return null;
    }

    @Override
    public boolean update(Dueno entidad) {
        String[] datosActualizados = empaquetarDatos(entidad);
        return DuenoCSVManager.updateRow(String.valueOf(entidad.getId()), datosActualizados);
    }

    @Override
    public boolean delete(Integer id) {
        return DuenoCSVManager.deleteRow(String.valueOf(id));
    }

    public boolean login(String correo, String contrasena) {
        List<String[]> lineasCsv = DuenoCSVManager.readCSV();
        for (String[] fila : lineasCsv) {
            if (fila.length >= 6) {
                String emailCsv = fila[4].trim();
                String passCsv = fila[5].trim();
                if (emailCsv.equalsIgnoreCase(correo.trim()) && passCsv.equals(contrasena)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String[] empaquetarDatos(Dueno entidad) {
        return new String[] {
                String.valueOf(entidad.getId()),
                entidad.getNombre() != null ? entidad.getNombre() : "",
                entidad.getApellidos() != null ? entidad.getApellidos() : "",
                entidad.getMascotas() != null ? entidad.getMascotasId() : "",
                entidad.getCorreo() != null ? entidad.getCorreo() : "",
                entidad.getContrasena() != null ? entidad.getContrasena() : "",
                entidad.getFoto_perfil() != null ? entidad.getFoto_perfil() : "",
                entidad.getCodigo_recuperacion() != null ? entidad.getCodigo_recuperacion() : ""
        };
    }

    private Dueno desempaquetarDatos(String[] fila) {
        Dueno dueno = new Dueno();
        dueno.setId(Integer.parseInt(fila[0]));
        dueno.setNombre(fila[1]);
        dueno.setApellidos(fila[2]);

        List<Mascota> listaMascotas = new ArrayList<>();
        if (fila.length > 3 && !fila[3].trim().isEmpty()) {
            String[] idsMascotas = fila[3].split(",");
            for (String idMascota : idsMascotas) {
                try {
                    Mascota m = new Mascota();
                    m.setId(Integer.parseInt(idMascota.trim()));
                    listaMascotas.add(m);
                } catch (NumberFormatException ignored) {}
            }
        }
        dueno.setMascotas(listaMascotas);

        dueno.setCorreo(fila.length > 4 ? fila[4] : "");
        dueno.setContrasena(fila.length > 5 ? fila[5] : "");
        dueno.setFoto_perfil(fila.length > 6 ? fila[6] : "");
        dueno.setCodigo_recuperacion(fila.length > 7 ? fila[7] : "");

        return dueno;
    }
}
