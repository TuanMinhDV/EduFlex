package controller;

import dal.SubjectDAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Subject;

public class SubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDAO da = new SubjectDAO();
        
        String action = request.getParameter("action");
        String subjectID = request.getParameter("subjectID");

        if ("delete".equals(action) && subjectID != null) {
            // Xóa subject nếu có yêu cầu delete
            da.deleteSubject(subjectID);
        } else if ("edit".equals(action) && subjectID != null) {
            // Chỉnh sửa subject nếu có yêu cầu edit
            Subject subject = da.getSubjectById(subjectID);
            request.setAttribute("subject", subject);
        }

        // Phân trang và tìm kiếm
        String search = request.getParameter("search");
        String pageStr = request.getParameter("page");
        int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
        int pageSize = 10;

        ArrayList<Subject> data = da.getSubjects(search, page, pageSize);
        int totalResults = da.countSubjects(search);
        int totalPages = (int) Math.ceil((double) totalResults / pageSize);

        // Truyền dữ liệu sang JSP
        request.setAttribute("data", data);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("search", search);
        
        request.getRequestDispatcher("SubjectList.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    SubjectDAO da = new SubjectDAO();
    
    String subjectID = request.getParameter("subjectID");
    String subjectName = request.getParameter("subjectName");
    String search = request.getParameter("search");
    String pageStr = request.getParameter("page");
    int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);

    // Kiểm tra tính hợp lệ của subjectName
    if (!subjectName.matches("[A-Za-z0-9 ]+")) {
        request.setAttribute("error", "Subject name must only contain A-Z, a-z, 0-9.");
    } else if (da.isSubjectExist(subjectName) && (subjectID == null || !da.getSubjectById(subjectID).getSubjectName().equals(subjectName))) {
        request.setAttribute("error", "Subject name already exists.");
    } else {
        if (subjectID == null || subjectID.isEmpty()) {
            // Thêm mới nếu không có subjectID
            da.addSubject(subjectName);
            request.setAttribute("message", "Subject added successfully.");
        } else {
            // Cập nhật nếu có subjectID
            da.updateSubject(subjectID, subjectName);
            request.setAttribute("message", "Subject updated successfully.");
        }
    }

    // Sau khi xử lý, tải lại danh sách subject và giữ nguyên phân trang
    response.sendRedirect("subject?search=" + (search == null ? "" : search) + "&page=" + page);
}


    @Override
    public String getServletInfo() {
        return "Subject management servlet with add, edit, delete, search, and pagination features.";
    }
}
