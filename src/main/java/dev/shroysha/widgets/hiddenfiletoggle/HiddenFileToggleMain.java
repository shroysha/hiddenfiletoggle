package dev.shroysha.widgets.hiddenfiletoggle;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class HiddenFileToggleMain {

    private static ControllerFrame frame;
    private static boolean isFilesHidden;
    private static JButton switchPrefsButton;


    public static void main(String[] args) {
        isFilesHidden = isFilesHidden();
        frame = new ControllerFrame();
        frame.setVisible(true);
    }

    private static void executeShowFiles() throws IOException {
        Runtime.getRuntime().exec("defaults write com.apple.Finder AppleShowAllFiles TRUE");
        killallFinder();
    }

    private static void executeHideFiles() throws IOException {
        Runtime.getRuntime().exec("defaults write com.apple.Finder AppleShowAllFiles FALSE");
        killallFinder();
    }

    private static void killallFinder() throws IOException {
        Runtime.getRuntime().exec("killall Finder");
    }

    private static void showErrorDialog(Exception ex) {
        JOptionPane.showConfirmDialog(frame, ex);
    }

    private static boolean isFilesHidden() {
        final String TRUE = "TRUE";

        try {
            Process exec = Runtime.getRuntime().exec("defaults read com.apple.Finder AppleShowAllFiles");
            InputStream is = exec.getInputStream();
            Scanner scanner = new Scanner(is);
            String response = scanner.nextLine();
            return !response.equals(TRUE);
        } catch (Exception ex) {
            showErrorDialog(ex);
            System.exit(1);
            return false;
        }
    }

    private static void switchButtonText() {
        if (isFilesHidden) {
            switchPrefsButton.setText("Show All Files");
        } else {
            switchPrefsButton.setText("Hide All Files");
        }
    }

    private static class ControllerFrame extends JFrame {
        public ControllerFrame() {
            super("Controller");
            init();
        }

        private void init() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            switchPrefsButton = new JButton();
            switchButtonText();
            switchPrefsButton.addActionListener(ae -> {
                try {
                    if (isFilesHidden)
                        executeShowFiles();
                    else
                        executeHideFiles();
                    isFilesHidden = !isFilesHidden;
                    switchButtonText();
                } catch (Exception ex) {
                    showErrorDialog(ex);
                }
            });

            contentPanel.add(switchPrefsButton, BorderLayout.CENTER);

            this.add(contentPanel, BorderLayout.CENTER);
            this.pack();
        }
    }
}
