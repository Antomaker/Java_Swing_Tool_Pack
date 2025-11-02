/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;

import java.io.File;
import javax.swing.*;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import retos.learnswing.auth.UserManager;


/**
 * Enhanced AdditionalComponentsPanel with detailed explanations, examples, and interactive components.
 *
 * @author jocde
 */
public class AdditionalComponentsPanel extends JPanel {

    /**
     * Constructor for AdditionalComponentsPanel.
     * Creates an enhanced lesson panel with explanations and interactive examples.
     */
    public AdditionalComponentsPanel() {
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

        theoryText.setText("🎯 COMPONENTES ADICIONALES Y DIÁLOGOS EN JAVA SWING\n\n" +
                "Estos componentes proporcionan funcionalidades avanzadas y diálogos especializados.\n\n" +
                "🌳 JTree - Árbol Jerárquico:\n" +
                "• Muestra datos organizados en estructura de árbol\n" +
                "• Soporta expansión/contraacción de nodos\n" +
                "• Permite selección simple o múltiple\n" +
                "• Ideal para sistemas de archivos, menús, categorías\n\n" +
                "📁 JFileChooser - Selector de Archivos:\n" +
                "• Diálogo para abrir/guardar archivos\n" +
                "• Soporta filtros de extensión\n" +
                "• Modos: archivos, directorios, ambos\n" +
                "• Personalizable con accesorios (previews)\n\n" +
                "🎨 JColorChooser - Selector de Colores:\n" +
                "• Diálogo para selección de colores\n" +
                "• Múltiples paneles: paleta, HSV, RGB, CMYK\n" +
                "• Soporta colores transparentes\n" +
                "• Preview en tiempo real\n\n" +
                "🏠 JInternalFrame - Ventana Interna:\n" +
                "• Ventana dentro de otra ventana (MDI)\n" +
                "• Se usa con JDesktopPane\n" +
                "• Soporta maximizar, minimizar, cerrar\n" +
                "• Ideal para aplicaciones de escritorio complejas\n\n" +
                "✨ Características Avanzadas:\n" +
                "• Integración con sistema operativo (JFileChooser)\n" +
                "• Modelos de datos jerárquicos (JTree)\n" +
                "• Interfaz de múltiples documentos (JInternalFrame)\n" +
                "• Sistema de color profesional (JColorChooser)");

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

        // Example 1: JTree with dynamic content
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: JTree con Contenido Dinámico"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// Crear JTree con DefaultMutableTreeNode\n" +
                "DefaultMutableTreeNode root = new DefaultMutableTreeNode(\"Mi PC\");\n" +
                "DefaultMutableTreeNode documents = new DefaultMutableTreeNode(\"Documentos\");\n" +
                "DefaultMutableTreeNode pictures = new DefaultMutableTreeNode(\"Imágenes\");\n" +
                "root.add(documents);\n" +
                "root.add(pictures);\n" +
                "documents.add(new DefaultMutableTreeNode(\"CV.pdf\"));\n" +
                "pictures.add(new DefaultMutableTreeNode(\"vacaciones.jpg\"));\n" +
                "\n" +
                "JTree tree = new JTree(root);\n" +
                "tree.addTreeSelectionListener(e -> {\n" +
                "    DefaultMutableTreeNode node = (DefaultMutableTreeNode) \n" +
                "        tree.getLastSelectedPathComponent();\n" +
                "    System.out.println(\"Seleccionado: \" + node.getUserObject());\n" +
                "});");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        // Interactive tree example
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Mi PC");
        DefaultMutableTreeNode documents = new DefaultMutableTreeNode("Documentos");
        DefaultMutableTreeNode pictures = new DefaultMutableTreeNode("Imágenes");
        DefaultMutableTreeNode music = new DefaultMutableTreeNode("Música");

        root.add(documents);
        root.add(pictures);
        root.add(music);

