import java.io.*;

public class FileChcecker extends javax.swing.filechooser.FileFilter {
    protected boolean isImageFile(String ext) {
        return (ext.equals("jpg") || ext.equals("png"));
    }

    public String getDescription() {
        return "";
    }

    public boolean accept(File f) {
        return false;
    }
}