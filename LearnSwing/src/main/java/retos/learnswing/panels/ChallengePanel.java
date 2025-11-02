/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import retos.learnswing.auth.UserStats;

/**
 * Interactive coding challenge panel for hands-on Swing component exercises.
 *
 * @author jocde
 */
public class ChallengePanel extends JPanel {
    private String lessonName;
    private UserStats userStats;
    private Challenge currentChallenge;
    private int attempts;

    private JLabel challengeLabel;
    private JTextArea codeArea;
    private JButton testButton;
    private JButton hintButton;
    private JButton solutionButton;
    private JButton completeButton;
    private JLabel attemptsLabel;
    private JTextArea feedbackArea;

    /**
     * Constructor for ChallengePanel.
     *
     * @param lessonName The name of the lesson to challenge on
     * @param userStats The user stats instance
     */
    public ChallengePanel(String lessonName, UserStats userStats) {
        this.lessonName = lessonName;
        this.userStats = userStats;
        this.attempts = 0;

        initializeComponents();
        loadRandomChallenge();
    }

    /**
     * Initializes the challenge components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        challengeLabel = new JLabel("Desafío de Código");
        challengeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(challengeLabel, BorderLayout.WEST);

        attemptsLabel = new JLabel("Intentos: 0");
        attemptsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(attemptsLabel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Challenge description
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        JLabel descLabel = new JLabel("Descripción del desafío:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descriptionPanel.add(descLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionArea.setBackground(getBackground());
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(600, 60));
        descriptionPanel.add(descScroll, BorderLayout.CENTER);

        contentPanel.add(descriptionPanel, BorderLayout.NORTH);

        // Code area
        JPanel codePanel = new JPanel(new BorderLayout());
        JLabel codeLabel = new JLabel("Tu código:");
        codeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        codePanel.add(codeLabel, BorderLayout.NORTH);

        codeArea = new JTextArea();
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setTabSize(4);
        JScrollPane codeScroll = new JScrollPane(codeArea);
        codeScroll.setPreferredSize(new Dimension(600, 200));
        codePanel.add(codeScroll, BorderLayout.CENTER);

        contentPanel.add(codePanel, BorderLayout.CENTER);

        // Feedback area
        JPanel feedbackPanel = new JPanel(new BorderLayout());
        JLabel feedbackLabel = new JLabel("Retroalimentación:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 14));
        feedbackPanel.add(feedbackLabel, BorderLayout.NORTH);

        feedbackArea = new JTextArea();
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Arial", Font.PLAIN, 12));
        feedbackArea.setBackground(getBackground());
        JScrollPane feedbackScroll = new JScrollPane(feedbackArea);
        feedbackScroll.setPreferredSize(new Dimension(600, 80));
        feedbackPanel.add(feedbackScroll, BorderLayout.CENTER);

        contentPanel.add(feedbackPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        testButton = new JButton("Probar Código");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testCode();
            }
        });
        buttonPanel.add(testButton);

        hintButton = new JButton("Pista");
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHint();
            }
        });
        buttonPanel.add(hintButton);

        solutionButton = new JButton("Mostrar Solución");
        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSolution();
            }
        });
        buttonPanel.add(solutionButton);

        completeButton = new JButton("Marcar Completado");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeChallenge();
            }
        });
        completeButton.setVisible(false);
        buttonPanel.add(completeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads a random challenge for the current lesson.
     */
    private void loadRandomChallenge() {
        List<Challenge> challenges = getChallengesForLesson(lessonName);
        if (!challenges.isEmpty()) {
            Random random = new Random();
            currentChallenge = challenges.get(random.nextInt(challenges.size()));

            // Update UI with challenge data
            challengeLabel.setText("Desafío: " + currentChallenge.getTitle());
            ((JTextArea) ((JScrollPane) ((JPanel) getComponent(1)).getComponent(0)).getViewport().getView()).setText(currentChallenge.getDescription());
            codeArea.setText(currentChallenge.getStarterCode());
            feedbackArea.setText("");
            attempts = 0;
            updateAttemptsLabel();
        }
    }

