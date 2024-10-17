
package model;


public class Comment {
    String comment_id , account_id , lesson_id , comment , comment_date , status ,report_id , cause , report_date;

    public Comment() {
    }

    public Comment(String comment_id, String account_id, String lesson_id, String comment, String comment_date, String status, String report_id, String cause, String report_date) {
        this.comment_id = comment_id;
        this.account_id = account_id;
        this.lesson_id = lesson_id;
        this.comment = comment;
        this.comment_date = comment_date;
        this.status = status;
        this.report_id = report_id;
        this.cause = cause;
        this.report_date = report_date;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(String lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }
    
    
}
