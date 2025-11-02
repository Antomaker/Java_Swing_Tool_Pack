/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import retos.learnswing.auth.UserManager;

/**
 *
 * @author jocde
 */

public class CodeViewPanel extends JPanel {
    private String lessonName;
    private JTextArea codeArea;

    /**
     * Constructor for CodeViewPanel.
     * 
     * @param lessonName The name of the lesson to display code for
     */
    public CodeViewPanel(String lessonName) {
        this.lessonName = lessonName;
        initializeComponents();
    }

    /**
     * Initializes the code view components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Title
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Código Fuente - " + lessonName));
        add(titlePanel, BorderLayout.NORTH);

        // Code display area
        codeArea = new JTextArea();
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setEditable(false);
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText(getCodeForLesson(lessonName));

        JScrollPane scrollPane = new JScrollPane(codeArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton copyButton = new JButton("Copiar Código");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copyCodeToClipboard();
            }
        });
        buttonPanel.add(copyButton);

        JButton markInterestedButton = new JButton("Marcar como Interesante");
        markInterestedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager.updateLessonProgress(lessonName, "interested");
                JOptionPane.showMessageDialog(CodeViewPanel.this,
                        "Lección marcada como interesante.");
            }
        });
        buttonPanel.add(markInterestedButton);

        JButton markCompletedButton = new JButton("Marcar como Completada");
        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager.updateLessonProgress(lessonName, "completed");
                JOptionPane.showMessageDialog(CodeViewPanel.this,
                        "Lección marcada como completada.");
            }
        });
        buttonPanel.add(markCompletedButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Copies the current code to the system clipboard.
     */
    private void copyCodeToClipboard() {
        String code = codeArea.getText();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Código copiado al portapapeles.");
    }

    /**
     * Gets the source code for a specific lesson.
     * 
     * @param lessonName The name of the lesson
     * @return The source code as a string
     */
    private String getCodeForLesson(String lessonName) {
        switch (lessonName) {
            case "Texto":
                return getTextPanelCode();
            case "Botones":
                return getButtonPanelCode();
            case "Selección":
                return getSelectionPanelCode();
            case "Avanzados":
                return getAdvancedPanelCode();
            case "Otros":
                return getOtherPanelCode();
            case "Adicionales":
                return getAdditionalPanelCode();
            default:
                return "// Código no disponible";
        }
    }

    /**
     * Gets the code for TextPanel.
     * 
     * @return The TextPanel code
     */
    private String getTextPanelCode() {
        return "import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class TextPanel extends JPanel {\n" +
                "    public TextPanel() {\n" +
                "        setLayout(new GridLayout(3, 2, 10, 10));\n" +
                "        \n" +
                "        // JTextField - Campo de texto de una línea\n" +
                "        add(new JLabel(\"Campo de Texto:\"));\n" +
                "        JTextField textField = new JTextField(20);\n" +
                "        add(textField);\n" +
                "        \n" +
                "        // JTextArea - Área de texto multi-línea\n" +
                "        add(new JLabel(\"Área de Texto:\"));\n" +
                "        JTextArea textArea = new JTextArea(5, 20);\n" +
                "        JScrollPane scrollPane = new JScrollPane(textArea);\n" +
                "        add(scrollPane);\n" +
                "        \n" +
                "        // JButton - Botón para mostrar el texto\n" +
                "        JButton showButton = new JButton(\"Mostrar Texto\");\n" +
                "        showButton.addActionListener(e -> {\n" +
                "            String text = textField.getText() + \"\\n\" + textArea.getText();\n" +
                "            JOptionPane.showMessageDialog(this, \"Texto: \" + text);\n" +
                "        });\n" +
                "        add(showButton);\n" +
                "    }\n" +
                "}";
    }

    /**
     * Gets the code for ButtonPanel.
     * 
     * @return The ButtonPanel code
     */
    private String getButtonPanelCode() {
        return "import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class ButtonPanel extends JPanel {\n" +
                "    public ButtonPanel() {\n" +
                "        setLayout(new GridLayout(4, 1, 10, 10));\n" +
                "        \n" +
                "        // JCheckBox - Checkbox para selección binaria\n" +
                "        JCheckBox checkBox = new JCheckBox(\"Opción 1\");\n" +
                "        add(checkBox);\n" +
                "        \n" +
                "        // JRadioButton con ButtonGroup - Botones de radio\n" +
                "        ButtonGroup group = new ButtonGroup();\n" +
                "        JRadioButton radio1 = new JRadioButton(\"Radio 1\");\n" +
                "        JRadioButton radio2 = new JRadioButton(\"Radio 2\");\n" +
                "        group.add(radio1);\n" +
                "        group.add(radio2);\n" +
                "        add(radio1);\n" +
                "        add(radio2);\n" +
                "        \n" +
                "        // JButton - Botón para mostrar selección\n" +
                "        JButton showSelection = new JButton(\"Mostrar Selección\");\n" +
                "        showSelection.addActionListener(e -> {\n" +
                "            String message = \"CheckBox: \" + checkBox.isSelected() + \"\\n\";\n" +
                "            message += \"Radio: \" + (radio1.isSelected() ? \"Radio 1\" : \"Radio 2\");\n" +
                "            JOptionPane.showMessageDialog(this, message);\n" +
                "        });\n" +
                "        add(showSelection);\n" +
                "    }\n" +
                "}";
    }

