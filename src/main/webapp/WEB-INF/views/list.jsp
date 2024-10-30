<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.clevertec.dto.ItemResponse" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of items</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12 text-center mb-4 d-flex justify-content-between">
                <h2>List of items</h2>
                <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/create-item'">Create new item</button>
            </div>
            <div class="col-md-12">
                <%
                    List<ItemResponse> items = (List<ItemResponse>) request.getAttribute("items");
                    if (items != null && !items.isEmpty()) {
                %>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (ItemResponse item : items) {
                            %>
                                <tr>
                                    <td><%= item.getId() %></td>
                                    <td><%= item.getName() %></td>
                                    <td>
                                        <button class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/items/<%= item.getId() %>'">View</button>
                                        <button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/update-item?id=<%= item.getId() %>&name=<%= item.getName()%>'">Edit</button>
                                        <form action="${pageContext.request.contextPath}/items/<%= item.getId() %>" method="post" style="display: inline;">
                                            <input type="hidden" name="_method" value="delete">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                <%
                    } else {
                %>
                    <div class="alert alert-info text-center">No items found</div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>



