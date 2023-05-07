package mx.GPS.healthec.modelos;

public class Ingredientes {
    private String Titulo;
    private int Imagen, Clave;

    public Ingredientes(String titulo, int imagen, int clave) {
        Titulo = titulo;
        Imagen = imagen;
        Clave = clave;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }

    public int getClave() {
        return Clave;
    }

    public void setClave(int clave) {
        Clave = clave;
    }
}
