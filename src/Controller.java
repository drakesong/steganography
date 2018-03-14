import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.imageio.ImageIO;

public class Controller {
    private View view;
    private Steganography model;

    private JTextArea input;
    private JButton encodeButton, decodeButton, fileButton;
    private JLabel image_input;

    private EncodeButton encButton;
    private DecodeButton decButton;
    private FileButton filButton;

    private String stat_path = "";
    private String stat_name = "";

    public Controller(View v, Steganography m) {
        view = v;
        model = m;

        input = view.getText();
        image_input = view.getImageInput();

        encodeButton = view.getEncodeButton();
        decodeButton = view.getDecodeButton();
        fileButton = view.getFileButton();

        encButton = new EncodeButton();
        encodeButton.addActionListener(encButton);
        decButton = new DecodeButton();
        decodeButton.addActionListener(decButton);
        filButton = new FileButton();
        fileButton.addActionListener(filButton);
    }

    private class EncodeButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("./");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(new FileChecker());
            int returnVal = chooser.showOpenDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                try {
                    String text = input.getText();
                    String ext = FileChecker.getExtension(directory);
                    String name = directory.getName();
                    String path = directory.getPath();
                    path = path.substring(0, path.length() - name.length() - 1);
                    name = name.substring(0, name.length() - 4);

                    String output = JOptionPane.showInputDialog(view,
                            "Enter output file name:", "File name",
                            JOptionPane.PLAIN_MESSAGE);

                    if (model.encode(path, name, ext, output, text)) {
                        JOptionPane.showMessageDialog(view,
                                "The Image was encoded successfully!",
                                "Success!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(view,
                                "The Image could not be encoded!",
                                "Error!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    encodePopUp(new ImageIcon(ImageIO.read(new File(path + "/" + output + ".png"))));
                } catch (Exception except) {
                    JOptionPane.showMessageDialog(view,
                            "The File cannot be opened!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private class FileButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser("./");
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(new FileChecker());
            int returnVal = chooser.showOpenDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                try {
                    String image = directory.getPath();
                    stat_name = directory.getName();
                    stat_path = directory.getPath();
                    stat_path = stat_path.substring(0, stat_path.length() - stat_name.length() - 1);
                    stat_name = stat_name.substring(0, stat_name.length() - 4);
                    image_input.setIcon(new ImageIcon(ImageIO.read(new File(image))));
                } catch (Exception except) {
                    JOptionPane.showMessageDialog(view,
                            "The File cannot be opened!",
                            "Error!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private class DecodeButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = model.decode(stat_path, stat_name);
            System.out.println(stat_path + ", " + stat_name);
            if (!message.equals("")) {
                JOptionPane.showMessageDialog(view,
                        "The Image was decoded successfully!",
                        "Success!", JOptionPane.INFORMATION_MESSAGE);
                decodePopUp(message);
            } else {
                JOptionPane.showMessageDialog(view,
                        "The Image could not be decoded!",
                        "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void update() {
        input.setText("");
        image_input.setIcon(null);
        stat_path = "";
        stat_name = "";
    }

    private void encodePopUp(ImageIcon img) {
        JFrame frame = new JFrame("Encoded Image");
        JLabel image = new JLabel(img);
        image.setPreferredSize(new Dimension(500, 400));
        frame.setContentPane(image);
        frame.setLocation(120, 120);
        frame.pack();
        frame.setVisible(true);
    }

    private void decodePopUp(String msg) {
        JFrame frame = new JFrame("Decoded Message");
        JTextArea text = new JTextArea(msg);
        text.setEditable(false);
        text.setPreferredSize(new Dimension(500, 400));
        frame.setContentPane(text);
        frame.setLocation(120,120);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new Controller(
                new View(), new Steganography());
    }
}
