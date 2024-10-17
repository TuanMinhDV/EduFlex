package dal;

import java.sql.*;
import java.util.ArrayList;
import model.Comment;

public class CommentDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public ArrayList<Comment> getListComment(String search, String filter, int pageNumber, int pageSize) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            String sql = "SELECT CL.comment_id, CL.account_id, CL.lesson_id, CL.comment, CL.comment_date, CL.status, CR.report_id, CR.cause, CR.report_date "
                    + "FROM [EduFlex].[dbo].[Comment_Lesson] CL "
                    + "LEFT JOIN [EduFlex].[dbo].[Comment_Report] CR ON CL.comment_id = CR.comment_id "
                    + "WHERE CL.comment LIKE ? ";

            if ("reported".equals(filter)) {
                sql += "AND CR.report_id IS NOT NULL ";
            }

            sql += "ORDER BY CL.comment_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            stm.setInt(2, (pageNumber - 1) * pageSize);
            stm.setInt(3, pageSize);
            rs = stm.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getString("comment_id"),
                        rs.getString("account_id"),
                        rs.getString("lesson_id"),
                        rs.getString("comment"),
                        rs.getString("comment_date"),
                        rs.getString("status"),
                        rs.getString("report_id"),
                        rs.getString("cause"),
                        rs.getString("report_date")
                );
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    public int getTotalComments(String search, String filter) {
        int total = 0;
        try {
            String sql = "SELECT COUNT(*) AS total FROM [EduFlex].[dbo].[Comment_Lesson] CL "
                    + "LEFT JOIN [EduFlex].[dbo].[Comment_Report] CR ON CL.comment_id = CR.comment_id "
                    + "WHERE CL.comment LIKE ? ";

            if ("reported".equals(filter)) {
                sql += "AND CR.report_id IS NOT NULL";
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
      public void updateCommentStatus(String commentId, String newStatus) {
        try {
            String sql = "UPDATE [EduFlex].[dbo].[Comment_Lesson] SET status = ? WHERE comment_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, newStatus);
            stm.setString(2, commentId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
