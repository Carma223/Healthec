package mx.GPS.healthec;

public class UserModel {

    //Atributos de la clase usuario
    private int idUser;
    private String email;
    private String password;
    //Constructor de la clase Usuario
    public UserModel(int idUser, String email, String password) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
    }
    //Constructor vacío de la clase usuario
    public UserModel() {
    }

    //toString es necesario para imprimir el contenido de objetos de la clase
    @Override
    public String toString() {
        return "UserModel{" +
                "idUser=" + idUser +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //getters and setters de los atributos de la clase
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
