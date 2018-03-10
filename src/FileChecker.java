import java.io.*;

public class FileChecker extends javax.swing.filechooser.FileFilter {

    // Checks if the extension of the file is either JPG or PNG
    protected boolean isImageFile(String ext) {
        return (ext.equals("jpg") || ext.equals("png"));
    }

    // Returns supported image files
    public String getDescription() {
        return "JPG and PNG images";
    }

    // Returns the extension of the file
    private static String getExtension(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            return s.substring(i+1).toLowerCase();
        }
        return "";
    }

    // Determines if file is a directory or an accepted image file
    public boolean accept(File f) {
         return f.isDirectory() || isImageFile(getExtension((f)));
    }
}