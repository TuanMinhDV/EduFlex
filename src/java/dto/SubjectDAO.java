package dto;

import dto.DBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Category;

import model.Subject;

/**
 *
 * @author lam12
 */
public class SubjectDAO extends DBContext {

    // get subject of lecturer and search ajax
    public List<Subject> getSubjectBankByLecturer(int lecturer_id, String txt) {
        List<Subject> list = new ArrayList<>();
        String sql = "select s.* \n"
                + "from [Subject] s\n"
                + "JOIN Account a ON a.account_id = s.lecturer_id\n"
                + "WHERE s.lecturer_id = ? AND s.subject_name like '%"+txt+"%'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, lecturer_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                byte[] imageData = rs.getBytes(4);
                String subjectImage = new String(Base64.getEncoder().encode(imageData));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt(1));
                s.setSubject_name(rs.getString(2));
                s.setImage(subjectImage);
                list.add(s);
            }
        } catch (SQLException e) {
        }

        return list;
    }
    
    public List<Subject> getTop5MostRatedSubjects() {
        List<Subject> list = new ArrayList<>();
        String query = "SELECT TOP 5\n"
                + "Subject.*,\n"
                + "Account.fullname,\n"
                + "ROUND(\n"
                + "(SELECT AVG(avg_rate) \n"
                + "FROM\n"
                + "(SELECT Course.course_id, AVG(Learner_Course.rate) AS avg_rate\n"
                + "FROM Learner_Course\n"
                + "INNER JOIN Course ON Learner_Course.course_id = Course.course_id\n"
                + "GROUP BY Course.course_id) AS course_rate\n"
                + "WHERE course_rate.course_id IN (SELECT Course.course_id\n"
                + "FROM Course\n"
                + "WHERE Course.subject_id = Subject.subject_id)), 1) AS subject_rate\n"
                + "FROM Subject INNER JOIN Account ON Subject.lecturer_id = Account.account_id\n"
                + "order by subject_rate desc";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                String base64Image = new String(Base64.getEncoder().encode(imageData));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setDescription(rs.getString("description"));
                s.setImage(base64Image);
                s.setPrice(rs.getFloat("price"));
                s.setDiscount(rs.getFloat("discount"));
                s.setSold(rs.getInt("sold"));
                s.setCreated_date(rs.getString("created_date"));
                s.setUpdated_date(rs.getString("updated_date"));
                s.setCategory_id(rs.getInt("category_id"));
                s.setLecturer_id(rs.getInt("lecturer_id"));
                s.setLecturer_name(rs.getString("fullname"));
                s.setRate_subject(rs.getDouble("subject_rate"));
                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Subject> getTop4NewestSubject() {
        List<Subject> list = new ArrayList<>();
        String query = "SELECT TOP 4 * FROM SUBJECT ORDER BY created_date desc";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                String base64Image = new String(Base64.getEncoder().encode(imageData));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setDescription(rs.getString("description"));
                s.setImage(base64Image);
                s.setPrice(rs.getFloat("price"));
                s.setDiscount(rs.getFloat("discount"));
                s.setSold(rs.getInt("sold"));
                s.setCreated_date(rs.getString("created_date"));
                s.setUpdated_date(rs.getString("updated_date"));
                s.setCategory_id(rs.getInt("category_id"));
                s.setLecturer_id(rs.getInt("lecturer_id"));
                list.add(s);
            }
        } catch (SQLException e) {
        }

        return list;
    }

    public boolean updateSubjectWithSubjectIDByLecturer(String subject_name, String description, String image, double discount, double price, int category_id, int subject_id) {
        String query1 = "UPDATE [dbo].[Subject]\n"
                + "   SET [subject_name] = ?\n"
                + "      ,[description] = ? \n"
                + "      ,[image] = ?\n"
                + "      ,[discount] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[category_id] = ?\n"
                + "      ,[updated_date] = getdate()\n"
                + " WHERE subject_id = ?\n";

        String query2 = "UPDATE [dbo].[Subject]\n"
                + "   SET [subject_name] = ?\n"
                + "      ,[description] = ? \n"
                + "      ,[discount] = ? \n"
                + "      ,[price] = ? \n"
                + "      ,[category_id] = ?\n"
                + "      ,[updated_date] = getdate()\n"
                + " WHERE subject_id = ?\n";

        if (image != null) {
            String pathToFile = "D:/FPTU/Sem5/SWP391/ImageRepository/";
            File subjectImage = new File(pathToFile + image);
            try ( InputStream subjectStream = new FileInputStream(subjectImage)) {
                PreparedStatement st = connection.prepareStatement(query1);
                st.setString(1, subject_name);
                st.setString(2, description);
                st.setBinaryStream(3, subjectStream);
                st.setDouble(4, discount);
                st.setDouble(5, price);
                st.setInt(6, category_id);
                st.setInt(7, subject_id);
                st.executeUpdate();
                return true;
            } catch (SQLException | IOException e) {
            }
        } else {
            try {
                PreparedStatement st = connection.prepareStatement(query2);
                st.setString(1, subject_name);
                st.setString(2, description);
                st.setDouble(3, discount);
                st.setDouble(4, price);
                st.setInt(5, category_id);
                st.setInt(6, subject_id);
                st.executeUpdate();
                return true;
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public List<Subject> getAllSubjectByLecturerId(int lecturer_id) {
        List<Subject> list = new ArrayList<>();
        String query = "SELECT [subject_id]\n"
                + "      ,[subject_name]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[price]\n"
                + "      ,[discount]\n"
                + "      ,[sold]\n"
                + "      ,[created_date]\n"
                + "      ,[updated_date]\n"
                + "      ,[category_id]\n"
                + "  FROM [dbo].[Subject]\n"
                + "  Where lecturer_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, lecturer_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                String base64Image = new String(Base64.getEncoder().encode(imageData));
                Subject s = new Subject();
                s.setSubject_id(rs.getInt("subject_id"));
                s.setSubject_name(rs.getString("subject_name"));
                s.setDescription(rs.getString("description"));
                s.setImage(base64Image);
                s.setPrice(rs.getFloat("price"));
                s.setDiscount(rs.getFloat("discount"));
                s.setSold(rs.getInt("sold"));
                s.setCreated_date(rs.getString("created_date"));
                s.setUpdated_date(rs.getString("updated_date"));
                s.setCategory_id(rs.getInt("category_id"));
               
                list.add(s);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean deleteSubjectBySubjectId(int subject_id) {
        String query = "DELETE FROM [Subject] WHERE subject_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, subject_id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }

    // add subject
    public boolean addSubject(String subject_name, String description, String image, double price, double discount, int category_id, int lecturer_id) {
        String query1 = "INSERT INTO [dbo].[Subject]\n"
                + "([subject_name], "
                + "[description], "
                + "[image], "
                + "[price], "
                + "[discount], "
                + "[created_date], "
                + "[updated_date], "
                + "[category_id], "
                + "[lecturer_id])\n"
                + "VALUES (?,?,?,?,?,getdate(),null,?,?)";

        String pathToFile = "D:/FPTU/Sem5/SWP391/ImageRepository/";
        File subjectImage = new File(pathToFile + image);
        try ( InputStream subjectStream = new FileInputStream(subjectImage)) {
            PreparedStatement st = connection.prepareStatement(query1);
            st.setString(1, subject_name);
            st.setString(2, description);
            st.setBinaryStream(3, subjectStream);
            st.setDouble(4, price);
            st.setDouble(5, discount);
            st.setInt(6, category_id);
            st.setInt(7, lecturer_id);
            st.executeUpdate();
            return true;
        } catch (SQLException | IOException e) {
        }

        return false;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "  FROM [OnlineLearning].[dbo].[Category]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category cy = new Category();
                cy.setCategory_id(rs.getInt("category_id"));
                cy.setCategory_name(rs.getString("category_name"));

                list.add(cy);
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public Subject getSubjectBySubjectId(int subject_id) {
        String query = "SELECT [subject_name]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[created_date]\n"
                + "      ,[updated_date]\n"
                + "      ,[price]\n"
                + "      ,[discount]\n"
                + "      ,[sold]\n"
                + "      ,[category_id]\n"
                + "      ,[lecturer_id]\n"
                + "  FROM [Subject]"
                + "  WHERE subject_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, subject_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                byte[] imageData = rs.getBytes("image");
                String base64Image = new String(Base64.getEncoder().encode(imageData));
                Subject s = new Subject();
                s.setSubject_id(subject_id);
                s.setSubject_name(rs.getString("subject_name"));
                s.setDescription(rs.getString("description"));
                s.setImage(base64Image);
                s.setPrice(rs.getFloat("price"));
                s.setDiscount(rs.getFloat("discount"));
                s.setSold(rs.getInt("sold"));
                s.setCreated_date(rs.getString("created_date"));
                s.setUpdated_date(rs.getString("updated_date"));
                s.setCategory_id(rs.getInt("category_id"));
                s.setLecturer_id(rs.getInt("lecturer_id"));
                return s;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    public static void main(String[] args) {
        SubjectDAO s = new SubjectDAO();
        System.out.println(        s.updateSubjectWithSubjectIDByLecturer("Subject Test", "huhuhufsdf", null, 10, 1000, 1, 6)
);
        List<Subject> lists = s.getTop5MostRatedSubjects();
                List<Subject> lists2 = s.getTop4NewestSubject();
        System.out.println(lists);
        System.out.println(lists2);
                
    }   
   

}//end dao
