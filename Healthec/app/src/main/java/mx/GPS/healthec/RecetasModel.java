package mx.GPS.healthec;

public class RecetasModel {
    private int idReceta;

    private String nombreReceta;

    private String pasosReceta;

    private String ingredientesReceta;

    public RecetasModel(int idReceta, String nombreReceta, String pasosReceta, String ingredientesReceta) {
        this.idReceta = idReceta;
        this.nombreReceta = nombreReceta;
        this.pasosReceta = pasosReceta;
        this.ingredientesReceta = ingredientesReceta;
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
}