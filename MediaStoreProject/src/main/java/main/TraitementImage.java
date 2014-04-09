/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import static com.drew.metadata.exif.ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL;
import static com.drew.metadata.exif.GpsDirectory.TAG_GPS_LATITUDE;
import static com.drew.metadata.exif.GpsDirectory.TAG_GPS_LONGITUDE;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Bdd.addImage;
import static main.Bdd.getAllImage;
import org.apache.commons.codec.digest.DigestUtils;

public class TraitementImage {

    public static void TraitementImage(String chemin) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException {
        String lat = null;
        String lon = null;
        String ARGB = null;
        File jpegFile = new File("f://test.jpg");
        Date date = null;
        long size = jpegFile.length();
        String path = jpegFile.toString();
        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (directory.getDescription(TAG_GPS_LATITUDE) != null) {
                    lat = directory.getDescription(TAG_GPS_LATITUDE).toString();
                }
                if (directory.getDescription(TAG_GPS_LONGITUDE) != null) {
                    lon = directory.getDescription(TAG_GPS_LONGITUDE).toString();
                }
                if (directory.getDescription(TAG_DATETIME_ORIGINAL) != null) {
                    date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                }

            }
        }
        String md5 = generateMD5(jpegFile);
        ARGB = main.hash.generateSignature(jpegFile.toString());

        lat = convertGPS(lat);
        lon = convertGPS(lon);
        int nb = (int) (Math.random() * 100);
        addImage(nb, path, size, date.toString(), md5, "hash", lat, lon, ARGB);

    }

    public static String convertGPS(String coor) {
        String convert = coor.replaceAll("[°,\",']", "");;
        return convert;

    }

    public static String generateMD5(File f) throws IOException {
        InputStream fis = new BufferedInputStream(new FileInputStream(f));
        String s = TraitementImage.generateMD5(fis);
        fis.close();
        return s;
    }

    public static String generateMD5(InputStream fi) throws IOException {

        byte[] buffer = DigestUtils.md5(fi);
        String s = DigestUtils.md5Hex(buffer);
        return s;
    }

    public static void main(String[] args) {
        try {
            TraitementImage("test");
            getAllImage();
        } catch (ImageProcessingException ex) {
            Logger.getLogger(TraitementImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TraitementImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TraitementImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TraitementImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cette méthode permet de parcourir tous les fichiers contenus dans un
     * repertoire entré et ses sous-repertoires. On veut s'arrêter au à un seuil
     * de profondeur entré k
     */
    public static void itererRepertoire(String repertoire, int j) {

        File dir = new File(repertoire);
        System.out.println(repertoire);
        System.out.println("j : " + j);
        //Once you have the appropriate path, you can iterate through its contents:  
        //List directory  
        // si le repertoire courant est bien un repertoire  
        if (dir.isDirectory()) {
            String s[] = dir.list();
            for (int i = 0; i < s.length; i++) {

                File dirTemp = new File(repertoire + s[i] + "\\");
                // si le terme de la liste est lui-même un répertoire                 
                if (dirTemp.isDirectory() && j > 0) {
                    itererRepertoire(repertoire + s[i] + "\\", j - 1);
                }
                // si le terme de la liste est un fichier  
                if (!dirTemp.isDirectory()) {
                    System.out.println("fichier : " + s[i]);
                }
            }
        }
    }
}
