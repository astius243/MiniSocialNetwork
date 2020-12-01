/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hau Huong
 */
@Entity
@Table(name = "Emotion", catalog = "MiniSocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emotion.findAll", query = "SELECT e FROM Emotion e")
    , @NamedQuery(name = "Emotion.findByEmotionID", query = "SELECT e FROM Emotion e WHERE e.emotionID = :emotionID")
    , @NamedQuery(name = "Emotion.findByEmotion", query = "SELECT e FROM Emotion e WHERE e.emotion = :emotion")})
public class Emotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EmotionID", nullable = false)
    private Integer emotionID;
    @Column(name = "Emotion", length = 100)
    private String emotion;
    @JoinColumn(name = "Email", referencedColumnName = "Email")
    @ManyToOne
    private Account email;
    @JoinColumn(name = "ArticleID", referencedColumnName = "ArticleID")
    @ManyToOne
    private Article articleID;

    public Emotion() {
    }

    public Emotion(Account email, Article articleID) {
        this.email = email;
        this.articleID = articleID;
    }

    public Emotion(Integer emotionID) {
        this.emotionID = emotionID;
    }

    public Integer getEmotionID() {
        return emotionID;
    }

    public void setEmotionID(Integer emotionID) {
        this.emotionID = emotionID;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
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
        hash += (emotionID != null ? emotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emotion)) {
            return false;
        }
        Emotion other = (Emotion) object;
        if ((this.emotionID == null && other.emotionID != null) || (this.emotionID != null && !this.emotionID.equals(other.emotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "huonglh.entities.Emotion[ emotionID=" + emotionID + " ]";
    }
    
}
