/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import javax.swing.*;

import retos.learnswing.auth.UserManager;


/**
 * Enhanced TextPanel with detailed explanations, examples, and interactive components.
 *
 * @author jocde
 */
public class TextPanel extends JPanel {

    /**
     * Constructor for TextPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public TextPanel() {
        setLayout(new BorderLayout());

        // Create tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Theory tab
        tabbedPane.addTab("📚 Teoría", createTheoryPanel());

        // Examples tab
        tabbedPane.addTab("💡 Ejemplos", createExamplesPanel());

        // Practice tab
        tabbedPane.addTab("🎯 Práctica", createPracticePanel());

        // Source Code tab
        tabbedPane.addTab("📝 Código Fuente", createSourceCodePanel());

        // Quiz tab
        tabbedPane.addTab("🎯 Quiz", createQuizPanel());

        // Tips tab
        tabbedPane.addTab("💡 Consejos", createTipsPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates the theory explanation panel.
     */
    private JPanel createTheoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea theoryText = new JTextArea();
        theoryText.setEditable(false);
        theoryText.setWrapStyleWord(true);
        theoryText.setLineWrap(true);
        theoryText.setFont(new Font("Arial", Font.PLAIN, 12));
        theoryText.setBackground(getBackground());

        theoryText.setText("🎯 COMPONENTES DE TEXTO EN JAVA SWING\n\n" +
                "Los componentes de texto permiten al usuario ingresar y mostrar información textual.\n\n" +
                "📝 JTextField - Campo de Texto Simple:\n" +
                "• Para entrada de una sola línea de texto\n" +
                "• Ideal para nombres, correos, números\n" +
                "• Tiene límite de caracteres visible\n" +
                "• Métodos importantes: getText(), setText(), setEditable()\n\n" +
                "📄 JTextArea - Área de Texto Multilínea:\n" +
                "• Para entrada de múltiples líneas\n" +
                "• Perfecto para comentarios, descripciones\n" +
                "• Soporta scroll automático con JScrollPane\n" +
                "• Métodos importantes: append(), setLineWrap(), setWrapStyleWord()\n\n" +
                "🔒 JPasswordField - Campo de Contraseña:\n" +
                "• Similar a JTextField pero oculta los caracteres\n" +
                "• Muestra puntos o asteriscos en lugar del texto real\n" +
                "• Método especial: getPassword() devuelve char[]\n\n" +
                "✨ Características Comunes:\n" +
                "• Todos heredan de JTextComponent\n" +
                "• Soporte para eventos (DocumentListener, ActionListener)\n" +
                "• Validación de entrada posible\n" +
                "• Personalización de fuentes y colores");

        JScrollPane scrollPane = new JScrollPane(theoryText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the examples panel with working code samples.
     */
    private JPanel createExamplesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Example 1: JTextField
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JTextField Básico"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// Crear un JTextField básico\n" +
                "JTextField textField = new JTextField(20);\n" +
                "textField.setText(\"Texto inicial\");\n" +
                "\n" +
                "// Obtener el texto ingresado\n" +
                "String texto = textField.getText();\n" +
                "\n" +
                "// Hacer el campo no editable\n" +
                "textField.setEditable(false);");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        JPanel fieldPanel = new JPanel(new FlowLayout());
        fieldPanel.add(new JLabel("Nombre:"));
        JTextField demoField = new JTextField(15);
        demoField.setText("Juan Pérez");
        fieldPanel.add(demoField);

