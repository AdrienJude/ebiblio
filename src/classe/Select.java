/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Mr.Adam's
 */
public class Select {
      public static ResultSet getData(String query)
    {
        Connection con=null;
        ResultSet rs=null;
        Statement st=null;
        try
        {
            con= connexionbd.seConnecter();
            st=con.createStatement();
            rs=st.executeQuery(query);
            return rs;
        }
        catch (Exception e)
        {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }
    
}
