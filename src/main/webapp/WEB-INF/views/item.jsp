<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.clevertec.dto.ItemResponse" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Item details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center mb-4">Item details</h2>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">ID: ${item.id}</h5>
                        <p class="card-text">Name: ${item.name}</p>
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/update-item?id=${item.id}&name=${item.name}'">Edit</button>
                            <form action="${pageContext.request.contextPath}/items/${item.id}" method="post" style="display: inline;">
                                <input type="hidden" name="_method" value="delete">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="mt-4 text-center">
                    <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/items'">Back to List</button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpathmaxcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


