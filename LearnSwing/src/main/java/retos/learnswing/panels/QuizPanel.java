/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import retos.learnswing.auth.UserStats;

/**
 * Interactive quiz panel for testing knowledge on Swing components.
 *
 * @author jocde
 */
public class QuizPanel extends JPanel {
    private String lessonName;
    private UserStats userStats;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private int totalQuestions;

    private JLabel questionLabel;
    private JPanel optionsPanel;
    private JButton nextButton;
    private JButton finishButton;
    private JLabel scoreLabel;
    private JLabel progressLabel;
    private JProgressBar progressBar;

    /**
     * Constructor for QuizPanel.
     *
     * @param lessonName The name of the lesson to quiz on
     * @param userStats The user stats instance
     */
    public QuizPanel(String lessonName, UserStats userStats) {
        this.lessonName = lessonName;
        this.userStats = userStats;
        this.questions = generateQuestionsForLesson(lessonName);
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.totalQuestions = questions.size();

        initializeComponents();
        showQuestion();
    }

    /**
     * Initializes the quiz components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Header panel with progress and score
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        progressLabel = new JLabel("Pregunta 1 de " + totalQuestions);
        progressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(progressLabel, BorderLayout.WEST);

        scoreLabel = new JLabel("Puntuación: 0/" + totalQuestions);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(scoreLabel, BorderLayout.EAST);

        progressBar = new JProgressBar(0, totalQuestions);
        progressBar.setValue(1);
        progressBar.setStringPainted(true);
        headerPanel.add(progressBar, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Question panel
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        questionLabel.setVerticalAlignment(SwingConstants.TOP);
        JScrollPane questionScroll = new JScrollPane(questionLabel);
        questionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScroll.setPreferredSize(new Dimension(600, 80));
        questionPanel.add(questionScroll, BorderLayout.NORTH);

        // Options panel
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        questionPanel.add(optionsPanel, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        nextButton = new JButton("Siguiente");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswerAndNext();
            }
        });
        buttonPanel.add(nextButton);

        finishButton = new JButton("Finalizar Quiz");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishQuiz();
            }
        });
        finishButton.setVisible(false);
        buttonPanel.add(finishButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Shows the current question.
     */
    private void showQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        QuizQuestion question = questions.get(currentQuestionIndex);

        progressLabel.setText("Pregunta " + (currentQuestionIndex + 1) + " de " + totalQuestions);
        progressBar.setValue(currentQuestionIndex + 1);
        scoreLabel.setText("Puntuación: " + score + "/" + totalQuestions);

        questionLabel.setText("<html><b>" + question.getQuestion() + "</b></html>");

