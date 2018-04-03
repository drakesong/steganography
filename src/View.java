import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextArea input;
    private JButton encodeButton, decodeButton, fileButton;
    private JLabel image_input;

    public View() {
        JFrame frame = new JFrame("Steganography");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ViewPanel(), BorderLayout.CENTER);
        frame.setLocation(100,100);
        frame.pack();
        frame.setVisible(true);
    }

    public JTextArea getText() {
        return input;
    }

    public JLabel getImageInput() {
        return image_input;
    }

    public JButton getEncodeButton() {
        return encodeButton;
    }

    public JButton getDecodeButton() {
        return decodeButton;
    }

    public JButton getFileButton() {
        return fileButton;
    }

    public class ViewPanel extends JPanel {
        public ViewPanel() {
            super(new GridLayout(1, 1));

            JTabbedPane tabbedPane = new JTabbedPane();

            JComponent panel1 = makeEncodePanel();
            tabbedPane.addTab("Encode", panel1);

            JComponent panel2 = makeDecodePanel();
            tabbedPane.addTab("Decode", panel2);

            add(tabbedPane);
            tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        }

        private JComponent makeEncodePanel() {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints layoutConstraints = new GridBagConstraints();

            JPanel panel = new JPanel(layout);
            panel.setPreferredSize(new Dimension(500, 400));

            JLabel instruct = new JLabel("Insert the message you want to hide");
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, 5, 0, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(instruct, layoutConstraints);
            panel.add(instruct);

            input = new JTextArea();
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, 0, 0, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 50.0;
            JScrollPane scroll = new JScrollPane(input,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            layout.setConstraints(scroll, layoutConstraints);
            scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            panel.add(scroll);

            encodeButton = new JButton("Encode Now");
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, -5, -5, -5);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(encodeButton, layoutConstraints);
            panel.add(encodeButton);

            panel.setBackground(Color.lightGray);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            return panel;
        }

        private JComponent makeDecodePanel() {
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints layoutConstraints = new GridBagConstraints();

            JPanel panel = new JPanel(layout);
            panel.setPreferredSize(new Dimension(500, 400));

            image_input = new JLabel();
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(0, 0, 0, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 50.0;
            JScrollPane scroll = new JScrollPane(image_input,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            layout.setConstraints(scroll, layoutConstraints);
            scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            image_input.setHorizontalAlignment(JLabel.CENTER);
            panel.add(scroll);

            fileButton = new JButton("Choose PNG File");
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(5, 5, 5, 5);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(fileButton, layoutConstraints);
            panel.add(fileButton);

            decodeButton = new JButton("Decode Now");
            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridheight = 1;
            layoutConstraints.fill = GridBagConstraints.BOTH;
            layoutConstraints.insets = new Insets(5, 5, 5, 5);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.weightx = 1.0;
            layoutConstraints.weighty = 1.0;
            layout.setConstraints(decodeButton, layoutConstraints);
            panel.add(decodeButton);

            panel.setBackground(Color.lightGray);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

            return panel;
        }
    }
}
