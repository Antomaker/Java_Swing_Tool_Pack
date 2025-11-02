/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import javax.swing.*;
import retos.learnswing.auth.UserManager;


/**
 * Enhanced SelectionPanel with detailed explanations, examples, and interactive components.
 *
 * @author jocde
 */
public class SelectionPanel extends JPanel {

    /**
     * Constructor for SelectionPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public SelectionPanel() {
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

        theoryText.setText("🎯 COMPONENTES DE SELECCIÓN EN JAVA SWING\n\n" +
                "Los componentes de selección permiten elegir entre múltiples opciones.\n\n" +
                "📋 JComboBox - Lista Desplegable:\n" +
                "• Para selección de una opción de una lista\n" +
                "• Ahorra espacio en la interfaz\n" +
                "• Editable o no editable\n" +
                "• Soporta modelo de datos personalizado\n\n" +
                "📝 JList - Lista de Elementos:\n" +
                "• Para mostrar múltiples elementos\n" +
                "• Soporta selección simple o múltiple\n" +
                "• Con JScrollPane para listas largas\n" +
                "• Altamente personalizable\n\n" +
                "🔢 JSpinner - Selector Numérico:\n" +
                "• Para selección de números o fechas\n" +
                "• Con botones arriba/abajo\n" +
                "• Configurable con límites y pasos\n" +
                "• Soporta diferentes tipos de datos\n\n" +
                "📅 JSlider - Control Deslizante:\n" +
                "• Para selección de valores en un rango\n" +
                "• Visual con marcas y etiquetas\n" +
                "• Orientación horizontal o vertical\n" +
                "• Ideal para ajustes continuos\n\n" +
                "✨ Características Comunes:\n" +
                "• Manejo de eventos (ActionListener, ListSelectionListener)\n" +
                "• Modelos de datos para contenido dinámico\n" +
                "• Renderers personalizados para apariencia\n" +
                "• Validación y filtrado de selecciones");

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

        // Example 1: JComboBox
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JComboBox Básico"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// Crear JComboBox con array de Strings\n" +
                "String[] paises = {\"España\", \"México\", \"Argentina\", \"Colombia\"};\n" +
                "JComboBox<String> comboBox = new JComboBox<>(paises);\n" +
                "\n" +
                "// Obtener selección\n" +
                "String seleccionado = (String) comboBox.getSelectedItem();\n" +
                "\n" +
                "// Manejar cambios de selección\n" +
                "comboBox.addActionListener(e -> {\n" +
                "    System.out.println(\"Seleccionado: \" + comboBox.getSelectedItem());\n" +
                "});");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        JPanel comboPanel = new JPanel(new FlowLayout());
        comboPanel.add(new JLabel("País:"));
        String[] countries = {"España", "México", "Argentina", "Colombia", "Perú"};
        JComboBox<String> demoCombo = new JComboBox<>(countries);
        demoCombo.setSelectedIndex(0);
        comboPanel.add(demoCombo);

        JButton showComboBtn = new JButton("Mostrar Selección");
        showComboBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "País seleccionado: " + demoCombo.getSelectedItem()));
        comboPanel.add(showComboBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(comboPanel, BorderLayout.CENTER);

        // Example 2: JList
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: JList con Selección Múltiple"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// Crear JList con selección múltiple\n" +
                "String[] frutas = {\"Manzana\", \"Banana\", \"Naranja\", \"Pera\", \"Uva\"};\n" +
                "JList<String> list = new JList<>(frutas);\n" +
                "list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);\n" +
                "\n" +
                "// Obtener selecciones múltiples\n" +
                "List<String> seleccionadas = list.getSelectedValuesList();\n" +
                "\n" +
                "// JScrollPane para listas largas\n" +
                "JScrollPane scrollPane = new JScrollPane(list);");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel listPanel = new JPanel(new BorderLayout());
        String[] fruits = {"Manzana", "Banana", "Naranja", "Pera", "Uva", "Mango", "Piña"};
        JList<String> demoList = new JList<>(fruits);
        demoList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        demoList.setVisibleRowCount(4);
        JScrollPane demoScroll = new JScrollPane(demoList);
        listPanel.add(demoScroll, BorderLayout.CENTER);

        JButton showListBtn = new JButton("Mostrar Seleccionadas");
        showListBtn.addActionListener(e -> {
            java.util.List<String> selected = demoList.getSelectedValuesList();
            String message = "Frutas seleccionadas:\n" + String.join("\n", selected);
            JOptionPane.showMessageDialog(this, message);
        });
        listPanel.add(showListBtn, BorderLayout.SOUTH);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(listPanel, BorderLayout.CENTER);

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

        JPanel practicePanel = new JPanel(new GridLayout(4, 1, 10, 10));

        // JComboBox practice
        JPanel comboSection = new JPanel(new BorderLayout());
        comboSection.setBorder(BorderFactory.createTitledBorder("JComboBox - Selección Única"));
        comboSection.add(new JLabel("Selecciona tu sistema operativo favorito:"), BorderLayout.NORTH);

        JPanel comboPanel = new JPanel(new FlowLayout());
        String[] os = {"Windows", "macOS", "Linux", "Android", "iOS"};
        JComboBox<String> osCombo = new JComboBox<>(os);
        comboPanel.add(osCombo);
        comboSection.add(comboPanel, BorderLayout.CENTER);

        // JList practice
        JPanel listSection = new JPanel(new BorderLayout());
        listSection.setBorder(BorderFactory.createTitledBorder("JList - Selección Múltiple"));
        listSection.add(new JLabel("Selecciona tus lenguajes de programación favoritos:"), BorderLayout.NORTH);

        JPanel listPanel = new JPanel(new BorderLayout());
        String[] languages = {"Java", "Python", "JavaScript", "C++", "C#", "PHP", "Ruby", "Go"};
        JList<String> langList = new JList<>(languages);
        langList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        langList.setVisibleRowCount(4);
        JScrollPane langScroll = new JScrollPane(langList);
        listPanel.add(langScroll, BorderLayout.CENTER);
        listSection.add(listPanel, BorderLayout.CENTER);

        // JSpinner practice
        JPanel spinnerSection = new JPanel(new BorderLayout());
        spinnerSection.setBorder(BorderFactory.createTitledBorder("JSpinner - Selección Numérica"));
        spinnerSection.add(new JLabel("Selecciona tu edad:"), BorderLayout.NORTH);

        JPanel spinnerPanel = new JPanel(new FlowLayout());
        SpinnerNumberModel ageModel = new SpinnerNumberModel(25, 10, 100, 1);
        JSpinner ageSpinner = new JSpinner(ageModel);
        spinnerPanel.add(ageSpinner);
        spinnerSection.add(spinnerPanel, BorderLayout.CENTER);

        // JSlider practice
        JPanel sliderSection = new JPanel(new BorderLayout());
        sliderSection.setBorder(BorderFactory.createTitledBorder("JSlider - Selección en Rango"));
        sliderSection.add(new JLabel("¿Qué tan satisfecho estás con Java Swing? (0-100):"), BorderLayout.NORTH);

        JPanel sliderPanel = new JPanel(new BorderLayout());
        JSlider satisfactionSlider = new JSlider(0, 100, 75);
        satisfactionSlider.setMajorTickSpacing(25);
        satisfactionSlider.setMinorTickSpacing(5);
        satisfactionSlider.setPaintTicks(true);
        satisfactionSlider.setPaintLabels(true);
        sliderPanel.add(satisfactionSlider, BorderLayout.CENTER);
        sliderSection.add(sliderPanel, BorderLayout.CENTER);

        // Action buttons
        JPanel buttonSection = new JPanel(new BorderLayout());
        buttonSection.setBorder(BorderFactory.createTitledBorder("Mostrar Todas las Selecciones"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton showAllBtn = new JButton("Mostrar Resultados");
        showAllBtn.addActionListener(e -> {
            StringBuilder message = new StringBuilder();
            message.append("Sistema Operativo: ").append(osCombo.getSelectedItem()).append("\n\n");

            message.append("Lenguajes de Programación:\n");
            java.util.List<String> selectedLangs = langList.getSelectedValuesList();
            if (selectedLangs.isEmpty()) {
                message.append("Ninguno seleccionado\n");
            } else {
                for (String lang : selectedLangs) {
                    message.append("• ").append(lang).append("\n");
                }
            }
            message.append("\n");

            message.append("Edad: ").append(ageSpinner.getValue()).append(" años\n\n");
            message.append("Satisfacción con Java Swing: ").append(satisfactionSlider.getValue()).append("/100");

            JOptionPane.showMessageDialog(this, message.toString(), "Encuesta de Preferencias", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(showAllBtn);

        JButton resetBtn = new JButton("Reiniciar");
        resetBtn.addActionListener(e -> {
            osCombo.setSelectedIndex(0);
            langList.clearSelection();
            ageSpinner.setValue(25);
            satisfactionSlider.setValue(75);
        });
        buttonPanel.add(resetBtn);

        buttonSection.add(buttonPanel, BorderLayout.CENTER);

        practicePanel.add(comboSection);
        practicePanel.add(listSection);
        practicePanel.add(spinnerSection);
        practicePanel.add(sliderSection);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane practiceScroll = new JScrollPane(practicePanel);
        mainPanel.add(practiceScroll, BorderLayout.CENTER);
        mainPanel.add(buttonSection, BorderLayout.SOUTH);

        panel.add(mainPanel, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the source code panel with complete implementations.
     */
    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane codeTabs = new JTabbedPane();

