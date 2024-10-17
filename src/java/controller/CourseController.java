package controller;

import dal.CourseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Course;
import model.Category;

public class CourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();

        // Nhận các tham số từ request
        String searchTerm = request.getParameter("searchTerm") != null ? request.getParameter("searchTerm") : "";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 10;  // Số lượng khóa học trên mỗi trang

        // Lấy danh sách khóa học với tìm kiếm và phân trang
        ArrayList<Course> courses = courseDAO.getCoursesWithCategories(searchTerm, page, pageSize);
        
        // Lấy danh sách các danh mục (categories)
        ArrayList<Category> categories = courseDAO.getAllCategories();

        // Đưa danh sách khóa học, danh mục, và các tham số vào request
        request.setAttribute("courses", courses);
        request.setAttribute("categories", categories);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("currentPage", page);

        // Chuyển hướng tới CourseList.jsp
        request.getRequestDispatcher("CourseList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();

        // Nhận tham số từ request
        String courseId = request.getParameter("courseId");
        String newCategoryId = request.getParameter("newCategory");

        // Gọi DAO để cập nhật category của khóa học
        courseDAO.updateCourseCategory(courseId, newCategoryId);

        // Sau khi cập nhật, chuyển hướng lại trang danh sách khóa học
        response.sendRedirect("course");
    }

    @Override
    public String getServletInfo() {
        return "Course Controller Servlet";
    }
}
