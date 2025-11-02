/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import retos.learnswing.auth.UserManager;


/**
 * Enhanced AdvancedPanel with detailed explanations, examples, and interactive components.
 *
 * @author jocde
 */
public class AdvancedPanel extends JPanel {

    /**
     * Constructor for AdvancedPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public AdvancedPanel() {
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

        theoryText.setText("🎯 COMPONENTES AVANZADOS EN JAVA SWING\n\n" +
                "Los componentes avanzados permiten mostrar datos complejos y proporcionar feedback visual.\n\n" +
                "📊 JTable - Tabla para Datos Tabulares:\n" +
                "• Muestra datos en filas y columnas organizadas\n" +
                "• Soporta ordenamiento, filtrado y edición\n" +
                "• Usa TableModel para gestionar datos\n" +
                "• Altamente personalizable con renderers y editors\n\n" +
                "🎚️ JSlider - Control Deslizante:\n" +
                "• Selección de valores en un rango continuo\n" +
                "• Personalizable con marcas y etiquetas\n" +
                "• Orientación horizontal o vertical\n" +
                "• Soporta ChangeListener para eventos\n\n" +
                "📈 JProgressBar - Barra de Progreso:\n" +
                "• Muestra progreso de tareas largas\n" +
                "• Modos determinado e indeterminado\n" +
                "• Personalizable con colores y texto\n" +
                "• Útil para mantener informado al usuario\n\n" +
                "🎨 JScrollPane - Panel con Scroll:\n" +
                "• Envuelve componentes para hacerlos scrollables\n" +
                "• Barras de scroll automáticas o siempre visibles\n" +
                "• Soporta scroll programático\n" +
                "• Esencial para contenido que excede el tamaño visible\n\n" +
                "✨ Características Avanzadas:\n" +
                "• Modelos de datos separados de la vista\n" +
                "• Renderers personalizados para apariencia\n" +
                "• Editors para edición inline\n" +
                "• Manejo avanzado de eventos y listeners");

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

        // Example 1: JTable with editing
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JTable Editable con Modelo Personalizado"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// Crear JTable con DefaultTableModel\n" +
                "String[] columns = {\"Producto\", \"Precio\", \"Stock\"};\n" +
                "DefaultTableModel model = new DefaultTableModel(columns, 0);\n" +
                "model.addRow(new Object[]{\"Laptop\", 1200.00, 15});\n" +
                "model.addRow(new Object[]{\"Mouse\", 25.50, 50});\n" +
                "\n" +
                "JTable table = new JTable(model);\n" +
                "table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);\n" +
                "JScrollPane scrollPane = new JScrollPane(table);");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        // Interactive table example
        String[] columns = {"Producto", "Precio", "Stock"};
        DefaultTableModel demoModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 1 ? Double.class : column == 2 ? Integer.class : String.class;
            }
        };
        demoModel.addRow(new Object[]{"Laptop", 1200.00, 15});
        demoModel.addRow(new Object[]{"Mouse", 25.50, 50});
        demoModel.addRow(new Object[]{"Teclado", 75.00, 30});

        JTable demoTable = new JTable(demoModel);
        JScrollPane demoScroll = new JScrollPane(demoTable);
        demoScroll.setPreferredSize(new Dimension(400, 100));

        JPanel tableControls = new JPanel(new FlowLayout());
        JButton addRowBtn = new JButton("Agregar Fila");
        addRowBtn.addActionListener(e ->
            demoModel.addRow(new Object[]{"Nuevo Item", 0.0, 0}));
        tableControls.add(addRowBtn);

        JButton removeRowBtn = new JButton("Eliminar Fila");
        removeRowBtn.addActionListener(e -> {
            int selectedRow = demoTable.getSelectedRow();
            if (selectedRow >= 0) {
                demoModel.removeRow(selectedRow);
            }
        });
        tableControls.add(removeRowBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(demoScroll, BorderLayout.CENTER);
        example1.add(tableControls, BorderLayout.SOUTH);

        // Example 2: JSlider and JProgressBar
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: JSlider Controlando JProgressBar"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// JSlider con ChangeListener\n" +
                "JSlider slider = new JSlider(0, 100, 50);\n" +
                "slider.setMajorTickSpacing(25);\n" +
                "slider.setPaintTicks(true);\n" +
                "slider.setPaintLabels(true);\n" +
                "\n" +
                "slider.addChangeListener(e -> {\n" +
                "    int value = slider.getValue();\n" +
                "    progressBar.setValue(value);\n" +
                "});");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel sliderDemo = new JPanel(new BorderLayout());
        JSlider demoSlider = new JSlider(0, 100, 50);
        demoSlider.setMajorTickSpacing(25);
        demoSlider.setMinorTickSpacing(5);
        demoSlider.setPaintTicks(true);
        demoSlider.setPaintLabels(true);

        JProgressBar demoProgress = new JProgressBar(0, 100);
        demoProgress.setStringPainted(true);
        demoProgress.setValue(50);

        // Connect slider to progress bar
        demoSlider.addChangeListener(e -> {
            int value = demoSlider.getValue();
            demoProgress.setValue(value);
            demoProgress.setString(value + "%");
        });

        sliderDemo.add(new JLabel("Control de Volumen:"), BorderLayout.NORTH);
        sliderDemo.add(demoSlider, BorderLayout.CENTER);
        sliderDemo.add(demoProgress, BorderLayout.SOUTH);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(sliderDemo, BorderLayout.CENTER);

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

        // JTable practice
        JPanel tableSection = new JPanel(new BorderLayout());
        tableSection.setBorder(BorderFactory.createTitledBorder("JTable - Gestión de Inventario"));

        String[] tableColumns = {"Producto", "Precio", "Cantidad", "Categoría"};
        DefaultTableModel inventoryModel = new DefaultTableModel(tableColumns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 1: return Double.class;
                    case 2: return Integer.class;
                    default: return String.class;
                }
            }
        };

        // Add sample data
        inventoryModel.addRow(new Object[]{"Laptop Dell", 899.99, 12, "Electrónica"});
        inventoryModel.addRow(new Object[]{"Mouse Logitech", 29.99, 45, "Accesorios"});
        inventoryModel.addRow(new Object[]{"Teclado Mecánico", 129.99, 8, "Accesorios"});

        JTable inventoryTable = new JTable(inventoryModel);
        JScrollPane tableScroll = new JScrollPane(inventoryTable);
        tableScroll.setPreferredSize(new Dimension(500, 120));

        JPanel tableControls = new JPanel(new FlowLayout());
        JButton addProductBtn = new JButton("Agregar Producto");
        addProductBtn.addActionListener(e -> {
            inventoryModel.addRow(new Object[]{"Nuevo Producto", 0.0, 0, "General"});
        });
        tableControls.add(addProductBtn);

        JButton calculateTotalBtn = new JButton("Calcular Total");
        calculateTotalBtn.addActionListener(e -> {
            double total = 0;
            for (int i = 0; i < inventoryModel.getRowCount(); i++) {
                double price = (Double) inventoryModel.getValueAt(i, 1);
                int quantity = (Integer) inventoryModel.getValueAt(i, 2);
                total += price * quantity;
            }
            JOptionPane.showMessageDialog(this, String.format("Valor total del inventario: $%.2f", total));
        });
        tableControls.add(calculateTotalBtn);

        tableSection.add(tableScroll, BorderLayout.CENTER);
        tableSection.add(tableControls, BorderLayout.SOUTH);

        // JSlider practice
        JPanel sliderSection = new JPanel(new BorderLayout());
        sliderSection.setBorder(BorderFactory.createTitledBorder("JSlider - Control de Configuración"));

        JPanel sliderPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        // Volume slider
        JPanel volumePanel = new JPanel(new BorderLayout());
        volumePanel.add(new JLabel("Volumen del Sistema:"), BorderLayout.WEST);
        JSlider volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumePanel.add(volumeSlider, BorderLayout.CENTER);
        sliderPanel.add(volumePanel);

        // Brightness slider
        JPanel brightnessPanel = new JPanel(new BorderLayout());
        brightnessPanel.add(new JLabel("Brillo de Pantalla:"), BorderLayout.WEST);
        JSlider brightnessSlider = new JSlider(0, 100, 75);
        brightnessSlider.setMajorTickSpacing(25);
        brightnessSlider.setPaintTicks(true);
        brightnessSlider.setPaintLabels(true);
        brightnessPanel.add(brightnessSlider, BorderLayout.CENTER);
        sliderPanel.add(brightnessPanel);

        // Progress simulation
        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.add(new JLabel("Progreso de Instalación:"), BorderLayout.WEST);
        JProgressBar installProgress = new JProgressBar(0, 100);
        installProgress.setStringPainted(true);
        installProgress.setValue(0);
        progressPanel.add(installProgress, BorderLayout.CENTER);

        JButton simulateBtn = new JButton("Simular Instalación");
        simulateBtn.addActionListener(e -> {
            Timer timer = new Timer(100, new ActionListener() {
                int progress = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 5;
                    installProgress.setValue(progress);
                    if (progress >= 100) {
                        ((Timer) e.getSource()).stop();
                        JOptionPane.showMessageDialog(AdvancedPanel.this, "¡Instalación completada!");
                    }
                }
            });
            timer.start();
        });
        progressPanel.add(simulateBtn, BorderLayout.EAST);
        sliderPanel.add(progressPanel);

        sliderSection.add(sliderPanel, BorderLayout.CENTER);

        // JScrollPane demonstration
        JPanel scrollSection = new JPanel(new BorderLayout());
        scrollSection.setBorder(BorderFactory.createTitledBorder("JScrollPane - Contenido Largo"));

        JTextArea longText = new JTextArea();
        longText.setText("Este es un ejemplo de contenido largo que requiere scroll.\n\n" +
                "JScrollPane automáticamente agrega barras de scroll cuando el contenido\n" +
                "es más grande que el área visible del componente.\n\n" +
                "Esto es especialmente útil para:\n" +
                "• JTextArea con mucho texto\n" +
                "• JTable con muchas filas\n" +
                "• JPanel con muchos componentes\n" +
                "• Cualquier componente que pueda crecer\n\n" +
                "El JScrollPane puede configurarse para:\n" +
                "• Mostrar barras siempre, nunca, o según sea necesario\n" +
                "• Scroll horizontal y vertical independiente\n" +
                "• Políticas de viewport personalizadas\n\n" +
                "Es uno de los componentes más importantes para crear\n" +
                "interfaces de usuario flexibles y usables.");
        longText.setEditable(false);
        longText.setWrapStyleWord(true);
        longText.setLineWrap(true);

        JScrollPane longTextScroll = new JScrollPane(longText);
        longTextScroll.setPreferredSize(new Dimension(500, 150));
        longTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        longTextScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollSection.add(longTextScroll, BorderLayout.CENTER);

        practicePanel.add(tableSection);
        practicePanel.add(sliderSection);
        practicePanel.add(scrollSection);

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

        // JTable example
        JTextArea tableCode = new JTextArea();
        tableCode.setEditable(false);
        tableCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        tableCode.setBackground(Color.BLACK);
        tableCode.setForeground(Color.GREEN);
        tableCode.setText("import javax.swing.*;\n" +
                "import javax.swing.table.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class TableExample extends JPanel {\n" +
                "    private JTable dataTable;\n" +
                "    private DefaultTableModel tableModel;\n" +
                "    private JButton addRowButton;\n" +
                "    private JButton removeRowButton;\n" +
                "    private JTextField nameField;\n" +
                "    private JSpinner ageSpinner;\n" +
                "    private JComboBox<String> cityCombo;\n" +
                "    \n" +
                "    public TableExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Crear modelo de tabla\n" +
                "        String[] columnNames = {\"Nombre\", \"Edad\", \"Ciudad\", \"Activo\"};\n" +
                "        tableModel = new DefaultTableModel(columnNames, 0) {\n" +
                "            @Override\n" +
                "            public Class<?> getColumnClass(int column) {\n" +
                "                return column == 3 ? Boolean.class : String.class;\n" +
                "            }\n" +
                "            \n" +
                "            @Override\n" +
                "            public boolean isCellEditable(int row, int column) {\n" +
                "                return true; // Todas las celdas editables\n" +
                "            }\n" +
                "        };\n" +
                "        \n" +
                "        // Agregar datos de ejemplo\n" +
                "        tableModel.addRow(new Object[]{\"Juan\", 25, \"Bogotá\", true});\n" +
                "        tableModel.addRow(new Object[]{\"María\", 30, \"Medellín\", false});\n" +
                "        tableModel.addRow(new Object[]{\"Pedro\", 35, \"Cali\", true});\n" +
                "        \n" +
                "        // Crear JTable\n" +
                "        dataTable = new JTable(tableModel);\n" +
                "        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);\n" +
                "        dataTable.setRowHeight(25);\n" +
                "        dataTable.setGridColor(Color.LIGHT_GRAY);\n" +
                "        \n" +
                "        // Configurar renderers y editors\n" +
                "        dataTable.getColumnModel().getColumn(3).setCellRenderer(\n" +
                "            dataTable.getDefaultRenderer(Boolean.class));\n" +
                "        dataTable.getColumnModel().getColumn(3).setCellEditor(\n" +
                "            dataTable.getDefaultEditor(Boolean.class));\n" +
                "        \n" +
                "        // JScrollPane para la tabla\n" +
                "        JScrollPane tableScroll = new JScrollPane(dataTable);\n" +
                "        tableScroll.setPreferredSize(new Dimension(400, 200));\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new GridBagLayout());\n" +
                "        controlPanel.setBorder(BorderFactory.createTitledBorder(\"Agregar Persona\"));\n" +
                "        GridBagConstraints gbc = new GridBagConstraints();\n" +
                "        gbc.insets = new Insets(5, 5, 5, 5);\n" +
                "        \n" +
                "        // Campos de entrada\n" +
                "        gbc.gridx = 0; gbc.gridy = 0;\n" +
                "        controlPanel.add(new JLabel(\"Nombre:\"), gbc);\n" +
                "        gbc.gridx = 1;\n" +
                "        nameField = new JTextField(15);\n" +
                "        controlPanel.add(nameField, gbc);\n" +
                "        \n" +
                "        gbc.gridx = 0; gbc.gridy = 1;\n" +
                "        controlPanel.add(new JLabel(\"Edad:\"), gbc);\n" +
                "        gbc.gridx = 1;\n" +
                "        SpinnerNumberModel ageModel = new SpinnerNumberModel(25, 0, 120, 1);\n" +
                "        ageSpinner = new JSpinner(ageModel);\n" +
                "        controlPanel.add(ageSpinner, gbc);\n" +
                "        \n" +
                "        gbc.gridx = 0; gbc.gridy = 2;\n" +
                "        controlPanel.add(new JLabel(\"Ciudad:\"), gbc);\n" +
                "        gbc.gridx = 1;\n" +
                "        String[] cities = {\"Bogotá\", \"Medellín\", \"Cali\", \"Barranquilla\"};\n" +
                "        cityCombo = new JComboBox<>(cities);\n" +
                "        controlPanel.add(cityCombo, gbc);\n" +
                "        \n" +
                "        // Botones\n" +
                "        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;\n" +
                "        JPanel buttonPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        addRowButton = new JButton(\"Agregar Fila\");\n" +
                "        addRowButton.addActionListener(e -> addRow());\n" +
                "        buttonPanel.add(addRowButton);\n" +
                "        \n" +
                "        removeRowButton = new JButton(\"Eliminar Fila Seleccionada\");\n" +
                "        removeRowButton.addActionListener(e -> removeSelectedRow());\n" +
                "        buttonPanel.add(removeRowButton);\n" +
                "        \n" +
                "        controlPanel.add(buttonPanel, gbc);\n" +
                "        \n" +
                "        // Información de la tabla\n" +
                "        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));\n" +
                "        infoPanel.add(new JLabel(\"Filas: \" + tableModel.getRowCount()));\n" +
                "        infoPanel.add(new JLabel(\" | Columnas: \" + tableModel.getColumnCount()));\n" +
                "        \n" +
                "        // Layout principal\n" +
                "        add(tableScroll, BorderLayout.CENTER);\n" +
                "        add(controlPanel, BorderLayout.EAST);\n" +
                "        add(infoPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    private void addRow() {\n" +
                "        String name = nameField.getText().trim();\n" +
                "        if (name.isEmpty()) {\n" +
                "            JOptionPane.showMessageDialog(this, \"Ingresa un nombre.\");\n" +
                "            return;\n" +
                "        }\n" +
                "        \n" +
                "        int age = (Integer) ageSpinner.getValue();\n" +
                "        String city = (String) cityCombo.getSelectedItem();\n" +
                "        \n" +
                "        tableModel.addRow(new Object[]{name, age, city, true});\n" +
                "        \n" +
                "        // Limpiar campos\n" +
                "        nameField.setText(\"\");\n" +
                "        ageSpinner.setValue(25);\n" +
                "        cityCombo.setSelectedIndex(0);\n" +
                "        \n" +
                "        updateInfo();\n" +
                "    }\n" +
                "    \n" +
                "    private void removeSelectedRow() {\n" +
                "        int selectedRow = dataTable.getSelectedRow();\n" +
                "        if (selectedRow >= 0) {\n" +
                "            tableModel.removeRow(selectedRow);\n" +
                "            updateInfo();\n" +
                "        } else {\n" +
                "            JOptionPane.showMessageDialog(this, \"Selecciona una fila para eliminar.\");\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    private void updateInfo() {\n" +
                "        // Actualizar contador de filas\n" +
                "        Component[] components = ((JPanel) getComponent(2)).getComponents();\n" +
                "        for (Component comp : components) {\n" +
                "            if (comp instanceof JLabel) {\n" +
                "                JLabel label = (JLabel) comp;\n" +
                "                if (label.getText().startsWith(\"Filas:\")) {\n" +
                "                    label.setText(\"Filas: \" + tableModel.getRowCount());\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public void addPerson(String name, int age, String city, boolean active) {\n" +
                "        tableModel.addRow(new Object[]{name, age, city, active});\n" +
                "        updateInfo();\n" +
                "    }\n" +
                "    \n" +
                "    public int getRowCount() {\n" +
                "        return tableModel.getRowCount();\n" +
                "    }\n" +
                "    \n" +
                "    public Object getValueAt(int row, int col) {\n" +
                "        return tableModel.getValueAt(row, col);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JTable", new JScrollPane(tableCode));

        // JSlider example
        JTextArea sliderCode = new JTextArea();
        sliderCode.setEditable(false);
        sliderCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        sliderCode.setBackground(Color.BLACK);
        sliderCode.setForeground(Color.GREEN);
        sliderCode.setText("import javax.swing.*;\n" +
                "import javax.swing.event.*;\n" +
                "import java.awt.*;\n" +
                "\n" +
                "public class SliderExample extends JPanel {\n" +
                "    private JSlider volumeSlider;\n" +
                "    private JSlider brightnessSlider;\n" +
                "    private JProgressBar volumeBar;\n" +
                "    private JPanel colorPanel;\n" +
                "    private JLabel volumeLabel;\n" +
                "    private JLabel brightnessLabel;\n" +
                "    \n" +
                "    public SliderExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new GridBagLayout());\n" +
                "        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));\n" +
                "        GridBagConstraints gbc = new GridBagConstraints();\n" +
                "        gbc.insets = new Insets(10, 10, 10, 10);\n" +
                "        \n" +
                "        // Slider de volumen\n" +
                "        gbc.gridx = 0; gbc.gridy = 0;\n" +
                "        controlPanel.add(new JLabel(\"Volumen:\"), gbc);\n" +
                "        \n" +
                "        gbc.gridx = 1;\n" +
                "        volumeSlider = new JSlider(0, 100, 50);\n" +
                "        volumeSlider.setMajorTickSpacing(25);\n" +
                "        volumeSlider.setMinorTickSpacing(5);\n" +
                "        volumeSlider.setPaintTicks(true);\n" +
                "        volumeSlider.setPaintLabels(true);\n" +
                "        volumeSlider.setToolTipText(\"Ajusta el volumen\");\n" +
                "        controlPanel.add(volumeSlider, gbc);\n" +
                "        \n" +
                "        // Barra de progreso para volumen\n" +
                "        gbc.gridx = 2;\n" +
                "        volumeBar = new JProgressBar(0, 100);\n" +
                "        volumeBar.setValue(50);\n" +
                "        volumeBar.setStringPainted(true);\n" +
                "        volumeBar.setPreferredSize(new Dimension(150, 20));\n" +
                "        controlPanel.add(volumeBar, gbc);\n" +
                "        \n" +
                "        // Slider de brillo\n" +
                "        gbc.gridx = 0; gbc.gridy = 1;\n" +
                "        controlPanel.add(new JLabel(\"Brillo:\"), gbc);\n" +
                "        \n" +
                "        gbc.gridx = 1;\n" +
                "        brightnessSlider = new JSlider(JSlider.VERTICAL, 0, 255, 128);\n" +
                "        brightnessSlider.setMajorTickSpacing(64);\n" +
                "        brightnessSlider.setMinorTickSpacing(16);\n" +
                "        brightnessSlider.setPaintTicks(true);\n" +
                "        brightnessSlider.setPaintLabels(true);\n" +
                "        brightnessSlider.setToolTipText(\"Ajusta el brillo\");\n" +
                "        brightnessSlider.setPreferredSize(new Dimension(100, 150));\n" +
                "        controlPanel.add(brightnessSlider, gbc);\n" +
                "        \n" +
                "        // Panel de color para preview de brillo\n" +
                "        gbc.gridx = 2;\n" +
                "        colorPanel = new JPanel();\n" +
                "        colorPanel.setPreferredSize(new Dimension(100, 100));\n" +
                "        colorPanel.setBackground(new Color(128, 128, 128));\n" +
                "        colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));\n" +
                "        controlPanel.add(colorPanel, gbc);\n" +
                "        \n" +
                "        // Etiquetas de valores\n" +
                "        JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));\n" +
                "        volumeLabel = new JLabel(\"Volumen: 50%\", SwingConstants.CENTER);\n" +
                "        volumeLabel.setFont(new Font(\"Arial\", Font.BOLD, 14));\n" +
                "        labelPanel.add(volumeLabel);\n" +
                "        \n" +
                "        brightnessLabel = new JLabel(\"Brillo: 128\", SwingConstants.CENTER);\n" +
                "        brightnessLabel.setFont(new Font(\"Arial\", Font.BOLD, 14));\n" +
                "        labelPanel.add(brightnessLabel);\n" +
                "        \n" +
                "        // Change listeners\n" +
                "        volumeSlider.addChangeListener(e -> {\n" +
                "            int value = volumeSlider.getValue();\n" +
                "            volumeBar.setValue(value);\n" +
                "            volumeLabel.setText(\"Volumen: \" + value + \"%\");\n" +
                "            \n" +
                "            // Cambiar color según volumen\n" +
                "            if (value < 25) {\n" +
                "                volumeBar.setForeground(Color.RED);\n" +
                "            } else if (value < 75) {\n" +
                "                volumeBar.setForeground(Color.YELLOW);\n" +
                "            } else {\n" +
                "                volumeBar.setForeground(Color.GREEN);\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        brightnessSlider.addChangeListener(e -> {\n" +
                "            int value = brightnessSlider.getValue();\n" +
                "            brightnessLabel.setText(\"Brillo: \" + value);\n" +
                "            \n" +
                "            // Actualizar color del panel\n" +
                "            colorPanel.setBackground(new Color(value, value, value));\n" +
                "        });\n" +
                "        \n" +
                "        // Botones de control\n" +
                "        JPanel buttonPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        JButton resetButton = new JButton(\"Reiniciar\");\n" +
                "        resetButton.addActionListener(e -> {\n" +
                "            volumeSlider.setValue(50);\n" +
                "            brightnessSlider.setValue(128);\n" +
                "        });\n" +
                "        buttonPanel.add(resetButton);\n" +
                "        \n" +
                "        JButton randomButton = new JButton(\"Valores Aleatorios\");\n" +
                "        randomButton.addActionListener(e -> {\n" +
                "            volumeSlider.setValue((int) (Math.random() * 101));\n" +
                "            brightnessSlider.setValue((int) (Math.random() * 256));\n" +
                "        });\n" +
                "        buttonPanel.add(randomButton);\n" +
                "        \n" +
                "        // Layout final\n" +
                "        add(controlPanel, BorderLayout.CENTER);\n" +
                "        add(labelPanel, BorderLayout.SOUTH);\n" +
                "        add(buttonPanel, BorderLayout.NORTH);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public int getVolume() {\n" +
                "        return volumeSlider.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public int getBrightness() {\n" +
                "        return brightnessSlider.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public void setVolume(int volume) {\n" +
                "        volumeSlider.setValue(volume);\n" +
                "    }\n" +
                "    \n" +
                "    public void setBrightness(int brightness) {\n" +
                "        brightnessSlider.setValue(brightness);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JSlider", new JScrollPane(sliderCode));

        // JProgressBar example
        JTextArea progressCode = new JTextArea();
        progressCode.setEditable(false);
        progressCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        progressCode.setBackground(Color.BLACK);
        progressCode.setForeground(Color.GREEN);
        progressCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class ProgressBarExample extends JPanel {\n" +
                "    private JProgressBar progressBar;\n" +
                "    private JProgressBar indeterminateBar;\n" +
                "    private JButton startButton;\n" +
                "    private JButton stopButton;\n" +
                "    private Timer progressTimer;\n" +
                "    private int progressValue;\n" +
                "    \n" +
                "    public ProgressBarExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Panel de barras de progreso\n" +
                "        JPanel progressPanel = new JPanel(new GridLayout(3, 1, 10, 10));\n" +
                "        progressPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));\n" +
                "        \n" +
                "        // Barra de progreso determinada\n" +
                "        JPanel determinatePanel = new JPanel(new BorderLayout());\n" +
                "        determinatePanel.add(new JLabel(\"Progreso Determinado:\"), BorderLayout.NORTH);\n" +
                "        \n" +
                "        progressBar = new JProgressBar(0, 100);\n" +
                "        progressBar.setValue(0);\n" +
                "        progressBar.setStringPainted(true);\n" +
                "        progressBar.setForeground(Color.BLUE);\n" +
                "        progressBar.setPreferredSize(new Dimension(300, 30));\n" +
                "        determinatePanel.add(progressBar, BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Barra de progreso indeterminada\n" +
                "        JPanel indeterminatePanel = new JPanel(new BorderLayout());\n" +
                "        indeterminatePanel.add(new JLabel(\"Progreso Indeterminado:\"), BorderLayout.NORTH);\n" +
                "        \n" +
                "        indeterminateBar = new JProgressBar();\n" +
                "        indeterminateBar.setIndeterminate(true);\n" +
                "        indeterminateBar.setStringPainted(false);\n" +
                "        indeterminateBar.setVisible(false);\n" +
                "        indeterminateBar.setPreferredSize(new Dimension(300, 30));\n" +
                "        indeterminatePanel.add(indeterminateBar, BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Información del progreso\n" +
                "        JPanel infoPanel = new JPanel(new BorderLayout());\n" +
                "        infoPanel.add(new JLabel(\"Estado:\"), BorderLayout.NORTH);\n" +
                "        \n" +
                "        JLabel statusLabel = new JLabel(\"Listo para comenzar\", SwingConstants.CENTER);\n" +
                "        statusLabel.setFont(new Font(\"Arial\", Font.BOLD, 12));\n" +
                "        infoPanel.add(statusLabel, BorderLayout.CENTER);\n" +
                "        \n" +
                "        progressPanel.add(determinatePanel);\n" +
                "        progressPanel.add(indeterminatePanel);\n" +
                "        progressPanel.add(infoPanel);\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        startButton = new JButton(\"Iniciar Progreso\");\n" +
                "        startButton.addActionListener(e -> startProgress());\n" +
                "        controlPanel.add(startButton);\n" +
                "        \n" +
                "        stopButton = new JButton(\"Detener\");\n" +
                "        stopButton.addActionListener(e -> stopProgress());\n" +
                "        stopButton.setEnabled(false);\n" +
                "        controlPanel.add(stopButton);\n" +
                "        \n" +
                "        JButton resetButton = new JButton(\"Reiniciar\");\n" +
                "        resetButton.addActionListener(e -> resetProgress());\n" +
                "        controlPanel.add(resetButton);\n" +
                "        \n" +
                "        // Timer para simular progreso\n" +
                "        progressTimer = new Timer(100, new ActionListener() {\n" +
                "            @Override\n" +
                "            public void actionPerformed(ActionEvent e) {\n" +
                "                progressValue++;\n" +
                "                progressBar.setValue(progressValue);\n" +
                "                \n" +
                "                if (progressValue >= 100) {\n" +
                "                    stopProgress();\n" +
                "                    JOptionPane.showMessageDialog(ProgressBarExample.this,\n" +
                "                        \"¡Proceso completado!\", \"Fin\", JOptionPane.INFORMATION_MESSAGE);\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        add(progressPanel, BorderLayout.CENTER);\n" +
                "        add(controlPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    private void startProgress() {\n" +
                "        if (!progressTimer.isRunning()) {\n" +
                "            progressValue = 0;\n" +
                "            progressBar.setValue(0);\n" +
                "            progressTimer.start();\n" +
                "            startButton.setEnabled(false);\n" +
                "            stopButton.setEnabled(true);\n" +
                "            indeterminateBar.setVisible(true);\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    private void stopProgress() {\n" +
                "        if (progressTimer.isRunning()) {\n" +
                "            progressTimer.stop();\n" +
                "            startButton.setEnabled(true);\n" +
                "            stopButton.setEnabled(false);\n" +
                "            indeterminateBar.setVisible(false);\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    private void resetProgress() {\n" +
                "        stopProgress();\n" +
                "        progressValue = 0;\n" +
                "        progressBar.setValue(0);\n" +
                "        startButton.setEnabled(true);\n" +
                "        indeterminateBar.setVisible(false);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public void setProgress(int value) {\n" +
                "        progressValue = Math.max(0, Math.min(100, value));\n" +
                "        progressBar.setValue(progressValue);\n" +
                "    }\n" +
                "    \n" +
                "    public int getProgress() {\n" +
                "        return progressBar.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public void setIndeterminate(boolean indeterminate) {\n" +
                "        progressBar.setIndeterminate(indeterminate);\n" +
                "        progressBar.setStringPainted(!indeterminate);\n" +
                "    }\n" +
                "    \n" +
                "    public boolean isIndeterminate() {\n" +
                "        return progressBar.isIndeterminate();\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JProgressBar", new JScrollPane(progressCode));

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes Avanzados", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre JTable, JSlider, JProgressBar y JScrollPane.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Componentes Avanzados");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Avanzados", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA COMPONENTES AVANZADOS\n\n" +
                "✅ USOS APROPIADOS:\n" +
                "• JTable: Datos tabulares, inventarios, listados\n" +
                "• JSlider: Configuraciones, controles de volumen/brillo\n" +
                "• JProgressBar: Tareas largas, descargas, instalación\n" +
                "• JScrollPane: Siempre que el contenido pueda crecer\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Usa DefaultTableModel para tablas simples\n" +
                "• Implementa TableModel personalizado para datos complejos\n" +
                "• Configura renderers para tipos de datos específicos\n" +
                "• Usa SwingWorker para actualizar JProgressBar\n" +
                "• JScrollPane es esencial para UX buena\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• No usar JScrollPane con JTable → datos cortados\n" +
                "• Actualizar JProgressBar en EDT → interfaz congelada\n" +
                "• No configurar tipos de columna en JTable → edición incorrecta\n" +
                "• Olvidar setStringPainted en JProgressBar → sin feedback\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Usa TableRowSorter para ordenamiento automático\n" +
                "• Implementa RowFilter para filtrado de datos\n" +
                "• Cachea datos grandes en el TableModel\n" +
                "• Usa viewport para tablas muy grandes\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para JTable: considera JXTable de SwingX para más features\n" +
                "• Para JProgressBar: usa modo indeterminado para tareas sin duración conocida\n" +
                "• Para JScrollPane: configura políticas de scroll apropiadas\n" +
                "• Para datos dinámicos: usa AbstractTableModel con notificaciones\n\n" +
                "🎨 PERSONALIZACIÓN:\n" +
                "• TableCellRenderer para colores alternos en filas\n" +
                "• JProgressBar con colores personalizados\n" +
                "• JScrollPane con bordes y fondos personalizados\n" +
                "• Sliders con íconos y tooltips informativos");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}
