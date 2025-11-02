/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import retos.learnswing.auth.UserManager;


/**
 * ChartsPanel for teaching graphics and data visualization in Java Swing.
 *
 * @author jocde
 */
public class ChartsPanel extends JPanel {

    /**
     * Constructor for ChartsPanel.
     * Creates an enhanced lesson panel for learning graphics and charts.
     */
    public ChartsPanel() {
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

        // Source code tab
        tabbedPane.addTab("📄 Código Fuente", createSourceCodePanel());

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

        theoryText.setText("🎯 GRÁFICOS Y VISUALIZACIÓN DE DATOS EN JAVA SWING\n\n" +
                "Java Swing proporciona poderosas capacidades gráficas para crear visualizaciones de datos.\n\n" +
                "🎨 Graphics2D - Motor Gráfico Principal:\n" +
                "• API avanzada para dibujar formas, texto e imágenes\n" +
                "• Soporte para transformaciones (rotación, escala, traslación)\n" +
                "• Control de calidad de renderizado (antialiasing)\n" +
                "• Sistema de coordenadas personalizable\n\n" +
                "📊 Tipos de Gráficos Comunes:\n" +
                "• Barras: Comparación de valores discretos\n" +
                "• Líneas: Tendencias y evolución temporal\n" +
                "• Circular: Proporciones y porcentajes\n" +
                "• Área: Acumulación de valores\n" +
                "• Scatter: Relaciones entre variables\n\n" +
                "🛠️ Componentes para Gráficos:\n" +
                "• JPanel personalizado con paintComponent()\n" +
                "• BufferedImage para renderizado off-screen\n" +
                "• Graphics2D con configuraciones avanzadas\n" +
                "• Sistema de coordenadas y escalas\n\n" +
                "📈 Conceptos Fundamentales:\n" +
                "• Transformaciones afines (translate, rotate, scale)\n" +
                "• Sistema de coordenadas (origen, ejes)\n" +
                "• Espacio de color y composición\n" +
                "• Renderizado y optimización de rendimiento");

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

        // Example 1: Basic Graphics2D
        JPanel example1 = new JPanel(new BorderLayout());
        example1.setBorder(BorderFactory.createTitledBorder("Ejemplo 1: Gráficos Básicos con Graphics2D"));

        JTextArea code1 = new JTextArea();
        code1.setEditable(false);
        code1.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code1.setText("// JPanel personalizado para dibujar\n" +
                "class DrawingPanel extends JPanel {\n" +
                "    @Override\n" +
                "    protected void paintComponent(Graphics g) {\n" +
                "        super.paintComponent(g);\n" +
                "        Graphics2D g2d = (Graphics2D) g;\n" +
                "        \n" +
                "        // Configurar calidad de renderizado\n" +
                "        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,\n" +
                "            RenderingHints.VALUE_ANTIALIAS_ON);\n" +
                "        \n" +
                "        // Dibujar formas\n" +
                "        g2d.setColor(Color.BLUE);\n" +
                "        g2d.fill(new Rectangle2D.Double(50, 50, 100, 60));\n" +
                "        \n" +
                "        g2d.setColor(Color.RED);\n" +
                "        g2d.fill(new Ellipse2D.Double(200, 50, 80, 80));\n" +
                "        \n" +
                "        // Dibujar texto\n" +
                "        g2d.setColor(Color.BLACK);\n" +
                "        g2d.setFont(new Font(\"Arial\", Font.BOLD, 16));\n" +
                "        g2d.drawString(\"Hola Swing!\", 50, 150);\n" +
                "    }\n" +
                "}");
        code1.setBackground(Color.BLACK);
        code1.setForeground(Color.GREEN);

        // Interactive drawing panel
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Enable antialiasing
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw shapes
                g2d.setColor(Color.BLUE);
                g2d.fill(new Rectangle2D.Double(20, 20, 80, 50));

                g2d.setColor(Color.RED);
                g2d.fill(new Ellipse2D.Double(150, 20, 60, 60));

                g2d.setColor(Color.GREEN);
                g2d.fill(new Rectangle2D.Double(250, 20, 70, 70));

                // Draw text
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString("Formas Básicas", 20, 100);
            }
        };
        drawingPanel.setPreferredSize(new Dimension(350, 120));
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        example1.add(code1, BorderLayout.NORTH);
        example1.add(drawingPanel, BorderLayout.CENTER);

        // Example 2: Bar Chart
        JPanel example2 = new JPanel(new BorderLayout());
        example2.setBorder(BorderFactory.createTitledBorder("Ejemplo 2: Gráfico de Barras Interactivo"));

        JTextArea code2 = new JTextArea();
        code2.setEditable(false);
        code2.setFont(new Font("Monospaced", Font.PLAIN, 11));
        code2.setText("// Gráfico de barras personalizado\n" +
                "class BarChartPanel extends JPanel {\n" +
                "    private int[] data = {45, 67, 23, 89, 34, 78};\n" +
                "    private String[] labels = {\"Ene\", \"Feb\", \"Mar\", \"Abr\", \"May\", \"Jun\"};\n" +
                "    \n" +
                "    @Override\n" +
                "    protected void paintComponent(Graphics g) {\n" +
                "        super.paintComponent(g);\n" +
                "        Graphics2D g2d = (Graphics2D) g;\n" +
                "        \n" +
                "        int width = getWidth();\n" +
                "        int height = getHeight();\n" +
                "        int barWidth = width / data.length;\n" +
                "        \n" +
                "        for (int i = 0; i < data.length; i++) {\n" +
                "            int barHeight = (int) (data[i] * height / 100.0);\n" +
                "            int x = i * barWidth;\n" +
                "            int y = height - barHeight;\n" +
                "            \n" +
                "            g2d.setColor(Color.BLUE);\n" +
                "            g2d.fillRect(x + 2, y, barWidth - 4, barHeight);\n" +
                "            \n" +
                "            g2d.setColor(Color.BLACK);\n" +
                "            g2d.drawString(labels[i], x + barWidth/2 - 10, height - 5);\n" +
                "        }\n" +
                "    }\n" +
                "}");
        code2.setBackground(Color.BLACK);
        code2.setForeground(Color.GREEN);

        // Interactive bar chart
        JPanel barChartPanel = new JPanel() {
            private int[] data = {45, 67, 23, 89, 34, 78};
            private String[] labels = {"Ene", "Feb", "Mar", "Abr", "May", "Jun"};
            private Color[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN};

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int barWidth = width / data.length;

                for (int i = 0; i < data.length; i++) {
                    int barHeight = (int) (data[i] * (height - 40) / 100.0);
                    int x = i * barWidth;
                    int y = height - 30 - barHeight;

                    g2d.setColor(colors[i % colors.length]);
                    g2d.fillRect(x + 2, y, barWidth - 4, barHeight);

                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(x + 2, y, barWidth - 4, barHeight);

                    // Draw value
                    g2d.drawString(String.valueOf(data[i]), x + barWidth/2 - 5, y - 5);

                    // Draw label
                    g2d.drawString(labels[i], x + barWidth/2 - 10, height - 10);
                }
            }
        };
        barChartPanel.setPreferredSize(new Dimension(350, 150));
        barChartPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel chartControls = new JPanel(new FlowLayout());
        JButton randomizeBtn = new JButton("Datos Aleatorios");
        randomizeBtn.addActionListener(e -> {
            Random rand = new Random();
            try {
                java.lang.reflect.Field dataField = barChartPanel.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                int[] data = (int[]) dataField.get(barChartPanel);
                for (int i = 0; i < data.length; i++) {
                    data[i] = rand.nextInt(100) + 1;
                }
            } catch (Exception ex) {
                System.err.println("Could not update bar chart data: " + ex.getMessage());
            }
            barChartPanel.repaint();
        });
        chartControls.add(randomizeBtn);

        example2.add(code2, BorderLayout.NORTH);
        example2.add(barChartPanel, BorderLayout.CENTER);
        example2.add(chartControls, BorderLayout.SOUTH);

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

        // Shape drawing practice
        JPanel shapeSection = new JPanel(new BorderLayout());
        shapeSection.setBorder(BorderFactory.createTitledBorder("Práctica: Dibujo de Formas Interactivo"));

        JPanel shapeControls = new JPanel(new FlowLayout());
        JButton rectBtn = new JButton("Rectángulo");
        JButton circleBtn = new JButton("Círculo");
        JButton lineBtn = new JButton("Línea");
        JButton clearBtn = new JButton("Limpiar");

        // Drawing canvas
        JPanel canvas = new JPanel() {
            private List<Shape> shapes = new ArrayList<>();
            private String currentShape = "rectangle";

            {
                rectBtn.addActionListener(e -> {
                    currentShape = "rectangle";
                    repaint();
                });
                circleBtn.addActionListener(e -> {
                    currentShape = "circle";
                    repaint();
                });
                lineBtn.addActionListener(e -> {
                    currentShape = "line";
                    repaint();
                });
                clearBtn.addActionListener(e -> {
                    shapes.clear();
                    repaint();
                });

                addMouseListener(new java.awt.event.MouseAdapter() {
                    private int startX, startY;

                    @Override
                    public void mousePressed(java.awt.event.MouseEvent e) {
                        startX = e.getX();
                        startY = e.getY();
                    }

                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent e) {
                        int endX = e.getX();
                        int endY = e.getY();

                        Shape shape;
                        switch (currentShape) {
                            case "rectangle":
                                shape = new Rectangle2D.Double(
                                    Math.min(startX, endX), Math.min(startY, endY),
                                    Math.abs(endX - startX), Math.abs(endY - startY));
                                break;
                            case "circle":
                                int diameter = Math.max(Math.abs(endX - startX), Math.abs(endY - startY));
                                shape = new Ellipse2D.Double(
                                    Math.min(startX, endX), Math.min(startY, endY),
                                    diameter, diameter);
                                break;
                            case "line":
                            default:
                                shape = new java.awt.geom.Line2D.Double(startX, startY, endX, endY);
                                break;
                        }
                        shapes.add(shape);
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(2));

                for (Shape shape : shapes) {
                    if (shape instanceof Rectangle2D) {
                        g2d.fill(shape);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(shape);
                        g2d.setColor(Color.BLUE);
                    } else if (shape instanceof Ellipse2D) {
                        g2d.fill(shape);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(shape);
                        g2d.setColor(Color.BLUE);
                    } else if (shape instanceof java.awt.geom.Line2D) {
                        g2d.setColor(Color.BLACK);
                        g2d.draw(shape);
                        g2d.setColor(Color.BLUE);
                    }
                }
            }
        };
        canvas.setPreferredSize(new Dimension(400, 200));
        canvas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        canvas.setBackground(Color.WHITE);

        shapeControls.add(rectBtn);
        shapeControls.add(circleBtn);
        shapeControls.add(lineBtn);
        shapeControls.add(clearBtn);

        shapeSection.add(shapeControls, BorderLayout.NORTH);
        shapeSection.add(canvas, BorderLayout.CENTER);

        // Pie chart practice
        JPanel pieSection = new JPanel(new BorderLayout());
        pieSection.setBorder(BorderFactory.createTitledBorder("Práctica: Gráfico Circular (Pie Chart)"));

        JPanel pieChartPanel = new JPanel() {
            private double[] data = {25, 35, 20, 20};
            private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
            private String[] labels = {"Producto A", "Producto B", "Producto C", "Producto D"};

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int diameter = Math.min(width, height) - 40;
                int centerX = width / 2;
                int centerY = height / 2;

                double total = 0;
                for (double value : data) total += value;

                double startAngle = 0;
                for (int i = 0; i < data.length; i++) {
                    double angle = (data[i] / total) * 360;

                    g2d.setColor(colors[i % colors.length]);
                    g2d.fillArc(centerX - diameter/2, centerY - diameter/2,
                              diameter, diameter, (int) startAngle, (int) angle);

                    g2d.setColor(Color.BLACK);
                    g2d.drawArc(centerX - diameter/2, centerY - diameter/2,
                              diameter, diameter, (int) startAngle, (int) angle);

                    // Draw label
                    double labelAngle = startAngle + angle / 2;
                    int labelX = (int) (centerX + (diameter/2 + 20) * Math.cos(Math.toRadians(labelAngle - 90)));
                    int labelY = (int) (centerY + (diameter/2 + 20) * Math.sin(Math.toRadians(labelAngle - 90)));
                    g2d.drawString(labels[i] + " (" + (int) data[i] + "%)", labelX - 20, labelY);

                    startAngle += angle;
                }
            }
        };
        pieChartPanel.setPreferredSize(new Dimension(400, 250));
        pieChartPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel pieControls = new JPanel(new GridLayout(2, 2, 5, 5));

        // Value inputs
        JTextField value1Field = new JTextField("25", 5);
        JTextField value2Field = new JTextField("35", 5);
        JTextField value3Field = new JTextField("20", 5);
        JTextField value4Field = new JTextField("20", 5);

        pieControls.add(new JLabel("Producto A:"));
        pieControls.add(value1Field);
        pieControls.add(new JLabel("Producto B:"));
        pieControls.add(value2Field);
        pieControls.add(new JLabel("Producto C:"));
        pieControls.add(value3Field);
        pieControls.add(new JLabel("Producto D:"));
        pieControls.add(value4Field);

        JButton updatePieBtn = new JButton("Actualizar Gráfico");
        updatePieBtn.addActionListener(e -> {
            try {
                double[] newData = {
                    Double.parseDouble(value1Field.getText()),
                    Double.parseDouble(value2Field.getText()),
                    Double.parseDouble(value3Field.getText()),
                    Double.parseDouble(value4Field.getText())
                };
                // Update the chart data using reflection
                try {
                    java.lang.reflect.Field dataField = pieChartPanel.getClass().getDeclaredField("data");
                    dataField.setAccessible(true);
                    dataField.set(pieChartPanel, newData);
                } catch (Exception ex) {
                    System.err.println("Could not update pie chart data: " + ex.getMessage());
                }
                pieChartPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos");
            }
        });

        JButton randomizePieBtn = new JButton("Datos Aleatorios");
        randomizePieBtn.addActionListener(e -> {
            Random rand = new Random();
            double[] newData = new double[4];
            for (int i = 0; i < newData.length; i++) {
                newData[i] = rand.nextInt(50) + 10;
            }
            // Update the chart data using reflection
            try {
                java.lang.reflect.Field dataField = pieChartPanel.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                dataField.set(pieChartPanel, newData);
            } catch (Exception ex) {
                System.err.println("Could not update pie chart data: " + ex.getMessage());
            }
            // Update text fields
            value1Field.setText(String.valueOf((int)newData[0]));
            value2Field.setText(String.valueOf((int)newData[1]));
            value3Field.setText(String.valueOf((int)newData[2]));
            value4Field.setText(String.valueOf((int)newData[3]));
            pieChartPanel.repaint();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(updatePieBtn);
        buttonPanel.add(randomizePieBtn);

        pieSection.add(pieChartPanel, BorderLayout.CENTER);
        pieSection.add(pieControls, BorderLayout.SOUTH);

        // Line chart practice
        JPanel lineSection = new JPanel(new BorderLayout());
        lineSection.setBorder(BorderFactory.createTitledBorder("Práctica: Gráfico de Líneas"));

        JPanel lineChartPanel = new JPanel() {
            private int[] data = {20, 35, 25, 45, 30, 60, 40, 70};

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();
                int margin = 40;
                int plotWidth = width - 2 * margin;
                int plotHeight = height - 2 * margin;

                // Draw axes
                g2d.setColor(Color.BLACK);
                g2d.drawLine(margin, height - margin, width - margin, height - margin); // X-axis
                g2d.drawLine(margin, height - margin, margin, margin); // Y-axis

                // Draw grid
                g2d.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i <= 5; i++) {
                    int y = height - margin - (i * plotHeight / 5);
                    g2d.drawLine(margin, y, width - margin, y);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(String.valueOf(i * 20), margin - 25, y + 5);
                    g2d.setColor(Color.LIGHT_GRAY);
                }

                // Draw data points and lines
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(2));

                int prevX = margin;
                int prevY = height - margin - (data[0] * plotHeight / 100);

                for (int i = 0; i < data.length; i++) {
                    int x = margin + (i * plotWidth / (data.length - 1));
                    int y = height - margin - (data[i] * plotHeight / 100);

                    // Draw line to previous point
                    if (i > 0) {
                        g2d.drawLine(prevX, prevY, x, y);
                    }

                    // Draw data point
                    g2d.setColor(Color.RED);
                    g2d.fillOval(x - 3, y - 3, 6, 6);
                    g2d.setColor(Color.BLUE);

                    prevX = x;
                    prevY = y;
                }
            }
        };
        lineChartPanel.setPreferredSize(new Dimension(400, 200));
        lineChartPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel lineControls = new JPanel(new GridLayout(3, 4, 5, 5));

        // Value inputs for line chart
        JTextField[] lineFields = new JTextField[8];
        String[] labels = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago"};

        for (int i = 0; i < lineFields.length; i++) {
            lineControls.add(new JLabel(labels[i] + ":"));
            lineFields[i] = new JTextField(String.valueOf(20 + i * 5), 3); // Default values
            lineControls.add(lineFields[i]);
        }

        JButton updateLineBtn = new JButton("Actualizar Gráfico");
        updateLineBtn.addActionListener(e -> {
            try {
                int[] newData = new int[lineFields.length];
                for (int i = 0; i < lineFields.length; i++) {
                    newData[i] = Integer.parseInt(lineFields[i].getText());
                }
                // Update the chart data using reflection
                try {
                    java.lang.reflect.Field dataField = lineChartPanel.getClass().getDeclaredField("data");
                    dataField.setAccessible(true);
                    dataField.set(lineChartPanel, newData);
                } catch (Exception ex) {
                    System.err.println("Could not update line chart data: " + ex.getMessage());
                }
                lineChartPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos");
            }
        });

        JButton randomizeLineBtn = new JButton("Datos Aleatorios");
        randomizeLineBtn.addActionListener(e -> {
            Random rand = new Random();
            int[] newData = new int[lineFields.length];
            for (int i = 0; i < newData.length; i++) {
                newData[i] = rand.nextInt(80) + 10;
                lineFields[i].setText(String.valueOf(newData[i]));
            }
            // Update the chart data using reflection
            try {
                java.lang.reflect.Field dataField = lineChartPanel.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                dataField.set(lineChartPanel, newData);
            } catch (Exception ex) {
                System.err.println("Could not update line chart data: " + ex.getMessage());
            }
            lineChartPanel.repaint();
        });

        JPanel lineButtonPanel = new JPanel(new FlowLayout());
        lineButtonPanel.add(updateLineBtn);
        lineButtonPanel.add(randomizeLineBtn);

        lineSection.add(lineChartPanel, BorderLayout.CENTER);
        lineSection.add(lineControls, BorderLayout.SOUTH);
        lineSection.add(lineButtonPanel, BorderLayout.SOUTH);

        practicePanel.add(shapeSection);
        practicePanel.add(pieSection);
        practicePanel.add(lineSection);

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
        JLabel titleLabel = new JLabel("🎯 Quiz: Gráficos y Visualización", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        quizPanel.add(titleLabel, BorderLayout.NORTH);

        // Quiz description
        JTextArea descArea = new JTextArea();
        descArea.setEditable(false);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descArea.setBackground(getBackground());
        descArea.setText("Evalúa tus conocimientos sobre Graphics2D, gráficos de barras, líneas y circulares.\n\n" +
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
            JFrame quizFrame = new JFrame("Quiz: Gráficos y Visualización");
            quizFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            quizFrame.setSize(700, 500);
            quizFrame.setLocationRelativeTo(null);

            QuizPanel quiz = new QuizPanel("Gráficos", UserManager.getCurrentUserStats());
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

        tipsText.setText("💡 CONSEJOS Y MEJORES PRÁCTICAS PARA GRÁFICOS EN SWING\n\n" +
                "✅ CONCEPTOS FUNDAMENTALES:\n" +
                "• Siempre llamar super.paintComponent(g) primero\n" +
                "• Usar Graphics2D en lugar de Graphics para features avanzadas\n" +
                "• Habilitar antialiasing para mejor calidad visual\n" +
                "• Gestionar el ciclo de vida del componente correctamente\n\n" +
                "🚀 MEJORES PRÁCTICAS:\n" +
                "• Calcular posiciones y tamaños en paintComponent()\n" +
                "• Usar RenderingHints para optimizar calidad/rendimiento\n" +
                "• Implementar buffering doble para animaciones complejas\n" +
                "• Manejar el redimensionamiento del componente\n" +
                "• Usar transformaciones para efectos especiales\n\n" +
                "⚠️ ERRORES COMUNES:\n" +
                "• Dibujar fuera de paintComponent() → gráficos desaparecen\n" +
                "• No restaurar el estado de Graphics2D → efectos secundarios\n" +
                "• Ignorar el clipping → rendimiento pobre\n" +
                "• Hardcodear posiciones → no responsive\n\n" +
                "🔧 OPTIMIZACIONES:\n" +
                "• Usar BufferedImage para gráficos complejos\n" +
                "• Implementar dirty regions para repaints selectivos\n" +
                "• Cachear cálculos costosos entre repaints\n" +
                "• Usar SwingWorker para gráficos que requieren cálculo\n\n" +
                "🎯 PRO TIPS:\n" +
                "• Para animaciones: usar Timer con repaints suaves\n" +
                "• Para zoom: implementar transformaciones de escala\n" +
                "• Para datos dinámicos: separar modelo de vista\n" +
                "• Para interactividad: manejar eventos del mouse\n\n" +
                "🎨 TÉCNICAS AVANZADAS:\n" +
                "• Composición: usar AlphaComposite para transparencias\n" +
                "• Gradientes: LinearGradientPaint y RadialGradientPaint\n" +
                "• Texturas: TexturePaint para efectos visuales\n" +
                "• Filtros: ConvolveOp para efectos de imagen");

        JScrollPane scrollPane = new JScrollPane(tipsText);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates the source code panel showing the complete source code of this class.
     */
    private JPanel createSourceCodePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea sourceCodeText = new JTextArea();
        sourceCodeText.setEditable(false);
        sourceCodeText.setFont(new Font("Monospaced", Font.PLAIN, 10));
        sourceCodeText.setBackground(Color.BLACK);
        sourceCodeText.setForeground(Color.GREEN);
        sourceCodeText.setWrapStyleWord(false);
        sourceCodeText.setLineWrap(false);

        // Get the source code content (simplified version for display)
        String sourceCode = "/*\n" +
                " * ChartsPanel for teaching graphics and data visualization in Java Swing.\n" +
                " *\n" +
                " * @author jocde\n" +
                " */\n" +
                "public class ChartsPanel extends JPanel {\n" +
                "\n" +
                "    public ChartsPanel() {\n" +
                "        setLayout(new BorderLayout());\n" +
                "        \n" +
                "        JTabbedPane tabbedPane = new JTabbedPane();\n" +
                "        \n" +
                "        // Theory tab\n" +
                "        tabbedPane.addTab(\"📚 Teoría\", createTheoryPanel());\n" +
                "        \n" +
                "        // Examples tab\n" +
                "        tabbedPane.addTab(\"💡 Ejemplos\", createExamplesPanel());\n" +
                "        \n" +
                "        // Practice tab\n" +
                "        tabbedPane.addTab(\"🎯 Práctica\", createPracticePanel());\n" +
                "        \n" +
                "        // Quiz tab\n" +
                "        tabbedPane.addTab(\"🎯 Quiz\", createQuizPanel());\n" +
                "        \n" +
                "        // Tips tab\n" +
                "        tabbedPane.addTab(\"💡 Consejos\", createTipsPanel());\n" +
                "        \n" +
                "        // Source code tab\n" +
                "        tabbedPane.addTab(\"📄 Código Fuente\", createSourceCodePanel());\n" +
                "        \n" +
                "        add(tabbedPane, BorderLayout.CENTER);\n" +
                "    }\n" +
                "\n" +
                "    // ... (other methods: createTheoryPanel, createExamplesPanel, etc.)\n" +
                "\n" +
                "    private JPanel createSourceCodePanel() {\n" +
                "        // This method displays the source code of the ChartsPanel class\n" +
                "        // Implementation details...\n" +
                "    }\n" +
                "}\n";

        sourceCodeText.setText(sourceCode);

        JScrollPane scrollPane = new JScrollPane(sourceCodeText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}