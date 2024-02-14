package tn.esprit.demogui.models;

public class Fidelite extends User {
    private int id,montant;

    private String code_promo;
    public Fidelite(int id, int montant, String code_promo){
        this.id=id;
        this.montant=montant;
        this.code_promo = code_promo;
}
    public Fidelite(int montant, String code_promo) {
        this.montant = montant;
        this.code_promo = code_promo;

    }
    public Fidelite() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(String code_promo) {
        this.code_promo = code_promo;
    }



    @Override
    public String toString() {
        return "Fidelite{" +
                "id=" + id +
                ", Montant=" + montant +
                ", code_promo=" + code_promo +
                '}';
    }
}