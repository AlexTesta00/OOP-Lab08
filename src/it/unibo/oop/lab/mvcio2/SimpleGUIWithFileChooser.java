package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My first Java GUI";
    private final JFrame frame = new JFrame(TITLE);
    private final Controller myfile = new Controller();
    private static final int ERROR_DIALOG = 0;

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser();
    }
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

    public SimpleGUIWithFileChooser() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        //Panel
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        canvas.setBorder(new TitledBorder("1 section"));
        frame.setContentPane(canvas);

        //ButtonArea (Browse) & UrlTextArea
        final JPanel containerButtonBrowse = new JPanel();
        final JButton browseButton = new JButton("Browse...");
        final JTextArea urlTextArea = new JTextArea();

        //Set Panel Options
        containerButtonBrowse.setLayout(new BorderLayout());
        containerButtonBrowse.setBorder(new TitledBorder("2 section"));
        urlTextArea.setText(myfile.getPathFile());
        urlTextArea.setEditable(false);
        containerButtonBrowse.add(browseButton, BorderLayout.LINE_END);
        containerButtonBrowse.add(urlTextArea, BorderLayout.CENTER);

        //TextArea
        final JTextArea input = new JTextArea();

        //ButtonArea (save)
        final JButton saveButton = new JButton("Save");

        /*Handler*/

        //SaveListener
        saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                myfile.write(input.getText());

                //Clean the TextArea
                input.setText("");
            }

        });

        //BrowseListener
        browseButton.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                //FileChooser
                final JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    myfile.setFile(fileChooser.getSelectedFile().getPath());
                    urlTextArea.setText(fileChooser.getSelectedFile().getPath());
                } else {
                    new JOptionPane();
                    //ErrorDialog
                    JOptionPane.showMessageDialog(new JButton(), "Select Valid File", "Error", ERROR_DIALOG);
                }
            }

        });


        //Implements All GUI
        canvas.add(containerButtonBrowse, BorderLayout.NORTH);
        canvas.add(input, BorderLayout.CENTER);
        canvas.add(saveButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
