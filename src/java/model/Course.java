
package model;


public class Course {
    String courseId , courseName , description , intructor , createDate , updateDate ,  category ;

    public Course() {
    }

    public Course(String courseId, String courseName, String description, String intructor, String createDate, String updateDate, String category) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.intructor = intructor;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.category = category;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntructor() {
        return intructor;
    }

    public void setIntructor(String intructor) {
        this.intructor = intructor;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
