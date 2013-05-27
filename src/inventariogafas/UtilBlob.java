package inventariogafas;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UtilBlob {

    /**
     * Convierte el contenido mostrado en un JPanel al tipo Blob utilizado
     * en las bases de datos, con el fin de tratarlo como una imagen.
     * Para almacenar el Blob en la base de datos se debe usar PreparedStatement
     * de manera similar a la siguiente:
     * PreparedStatement pstmt = Conexion.conexion.prepareStatement("UPDATE tabla SET campoBlob = ? WHERE condicion=valor");
     * pstmt.setBlob(1, variableQueContieneElBlob);
     * pstmt.execute();
     * @param jPanel Panel del que se obtendrá el contenido
     * @return Blob con el contenido del panel que se ha pasado por parámetro
     */
    public static Blob JPanelToBlob(JPanel jPanel) {
        try {
            Dimension size = jPanel.getSize();
            BufferedImage bufferedImage = new BufferedImage(
                        size.width, size.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            jPanel.paint(g2);            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            Blob blob = new SerialBlob(imageInByte);
            return blob;
        } catch (SerialException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Muestra en un JLabel la imagen contenida en un objeto Blog que se haya podido
     * obtener de una base de datos. La imagen se reducirá de tamaño si es más 
     * grande que el tamaño del JPanel, y en caso contrario se mantendrá con su
     * tamaño original.
     * @param blob Objeto Blob que contiene la imagen
     * @param jLabel JLabel en el que se mostrará la imagen, al que previamente se
     * le habrá dado el tamaño máximo que se usará para mostrar la imagen
     */
    
    public static void BlobToJLabel(Blob blob, JLabel jLabel) {
        try {
            ImageIcon imageIcon = new ImageIcon(blob.getBytes(1L, (int)blob.length()));
            double escalaX = (double)jLabel.getWidth() / imageIcon.getIconWidth();
            double escalaY = (double)jLabel.getHeight() / imageIcon.getIconHeight();
            double escalaMin = Math.min(escalaX, escalaY);
            Image image = imageIcon.getImage(); 
            if(escalaMin<1) {
                //Si la imagen es más grande que el JPanel, escalar la imagen proporcionalmente
                Image newimg = image.getScaledInstance((int)(imageIcon.getIconWidth() * escalaMin), (int) (imageIcon.getIconHeight() * escalaMin), java.awt.Image.SCALE_SMOOTH);  
                imageIcon = new ImageIcon(newimg);   
            } else {
                //Si la imagen es más pequeña que el JPanel, no agrandar
                Image newimg = image.getScaledInstance((int)(imageIcon.getIconWidth()), (int) (imageIcon.getIconHeight()), java.awt.Image.SCALE_SMOOTH);  
                imageIcon = new ImageIcon(newimg);   
            }            
            jLabel.setIcon(imageIcon);
        } catch (SQLException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
/**
     * Convierte un archivo de imagen al tipo Blob utilizado
     * en las bases de datos, con el fin de tratarlo como una imagen.
     * Para almacenar el Blob en la base de datos se debe usar PreparedStatement
     * de manera similar a la siguiente:
     * PreparedStatement pstmt = Conexion.conexion.prepareStatement("UPDATE tabla SET campoBlob = ? WHERE condicion=valor");
     * pstmt.setBlob(1, variableQueContieneElBlob);
     * pstmt.execute();
     * @param file File que contiene la imagen en cualquier de los formatos más comunes: gif, png, jpeg, etc.
     * @return Blob con el contenido del panel que se ha pasado por parámetro
     */
    public static Blob FileToBlob(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Graphics2D g2 = bufferedImage.createGraphics();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            Blob blob = new SerialBlob(imageInByte);
            return blob;
        } catch (SerialException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(UtilBlob.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
