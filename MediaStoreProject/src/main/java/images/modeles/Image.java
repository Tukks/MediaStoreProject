/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.modeles;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author giuse_000
 */
@Entity
public class Image implements Serializable {

    public Image() {
    }

    public Image(String name, String hash, String md5) {
        this.name = name;
        this.hash = hash;
        this.md5 = md5;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    private String hash;

    /**
     * Get the value of hash
     *
     * @return the value of hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the value of hash
     *
     * @param hash new value of hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    private String md5;

    /**
     * Get the value of md5
     *
     * @return the value of md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Set the value of md5
     *
     * @param md5 new value of md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "images.modeles.image[ id=" + id + " ]";
    }

}