        documents.add(new DefaultMutableTreeNode("CV.pdf"));
        documents.add(new DefaultMutableTreeNode("Factura.xlsx"));
        pictures.add(new DefaultMutableTreeNode("vacaciones.jpg"));
        pictures.add(new DefaultMutableTreeNode("familia.png"));
        music.add(new DefaultMutableTreeNode("cancion1.mp3"));
        music.add(new DefaultMutableTreeNode("cancion2.mp3"));

        JTree demoTree = new JTree(root);
        demoTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) demoTree.getLastSelectedPathComponent();
                if (node != null) {
                    JOptionPane.showMessageDialog(AdditionalComponentsPanel.this,
                        "Seleccionado: " + node.getUserObject());
                }
            }
        });

        JScrollPane treeScroll = new JScrollPane(demoTree);
        treeScroll.setPreferredSize(new Dimension(300, 150));

        JPanel treeControls = new JPanel(new FlowLayout());
        JButton addNodeBtn = new JButton("Agregar Archivo");
        addNodeBtn.addActionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) demoTree.getLastSelectedPathComponent();
            if (selectedNode != null && !selectedNode.isLeaf()) {
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("nuevo_archivo.txt");
                selectedNode.add(newNode);
                ((DefaultTreeModel) demoTree.getModel()).reload(selectedNode);
                demoTree.expandPath(new javax.swing.tree.TreePath(selectedNode.getPath()));
            }
        });
        treeControls.add(addNodeBtn);

        example1.add(code1, BorderLayout.NORTH);
        example1.add(treeScroll, BorderLayout.CENTER);
        example1.add(treeControls, BorderLayout.SOUTH);

        // Example 2: File and Color Choosers
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: Selectores de Archivos y Colores"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// JFileChooser para selección de archivos\n" +
                "JFileChooser fileChooser = new JFileChooser();\n" +
                "fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);\n" +
                "fileChooser.setFileFilter(new FileNameExtensionFilter(\n" +
                "    \"Archivos de texto\", \"txt\", \"java\"));\n" +
                "\n" +
                "int result = fileChooser.showOpenDialog(parent);\n" +
                "if (result == JFileChooser.APPROVE_OPTION) {\n" +
                "    File selectedFile = fileChooser.getSelectedFile();\n" +
                "}\n" +
                "\n" +
                "// JColorChooser para selección de colores\n" +
                "Color selectedColor = JColorChooser.showDialog(\n" +
                "    parent, \"Seleccionar Color\", Color.BLUE);\n" +
                "if (selectedColor != null) {\n" +
                "    // Usar el color seleccionado\n" +
                "}");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        JPanel chooserDemo = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton fileBtn = new JButton("Abrir Archivo");
        fileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos de texto", "txt", "java", "md"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Archivo seleccionado:\n" + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        chooserDemo.add(fileBtn);

        JButton saveBtn = new JButton("Guardar Archivo");
        saveBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar archivo");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Archivo a guardar:\n" + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        chooserDemo.add(saveBtn);

        JButton colorBtn = new JButton("Elegir Color");
        colorBtn.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(this, "Seleccionar Color", colorBtn.getBackground());
            if (selectedColor != null) {
                colorBtn.setBackground(selectedColor);
                JOptionPane.showMessageDialog(this,
                    "Color seleccionado: RGB(" + selectedColor.getRed() + ", " +
                    selectedColor.getGreen() + ", " + selectedColor.getBlue() + ")");
            }
        });
        chooserDemo.add(colorBtn);

        JButton internalBtn = new JButton("Ventana Interna");
        internalBtn.addActionListener(e -> {
            JInternalFrame internalFrame = new JInternalFrame("Ventana Interna", true, true, true, true);
            internalFrame.setSize(300, 200);
            internalFrame.setLocation(50, 50);

            JPanel content = new JPanel(new BorderLayout());
            content.add(new JLabel("Esta es una ventana interna", SwingConstants.CENTER), BorderLayout.CENTER);
            JButton closeBtn = new JButton("Cerrar");
            closeBtn.addActionListener(ev -> internalFrame.dispose());
            content.add(closeBtn, BorderLayout.SOUTH);

            internalFrame.add(content);
            internalFrame.setVisible(true);

            JOptionPane.showMessageDialog(this,
                "JInternalFrame creado. Normalmente se usa con JDesktopPane\n" +
                "para crear interfaces de múltiples documentos (MDI).");
        });
        chooserDemo.add(internalBtn);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(chooserDemo, BorderLayout.CENTER);

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

        // JTree practice
        JPanel treeSection = new JPanel(new BorderLayout());
        treeSection.setBorder(BorderFactory.createTitledBorder("Práctica: Explorador de Archivos"));

        DefaultMutableTreeNode fileRoot = new DefaultMutableTreeNode("Proyecto Java");
        DefaultMutableTreeNode src = new DefaultMutableTreeNode("src");
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("main");
        DefaultMutableTreeNode java = new DefaultMutableTreeNode("java");
        DefaultMutableTreeNode com = new DefaultMutableTreeNode("com.example");

        fileRoot.add(src);
        src.add(main);
        main.add(java);
        java.add(com);
        com.add(new DefaultMutableTreeNode("Main.java"));
        com.add(new DefaultMutableTreeNode("Utils.java"));

        DefaultMutableTreeNode resources = new DefaultMutableTreeNode("resources");
        src.add(resources);
        resources.add(new DefaultMutableTreeNode("config.properties"));
        resources.add(new DefaultMutableTreeNode("logo.png"));

        JTree fileTree = new JTree(fileRoot);
        fileTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
            if (node != null && node.isLeaf()) {
                JOptionPane.showMessageDialog(this, "Archivo seleccionado: " + node.getUserObject());
            }
        });

        JScrollPane treeScroll = new JScrollPane(fileTree);
        treeScroll.setPreferredSize(new Dimension(300, 150));

        JPanel treeControls = new JPanel(new FlowLayout());
        JButton expandBtn = new JButton("Expandir Todo");
        expandBtn.addActionListener(e -> {
            for (int i = 0; i < fileTree.getRowCount(); i++) {
                fileTree.expandRow(i);
            }
        });
        treeControls.add(expandBtn);

        JButton collapseBtn = new JButton("Contraer Todo");
        collapseBtn.addActionListener(e -> {
            for (int i = fileTree.getRowCount() - 1; i >= 0; i--) {
                fileTree.collapseRow(i);
            }
        });
        treeControls.add(collapseBtn);

        treeSection.add(treeScroll, BorderLayout.CENTER);
        treeSection.add(treeControls, BorderLayout.SOUTH);

        // File operations practice
        JPanel fileSection = new JPanel(new BorderLayout());
        fileSection.setBorder(BorderFactory.createTitledBorder("Práctica: Operaciones con Archivos"));

        JPanel fileControls = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton openFileBtn = new JButton("Abrir Archivo de Texto");
        openFileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos de texto", "txt", "java", "md"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Simulación: Archivo '" + fileChooser.getSelectedFile().getName() + "' abierto");
            }
        });
        fileControls.add(openFileBtn);

        JButton saveFileBtn = new JButton("Guardar Archivo");
        saveFileBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar archivo");
            fileChooser.setSelectedFile(new File("documento.txt"));
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Simulación: Archivo guardado como '" + fileChooser.getSelectedFile().getName() + "'");
            }
        });
        fileControls.add(saveFileBtn);

        JButton chooseDirBtn = new JButton("Seleccionar Directorio");
        chooseDirBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Seleccionar directorio");
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Directorio seleccionado: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        fileControls.add(chooseDirBtn);

        JButton colorPickerBtn = new JButton("Selector de Tema");
        colorPickerBtn.addActionListener(e -> {
            Color bgColor = JColorChooser.showDialog(this, "Seleccionar Color de Fondo", getBackground());
            if (bgColor != null) {
                // Cambiar el color de fondo de este panel para demostrar
                setBackground(bgColor);
                JOptionPane.showMessageDialog(this,
                    "Color de fondo cambiado. El tema se aplicaría a toda la aplicación.");
            }
        });
        fileControls.add(colorPickerBtn);

        fileSection.add(fileControls, BorderLayout.CENTER);

        // Internal frame practice
        JPanel internalSection = new JPanel(new BorderLayout());
        internalSection.setBorder(BorderFactory.createTitledBorder("Práctica: Ventanas Internas (MDI)"));

        JPanel internalControls = new JPanel(new FlowLayout());

        JButton createFrameBtn = new JButton("Crear Ventana Interna");
        createFrameBtn.addActionListener(e -> {
            JInternalFrame frame = new JInternalFrame("Documento " + (int)(Math.random() * 1000),
                true, true, true, true);
            frame.setSize(250, 150);
            frame.setLocation((int)(Math.random() * 200), (int)(Math.random() * 150));

            JPanel content = new JPanel(new BorderLayout());
            JTextArea textArea = new JTextArea("Contenido de la ventana interna...");
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            content.add(new JScrollPane(textArea), BorderLayout.CENTER);

            JButton closeInternalBtn = new JButton("Cerrar");
            closeInternalBtn.addActionListener(ev -> frame.dispose());
            content.add(closeInternalBtn, BorderLayout.SOUTH);

            frame.add(content);
            frame.setVisible(true);

            JOptionPane.showMessageDialog(this,
                "Ventana interna creada. En una aplicación real,\n" +
                "se agregaría a un JDesktopPane para gestión completa.");
        });
        internalControls.add(createFrameBtn);

        internalSection.add(internalControls, BorderLayout.CENTER);

        practicePanel.add(treeSection);
        practicePanel.add(fileSection);
        practicePanel.add(internalSection);

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Componentes Adicionales", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre JTree, JFileChooser, JColorChooser y JInternalFrame.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Componentes Adicionales");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Adicionales", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA COMPONENTES ADICIONALES\n\n" +
                "✅ USOS RECOMENDADOS:\n" +
                "• JTree: Estructuras jerárquicas (archivos, menús, categorías)\n" +
                "• JFileChooser: Cualquier operación con archivos\n" +
                "• JColorChooser: Personalización de colores en aplicaciones\n" +
                "• JInternalFrame: Aplicaciones MDI complejas\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Siempre usar JScrollPane con JTree\n" +
                "• Configurar filtros apropiados en JFileChooser\n" +
                "• Usar JDesktopPane como contenedor para JInternalFrame\n" +
                "• Implementar TreeModel para datos dinámicos grandes\n" +
                "• Proporcionar previews en JFileChooser cuando sea útil\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• No manejar JInternalFrame sin JDesktopPane → comportamiento impredecible\n" +
                "• Olvidar filtros en JFileChooser → usuarios ven todos los archivos\n" +
                "• No configurar selección apropiada en JTree → UX confusa\n" +
                "• Ignorar excepciones en operaciones de archivos\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Lazy loading para JTree con muchos nodos\n" +
                "• Cache de miniaturas para JFileChooser\n" +
                "• TreeCellRenderer personalizado para mejor apariencia\n" +
                "• Manejo de hilos para operaciones de archivos pesadas\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para JTree: usa DefaultTreeCellRenderer para íconos personalizados\n" +
                "• Para JFileChooser: implementa FileView para previews\n" +
                "• Para JColorChooser: crea paneles personalizados para colores específicos\n" +
                "• Para MDI: considera JTabbedPane como alternativa más simple\n\n" +
                "🎨 PERSONALIZACIÓN:\n" +
                "• Look and Feel personalizado para JFileChooser\n" +
                "• Íconos personalizados en JTree\n" +
                "• Paletas de colores específicas para JColorChooser\n" +
                "• Skins personalizados para JInternalFrame");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}