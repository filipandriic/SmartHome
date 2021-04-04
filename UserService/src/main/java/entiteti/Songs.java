/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "songs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Songs.findAll", query = "SELECT s FROM Songs s"),
    @NamedQuery(name = "Songs.findByIdSongs", query = "SELECT s FROM Songs s WHERE s.idSongs = :idSongs"),
    @NamedQuery(name = "Songs.findByName", query = "SELECT s FROM Songs s WHERE s.name = :name"),
    @NamedQuery(name = "Songs.findByIdUser", query = "SELECT s FROM Songs s WHERE s.idUser = :idUser")})
public class Songs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSongs")
    private Integer idSongs;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Column(name = "idUser")
    private Integer idUser;

    public Songs() {
    }

    public Songs(Integer idSongs) {
        this.idSongs = idSongs;
    }

    public Integer getIdSongs() {
        return idSongs;
    }

    public void setIdSongs(Integer idSongs) {
        this.idSongs = idSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSongs != null ? idSongs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Songs)) {
            return false;
        }
        Songs other = (Songs) object;
        if ((this.idSongs == null && other.idSongs != null) || (this.idSongs != null && !this.idSongs.equals(other.idSongs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Songs[ idSongs=" + idSongs + " ]";
    }
    
}
