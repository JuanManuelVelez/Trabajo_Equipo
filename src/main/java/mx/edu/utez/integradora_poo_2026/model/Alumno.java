package mx.edu.utez.integradora_poo_2026.model;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private String matricula;
    private String correoElectronico;
    private String sexo;

    public Alumno(int id, String nombre, String especie, int edad, String personalidad, String correoElectronico, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = especie;
        this.edad = edad;
        this.matricula = personalidad;
        this.correoElectronico = correoElectronico;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return id + ',' + nombre + ',' + apellidos + ',' + edad
                + ',' + matricula + ',' + correoElectronico + ',' + sexo;
    }
}
