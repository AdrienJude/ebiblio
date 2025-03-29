/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gesbibliothèque.classe;

import java.time.LocalDateTime;
import jxl.write.DateTime;

/**
 *
 * @author Stéphane Nana
 */
public class Emprunt {
    
    private String idAherent;
    private int idLivre;
    private String statut;
    private int delaie;
    private  LocalDateTime  dateEmprunt; 
    private   LocalDateTime dateRestitution;

    public Emprunt(String idAherent, int idLivre, int delaie) {
        this.idAherent = idAherent;
        this.idLivre = idLivre;
        this.statut = "en cours";
        this.delaie = delaie;
        this.dateEmprunt = LocalDateTime.now(); 
        this.dateRestitution = dateEmprunt.plusWeeks(delaie);
        
  
    }

    public String getIdAherent() {
        return idAherent;
    }

    public void setIdAherent(String idAherent) {
        this.idAherent = idAherent;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getDelaie() {
        return delaie;
    }

    public void setDelaie(int delaie) {
        this.delaie = delaie;
    }

    public LocalDateTime getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDateTime dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDateTime getDateRestitution() {
        return dateRestitution;
    }

    public void setDateRestitution(LocalDateTime dateRestitution) {
        this.dateRestitution = dateRestitution;
    }

     

     
    
}
