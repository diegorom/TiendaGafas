/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class GestionTipo {

    Conexion conexion = new Conexion();
    Tipo tipo = new Tipo();

    public ArrayList<Tipo> selecTipo() {
        ArrayList<Tipo> tipos = new ArrayList();
        try {
            Statement stmt = Conexion.conexion.createStatement();
            String sql = "SELECT id_tipo, solarGraduada FROM tipo";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int idTipo = rs.getInt("id_tipo");
                String solarGraduada = rs.getString("solarGraduada");
                Tipo newTipo = new Tipo(idTipo, solarGraduada);
                tipos.add(newTipo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar la base de datos");
            ex.printStackTrace();
        }
        return tipos;
    }

    public String toString() {
        return tipo.solarGraduada;

    }
}
