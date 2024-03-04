package tn.esprit.guiapplicatio.models;
import java.sql.Date;
import java.time.LocalDate;


public class CodePromo {

    private int id;
    private int code;
    private Date date_exp;
    private int montant;
    private int user_id;


 /*   public void setdate_exp(Timestamp date_exp) {
        this.date_exp = date_exp;
    }
*/
  /*  public Timestamp getdate_exp() {
        return date_exp;
    }
*/

    public CodePromo() {

        this.code = code;
        this.date_exp = date_exp;
        this.montant = montant;
        this.user_id = user_id;

    }


    public CodePromo(String code, Date dateExp, String user_id, int montant) {
        this.id = id;
        this.code = this.code;
        this.date_exp = date_exp;
        this.montant = this.montant;
        this.user_id = this.user_id;


    }
    public CodePromo(int id, int code, LocalDate date, int montant, int userId) {
        // Initialiser les attributs de la classe avec les valeurs passées en paramètres
        this.id = id;
        this.code = code;
        this.date_exp = Date.valueOf(date);
        this.montant = montant;
        this.user_id = userId;}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

  public Date getDate_exp() {

      return date_exp;
  }

    public void setDate_exp(Date date_exp) {this.date_exp = date_exp;}

    public int getmontant() {
        return montant;
    }

    public void setmontant(int montant) {
        this.montant = montant;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CodePromo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date_exp=" + date_exp +
                ", montant=" + montant +
                ", user_id=" + user_id +
                '}';
    }
}
