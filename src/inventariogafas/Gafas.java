/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

/**
 *
 * @author diego
 */
public class Gafas {

    String modelo;
    String marca;
    int precio;
    String color;
    String genero;
    String material;
    String forma;
    int idGafas;
    String tipo;

    Gafas() {
    }

    Gafas(int idGafas, String modelo, String marca, int precio, String color, String genero, String material, String forma, String tipo) {
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.color = color;
        this.genero = genero;
        this.material = material;
        this.forma = forma;
        this.idGafas = idGafas;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdGafas() {
        return idGafas;
    }

    public void setIdGafas(int idGafas) {
        this.idGafas = idGafas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
