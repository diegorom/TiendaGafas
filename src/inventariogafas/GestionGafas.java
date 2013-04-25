/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class GestionGafas {
    Gafas gafa;
    Conexion conecion;
    
    boolean delete(Gafas gafa){
        this.gafa=gafa;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "DELETE FROM gafas WHERE id_gafas="+gafa.getIdGafas();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionGafas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
            return false;
        }
        return true;
    }   
    
    public boolean update(Gafas gafa) {
        try {
            
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "Update gafas set " + gafa.getIdGafas()+gafa.getModelo()+gafa.getMarca()+gafa.getPrecio()+gafa.getColor()+gafa.getGenero()+gafa.getMaterial()+gafa.getForma()+gafa.getTipo();
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(GestionGafas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean insert(Gafas gafa) {
        try {
            
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "INSERT INTO gafas (idGafas,modelo,marca,precio,color,genero,material,forma) VALUES " + "("+gafa.getIdGafas()+",'"+gafa.getModelo()+"','"+gafa.getMarca()+"',"+gafa.getPrecio()+",'"+gafa.getColor()+"','"+gafa.getGenero()+"','"+gafa.getMaterial()+"','"+gafa.getForma()+"','"+gafa.getTipo()+"')";
            stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(GestionGafas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
