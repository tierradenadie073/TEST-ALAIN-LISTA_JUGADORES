package packModelo1;

public class Jugador1 {
    private int id;
    private String nombre;
    private int dorsal;
    private double altura;

    public Jugador1(int id, String nombre, int dorsal, double altura) {
        this.id = id;
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.altura = altura;
    }

    // Getters y setters

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

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}

