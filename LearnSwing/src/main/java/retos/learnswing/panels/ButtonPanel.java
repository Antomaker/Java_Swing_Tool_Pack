/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;

import javax.swing.*;

import retos.learnswing.auth.UserManager;


/**
 * Enhanced ButtonPanel with detailed explanations, examples, and interactive components.
 *
 * @author jocde
 */
public class ButtonPanel extends JPanel {

    /**
     * Constructor for ButtonPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public ButtonPanel() {
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

        theoryText.setText("🎯 COMPONENTES DE BOTONES EN JAVA SWING\n\n" +
                "Los componentes de botones permiten al usuario interactuar y hacer selecciones.\n\n" +
                "🔘 JButton - Botón de Acción:\n" +
                "• Ejecuta una acción cuando se hace clic\n" +
                "• Puede tener texto, íconos o ambos\n" +
                "• Soporta ActionListener para manejar eventos\n" +
                "• Estados: normal, presionado, deshabilitado\n\n" +
                "☑️ JCheckBox - Casilla de Verificación:\n" +
                "• Para selecciones múltiples independientes\n" +
                "• Puede estar marcado o desmarcado\n" +
                "• Útil para opciones que no se excluyen mutuamente\n" +
                "• Método importante: isSelected()\n\n" +
                "🔘 JRadioButton - Botón de Opción:\n" +
                "• Para selecciones mutuamente exclusivas\n" +
                "• Debe agruparse con ButtonGroup\n" +
                "• Solo uno puede estar seleccionado por grupo\n" +
                "• Perfecto para opciones como tamaño, color, etc.\n\n" +
                "🎛️ JToggleButton - Botón Conmutable:\n" +
                "• Mantiene estado presionado/soltado\n" +
                "• Como un checkbox pero con apariencia de botón\n" +
                "• Útil para modos on/off\n\n" +
                "✨ Características Comunes:\n" +
                "• Todos heredan de AbstractButton\n" +
                "• Soporte para ActionListener e ItemListener\n" +
                "• Personalización de apariencia (íconos, colores)\n" +
                "• Estados: seleccionado, presionado, rollover, deshabilitado");

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

        // Example 1: JButton
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JButton con ActionListener"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// Crear JButton con ActionListener\n" +
                "JButton button = new JButton(\"Haz Clic\");\n" +
                "button.addActionListener(new ActionListener() {\n" +
                "    @Override\n" +
                "    public void actionPerformed(ActionEvent e) {\n" +
                "        // Código a ejecutar\n" +
                "        JOptionPane.showMessageDialog(null, \"¡Clic!\");\n" +
                "    }\n" +
                "});\n" +
                "\n" +
                "// O usando lambda (Java 8+)\n" +
                "button.addActionListener(e -> \n" +
                "    JOptionPane.showMessageDialog(null, \"¡Clic!\"));");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton demoButton = new JButton("¡Haz Clic Aquí!");
        demoButton.setBackground(Color.CYAN);
        demoButton.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "¡Botón presionado!", "Demo JButton", JOptionPane.INFORMATION_MESSAGE));
        buttonPanel.add(demoButton);

        JButton disableBtn = new JButton("Deshabilitar");
        disableBtn.addActionListener(e -> {
            demoButton.setEnabled(!demoButton.isEnabled());
            disableBtn.setText(demoButton.isEnabled() ? "Deshabilitar" : "Habilitar");
        });
        buttonPanel.add(disableBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(buttonPanel, BorderLayout.CENTER);

        // Example 2: JCheckBox and JRadioButton
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: JCheckBox y JRadioButton"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// JCheckBox - selección múltiple\n" +
                "JCheckBox check1 = new JCheckBox(\"Opción A\");\n" +
                "JCheckBox check2 = new JCheckBox(\"Opción B\");\n" +
                "\n" +
                "// JRadioButton - selección exclusiva\n" +
                "ButtonGroup group = new ButtonGroup();\n" +
                "JRadioButton radio1 = new JRadioButton(\"Tamaño S\");\n" +
                "JRadioButton radio2 = new JRadioButton(\"Tamaño M\");\n" +
                "JRadioButton radio3 = new JRadioButton(\"Tamaño L\");\n" +
                "group.add(radio1);\n" +
                "group.add(radio2);\n" +
                "group.add(radio3);");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel selectionPanel = new JPanel(new GridLayout(2, 1));

        // Checkboxes
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkPanel.add(new JLabel("Preferencias:"));
        JCheckBox check1 = new JCheckBox("Notificaciones");
        JCheckBox check2 = new JCheckBox("Recordar sesión");
        checkPanel.add(check1);
        checkPanel.add(check2);

        // Radio buttons
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(new JLabel("Tamaño:"));
        ButtonGroup sizeGroup = new ButtonGroup();
        JRadioButton small = new JRadioButton("Pequeño");
        JRadioButton medium = new JRadioButton("Mediano", true);
        JRadioButton large = new JRadioButton("Grande");
        sizeGroup.add(small);
        sizeGroup.add(medium);
        sizeGroup.add(large);
        radioPanel.add(small);
        radioPanel.add(medium);
        radioPanel.add(large);

        selectionPanel.add(checkPanel);
        selectionPanel.add(radioPanel);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(selectionPanel, BorderLayout.CENTER);

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

        JPanel practicePanel = new JPanel(new GridLayout(5, 1, 10, 10));

        // Checkboxes section
        JPanel checkSection = new JPanel(new BorderLayout());
        checkSection.setBorder(BorderFactory.createTitledBorder("JCheckBox - Selección Múltiple"));
        checkSection.add(new JLabel("Selecciona tus hobbies:"), BorderLayout.NORTH);

        JPanel checkPanel = new JPanel(new GridLayout(2, 2));
        JCheckBox hobby1 = new JCheckBox("Deportes");
        JCheckBox hobby2 = new JCheckBox("Música");
        JCheckBox hobby3 = new JCheckBox("Lectura");
        JCheckBox hobby4 = new JCheckBox("Viajes");
        checkPanel.add(hobby1);
        checkPanel.add(hobby2);
        checkPanel.add(hobby3);
        checkPanel.add(hobby4);
        checkSection.add(checkPanel, BorderLayout.CENTER);

        // Radio buttons section
        JPanel radioSection = new JPanel(new BorderLayout());
        radioSection.setBorder(BorderFactory.createTitledBorder("JRadioButton - Selección Exclusiva"));
        radioSection.add(new JLabel("Selecciona tu nivel de experiencia:"), BorderLayout.NORTH);

        JPanel radioPanel = new JPanel(new FlowLayout());
        ButtonGroup experienceGroup = new ButtonGroup();
        JRadioButton beginner = new JRadioButton("Principiante");
        JRadioButton intermediate = new JRadioButton("Intermedio");
        JRadioButton advanced = new JRadioButton("Avanzado");
        experienceGroup.add(beginner);
        experienceGroup.add(intermediate);
        experienceGroup.add(advanced);
        radioPanel.add(beginner);
        radioPanel.add(intermediate);
        radioPanel.add(advanced);
        radioSection.add(radioPanel, BorderLayout.CENTER);

        // Toggle button section
        JPanel toggleSection = new JPanel(new BorderLayout());
        toggleSection.setBorder(BorderFactory.createTitledBorder("JToggleButton - Estado Conmutable"));
        toggleSection.add(new JLabel("Modo oscuro:"), BorderLayout.NORTH);

        JPanel togglePanel = new JPanel(new FlowLayout());
        JToggleButton darkMode = new JToggleButton("Modo Oscuro");
        darkMode.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Modo " + (darkMode.isSelected() ? "oscuro" : "claro") + " activado"));
        togglePanel.add(darkMode);
        toggleSection.add(togglePanel, BorderLayout.CENTER);

        // Action buttons
        JPanel buttonSection = new JPanel(new BorderLayout());
        buttonSection.setBorder(BorderFactory.createTitledBorder("JButton - Acciones"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton showSelectionsBtn = new JButton("Mostrar Selecciones");
        showSelectionsBtn.addActionListener(e -> {
            StringBuilder message = new StringBuilder();
            message.append("Hobbies seleccionados:\n");
            if (hobby1.isSelected()) message.append("• Deportes\n");
            if (hobby2.isSelected()) message.append("• Música\n");
            if (hobby3.isSelected()) message.append("• Lectura\n");
            if (hobby4.isSelected()) message.append("• Viajes\n");

            message.append("\nNivel de experiencia: ");
            if (beginner.isSelected()) message.append("Principiante");
            else if (intermediate.isSelected()) message.append("Intermedio");
            else if (advanced.isSelected()) message.append("Avanzado");
            else message.append("No seleccionado");

            message.append("\n\nModo oscuro: ").append(darkMode.isSelected() ? "Activado" : "Desactivado");

            JOptionPane.showMessageDialog(this, message.toString(), "Selecciones", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(showSelectionsBtn);

        JButton resetBtn = new JButton("Reiniciar");
        resetBtn.addActionListener(e -> {
            hobby1.setSelected(false);
            hobby2.setSelected(false);
            hobby3.setSelected(false);
            hobby4.setSelected(false);
            experienceGroup.clearSelection();
            darkMode.setSelected(false);
        });
        buttonPanel.add(resetBtn);

        buttonSection.add(buttonPanel, BorderLayout.CENTER);

        practicePanel.add(checkSection);
        practicePanel.add(radioSection);
        practicePanel.add(toggleSection);
        practicePanel.add(buttonSection);

        JScrollPane scrollPane = new JScrollPane(practicePanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the source code panel with complete implementations.
     */
    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane codeTabs = new JTabbedPane();

