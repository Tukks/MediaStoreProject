/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package images.modeles;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author giuse_000
 */
@Entity
public class Image implements Serializable {

    public Image() {
    }

    public Image(String name, String hashPic, Date date, String pathPic, String md5) {
        this.hashPic = hashPic;
        this.date = date;
        this.pathPic = pathPic;
        this.name = name;
        this.md5 = md5;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String hashPic;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String pathPic;

    private String name;
    private String md5;

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

    public String getHashPic() {
        return hashPic;
    }

    public void setHashPic(String hashPic) {
        this.hashPic = hashPic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPathPic() {
        return pathPic;
    }

    public void setPathPic(String pathPic) {
        this.pathPic = pathPic;
    }

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
