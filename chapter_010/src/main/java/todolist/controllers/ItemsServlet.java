package todolist.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import todolist.logic.ItemStorage;
import todolist.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

/**
 * Севрлет управления приложенияем.
 */
public class ItemsServlet extends HttpServlet {

    private final ItemStorage storage = ItemStorage.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Item> list = null;

        if (req.getParameter("include_done").equals("true")) {
            list = storage.getAll();
        } else {
            list = storage.getAllUndone();
        }

        JSONArray jsonArray = new JSONArray();
        for (Item item : list) {
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
            String date = df.format(item.getDate()).toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("description", item.getDescription());
            jsonObject.put("date", date);
            jsonObject.put("done", item.isDone());
            jsonArray.add(jsonObject);
        }

        Writer writer = resp.getWriter();
        writer.append(jsonArray.toJSONString());

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String description = req.getParameter("item_description");
        String id = req.getParameter("item_id");
        String done = req.getParameter("item_done");

        //если добавляем запись
        if (description != null) {
            Item item = new Item();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setDescription(description);
            item.setDate(timestamp);
            storage.add(item);
        }

        //если обновляем статус существующей записи
        if (id != null && done != null) {
            storage.toggleDone(Integer.valueOf(id), Boolean.valueOf(done));
        }
    }
}