        // JButton example
        JTextArea buttonCode = new JTextArea();
        buttonCode.setEditable(false);
        buttonCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        buttonCode.setBackground(Color.BLACK);
        buttonCode.setForeground(Color.GREEN);
        buttonCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class ButtonExample extends JPanel {\n" +
                "    private JButton actionButton;\n" +
                "    private JButton toggleButton;\n" +
                "    private int clickCount;\n" +
                "    \n" +
                "    public ButtonExample() {\n" +
                "        setLayout(new FlowLayout());\n" +
                "        clickCount = 0;\n" +
                "        \n" +
                "        // JButton básico\n" +
                "        actionButton = new JButton(\"Haz Clic\");\n" +
                "        actionButton.setToolTipText(\"Botón de acción\");\n" +
                "        actionButton.setFont(new Font(\"Arial\", Font.BOLD, 14));\n" +
                "        actionButton.setForeground(Color.BLUE);\n" +
                "        \n" +
                "        // Configurar mnemonic (Alt + C)\n" +
                "        actionButton.setMnemonic(KeyEvent.VK_C);\n" +
                "        \n" +
                "        // Agregar ActionListener\n" +
                "        actionButton.addActionListener(new ActionListener() {\n" +
                "            @Override\n" +
                "            public void actionPerformed(ActionEvent e) {\n" +
                "                clickCount++;\n" +
                "                actionButton.setText(\"Clic #\" + clickCount);\n" +
                "                \n" +
                "                if (clickCount % 5 == 0) {\n" +
                "                    JOptionPane.showMessageDialog(ButtonExample.this,\n" +
                "                        \"¡Has hecho \" + clickCount + \" clics!\");\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        // JButton con ícono\n" +
                "        toggleButton = new JButton(\"Alternar Estado\");\n" +
                "        toggleButton.setBackground(Color.LIGHT_GRAY);\n" +
                "        \n" +
                "        toggleButton.addActionListener(e -> {\n" +
                "            boolean isActive = toggleButton.getText().contains(\"ACTIVO\");\n" +
                "            if (isActive) {\n" +
                "                toggleButton.setText(\"Alternar Estado\");\n" +
                "                toggleButton.setBackground(Color.LIGHT_GRAY);\n" +
                "            } else {\n" +
                "                toggleButton.setText(\"ACTIVO\");\n" +
                "                toggleButton.setBackground(Color.GREEN);\n" +
                "                toggleButton.setForeground(Color.WHITE);\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        add(actionButton);\n" +
                "        add(toggleButton);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public void setButtonText(String text) {\n" +
                "        actionButton.setText(text);\n" +
                "    }\n" +
                "    \n" +
                "    public void setButtonEnabled(boolean enabled) {\n" +
                "        actionButton.setEnabled(enabled);\n" +
                "    }\n" +
                "    \n" +
                "    public void resetCounter() {\n" +
                "        clickCount = 0;\n" +
                "        actionButton.setText(\"Haz Clic\");\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JButton", new JScrollPane(buttonCode));

