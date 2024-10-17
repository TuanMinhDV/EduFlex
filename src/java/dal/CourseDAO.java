package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Category;

public class CourseDAO extends DBContext {
    PreparedStatement stm;
    ResultSet rs;

    // Lấy danh sách khóa học với danh mục
    public ArrayList<Course> getCoursesWithCategories(String searchTerm, int page, int pageSize) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name, c.description, c.created_date, "
                   + "c.updated_date, c.price, c.discount, c.sold, ca.category_name "
                   + "FROM EduFlex.dbo.Course c "
                   + "JOIN EduFlex.dbo.Category ca ON c.category_id = ca.category_id "
                   + "WHERE c.course_name LIKE ? "
                   + "ORDER BY c.course_name "
                   + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + searchTerm + "%");  // tìm kiếm theo tên khóa học
            stm.setInt(2, (page - 1) * pageSize);  // tính offset
            stm.setInt(3, pageSize);  // giới hạn số hàng trả về
            rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDescription(rs.getString("description"));
                course.setCreateDate(rs.getString("created_date"));
                course.setUpdateDate(rs.getString("updated_date"));
                course.setCategory(rs.getString("category_name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }

    // Lấy tất cả danh mục (categories)
    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "SELECT category_id, category_name FROM EduFlex.dbo.Category";

        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    // Cập nhật danh mục cho khóa học
    public void updateCourseCategory(String courseId, String newCategoryId) {
        String sql = "UPDATE EduFlex.dbo.Course SET category_id = ? WHERE course_id = ?";

        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, newCategoryId);
            stm.setString(2, courseId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//     // Lấy danh sách các khóa học theo subjectID (đã sửa)
//    public List<Course> getCoursesBySubjectId(String subjectID) {
//        List<Course> courses = new ArrayList<>();
//        String query = "SELECT course_id, course_name, description FROM EduFlex.dbo.Course WHERE subject_id = ?";
//
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, subjectID);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Course course = new Course();
//                    course.setCourseId(rs.getString("course_id"));
//                    course.setCourseName(rs.getString("course_name"));
//                    course.setDescription(rs.getString("description"));
//                    courses.add(course);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return courses;
//    }
}