        JButton getTextBtn = new JButton("Obtener Texto");
        getTextBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Texto: " + demoField.getText()));
        fieldPanel.add(getTextBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(fieldPanel, BorderLayout.CENTER);

        // Example 2: JTextArea
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: JTextArea con Scroll"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// Crear JTextArea con JScrollPane\n" +
                "JTextArea textArea = new JTextArea(5, 30);\n" +
                "textArea.setLineWrap(true);\n" +
                "textArea.setWrapStyleWord(true);\n" +
                "\n" +
                "JScrollPane scrollPane = new JScrollPane(textArea);\n" +
                "scrollPane.setVerticalScrollBarPolicy(\n" +
                "    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel areaPanel = new JPanel(new BorderLayout());
        JTextArea demoArea = new JTextArea(4, 25);
        demoArea.setText("Esta es un área de texto multilínea.\nPuedes escribir varias líneas aquí.\n\nIncluye automáticamente scroll cuando es necesario.");
        JScrollPane demoScroll = new JScrollPane(demoArea);
        areaPanel.add(demoScroll, BorderLayout.CENTER);

        JButton appendBtn = new JButton("Agregar Texto");
        appendBtn.addActionListener(e ->
            demoArea.append("\n\nTexto agregado dinámicamente."));
        areaPanel.add(appendBtn, BorderLayout.SOUTH);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(areaPanel, BorderLayout.CENTER);

        // Combine examples
        JPanel examplesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        examplesPanel.add(example1);
        examplesPanel.add(example2);

        JScrollPane scrollPane = new JScrollPane(examplesPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the interactive practice panel.
     */
    private JPanel createPracticePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel practicePanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // JTextField practice
        practicePanel.add(new JLabel("Campo de Texto:"));
        JTextField textField = new JTextField(20);
        textField.setToolTipText("Ingresa tu nombre completo");
        practicePanel.add(textField);

        // JTextArea practice
        practicePanel.add(new JLabel("Área de Texto:"));
        JTextArea textArea = new JTextArea(3, 20);
        textArea.setToolTipText("Escribe una breve descripción");
        JScrollPane scrollPane = new JScrollPane(textArea);
        practicePanel.add(scrollPane);

        // JPasswordField practice
        practicePanel.add(new JLabel("Contraseña:"));
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setToolTipText("Ingresa una contraseña segura");
        practicePanel.add(passwordField);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton showAllBtn = new JButton("Mostrar Todo");
        showAllBtn.addActionListener(e -> {
            String message = "📝 Campo de Texto: " + textField.getText() + "\n\n" +
                           "📄 Área de Texto:\n" + textArea.getText() + "\n\n" +
                           "🔒 Contraseña: " + new String(passwordField.getPassword());
            JOptionPane.showMessageDialog(this, message, "Datos Ingresados", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(showAllBtn);

        JButton clearBtn = new JButton("Limpiar");
        clearBtn.addActionListener(e -> {
            textField.setText("");
            textArea.setText("");
            passwordField.setText("");
        });
        buttonPanel.add(clearBtn);

        practicePanel.add(new JLabel("")); // Empty cell
        practicePanel.add(buttonPanel);

        panel.add(practicePanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the source code panel with complete implementations.
     */
    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane codeTabs = new JTabbedPane();

        // JTextField example
        JTextArea textFieldCode = new JTextArea();
        textFieldCode.setEditable(false);
        textFieldCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textFieldCode.setBackground(Color.BLACK);
        textFieldCode.setForeground(Color.GREEN);
        textFieldCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class TextFieldExample extends JPanel {\n" +
                "    private JTextField nameField;\n" +
                "    private JButton submitButton;\n" +
                "    \n" +
                "    public TextFieldExample() {\n" +
                "        setLayout(new FlowLayout());\n" +
                "        \n" +
                "        // Crear campo de texto\n" +
                "        nameField = new JTextField(20);\n" +
                "        nameField.setToolTipText(\"Ingresa tu nombre\");\n" +
                "        \n" +
                "        // Configurar propiedades\n" +
                "        nameField.setFont(new Font(\"Arial\", Font.PLAIN, 14));\n" +
                "        nameField.setHorizontalAlignment(JTextField.LEFT);\n" +
                "        \n" +
                "        // Agregar listener para Enter\n" +
                "        nameField.addActionListener(e -> {\n" +
                "            String name = nameField.getText().trim();\n" +
                "            if (!name.isEmpty()) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Hola, \" + name + \"!\");\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        // Botón para procesar\n" +
                "        submitButton = new JButton(\"Saludar\");\n" +
                "        submitButton.addActionListener(e -> {\n" +
                "            nameField.postActionEvent(); // Simula Enter\n" +
                "        });\n" +
                "        \n" +
                "        add(new JLabel(\"Nombre:\"));\n" +
                "        add(nameField);\n" +
                "        add(submitButton);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public void setText(String text) {\n" +
                "        nameField.setText(text);\n" +
                "    }\n" +
                "    \n" +
                "    public String getText() {\n" +
                "        return nameField.getText();\n" +
                "    }\n" +
                "    \n" +
                "    public void setEditable(boolean editable) {\n" +
                "        nameField.setEditable(editable);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JTextField", new JScrollPane(textFieldCode));

        // JTextArea example
        JTextArea textAreaCode = new JTextArea();
        textAreaCode.setEditable(false);
        textAreaCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textAreaCode.setBackground(Color.BLACK);
        textAreaCode.setForeground(Color.GREEN);
        textAreaCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class TextAreaExample extends JPanel {\n" +
                "    private JTextArea textArea;\n" +
                "    private JButton countButton;\n" +
                "    private JLabel infoLabel;\n" +
                "    \n" +
                "    public TextAreaExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Crear área de texto\n" +
                "        textArea = new JTextArea(10, 40);\n" +
                "        textArea.setFont(new Font(\"Monospaced\", Font.PLAIN, 12));\n" +
                "        \n" +
                "        // Configurar propiedades\n" +
                "        textArea.setLineWrap(true);\n" +
                "        textArea.setWrapStyleWord(true);\n" +
                "        textArea.setTabSize(4);\n" +
                "        \n" +
                "        // Panel de scroll\n" +
                "        JScrollPane scrollPane = new JScrollPane(textArea);\n" +
                "        scrollPane.setVerticalScrollBarPolicy(\n" +
                "            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);\n" +
                "        scrollPane.setHorizontalScrollBarPolicy(\n" +
                "            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        countButton = new JButton(\"Contar Caracteres\");\n" +
                "        countButton.addActionListener(e -> {\n" +
                "            String text = textArea.getText();\n" +
                "            int chars = text.length();\n" +
                "            int words = text.trim().isEmpty() ? 0 : text.trim().split(\"\\\\s+\").length;\n" +
                "            int lines = textArea.getLineCount();\n" +
                "            \n" +
                "            infoLabel.setText(String.format(\n" +
                "                \"Caracteres: %d | Palabras: %d | Líneas: %d\",\n" +
                "                chars, words, lines));\n" +
                "        });\n" +
                "        \n" +
                "        JButton clearButton = new JButton(\"Limpiar\");\n" +
                "        clearButton.addActionListener(e -> {\n" +
                "            textArea.setText(\"\");\n" +
                "            infoLabel.setText(\"Caracteres: 0 | Palabras: 0 | Líneas: 1\");\n" +
                "        });\n" +
                "        \n" +
                "        infoLabel = new JLabel(\"Caracteres: 0 | Palabras: 0 | Líneas: 1\");\n" +
                "        \n" +
                "        controlPanel.add(countButton);\n" +
                "        controlPanel.add(clearButton);\n" +
                "        controlPanel.add(infoLabel);\n" +
                "        \n" +
                "        add(scrollPane, BorderLayout.CENTER);\n" +
                "        add(controlPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public void setText(String text) {\n" +
                "        textArea.setText(text);\n" +
                "    }\n" +
                "    \n" +
                "    public String getText() {\n" +
                "        return textArea.getText();\n" +
                "    }\n" +
                "    \n" +
                "    public void append(String text) {\n" +
                "        textArea.append(text);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JTextArea", new JScrollPane(textAreaCode));

        // JPasswordField example
        JTextArea passwordCode = new JTextArea();
        passwordCode.setEditable(false);
        passwordCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        passwordCode.setBackground(Color.BLACK);
        passwordCode.setForeground(Color.GREEN);
        passwordCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "import java.util.Arrays;\n" +
                "\n" +
                "public class PasswordFieldExample extends JPanel {\n" +
                "    private JPasswordField passwordField;\n" +
                "    private JPasswordField confirmField;\n" +
                "    private JCheckBox showPassword;\n" +
                "    private JButton validateButton;\n" +
                "    \n" +
                "    public PasswordFieldExample() {\n" +
                "        setLayout(new GridBagLayout());\n" +
                "        GridBagConstraints gbc = new GridBagConstraints();\n" +
                "        gbc.insets = new Insets(5, 5, 5, 5);\n" +
                "        \n" +
                "        // Campo de contraseña\n" +
                "        gbc.gridx = 0; gbc.gridy = 0;\n" +
                "        add(new JLabel(\"Contraseña:\"), gbc);\n" +
                "        \n" +
                "        gbc.gridx = 1;\n" +
                "        passwordField = new JPasswordField(20);\n" +
                "        passwordField.setEchoChar('*');\n" +
                "        add(passwordField, gbc);\n" +
                "        \n" +
                "        // Campo de confirmación\n" +
                "        gbc.gridx = 0; gbc.gridy = 1;\n" +
                "        add(new JLabel(\"Confirmar:\"), gbc);\n" +
                "        \n" +
                "        gbc.gridx = 1;\n" +
                "        confirmField = new JPasswordField(20);\n" +
                "        confirmField.setEchoChar('*');\n" +
                "        add(confirmField, gbc);\n" +
                "        \n" +
                "        // Checkbox para mostrar/ocultar\n" +
                "        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;\n" +
                "        showPassword = new JCheckBox(\"Mostrar contraseña\");\n" +
                "        showPassword.addActionListener(e -> {\n" +
                "            char echoChar = showPassword.isSelected() ? (char) 0 : '*';\n" +
                "            passwordField.setEchoChar(echoChar);\n" +
                "            confirmField.setEchoChar(echoChar);\n" +
                "        });\n" +
                "        add(showPassword, gbc);\n" +
                "        \n" +
                "        // Botón de validación\n" +
                "        gbc.gridy = 3;\n" +
                "        validateButton = new JButton(\"Validar Contraseña\");\n" +
                "        validateButton.addActionListener(e -> validatePassword());\n" +
                "        add(validateButton, gbc);\n" +
                "    }\n" +
                "    \n" +
                "    private void validatePassword() {\n" +
                "        char[] password = passwordField.getPassword();\n" +
                "        char[] confirm = confirmField.getPassword();\n" +
                "        \n" +
                "        try {\n" +
                "            // Validaciones de seguridad\n" +
                "            if (password.length < 8) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"La contraseña debe tener al menos 8 caracteres.\");\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            if (!Arrays.equals(password, confirm)) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Las contraseñas no coinciden.\");\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            // Verificar complejidad\n" +
                "            boolean hasUpper = false, hasLower = false, hasDigit = false;\n" +
                "            for (char c : password) {\n" +
                "                if (Character.isUpperCase(c)) hasUpper = true;\n" +
                "                else if (Character.isLowerCase(c)) hasLower = true;\n" +
                "                else if (Character.isDigit(c)) hasDigit = true;\n" +
                "            }\n" +
                "            \n" +
                "            if (!hasUpper || !hasLower || !hasDigit) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"La contraseña debe contener mayúsculas, minúsculas y números.\");\n" +
                "                return;\n" +
                "            }\n" +
                "            \n" +
                "            JOptionPane.showMessageDialog(this,\n" +
                "                \"✅ Contraseña válida y segura!\");\n" +
                "                \n" +
                "        } finally {\n" +
                "            // Limpiar arrays por seguridad\n" +
                "            Arrays.fill(password, ' ');\n" +
                "            Arrays.fill(confirm, ' ');\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    // Método seguro para obtener contraseña\n" +
                "    public char[] getPassword() {\n" +
                "        return passwordField.getPassword();\n" +
                "    }\n" +
                "    \n" +
                "    public void clearFields() {\n" +
                "        passwordField.setText(\"\");\n" +
                "        confirmField.setText(\"\");\n" +
                "        showPassword.setSelected(false);\n" +
                "        passwordField.setEchoChar('*');\n" +
                "        confirmField.setEchoChar('*');\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JPasswordField", new JScrollPane(passwordCode));

        panel.add(codeTabs, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the quiz panel for this lesson.
     */
    private JPanel createQuizPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel quizPanel = new JPanel(new BorderLayout());

        // Quiz title
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes de Texto", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre JTextField, JTextArea y JPasswordField.\n\n" +
                "• 6 preguntas de opción múltiple\n" +
                "• Cada respuesta correcta vale 10 XP\n" +
                "• Puntaje perfecto: 100 XP + bono\n" +
                "• Puedes repetir el quiz las veces que quieras");
        descArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Start quiz button
        JButton startQuizBtn = new JButton("🚀 Comenzar Quiz");
        startQuizBtn.setFont(new Font("Arial", Font.BOLD, 14));
        startQuizBtn.setPreferredSize(new Dimension(200, 40));
        startQuizBtn.addActionListener(e -> {
            JFrame quizFrame = new JFrame("Quiz: Componentes de Texto");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Texto", UserManager.getCurrentUserStats());
            quizFrame.add(quiz);
            quizFrame.setVisible(true);
        });

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(descArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startQuizBtn);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        quizPanel.add(centerPanel, BorderLayout.CENTER);

        panel.add(quizPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the tips and best practices panel.
     */
    private JPanel createTipsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea tipsText = new JTextArea();
        tipsText.setEditable(false);
        tipsText.setWrapStyleWord(true);
        tipsText.setLineWrap(true);
        tipsText.setFont(new Font("Arial", Font.PLAIN, 12));
        tipsText.setBackground(getBackground());

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS\n\n" +
                "✅ USOS RECOMENDADOS:\n" +
                "• JTextField: Nombres, correos, números de teléfono\n" +
                "• JTextArea: Comentarios, descripciones, texto largo\n" +
                "• JPasswordField: Siempre para contraseñas\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Siempre usa JScrollPane con JTextArea\n" +
                "• Configura setLineWrap(true) para mejor UX\n" +
                "• Usa setWrapStyleWord(true) para evitar cortar palabras\n" +
                "• Valida entrada antes de procesar\n" +
                "• Considera límites de caracteres\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• Olvidar JScrollPane con JTextArea → texto se corta\n" +
                "• No configurar line wrap → scroll horizontal incómodo\n" +
                "• Usar getText() en JPasswordField → inseguro\n" +
                "• No validar entrada → excepciones inesperadas\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Usa DocumentListener para validación en tiempo real\n" +
                "• Implementa InputVerifier para validación avanzada\n" +
                "• Considera JTextPane/JEditorPane para texto rico\n" +
                "• Usa SwingWorker para procesamiento de texto largo\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para números: combina con JFormattedTextField\n" +
                "• Para búsqueda: agrega ActionListener al JTextField\n" +
                "• Para autocompletado: implementa custom DocumentFilter\n" +
                "• Para internacionalización: usa ResourceBundle");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
