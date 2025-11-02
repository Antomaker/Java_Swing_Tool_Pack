/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package retos.learnswing;

import javax.swing.*;
import retos.learnswing.UI.DashboardPanel;
import retos.learnswing.auth.*;
import retos.learnswing.panels.*;

/**
 *
 * @author jocde
 */
public class LearnSwing {

    private JFrame mainFrame;

    public static void main(String[] args) {
        new LearnSwing().startApplication();
    }

    /**
     * Starts the application by showing the login dialog.
     */
    public void startApplication() {
        // Show login dialog
        JFrame tempFrame = new JFrame();
        LoginDialog loginDialog = new LoginDialog(tempFrame);

        if (loginDialog.showDialog()) {
            // Login successful, show main application
            showMainApplication();
        } else {
            // Login failed or cancelled, exit
            System.exit(0);
        }
    }

    /**
     * Shows the main application window with dashboard.
     */
    private void showMainApplication() {
        mainFrame = new JFrame("Sistema de Aprendizaje Swing - " + UserManager.getCurrentUser());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 700);
        mainFrame.setLocationRelativeTo(null);

        // Create dashboard panel
        DashboardPanel dashboard = new DashboardPanel(mainFrame);
        mainFrame.add(dashboard);

        mainFrame.setVisible(true);
    }

    /**
     * Legacy method for backward compatibility - shows the original demo without
     * user system.
     */
    public static void showDemo() {
        // Create the main frame
        JFrame frame = new JFrame("Aprendiendo Swing - Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add all panels
        tabbedPane.addTab("Texto", new TextPanel());
        tabbedPane.addTab("Botones", new ButtonPanel());
        tabbedPane.addTab("Selección", new SelectionPanel());
        tabbedPane.addTab("Avanzados", new AdvancedPanel());
        tabbedPane.addTab("Otros", new OtherPanel());
        tabbedPane.addTab("Adicionales", new AdditionalComponentsPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }
}
