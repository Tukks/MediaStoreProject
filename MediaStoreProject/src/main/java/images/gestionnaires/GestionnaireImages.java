/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.gestionnaires;

import images.analyzer.ImageDescriptor;
import images.modeles.Image;
import java.io.File;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author giuse_000
 */
@Stateless
public class GestionnaireImages {

    @PersistenceContext
    private EntityManager em;

    public void creerImagesDetest() {
        Date d = new Date(10, 12, 2014);
        creerImage("nom", "hdhd", "hash", new Date(), "C:");
        creerImage("nom", "hdhd", "hash", new Date(), "C:");

    }

    /*
     To do : generate Hash, and md5
     */
    public void dropPicture(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            if (root.isFile()) {
                ImageDescriptor id = ImageDescriptor.readFromDisk(root.getAbsolutePath());
                Date date = new Date(root.lastModified());
                creerImage(root.getName(), "", "", date, id.getPath());
                System.out.println("File:" + root.getAbsoluteFile());
            }
        } else {

            for (File f : list) {
                if (f.isDirectory()) {
                    dropPicture(f.getAbsolutePath());
                    System.out.println("Dir:" + f.getAbsoluteFile());
                } else {
                    ImageDescriptor id = ImageDescriptor.readFromDisk(f.getAbsolutePath());
                    Date date = new Date(f.lastModified());
                    creerImage(f.getName(), "", "", date, id.getPath());
                    System.out.println("File:" + f.getAbsoluteFile());
                }
            }
        }
    }

    public Image creerImage(String nom, String md5, String hash, Date date, String path) {
        Image i = new Image(nom, hash, date, path, md5);
        em.persist(i);

        return i;

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
