package controller;

import dal.CommentDAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;

@WebServlet(name = "CommentController", urlPatterns = {"/commentcontroller"})
public class CommentController extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommentDAO da = new CommentDAO();

        String search = request.getParameter("search") != null ? request.getParameter("search") : "";
        String filter = request.getParameter("filter") != null ? request.getParameter("filter") : "";
        int pageNumber = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 10;

        ArrayList<Comment> data = da.getListComment(search, filter, pageNumber, pageSize);
        int totalComments = da.getTotalComments(search, filter);
        int totalPages = (int) Math.ceil((double) totalComments / pageSize);

        request.setAttribute("comments", data);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);
        request.setAttribute("filter", filter);

        request.getRequestDispatcher("ViewCommentReport.jsp").forward(request, response);
    }


     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý thay đổi status
        String commentId = request.getParameter("commentId");
        String newStatus = request.getParameter("newStatus");

        CommentDAO da = new CommentDAO();
        da.updateCommentStatus(commentId, newStatus);

        // Sau khi cập nhật, chuyển hướng lại trang danh sách
        response.sendRedirect(request.getContextPath() + "/commentcontroller");
    }

    @Override
    public String getServletInfo() {
        return "Comment Controller Servlet";
    }
}
