<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Subject List</title>
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
            background-color: rgba(34, 34, 34, 0.9); 
        }
        .navbar .logo {
            color: #ffcc00;
            font-size: 1.8rem;
            font-weight: bold;
            margin-right: 20px;
        }
        .navbar-nav .nav-link {
            color: #fff;
            padding: 0.75rem 1.25rem;
            transition: color 0.3s ease;
        }
        .navbar-nav .nav-link:hover {
            color: #ffcc00;
        }
        .container {
            padding: 50px;
            max-width: 900px;
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 8px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            color: black;
        }
        h2 {
            font-weight: bold;
            color: #333;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        table {
            background-color: white;
            color: black;
            margin-top: 20px;
        }
        table th {
            background-color: #343a40;
            color: white;
        }
        table td {
            vertical-align: middle;
        }
        .btn-warning, .btn-danger {
            margin-right: 5px;
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
        <h2 class="text-center">${subject != null ? 'Update Subject' : 'Add New Subject'}</h2>
        <form method="POST" action="subject" class="mb-4">
            <c:if test="${subject != null}">
                <input type="hidden" name="subjectID" value="${subject.subjectID}">
            </c:if>
            <div class="mb-3">
                <label for="subjectName" class="form-label">Subject Name:</label>
                <input type="text" id="subjectName" name="subjectName" class="form-control" value="${subject != null ? subject.subjectName : ''}" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">${subject != null ? 'Update Subject' : 'Add Subject'}</button>
        </form>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <a href="http://localhost:8080/EduFlexInter2/course" class="btn btn-secondary mb-4">View Course List</a>

        <h2 class="text-center">List of Subjects</h2>

        <form method="GET" action="subject" class="mb-4">
            <div class="input-group">
                <input type="text" name="search" class="form-control" placeholder="Search by Subject Name" value="${search}">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>

        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th>SubjectID</th>
                    <th>SubjectName</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${data}" var="s">
                    <tr>
                        <td>${s.subjectID}</td>
                        <td>${s.subjectName}</td>
                        <td>
<!--                             View Details Button 
                            <a href="subjectDetail?subjectID=${s.subjectID}" class="btn btn-info btn-sm">View Details</a>-->

                            <!-- Edit Button -->
                            <a href="subject?action=edit&subjectID=${s.subjectID}" class="btn btn-warning btn-sm">Edit</a>

                            <!-- Delete Button -->
                            <a href="subject?action=delete&subjectID=${s.subjectID}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this subject?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-between align-items-center mt-4">
            <c:if test="${currentPage > 1}">
                <a href="subject?search=${search}&page=${currentPage - 1}" class="btn btn-primary">Previous</a>
            </c:if>

            <span>Page ${currentPage} of ${totalPages}</span>

            <c:if test="${currentPage < totalPages}">
                <a href="subject?search=${search}&page=${currentPage + 1}" class="btn btn-primary">Next</a>
            </c:if>
        </div>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
