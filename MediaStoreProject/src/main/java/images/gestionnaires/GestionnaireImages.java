/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.gestionnaires;

import images.modeles.Image;
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
        creerImage("nom", "hdhd", "C:/");
        creerImage("nom1", "hdsshd", "C:/");

    }

    public Image creerImage(String nom, String md5, String hash) {
        Image i = new Image(nom, md5, hash);
        em.persist(i);

        return i;

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
