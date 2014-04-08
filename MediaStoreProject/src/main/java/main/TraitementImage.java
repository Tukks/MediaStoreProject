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
import java.text.ParseException;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

public class TraitementImage {

    public static void main(String[] args) throws ImageProcessingException, IOException, MetadataException, ParseException {
        String lat = null;
        String lon = null;
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
        System.out.println("Latitude :" + lat);
        System.out.println("Longitude :" + lon);
        System.out.println("Taille :" + size);
        System.out.println("Date : " + date);
        System.out.println("Chemin :" + path);
        String md5 = generateMD5(jpegFile);
        System.out.println("MD5 :" + md5);

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
}
