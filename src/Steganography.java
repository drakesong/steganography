import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Steganography {

    /**
     * Empty constructor
     */
    public Steganography() {
    }

    /**
     * Creates a PNG image file with the message encrypted in it
     *
     * @param path      Path of the image file that is to be modified
     * @param original  Name of the original image
     * @param ext       Extension of the image file (has to be JPG or PNG)
     * @param output    Name of the output image file
     * @param msg       Message to encrypt into image
     * @return          Saves modified PNG image with the specified name (output) into path
     */
    public boolean encode(String path, String original, String ext, String output, String msg) {
        String fileName = imagePath(path, original, ext);
        BufferedImage imgOrig = getImage(fileName);

        BufferedImage img = userSpace(imgOrig);
        img = addText(img, msg);

        return setImage(img, new File(imagePath(path, output, "png")), "png");
    }

    /**
     * Combines inputs to create a full accessible path
     *
     * @param path  Path of the image file
     * @param name  Name of the image
     * @param ext   Extension of the image
     * @return      Full path for the image (path/name.ext)
     */
    private String imagePath(String path, String name, String ext) {
        return path + "/" + name + "." + ext;
    }

    /**
     * Get method that returns the image
     *
     * @param f Complete path to the file
     * @return  BufferedImage of the given image
     */
    private BufferedImage getImage(String f) {
        BufferedImage img = null;
        File file = new File(f);

        try {
            img = ImageIO.read(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Image could not be read!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        return img;
    }

    /**
     * Creates a copy of teh BufferedImage for editing and saving purposes
     *
     * @param img   Image that is to be edited
     * @return      User space of a BufferedImage where compression interferences won't occur
     */
    private BufferedImage userSpace(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = newImg.createGraphics();
        graphics.drawRenderedImage(img, null);
        graphics.dispose();
        return newImg;
    }

    /**
     * Adds text into image
     *
     * @param image Image where the text is to be added
     * @param txt   Text that is to be added
     * @return      Image with the text embedded
     */
    private BufferedImage addText(BufferedImage image, String txt) {
        byte img[] = getByteData(image);
        byte msg[] = txt.getBytes();
        byte len[] = bitConversion(msg.length);

        try {
            encodeText(img, len, 0);
            encodeText(img, msg, 32);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Target file could not be found!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /**
     * Gets the byte array of the image
     *
     * @param img   Image to get the byte data from
     * @return      Byte array of the image
     */
    private byte[] getByteData(BufferedImage img) {
        WritableRaster raster = img.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    /**
     * Converts integer to byte form
     *
     * @param i Integer that is to be converted
     * @return  Byte[4] array that contains the converted integer in byte format
     */
    private byte[] bitConversion(int i) {
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24);
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
        byte byte0 = (byte) ((i & 0x000000FF));

        return new byte[]{byte3, byte2, byte1, byte0};
    }

    /**
     * Set method that saves the image
     *
     * @param img   Image file that is to be saved
     * @param f     File to save the image to
     * @param ext   Format of the image to be saved
     * @return      True if the save is successful
     */
    private boolean setImage(BufferedImage img, File f, String ext) {
        try {
            f.delete();
            ImageIO.write(img, ext, f);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "File could not be saved!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Encode an array of bytes into another array of bytes at specified offset
     *
     * @param img       Array of data that represents the image
     * @param addition  Data that is to be added into image
     * @param offset    Offset to be implemented
     * @return          Data array of merged image and addition
     */
    private byte[] encodeText(byte[] img, byte[] addition, int offset) {
        if (addition.length + offset > img.length) {
            throw new IllegalArgumentException("File is not long enough!");
        }

        for (int i = 0; i < addition.length; i++) {
            int add = addition[i];
            for (int bit = 7; bit >= 0; bit--, offset++) {
                int b = (add >>> bit) & 1;
                img[offset] = (byte) ((img[offset] & 0xFE) | b);
            }
        }

        return img;
    }

}