    /**
     * Gets the code for SelectionPanel.
     * 
     * @return The SelectionPanel code
     */
    private String getSelectionPanelCode() {
        return "import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class SelectionPanel extends JPanel {\n" +
                "    public SelectionPanel() {\n" +
                "        setLayout(new GridLayout(2, 2, 10, 10));\n" +
                "        \n" +
                "        // JComboBox - Lista desplegable\n" +
                "        add(new JLabel(\"ComboBox:\"));\n" +
                "        String[] options = { \"Opción 1\", \"Opción 2\", \"Opción 3\" };\n" +
                "        JComboBox<String> comboBox = new JComboBox<>(options);\n" +
                "        add(comboBox);\n" +
                "        \n" +
                "        // JList - Lista para selección\n" +
                "        add(new JLabel(\"Lista:\"));\n" +
                "        String[] listData = { \"Elemento 1\", \"Elemento 2\", \"Elemento 3\", \"Elemento 4\" };\n" +
                "        JList<String> list = new JList<>(listData);\n" +
                "        JScrollPane listScrollPane = new JScrollPane(list);\n" +
                "        add(listScrollPane);\n" +
                "        \n" +
                "        // JButton - Botón para mostrar selección\n" +
                "        JButton showSelection = new JButton(\"Mostrar Selección\");\n" +
                "        showSelection.addActionListener(e -> {\n" +
                "            String message = \"ComboBox: \" + comboBox.getSelectedItem() + \"\\n\";\n" +
                "            message += \"Lista: \" + list.getSelectedValue();\n" +
                "            JOptionPane.showMessageDialog(this, message);\n" +
                "        });\n" +
                "        add(showSelection);\n" +
                "    }\n" +
                "}";
    }

    /**
     * Gets the code for AdvancedPanel.
     * 
     * @return The AdvancedPanel code
     */
    private String getAdvancedPanelCode() {
        return "import javax.swing.*;\n" +
                "import javax.swing.table.DefaultTableModel;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class AdvancedPanel extends JPanel {\n" +
                "    public AdvancedPanel() {\n" +
                "        setLayout(new GridLayout(3, 1, 10, 10));\n" +
                "        \n" +
                "        // JTable - Tabla para mostrar datos\n" +
                "        String[] columnNames = { \"Nombre\", \"Edad\", \"Ciudad\" };\n" +
                "        Object[][] data = {\n" +
                "                { \"Juan\", 25, \"Madrid\" },\n" +
                "                { \"María\", 30, \"Barcelona\" },\n" +
                "                { \"Pedro\", 35, \"Valencia\" }\n" +
                "        };\n" +
                "        DefaultTableModel model = new DefaultTableModel(data, columnNames);\n" +
                "        JTable table = new JTable(model);\n" +
                "        JScrollPane tableScrollPane = new JScrollPane(table);\n" +
                "        add(tableScrollPane);\n" +
                "        \n" +
                "        // JSlider - Control deslizante para valores\n" +
                "        JSlider slider = new JSlider(0, 100, 50);\n" +
                "        slider.setMajorTickSpacing(20);\n" +
                "        slider.setMinorTickSpacing(5);\n" +
                "        slider.setPaintTicks(true);\n" +
                "        slider.setPaintLabels(true);\n" +
                "        add(slider);\n" +
                "        \n" +
                "        // JProgressBar - Barra de progreso\n" +
                "        JProgressBar progressBar = new JProgressBar(0, 100);\n" +
                "        progressBar.setValue(75);\n" +
                "        progressBar.setStringPainted(true);\n" +
                "        add(progressBar);\n" +
                "    }\n" +
                "}";
    }

