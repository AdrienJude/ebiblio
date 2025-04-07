/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package gesbibliothèque;

import classe.ReportView;
import classe.connexionbd;
import gesbibliothèque.classe.Connexion;
import gesbibliothèque.classe.Emprunt;
import static gesbibliothèque.formadherent.con;
import static gesbibliothèque.formadherent.st;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
  import javax.swing.event.ListSelectionListener;
  import javax.swing.event.ListSelectionEvent;
  import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Adrien
 */
public class formEmprunt extends javax.swing.JInternalFrame {

    /**
     * Creates new form formLivre
     */
    Connection con;
    private TableRowSorter<DefaultTableModel> sorter;
    private TableRowSorter<DefaultTableModel> sorter1;
    private TableRowSorter<DefaultTableModel> sorter2;
    private TableRowSorter<DefaultTableModel> sorter3;
    
    DefaultTableModel model; 
    DefaultTableModel model1; 
    static int index;
    static int index2;
    static  int modelRow;
    static  int modelRow1;
    
    DefaultTableModel model2; 
    DefaultTableModel model3; 
    static int index3;
    static int index4;
    static  int modelRow2;
    static  int modelRow3;
    
    
    public formEmprunt() {
        initComponents();
      con=Connexion.getConnection();
      afficher_livre();
      afficher_adherent();
      afficher_livre1();
      afficher_adherent1();
      afficher_emprunts();
       
      
      
      /////////////////////////////////////////////////////////////////////////////////////
       model = (DefaultTableModel) tablo_adherent.getModel();
       sorter = new TableRowSorter<>(model);
      tablo_adherent.setRowSorter(sorter);
         model1 = (DefaultTableModel) tablo_livre.getModel();
       sorter1 = new TableRowSorter<>(model1);
      tablo_livre.setRowSorter(sorter1);
      
       model2 = (DefaultTableModel) tablo_adherent1.getModel();
       sorter2 = new TableRowSorter<>(model2);
      tablo_adherent1.setRowSorter(sorter2);
         model3 = (DefaultTableModel) tablo_livre1.getModel();
       sorter3 = new TableRowSorter<>(model3);
      tablo_livre1.setRowSorter(sorter3);
      
      
      tablo_livre.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            afficherDetailsSelectiona();
        }
    }
});
      
  
      tablo_adherent.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            afficherDetailsSelectionb();
        }
    }
});  
      
       tablo_livre1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            afficherDetailsSelectionc();
        }
    }
});
      
  
      tablo_adherent1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            afficherDetailsSelectiond();
        }
    }
});       
      /////////////////////////////////////////////////////////////////////////////////////
     
   
        
    }
    

   private void imprimer_recu(){
               // TODO add your handling code here:
       try { 
           
     Connection con = Connexion.getConnection();
    // Chemin vers le fichier .jrxml
    String reportPath = "src\\gesbibliothèque\\rapportemprunt.jasper";

    // Compilation du rapport
   JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

 

    // Remplissage du rapport avec les données
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(),con);

    // Affichage du rapport
    JasperViewer.viewReport(jasperPrint, false);

    
 
    // Fermeture de la connexion
    
} catch (JRException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Erreur lors de la génération du rapport : " + e.getMessage());
}


   } 
    
    
 
     
private void afficherDetailsSelectiona() {
    index = tablo_livre.getSelectedRow();
    if (index != -1) { // Vérifie si une ligne est sélectionnée
         modelRow = tablo_livre.convertRowIndexToModel(index);
      //   model1 = (DefaultTableModel) tablo_livre.getModel();
        
        // Supposons que vous ayez des colonnes "Nom", "Prénom", "Email" dans votre JTable
        String titre = model1.getValueAt(modelRow,0).toString();
        String auteur = model1.getValueAt(modelRow,1).toString();
        
        
        // Mise à jour des labels avec les informations récupérées
        txttitre.setText(titre);
        txtauteur.setText(auteur);
        
    }
}
 
