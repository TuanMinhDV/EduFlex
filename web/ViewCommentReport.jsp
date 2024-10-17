<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Comments and Reports</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                font-family: 'Poppins', sans-serif;
                background-image: url('https://s1.1zoom.me/big0/175/Still-life_Lamp_Book_Old_603338_1280x854.jpg');
                background-size: cover;
                background-position: center;
                color: white;
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
            }
            .navbar {
                background-color: rgba(51, 51, 51, 0.9); /* Slight transparency */
                padding: 15px;
            }
            .navbar .logo {
                color: #ffcc00;
                font-size: 30px;
                font-weight: bold;
                text-transform: uppercase;
                margin-right: 20px;
            }
            .navbar-nav .nav-link {
                color: white;
                padding: 14px 20px;
                transition: background-color 0.3s, color 0.3s;
            }
            .navbar-nav .nav-link:hover {
                background-color: #575757;
                color: #ffcc00;
            }
            .container {
                padding: 50px;
                max-width: 1200px;
                background-color: rgba(255, 255, 255, 0.95);
                border-radius: 8px;
                color: black;
                box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            }
            h1 {
                margin-bottom: 30px;
                font-weight: bold;
            }
            table {
                background-color: white;
                color: black;
            }
            table th {
                background-color: #343a40;
                color: white;
            }
            .form-control, .btn {
                margin-bottom: 20px;
            }
            .btn-warning, .btn-danger {
                margin-right: 5px;
            }
            .pagination a {
                margin: 0 5px;
                padding: 5px 10px;
                background-color: #f8f9fa;
                border: 1px solid #dee2e6;
                border-radius: 5px;
                color: #343a40;
                text-decoration: none;
            }
            .pagination a:hover {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand logo" href="#">EduFlex</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/EduFlexInter2/index.html">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/EduFlexInter2/about">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/EduFlexInter2/courses">Courses</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/EduFlexInter2/subject">View Subject List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/EduFlexInter2/commentcontroller">View Comment Report</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="container mt-5">
            <h1 class="text-center">Comments and Reports</h1>

            <!-- Search Form -->
            <form action="${pageContext.request.contextPath}/commentcontroller" method="GET" class="mb-4">
                <div class="input-group mb-3">
                    <input type="text" name="search" class="form-control" value="${search}" placeholder="Tìm kiếm comment...">
                    <div class="input-group-text">
                        <input type="checkbox" name="filter" value="reported" <c:if test="${filter == 'reported'}">checked</c:if>> Chỉ hiển thị comment bị report
                    </div>
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </div>
            </form>

            <!-- Comments Table -->
            <table class="table table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Comment ID</th>
                        <th>Account ID</th>
                        <th>Lesson ID</th>
                        <th>Comment</th>
                        <th>Comment Date</th>
                        <th>Status</th>
                        <th>Report ID</th>
                        <th>Cause</th>
                        <th>Report Date</th>
                        <th>Change Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comment" items="${comments}">
                        <tr>
                            <td>${comment.comment_id}</td>
                            <td>${comment.account_id}</td>
                            <td>${comment.lesson_id}</td>
                            <td>${comment.comment}</td>
                            <td>${comment.comment_date}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${comment.status == '1'}">
                                        Active
                                    </c:when>
                                    <c:otherwise>
                                        Blocked
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${comment.report_id}</td>
                            <td>${comment.cause}</td>
                            <td>${comment.report_date}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/commentcontroller" method="POST">
                                    <input type="hidden" name="commentId" value="${comment.comment_id}">
                                    <input type="hidden" name="newStatus" value="${comment.status == '1' ? '0' : '1'}">
                                    <button type="submit" class="btn btn-warning btn-sm">Change Status</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Pagination -->
            <div class="d-flex justify-content-between align-items-center mt-4">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/commentcontroller?page=${currentPage - 1}&search=${search}" class="btn btn-primary">Trang trước</a>
                </c:if>

                <div class="pagination">
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <a href="${pageContext.request.contextPath}/commentcontroller?page=${i}&search=${search}" class="${i == currentPage ? 'btn btn-primary' : 'btn btn-secondary'}">${i}</a>
                    </c:forEach>
                </div>

                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/commentcontroller?page=${currentPage + 1}&search=${search}" class="btn btn-primary">Trang sau</a>
                </c:if>
            </div>
        </div>

        <!-- Bootstrap JS and Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
