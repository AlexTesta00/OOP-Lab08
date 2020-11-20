package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller = new UseController();

    public static void main(final String[] args) {
        new SimpleGUI();
    }
    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        //TopContainer
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        //Set Component
        final JTextField input = new JTextField();
        final JTextArea output = new JTextArea();
        final JButton print = new JButton("Print...");
        final JButton showHistory = new JButton("Show History...");
        final JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        buttonContainer.add(print, BorderLayout.LINE_START);
        buttonContainer.add(showHistory, BorderLayout.LINE_END);

        /*Listener*/
        //PrintListener
        print.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
                  controller.setNextString(input.getText());
                  output.setText(input.getText());
            }

        });

        //HistoryListener
        showHistory.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent e) {
               output.setText(controller.getHistory().toString());
            }

        });


        //Create GUI
        frame.setContentPane(canvas);
        canvas.add(input, BorderLayout.NORTH);
        canvas.add(output, BorderLayout.CENTER);
        canvas.add(buttonContainer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}
