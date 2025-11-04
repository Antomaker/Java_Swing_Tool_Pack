/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import retos.learnswing.LearnSwing;
import retos.learnswing.auth.UserManager;
import retos.learnswing.auth.UserStats;
import retos.learnswing.panels.AchievementPanel;
import retos.learnswing.panels.AdditionalComponentsPanel;
import retos.learnswing.panels.AdvancedPanel;
import retos.learnswing.panels.ButtonPanel;
import retos.learnswing.panels.ChallengePanel;
import retos.learnswing.panels.ChartsPanel;
import retos.learnswing.panels.CodeViewPanel;
import retos.learnswing.panels.ComponentGalleryPanel;
import retos.learnswing.panels.OtherPanel;
import retos.learnswing.panels.QuizPanel;
import retos.learnswing.panels.SampleProjectsPanel;
import retos.learnswing.panels.SelectionPanel;
import retos.learnswing.panels.TextPanel;


/**
 *
 * @author jocde
 */
public class DashboardPanel extends JPanel {
    private JFrame parentFrame;

    /**
     * Constructor for DashboardPanel.
     * 
     * @param parentFrame The parent frame
     */
    public DashboardPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
    }

    /**
     * Initializes the dashboard components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Header panel with user info and logout
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Main content with lessons, achievements, and admin tab (if admin)
        JTabbedPane mainTabbedPane = new JTabbedPane();

        JPanel lessonsPanel = createLessonsPanel();
        mainTabbedPane.addTab("Lecciones", lessonsPanel);

        AchievementPanel achievementPanel = new AchievementPanel(UserManager.getCurrentUserStats());
        mainTabbedPane.addTab("Logros", achievementPanel);

        // Add admin panel if user is admin
        if (UserManager.isAdmin()) {
            AdminPanel adminPanel = new AdminPanel();
            mainTabbedPane.addTab("Administración", adminPanel);
        }

        add(mainTabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates the header panel with user information, stats, and logout button.
     *
     * @return The header panel
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left side - User info and stats
        JPanel leftPanel = new JPanel(new GridLayout(2, 1));

        // User info label
        JLabel userLabel = new JLabel("Bienvenido, " + UserManager.getCurrentUser() + "!");
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        leftPanel.add(userLabel);

        // Stats label
        UserStats stats = UserManager.getCurrentUserStats();
        String statsText = String.format("Nivel %d • %d XP • %d%% al siguiente nivel",
                stats.getLevel(), stats.getTotalXP(), stats.getLevelProgressPercentage());
        JLabel statsLabel = new JLabel(statsText);
        statsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statsLabel.setForeground(Color.BLUE);
        leftPanel.add(statsLabel);

        headerPanel.add(leftPanel, BorderLayout.WEST);

        // Center - Achievement badges
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel.add(new JLabel("Logros: "));

        for (String achievement : stats.getUnlockedAchievements()) {
            JButton badgeButton = new JButton("🏆");
            badgeButton.setToolTipText(UserStats.getAchievementName(achievement));
            badgeButton.setPreferredSize(new Dimension(30, 30));
            badgeButton.setMargin(new Insets(0, 0, 0, 0));
            centerPanel.add(badgeButton);
        }

        headerPanel.add(centerPanel, BorderLayout.CENTER);

        // Right side - Logout button
        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(parentFrame,
                        "¿Está seguro que desea cerrar sesión?",
                        "Confirmar Cierre de Sesión",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    UserManager.logout();
                    parentFrame.dispose();
                    LearnSwing mainApp = new LearnSwing();
                    mainApp.startApplication();
                }
            }
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the lessons panel with lesson cards.
     *
     * @return The lessons panel
     */
    private JPanel createLessonsPanel() {
        return createContentPanel();
    }

    /**
     * Creates the main content panel with lesson cards.
     *
     * @return The content panel
     */
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Get user progress
        Map<String, String> progress = UserManager.getAllLessonProgress();

        // Lesson data
        String[] lessonNames = { "Texto", "Botones", "Selección", "Avanzados", "Otros", "Adicionales", "Gráficos", "Galería", "Proyectos" };
        String[] descriptions = {
                "Campos de texto, áreas de texto y campos de contraseña",
                "Botones, checkboxes y radio buttons",
                "Comboboxes y listas para selección",
                "Tablas, sliders y barras de progreso",
                "Spinners, campos formateados y toggles",
                "Árboles, selectores de archivos y colores",
                "Gráficos 2D, barras, líneas y visualización de datos",
                "Constructor visual de interfaces con drag & drop",
                "Proyectos completos: gestor de usuarios, biblioteca, inventario y farmacia"
        };

        for (int i = 0; i < lessonNames.length; i++) {
            JPanel lessonCard = createLessonCard(lessonNames[i], descriptions[i],
                    progress.getOrDefault(lessonNames[i], "none"));
            contentPanel.add(lessonCard);
        }

        return contentPanel;
    }

    /**
     * Creates a lesson card with progress indicator and action buttons.
     * 
     * @param lessonName  The name of the lesson
     * @param description The description of the lesson
     * @param progress    The current progress status
     * @return The lesson card panel
     */
    private JPanel createLessonCard(String lessonName, String description, String progress) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        // Title and progress
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(lessonName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel progressLabel = new JLabel(getProgressText(progress));
        progressLabel.setForeground(getProgressColor(progress));
        progressPanel.add(progressLabel);

        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(progressPanel, BorderLayout.EAST);

        card.add(titlePanel, BorderLayout.NORTH);

        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(descArea, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton viewButton = new JButton("Ver Lección");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLesson(lessonName);
            }
        });
        buttonPanel.add(viewButton);

        JButton quizButton = new JButton("Hacer Quiz");
        quizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showQuiz(lessonName);
            }
        });
        buttonPanel.add(quizButton);

        JButton challengeButton = new JButton("Desafío");
        challengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showChallenge(lessonName);
            }
        });
        buttonPanel.add(challengeButton);

        JButton markCompletedButton = new JButton("Completado");
        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager.updateLessonProgress(lessonName, "completed");
                UserManager.getCurrentUserStats().recordLessonScore(lessonName, 100);
                refreshDashboard();
            }
        });
        buttonPanel.add(markCompletedButton);

        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    /**
     * Gets the display text for progress status.
     * 
     * @param progress The progress status
     * @return The display text
     */
    private String getProgressText(String progress) {
        switch (progress) {
            case "interested":
                return "Interesado";
            case "completed":
                return "Completado";
            default:
                return "No visto";
        }
    }

    /**
     * Gets the color for progress status.
     * 
     * @param progress The progress status
     * @return The color
     */
    private Color getProgressColor(String progress) {
        switch (progress) {
            case "interested":
                return Color.BLUE;
            case "completed":
                return Color.GREEN;
            default:
                return Color.GRAY;
        }
    }

    /**
     * Shows the lesson in a new window.
     *
     * @param lessonName The name of the lesson to show
     */
    private void showLesson(String lessonName) {
        JFrame lessonFrame = new JFrame("Lección: " + lessonName);
        lessonFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lessonFrame.setSize(900, 700);
        lessonFrame.setLocationRelativeTo(parentFrame);

        // Create tabbed pane for the lesson
        JTabbedPane lessonTabbedPane = new JTabbedPane();

        // Add the appropriate panel based on lesson name
        JPanel lessonPanel = createLessonPanel(lessonName);
        lessonTabbedPane.addTab("Componentes", lessonPanel);

        // Add code view panel
        CodeViewPanel codePanel = new CodeViewPanel(lessonName);
        lessonTabbedPane.addTab("Código", codePanel);

        lessonFrame.add(lessonTabbedPane);
        lessonFrame.setVisible(true);
    }

    /**
     * Shows the quiz for a lesson in a new window.
     *
     * @param lessonName The name of the lesson to quiz on
     */
    private void showQuiz(String lessonName) {
        JFrame quizFrame = new JFrame("Quiz: " + lessonName);
        quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        quizFrame.setSize(800, 600);
        quizFrame.setLocationRelativeTo(parentFrame);

        QuizPanel quizPanel = new QuizPanel(lessonName, UserManager.getCurrentUserStats());
        quizFrame.add(quizPanel);
        quizFrame.setVisible(true);
    }

    /**
     * Shows the challenge for a lesson in a new window.
     *
     * @param lessonName The name of the lesson to challenge on
     */
    private void showChallenge(String lessonName) {
        JFrame challengeFrame = new JFrame("Desafío: " + lessonName);
        challengeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        challengeFrame.setSize(900, 700);
        challengeFrame.setLocationRelativeTo(parentFrame);

        ChallengePanel challengePanel = new ChallengePanel(lessonName, UserManager.getCurrentUserStats());
        challengeFrame.add(challengePanel);
        challengeFrame.setVisible(true);
    }

    /**
     * Creates the appropriate lesson panel based on lesson name.
      *
      * @param lessonName The name of the lesson
      * @return The lesson panel
      */
     private JPanel createLessonPanel(String lessonName) {
         switch (lessonName) {
             case "Texto":
                 return new TextPanel();
             case "Botones":
                 return new ButtonPanel();
             case "Selección":
                 return new SelectionPanel();
             case "Avanzados":
                 return new AdvancedPanel();
             case "Otros":
                 return new OtherPanel();
             case "Adicionales":
                 return new AdditionalComponentsPanel();
             case "Gráficos":
                 return new ChartsPanel();
             case "Galería":
                 return new ComponentGalleryPanel();
             case "Proyectos":
                 return new SampleProjectsPanel();
             default:
                 return new JPanel();
         }
     }

    /**
     * Refreshes the dashboard to show updated progress.
     */
    private void refreshDashboard() {
        // Update header stats
        JPanel headerPanel = createHeaderPanel();
        if (getComponentCount() > 0) {
            remove(0);
            add(headerPanel, BorderLayout.NORTH, 0);
        }

        // Update achievement panel if it's visible
        JTabbedPane mainTabbedPane = (JTabbedPane) getComponent(1);
        AchievementPanel achievementPanel = new AchievementPanel(UserManager.getCurrentUserStats());
        mainTabbedPane.setComponentAt(1, achievementPanel);

        revalidate();
        repaint();
    }
}
