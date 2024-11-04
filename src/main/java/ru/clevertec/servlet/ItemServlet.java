package ru.clevertec.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ru.clevertec.dto.ItemRequest;
import ru.clevertec.dto.ItemResponse;
import ru.clevertec.service.ItemService;
import ru.clevertec.service.ItemServiceImpl;

import java.io.IOException;

@Slf4j
@WebServlet(name = "item-servlet", value = "/items/*")
public class ItemServlet extends HttpServlet {
    private final ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("Init method from ItemServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = getId(req, resp);
        if (id == null) return;

        ItemResponse itemResponse = itemService.getOne(id);
        if (itemResponse == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Item with ID not found");
            return;
        }

        req.setAttribute("item", itemResponse);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/item.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getParameter("_method");
        if ("put".equalsIgnoreCase(method)) {
            this.doPut(req, resp);
        } else if ("delete".equalsIgnoreCase(method)) {
            this.doDelete(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Invalid method");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getId(req, resp);
        if (id == null) return;

        String name = req.getParameter("name");
        ItemRequest itemRequest = new ItemRequest(name);

        ItemResponse itemResponse = itemService.update(id, itemRequest);
        if (itemResponse == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Item with ID not found");
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.sendRedirect(req.getContextPath() + "/items");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = getId(req, resp);
        if (id == null) return;

        itemService.delete(id);

        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        resp.sendRedirect(req.getContextPath() + "/items");
    }

    private Long getId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item ID is missing");
            return null;
        }

        try {
            String itemIdStr = pathInfo.substring(1);
            return Long.parseLong(itemIdStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid item ID");
            return null;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        log.info("Destroy method from ItemServlet");
    }
}
