package mx.edu.utez.integradora_poo_2026.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.integradora_poo_2026.model.Alumno;
import mx.edu.utez.integradora_poo_2026.model.dao.AlumnoDao;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AlumnoServlet", value = "/alumno")
public class AlumnoServlet extends HttpServlet {

    private final AlumnoDao alumnoDao = new AlumnoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumno> lista = alumnoDao.getAll();
        request.setAttribute("listaAlumnos", lista);
        request.getRequestDispatcher("gestion-alumnos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            int id =  Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String matricula = request.getParameter("matricula");
            String correoElectronico = request.getParameter("correoElectronico");
            String sexo = request.getParameter("sexo");


            Alumno nuevaALumno = new Alumno();
            nuevaALumno.setId(id);
            nuevaALumno.setNombre(nombre);
            nuevaALumno.setApellidos(apellidos);
            nuevaALumno.setEdad(edad);
            nuevaALumno.setMatricula(matricula);
            nuevaALumno.setCorreoElectronico(correoElectronico);
            nuevaALumno.setSexo(sexo);

            alumnoDao.create(nuevaALumno);
        } catch (NumberFormatException e) {
            System.err.println("Error al transformar datos numéricos en el registro: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect("alumno");
    }
}