/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau Huong
 */
@Entity
@Table(name = "Notification", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByNotifyID", query = "SELECT n FROM Notification n WHERE n.notifyID = :notifyID")
    , @NamedQuery(name = "Notification.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date")
    , @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NotifyID", nullable = false)
    private Integer notifyID;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "Content", length = 100)
    private String content;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private Account email;
    @JoinColumn(name = "ArticleID", referencedColumnName = "ArticleID")
    @ManyToOne
    private Article articleID;

    public Notification() {
    }

    public Notification(Date date, String content, Article articleID) {
        this.date = date;
        this.content = content;
        this.articleID = articleID;
    }

    public Notification(Integer notifyID) {
        this.notifyID = notifyID;
    }

    public Integer getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(Integer notifyID) {
        this.notifyID = notifyID;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getEmail() {
        return email;
    }

    public void setEmail(Account email) {
        this.email = email;
    }

    public Article getArticleID() {
        return articleID;
    }

    public void setArticleID(Article articleID) {
        this.articleID = articleID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifyID != null ? notifyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notifyID == null && other.notifyID != null) || (this.notifyID != null && !this.notifyID.equals(other.notifyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "huonglh.entities.Notification[ notifyID=" + notifyID + " ]";
    }
    
}
