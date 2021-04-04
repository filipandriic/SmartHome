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
@Table(name = "tasks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.findByIdTasks", query = "SELECT t FROM Tasks t WHERE t.idTasks = :idTasks"),
    @NamedQuery(name = "Tasks.findByName", query = "SELECT t FROM Tasks t WHERE t.name = :name"),
    @NamedQuery(name = "Tasks.findByStartDate", query = "SELECT t FROM Tasks t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "Tasks.findByStartTime", query = "SELECT t FROM Tasks t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Tasks.findByEndDate", query = "SELECT t FROM Tasks t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "Tasks.findByEndTime", query = "SELECT t FROM Tasks t WHERE t.endTime = :endTime"),
    @NamedQuery(name = "Tasks.findByLocation", query = "SELECT t FROM Tasks t WHERE t.location = :location"),
    @NamedQuery(name = "Tasks.findByIdUser", query = "SELECT t FROM Tasks t WHERE t.idUser = :idUser")})
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTasks")
    private Integer idTasks;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "StartDate")
    private String startDate;
    @Size(max = 45)
    @Column(name = "StartTime")
    private String startTime;
    @Size(max = 45)
    @Column(name = "EndDate")
    private String endDate;
    @Size(max = 45)
    @Column(name = "EndTime")
    private String endTime;
    @Size(max = 45)
    @Column(name = "Location")
    private String location;
    @Column(name = "idUser")
    private Integer idUser;

    public Tasks() {
    }

    public Tasks(Integer idTasks) {
        this.idTasks = idTasks;
    }

    public Integer getIdTasks() {
        return idTasks;
    }

    public void setIdTasks(Integer idTasks) {
        this.idTasks = idTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        hash += (idTasks != null ? idTasks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.idTasks == null && other.idTasks != null) || (this.idTasks != null && !this.idTasks.equals(other.idTasks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Tasks[ idTasks=" + idTasks + " ]";
    }
    
}
