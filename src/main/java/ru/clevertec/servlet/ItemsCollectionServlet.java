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
import java.util.List;

@Slf4j
@WebServlet(name = "items-collection-servlet", value = "/items")
public class ItemsCollectionServlet extends HttpServlet {
    private final ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("Init method from ItemsCollectionServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemResponse> items = itemService.getList();
        req.setAttribute("items", items);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        ItemRequest itemRequest = new ItemRequest(name);
        itemService.create(itemRequest);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.sendRedirect(req.getContextPath() + "/items");
    }

    @Override
    public void destroy() {
        super.destroy();
        log.info("Destroy method from ItemsCollectionServlet");
    }
}
