import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

public class Steganography {

    public Steganography() {
    }

    public boolean encode(String path, String original, String ext, String output, String msg) {
        String fileName = imagePath(path, original, ext);
        BufferedImage imgOrig = getImage(fileName);

        BufferedImage img = userSpace(imgOrig);
        img = addText(img, msg);

        return setImage(img, new File(imagePath(path, output, "png")), "png");
    }

    private String imagePath(String path, String name, String ext) {
        return path + "/" + name + "." + ext;
    }

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

    private BufferedImage userSpace(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = newImg.createGraphics();
        graphics.drawRenderedImage(img, null);
        graphics.dispose();
        return newImg;
    }

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

    private byte[] getByteData(BufferedImage img) {
        WritableRaster raster = img.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    private byte[] bitConversion(int i) {
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24);
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
        byte byte0 = (byte) ((i & 0x000000FF));

        return new byte[]{byte3, byte2, byte1, byte0};
    }

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
