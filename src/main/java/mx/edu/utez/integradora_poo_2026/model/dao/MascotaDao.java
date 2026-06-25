package mx.edu.utez.integradora_poo_2026.model.dao;

import mx.edu.utez.integradora_poo_2026.model.Mascota;
import mx.edu.utez.integradora_poo_2026.utils.MascotaCSVManager;

import java.util.ArrayList;
import java.util.List;

public class MascotaDao implements Dao<Mascota, Integer>{
    @Override
    public boolean create(Mascota entidad) {
        String[] datos = {
                String.valueOf(entidad.getId()),
                entidad.getNombre(),
                entidad.getEspecie(),
                String.valueOf(entidad.getEdad()),
                entidad.getPersonalidad(),
                entidad.getFoto(),
                entidad.isVacunada() ? "1" : "0"
        };

        MascotaCSVManager.addToCSV(datos);
        return true; // Cambiado a true para indicar éxito
    }

    @Override
    public List<Mascota> getAll() {
        List<Mascota> datos = new ArrayList<>();
        List<String[]> lineas = MascotaCSVManager.readCSV();
        for(String[] linea : lineas) {
            if (linea.length < 7) continue;

            Mascota m = new Mascota();
            m.setId(Integer.parseInt(linea[0].trim()));
            m.setNombre(linea[1]);
            m.setEspecie(linea[2]);
            m.setEdad(Integer.parseInt(linea[3].trim()));
            m.setPersonalidad(linea[4]);
            m.setFoto(linea[5]);
            m.setVacunada(linea[6].trim().equals("1"));
            datos.add(m);
        }
        return datos;
    }

    @Override
    public Mascota getById(Integer id) {
        List<String[]> lineas = MascotaCSVManager.readCSV();
        for(String[] linea : lineas) {
            if(linea.length > 0 && linea[0].trim().equals(id.toString())) {
                Mascota m = new Mascota();
                m.setId(Integer.parseInt(linea[0].trim()));
                m.setNombre(linea[1]);
                m.setEspecie(linea[2]);
                m.setEdad(Integer.parseInt(linea[3].trim()));
                m.setPersonalidad(linea[4]);
                m.setFoto(linea[5]);
                m.setVacunada(linea[6].trim().equals("1"));
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean update(Mascota entidad) {
        String[] datosActualizados = {
                String.valueOf(entidad.getId()),
                entidad.getNombre(),
                entidad.getEspecie(),
                String.valueOf(entidad.getEdad()),
                entidad.getPersonalidad(),
                entidad.getFoto(),
                entidad.isVacunada() ? "1" : "0"
        };

        String idString = String.valueOf(entidad.getId());
        return MascotaCSVManager.updateRow(idString, datosActualizados);
    }

    @Override
    public boolean delete(Integer id) {
        String idString = String.valueOf(id);
        return MascotaCSVManager.deleteRow(idString);
    }
}
