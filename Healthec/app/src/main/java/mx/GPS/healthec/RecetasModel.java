package mx.GPS.healthec;

public class RecetasModel {
    private int idReceta;

    private String nombreReceta;

    private String pasosReceta;

    private String ingredientesReceta;
    private int imagenReceta;

    public RecetasModel(int idReceta, String nombreReceta, String pasosReceta, String ingredientesReceta, int imagenReceta) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.pasosReceta = pasosReceta;
        this.ingredientesReceta = ingredientesReceta;
        this.imagenReceta = imagenReceta;
    }

    public RecetasModel() {
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "idReceta=" + idReceta +
                ", nombreReceta='" + nombreReceta + '\'' +
                ", pasosReceta='" + pasosReceta + '\'' +
                ", ingredientesReceta='" + ingredientesReceta + '\'' +
                '}';
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getPasosReceta() {
        return pasosReceta;
    }

    public void setPasosReceta(String pasosReceta) {
        this.pasosReceta = pasosReceta;
    }

    public String getIngredientesReceta() {
        return ingredientesReceta;
    }

    public void setIngredientesReceta(String ingredientesReceta) {
        this.ingredientesReceta = ingredientesReceta;
    }

    public int getImagenReceta() {
        return imagenReceta;
    }

    public void setImagenReceta(int imagenReceta) {
        this.imagenReceta = imagenReceta;
    }
}