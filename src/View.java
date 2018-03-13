import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

public class View extends JFrame {
    private static int WIDTH = 500;
    private static int HEIGHT = 400;

    private JTextArea input;
    private JScrollBar scroll1, scroll2;
    private JButton encodeButton, decodeButton;
    private JLabel image_input;

    private JMenu file;
    private JMenuItem encode;
    private JMenuItem decode;
    private JMenuItem exit;

    public View(String name) {
        super(name);

        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("File");
        encode = new JMenuItem("Encode");
        file.add(encode);
        decode = new JMenuItem("Decode");
        file.add(decode);
        file.addSeparator();
        exit = new JMenuItem("Exit");
        file.add(exit);

        menu.add(file);
        setJMenuBar(menu);

        setResizable(true);
        setBackground(Color.lightGray);
        setLocation(100,100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public JMenuItem getEncode() {
        return encode;
    }

    public JMenuItem getDecode() {
        return decode;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JTextArea getText() {
        return input;
    }

    public JLabel getImageInput() {
        return image_input;
    }

    public JPanel getTextPanel() {
        return new Text_Panel();
    }

    public JPanel getImagePanel() {
        return new Image_Panel();
    }

    public JButton getEncodeButton() {
        return encodeButton;
    }

    public JButton getDecodeButton() {
        return decodeButton;
    }

    private class Text_Panel extends JPanel {
        public Text_Panel() {

            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints layoutConstraints = new GridBagConstraints();
            setLayout(layout);

            input = new JTextArea();
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, 0, 0, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 50.0;

            JScrollPane scroll1 = new JScrollPane(input,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            layout.setConstraints(scroll1, layoutConstraints);
            scroll1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            add(scroll1);

            encodeButton = new JButton("Encode Now");
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, -5, -5, -5);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(encodeButton, layoutConstraints);
            add(encodeButton);

            setBackground(Color.lightGray);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    private class Image_Panel extends JPanel {

        public Image_Panel() {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints layoutConstraints = new GridBagConstraints();
            setLayout(layout);

            image_input = new JLabel();
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, 0, 0, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 50.0;

            JScrollPane scroll2 = new JScrollPane(image_input,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            layout.setConstraints(scroll2, layoutConstraints);
            scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            image_input.setHorizontalAlignment(JLabel.CENTER);
            add(scroll2);

            decodeButton = new JButton("Decode Now");
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, -5, -5, -5);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(decodeButton, layoutConstraints);
            add(decodeButton);

            setBackground(Color.lightGray);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    public static void main(String args[]) {
        new View("Steganography");
    }
}