    /**
     * Gets the code for OtherPanel.
     * 
     * @return The OtherPanel code
     */
    private String getOtherPanelCode() {
        return "import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.text.DateFormat;\n" +
                "\n" +
                "public class OtherPanel extends JPanel {\n" +
                "    public OtherPanel() {\n" +
                "        setLayout(new GridLayout(4, 2, 10, 10));\n" +
                "        \n" +
                "        // JPasswordField - Campo para contraseñas\n" +
                "        add(new JLabel(\"Contraseña:\"));\n" +
                "        JPasswordField passwordField = new JPasswordField(20);\n" +
                "        add(passwordField);\n" +
                "        \n" +
                "        // JSpinner - Selector numérico\n" +
                "        add(new JLabel(\"Spinner:\"));\n" +
                "        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));\n" +
                "        add(spinner);\n" +
                "        \n" +
                "        // JFormattedTextField - Campo con formato\n" +
                "        add(new JLabel(\"Fecha:\"));\n" +
                "        JFormattedTextField formattedField = new JFormattedTextField(DateFormat.getDateInstance());\n"
                +
                "        add(formattedField);\n" +
                "        \n" +
                "        // JToggleButton - Botón conmutable\n" +
                "        add(new JLabel(\"Toggle:\"));\n" +
                "        JToggleButton toggleButton = new JToggleButton(\"Toggle\");\n" +
                "        add(toggleButton);\n" +
                "        \n" +
                "        // JButton - Botón para mostrar valores\n" +
                "        JButton showButton = new JButton(\"Mostrar Valores\");\n" +
                "        showButton.addActionListener(e -> {\n" +
                "            String message = \"Contraseña: \" + new String(passwordField.getPassword()) + \"\\n\";\n" +
                "            message += \"Spinner: \" + spinner.getValue() + \"\\n\";\n" +
                "            message += \"Fecha: \" + formattedField.getText() + \"\\n\";\n" +
                "            message += \"Toggle: \" + toggleButton.isSelected();\n" +
                "            JOptionPane.showMessageDialog(this, message);\n" +
                "        });\n" +
                "        add(showButton);\n" +
                "    }\n" +
                "}";
    }

    /**
     * Gets the code for AdditionalComponentsPanel.
     * 
     * @return The AdditionalComponentsPanel code
     */
    private String getAdditionalPanelCode() {
        return "import javax.swing.*;\n" +
                "import javax.swing.tree.DefaultMutableTreeNode;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.ActionEvent;\n" +
                "import java.awt.event.ActionListener;\n" +
                "\n" +
                "public class AdditionalComponentsPanel extends JPanel {\n" +
                "    public AdditionalComponentsPanel() {\n" +
                "        setLayout(new GridLayout(4, 1, 10, 10));\n" +
                "        \n" +
                "        // JTree - Componente para datos jerárquicos\n" +
                "        DefaultMutableTreeNode root = new DefaultMutableTreeNode(\"Root\");\n" +
                "        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(\"Child 1\");\n" +
                "        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(\"Child 2\");\n" +
                "        root.add(child1);\n" +
                "        root.add(child2);\n" +
                "        child1.add(new DefaultMutableTreeNode(\"Grandchild 1\"));\n" +
                "        child2.add(new DefaultMutableTreeNode(\"Grandchild 2\"));\n" +
                "        JTree tree = new JTree(root);\n" +
                "        JScrollPane treeScrollPane = new JScrollPane(tree);\n" +
                "        add(treeScrollPane);\n" +
                "        \n" +
                "        // JButton para JFileChooser\n" +
                "        JButton fileChooserButton = new JButton(\"Seleccionar Archivo\");\n" +
                "        fileChooserButton.addActionListener(e -> {\n" +
                "            JFileChooser fileChooser = new JFileChooser();\n" +
                "            int result = fileChooser.showOpenDialog(this);\n" +
                "            if (result == JFileChooser.APPROVE_OPTION) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Archivo seleccionado: \" + fileChooser.getSelectedFile().getName());\n" +
                "            }\n" +
                "        });\n" +
                "        add(fileChooserButton);\n" +
                "        \n" +
                "        // JButton para JColorChooser\n" +
                "        JButton colorChooserButton = new JButton(\"Seleccionar Color\");\n" +
                "        colorChooserButton.addActionListener(e -> {\n" +
                "            Color selectedColor = JColorChooser.showDialog(this,\n" +
                "                \"Seleccionar Color\", Color.BLUE);\n" +
                "            if (selectedColor != null) {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Color seleccionado: \" + selectedColor.toString());\n" +
                "            }\n" +
                "        });\n" +
                "        add(colorChooserButton);\n" +
                "        \n" +
                "        // JInternalFrame example\n" +
                "        JButton internalFrameButton = new JButton(\"Mostrar Internal Frame\");\n" +
                "        internalFrameButton.addActionListener(e -> {\n" +
                "            JInternalFrame internalFrame = new JInternalFrame(\"Internal Frame\", true, true, true, true);\n"
                +
                "            internalFrame.setSize(200, 150);\n" +
                "            internalFrame.setVisible(true);\n" +
                "            JOptionPane.showMessageDialog(this,\n" +
                "                \"JInternalFrame creado (normalmente usado con JDesktopPane)\");\n" +
                "        });\n" +
                "        add(internalFrameButton);\n" +
                "    }\n" +
                "}";
    }
}