private void afficherDetailsSelectionb() {
    index2 = tablo_adherent.getSelectedRow();
    if (index2 != -1) { // Vérifie si une ligne est sélectionnée
         modelRow1 = tablo_adherent.convertRowIndexToModel(index2);
   //     model = (DefaultTableModel) tablo_adherent.getModel();
        
        // Supposons que vous ayez des colonnes "Nom", "Prénom", "Email" dans votre JTable
        String nom = model.getValueAt(modelRow1,1).toString();
        String prenom = model.getValueAt(modelRow1,2).toString();
        
        
        // Mise à jour des labels avec les informations récupérées
        txtadherent.setText(nom+" "+prenom);
        
        
    }
}

private void afficherDetailsSelectionc() {
    index3 = tablo_livre1.getSelectedRow();
    if (index3 != -1) { // Vérifie si une ligne est sélectionnée
         modelRow3 = tablo_livre1.convertRowIndexToModel(index3);
      //   model1 = (DefaultTableModel) tablo_livre.getModel();
        
        // Supposons que vous ayez des colonnes "Nom", "Prénom", "Email" dans votre JTable
        String titre = model3.getValueAt(modelRow3,0).toString();
        String auteur = model3.getValueAt(modelRow3,1).toString();
        
        
        // Mise à jour des labels avec les informations récupérées
        txttitre1.setText(titre);
        txtauteur1.setText(auteur);
        
    }
}
 