        // JCheckBox example
        JTextArea checkBoxCode = new JTextArea();
        checkBoxCode.setEditable(false);
        checkBoxCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        checkBoxCode.setBackground(Color.BLACK);
        checkBoxCode.setForeground(Color.GREEN);
        checkBoxCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class CheckBoxExample extends JPanel {\n" +
                "    private List<JCheckBox> optionBoxes;\n" +
                "    private JButton showSelectionButton;\n" +
                "    \n" +
                "    public CheckBoxExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        optionBoxes = new ArrayList<>();\n" +
                "        \n" +
                "        // Panel de opciones\n" +
                "        JPanel optionsPanel = new JPanel(new GridLayout(0, 1, 5, 5));\n" +
                "        optionsPanel.setBorder(BorderFactory.createTitledBorder(\"Opciones\"));\n" +
                "        \n" +
                "        String[] options = {\"Opción A\", \"Opción B\", \"Opción C\", \"Opción D\"};\n" +
                "        \n" +
                "        for (String option : options) {\n" +
                "            JCheckBox checkBox = new JCheckBox(option);\n" +
                "            checkBox.setFont(new Font(\"Arial\", Font.PLAIN, 12));\n" +
                "            \n" +
                "            // Agregar ItemListener para respuesta inmediata\n" +
                "            checkBox.addItemListener(e -> {\n" +
                "                boolean selected = checkBox.isSelected();\n" +
                "                System.out.println(option + \" \" + (selected ? \"seleccionada\" : \"deseleccionada\"));\n" +
                "            });\n" +
                "            \n" +
                "            optionBoxes.add(checkBox);\n" +
                "            optionsPanel.add(checkBox);\n" +
                "        }\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        showSelectionButton = new JButton(\"Mostrar Selección\");\n" +
                "        showSelectionButton.addActionListener(e -> {\n" +
                "            List<String> selected = new ArrayList<>();\n" +
                "            for (JCheckBox checkBox : optionBoxes) {\n" +
                "                if (checkBox.isSelected()) {\n" +
                "                    selected.add(checkBox.getText());\n" +
                "                }\n" +
                "            }\n" +
                "            \n" +
                "            if (selected.isEmpty()) {\n" +
                "                JOptionPane.showMessageDialog(this, \"No hay opciones seleccionadas.\");\n" +
                "            } else {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Opciones seleccionadas:\\n\" + String.join(\"\\n\", selected));\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        JButton selectAllButton = new JButton(\"Seleccionar Todo\");\n" +
                "        selectAllButton.addActionListener(e -> {\n" +
                "            boolean allSelected = optionBoxes.stream().allMatch(JCheckBox::isSelected);\n" +
                "            optionBoxes.forEach(box -> box.setSelected(!allSelected));\n" +
                "        });\n" +
                "        \n" +
                "        JButton clearAllButton = new JButton(\"Limpiar Todo\");\n" +
                "        clearAllButton.addActionListener(e -> \n" +
                "            optionBoxes.forEach(box -> box.setSelected(false)));\n" +
                "        \n" +
                "        controlPanel.add(showSelectionButton);\n" +
                "        controlPanel.add(selectAllButton);\n" +
                "        controlPanel.add(clearAllButton);\n" +
                "        \n" +
                "        add(optionsPanel, BorderLayout.CENTER);\n" +
                "        add(controlPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public List<String> getSelectedOptions() {\n" +
                "        List<String> selected = new ArrayList<>();\n" +
                "        for (JCheckBox checkBox : optionBoxes) {\n" +
                "            if (checkBox.isSelected()) {\n" +
                "                selected.add(checkBox.getText());\n" +
                "            }\n" +
                "        }\n" +
                "        return selected;\n" +
                "    }\n" +
                "    \n" +
                "    public void setSelectedOptions(List<String> options) {\n" +
                "        for (JCheckBox checkBox : optionBoxes) {\n" +
                "            checkBox.setSelected(options.contains(checkBox.getText()));\n" +
                "        }\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JCheckBox", new JScrollPane(checkBoxCode));

