package application;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс получения списка городов на соответствующую страну.
 */
public class CitiesForCountry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        String country = req.getParameter("country");
        if (!country.equals("")) {
            List<City> cities = DbManager.INSTANCE.getCitiesForCountry(country);

            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("name", "");
            jsonArray.add(jsonObject);

            for (City city : cities) {
                String name = city.getName();
                jsonObject = new JSONObject();
                jsonObject.put("name", name);
                jsonArray.add(jsonObject);
            }

            writer.append(jsonArray.toJSONString());
            writer.flush();
        } else {
            writer.append("[]");
            writer.flush();
        }
    }
}
