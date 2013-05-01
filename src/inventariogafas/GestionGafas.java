/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
            String sql = "UPDATE gafas SET " + gafa.getIdGafas()+gafa.getModelo()+gafa.getMarca()+gafa.getPrecio()+gafa.getColor()+gafa.getGenero()+gafa.getMaterial()+gafa.getForma()+gafa.getTipo();
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
            String sql = "INSERT INTO gafas (id_gafas,modelo,marca,precio,color,genero,material,forma,tipo) VALUES " + "("+gafa.getIdGafas()+",'"+gafa.getModelo()+"','"+gafa.getMarca()+"',"+gafa.getPrecio()+",'"+gafa.getColor()+"','"+gafa.getGenero()+"','"+gafa.getMaterial()+"','"+gafa.getForma()+"','"+gafa.getTipo()+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionGafas.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<Gafas> selecGafas() {
        ArrayList<Gafas> gafas = new ArrayList();
        try {
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "SELECT * FROM gafas";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idGafas = rs.getInt("id_gafas");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                int precio = rs.getInt("precio");
                String color = rs.getString("color");
                String genero = rs.getString("genero");
                String material = rs.getString("material");
                String forma = rs.getString("forma");
                String tipo = rs.getString("tipo");
                Gafas NewGafa = new Gafas(idGafas,"modelo", "marca",precio,"color","genero","material","forma","tipo");
                gafas.add(NewGafa);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return gafas;
    }
    public ArrayList<String> selecMarca() {
        ArrayList<String> marca = new ArrayList();
        try {
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "SELECT marca FROM gafas";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String marcas = rs.getString("marca");
                marca.add(marcas);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return marca;
    }
}