        // JRadioButton example
        JTextArea radioCode = new JTextArea();
        radioCode.setEditable(false);
        radioCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        radioCode.setBackground(Color.BLACK);
        radioCode.setForeground(Color.GREEN);
        radioCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class RadioButtonExample extends JPanel {\n" +
                "    private ButtonGroup sizeGroup;\n" +
                "    private ButtonGroup colorGroup;\n" +
                "    private JButton applyButton;\n" +
                "    \n" +
                "    public RadioButtonExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Panel de tamaños\n" +
                "        JPanel sizePanel = new JPanel(new GridLayout(0, 1, 5, 5));\n" +
                "        sizePanel.setBorder(BorderFactory.createTitledBorder(\"Tamaño\"));\n" +
                "        \n" +
                "        sizeGroup = new ButtonGroup();\n" +
                "        String[] sizes = {\"Pequeño\", \"Mediano\", \"Grande\", \"Extra Grande\"};\n" +
                "        \n" +
                "        for (String size : sizes) {\n" +
                "            JRadioButton radio = new JRadioButton(size);\n" +
                "            radio.setActionCommand(size);\n" +
                "            sizeGroup.add(radio);\n" +
                "            sizePanel.add(radio);\n" +
                "            \n" +
                "            // Seleccionar el primero por defecto\n" +
                "            if (size.equals(\"Mediano\")) {\n" +
                "                radio.setSelected(true);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // Panel de colores\n" +
                "        JPanel colorPanel = new JPanel(new GridLayout(0, 1, 5, 5));\n" +
                "        colorPanel.setBorder(BorderFactory.createTitledBorder(\"Color\"));\n" +
                "        \n" +
                "        colorGroup = new ButtonGroup();\n" +
                "        String[] colors = {\"Rojo\", \"Verde\", \"Azul\", \"Amarillo\"};\n" +
                "        Color[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};\n" +
                "        \n" +
                "        for (int i = 0; i < colors.length; i++) {\n" +
                "            JRadioButton radio = new JRadioButton(colors[i]);\n" +
                "            radio.setActionCommand(colors[i]);\n" +
                "            radio.setForeground(colorValues[i]);\n" +
                "            colorGroup.add(radio);\n" +
                "            colorPanel.add(radio);\n" +
                "            \n" +
                "            if (colors[i].equals(\"Azul\")) {\n" +
                "                radio.setSelected(true);\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        // Panel de opciones\n" +
                "        JPanel optionsPanel = new JPanel(new GridLayout(1, 2, 10, 10));\n" +
                "        optionsPanel.add(sizePanel);\n" +
                "        optionsPanel.add(colorPanel);\n" +
                "        \n" +
                "        // Panel de resultado\n" +
                "        JPanel resultPanel = new JPanel(new BorderLayout());\n" +
                "        resultPanel.setBorder(BorderFactory.createTitledBorder(\"Resultado\"));\n" +
                "        \n" +
                "        JTextArea resultArea = new JTextArea(3, 30);\n" +
                "        resultArea.setEditable(false);\n" +
                "        resultArea.setBackground(Color.LIGHT_GRAY);\n" +
                "        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Botón aplicar\n" +
                "        applyButton = new JButton(\"Aplicar Selección\");\n" +
                "        applyButton.addActionListener(e -> {\n" +
                "            String size = sizeGroup.getSelection() != null ?\n" +
                "                sizeGroup.getSelection().getActionCommand() : \"Ninguno\";\n" +
                "            String color = colorGroup.getSelection() != null ?\n" +
                "                colorGroup.getSelection().getActionCommand() : \"Ninguno\";\n" +
                "            \n" +
                "            resultArea.setText(\"Configuración seleccionada:\\n\" +\n" +
                "                \"Tamaño: \" + size + \"\\n\" +\n" +
                "                \"Color: \" + color);\n" +
                "        });\n" +
                "        resultPanel.add(applyButton, BorderLayout.SOUTH);\n" +
                "        \n" +
                "        add(optionsPanel, BorderLayout.CENTER);\n" +
                "        add(resultPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public String getSelectedSize() {\n" +
                "        return sizeGroup.getSelection() != null ?\n" +
                "            sizeGroup.getSelection().getActionCommand() : null;\n" +
                "    }\n" +
                "    \n" +
                "    public String getSelectedColor() {\n" +
                "        return colorGroup.getSelection() != null ?\n" +
                "            colorGroup.getSelection().getActionCommand() : null;\n" +
                "    }\n" +
                "    \n" +
                "    public void resetSelection() {\n" +
                "        sizeGroup.clearSelection();\n" +
                "        colorGroup.clearSelection();\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JRadioButton", new JScrollPane(radioCode));

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes de Botones", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre JButton, JCheckBox, JRadioButton y JToggleButton.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Componentes de Botones");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Botones", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA BOTONES\n\n" +
                "✅ CUÁNDO USAR CADA COMPONENTE:\n" +
                "• JButton: Para ejecutar acciones (guardar, enviar, cancelar)\n" +
                "• JCheckBox: Opciones independientes (preferencias, filtros)\n" +
                "• JRadioButton: Opciones mutuamente exclusivas (tamaño, color)\n" +
                "• JToggleButton: Estados on/off (modo oscuro, vista previa)\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Siempre usa ButtonGroup con JRadioButton\n" +
                "• Proporciona texto descriptivo claro\n" +
                "• Considera accesibilidad (mnemonics, tooltips)\n" +
                "• Usa íconos para mejorar UX\n" +
                "• Agrupa botones relacionados visualmente\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• Olvidar ButtonGroup → múltiples radios seleccionados\n" +
                "• No manejar eventos → botones sin funcionalidad\n" +
                "• Texto demasiado largo → botones difíciles de leer\n" +
                "• No deshabilitar botones innecesarios → confusión\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Usa Action para compartir comportamiento\n" +
                "• Implementa Key Bindings para accesibilidad\n" +
                "• Considera ButtonModel para control avanzado\n" +
                "• Usa SwingWorker para acciones que toman tiempo\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para formularios: valida antes de procesar\n" +
                "• Para navegación: usa íconos + texto\n" +
                "• Para estados: cambia apariencia según estado\n" +
                "• Para internacionalización: externaliza textos\n\n" +
                "🎨 PERSONALIZACIÓN:\n" +
                "• Cambia colores según tema de la aplicación\n" +
                "• Usa bordes para agrupar botones relacionados\n" +
                "• Considera efectos hover y focus\n" +
                "• Adapta tamaño según importancia");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}