private void afficherDetailsSelectiond() {
    index4 = tablo_adherent1.getSelectedRow();
    if (index4 != -1) { // Vérifie si une ligne est sélectionnée
         modelRow2 = tablo_adherent1.convertRowIndexToModel(index4);
   //     model = (DefaultTableModel) tablo_adherent.getModel();
        
        // Supposons que vous ayez des colonnes "Nom", "Prénom", "Email" dans votre JTable
        String nom = model2.getValueAt(modelRow2,1).toString();
        String prenom = model2.getValueAt(modelRow2,2).toString();
        
        
        // Mise à jour des labels avec les informations récupérées
        txtadherent1.setText(nom+" "+prenom);
        
        
    }
}

    
    
    
public void updateStatut(int id, String cnib) {
    Connection con = null;
    PreparedStatement selectStmt = null;
    PreparedStatement updateStmt = null;
    ResultSet rs = null;

    try {
        // Établir la connexion
        con = Connexion.getConnection();
        con.setAutoCommit(false); // Début de la transaction

        // Étape 1: Sélectionner l'idEmprunt minimal correspondant aux critères
        String selectSql = "SELECT MIN(idEmprunt) AS minId FROM emprunt WHERE refLivre = ? AND refAdherent = ? AND statut=?";
        selectStmt = con.prepareStatement(selectSql);
        selectStmt.setInt(1, id);
        selectStmt.setString(2, cnib);
        selectStmt.setString(3,"en cours");
        rs = selectStmt.executeQuery();

        if (rs.next()) {
            int minId = rs.getInt("minId");

            // Étape 2: Mettre à jour le statut de l'emprunt correspondant
            String updateSql = "UPDATE emprunt SET statut = ? WHERE idEmprunt = ?";
            updateStmt = con.prepareStatement(updateSql);
            updateStmt.setString(1, "rendu");
            updateStmt.setInt(2, minId);
            int rowsAffected = updateStmt.executeUpdate();

            if (rowsAffected > 0) {
                con.commit(); // Valider la transaction
            } else {
                con.rollback(); // Annuler la transaction en cas d'échec
            }
        } else {
            System.out.println("Aucun emprunt trouvé pour les critères spécifiés.");
            con.rollback(); // Annuler la transaction si aucun emprunt n'est trouvé
        }
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            if (con != null) {
                con.rollback(); // Annuler la transaction en cas d'exception
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    }  
}

     
    
    
    
        public void afficher_livre(){
    
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_livre.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select livre.titre AS vl,livre.auteur AS vq,livre.editeur AS vpu,livre.dateEdition AS vdt , livre.type AS tpy ,livre.nbExemplaire AS nx from livre ORDER BY titre ASC";
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String titre=rs.getString("vl");
                 String auteur=rs.getString("vq");
                 String editeur=rs.getString("vpu");
                 String dateEdition=rs.getString("vdt");
                 String type=rs.getString("tpy");
                 String nbExemplaire=rs.getString("nx");
 
                 
          
                 
                      
                 
                 String tbData[]={titre,auteur,editeur,dateEdition,type,nbExemplaire};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
        
        
         public void afficher_livre1(){
    
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_livre1.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select livre.titre AS vl,livre.auteur AS vq,livre.editeur AS vpu,livre.dateEdition AS vdt , livre.type AS tpy ,livre.nbExemplaire AS nx from livre ORDER BY titre ASC";
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String titre=rs.getString("vl");
                 String auteur=rs.getString("vq");
                 String editeur=rs.getString("vpu");
                 String dateEdition=rs.getString("vdt");
                 String type=rs.getString("tpy");
                 String nbExemplaire=rs.getString("nx");
 
                 
          
                 
                      
                 
                 String tbData[]={titre,auteur,editeur,dateEdition,type,nbExemplaire};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }       
        
         public void afficher_adherent(){
    
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_adherent.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select adherent.idAdherent AS vl,adherent.nom AS vq,adherent.prenom AS vpu,adherent.adresse AS vdt , adherent.type AS tpy ,adherent.quota AS nx ,adherent.statut AS stu from adherent ORDER BY nom ASC";
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String idAdherent=rs.getString("vl");
                 String nom=rs.getString("vq");
                 String prenom=rs.getString("vpu");
                 String adresse=rs.getString("vdt");
                 String type=rs.getString("tpy");
                 String quota=rs.getString("nx");
                 String statut=rs.getString("stu");
                 
          
                 
                      
                 
                 String tbData[]={idAdherent,nom,prenom,adresse,type,quota,statut};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }  
          //affichage des adherent dans restituer livre     
         
             public void afficher_adherent1(){
    
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_adherent1.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select adherent.idAdherent AS vl,adherent.nom AS vq,adherent.prenom AS vpu,adherent.adresse AS vdt , adherent.type AS tpy ,adherent.quota AS nx ,adherent.statut AS stu from adherent ORDER BY nom ASC";
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String idAdherent=rs.getString("vl");
                 String nom=rs.getString("vq");
                 String prenom=rs.getString("vpu");
                 String adresse=rs.getString("vdt");
                 String type=rs.getString("tpy");
                 String quota=rs.getString("nx");
                 String statut=rs.getString("stu");
                 
          
                 
                      
                 
                 String tbData[]={idAdherent,nom,prenom,adresse,type,quota,statut};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }      
         
         
          public void afficher_emprunts(){
    
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_emprunt.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select livre.titre AS vl,adherent.nom AS vq,adherent.prenom AS vpu,emprunt.statut AS vdt , emprunt.datePret AS tpy ,emprunt.dateRestitution AS nx from emprunt INNER JOIN livre ON livre.idLivre = emprunt.refLivre INNER JOIN adherent ON adherent.idAdherent=emprunt.refAdherent ORDER BY emprunt.idEmprunt ASC";
             
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String titre=rs.getString("vl");
                 String nom=rs.getString("vq");
                 String prenom=rs.getString("vpu");
                 String nometprenom=nom+" "+prenom;
                 String statut=rs.getString("vdt");
                 String nbExemplaire="1";
                 String datePret=rs.getString("tpy");
                 String dateRestitution=rs.getString("nx");
                 
                 
          
                 
                      
                 
                 String tbData[]={titre,nometprenom,statut,nbExemplaire,datePret,dateRestitution};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    } 
    
          
         
         public int recupereridLivre(String titre,String auteur){
             
              
        Integer idLivre = null;
        String requete = "SELECT idLivre FROM livre WHERE titre = ? AND auteur = ?";

        try {
            
            Connection con  = Connexion.getConnection();           
            PreparedStatement statement = con.prepareStatement(requete);
            statement.setString(1, titre);
            statement.setString(2, auteur);

            try (ResultSet resultat = statement.executeQuery()) {
                if (resultat.next()) {
                    idLivre = resultat.getInt("idLivre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idLivre;
    
         }
         
         public void updatequota(String cnib,String operation){
             int pas=0;
             if(operation.equals("increment")){
                 pas=1;
             }else if(operation.equals("decrement")){
                 pas=-1;
             }else{
             JOptionPane.showMessageDialog(this,"error occured","erreur",JOptionPane.ERROR_MESSAGE);
             }
             
             
         String kota=model.getValueAt(modelRow1,5).toString().trim();
         int quota=Integer.parseInt(kota);
             
             Connection con=Connexion.getConnection();
             String query=" UPDATE adherent SET quota =? WHERE idAdherent=? ";
             PreparedStatement st=null;
            
             try{
             st=con.prepareStatement(query);
             st.setInt(1,quota+pas);
             st.setString(2,cnib);
             st.executeUpdate();
             }catch(SQLException e){
               JOptionPane.showMessageDialog(this, e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE); 
             }
         }
         
           public void updatequota1(String cnib,String operation){
             int pas=0;
             if(operation.equals("increment")){
                 pas=1;
             }else if(operation.equals("decrement")){
                 pas=-1;
             }else{
             JOptionPane.showMessageDialog(this,"error occured","erreur",JOptionPane.ERROR_MESSAGE);
             }
               
         String kota=model2.getValueAt(modelRow2,5).toString().trim();
         int quota=Integer.parseInt(kota);
             
             Connection con=Connexion.getConnection();
             String query=" UPDATE adherent SET quota =? WHERE idAdherent=? ";
             PreparedStatement st=null;
            
             try{
             st=con.prepareStatement(query);
             st.setInt(1,quota+pas);
             st.setString(2,cnib);
             st.executeUpdate();
             }catch(SQLException e){
               JOptionPane.showMessageDialog(this, e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE); 
             }
         }
                  public void updatenbexemplaire(int idlivre,String operation){
             int pas=0;
             if(operation.equals("increment")){
                 pas=1;
             }else if(operation.equals("decrement")){
                 pas=-1;
             }else{
             JOptionPane.showMessageDialog(this,"error occured","erreur",JOptionPane.ERROR_MESSAGE);
             }
              
         String nb=model1.getValueAt(modelRow,5).toString().trim();
         int nblivre=Integer.parseInt(nb);
             
             Connection con=Connexion.getConnection();
             String query=" UPDATE livre SET nbExemplaire =? WHERE idLivre=? ";
             PreparedStatement st=null;
            
             try{
             st=con.prepareStatement(query);
             st.setInt(1,nblivre+pas);
             st.setInt(2,idlivre);
             st.executeUpdate();
             }catch(SQLException e){
               JOptionPane.showMessageDialog(this, e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE); 
             }
         }
                           public void updatenbexemplaire1(int idlivre,String operation){
             int pas=0;
             if(operation.equals("increment")){
                 pas=1;
             }else if(operation.equals("decrement")){
                 pas=-1;
             }else{
             JOptionPane.showMessageDialog(this,"error occured","erreur",JOptionPane.ERROR_MESSAGE);
             }
         
         String nb=model3.getValueAt(modelRow3,5).toString().trim();
         int nblivre=Integer.parseInt(nb);
             
             Connection con=Connexion.getConnection();
             String query=" UPDATE livre SET nbExemplaire =? WHERE idLivre=? ";
             PreparedStatement st=null;
            
             try{
             st=con.prepareStatement(query);
             st.setInt(1,nblivre+pas);
             st.setInt(2,idlivre);
             st.executeUpdate();
             }catch(SQLException e){
               JOptionPane.showMessageDialog(this, e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE); 
             }
         }
         

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablo_emprunt = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txttitre = new javax.swing.JLabel();
        txtadherent = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablo_livre = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablo_adherent = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtauteur = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txttitre1 = new javax.swing.JLabel();
        txtadherent1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablo_livre1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablo_adherent1 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtauteur1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablo_emprunt.setBackground(new java.awt.Color(255, 255, 255));
        tablo_emprunt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "Adhérent", "statut", "Nombre exemplaire", "Date de Prêt", "Date limite de Restitution"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablo_emprunt);

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Nombre d'emprunt par livre");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Nombre d'emprunt par adhérent");
        jButton2.setBorderPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Tous les emprunts effectués");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(97, 97, 97)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Statuts des emprunts", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gesbibliothèque/image/search.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Adhérent : ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Titre du livre :");

        txttitre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttitre.setForeground(new java.awt.Color(0, 102, 255));
        txttitre.setText(" ");

        txtadherent.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtadherent.setForeground(new java.awt.Color(0, 102, 255));
        txtadherent.setText(" ");

        jButton3.setBackground(new java.awt.Color(204, 102, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Prêter le livre");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Titre");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setText(" ");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Numéro CNIB de l'adhérent");

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setText(" ");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gesbibliothèque/image/search.png"))); // NOI18N

        tablo_livre.setBackground(new java.awt.Color(255, 255, 255));
        tablo_livre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "Auteur ", "Editeur", "dateEdition", "Type", "nombre exemplaire"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablo_livre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablo_livreMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablo_livre);
        ////////////////////////////////////////////

        tablo_adherent.setBackground(new java.awt.Color(255, 255, 255));
        tablo_adherent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNIB", "Nom", "Prénom", "Adresse", "Type", "quota", "Statut"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablo_adherent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablo_adherentMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablo_adherent);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("auteur");

        txtauteur.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtauteur.setForeground(new java.awt.Color(0, 102, 255));
        txtauteur.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtadherent, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txttitre, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txttitre)
                    .addComponent(jLabel15)
                    .addComponent(txtauteur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtadherent))
                .addGap(16, 16, 16)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            private void appliquerFiltre() {
                String text = jTextField1.getText().trim();
                if (text.trim().length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    sorter1.setRowFilter(RowFilter.regexFilter("^" + text, 0));
                }
            }
        });
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            private void appliquerFiltre() {
                String text = jTextField3.getText().trim().toUpperCase();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("^" + text, 0));
                }
            }
        });

        jTabbedPane1.addTab("Prêter livre", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gesbibliothèque/image/search.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Adhérent : ");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Titre du livre :");

        txttitre1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txttitre1.setForeground(new java.awt.Color(0, 102, 255));
        txttitre1.setText(" ");

        txtadherent1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtadherent1.setForeground(new java.awt.Color(0, 102, 255));
        txtadherent1.setText(" ");

        jButton4.setBackground(new java.awt.Color(0, 204, 204));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Restituer livre");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Titre");

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setText(" ");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Numéro CNIB de l'adhérent");

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setText(" ");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gesbibliothèque/image/search.png"))); // NOI18N

        tablo_livre1.setBackground(new java.awt.Color(255, 255, 255));
        tablo_livre1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "Auteur ", "Editeur", "dateEdition", "Type", "nombre exemplaire"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablo_livre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablo_livre1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablo_livre1);

        tablo_adherent1.setBackground(new java.awt.Color(255, 255, 255));
        tablo_adherent1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNIB", "Nom", "Prénom", "Adresse", "Type", "quota", "Statut"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablo_adherent1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablo_adherent1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablo_adherent1);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("auteur");

        txtauteur1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtauteur1.setForeground(new java.awt.Color(0, 102, 255));
        txtauteur1.setText(" ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtadherent1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txttitre1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtauteur1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 286, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txttitre1)
                    .addComponent(jLabel25)
                    .addComponent(txtauteur1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtadherent1))
                .addGap(16, 16, 16)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            private void appliquerFiltre() {
                String text = jTextField2.getText().trim();
                if (text.trim().length() == 0) {
                    sorter3.setRowFilter(null);
                } else {
                    sorter3.setRowFilter(RowFilter.regexFilter("^" + text, 0));
                }
            }
        });
        jTextField4.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                appliquerFiltre();
            }

            private void appliquerFiltre() {
                String text = jTextField4.getText().trim().toUpperCase();
                if (text.trim().length() == 0) {
                    sorter2.setRowFilter(null);
                } else {
                    sorter2.setRowFilter(RowFilter.regexFilter("^" + text, 0));
                }
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Restituer livre", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablo_livreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablo_livreMouseClicked
        // TODO add your handling code here:
    /*    int  index=tablo_livre.getSelectedRow();
      TableModel   tm=tablo_livre.getModel();
        txttitre.setText(tm.getValueAt(index,0).toString());
        txtauteur.setText(tm.getValueAt(index,1).toString());
       */ 
    }//GEN-LAST:event_tablo_livreMouseClicked

    private void tablo_adherentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablo_adherentMouseClicked
        // TODO add your handling code here:
      /*   int index=tablo_adherent.getSelectedRow();
        TableModel tm=tablo_adherent.getModel();
        txtadherent.setText(tm.getValueAt(index,1).toString()+" "+tm.getValueAt(index,2).toString());*/
    }//GEN-LAST:event_tablo_adherentMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
 /* int selectedRow1=tablo_livre.getSelectedRow();
  int selectedRow2=tablo_adherent.getSelectedRow();
  
  if(selectedRow1 != -1){
      
      if(selectedRow2 != -1){
             // TODO add your handling code here:
     int id= recupereridLivre(txttitre.getText(),txtauteur.getText());
     
        
         String nb=model1.getValueAt(modelRow,5).toString().trim();
         int nblivre=Integer.parseInt(nb);             
      
    
        String kota=model.getValueAt(modelRow1,5).toString().trim();
         int quota=Integer.parseInt(kota);
       String type=model.getValueAt(modelRow1,4).toString().trim();
       String cnib= model.getValueAt(modelRow1,0).toString();
       String statutAd=model.getValueAt(modelRow1,6).toString();
       
      int delaie=0;
      if(type.equals("Etudiant"))
      {
       delaie=1;
      }else if(type.equals("Professeur")){
       delaie=3;
      }else if (type.equals("Visiteur")){
      
      delaie=1;
      }
      else{
      JOptionPane.showMessageDialog(null, "Erreur de type", "erreur", JOptionPane.ERROR_MESSAGE);
      }
      if(nblivre<1){
          JOptionPane.showMessageDialog(null, "Le livre n'est pas disponible pour le moment", "erreur", JOptionPane.ERROR_MESSAGE);
      }else if(quota<1){
          JOptionPane.showMessageDialog(null, "L'adherent(e) à atteind le nombre maximum de livre empruntable", "erreur", JOptionPane.ERROR_MESSAGE);
      }
       
      else if(statutAd.equals("Retardataire")){
          JOptionPane.showMessageDialog(null, "L'adherent étant retardataire ne peut pas effectuer d'emprunt à nouveau", "erreur", JOptionPane.ERROR_MESSAGE);
      }else{
          Emprunt emprunt=new Emprunt(cnib,id,delaie);
      
         Connection con = Connexion.getConnection();
        String query = "INSERT INTO emprunt (refAdherent,refLivre,statut,datePret,dateRestitution) VALUES (?, ? ,? ,? ,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, emprunt.getIdAherent());
            stmt.setInt(2, emprunt.getIdLivre());
            stmt.setString(3, emprunt.getStatut());
            stmt.setObject(4, emprunt.getDateEmprunt());
            stmt.setObject(5, emprunt.getDateRestitution());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Emprunt effectué avec succès !");
            updatequota(cnib,"decrement");
            updatenbexemplaire(id,"decrement");
            afficher_livre();
            afficher_adherent();
            afficher_emprunts();
            imprimer_recu();
        } catch (SQLException e) {
            System.out.println("Erreur  : " + e.getMessage());
        }
      txttitre.setText("");
      txtadherent.setText("");
      txtauteur.setText("");
      }
      }else{
     JOptionPane.showMessageDialog(this,"veillez selectionner l'adhérent(e)","attention",JOptionPane.ERROR_MESSAGE);     
      }
  }else{
      JOptionPane.showMessageDialog(this,"veillez selectionner le livre","attention",JOptionPane.ERROR_MESSAGE);
  }*/
    int selectedRow1=tablo_livre.getSelectedRow();