    /**
     * Tests the user's code.
     */
    private void testCode() {
        attempts++;
        updateAttemptsLabel();

        String userCode = codeArea.getText().trim();

        if (userCode.isEmpty()) {
            feedbackArea.setText("Error: El código no puede estar vacío.");
            return;
        }

        // Basic validation
        String validationResult = validateCode(userCode);
        if (!validationResult.isEmpty()) {
            feedbackArea.setText("Errores encontrados:\n" + validationResult);
            return;
        }

        // Check if code matches expected solution
        if (currentChallenge.checkSolution(userCode)) {
            feedbackArea.setText("¡Excelente! Tu código es correcto.\n\n" +
                    "Has completado el desafío exitosamente.\n" +
                    "Haz clic en 'Marcar Completado' para guardar tu progreso.");
            testButton.setEnabled(false);
            completeButton.setVisible(true);
        } else {
            feedbackArea.setText("Tu código no cumple con los requisitos del desafío.\n\n" +
                    "Revisa la descripción y intenta de nuevo.\n" +
                    "Usa la pista si necesitas ayuda.");
        }
    }

    /**
     * Shows a hint for the current challenge.
     */
    private void showHint() {
        if (currentChallenge != null && !currentChallenge.getHints().isEmpty()) {
            String hint = currentChallenge.getHints().get(0); // Show first hint
            JOptionPane.showMessageDialog(this, "Pista: " + hint,
                    "Pista del Desafío", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Shows the solution for the current challenge.
     */
    private void showSolution() {
        if (currentChallenge != null) {
            int result = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro que quieres ver la solución?\n" +
                    "Esto reducirá los puntos que puedes ganar.",
                    "Mostrar Solución", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                codeArea.setText(currentChallenge.getSolution());
                feedbackArea.setText("Solución mostrada. Puedes modificar el código y probarlo.");
                testButton.setEnabled(true);
            }
        }
    }

    /**
     * Completes the challenge and awards points.
     */
    private void completeChallenge() {
        if (currentChallenge != null) {
            userStats.recordChallengeCompletion(lessonName);

            int bonusPoints = Math.max(0, 50 - (attempts - 1) * 5); // Bonus for fewer attempts
            userStats.addXP(bonusPoints);

            JOptionPane.showMessageDialog(this,
                    "¡Desafío completado!\n\n" +
                    "XP ganado: " + bonusPoints + "\n" +
                    "Intentos: " + attempts + "\n\n" +
                    "¡Sigue practicando!",
                    "¡Felicitaciones!", JOptionPane.INFORMATION_MESSAGE);

            // Close the challenge window
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        }
    }

    /**
     * Updates the attempts label.
     */
    private void updateAttemptsLabel() {
        attemptsLabel.setText("Intentos: " + attempts);
    }

    /**
     * Validates the user's code for basic syntax and structure.
     *
     * @param code The code to validate
     * @return Error message or empty string if valid
     */
    private String validateCode(String code) {
        StringBuilder errors = new StringBuilder();

        // Check for basic structure
        if (!code.contains("JPanel")) {
            errors.append("- El código debe contener al menos un JPanel\n");
        }

        if (!code.contains("setLayout")) {
            errors.append("- El código debe configurar un layout\n");
        }

        // Check for balanced braces
        int openBraces = code.length() - code.replace("{", "").length();
        int closeBraces = code.length() - code.replace("}", "").length();
        if (openBraces != closeBraces) {
            errors.append("- Las llaves no están balanceadas\n");
        }

        return errors.toString();
    }

    /**
     * Gets challenges for a specific lesson.
     *
     * @param lessonName The name of the lesson
     * @return List of challenges
     */
    private java.util.List<Challenge> getChallengesForLesson(String lessonName) {
        java.util.List<Challenge> challenges = new ArrayList<>();

        switch (lessonName) {
            case "Texto":
                challenges.add(new Challenge(
                    "Campo de Texto Interactivo",
                    "Crea un JPanel que contenga:\n" +
                    "- Un JTextField para nombre\n" +
                    "- Un JTextArea para descripción\n" +
                    "- Un JButton que muestre el texto introducido\n" +
                    "- Usa GridLayout con 3 filas y 2 columnas",
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "import java.awt.event.*;\n" +
                    "\n" +
                    "public class TextChallenge extends JPanel {\n" +
                    "    public TextChallenge() {\n" +
                    "        // Tu código aquí\n" +
                    "    }\n" +
                    "}",
                    Arrays.asList(
                        "Recuerda usar setLayout(new GridLayout(3, 2, 10, 10))",
                        "Los componentes deben agregarse en orden: label, component, label, component, etc.",
                        "El botón debe tener un ActionListener"
                    ),
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "import java.awt.event.*;\n" +
                    "\n" +
                    "public class TextChallenge extends JPanel {\n" +
                    "    public TextChallenge() {\n" +
                    "        setLayout(new GridLayout(3, 2, 10, 10));\n" +
                    "        \n" +
                    "        add(new JLabel(\"Nombre:\"));\n" +
                    "        JTextField nameField = new JTextField(20);\n" +
                    "        add(nameField);\n" +
                    "        \n" +
                    "        add(new JLabel(\"Descripción:\"));\n" +
                    "        JTextArea descArea = new JTextArea(3, 20);\n" +
                    "        JScrollPane scrollPane = new JScrollPane(descArea);\n" +
                    "        add(scrollPane);\n" +
                    "        \n" +
                    "        JButton showButton = new JButton(\"Mostrar\");\n" +
                    "        showButton.addActionListener(e -> {\n" +
                    "            String text = \"Nombre: \" + nameField.getText() + \"\\n\" +\n" +
                    "                          \"Descripción: \" + descArea.getText();\n" +
                    "            JOptionPane.showMessageDialog(this, text);\n" +
                    "        });\n" +
                    "        add(showButton);\n" +
                    "    }\n" +
                    "}"
                ));
                break;

            case "Botones":
                challenges.add(new Challenge(
                    "Selector de Opciones",
                    "Crea un JPanel con:\n" +
                    "- Dos JCheckBox para opciones múltiples\n" +
                    "- Tres JRadioButton en un ButtonGroup\n" +
                    "- Un JButton que muestre las selecciones\n" +
                    "- Usa BoxLayout vertical",
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class ButtonChallenge extends JPanel {\n" +
                    "    public ButtonChallenge() {\n" +
                    "        // Tu código aquí\n" +
                    "    }\n" +
                    "}",
                    Arrays.asList(
                        "Usa setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))",
                        "Crea un ButtonGroup y agrega los JRadioButton a él",
                        "Usa isSelected() para verificar el estado de los componentes"
                    ),
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class ButtonChallenge extends JPanel {\n" +
                    "    public ButtonChallenge() {\n" +
                    "        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));\n" +
                    "        \n" +
                    "        JCheckBox option1 = new JCheckBox(\"Opción 1\");\n" +
                    "        JCheckBox option2 = new JCheckBox(\"Opción 2\");\n" +
                    "        add(option1);\n" +
                    "        add(option2);\n" +
                    "        \n" +
                    "        ButtonGroup group = new ButtonGroup();\n" +
                    "        JRadioButton radio1 = new JRadioButton(\"Bajo\");\n" +
                    "        JRadioButton radio2 = new JRadioButton(\"Medio\");\n" +
                    "        JRadioButton radio3 = new JRadioButton(\"Alto\");\n" +
                    "        group.add(radio1);\n" +
                    "        group.add(radio2);\n" +
                    "        group.add(radio3);\n" +
                    "        add(radio1);\n" +
                    "        add(radio2);\n" +
                    "        add(radio3);\n" +
                    "        \n" +
                    "        JButton showButton = new JButton(\"Mostrar Selección\");\n" +
                    "        showButton.addActionListener(e -> {\n" +
                    "            String message = \"CheckBoxes: \" + option1.isSelected() + \", \" + option2.isSelected() + \"\\n\";\n" +
                    "            String radio = radio1.isSelected() ? \"Bajo\" : radio2.isSelected() ? \"Medio\" : \"Alto\";\n" +
                    "            message += \"Radio: \" + radio;\n" +
                    "            JOptionPane.showMessageDialog(this, message);\n" +
                    "        });\n" +
                    "        add(showButton);\n" +
                    "    }\n" +
                    "}"
                ));
                break;

            case "Selección":
                challenges.add(new Challenge(
                    "Lista de Tareas",
                    "Crea un JPanel con:\n" +
                    "- Un JComboBox con prioridades (Baja, Media, Alta)\n" +
                    "- Un JList con tareas para seleccionar\n" +
                    "- Un JButton que muestre la selección\n" +
                    "- Usa BorderLayout",
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class SelectionChallenge extends JPanel {\n" +
                    "    public SelectionChallenge() {\n" +
                    "        // Tu código aquí\n" +
                    "    }\n" +
                    "}",
                    Arrays.asList(
                        "Usa BorderLayout con NORTH, CENTER, SOUTH",
                        "El JList debe tener datos de String[]",
                        "Usa getSelectedValue() para el JList y getSelectedItem() para el JComboBox"
                    ),
                    "import javax.swing.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class SelectionChallenge extends JPanel {\n" +
                    "    public SelectionChallenge() {\n" +
                    "        setLayout(new BorderLayout());\n" +
                    "        \n" +
                    "        // Priority combo box\n" +
                    "        JPanel topPanel = new JPanel(new FlowLayout());\n" +
                    "        topPanel.add(new JLabel(\"Prioridad:\"));\n" +
                    "        String[] priorities = {\"Baja\", \"Media\", \"Alta\"};\n" +
                    "        JComboBox<String> priorityBox = new JComboBox<>(priorities);\n" +
                    "        topPanel.add(priorityBox);\n" +
                    "        add(topPanel, BorderLayout.NORTH);\n" +
                    "        \n" +
                    "        // Task list\n" +
                    "        String[] tasks = {\"Comprar comida\", \"Hacer ejercicio\", \"Estudiar Java\", \"Llamar a mamá\", \"Limpiar casa\"};\n" +
                    "        JList<String> taskList = new JList<>(tasks);\n" +
                    "        JScrollPane listScroll = new JScrollPane(taskList);\n" +
                    "        add(listScroll, BorderLayout.CENTER);\n" +
                    "        \n" +
                    "        // Show button\n" +
                    "        JButton showButton = new JButton(\"Mostrar Selección\");\n" +
                    "        showButton.addActionListener(e -> {\n" +
                    "            String message = \"Prioridad: \" + priorityBox.getSelectedItem() + \"\\n\";\n" +
                    "            message += \"Tarea: \" + taskList.getSelectedValue();\n" +
                    "            JOptionPane.showMessageDialog(this, message);\n" +
                    "        });\n" +
                    "        add(showButton, BorderLayout.SOUTH);\n" +
                    "    }\n" +
                    "}"
                ));
                break;

            case "Avanzados":
                challenges.add(new Challenge(
                    "Tabla de Datos",
                    "Crea un JPanel con:\n" +
                    "- Un JTable con datos de empleados (Nombre, Puesto, Salario)\n" +
                    "- Un JSlider para filtrar por salario mínimo\n" +
                    "- Un JProgressBar que muestre el progreso\n" +
                    "- Usa BorderLayout",
                    "import javax.swing.*;\n" +
                    "import javax.swing.table.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class AdvancedChallenge extends JPanel {\n" +
                    "    public AdvancedChallenge() {\n" +
                    "        // Tu código aquí\n" +
                    "    }\n" +
                    "}",
                    Arrays.asList(
                        "Usa DefaultTableModel para crear la tabla",
                        "El JSlider debe tener ChangeListener",
                        "Configura el JProgressBar con setValue()"
                    ),
                    "import javax.swing.*;\n" +
                    "import javax.swing.table.*;\n" +
                    "import java.awt.*;\n" +
                    "\n" +
                    "public class AdvancedChallenge extends JPanel {\n" +
                    "    public AdvancedChallenge() {\n" +
                    "        setLayout(new BorderLayout());\n" +
                    "        \n" +
                    "        // Table data\n" +
                    "        String[] columns = {\"Nombre\", \"Puesto\", \"Salario\"};\n" +
                    "        Object[][] data = {\n" +
                    "            {\"Juan\", \"Desarrollador\", 50000},\n" +
                    "            {\"María\", \"Diseñador\", 45000},\n" +
                    "            {\"Pedro\", \"Gerente\", 60000}\n" +
                    "        };\n" +
                    "        DefaultTableModel model = new DefaultTableModel(data, columns);\n" +
                    "        JTable table = new JTable(model);\n" +
                    "        JScrollPane tableScroll = new JScrollPane(table);\n" +
                    "        add(tableScroll, BorderLayout.CENTER);\n" +
                    "        \n" +
                    "        // Controls panel\n" +
                    "        JPanel controlsPanel = new JPanel(new GridLayout(2, 1));\n" +
                    "        \n" +
                    "        JSlider salarySlider = new JSlider(30000, 80000, 40000);\n" +
                    "        salarySlider.setMajorTickSpacing(10000);\n" +
                    "        salarySlider.setPaintTicks(true);\n" +
                    "        salarySlider.setPaintLabels(true);\n" +
                    "        controlsPanel.add(salarySlider);\n" +
                    "        \n" +
                    "        JProgressBar progressBar = new JProgressBar(0, 100);\n" +
                    "        progressBar.setValue(75);\n" +
                    "        progressBar.setStringPainted(true);\n" +
                    "        controlsPanel.add(progressBar);\n" +
                    "        \n" +
                    "        add(controlsPanel, BorderLayout.SOUTH);\n" +
                    "    }\n" +
                    "}"
                ));
                break;
        }

        return challenges;
    }

    /**
     * Inner class representing a coding challenge.
     */
    private static class Challenge {
        private String title;
        private String description;
        private String starterCode;
        private java.util.List<String> hints;
        private String solution;

        public Challenge(String title, String description, String starterCode, java.util.List<String> hints, String solution) {
            this.title = title;
            this.description = description;
            this.starterCode = starterCode;
            this.hints = hints;
            this.solution = solution;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getStarterCode() { return starterCode; }
        public java.util.List<String> getHints() { return hints; }
        public String getSolution() { return solution; }

        /**
         * Checks if the user's code matches the expected solution structure.
         *
         * @param userCode The user's code
         * @return true if the solution is correct
         */
        public boolean checkSolution(String userCode) {
            // Basic checks - in a real implementation, this would be more sophisticated
            String lowerCode = userCode.toLowerCase();

            // Check for required components based on challenge
            if (title.contains("Campo de Texto")) {
                return lowerCode.contains("jtextfield") && lowerCode.contains("jtextarea") &&
                       lowerCode.contains("jbutton") && lowerCode.contains("gridlayout");
            } else if (title.contains("Selector de Opciones")) {
                return lowerCode.contains("jcheckbox") && lowerCode.contains("jradiobutton") &&
                       lowerCode.contains("buttongroup") && lowerCode.contains("boxlayout");
            } else if (title.contains("Lista de Tareas")) {
                return lowerCode.contains("jcombobox") && lowerCode.contains("jlist") &&
                       lowerCode.contains("jbutton") && lowerCode.contains("borderlayout");
            } else if (title.contains("Tabla de Datos")) {
                return lowerCode.contains("jtable") && lowerCode.contains("jslider") &&
                       lowerCode.contains("jprogressbar") && lowerCode.contains("borderlayout");
            }

            return false;
        }
    }
}