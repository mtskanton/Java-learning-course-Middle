package app.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер загрузки / скачивания файлов.
 */
@Controller
public class FileController {

    @Autowired
    ServletContext servletContext;

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        File dir = new File(servletContext.getRealPath("img"));
        List<String> filenames = new ArrayList<>();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for(File file : files) {
                filenames.add(file.getName());
            }
        }
        model.addAttribute("files", filenames);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("file") MultipartFile[] files) {
        this.doUpload(files);
        return "redirect:upload";
    }

    private void doUpload(MultipartFile[] files) {
        File dir = new File(servletContext.getRealPath("") + File.separator + "img");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                File uploadedFile = new File (dir.getAbsolutePath() + File.separator + filename);

                try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(uploadedFile))) {
                    byte[] bytes = file.getBytes();
                    output.write(bytes);
                    output.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
