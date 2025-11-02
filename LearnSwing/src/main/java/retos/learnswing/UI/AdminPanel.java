/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.UI;

import java.awt.*;

import java.util.List;
import java.util.Map;
import javax.swing.*;
import retos.learnswing.auth.UserManager;
import retos.learnswing.auth.UserStats;

/**
 *
 * @author jocde
 */
public class AdminPanel extends JPanel {

    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private JTable progressTable;
    private JButton deleteUserButton;
    private JButton refreshButton;
    private JButton editProgressButton;

    /**
     * Constructor for AdminPanel.
     */
    public AdminPanel() {
        initializeComponents();
    }

    /**
     * Initializes the admin panel components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Panel de Administración"));
        add(titlePanel, BorderLayout.NORTH);

        // Main content split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(200);

        // Left panel - User list
        JPanel leftPanel = createUserListPanel();
        splitPane.setLeftComponent(leftPanel);

        // Right panel - User details and actions
        JPanel rightPanel = createUserDetailsPanel();
        splitPane.setRightComponent(rightPanel);

        add(splitPane, BorderLayout.CENTER);

        // Load initial data
        loadUsers();
    }

    /**
     * Creates the user list panel.
     *
     * @return The user list panel
     */
    private JPanel createUserListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Usuarios"));

        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadUserProgress();
            }
        });

        JScrollPane scrollPane = new JScrollPane(userList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        refreshButton = new JButton("Actualizar");
        refreshButton.addActionListener(e -> loadUsers());
        buttonPanel.add(refreshButton);

        deleteUserButton = new JButton("Eliminar Usuario");
        deleteUserButton.addActionListener(e -> deleteSelectedUser());
        deleteUserButton.setEnabled(false);
        buttonPanel.add(deleteUserButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the user details panel.
     *
     * @return The user details panel
     */
    private JPanel createUserDetailsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Detalles del Usuario"));

        // Create tabbed pane for different admin functions
        JTabbedPane detailsTabbedPane = new JTabbedPane();

        // Progress tab
        JPanel progressPanel = createProgressPanel();
        detailsTabbedPane.addTab("Progreso", progressPanel);

        // Stats tab
        JPanel statsPanel = createStatsPanel();
        detailsTabbedPane.addTab("Estadísticas", statsPanel);

        // Achievements tab
        JPanel achievementsPanel = createAchievementsPanel();
        detailsTabbedPane.addTab("Logros", achievementsPanel);

        panel.add(detailsTabbedPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the progress panel.
     *
     * @return The progress panel
     */
    private JPanel createProgressPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Progress table
        String[] columnNames = {"Lección", "Estado"};
        Object[][] data = {}; // Empty initially
        progressTable = new JTable(data, columnNames);
        progressTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(progressTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Action buttons
        JPanel actionPanel = new JPanel(new FlowLayout());

        editProgressButton = new JButton("Editar Progreso");
        editProgressButton.addActionListener(e -> editUserProgress());
        editProgressButton.setEnabled(false);
        actionPanel.add(editProgressButton);

        JButton viewChangesButton = new JButton("Ver Cambios");
        viewChangesButton.addActionListener(e -> viewChanges());
        viewChangesButton.setEnabled(false);
        actionPanel.add(viewChangesButton);

        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the stats panel.
     *
     * @return The stats panel
     */
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel statsDisplayPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        statsDisplayPanel.setBorder(BorderFactory.createTitledBorder("Estadísticas Actuales"));

        // Labels for displaying stats
        statsDisplayPanel.add(new JLabel("XP Total:"));
        statsDisplayPanel.add(new JLabel("--"));

        statsDisplayPanel.add(new JLabel("Nivel:"));
        statsDisplayPanel.add(new JLabel("--"));

        statsDisplayPanel.add(new JLabel("XP en Nivel Actual:"));
        statsDisplayPanel.add(new JLabel("--"));

        statsDisplayPanel.add(new JLabel("XP para Siguiente Nivel:"));
        statsDisplayPanel.add(new JLabel("--"));

        panel.add(statsDisplayPanel, BorderLayout.CENTER);

        // Edit buttons
        JPanel editPanel = new JPanel(new FlowLayout());
        editPanel.setBorder(BorderFactory.createTitledBorder("Editar Estadísticas"));

        JButton editXPButton = new JButton("Editar XP");
        editXPButton.addActionListener(e -> editUserXP());
        editXPButton.setEnabled(false);
        editPanel.add(editXPButton);

        JButton editLevelButton = new JButton("Editar Nivel");
        editLevelButton.addActionListener(e -> editUserLevel());
        editLevelButton.setEnabled(false);
        editPanel.add(editLevelButton);

        panel.add(editPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Creates the achievements panel.
     *
     * @return The achievements panel
     */
    private JPanel createAchievementsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Available achievements list
        java.util.Set<String> achievements = UserManager.getAllAchievementIds();

        JPanel achievementsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        achievementsPanel.setBorder(BorderFactory.createTitledBorder("Logros Disponibles"));

        for (String achievement : achievements) {
            JCheckBox checkBox = new JCheckBox(UserStats.getAchievementName(achievement));
            checkBox.setName(achievement);
            checkBox.addActionListener(e -> {
                String selectedUser = userList.getSelectedValue();
                if (selectedUser != null) {
                    JCheckBox cb = (JCheckBox) e.getSource();
                    UserManager.updateUserAchievement(selectedUser, cb.getName(), cb.isSelected());
                }
            });
            achievementsPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(achievementsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add/Remove buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addAchievementButton = new JButton("Agregar Logro");
        addAchievementButton.addActionListener(e -> addAchievement());
        buttonPanel.add(addAchievementButton);

        JButton removeAchievementButton = new JButton("Eliminar Logro");
        removeAchievementButton.addActionListener(e -> removeAchievement());
        buttonPanel.add(removeAchievementButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Loads the list of all users.
     */
    private void loadUsers() {
        userListModel.clear();
        List<String> users = UserManager.getAllUsers();
        for (String user : users) {
            userListModel.addElement(user);
        }

        // Enable/disable buttons based on selection
        updateButtonStates();
    }

    /**
     * Loads the progress for the selected user.
     */
    private void loadUserProgress() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            progressTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Lección", "Estado"}
            ));
            return;
        }

        Map<String, String> progress = UserManager.getUserLessonProgress(selectedUser);

        // Convert to table data
        Object[][] data = new Object[progress.size()][2];
        int i = 0;
        for (Map.Entry<String, String> entry : progress.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = getProgressDisplayText(entry.getValue());
            i++;
        }

        progressTable.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[]{"Lección", "Estado"}
        ));

        updateButtonStates();
        loadUserStats();
        loadUserAchievements();
    }

    /**
     * Gets the display text for progress status.
     *
     * @param status The progress status
     * @return The display text
     */
    private String getProgressDisplayText(String status) {
        switch (status) {
            case "interested":
                return "Interesado";
            case "completed":
                return "Completado";
            default:
                return "No visto";
        }
    }

    /**
     * Updates the enabled state of buttons based on current selection.
     */
    private void updateButtonStates() {
        boolean hasSelection = userList.getSelectedValue() != null;
        boolean isNotAdmin = hasSelection && !userList.getSelectedValue().equals("maker");

        deleteUserButton.setEnabled(isNotAdmin);
        editProgressButton.setEnabled(hasSelection);
    }

    /**
     * Deletes the selected user.
     */
    private void deleteSelectedUser() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        int result = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar al usuario '" + selectedUser + "'?\n" +
                "Esta acción no se puede deshacer.",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            if (UserManager.deleteUser(selectedUser)) {
                JOptionPane.showMessageDialog(this,
                        "Usuario eliminado exitosamente.",
                        "Eliminación Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al eliminar el usuario.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a dialog to edit user progress.
     */
    private void editUserProgress() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        // Get current progress
        Map<String, String> currentProgress = UserManager.getUserLessonProgress(selectedUser);

        // Create dialog
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Editar Progreso - " + selectedUser, true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);

        // Progress editing panel
        JPanel editPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] lessons = {"Texto", "Botones", "Selección", "Avanzados", "Otros", "Adicionales", "Gráficos"};
        @SuppressWarnings("unchecked")
        JComboBox<String>[] statusBoxes = new JComboBox[lessons.length];

        for (int i = 0; i < lessons.length; i++) {
            editPanel.add(new JLabel(lessons[i] + ":"));

            JComboBox<String> statusBox = new JComboBox<>(new String[]{"No visto", "Interesado", "Completado"});
            String currentStatus = currentProgress.getOrDefault(lessons[i], "none");
            switch (currentStatus) {
                case "interested":
                    statusBox.setSelectedIndex(1);
                    break;
                case "completed":
                    statusBox.setSelectedIndex(2);
                    break;
                default:
                    statusBox.setSelectedIndex(0);
                    break;
            }
            statusBoxes[i] = statusBox;
            editPanel.add(statusBox);
        }

        JScrollPane scrollPane = new JScrollPane(editPanel);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            // Save changes
            for (int i = 0; i < lessons.length; i++) {
                String status = "none";
                switch (statusBoxes[i].getSelectedIndex()) {
                    case 1:
                        status = "interested";
                        break;
                    case 2:
                        status = "completed";
                        break;
                }
                UserManager.updateUserLessonProgress(selectedUser, lessons[i], status);
            }

            JOptionPane.showMessageDialog(dialog,
                    "Progreso actualizado exitosamente.",
                    "Actualización Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);

            dialog.dispose();
            loadUserProgress(); // Refresh the table
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    /**
     * Shows a dialog with current changes or state.
     */
    private void viewChanges() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        Map<String, String> progress = UserManager.getUserLessonProgress(selectedUser);

        StringBuilder message = new StringBuilder();
        message.append("Estado actual del usuario '").append(selectedUser).append("':\n\n");

        for (Map.Entry<String, String> entry : progress.entrySet()) {
            message.append(entry.getKey()).append(": ").append(getProgressDisplayText(entry.getValue())).append("\n");
        }

        JOptionPane.showMessageDialog(this,
                message.toString(),
                "Estado del Usuario",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Opens a dialog to edit user XP.
     */
    private void editUserXP() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        UserStats userStats = UserManager.getUserStats(selectedUser);
        if (userStats == null) {
            return;
        }

        String newXPString = JOptionPane.showInputDialog(this,
                "XP actual: " + userStats.getTotalXP() + "\n\nIngrese el nuevo valor de XP total:",
                "Editar XP - " + selectedUser,
                JOptionPane.QUESTION_MESSAGE);

        if (newXPString != null && !newXPString.trim().isEmpty()) {
            try {
                int newXP = Integer.parseInt(newXPString.trim());
                if (newXP >= 0) {
                    if (UserManager.updateUserXP(selectedUser, newXP)) {
                        JOptionPane.showMessageDialog(this,
                                "XP actualizado exitosamente.",
                                "Actualización Exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadUserStats(); // Refresh stats display
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Error al actualizar XP.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El XP debe ser un número positivo.",
                            "Valor Inválido",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un número válido.",
                        "Valor Inválido",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Opens a dialog to edit user level.
     */
    private void editUserLevel() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        UserStats userStats = UserManager.getUserStats(selectedUser);
        if (userStats == null) {
            return;
        }

        String newLevelString = JOptionPane.showInputDialog(this,
                "Nivel actual: " + userStats.getLevel() + "\n\nIngrese el nuevo nivel:",
                "Editar Nivel - " + selectedUser,
                JOptionPane.QUESTION_MESSAGE);

        if (newLevelString != null && !newLevelString.trim().isEmpty()) {
            try {
                int newLevel = Integer.parseInt(newLevelString.trim());
                if (newLevel > 0) {
                    if (UserManager.updateUserLevel(selectedUser, newLevel)) {
                        JOptionPane.showMessageDialog(this,
                                "Nivel actualizado exitosamente.",
                                "Actualización Exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        loadUserStats(); // Refresh stats display
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Error al actualizar nivel.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "El nivel debe ser mayor que 0.",
                            "Valor Inválido",
                            JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Por favor ingrese un número válido.",
                        "Valor Inválido",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Loads and displays user stats when a user is selected.
     */
    private void loadUserStats() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        UserStats userStats = UserManager.getUserStats(selectedUser);
        if (userStats == null) {
            return;
        }

        // Update stats display (this would need to be implemented in the UI)
        // For now, we'll just refresh the progress table
        loadUserProgress();
    }

    /**
     * Loads and displays user achievements when a user is selected.
     */
    private void loadUserAchievements() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser == null) {
            return;
        }

        UserStats userStats = UserManager.getUserStats(selectedUser);
        if (userStats == null) {
            return;
        }

        // Update achievement checkboxes based on user's unlocked achievements
        java.util.Set<String> unlockedAchievements = userStats.getUnlockedAchievements();

        // Find the achievements panel and update checkboxes
        for (java.awt.Component component : getComponents()) {
            if (component instanceof javax.swing.JTabbedPane) {
                javax.swing.JTabbedPane tabbedPane = (javax.swing.JTabbedPane) component;
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if ("Logros".equals(tabbedPane.getTitleAt(i))) {
                        javax.swing.JPanel achievementsPanel = (javax.swing.JPanel) tabbedPane.getComponentAt(i);
                        updateAchievementCheckboxes(achievementsPanel, unlockedAchievements);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * Updates achievement checkboxes based on unlocked achievements.
     */
    private void updateAchievementCheckboxes(javax.swing.JPanel panel, java.util.Set<String> unlockedAchievements) {
        for (java.awt.Component component : panel.getComponents()) {
            if (component instanceof javax.swing.JScrollPane) {
                javax.swing.JScrollPane scrollPane = (javax.swing.JScrollPane) component;
                javax.swing.JPanel innerPanel = (javax.swing.JPanel) scrollPane.getViewport().getView();
                for (java.awt.Component innerComponent : innerPanel.getComponents()) {
                    if (innerComponent instanceof javax.swing.JCheckBox) {
                        javax.swing.JCheckBox checkBox = (javax.swing.JCheckBox) innerComponent;
                        String achievementId = checkBox.getName();
                        checkBox.setSelected(unlockedAchievements.contains(achievementId));
                    }
                }
                break;
            }
        }
    }

    /**
     * Opens a dialog to add a new achievement.
     */
    private void addAchievement() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Agregar Logro", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID del Logro:"));
        JTextField idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nombre:"));
        JTextField nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Descripción:"));
        JTextField descField = new JTextField();
        inputPanel.add(descField);

        dialog.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String desc = descField.getText().trim();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "ID y Nombre son obligatorios.",
                        "Campos Requeridos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (UserManager.addAchievement(id, name, desc)) {
                JOptionPane.showMessageDialog(dialog,
                        "Logro agregado exitosamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                refreshAchievementsPanel();
            } else {
                JOptionPane.showMessageDialog(dialog,
                        "Error al agregar el logro. El ID ya existe.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    /**
     * Opens a dialog to remove an achievement.
     */
    private void removeAchievement() {
        java.util.Set<String> achievements = UserManager.getAllAchievementIds();
        if (achievements.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay logros para eliminar.",
                    "Sin Logros",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] achievementArray = achievements.toArray(new String[0]);
        String selectedAchievement = (String) JOptionPane.showInputDialog(this,
                "Selecciona el logro a eliminar:",
                "Eliminar Logro",
                JOptionPane.QUESTION_MESSAGE,
                null,
                achievementArray,
                achievementArray[0]);

        if (selectedAchievement != null) {
            int result = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar el logro '" + UserManager.getAchievementName(selectedAchievement) + "'?\n" +
                    "Esta acción no se puede deshacer.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                if (UserManager.removeAchievement(selectedAchievement)) {
                    JOptionPane.showMessageDialog(this,
                            "Logro eliminado exitosamente.",
                            "Eliminación Exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshAchievementsPanel();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al eliminar el logro.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Refreshes the achievements panel to show updated achievements.
     */
    private void refreshAchievementsPanel() {
        // Find the achievements panel and recreate it
        for (java.awt.Component component : getComponents()) {
            if (component instanceof javax.swing.JTabbedPane) {
                javax.swing.JTabbedPane tabbedPane = (javax.swing.JTabbedPane) component;
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if ("Logros".equals(tabbedPane.getTitleAt(i))) {
                        tabbedPane.setComponentAt(i, createAchievementsPanel());
                        break;
                    }
                }
                break;
            }
        }
    }
}