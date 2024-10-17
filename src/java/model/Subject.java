
package model;


public class Subject {
    String SubjectID , SubjectName;

    public Subject() {
    }

    public Subject(String SubjectID, String SubjectName) {
        this.SubjectID = SubjectID;
        this.SubjectName = SubjectName;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }
    
    
}
