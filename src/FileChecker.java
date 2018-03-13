import java.io.*;

public class FileChecker extends javax.swing.filechooser.FileFilter {

    /**
     *  Returns supported image files
     *
     * @return Supported image types
     */
    public String getDescription() {
        return "JPG and PNG images";
    }

    /**
     * Determines if file is a directory or an accepted image file
     *
     * @param f File to check
     * @return  True if File is directory or accepted extension
     */
    public boolean accept(File f) {
        return f.isDirectory() || isImageFile(getExtension((f)));
    }

    /**
     * Checks if the extension of the file is either JPG or PNG
     *
     * @param ext   Extension to check
     * @return      True if extension is either JPG or PNG
     */
    private boolean isImageFile(String ext) {
        return (ext.equals("jpg") || ext.equals("png"));
    }

    /**
     * Returns the extension of the file
     *
     * @param f File to extract the extension from
     * @return  Extension of the file; "" if none
     */
    public static String getExtension(File f) {
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            return s.substring(i + 1).toLowerCase();
        }
        return "";
    }
}