package app.example.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * форма загрузки файла.
 */
public class UploadForm {

    private String directory;
    private MultipartFile[] files;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
