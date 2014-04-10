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
import java.text.SimpleDateFormat;
import java.util.Date;
import static main.Bdd.addImage;
import static main.Bdd.getAllImage;
import org.apache.commons.codec.digest.DigestUtils;

public class TraitementImage {

    public static void TraitementImage(String chemin) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException, ParseException {
        String lat = null;
        String lon = null;
        String ARGB = null;
        System.out.println(chemin);
        File jpegFile = new File(chemin);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Date date = sdf.parse(dateInString);
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
        if (date.toString().equals("Tue Aug 31 10:20:56 CEST 1982")) {
            System.out.println("Pas de date");
            addImage(path, size, "null", md5, "hash", lat, lon, ARGB);
        } else {
            addImage(path, size, date.toString(), md5, "hash", lat, lon, ARGB);
            System.out.println("Date OK");
        }
    }

    public static String convertGPS(String coor) {
        if (coor == null) {
            return "null";
        }
        String convert = coor.replaceAll("[°,\",']", "");
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

    public static void main(String[] args) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException, ParseException {
        itererRepertoire("C:\\Users\\Justin\\Pictures\\projet\\testImages\\", 0);
        //getAllImage();
    }


    public static void itererRepertoire(String repertoire, int j) throws ImageProcessingException, IOException, ClassNotFoundException, SQLException, ParseException {

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
                if (dirTemp.isDirectory()) {
                    itererRepertoire(repertoire + s[i] + "\\", j - 1);

                }
                // si le terme de la liste est un fichier  
                if (!dirTemp.isDirectory()) {
                    String ext = s[i].substring(s[i].lastIndexOf(".")); //récupère l'extention 
                    if (ext.equals(".jpg")) {
                        TraitementImage(repertoire + "\\" + s[i]);
                    }

                }
            }
        }
    }
}
