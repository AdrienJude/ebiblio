/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classe;
import java.awt.Container;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 * @param fileName
 * @param map
 * @author Adrien
 */
public class ReportView extends javax.swing.JFrame{
    Connection con = null;
    public ReportView(String fileName,HashMap map)
    {
        super("Page à imprimer");
        try {
            con = connexionbd.seConnecter();
            JasperPrint print = JasperFillManager.fillReport(fileName, map,con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);
            setBounds(10, 10, 900, 550);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } catch ( Exception ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
