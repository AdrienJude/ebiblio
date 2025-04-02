/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package gesbibliothèque;


import classe.ReportView;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import classe.connexionbd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Adrien
 */
public class formadherent extends javax.swing.JInternalFrame {
    public static Connection con;
     public static Statement st;
     public static PreparedStatement ps;
     public static ResultSet rs;
     private  final DefaultTableModel tm;
    
     

    /**
     * Creates new form formLivre
     */
    public formadherent() {
    
        
      initComponents();
      tm= (DefaultTableModel)tabAdherent.getModel();
      // Récupérer la colonne "Type" (indice 5)
   
TableColumn typeColumn = tabAdherent.getColumnModel().getColumn(5);

// Créer une JComboBox avec les valeurs autorisées
JComboBox<String> comboBox = new JComboBox<>(new String[] { "Etudiant", "Enseignant", "Visiteur" });

// Définir le comboBox comme éditeur de cellule pour la colonne "Type"
typeColumn.setCellEditor(new DefaultCellEditor(comboBox));

    btnImprimer.setEnabled(false);
    btnSupprimer.setEnabled(false);
    btnModifier.setEnabled(false);
    setTxtIdValue();
    ajouterListenersCases();
        actualiser();
   
    }/**/
    public void verifierCasesCochees() {
    int count = 0;

    for (int i = 0; i < tm.getRowCount(); i++) {
        Boolean estCoche = (Boolean) tm.getValueAt(i, 8); // Colonne 9 (index 8)
        if (estCoche != null && estCoche) {
            count++;
        }
    }

    // Activer le bouton si une seule case est cochée
    btnModifier.setEnabled(count == 1);
}

private void ajouterListenersCases() {
    tm.addTableModelListener(e -> verifierCasesCochees());
}

    public void setTxtIdValue()
    {
        try {
            con= connexionbd.seConnecter();
            
            txtId.setText(genererNouvelId(con));
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
  
     public String genererNouvelId(Connection connection) {
    String dernierId = null;
    String nouvelId = "ADH001"; // Valeur par défaut si aucun ID n'existe encore

    try {
        // Récupérer le dernier ID existant
        String sql = "SELECT idAdherent FROM adherent ORDER BY idAdherent DESC LIMIT 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            dernierId = rs.getString("idAdherent");
            // Extraire le numéro après "ADH"
            int numero = Integer.parseInt(dernierId.substring(3)); 
            // Générer le prochain ID en format ADHXXX
            nouvelId = String.format("ADH%03d", numero + 1);
        }

        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return nouvelId;
}
     public void actualiser(){
         try {
       
    
    tm.setRowCount(0); // Vide le tableau avant d'afficher les résultats
 
    int n = 0;
        try {
            con = connexionbd.seConnecter(); // Connexion à la BD
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
    String sql = "SELECT * FROM Adherent";
    
          try (PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
              
              while (rs.next()) {
                  n++; // Incrémente le compteur des résultats trouvés
                  tm.addRow(new Object[]{
                      n, // Affiche un index au lieu d'une valeur vide
                      rs.getString("idAdherent"),
                      rs.getString("nom"),
                      rs.getString("prenom"),
                      rs.getString("adresse"),
                      rs.getString("type"),
                      rs.getInt("quota"),
                      rs.getString("statut"), false // Valeur false ajoutée correctement
                  });
                  
              }
              
          }
    con.close();

    // Active les boutons si des résultats sont trouvés
    boolean hasResults = (tm.getRowCount() != 0);
    btnImprimer.setEnabled(hasResults);
    btnSupprimer.setEnabled(hasResults);
    btnModifier.setEnabled(hasResults);
    

} catch (SQLException ex) {
    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAdresse = new javax.swing.JTextField();
        txtPrenom = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAjouter = new javax.swing.JButton();
        cmbType = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtRecherche = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabAdherent = new javax.swing.JTable();
        btnModifier = new javax.swing.JButton();
        btnSupprimer = new javax.swing.JButton();
        btnImprimer = new javax.swing.JButton();
        btnRechercher = new javax.swing.JButton();

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Prenom");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nom");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Type d'adhérent");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("id");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Adresse");

        btnAjouter.setText("Ajouter");
        btnAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjouterMouseClicked(evt);
            }
        });

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Etudiant", "Enseignant", "Visiteur", " " }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtId.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtAdresse, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(txtPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(txtId)
                    .addComponent(cmbType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(219, 219, 219))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAdresse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ajout des adhérents", jPanel1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Rechercher");

