/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


/**
 * Interactive Component Gallery Panel - A visual component builder where students
 * can drag components from a palette, arrange them on a design canvas, and generate
 * functional Java Swing code.
 *
 * @author jocde
 */
public class ComponentGalleryPanel extends JPanel {

    private JPanel palettePanel;
    private DesignCanvas designCanvas;
    private JTextArea codeArea;
    private JButton generateCodeBtn;
    private JButton clearCanvasBtn;
    private JButton previewBtn;

    // Component templates for the palette
    private final ComponentTemplate[] componentTemplates = {
        new ComponentTemplate("JButton", "Botón", "JButton button = new JButton(\"Texto\");",
                            () -> new JButton("Botón")),
        new ComponentTemplate("JTextField", "Campo de Texto", "JTextField textField = new JTextField(20);",
                            () -> new JTextField(15)),
        new ComponentTemplate("JLabel", "Etiqueta", "JLabel label = new JLabel(\"Texto\");",
                            () -> new JLabel("Etiqueta")),
        new ComponentTemplate("JCheckBox", "Casilla", "JCheckBox checkBox = new JCheckBox(\"Opción\");",
                            () -> new JCheckBox("Opción")),
        new ComponentTemplate("JRadioButton", "Botón Radio", "JRadioButton radio = new JRadioButton(\"Opción\");",
                            () -> new JRadioButton("Opción")),
        new ComponentTemplate("JComboBox", "Lista Desplegable",
                            "String[] items = {\"Opción 1\", \"Opción 2\"};\nJComboBox<String> combo = new JComboBox<>(items);",
                            () -> {
                                String[] items = {"Opción 1", "Opción 2"};
                                return new JComboBox<>(items);
                            }),
        new ComponentTemplate("JList", "Lista",
                            "String[] data = {\"Item 1\", \"Item 2\", \"Item 3\"};\nJList<String> list = new JList<>(data);",
                            () -> {
                                String[] data = {"Item 1", "Item 2", "Item 3"};
                                JList<String> list = new JList<>(data);
                                list.setVisibleRowCount(3);
                                return list;
                            }),
        new ComponentTemplate("JTextArea", "Área de Texto", "JTextArea textArea = new JTextArea(5, 20);",
                            () -> new JTextArea(3, 15)),
        new ComponentTemplate("JSlider", "Control Deslizante", "JSlider slider = new JSlider(0, 100, 50);",
                            () -> new JSlider(0, 100, 50)),
        new ComponentTemplate("JSpinner", "Selector Numérico", "JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));",
                            () -> new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)))
    };

    /**
     * Constructor for ComponentGalleryPanel.
     */
    public ComponentGalleryPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    /**
     * Initializes all components of the gallery panel.
     */
    private void initializeComponents() {
        // Create main split pane
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainSplit.setResizeWeight(0.3);

        // Left side - Palette and controls
        JPanel leftPanel = createLeftPanel();
        mainSplit.setLeftComponent(leftPanel);

        // Right side - Design canvas and code
        JPanel rightPanel = createRightPanel();
        mainSplit.setRightComponent(rightPanel);

        add(mainSplit, BorderLayout.CENTER);

        // Initialize drag and drop
        setupDragAndDrop();
    }

    /**
     * Creates the left panel with component palette and controls.
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new BorderLayout());

        // Component palette
        palettePanel = new JPanel();
        palettePanel.setLayout(new BoxLayout(palettePanel, BoxLayout.Y_AXIS));
        palettePanel.setBorder(BorderFactory.createTitledBorder("🎨 Paleta de Componentes"));

        JScrollPane paletteScroll = new JScrollPane(palettePanel);
        paletteScroll.setPreferredSize(new Dimension(250, 400));

        // Add component buttons to palette
        for (ComponentTemplate template : componentTemplates) {
            JButton componentBtn = createComponentButton(template);
            palettePanel.add(componentBtn);
            palettePanel.add(Box.createVerticalStrut(5));
        }

        leftPanel.add(paletteScroll, BorderLayout.CENTER);

        // Control buttons
        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controles"));

        clearCanvasBtn = new JButton("🗑️ Limpiar Lienzo");
        clearCanvasBtn.addActionListener(e -> designCanvas.clearCanvas());

        previewBtn = new JButton("👁️ Vista Previa");
        previewBtn.addActionListener(e -> showPreview());

        generateCodeBtn = new JButton("📝 Generar Código");
        generateCodeBtn.addActionListener(e -> generateCode());

        controlPanel.add(clearCanvasBtn);
        controlPanel.add(previewBtn);
        controlPanel.add(generateCodeBtn);

        leftPanel.add(controlPanel, BorderLayout.SOUTH);

        return leftPanel;
    }

    /**
     * Creates the right panel with design canvas and code area.
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());

        // Design canvas
        designCanvas = new DesignCanvas();
        JScrollPane canvasScroll = new JScrollPane(designCanvas);
        canvasScroll.setPreferredSize(new Dimension(600, 400));
        canvasScroll.setBorder(BorderFactory.createTitledBorder("🎯 Lienzo de Diseño"));

        // Code area
        codeArea = new JTextArea();
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setEditable(false);
        JScrollPane codeScroll = new JScrollPane(codeArea);
        codeScroll.setPreferredSize(new Dimension(600, 200));
        codeScroll.setBorder(BorderFactory.createTitledBorder("📝 Código Generado"));

        // Split between canvas and code
        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightSplit.setResizeWeight(0.7);
        rightSplit.setTopComponent(canvasScroll);
        rightSplit.setBottomComponent(codeScroll);

        rightPanel.add(rightSplit, BorderLayout.CENTER);

        return rightPanel;
    }

    /**
     * Creates a draggable component button for the palette.
     */
    private JButton createComponentButton(ComponentTemplate template) {
        JButton button = new JButton(template.displayName);
        button.setToolTipText(template.description);
        button.setMaximumSize(new Dimension(200, 30));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        // Make it draggable
        button.setTransferHandler(new TransferHandler() {
            @Override
            protected Transferable createTransferable(JComponent c) {
                return new StringSelection(template.componentType);
            }

            @Override
            public int getSourceActions(JComponent c) {
                return COPY;
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JComponent c = (JComponent) e.getSource();
                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY);
            }
        });

        return button;
    }

    /**
     * Sets up drag and drop functionality.
     */
    private void setupDragAndDrop() {
        // The design canvas handles drop operations
        designCanvas.setTransferHandler(new CanvasTransferHandler());
    }

    /**
     * Shows a preview of the designed interface.
     */
    private void showPreview() {
        JFrame previewFrame = new JFrame("Vista Previa - Galería de Componentes");
        previewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        previewFrame.setSize(600, 400);
        previewFrame.setLocationRelativeTo(this);

        JPanel previewPanel = new JPanel(new FlowLayout());
        previewPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add copies of all components from the canvas
        for (Component component : designCanvas.getComponents()) {
            if (component instanceof JComponent) {
                try {
                    JComponent comp = (JComponent) component;
                    JComponent copy = createComponentCopy(comp);
                    if (copy != null) {
                        previewPanel.add(copy);
                    }
                } catch (Exception e) {
                    // Skip components that can't be copied
                }
            }
        }

        if (previewPanel.getComponentCount() == 0) {
            previewPanel.add(new JLabel("No hay componentes en el lienzo"));
        }

        previewFrame.add(previewPanel);
        previewFrame.setVisible(true);
    }

    /**
     * Creates a copy of a component for preview.
     */
    @SuppressWarnings("unchecked")
    private JComponent createComponentCopy(JComponent original) {
        if (original instanceof JButton) {
            return new JButton(((JButton) original).getText());
        } else if (original instanceof JTextField) {
            JTextField copy = new JTextField(((JTextField) original).getText());
            copy.setColumns(((JTextField) original).getColumns());
            return copy;
        } else if (original instanceof JLabel) {
            return new JLabel(((JLabel) original).getText());
        } else if (original instanceof JCheckBox) {
            return new JCheckBox(((JCheckBox) original).getText());
        } else if (original instanceof JRadioButton) {
            return new JRadioButton(((JRadioButton) original).getText());
        } else if (original instanceof JComboBox) {
            JComboBox<String> combo = new JComboBox<>();
            for (int i = 0; i < ((JComboBox<String>) original).getItemCount(); i++) {
                combo.addItem((String) ((JComboBox<String>) original).getItemAt(i));
            }
            return combo;
        } else if (original instanceof JList) {
            JList<String> list = new JList<>(((JList<String>) original).getModel());
            list.setVisibleRowCount(Math.min(3, list.getModel().getSize()));
            return list;
        } else if (original instanceof JTextArea) {
            JTextArea copy = new JTextArea(((JTextArea) original).getText());
            copy.setRows(((JTextArea) original).getRows());
            copy.setColumns(((JTextArea) original).getColumns());
            return copy;
        } else if (original instanceof JSlider) {
            JSlider slider = (JSlider) original;
            return new JSlider(slider.getMinimum(), slider.getMaximum(), slider.getValue());
        } else if (original instanceof JSpinner) {
            return new JSpinner(((JSpinner) original).getModel());
        }

        return null;
    }

    /**
     * Generates Java code from the designed interface.
     */
    private void generateCode() {
        StringBuilder code = new StringBuilder();
        code.append("import javax.swing.*;\n");
        code.append("import java.awt.*;\n");
        code.append("import java.awt.event.*;\n\n");

        code.append("public class GeneratedInterface extends JPanel {\n\n");
        code.append("    public GeneratedInterface() {\n");
        code.append("        setLayout(new FlowLayout());\n\n");

        int componentCount = 0;
        for (Component component : designCanvas.getComponents()) {
            if (component instanceof JComponent) {
                String varName = "component" + componentCount;
                String componentCode = generateComponentCode((JComponent) component, varName);
                if (componentCode != null) {
                    code.append("        ").append(componentCode).append("\n");
                    code.append("        add(").append(varName).append(");\n\n");
                    componentCount++;
                }
            }
        }

        code.append("    }\n\n");
        code.append("    public static void main(String[] args) {\n");
        code.append("        JFrame frame = new JFrame(\"Interfaz Generada\");\n");
        code.append("        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n");
        code.append("        frame.setSize(400, 300);\n");
        code.append("        frame.add(new GeneratedInterface());\n");
        code.append("        frame.setVisible(true);\n");
        code.append("    }\n");
        code.append("}");

        codeArea.setText(code.toString());
    }

    /**
     * Generates code for a specific component.
     */
    @SuppressWarnings("unchecked")
    private String generateComponentCode(JComponent component, String varName) {
        if (component instanceof JButton) {
            JButton btn = (JButton) component;
            return String.format("JButton %s = new JButton(\"%s\");", varName, btn.getText());
        } else if (component instanceof JTextField) {
            JTextField tf = (JTextField) component;
            return String.format("JTextField %s = new JTextField(\"%s\", %d);",
                               varName, tf.getText(), tf.getColumns());
        } else if (component instanceof JLabel) {
            JLabel lbl = (JLabel) component;
            return String.format("JLabel %s = new JLabel(\"%s\");", varName, lbl.getText());
        } else if (component instanceof JCheckBox) {
            JCheckBox cb = (JCheckBox) component;
            return String.format("JCheckBox %s = new JCheckBox(\"%s\");", varName, cb.getText());
        } else if (component instanceof JRadioButton) {
            JRadioButton rb = (JRadioButton) component;
            return String.format("JRadioButton %s = new JRadioButton(\"%s\");", varName, rb.getText());
        } else if (component instanceof JComboBox) {
            JComboBox<String> combo = (JComboBox<String>) component;
            StringBuilder items = new StringBuilder();
            for (int i = 0; i < combo.getItemCount(); i++) {
                if (i > 0) items.append(", ");
                items.append("\"").append(combo.getItemAt(i)).append("\"");
            }
            return String.format("String[] %s_items = {%s};\n        JComboBox<String> %s = new JComboBox<>(%s_items);",
                               varName, items.toString(), varName, varName);
        } else if (component instanceof JList) {
            JList<String> list = (JList<String>) component;
            StringBuilder items = new StringBuilder();
            for (int i = 0; i < list.getModel().getSize(); i++) {
                if (i > 0) items.append(", ");
                items.append("\"").append(list.getModel().getElementAt(i)).append("\"");
            }
            return String.format("String[] %s_data = {%s};\n        JList<String> %s = new JList<>(%s_data);\n        %s.setVisibleRowCount(%d);",
                               varName, items.toString(), varName, varName, varName,
                               Math.min(3, list.getModel().getSize()));
        } else if (component instanceof JTextArea) {
            JTextArea ta = (JTextArea) component;
            return String.format("JTextArea %s = new JTextArea(\"%s\", %d, %d);",
                               varName, ta.getText(), ta.getRows(), ta.getColumns());
        } else if (component instanceof JSlider) {
            JSlider slider = (JSlider) component;
            return String.format("JSlider %s = new JSlider(%d, %d, %d);",
                               varName, slider.getMinimum(), slider.getMaximum(), slider.getValue());
        } else if (component instanceof JSpinner) {
            return String.format("JSpinner %s = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));", varName);
        }

        return null;
    }

    /**
     * Inner class representing a component template.
     */
    private static class ComponentTemplate {
        String componentType;
        String displayName;
        String description;
        java.util.function.Supplier<JComponent> factory;

        ComponentTemplate(String componentType, String displayName, String description,
                         java.util.function.Supplier<JComponent> factory) {
            this.componentType = componentType;
            this.displayName = displayName;
            this.description = description;
            this.factory = factory;
        }
    }

    /**
     * Custom canvas for designing interfaces with drag and drop.
     */
    private class DesignCanvas extends JPanel {
        private List<JComponent> placedComponents = new ArrayList<>();

        public DesignCanvas() {
            setLayout(null); // Absolute positioning
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // Enable drop
            setDropTarget(new DropTarget(this, new CanvasDropTargetListener()));
        }

        public void clearCanvas() {
            placedComponents.clear();
            removeAll();
            repaint();
        }

        public void addComponentAt(String componentType, Point location) {
            JComponent component = createComponentFromType(componentType);
            if (component != null) {
                component.setBounds(location.x, location.y, component.getPreferredSize().width,
                                  component.getPreferredSize().height);
                add(component);
                placedComponents.add(component);

                // Make component movable
                ComponentMover mover = new ComponentMover(component, this);
                component.addMouseListener(mover);
                component.addMouseMotionListener(mover);

                repaint();
            }
        }

        private JComponent createComponentFromType(String componentType) {
            for (ComponentTemplate template : componentTemplates) {
                if (template.componentType.equals(componentType)) {
                    return template.factory.get();
                }
            }
            return null;
        }

        public void editComponentProperties(JComponent component) {
            String newText = JOptionPane.showInputDialog(this, "Editar texto del componente:",
                                                       getComponentText(component));
            if (newText != null) {
                setComponentText(component, newText);
                repaint();
            }
        }

        private String getComponentText(JComponent component) {
            if (component instanceof JButton) return ((JButton) component).getText();
            if (component instanceof JTextField) return ((JTextField) component).getText();
            if (component instanceof JLabel) return ((JLabel) component).getText();
            if (component instanceof JCheckBox) return ((JCheckBox) component).getText();
            if (component instanceof JRadioButton) return ((JRadioButton) component).getText();
            return "";
        }

        private void setComponentText(JComponent component, String text) {
            if (component instanceof JButton) ((JButton) component).setText(text);
            else if (component instanceof JTextField) ((JTextField) component).setText(text);
            else if (component instanceof JLabel) ((JLabel) component).setText(text);
            else if (component instanceof JCheckBox) ((JCheckBox) component).setText(text);
            else if (component instanceof JRadioButton) ((JRadioButton) component).setText(text);
        }

        private class CanvasDropTargetListener extends DropTargetAdapter {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    Transferable tr = dtde.getTransferable();
                    String componentType = (String) tr.getTransferData(DataFlavor.stringFlavor);

                    if (componentType != null) {
                        Point location = dtde.getLocation();
                        addComponentAt(componentType, location);
                        dtde.dropComplete(true);
                    }
                } catch (Exception e) {
                    dtde.dropComplete(false);
                }
            }
        }
    }

    /**
     * Transfer handler for the canvas.
     */
    private class CanvasTransferHandler extends TransferHandler {
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            try {
                String componentType = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                Point dropPoint = support.getDropLocation().getDropPoint();
                designCanvas.addComponentAt(componentType, dropPoint);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * Helper class for moving components around the canvas.
     */
    private class ComponentMover extends MouseAdapter {
        private JComponent component;
        private DesignCanvas canvas;
        private Point initialClick;

        public ComponentMover(JComponent component, DesignCanvas canvas) {
            this.component = component;
            this.canvas = canvas;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            initialClick = e.getPoint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                canvas.editComponentProperties(component);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (initialClick != null) {
                int deltaX = e.getX() - initialClick.x;
                int deltaY = e.getY() - initialClick.y;
                Rectangle bounds = component.getBounds();
                bounds.translate(deltaX, deltaY);
                component.setBounds(bounds);
                canvas.repaint();
            }
        }
    }
}