        // JComboBox example
        JTextArea comboBoxCode = new JTextArea();
        comboBoxCode.setEditable(false);
        comboBoxCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        comboBoxCode.setBackground(Color.BLACK);
        comboBoxCode.setForeground(Color.GREEN);
        comboBoxCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "\n" +
                "public class ComboBoxExample extends JPanel {\n" +
                "    private JComboBox<String> countryBox;\n" +
                "    private JComboBox<String> cityBox;\n" +
                "    private JLabel resultLabel;\n" +
                "    \n" +
                "    public ComboBoxExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Panel superior - País\n" +
                "        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));\n" +
                "        topPanel.add(new JLabel(\"País:\"));\n" +
                "        \n" +
                "        String[] countries = {\"Colombia\", \"México\", \"Argentina\", \"Chile\", \"Perú\"};\n" +
                "        countryBox = new JComboBox<>(countries);\n" +
                "        countryBox.setSelectedIndex(0);\n" +
                "        countryBox.setToolTipText(\"Selecciona tu país\");\n" +
                "        \n" +
                "        // Listener para cambio de país\n" +
                "        countryBox.addActionListener(e -> updateCities());\n" +
                "        topPanel.add(countryBox);\n" +
                "        \n" +
                "        // Panel central - Ciudad\n" +
                "        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));\n" +
                "        centerPanel.add(new JLabel(\"Ciudad:\"));\n" +
                "        \n" +
                "        cityBox = new JComboBox<>();\n" +
                "        cityBox.setToolTipText(\"Selecciona tu ciudad\");\n" +
                "        updateCities(); // Inicializar ciudades\n" +
                "        centerPanel.add(cityBox);\n" +
                "        \n" +
                "        // Panel inferior - Resultado\n" +
                "        JPanel bottomPanel = new JPanel(new BorderLayout());\n" +
                "        bottomPanel.setBorder(BorderFactory.createTitledBorder(\"Selección\"));\n" +
                "        \n" +
                "        resultLabel = new JLabel(\"Selecciona país y ciudad\", SwingConstants.CENTER);\n" +
                "        resultLabel.setFont(new Font(\"Arial\", Font.BOLD, 14));\n" +
                "        bottomPanel.add(resultLabel, BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Botón mostrar\n" +
                "        JButton showButton = new JButton(\"Mostrar Selección\");\n" +
                "        showButton.addActionListener(e -> {\n" +
                "            String country = (String) countryBox.getSelectedItem();\n" +
                "            String city = (String) cityBox.getSelectedItem();\n" +
                "            resultLabel.setText(\"País: \" + country + \" - Ciudad: \" + city);\n" +
                "            \n" +
                "            JOptionPane.showMessageDialog(this,\n" +
                "                \"Has seleccionado:\\nPaís: \" + country + \"\\nCiudad: \" + city,\n" +
                "                \"Selección\", JOptionPane.INFORMATION_MESSAGE);\n" +
                "        });\n" +
                "        bottomPanel.add(showButton, BorderLayout.SOUTH);\n" +
                "        \n" +
                "        add(topPanel, BorderLayout.NORTH);\n" +
                "        add(centerPanel, BorderLayout.CENTER);\n" +
                "        add(bottomPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    private void updateCities() {\n" +
                "        String selectedCountry = (String) countryBox.getSelectedItem();\n" +
                "        cityBox.removeAllItems();\n" +
                "        \n" +
                "        // Ciudades por país\n" +
                "        String[] cities;\n" +
                "        switch (selectedCountry) {\n" +
                "            case \"Colombia\":\n" +
                "                cities = new String[]{\"Bogotá\", \"Medellín\", \"Cali\", \"Barranquilla\"};\n" +
                "                break;\n" +
                "            case \"México\":\n" +
                "                cities = new String[]{\"Ciudad de México\", \"Guadalajara\", \"Monterrey\", \"Puebla\"};\n" +
                "                break;\n" +
                "            case \"Argentina\":\n" +
                "                cities = new String[]{\"Buenos Aires\", \"Córdoba\", \"Rosario\", \"Mendoza\"};\n" +
                "                break;\n" +
                "            case \"Chile\":\n" +
                "                cities = new String[]{\"Santiago\", \"Valparaíso\", \"Concepción\", \"La Serena\"};\n" +
                "                break;\n" +
                "            case \"Perú\":\n" +
                "                cities = new String[]{\"Lima\", \"Arequipa\", \"Trujillo\", \"Cusco\"};\n" +
                "                break;\n" +
                "            default:\n" +
                "                cities = new String[]{\"Ciudad 1\", \"Ciudad 2\"};\n" +
                "        }\n" +
                "        \n" +
                "        for (String city : cities) {\n" +
                "            cityBox.addItem(city);\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public String getSelectedCountry() {\n" +
                "        return (String) countryBox.getSelectedItem();\n" +
                "    }\n" +
                "    \n" +
                "    public String getSelectedCity() {\n" +
                "        return (String) cityBox.getSelectedItem();\n" +
                "    }\n" +
                "    \n" +
                "    public void setSelectedCountry(String country) {\n" +
                "        countryBox.setSelectedItem(country);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JComboBox", new JScrollPane(comboBoxCode));

        // JList example
        JTextArea listCode = new JTextArea();
        listCode.setEditable(false);
        listCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        listCode.setBackground(Color.BLACK);
        listCode.setForeground(Color.GREEN);
        listCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import javax.swing.event.ListSelectionEvent;\n" +
                "import javax.swing.event.ListSelectionListener;\n" +
                "\n" +
                "public class ListExample extends JPanel {\n" +
                "    private JList<String> itemList;\n" +
                "    private DefaultListModel<String> listModel;\n" +
                "    private JTextField addField;\n" +
                "    private JButton addButton;\n" +
                "    private JButton removeButton;\n" +
                "    private JLabel selectionLabel;\n" +
                "    \n" +
                "    public ListExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Modelo de lista\n" +
                "        listModel = new DefaultListModel<>();\n" +
                "        listModel.addElement(\"Elemento 1\");\n" +
                "        listModel.addElement(\"Elemento 2\");\n" +
                "        listModel.addElement(\"Elemento 3\");\n" +
                "        listModel.addElement(\"Elemento 4\");\n" +
                "        listModel.addElement(\"Elemento 5\");\n" +
                "        \n" +
                "        // Crear JList\n" +
                "        itemList = new JList<>(listModel);\n" +
                "        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);\n" +
                "        itemList.setVisibleRowCount(8);\n" +
                "        itemList.setFont(new Font(\"Arial\", Font.PLAIN, 12));\n" +
                "        \n" +
                "        // Listener para selección\n" +
                "        itemList.addListSelectionListener(new ListSelectionListener() {\n" +
                "            @Override\n" +
                "            public void valueChanged(ListSelectionEvent e) {\n" +
                "                if (!e.getValueIsAdjusting()) {\n" +
                "                    List<String> selected = itemList.getSelectedValuesList();\n" +
                "                    if (selected.isEmpty()) {\n" +
                "                        selectionLabel.setText(\"Ningún elemento seleccionado\");\n" +
                "                    } else {\n" +
                "                        selectionLabel.setText(\"Seleccionados: \" + selected.size() + \" elementos\");\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        // Panel de scroll para la lista\n" +
                "        JScrollPane listScroll = new JScrollPane(itemList);\n" +
                "        listScroll.setPreferredSize(new Dimension(200, 150));\n" +
                "        \n" +
                "        // Panel de controles\n" +
                "        JPanel controlPanel = new JPanel(new BorderLayout());\n" +
                "        \n" +
                "        // Panel para agregar elementos\n" +
                "        JPanel addPanel = new JPanel(new BorderLayout());\n" +
                "        addField = new JTextField(15);\n" +
                "        addField.setToolTipText(\"Ingresa nuevo elemento\");\n" +
                "        addButton = new JButton(\"Agregar\");\n" +
                "        addButton.addActionListener(e -> {\n" +
                "            String text = addField.getText().trim();\n" +
                "            if (!text.isEmpty()) {\n" +
                "                listModel.addElement(text);\n" +
                "                addField.setText(\"\");\n" +
                "                addField.requestFocus();\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        // Permitir Enter para agregar\n" +
                "        addField.addActionListener(e -> addButton.doClick());\n" +
                "        \n" +
                "        addPanel.add(addField, BorderLayout.CENTER);\n" +
                "        addPanel.add(addButton, BorderLayout.EAST);\n" +
                "        \n" +
                "        // Botón remover\n" +
                "        removeButton = new JButton(\"Remover Seleccionados\");\n" +
                "        removeButton.addActionListener(e -> {\n" +
                "            int[] selectedIndices = itemList.getSelectedIndices();\n" +
                "            if (selectedIndices.length > 0) {\n" +
                "                // Remover en orden inverso para mantener índices\n" +
                "                for (int i = selectedIndices.length - 1; i >= 0; i--) {\n" +
                "                    listModel.remove(selectedIndices[i]);\n" +
                "                }\n" +
                "            } else {\n" +
                "                JOptionPane.showMessageDialog(this,\n" +
                "                    \"Selecciona elementos para remover.\",\n" +
                "                    \"Sin selección\", JOptionPane.WARNING_MESSAGE);\n" +
                "            }\n" +
                "        });\n" +
                "        \n" +
                "        controlPanel.add(addPanel, BorderLayout.NORTH);\n" +
                "        controlPanel.add(removeButton, BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Etiqueta de estado\n" +
                "        selectionLabel = new JLabel(\"Ningún elemento seleccionado\");\n" +
                "        selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);\n" +
                "        selectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));\n" +
                "        \n" +
                "        add(listScroll, BorderLayout.CENTER);\n" +
                "        add(controlPanel, BorderLayout.SOUTH);\n" +
                "        add(selectionLabel, BorderLayout.NORTH);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public List<String> getSelectedItems() {\n" +
                "        return itemList.getSelectedValuesList();\n" +
                "    }\n" +
                "    \n" +
                "    public void addItem(String item) {\n" +
                "        listModel.addElement(item);\n" +
                "    }\n" +
                "    \n" +
                "    public void removeSelectedItems() {\n" +
                "        int[] indices = itemList.getSelectedIndices();\n" +
                "        for (int i = indices.length - 1; i >= 0; i--) {\n" +
                "            listModel.remove(indices[i]);\n" +
                "        }\n" +
                "    }\n" +
                "    \n" +
                "    public int getItemCount() {\n" +
                "        return listModel.getSize();\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JList", new JScrollPane(listCode));

        // JSpinner example
        JTextArea spinnerCode = new JTextArea();
        spinnerCode.setEditable(false);
        spinnerCode.setFont(new Font("Monospaced", Font.PLAIN, 12));
        spinnerCode.setBackground(Color.BLACK);
        spinnerCode.setForeground(Color.GREEN);
        spinnerCode.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.util.Calendar;\n" +
                "import java.util.Date;\n" +
                "\n" +
                "public class SpinnerExample extends JPanel {\n" +
                "    private JSpinner numberSpinner;\n" +
                "    private JSpinner dateSpinner;\n" +
                "    private JSpinner listSpinner;\n" +
                "    private JLabel resultLabel;\n" +
                "    \n" +
                "    public SpinnerExample() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        // Panel de spinners\n" +
                "        JPanel spinnerPanel = new JPanel(new GridLayout(3, 2, 10, 10));\n" +
                "        spinnerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));\n" +
                "        \n" +
                "        // Spinner numérico\n" +
                "        spinnerPanel.add(new JLabel(\"Cantidad (0-100):\"));\n" +
                "        SpinnerNumberModel numberModel = new SpinnerNumberModel(50, 0, 100, 5);\n" +
                "        numberSpinner = new JSpinner(numberModel);\n" +
                "        numberSpinner.setToolTipText(\"Ajusta la cantidad con las flechas\");\n" +
                "        spinnerPanel.add(numberSpinner);\n" +
                "        \n" +
                "        // Spinner de fecha\n" +
                "        spinnerPanel.add(new JLabel(\"Fecha:\"));\n" +
                "        Calendar calendar = Calendar.getInstance();\n" +
                "        Date initialDate = calendar.getTime();\n" +
                "        calendar.add(Calendar.YEAR, -100); // 100 años atrás\n" +
                "        Date earliestDate = calendar.getTime();\n" +
                "        calendar.add(Calendar.YEAR, 200); // 100 años adelante\n" +
                "        Date latestDate = calendar.getTime();\n" +
                "        \n" +
                "        SpinnerDateModel dateModel = new SpinnerDateModel(initialDate,\n" +
                "            earliestDate, latestDate, Calendar.DAY_OF_MONTH);\n" +
                "        dateSpinner = new JSpinner(dateModel);\n" +
                "        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, \"dd/MM/yyyy\"));\n" +
                "        spinnerPanel.add(dateSpinner);\n" +
                "        \n" +
                "        // Spinner de lista\n" +
                "        spinnerPanel.add(new JLabel(\"Prioridad:\"));\n" +
                "        String[] priorities = {\"Baja\", \"Normal\", \"Alta\", \"Urgente\"};\n" +
                "        SpinnerListModel listModel = new SpinnerListModel(priorities);\n" +
                "        listSpinner = new JSpinner(listModel);\n" +
                "        spinnerPanel.add(listSpinner);\n" +
                "        \n" +
                "        // Panel de resultado\n" +
                "        JPanel resultPanel = new JPanel(new BorderLayout());\n" +
                "        resultPanel.setBorder(BorderFactory.createTitledBorder(\"Valores Actuales\"));\n" +
                "        \n" +
                "        resultLabel = new JLabel(\"\", SwingConstants.CENTER);\n" +
                "        resultLabel.setFont(new Font(\"Arial\", Font.PLAIN, 12));\n" +
                "        updateResultLabel();\n" +
                "        resultPanel.add(resultLabel, BorderLayout.CENTER);\n" +
                "        \n" +
                "        // Botones de control\n" +
                "        JPanel buttonPanel = new JPanel(new FlowLayout());\n" +
                "        \n" +
                "        JButton updateButton = new JButton(\"Actualizar\");\n" +
                "        updateButton.addActionListener(e -> updateResultLabel());\n" +
                "        buttonPanel.add(updateButton);\n" +
                "        \n" +
                "        JButton resetButton = new JButton(\"Reiniciar\");\n" +
                "        resetButton.addActionListener(e -> {\n" +
                "            numberSpinner.setValue(50);\n" +
                "            dateSpinner.setValue(new Date());\n" +
                "            listSpinner.setValue(\"Normal\");\n" +
                "            updateResultLabel();\n" +
                "        });\n" +
                "        buttonPanel.add(resetButton);\n" +
                "        \n" +
                "        resultPanel.add(buttonPanel, BorderLayout.SOUTH);\n" +
                "        \n" +
                "        // Change listeners para actualización automática\n" +
                "        numberSpinner.addChangeListener(e -> updateResultLabel());\n" +
                "        dateSpinner.addChangeListener(e -> updateResultLabel());\n" +
                "        listSpinner.addChangeListener(e -> updateResultLabel());\n" +
                "        \n" +
                "        add(spinnerPanel, BorderLayout.CENTER);\n" +
                "        add(resultPanel, BorderLayout.SOUTH);\n" +
                "    }\n" +
                "    \n" +
                "    private void updateResultLabel() {\n" +
                "        String result = String.format(\n" +
                "            \"<html>Cantidad: <b>%s</b><br/>\" +\n" +
                "            \"Fecha: <b>%s</b><br/>\" +\n" +
                "            \"Prioridad: <b>%s</b></html>\",\n" +
                "            numberSpinner.getValue(),\n" +
                "            String.format(\"%1$td/%1$tm/%1$tY\", dateSpinner.getValue()),\n" +
                "            listSpinner.getValue()\n" +
                "        );\n" +
                "        resultLabel.setText(result);\n" +
                "    }\n" +
                "    \n" +
                "    // Métodos útiles\n" +
                "    public int getNumberValue() {\n" +
                "        return (Integer) numberSpinner.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public Date getDateValue() {\n" +
                "        return (Date) dateSpinner.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public String getListValue() {\n" +
                "        return (String) listSpinner.getValue();\n" +
                "    }\n" +
                "    \n" +
                "    public void setNumberValue(int value) {\n" +
                "        numberSpinner.setValue(value);\n" +
                "    }\n" +
                "}");
        codeTabs.addTab("JSpinner", new JScrollPane(spinnerCode));

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes de Selección", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre JComboBox, JList, JSpinner y JSlider.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Componentes de Selección");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Selección", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA COMPONENTES DE SELECCIÓN\n\n" +
                "✅ CUÁNDO USAR CADA COMPONENTE:\n" +
                "• JComboBox: Pocas opciones, espacio limitado (países, categorías)\n" +
                "• JList: Muchas opciones, selección múltiple posible (etiquetas, items)\n" +
                "• JSpinner: Números, fechas, valores incrementales\n" +
                "• JSlider: Rangos continuos, ajustes visuales (volumen, brillo)\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Ordena opciones alfabéticamente cuando sea posible\n" +
                "• Proporciona valores por defecto sensatos\n" +
                "• Usa JScrollPane para listas que puedan crecer\n" +
                "• Valida selecciones antes de procesar\n" +
                "• Considera accesibilidad (teclas de navegación)\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• No configurar JScrollPane → listas cortadas\n" +
                "• Olvidar setVisibleRowCount → listas demasiado grandes\n" +
                "• No manejar selecciones vacías → NullPointerException\n" +
                "• Usar JComboBox para muchas opciones → UX pobre\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Implementa ListModel para datos dinámicos grandes\n" +
                "• Usa CellRenderer para apariencia personalizada\n" +
                "• Implementa filtrado/búsqueda para listas largas\n" +
                "• Considera JTable para datos tabulares complejos\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para enums: usa JComboBox con values()\n" +
                "• Para búsqueda: combina JComboBox editable + autocompletado\n" +
                "• Para rangos: JSlider con ChangeListener para feedback inmediato\n" +
                "• Para internacionalización: usa ResourceBundle para opciones\n\n" +
                "🎨 PERSONALIZACIÓN:\n" +
                "• Cambia colores según selección\n" +
                "• Usa íconos para mejorar reconocimiento visual\n" +
                "• Implementa tooltips informativos\n" +
                "• Considera temas y skins personalizados");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}