/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import retos.learnswing.auth.UserManager;
/**
 * Enhanced OtherPanel with detailed explanations, examples, and interactive
 * components.
 *
 * @author jocde
 */
public class OtherPanel extends JPanel {

    /**
     * Constructor for OtherPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public OtherPanel() {
        setLayout(new BorderLayout());

        // Create tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Theory tab
        tabbedPane.addTab("📚 Teoría", createTheoryPanel());

        // Examples tab
        tabbedPane.addTab("💡 Ejemplos", createExamplesPanel());

        // Practice tab
        tabbedPane.addTab("🎯 Práctica", createPracticePanel());

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

        theoryText.setText("🎯 COMPONENTES ESPECIALIZADOS EN JAVA SWING\n\n" +
                "Estos componentes proporcionan funcionalidades específicas para casos de uso particulares.\n\n" +
                "🔒 JPasswordField - Campo de Contraseña:\n" +
                "• Similar a JTextField pero oculta caracteres\n" +
                "• Muestra puntos o asteriscos en lugar del texto\n" +
                "• Método getPassword() devuelve char[] por seguridad\n" +
                "• Ideal para autenticación y datos sensibles\n\n" +
                "🔢 JSpinner - Selector Numérico:\n" +
                "• Para selección de números con controles arriba/abajo\n" +
                "• Configurable con límites, pasos y tipos de datos\n" +
                "• Soporta SpinnerNumberModel, SpinnerDateModel, etc.\n" +
                "• Útil para cantidades, edades, porcentajes\n\n" +
                "📅 JFormattedTextField - Campo con Formato:\n" +
                "• JTextField con formato automático\n" +
                "• Soporta máscaras para teléfonos, fechas, números\n" +
                "• Usa Format objects (NumberFormat, DateFormat)\n" +
                "• Valida entrada automáticamente\n\n" +
                "🔄 JToggleButton - Botón Conmutable:\n" +
                "• Mantiene estado presionado/soltado\n" +
                "• Apariencia de botón pero comportamiento de checkbox\n" +
                "• Útil para modos (negrita, cursiva, etc.)\n" +
                "• Puede agruparse en ButtonGroup\n\n" +
                "✨ Características Especializadas:\n" +
                "• Validación automática de entrada\n" +
                "• Formateo inteligente de datos\n" +
                "• Seguridad mejorada para datos sensibles\n" +
                "• Interfaz intuitiva para tipos específicos de datos");

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

        // Example 1: JPasswordField and Security
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JPasswordField con Validación"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// JPasswordField para entrada segura\n" +
                "JPasswordField passwordField = new JPasswordField(20);\n" +
                "passwordField.setEchoChar('*'); // Carácter de máscara\n" +
                "\n" +
                "// Obtener contraseña de forma segura\n" +
                "char[] password = passwordField.getPassword();\n" +
                "String passwordString = new String(password);\n" +
                "\n" +
                "// Limpiar array por seguridad\n" +
                "Arrays.fill(password, ' ');\n" +
                "\n" +
                "// Validación básica\n" +
                "if (passwordString.length() < 8) {\n" +
                "    JOptionPane.showMessageDialog(null, \"Contraseña muy corta\");\n" +
                "}");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        JPanel passwordDemo = new JPanel(new GridLayout(3, 2, 5, 5));
        passwordDemo.add(new JLabel("Usuario:"));
        JTextField userField = new JTextField(15);
        passwordDemo.add(userField);

        passwordDemo.add(new JLabel("Contraseña:"));
        JPasswordField demoPassword = new JPasswordField(15);
        passwordDemo.add(demoPassword);

        passwordDemo.add(new JLabel("Confirmar:"));
        JPasswordField confirmPassword = new JPasswordField(15);
        passwordDemo.add(confirmPassword);

        JButton validateBtn = new JButton("Validar");
        validateBtn.addActionListener(e -> {
            String password = new String(demoPassword.getPassword());
            String confirm = new String(confirmPassword.getPassword());

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres");
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            } else {
                JOptionPane.showMessageDialog(this, "✅ Contraseña válida");
            }
        });
        passwordDemo.add(new JLabel(""));
        passwordDemo.add(validateBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(passwordDemo, BorderLayout.CENTER);

        // Example 2: JSpinner and JFormattedTextField
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: JSpinner y JFormattedTextField"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// JSpinner con SpinnerNumberModel\n" +
                "SpinnerNumberModel numberModel = new SpinnerNumberModel(50, 0, 100, 5);\n" +
                "JSpinner numberSpinner = new JSpinner(numberModel);\n" +
                "\n" +
                "// JSpinner con fechas\n" +
                "SpinnerDateModel dateModel = new SpinnerDateModel();\n" +
                "JSpinner dateSpinner = new JSpinner(dateModel);\n" +
                "dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, \"dd/MM/yyyy\"));\n" +
                "\n" +
                "// JFormattedTextField para números\n" +
                "NumberFormat numberFormat = NumberFormat.getCurrencyInstance();\n" +
                "JFormattedTextField currencyField = new JFormattedTextField(numberFormat);\n" +
                "currencyField.setValue(1234.56);");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel spinnerDemo = new JPanel(new GridLayout(4, 2, 5, 5));

        // Number spinner
        spinnerDemo.add(new JLabel("Cantidad (0-100):"));
        JSpinner numberSpinner = new JSpinner(new SpinnerNumberModel(50, 0, 100, 5));
        spinnerDemo.add(numberSpinner);

        // Date spinner
        spinnerDemo.add(new JLabel("Fecha:"));
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        spinnerDemo.add(dateSpinner);

        // Currency formatted field
        spinnerDemo.add(new JLabel("Precio:"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        JFormattedTextField currencyField = new JFormattedTextField(currencyFormat);
        currencyField.setValue(99.99);
        spinnerDemo.add(currencyField);

        // Phone formatted field
        spinnerDemo.add(new JLabel("Teléfono:"));
        try {
            MaskFormatter phoneFormat = new MaskFormatter("(###) ###-####");
            phoneFormat.setPlaceholderCharacter('_');
            JFormattedTextField phoneField = new JFormattedTextField(phoneFormat);
            spinnerDemo.add(phoneField);
        } catch (ParseException e) {
            JTextField phoneField = new JTextField("(555) 123-4567");
            spinnerDemo.add(phoneField);
        }

        example2.add(code2, BorderLayout.NORTH);
        example2.add(spinnerDemo, BorderLayout.CENTER);

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

        JPanel practicePanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // Password practice
        JPanel passwordSection = new JPanel(new BorderLayout());
        passwordSection.setBorder(BorderFactory.createTitledBorder("Práctica: Sistema de Login"));

        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        loginPanel.add(new JLabel("Usuario:"));
        JTextField loginUser = new JTextField(15);
        loginPanel.add(loginUser);

        loginPanel.add(new JLabel("Contraseña:"));
        JPasswordField loginPass = new JPasswordField(15);
        loginPanel.add(loginPass);

        loginPanel.add(new JLabel("Confirmar:"));
        JPasswordField loginConfirm = new JPasswordField(15);
        loginPanel.add(loginConfirm);

        JButton registerBtn = new JButton("Registrar");
        registerBtn.addActionListener(e -> {
            String user = loginUser.getText().trim();
            String pass = new String(loginPass.getPassword());
            String confirm = new String(loginConfirm.getPassword());

            if (user.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre de usuario");
            } else if (pass.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres");
            } else if (!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            } else {
                JOptionPane.showMessageDialog(this, "✅ Usuario registrado exitosamente");
                loginUser.setText("");
                loginPass.setText("");
                loginConfirm.setText("");
            }
        });
        loginPanel.add(new JLabel(""));
        loginPanel.add(registerBtn);

        passwordSection.add(loginPanel, BorderLayout.CENTER);

        // Spinner practice
        JPanel spinnerSection = new JPanel(new BorderLayout());
        spinnerSection.setBorder(BorderFactory.createTitledBorder("Práctica: Configuración de Sistema"));

        JPanel configPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        // Volume setting
        configPanel.add(new JLabel("Volumen (0-100):"));
        JSpinner volumeSpinner = new JSpinner(new SpinnerNumberModel(50, 0, 100, 5));
        configPanel.add(volumeSpinner);

        // Font size
        configPanel.add(new JLabel("Tamaño de fuente (8-24):"));
        JSpinner fontSpinner = new JSpinner(new SpinnerNumberModel(12, 8, 24, 1));
        configPanel.add(fontSpinner);

        // Timeout
        configPanel.add(new JLabel("Tiempo de espera (segundos):"));
        JSpinner timeoutSpinner = new JSpinner(new SpinnerNumberModel(30, 10, 300, 10));
        configPanel.add(timeoutSpinner);

        // Date setting
        configPanel.add(new JLabel("Fecha límite:"));
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        configPanel.add(dateSpinner);

        JButton applyBtn = new JButton("Aplicar Configuración");
        applyBtn.addActionListener(e -> {
            String config = String.format(
                    "Configuración aplicada:\n" +
                            "• Volumen: %d%%\n" +
                            "• Tamaño de fuente: %dpt\n" +
                            "• Tiempo de espera: %d segundos\n" +
                            "• Fecha límite: %s",
                    volumeSpinner.getValue(),
                    fontSpinner.getValue(),
                    timeoutSpinner.getValue(),
                    dateSpinner.getValue().toString());
            JOptionPane.showMessageDialog(this, config);
        });
        configPanel.add(new JLabel(""));
        configPanel.add(applyBtn);

        spinnerSection.add(configPanel, BorderLayout.CENTER);

        // Formatted fields practice
        JPanel formatSection = new JPanel(new BorderLayout());
        formatSection.setBorder(BorderFactory.createTitledBorder("Práctica: Formularios con Validación"));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        // Currency
        formPanel.add(new JLabel("Precio del producto:"));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        JFormattedTextField priceField = new JFormattedTextField(currencyFormat);
        priceField.setValue(29.99);
        formPanel.add(priceField);

        // Percentage
        formPanel.add(new JLabel("Descuento (%):"));
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        JFormattedTextField discountField = new JFormattedTextField(percentFormat);
        discountField.setValue(0.15);
        formPanel.add(discountField);

        // Phone
        formPanel.add(new JLabel("Teléfono:"));
        try {
            MaskFormatter phoneMask = new MaskFormatter("(###) ###-####");
            phoneMask.setPlaceholderCharacter('_');
            JFormattedTextField phoneField = new JFormattedTextField(phoneMask);
            phoneField.setText("(555) 123-4567");
            formPanel.add(phoneField);
        } catch (ParseException e) {
            JTextField phoneField = new JTextField("(555) 123-4567");
            formPanel.add(phoneField);
        }

        // Date
        formPanel.add(new JLabel("Fecha de nacimiento:"));
        JFormattedTextField birthDateField = new JFormattedTextField(DateFormat.getDateInstance());
        birthDateField.setText("15/08/1990");
        formPanel.add(birthDateField);

        JButton submitBtn = new JButton("Enviar Formulario");
        submitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "✅ Formulario enviado correctamente\n\n" +
                            "Los datos han sido validados y formateados automáticamente.");
        });
        formPanel.add(new JLabel(""));
        formPanel.add(submitBtn);

        formatSection.add(formPanel, BorderLayout.CENTER);

        practicePanel.add(passwordSection);
        practicePanel.add(spinnerSection);
        practicePanel.add(formatSection);

        JScrollPane scrollPane = new JScrollPane(practicePanel);
        panel.add(scrollPane, BorderLayout.CENTER);

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes Especializados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText(
                "Evalúa tus conocimientos sobre JPasswordField, JSpinner, JFormattedTextField y JToggleButton.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Componentes Especializados");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Otros", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA COMPONENTES ESPECIALIZADOS\n\n" +
                "✅ USOS RECOMENDADOS:\n" +
                "• JPasswordField: Login, cambio de contraseña, datos sensibles\n" +
                "• JSpinner: Cantidades, edades, configuraciones numéricas\n" +
                "• JFormattedTextField: Fechas, teléfonos, precios, códigos\n" +
                "• JToggleButton: Herramientas (negrita, cursiva), modos (día/noche)\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Siempre usar getPassword() en lugar de getText() para contraseñas\n" +
                "• Limpiar arrays de char[] después de usar contraseñas\n" +
                "• Configurar límites apropiados en JSpinner\n" +
                "• Usar MaskFormatter para datos con formato fijo\n" +
                "• Proporcionar feedback visual para validación\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• Usar getText() con JPasswordField → vulnerabilidad de seguridad\n" +
                "• No configurar límites en JSpinner → valores inválidos\n" +
                "• Olvidar setPlaceholderCharacter en MaskFormatter → confusión\n" +
                "• No manejar ParseException en JFormattedTextField\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Usar DocumentListener para validación en tiempo real\n" +
                "• Implementar InputVerifier para validación avanzada\n" +
                "• Cachear Format objects para mejor rendimiento\n" +
                "• Usar SwingWorker para validaciones que toman tiempo\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para contraseñas: mostrar/ocultar con JToggleButton\n" +
                "• Para fechas: usar JXDatePicker de SwingX para mejor UX\n" +
                "• Para números: combinar JFormattedTextField con JSpinner\n" +
                "• Para internacionalización: usar formatos locales apropiados\n\n" +
                "🔒 SEGURIDAD:\n" +
                "• Nunca almacenar contraseñas como String\n" +
                "• Limpiar arrays de caracteres inmediatamente\n" +
                "• Usar hashing para almacenamiento de contraseñas\n" +
                "• Validar entrada tanto en cliente como servidor\n\n" +
                "🎨 PERSONALIZACIÓN:\n" +
                "• Cambiar echoChar en JPasswordField\n" +
                "• Personalizar colores de validación en JFormattedTextField\n" +
                "• Crear SpinnerModel personalizado para datos complejos\n" +
                "• Usar íconos en JToggleButton para mejor UX");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
