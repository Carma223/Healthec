package mx.GPS.healthec.modelos;

public class Ingredientes {
    private String Titulo;
    private int Imagen, clave;

    public Ingredientes(String titulo, int imagen, int clave) {
        Titulo = titulo;
        Imagen = imagen;
        clave = clave;
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
        return clave;
    }

    public void setClave(int clave) {
        clave = clave;
    }
}
