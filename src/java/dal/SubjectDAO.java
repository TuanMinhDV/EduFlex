
package dal;

import java.sql.*;
import java.util.ArrayList;
import model.Subject;


public class SubjectDAO extends DBContext {
    PreparedStatement stm;
    ResultSet rs;

    public ArrayList<Subject> getSubjects(String name, int page, int pageSize) {
    ArrayList<Subject> data = new ArrayList<>();
    try {
        String strSQL = "SELECT [category_id], [category_name] FROM [EduFlex].[dbo].[Category] ";
        
        // Nếu có từ khóa tìm kiếm thì thêm điều kiện WHERE
        if (name != null && !name.isEmpty()) {
            strSQL += "WHERE [category_name] LIKE ? ";
        }
        
        strSQL += "ORDER BY [category_id] OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        stm = connection.prepareStatement(strSQL);
        
        int paramIndex = 1;
        if (name != null && !name.isEmpty()) {
            stm.setString(paramIndex++, "%" + name + "%");  // Thêm từ khóa tìm kiếm
        }
        
        stm.setInt(paramIndex++, (page - 1) * pageSize); // vị trí bắt đầu của trang
        stm.setInt(paramIndex, pageSize);  // số bản ghi trên mỗi trang

        rs = stm.executeQuery();
        while (rs.next()) {
            String subjectID = rs.getString(1);
            String subjectName = rs.getString(2);
            Subject subject = new Subject(subjectID, subjectName);
            data.add(subject);
        }
    } catch (Exception e) {
        System.out.println("getSubjects: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return data;
}

     public int countSubjects(String name) {
    int count = 0;
    try {
        String strSQL = "SELECT COUNT(*) FROM [EduFlex].[dbo].[Category] ";
        
        if (name != null && !name.isEmpty()) {
            strSQL += "WHERE [category_name] LIKE ?";
        }
        
        stm = connection.prepareStatement(strSQL);
        
        if (name != null && !name.isEmpty()) {
            stm.setString(1, "%" + name + "%");
        }

        rs = stm.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("countSubjects: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return count;
}

    
    public ArrayList<Subject> searchSubjectByName(String name, int page, int pageSize) {
    ArrayList<Subject> data = new ArrayList<>();
    try {
        String strSQL = "SELECT [category_id], [category_name] FROM [EduFlex].[dbo].[Category] " +
                        "WHERE [category_name] LIKE ? ORDER BY [category_id] " +
                        "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, "%" + name + "%");
        stm.setInt(2, (page - 1) * pageSize); // tính vị trí bắt đầu của trang
        stm.setInt(3, pageSize); // số lượng bản ghi trên mỗi trang

        rs = stm.executeQuery();
        while (rs.next()) {
            String subjectID = rs.getString(1);
            String subjectName = rs.getString(2);
            Subject subject = new Subject(subjectID, subjectName);
            data.add(subject);
        }
    } catch (Exception e) {
        System.out.println("searchSubjectByName: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return data;
}

    public int countSearchResults(String name) {
    int count = 0;
    try {
        String strSQL = "SELECT COUNT(*) FROM [EduFlex].[dbo].[Category] WHERE [category_name] LIKE ?";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, "%" + name + "%");

        rs = stm.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("countSearchResults: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return count;
}

    
    public void deleteSubject(String subjectID) {
    try {
        String strSQL = "DELETE FROM [EduFlex].[dbo].[Category] WHERE [category_id] = ?";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, subjectID);
        stm.executeUpdate();
    } catch (Exception e) {
        System.out.println("deleteSubject: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
}

    
    public boolean isSubjectExist(String subjectName) {
    try {
        String strSQL = "SELECT COUNT(*) FROM [EduFlex].[dbo].[Category] WHERE [category_name] = ?";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, subjectName);
        rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        System.out.println("isSubjectExist: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return false;
}

  public void addSubject(String subjectName) {
    try {
        String strSQL = "INSERT INTO [EduFlex].[dbo].[Category] ([category_name]) VALUES (?)";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, subjectName);
        stm.executeUpdate();
    } catch (Exception e) {
        System.out.println("addSubject: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
}
  public Subject getSubjectById(String subjectID) {
    Subject subject = null;
    try {
        String strSQL = "SELECT [category_id], [category_name] FROM [EduFlex].[dbo].[Category] WHERE [category_id] = ?";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, subjectID);
        rs = stm.executeQuery();
        if (rs.next()) {
            String subjectName = rs.getString("category_name");
            subject = new Subject(subjectID, subjectName);
        }
    } catch (Exception e) {
        System.out.println("getSubjectById: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return subject;
}

  public void updateSubject(String subjectID, String subjectName) {
    try {
        String strSQL = "UPDATE [EduFlex].[dbo].[Category] SET [category_name] = ? WHERE [category_id] = ?";
        stm = connection.prepareStatement(strSQL);
        stm.setString(1, subjectName);
        stm.setString(2, subjectID);
        stm.executeUpdate();
    } catch (Exception e) {
        System.out.println("updateSubject: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
        } catch (SQLException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
}

  


}