int selectedRow2=tablo_adherent.getSelectedRow();

if(selectedRow1 != -1){
    
    if(selectedRow2 != -1){
        // TODO add your handling code here:
        int id= recupereridLivre(txttitre.getText(),txtauteur.getText());
        
        String nb=model1.getValueAt(modelRow,5).toString().trim();
        int nblivre=Integer.parseInt(nb);             
        
        String kota=model.getValueAt(modelRow1,5).toString().trim();
        int quota=Integer.parseInt(kota);
        String type=model.getValueAt(modelRow1,4).toString().trim();
        String cnib= model.getValueAt(modelRow1,0).toString();
        String statutAd=model.getValueAt(modelRow1,6).toString();
        
        int delaie=0;
        if(type.equals("Etudiant"))
        {
            delaie=1;
        }else if(type.equals("Professeur")){
            delaie=3;
        }else if (type.equals("Visiteur")){
            delaie=1;
        }
        else{
            JOptionPane.showMessageDialog(null, "Erreur de type", "erreur", JOptionPane.ERROR_MESSAGE);
        }
        if(nblivre<1){
            JOptionPane.showMessageDialog(null, "Le livre n'est pas disponible pour le moment", "erreur", JOptionPane.ERROR_MESSAGE);
        }else if(quota<1){
            JOptionPane.showMessageDialog(null, "L'adherent(e) à atteind le nombre maximum de livre empruntable", "erreur", JOptionPane.ERROR_MESSAGE);
        }
        
        else if(statutAd.equals("Retardataire")){
            JOptionPane.showMessageDialog(null, "L'adherent étant retardataire ne peut pas effectuer d'emprunt à nouveau", "erreur", JOptionPane.ERROR_MESSAGE);
        }else{
            Emprunt emprunt=new Emprunt(cnib,id,delaie);
            
            Connection con = Connexion.getConnection();
            String query = "INSERT INTO emprunt (refAdherent,refLivre,statut,datePret,dateRestitution) VALUES (?, ? ,? ,? ,?)";
            try {
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, emprunt.getIdAherent());
                stmt.setInt(2, emprunt.getIdLivre());
                stmt.setString(3, emprunt.getStatut());
                
                // Utiliser setTimestamp pour les objets LocalDateTime
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(emprunt.getDateEmprunt()));
                stmt.setTimestamp(5, java.sql.Timestamp.valueOf(emprunt.getDateRestitution()));
                
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Emprunt effectué avec succès !");
                updatequota(cnib,"decrement");
                updatenbexemplaire(id,"decrement");
                afficher_livre();
                afficher_adherent();
                afficher_emprunts();
                imprimer_recu();
            } catch (SQLException e) {
                System.out.println("Erreur  : " + e.getMessage());
            }
            txttitre.setText("");
            txtadherent.setText("");
            txtauteur.setText("");
        }
    }else{
        JOptionPane.showMessageDialog(this,"veillez selectionner l'adhérent(e)","attention",JOptionPane.ERROR_MESSAGE);     
    }
}else{
    JOptionPane.showMessageDialog(this,"veillez selectionner le livre","attention",JOptionPane.ERROR_MESSAGE);
}      
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_emprunt.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select livre.titre AS vl,COUNT(*) AS vq from emprunt INNER JOIN livre ON livre.idLivre = emprunt.refLivre GROUP BY livre.titre ORDER BY emprunt.idEmprunt ASC";
             
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String titre=rs.getString("vl");
                 String nometprenom="--------";
                 String statut="--------";
                 String nbExemplaire=rs.getString("vq");
                 String datePret="--------";
                 String dateRestitution="--------";
                 
                 
          
                 
                      
                 
                 String tbData[]={titre,nometprenom,statut,nbExemplaire,datePret,dateRestitution};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
          try{
          
          DefaultTableModel tblModel=(DefaultTableModel)tablo_emprunt.getModel();  
          tblModel.setRowCount(0);
          Connection con=Connexion.getConnection();
          
          Statement st = con.createStatement();

             String sql="Select adherent.nom AS vb,adherent.prenom AS vpr,COUNT(*) AS vq from emprunt INNER JOIN  adherent ON adherent.idAdherent=emprunt.refAdherent GROUP BY adherent.idAdherent ORDER BY vq DESC";
             
             ResultSet rs=st.executeQuery(sql);
             
             while(rs.next()){
             
                 String titre="--------";
                 String nom=rs.getString("vb");
                 String prenom=rs.getString("vpr");
                 String nometprenom=nom+" "+prenom;
                 String statut="--------";
                 String nbExemplaire=rs.getString("vq");
                 String datePret="--------";
                 String dateRestitution="--------";
                 
                 
          
                 
                      
                 
                 String tbData[]={titre,nometprenom,statut,nbExemplaire,datePret,dateRestitution};
                 tblModel.addRow(tbData);
             }
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(this,"problème d'affichage"+e.getMessage(),"erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        afficher_emprunts();
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
         int selectedRow1=tablo_livre1.getSelectedRow();
         int selectedRow2=tablo_adherent1.getSelectedRow();
         
         if(selectedRow1!= -1){
             
             if(selectedRow2!= -1){
                         // TODO add your handling code here:
         int id= recupereridLivre(txttitre1.getText(),txtauteur1.getText());
      
   
      
       String cnib= model2.getValueAt(modelRow2,0).toString();
       int nbligne=0;
               
try {
    // Établir la connexion
    Connection con = Connexion.getConnection();
    
    // Préparer la requête SQL avec des paramètres
    String sql = "SELECT COUNT(*) AS vq FROM emprunt WHERE refLivre = ? AND refAdherent = ? AND statut=?";
    PreparedStatement pst = con.prepareStatement(sql);
    
    // Remplacer les paramètres par les valeurs correspondantes
    pst.setInt(1, id);
    pst.setString(2, cnib);
    pst.setString(3,"en cours");
    
    // Exécuter la requête
    ResultSet result = pst.executeQuery();
    
    // Traiter le résultat
    if (result.next()) {
         nbligne = result.getInt("vq");
        // Utiliser la variable nombreLignes selon vos besoins
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
}  // Fermer les ressources
 /*  String statutE="";
 try {
    // Établir la connexion
    Connection con = Connexion.getConnection();
    
    // Préparer la requête SQL avec des paramètres
    String sql = "SELECT statut AS vq FROM emprunt WHERE refLivre = ? AND refAdherent = ?";
    PreparedStatement pst = con.prepareStatement(sql);
    
    // Remplacer les paramètres par les valeurs correspondantes
    pst.setInt(1, id);
    pst.setString(2, cnib);
    
    // Exécuter la requête
    ResultSet result = pst.executeQuery();
    
    // Traiter le résultat
    if (result.next()) {
         statutE = result.getString("vq");
        
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
}   */     
    
     
     if(nbligne<1){
         JOptionPane.showMessageDialog(this,"l'adhérent "+txtadherent1.getText()+" n'a pas d'umprunt en cours concernant le livre "+txttitre1.getText(),"erreur",JOptionPane.ERROR_MESSAGE);
     }
     else{
         
             
             updateStatut(id,cnib);
             afficher_emprunts();
             JOptionPane.showMessageDialog(this,"Le livre a été restitué avec succès");
            updatequota1(cnib,"increment");
            updatenbexemplaire1(id,"increment");
            afficher_adherent1();
            afficher_livre1();
             txttitre1.setText("");
             txtadherent1.setText("");
             txtauteur1.setText("");
          
     } 
             }else{
                 
                 JOptionPane.showMessageDialog(this,"veillez selectionner l'adhérent(e)","attention",JOptionPane.ERROR_MESSAGE);
             }
             
         }else{
             JOptionPane.showMessageDialog(this,"veillez selectionner le livre","attention",JOptionPane.ERROR_MESSAGE);
         }
       
    }//GEN-LAST:event_jButton4MouseClicked

    private void tablo_livre1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablo_livre1MouseClicked
        // TODO add your handling code here:
      /*    int index=tablo_livre1.getSelectedRow();
        TableModel tm=tablo_livre1.getModel();
        txttitre1.setText(tm.getValueAt(index,0).toString());
        txtauteur1.setText(tm.getValueAt(index,1).toString());*/
    }//GEN-LAST:event_tablo_livre1MouseClicked

    private void tablo_adherent1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablo_adherent1MouseClicked
        // TODO add your handling code here:
       /*     // TODO add your handling code here:
         int index=tablo_adherent1.getSelectedRow();
        TableModel tm=tablo_adherent1.getModel();
        txtadherent1.setText(tm.getValueAt(index,1).toString()+" "+tm.getValueAt(index,2).toString());
        */
    }//GEN-LAST:event_tablo_adherent1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tablo_adherent;
    private javax.swing.JTable tablo_adherent1;
    private javax.swing.JTable tablo_emprunt;
    private javax.swing.JTable tablo_livre;
    private javax.swing.JTable tablo_livre1;
    private javax.swing.JLabel txtadherent;
    private javax.swing.JLabel txtadherent1;
    private javax.swing.JLabel txtauteur;
    private javax.swing.JLabel txtauteur1;
    private javax.swing.JLabel txttitre;
    private javax.swing.JLabel txttitre1;
    // End of variables declaration//GEN-END:variables
}
