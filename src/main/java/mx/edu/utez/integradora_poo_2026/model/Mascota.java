package mx.edu.utez.integradora_poo_2026.model;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private int edad;
    private String personalidad;
    private String foto;
    private boolean vacunada;

    public Mascota() {
    }

    public Mascota(int id, String nombre, String especie, int edad, String personalidad, String foto, boolean vacunada) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.personalidad = personalidad;
        this.foto = foto;
        this.vacunada = vacunada;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isVacunada() {
        return vacunada;
    }

    public void setVacunada(boolean vacunada) {
        this.vacunada = vacunada;
    }

    @Override
    public String toString() {
        return id + ',' + nombre + ',' + especie + ',' + edad
                + ',' + personalidad + ',' + foto + ',' + vacunada;
    }
}
