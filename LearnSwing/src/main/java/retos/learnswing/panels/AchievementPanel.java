/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.util.Set;
import javax.swing.*;
import retos.learnswing.auth.UserStats;

/**
 * Panel that displays user achievements and badges.
 *
 * @author jocde
 */
public class AchievementPanel extends JPanel {
    private UserStats userStats;

    /**
     * Constructor for AchievementPanel.
     *
     * @param userStats The user stats instance
     */
    public AchievementPanel(UserStats userStats) {
        this.userStats = userStats;
        initializeComponents();
    }

    /**
     * Initializes the achievement panel components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("🏆 Mis Logros", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Stats summary
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Estadísticas Generales"));

        statsPanel.add(createStatPanel("Nivel", String.valueOf(userStats.getLevel())));
        statsPanel.add(createStatPanel("XP Total", String.valueOf(userStats.getTotalXP())));
        statsPanel.add(createStatPanel("Logros", String.valueOf(userStats.getUnlockedAchievements().size())));
        statsPanel.add(createStatPanel("Racha Diaria", String.valueOf(userStats.getDailyChallengeStreak())));

        add(statsPanel, BorderLayout.CENTER);

        // Achievements grid
        JPanel achievementsPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        achievementsPanel.setBorder(BorderFactory.createTitledBorder("Logros Desbloqueados"));

        JScrollPane scrollPane = new JScrollPane(achievementsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        add(scrollPane, BorderLayout.SOUTH);

        // Add achievement badges
        Set<String> unlockedAchievements = userStats.getUnlockedAchievements();

        // All possible achievements
        String[] allAchievements = {
            UserStats.ACHIEVEMENT_FIRST_LESSON,
            UserStats.ACHIEVEMENT_QUIZ_MASTER,
            UserStats.ACHIEVEMENT_CODE_CHALLENGER,
            UserStats.ACHIEVEMENT_SWING_EXPERT,
            UserStats.ACHIEVEMENT_DAILY_CHALLENGER,
            "level_5"
        };

        for (String achievementId : allAchievements) {
            boolean isUnlocked = unlockedAchievements.contains(achievementId);
            achievementsPanel.add(createAchievementBadge(achievementId, isUnlocked));
        }
    }

    /**
     * Creates a stat display panel.
     *
     * @param label The stat label
     * @param value The stat value
     * @return The stat panel
     */
    private JPanel createStatPanel(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createRaisedBevelBorder());

        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(Color.BLUE);

        JLabel textLabel = new JLabel(label, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        panel.add(valueLabel, BorderLayout.CENTER);
        panel.add(textLabel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates an achievement badge.
     *
     * @param achievementId The achievement ID
     * @param isUnlocked Whether the achievement is unlocked
     * @return The badge panel
     */
    private JPanel createAchievementBadge(String achievementId, boolean isUnlocked) {
        JPanel badgePanel = new JPanel(new BorderLayout());
        badgePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(isUnlocked ? Color.GREEN : Color.GRAY, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Badge icon
        JLabel iconLabel = new JLabel(isUnlocked ? "🏆" : "🔒", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        badgePanel.add(iconLabel, BorderLayout.NORTH);

        // Achievement name
        String name = UserStats.getAchievementName(achievementId);
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        if (!isUnlocked) {
            nameLabel.setForeground(Color.GRAY);
        }
        badgePanel.add(nameLabel, BorderLayout.CENTER);

        // Achievement description
        String description = UserStats.getAchievementDescription(achievementId);
        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        if (!isUnlocked) {
            descLabel.setForeground(Color.GRAY);
        }
        badgePanel.add(descLabel, BorderLayout.SOUTH);

        // Tooltip with more info
        badgePanel.setToolTipText(isUnlocked ? "¡Desbloqueado! " + description : "Bloqueado: " + description);

        return badgePanel;
    }
}