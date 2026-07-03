package mx.edu.utez.integradora_poo_2026.model.dao;

import mx.edu.utez.integradora_poo_2026.model.Alumno;
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
        String sql = "INSERT INTO ALUMNOS(id,nombre, apellidos, edad, matricula, correoElectronico, sexo) VALUES(?,?, ?, ?, ?, ?, ?)";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entidad.getId());
            ps.setString(2, entidad.getNombre());
            ps.setString(3, entidad.getApellidos());
            ps.setInt(4, entidad.getEdad());
            ps.setString(5, entidad.getMatricula());
            ps.setString(6, entidad.getCorreoElectronico());
            ps.setString(7, entidad.getSexo());

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
             PreparedStatement ps = con.prepareStatement("SELECT * FROM ALUMNOS ORDER BY ID");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno m = new Alumno();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setApellidos(rs.getString("apellidos"));
                m.setEdad(rs.getInt("edad"));
                m.setMatricula(rs.getString("matricula"));
                m.setCorreoElectronico(rs.getString("correoElectronico"));
                m.setSexo(rs.getString("sexo"));

                datos.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    @Override
    public Alumno getById(Integer id) {
        String sql = "SELECT * FROM ALUMNOS WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno m = new Alumno();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setApellidos(rs.getString("apellidos"));
                    m.setEdad(rs.getInt("edad"));
                    m.setMatricula(rs.getString("matricula"));
                    m.setCorreoElectronico(rs.getString("correoElectronico"));
                    m.setSexo(rs.getString("sexo"));
                    return m;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Alumno entidad) {
        String sql = "UPDATE ALUMNOS SET nombre = ?, apellidos = ?, edad = ?, matricula = ?, correoElectronico = ?, sexo = ? WHERE id = ?";
        try (Connection con = SQLConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getApellidos());
            ps.setInt(3, entidad.getEdad());
            ps.setString(4, entidad.getMatricula());
            ps.setString(5, entidad.getCorreoElectronico());
            ps.setString(6, entidad.getSexo());
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
        String sql = "DELETE FROM ALUMNOS WHERE id = ?";
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
