package application.mvc.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.xml.ws.RequestWrapper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Контроллер загрузки файлов.
 */
@Controller
public class FileUploadController {

    @Autowired
    private ServletContext servletContext;

    @PostMapping
    @RequestMapping(value = "/uploadFile")
    public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file) {

        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");

                File uploadedFile = new File(servletContext.getRealPath("") + File.separator + file.getOriginalFilename());
                System.out.println(uploadedFile);
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                output.write(bytes);
                output.close();
                return "file uploaded";

            } catch (IOException ioe) {
                ioe.printStackTrace();
                return "exception";
            }
        } else {
            return "file is empty";
        }
    }
}