        txtRecherche.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRecherche.setForeground(new java.awt.Color(102, 102, 102));
        txtRecherche.setText(" Veuillez saisir le nom de l'adherent");
        txtRecherche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRechercheFocusGained(evt);
            }
        });
        txtRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercheActionPerformed(evt);
            }
        });

        tabAdherent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Id", "Nom", "Prenom", "Adresse", "Type", "Quota", "Statut", "cocher"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabAdherent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabAdherentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabAdherent);
        if (tabAdherent.getColumnModel().getColumnCount() > 0) {
            tabAdherent.getColumnModel().getColumn(1).setPreferredWidth(220);
            tabAdherent.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabAdherent.getColumnModel().getColumn(3).setPreferredWidth(300);
            tabAdherent.getColumnModel().getColumn(4).setPreferredWidth(300);
            tabAdherent.getColumnModel().getColumn(5).setPreferredWidth(400);
            tabAdherent.getColumnModel().getColumn(6).setPreferredWidth(130);
            tabAdherent.getColumnModel().getColumn(7).setPreferredWidth(400);
            tabAdherent.getColumnModel().getColumn(8).setPreferredWidth(160);
        }

        btnModifier.setText("modifier");
        btnModifier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModifierMouseClicked(evt);
            }
        });

        btnSupprimer.setText("supprimer");
        btnSupprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSupprimerMouseClicked(evt);
            }
        });

        btnImprimer.setText("Imprimer");
        btnImprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimerMouseClicked(evt);
            }
        });

        btnRechercher.setBackground(new java.awt.Color(255, 255, 255));
        btnRechercher.setForeground(new java.awt.Color(102, 102, 102));
        btnRechercher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gesbibliothèque/image/search.png"))); // NOI18N
        btnRechercher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRechercherMouseClicked(evt);
            }
        });
        btnRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechercherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnImprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel8)
                .addGap(27, 27, 27)
                .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnRechercher)
                .addContainerGap(141, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRechercher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModifier, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Liste des adhérents", jPanel3);

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

    private void btnAjouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjouterMouseClicked
try (Connection con = connexionbd.seConnecter();
     PreparedStatement ps = con.prepareStatement(
             "INSERT INTO adherent(idAdherent, nom, prenom, adresse, type, quota, statut) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
    
    // Récupérer les valeurs des champs avec trim() pour enlever les espaces inutiles
    String nom = txtNom.getText().trim();
    String prenom = txtPrenom.getText().trim();
    String adresse = txtAdresse.getText().trim();
    String type = cmbType.getSelectedItem().toString().trim();

    // Vérifier si les champs obligatoires sont vides
    if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires !", "Champs vides", JOptionPane.WARNING_MESSAGE);
        return; // Arrêter l'exécution si un champ est vide
    }

    // Générer un nouvel ID
    String nouvelId = genererNouvelId(con);

    // Définir le quota en fonction du type d'adhérent
    int quota;
    switch (type) {
        case "Etudiant":
            quota = 2;
            break;
        case "Enseignant":
            quota = 4;
            break;
        case "Visiteur": // Correction de la casse
            quota = 1;
            break;
        default:
            JOptionPane.showMessageDialog(null, "Type d'adhérent invalide !", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
    }

    // Remplir la requête SQL
    ps.setString(1, nouvelId);
    ps.setString(2, nom);
    ps.setString(3, prenom);
    ps.setString(4, adresse);
    ps.setString(5, type);
    ps.setInt(6, quota);
    ps.setString(7, "Non retardataire");

    // Exécuter l'insertion
    ps.executeUpdate();

    // Afficher un message de confirmation
    JOptionPane.showMessageDialog(null, "Adhérent ajouté avec succès !");
    
        ps.close();
        con.close(); 

    // Réinitialiser les champs de saisie
    txtNom.setText("");
    txtPrenom.setText("");
    txtAdresse.setText("");
     setTxtIdValue();

} catch (SQLException ex) {
    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, "Erreur lors de l'ajout de l'adhérent", ex);
    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'adhérent : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, "Erreur inattendue", ex);
    JOptionPane.showMessageDialog(null, "Une erreur inattendue est survenue : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_btnAjouterMouseClicked

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void tabAdherentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabAdherentMouseClicked

    }//GEN-LAST:event_tabAdherentMouseClicked

    private void btnRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRechercherActionPerformed

    private void btnRechercherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRechercherMouseClicked
   // Ajouter ceci au début de votre méthode de recherche
if (tabAdherent.isEditing()) {
    tabAdherent.getCellEditor().stopCellEditing();
}
    try {
        
        
    
    tm.setRowCount(0); // Vide le tableau avant d'afficher les résultats
 
    int n = 0;
        try {
            con = connexionbd.seConnecter(); // Connexion à la BD
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
        }
    String sql = "SELECT * FROM Adherent WHERE LOWER(nom) = LOWER(?)";
    
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setString(1, txtRecherche.getText().trim()); // Ajout sécurisé du texte
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {
        n++; // Incrémente le compteur des résultats trouvés
        tm.addRow(new Object[]{
            n, // Affiche un index au lieu d'une valeur vide
            rs.getString("idAdherent"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("adresse"),
            rs.getString("type"),
            rs.getString("quota"),
            rs.getString("statut"), false // Valeur false ajoutée correctement
        });
        
    }

    rs.close();
    pst.close();
    con.close();

    // Active les boutons si des résultats sont trouvés
    boolean hasResults = (tm.getRowCount() != 0);
    btnImprimer.setEnabled(hasResults);
    btnSupprimer.setEnabled(hasResults);
    btnModifier.setEnabled(hasResults);

} catch (SQLException ex) {
    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
}
    }//GEN-LAST:event_btnRechercherMouseClicked

    private void txtRechercheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRechercheFocusGained
        // TODO add your handling code here:
        txtRecherche.setText("");
    }//GEN-LAST:event_txtRechercheFocusGained

    private void txtRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercheActionPerformed

    private void btnSupprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSupprimerMouseClicked
        // TODO add your handling code here:
        
        int n=0;
        for(int i=0;i<tm.getRowCount();i++){
           
            if(Boolean.valueOf(tm.getValueAt(i, 8).toString())==true)
            {
                try {
                    String idAdherent = tm.getValueAt(i, 1).toString();
                    n++;
                    
                    con=connexionbd.seConnecter();
                    st=con.createStatement();
                    st.executeUpdate("DELETE FROM adherent WHERE idAdherent='"+idAdherent+"'");
                    con.close();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(formadherent.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
             }
            if(n==0)
                JOptionPane.showMessageDialog(null,"Veuillez choisir au moins un adherent");
            else{
                   JOptionPane.showMessageDialog(null,n+" Adhérents(s) Supprimé(s)");
                   btnSupprimer.setEnabled(false);
                   btnModifier.setEnabled(false);
                   btnImprimer.setEnabled(false);
                   
                  
                   tm.setRowCount(0);
            }
            actualiser();
         
    }//GEN-LAST:event_btnSupprimerMouseClicked

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
           setTxtIdValue();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnModifierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModifierMouseClicked
    int selectedRow = -1;
    int count = 0;

    // Vérifier combien de cases sont cochées
    for (int i = 0; i < tm.getRowCount(); i++) {
        Boolean estCoche = (Boolean) tm.getValueAt(i, 8); // Colonne "cocher"
        if (estCoche != null && estCoche) {
            selectedRow = i;
            count++;
        }
    }

    // S'assurer qu'une seule ligne est cochée
    if (count != 1) {
        JOptionPane.showMessageDialog(null, "Veuillez sélectionner UNE seule ligne à modifier !");
        return;
    }

    try {
        // Récupérer les données du tableau
        String idAdherent = tm.getValueAt(selectedRow, 1).toString();
        String nom        = tm.getValueAt(selectedRow, 2).toString();
        String prenom     = tm.getValueAt(selectedRow, 3).toString();
        String adresse    = tm.getValueAt(selectedRow, 4).toString();
        String type       = tm.getValueAt(selectedRow, 5).toString();
        String statut = tm.getValueAt(selectedRow, 7).toString();
        

        // Convertir la valeur en minuscules pour ignorer la casse
        String typeLower  = type.toLowerCase();

        int quota;
        switch (typeLower) {
            case "etudiant":
                quota = 2;
                break;
            case "enseignant":
                quota = 4;
                break;
            case "visiteur":
                quota = 1;
                break;
            default:
                // Conserver l'ancien quota ou fixer une valeur par défaut
                quota = Integer.parseInt(tm.getValueAt(selectedRow, 6).toString());
        }

        // Mettre à jour la cellule Quota dans le tableau (colonne 6)
          tm.setValueAt(quota, selectedRow, 6);

        // Récupérer le statut (colonne 7)
        

        // Mise à jour en base
        con = connexionbd.seConnecter();
        String sql = "UPDATE adherent SET nom=?, prenom=?, adresse=?, type=?, quota=?, statut=? WHERE idAdherent=?";
        PreparedStatement ps = con.prepareStatement(sql);

       
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, adresse);
        ps.setString(4, type);
        ps.setInt(5, quota);
        ps.setString(6, statut);
         ps.setString(7, idAdherent);

        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Modification enregistrée avec succès !");
       
        ps.close();
        con.close();
        actualiser();
      
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erreur lors de la modification !");
       
    }
   
    }//GEN-LAST:event_btnModifierMouseClicked

    private void btnImprimerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimerMouseClicked
        // TODO add your handling code here:
      
  HashMap map = new HashMap();
        ReportView report = null;
        try {
            con = connexionbd.seConnecter();
             st = con.createStatement();
             st.executeUpdate("CREATE OR REPLACE VIEW vueListeAdherents AS " +
                "SELECT idAdherent, nom, prenom, adresse, type, quota, statut " +
                "FROM adherent ORDER BY nom");
             report= new ReportView("listeAdherent.jasper", map);
             report.setVisible(true);
             report.setLocation(200,0);
                st.close();
    con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(formEmprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnImprimerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouter;
    private javax.swing.JButton btnImprimer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRechercher;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabAdherent;
    private javax.swing.JTextField txtAdresse;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtRecherche;
    // End of variables declaration//GEN-END:variables
}
