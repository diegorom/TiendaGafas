/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

/**
 *
 * @author diego
 */
public class Tipo {

    int idTipo;
    String solarGraduada;

    Tipo() {
    }

    Tipo(int idTipo, String solarGraduada) {
        this.idTipo = idTipo;
        this.solarGraduada = solarGraduada;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getSolarGraduada() {
        return solarGraduada;
    }

    public void setSolarGraduada(String solarGraduada) {
        this.solarGraduada = solarGraduada;
    }
}
