package tn.esprit.guiapplication.models;

public class Reservation {

    public int id_reservation,phone;
    public String type_reservation,username,email;

    public Reservation(int id_reservation, String type_reservation, String username, String email,int phone) {
        this.id_reservation = id_reservation;
        this.phone = phone;
        this.type_reservation = type_reservation;
        this.username = username;
        this.email = email;
    }

    public Reservation( String type_reservation, String username, String email,int phone) {
        this.phone = phone;
        this.type_reservation = type_reservation;
        this.username = username;
        this.email = email;
    }

    public Reservation() {


    }

    public int getId_reservation() {
        return id_reservation;
    }

    public int getPhone() {
        return phone;
    }

    public String getType_reservation() {
        return type_reservation;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setType_reservation(String type_reservation) {
        this.type_reservation = type_reservation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_reservation=" + id_reservation +
                ", phone=" + phone +
                ", type_reservation='" + type_reservation + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


