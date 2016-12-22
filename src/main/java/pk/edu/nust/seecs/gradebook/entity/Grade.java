package pk.edu.nust.seecs.gradebook.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * An Entity to contain the Grade.
 * <p>
 This entity class holds the general attributes of a grade item. 
 Contained in this class are attributes such as its name and its score. Also 
 the reverse relationship with content is defined here.
 */
@Entity
public class Grade implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name = "grade_id")
    private Integer gradeId;
    
    @Column
    private String name;
    
    @Column
    private Integer score;
    
    /*
    * This relationship contains the content this grade is mapped to.
    * The reverse relationship is present in the Grade entity class
    */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="content_id",nullable=false)
    private Content contentItem;

    /*
    * Class constructor.
    */
    public Grade() {
    }
    
    public int getGradeId() {
        return gradeId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }

    public Content getContentItem() {
        return contentItem;
    }

    public void setContentItem(Content contentItem) {
        this.contentItem = contentItem;
    }

    @Override
    public String toString() {
        return "Grade{" + "gradeId=" + gradeId + ", name=" + name + ", score=" + score + ", contentItem=" + contentItem + '}';
    }
    
}
