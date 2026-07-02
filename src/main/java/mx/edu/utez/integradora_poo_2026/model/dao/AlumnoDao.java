package mx.edu.utez.integradora_poo_2026.model.dao;

import mx.edu.utez.integradora_poo_2026.model.Alumno;
import mx.edu.utez.integradora_poo_2026.model.Mascota;
import mx.edu.utez.integradora_poo_2026.utils.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDao implements Dao<Alumno, Integer>{
    @Override
    public boolean create(Alumno entidad) {
        String sql = "INSERT INTO ALUMNOS(nombre, apellidos, edad, matricula, correoElectronico, sexo) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellidos());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getMatricula());
            ps.setString(5, entidad.getCorreoElectronico());
            ps.setString(6, entidad.getSexo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Alumno> getAll() {
        List<Alumno> datos = new ArrayList<>();
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM ALUMNO");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidos(rs.getString("especie"));
                a.setEdad(rs.getInt("edad"));
                a.setMatricula(rs.getString("matricula"));
                a.setCorreoElectronico(rs.getString("correoElectronico"));
                a.setSexo(rs.getString("sexo"));
                datos.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Alumno getById(Integer id) {
        String sql = "SELECT * FROM MASCOTAS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Mascota a = new Mascota();
                    a.setId(rs.getInt("id"));
                    a.setNombre(rs.getString("nombre"));
                    a.setEspecie(rs.getString("especie"));
                    a.setEdad(rs.getInt("edad"));
                    a.setPersonalidad(rs.getString("personalidad"));
                    a.setFoto(rs.getString("foto"));
                    a.setVacunada(rs.getInt("vacunada") == 1);
                    return a;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Mascota entidad) {
        String sql = "UPDATE MASCOTAS SET nombre = ?, especie = ?, edad = ?, personalidad = ?, foto = ?, vacunada = ? WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getEspecie());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getPersonalidad());
            ps.setString(5, entidad.getFoto());
            ps.setInt(6, entidad.isVacunada() ? 1 : 0);
            ps.setInt(7, entidad.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM MASCOTAS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
