package app.example.controller;

import app.example.form.UploadForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    private final String DEFAULT_DIRECTORY = "img";

    @GetMapping("/upload")
    public String uploadPage(Model model) {
        File dir = new File(servletContext.getRealPath(DEFAULT_DIRECTORY));
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
    public String uploadFiles(@RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes) {
        String result = "";
        try {
            result = this.doUpload(files, DEFAULT_DIRECTORY);
            if (result.equals("")) {
                redirectAttributes.addFlashAttribute("result", "Выберите файл для загрузки");
            } else {
                redirectAttributes.addFlashAttribute("result", "Загрузка успешно выполнена");
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("result", "Ошибка при загрузке: " + e.getMessage());
        }
        return "redirect:upload";
    }

    @GetMapping("/restUpload")
    public String restUploadPage() {
        return "restUpload";
    }

    @PostMapping("/restUpload")
    @ResponseBody
    public ResponseEntity<?> restUploadFiles(@ModelAttribute UploadForm uploadForm) {
        String result = null;
        try {
            String directory = (uploadForm.getDirectory().isEmpty()) ? DEFAULT_DIRECTORY : uploadForm.getDirectory();
            result = this.doUpload(uploadForm.getFiles(), directory);
        } catch (IOException e) {
            return new ResponseEntity<>("Ошибка: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (result.equals("")) {
            return new ResponseEntity<>("Выберите файл для загрузки", HttpStatus.OK);
        }
        return new ResponseEntity<>("Загрузка успешно выполнена : <br/>" + result, HttpStatus.OK);
    }

    private String doUpload(MultipartFile[] files, String folder) throws IOException{

        String result = "";
        StringBuilder sb = new StringBuilder();

        File dir = new File(servletContext.getRealPath("") + File.separator + "img");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                File uploadedFile = new File (dir.getAbsolutePath() + File.separator + filename);
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                byte[] bytes = file.getBytes();
                output.write(bytes);
                output.flush();
                output.close();
                sb.append(folder).append("/").append(filename).append("<br />");
                result = sb.toString();
            }
        }
        return result;
    }
}