        optionsPanel.removeAll();

        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < question.getOptions().size(); i++) {
            JRadioButton option = new JRadioButton(question.getOptions().get(i));
            option.setActionCommand(String.valueOf(i));
            group.add(option);
            optionsPanel.add(option);
            optionsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        optionsPanel.revalidate();
        optionsPanel.repaint();

        nextButton.setEnabled(true);
        if (currentQuestionIndex == questions.size() - 1) {
            nextButton.setText("Finalizar");
        } else {
            nextButton.setText("Siguiente");
        }
    }

    /**
     * Checks the answer and moves to next question.
     */
    private void checkAnswerAndNext() {
        QuizQuestion question = questions.get(currentQuestionIndex);

        // Find selected option
        int selectedIndex = -1;
        Component[] components = optionsPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JRadioButton) {
                JRadioButton radio = (JRadioButton) comp;
                if (radio.isSelected()) {
                    selectedIndex = Integer.parseInt(radio.getActionCommand());
                    break;
                }
            }
        }

        if (selectedIndex == question.getCorrectAnswerIndex()) {
            score++;
            JOptionPane.showMessageDialog(this, "¡Correcto! +10 XP",
                    "Respuesta Correcta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto. La respuesta correcta es: " +
                    question.getOptions().get(question.getCorrectAnswerIndex()),
                    "Respuesta Incorrecta", JOptionPane.WARNING_MESSAGE);
        }

        currentQuestionIndex++;
        showQuestion();
    }

    /**
     * Finishes the quiz and shows results.
     */
    private void finishQuiz() {
        int percentage = (int) ((double) score / totalQuestions * 100);

        // Record quiz attempt
        userStats.recordQuizAttempt(lessonName, percentage);

        // Auto-complete lesson if perfect score
        if (percentage == 100) {
            // Update lesson progress to completed
            retos.learnswing.auth.UserManager.updateLessonProgress(lessonName, "completed");
        }

        String message = "¡Quiz completado!\n\n";
        message += "Puntuación: " + score + "/" + totalQuestions + " (" + percentage + "%)\n";
        message += "XP ganado: " + (percentage / 10) + "\n\n";

        if (percentage == 100) {
            message += "¡PERFECTO! Lección completada automáticamente. 🎉🏆";
        } else if (percentage >= 80) {
            message += "¡Excelente trabajo! 🎉";
        } else if (percentage >= 60) {
            message += "Buen trabajo, sigue practicando.";
        } else {
            message += "Necesitas estudiar más este tema.";
        }

        JOptionPane.showMessageDialog(this, message, "Resultados del Quiz",
                percentage >= 80 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

        // Close the quiz window
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    /**
     * Generates questions for a specific lesson.
     *
     * @param lessonName The name of the lesson
     * @return List of quiz questions
     */
    private List<QuizQuestion> generateQuestionsForLesson(String lessonName) {
        List<QuizQuestion> questions = new ArrayList<>();

        switch (lessonName) {
            case "Texto":
                questions.add(new QuizQuestion(
                    "¿Cuál es la diferencia principal entre JTextField y JTextArea?",
                    Arrays.asList("JTextField es multi-línea, JTextArea es una línea", "JTextArea es multi-línea, JTextField es una línea", "Ambos son iguales", "JTextField no existe"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para obtener el texto de un JTextField?",
                    Arrays.asList("getText()", "getValue()", "getString()", "getContent()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál de estos NO es un componente de texto en Swing?",
                    Arrays.asList("JTextField", "JTextArea", "JPasswordField", "JTextLabel"),
                    3
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa específicamente para contraseñas?",
                    Arrays.asList("JTextField", "JTextArea", "JPasswordField", "JLabel"),
                    2
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para establecer el texto en un JTextArea?",
                    Arrays.asList("setText()", "setValue()", "setString()", "setContent()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la ventaja principal de JTextArea sobre JTextField?",
                    Arrays.asList("Es más rápido", "Permite múltiples líneas", "Tiene mejor apariencia", "Es más pequeño"),
                    1
                ));
                break;

            case "Botones":
                questions.add(new QuizQuestion(
                    "¿Qué componente permite selección múltiple independiente?",
                    Arrays.asList("JRadioButton", "JCheckBox", "JButton", "JToggleButton"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué se necesita para agrupar JRadioButtons mutuamente exclusivos?",
                    Arrays.asList("ButtonGroup", "PanelGroup", "RadioGroup", "SelectionGroup"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la diferencia entre JButton y JToggleButton?",
                    Arrays.asList("JToggleButton mantiene estado presionado", "JButton es más grande", "JToggleButton no tiene texto", "No hay diferencia"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para verificar si un JCheckBox está seleccionado?",
                    Arrays.asList("isSelected()", "isChecked()", "getState()", "isPressed()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuántos JRadioButtons pueden estar seleccionados en un ButtonGroup?",
                    Arrays.asList("Ninguno", "Uno", "Todos", "Depende del programador"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa para acciones que el usuario puede ejecutar?",
                    Arrays.asList("JLabel", "JButton", "JTextField", "JPanel"),
                    1
                ));
                break;

            case "Selección":
                questions.add(new QuizQuestion(
                    "¿Qué componente muestra una lista desplegable con opciones?",
                    Arrays.asList("JList", "JComboBox", "JTable", "JTree"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente permite selección múltiple por defecto?",
                    Arrays.asList("JComboBox", "JList", "JRadioButton", "JSpinner"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para obtener el elemento seleccionado de un JComboBox?",
                    Arrays.asList("getSelectedItem()", "getValue()", "getText()", "getSelection()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa para seleccionar valores numéricos con flechas arriba/abajo?",
                    Arrays.asList("JSlider", "JSpinner", "JProgressBar", "JComboBox"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la diferencia entre JComboBox y JList?",
                    Arrays.asList("JComboBox ahorra espacio, JList muestra más opciones", "JList ahorra espacio, JComboBox muestra más opciones", "Son iguales", "JComboBox no existe"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa para valores continuos en un rango?",
                    Arrays.asList("JSpinner", "JSlider", "JProgressBar", "JComboBox"),
                    1
                ));
                break;

            case "Avanzados":
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa para mostrar datos en formato tabular?",
                    Arrays.asList("JList", "JTable", "JComboBox", "JTree"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué modelo se usa típicamente con JTable?",
                    Arrays.asList("TableModel", "ListModel", "ComboBoxModel", "TreeModel"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente muestra el progreso de una tarea larga?",
                    Arrays.asList("JSlider", "JSpinner", "JProgressBar", "JTable"),
                    2
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método configura el valor máximo de un JProgressBar?",
                    Arrays.asList("setMaximum()", "setMax()", "setUpper()", "setLimit()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es el propósito principal de JScrollPane?",
                    Arrays.asList("Decorar componentes", "Agregar scroll a componentes grandes", "Cambiar colores", "Crear animaciones"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente permite ajustar valores arrastrando un control deslizante?",
                    Arrays.asList("JSpinner", "JSlider", "JProgressBar", "JComboBox"),
                    1
                ));
                break;

            case "Otros":
                questions.add(new QuizQuestion(
                    "¿Qué componente oculta el texto introducido mostrando asteriscos?",
                    Arrays.asList("JTextField", "JPasswordField", "JTextArea", "JLabel"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente permite entrada de texto con formato específico?",
                    Arrays.asList("JFormattedTextField", "JTextField", "JPasswordField", "JTextArea"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente puede estar en estado presionado o no presionado?",
                    Arrays.asList("JButton", "JToggleButton", "JRadioButton", "JCheckBox"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué modelo se usa con JSpinner para fechas?",
                    Arrays.asList("SpinnerNumberModel", "SpinnerDateModel", "SpinnerListModel", "SpinnerTextModel"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la función principal de JFormattedTextField?",
                    Arrays.asList("Mostrar texto coloreado", "Validar y formatear entrada de texto", "Crear contraseñas", "Mostrar múltiples líneas"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente se usa para seleccionar de una lista de valores predefinidos?",
                    Arrays.asList("JComboBox", "JSpinner con SpinnerListModel", "JList", "Todos los anteriores"),
                    3
                ));
                break;

            case "Adicionales":
                questions.add(new QuizQuestion(
                    "¿Qué componente muestra datos en estructura jerárquica?",
                    Arrays.asList("JTable", "JTree", "JList", "JComboBox"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente permite al usuario seleccionar archivos del sistema?",
                    Arrays.asList("JFileChooser", "JColorChooser", "JOptionPane", "JDialog"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué componente permite al usuario seleccionar colores?",
                    Arrays.asList("JColorChooser", "JFileChooser", "JFontChooser", "JOptionPane"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué es JInternalFrame?",
                    Arrays.asList("Una ventana dentro de otra ventana", "Un marco externo", "Un panel flotante", "Un botón especial"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para mostrar un JFileChooser?",
                    Arrays.asList("showOpenDialog()", "showFileDialog()", "showChooser()", "displayFile()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la ventaja de JInternalFrame sobre JFrame?",
                    Arrays.asList("Es más rápido", "Puede estar dentro de otro contenedor", "Tiene mejor apariencia", "Es más pequeño"),
                    1
                ));
                break;

            case "Gráficos":
                questions.add(new QuizQuestion(
                    "¿Qué paquete contiene las clases para crear gráficos en Java?",
                    Arrays.asList("java.awt", "javax.swing", "java.graphics", "javax.chart"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se usa para dibujar un rectángulo relleno?",
                    Arrays.asList("drawRect()", "fillRect()", "paintRect()", "createRect()"),
                    1
                ));
                questions.add(new QuizQuestion(
                    "¿Qué clase se usa para definir colores en Java?",
                    Arrays.asList("Color", "Paint", "Graphics", "Shape"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué método se llama automáticamente cuando se repinta un componente?",
                    Arrays.asList("paint()", "draw()", "render()", "display()"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Qué interfaz se implementa para manejar eventos de mouse?",
                    Arrays.asList("MouseListener", "MouseMotionListener", "MouseEvent", "MouseHandler"),
                    0
                ));
                questions.add(new QuizQuestion(
                    "¿Cuál es la coordenada Y de la esquina superior izquierda?",
                    Arrays.asList("0", "Ancho del componente", "Alto del componente", "Depende del componente"),
                    0
                ));
                break;
        }

        // Shuffle questions for variety
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(6, questions.size())); // Return up to 6 questions
    }

    /**
     * Inner class representing a quiz question.
     */
    private static class QuizQuestion {
        private String question;
        private List<String> options;
        private int correctAnswerIndex;

        public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getQuestion() { return question; }
        public List<String> getOptions() { return options; }
        public int getCorrectAnswerIndex() { return correctAnswerIndex; }
    }
}