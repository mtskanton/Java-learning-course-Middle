package todolist.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import todolist.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

/**
 * Севрлет управления приложенияем.
 */
public class ItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        String sql = "from Item where done=false order by date";
        if (req.getParameter("include_done").equals("true")) {
             sql = "from Item order by date";
        }
        Query query = session.createQuery(sql);
        List<Item> list = query.list();

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

        session.getTransaction().commit();
        session.close();
        factory.close();

        Writer writer = resp.getWriter();
        writer.append(jsonArray.toJSONString());

        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        String description = req.getParameter("item_description");
        String id = req.getParameter("item_id");
        String done = req.getParameter("item_done");

        //если добавляем запись
        if (description != null) {
            Item item = new Item();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setDescription(description);
            item.setDate(timestamp);
            session.save(item);
        }

        //если обновляем статус существующей записи
        if (id != null && done != null) {
            Query query = session.createQuery("update Item set done=:isDone where id=:itemId");
            query.setParameter("isDone", Boolean.valueOf(done));
            query.setParameter("itemId", Integer.valueOf(id));
            int u = query.executeUpdate();
        }

        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}