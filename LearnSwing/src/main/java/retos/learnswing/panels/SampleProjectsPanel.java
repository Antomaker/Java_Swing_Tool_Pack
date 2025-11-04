/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.panels;

import java.awt.*;
import javax.swing.*;


/**
 * Sample Projects Panel - Complete project examples for students to practice
 * with real-world applications: User Manager, Library System, Shopping Center Inventory,
 * and Pharmacy Management System.
 *
 * @author jocde
 */
public class SampleProjectsPanel extends JPanel {

    private JTabbedPane mainTabbedPane;
    private JComboBox<String> projectSelector;
    private JTextArea descriptionArea;

    // Project data
    private final String[] projectNames = {
        "Gestor de Usuarios",
        "Sistema de Biblioteca",
        "Inventario Centro Comercial",
        "Sistema de Farmacia"
    };

    private final String[] projectDescriptions = {
        "Sistema completo para gestión de usuarios con registro, login, perfiles y administración.",
        "Sistema de biblioteca con gestión de libros, préstamos, usuarios y reportes.",
        "Sistema de inventario para centro comercial con productos, categorías y ventas.",
        "Sistema de gestión farmacéutica con medicamentos, proveedores y control de stock."
    };

    /**
     * Constructor for SampleProjectsPanel.
     */
    public SampleProjectsPanel() {
        setLayout(new BorderLayout());
        initializeComponents();
    }

    /**
     * Initializes all components of the sample projects panel.
     */
    private void initializeComponents() {
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Main content
        mainTabbedPane = new JTabbedPane();

        // Project overview tab
        mainTabbedPane.addTab("📋 Descripción de Proyectos", createOverviewPanel());

        // Individual project tabs
        mainTabbedPane.addTab("👥 Gestor de Usuarios", createUserManagerProject());
        mainTabbedPane.addTab("📚 Biblioteca", createLibraryProject());
        mainTabbedPane.addTab("🏪 Inventario", createInventoryProject());
        mainTabbedPane.addTab("💊 Farmacia", createPharmacyProject());

        // Practice tab
        mainTabbedPane.addTab("🎯 Práctica", createPracticePanel());

        add(mainTabbedPane, BorderLayout.CENTER);
    }

    /**
     * Creates the header panel with project selector.
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel selectorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectorPanel.add(new JLabel("Seleccionar Proyecto:"));
        projectSelector = new JComboBox<>(projectNames);
        projectSelector.addActionListener(e -> updateProjectDescription());
        selectorPanel.add(projectSelector);

        JButton viewProjectBtn = new JButton("Ver Proyecto Completo");
        viewProjectBtn.addActionListener(e -> showSelectedProject());
        selectorPanel.add(viewProjectBtn);

        headerPanel.add(selectorPanel, BorderLayout.WEST);

        // Progress indicator
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        progressPanel.add(new JLabel("Progreso: "));

        // Mock progress for different projects (simulated)
        int currentProgress = (int) (Math.random() * 100); // Simulated progress

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(currentProgress);
        progressBar.setStringPainted(true);
        progressBar.setString(currentProgress + "%");
        progressPanel.add(progressBar);

        headerPanel.add(progressPanel, BorderLayout.EAST);

        return headerPanel;
    }

    /**
     * Creates the project overview panel.
     */
    private JPanel createOverviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Project description area
        descriptionArea = new JTextArea();
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionArea.setBackground(getBackground());

        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(600, 200));
        descScroll.setBorder(BorderFactory.createTitledBorder("Descripción del Proyecto"));

        // Project cards
        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        cardsPanel.setBorder(BorderFactory.createTitledBorder("Proyectos Disponibles"));

        String[] icons = {"👥", "📚", "🏪", "💊"};
        String[] shortDescs = {
            "Gestión completa de usuarios del sistema",
            "Control de libros y préstamos bibliotecarios",
            "Inventario y ventas de productos comerciales",
            "Administración de medicamentos y stock"
        };

        for (int i = 0; i < projectNames.length; i++) {
            JPanel card = createProjectCard(icons[i], projectNames[i], shortDescs[i], i);
            cardsPanel.add(card);
        }

        JScrollPane cardsScroll = new JScrollPane(cardsPanel);

        // Split between description and cards
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.4);
        splitPane.setTopComponent(descScroll);
        splitPane.setBottomComponent(cardsScroll);

        panel.add(splitPane, BorderLayout.CENTER);

        // Initialize description
        updateProjectDescription();

        return panel;
    }

    /**
     * Creates a project card for the overview.
     */
    private JPanel createProjectCard(String icon, String title, String description, int projectIndex) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        // Title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titlePanel.add(iconLabel);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titlePanel.add(titleLabel);

        card.add(titlePanel, BorderLayout.NORTH);

        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(descArea, BorderLayout.CENTER);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton exploreBtn = new JButton("Explorar");
        exploreBtn.addActionListener(e -> mainTabbedPane.setSelectedIndex(projectIndex + 1));
        buttonPanel.add(exploreBtn);

        JButton practiceBtn = new JButton("Practicar");
        practiceBtn.addActionListener(e -> {
            mainTabbedPane.setSelectedIndex(5); // Practice tab
            // Could set specific project here
        });
        buttonPanel.add(practiceBtn);

        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    /**
     * Updates the project description based on selection.
     */
    private void updateProjectDescription() {
        int selectedIndex = projectSelector.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < projectDescriptions.length) {
            descriptionArea.setText(projectDescriptions[selectedIndex]);
        }
    }

    /**
     * Shows the selected project in the tabbed pane.
     */
    private void showSelectedProject() {
        int selectedIndex = projectSelector.getSelectedIndex();
        if (selectedIndex >= 0) {
            mainTabbedPane.setSelectedIndex(selectedIndex + 1); // +1 because overview is first
        }
    }

    /**
     * Creates the User Manager project panel.
     */
    private JPanel createUserManagerProject() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane projectTabs = new JTabbedPane();

        // Project structure
        projectTabs.addTab("📁 Estructura", createUserManagerStructure());

        // Classes
        projectTabs.addTab("👤 User", createUserManagerUserClass());
        projectTabs.addTab("👥 UserManager", createUserManagerManagerClass());
        projectTabs.addTab("🔐 AuthService", createUserManagerAuthClass());
        projectTabs.addTab("🎛️ UserInterface", createUserManagerUIClass());

        // Complete project
        projectTabs.addTab("🚀 Proyecto Completo", createUserManagerComplete());

        panel.add(projectTabs, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates the Library System project panel.
     */
    private JPanel createLibraryProject() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane projectTabs = new JTabbedPane();

        // Project structure
        projectTabs.addTab("📁 Estructura", createLibraryStructure());

        // Classes
        projectTabs.addTab("📖 Book", createLibraryBookClass());
        projectTabs.addTab("👤 Member", createLibraryMemberClass());
        projectTabs.addTab("📚 LibraryManager", createLibraryManagerClass());
        projectTabs.addTab("🎛️ LibraryUI", createLibraryUIClass());

        // Complete project
        projectTabs.addTab("🚀 Proyecto Completo", createLibraryComplete());

        panel.add(projectTabs, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates the Inventory project panel.
     */
    private JPanel createInventoryProject() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane projectTabs = new JTabbedPane();

        // Project structure
        projectTabs.addTab("📁 Estructura", createInventoryStructure());

        // Classes
        projectTabs.addTab("📦 Product", createInventoryProductClass());
        projectTabs.addTab("🏪 Store", createInventoryStoreClass());
        projectTabs.addTab("📊 InventoryManager", createInventoryManagerClass());
        projectTabs.addTab("🎛️ InventoryUI", createInventoryUIClass());

        // Complete project
        projectTabs.addTab("🚀 Proyecto Completo", createInventoryComplete());

        panel.add(projectTabs, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates the Pharmacy project panel.
     */
    private JPanel createPharmacyProject() {
        JPanel panel = new JPanel(new BorderLayout());

        JTabbedPane projectTabs = new JTabbedPane();

        // Project structure
        projectTabs.addTab("📁 Estructura", createPharmacyStructure());

        // Classes
        projectTabs.addTab("💊 Medicine", createPharmacyMedicineClass());
        projectTabs.addTab("🏥 Pharmacy", createPharmacyPharmacyClass());
        projectTabs.addTab("📋 PharmacyManager", createPharmacyManagerClass());
        projectTabs.addTab("🎛️ PharmacyUI", createPharmacyUIClass());

        // Complete project
        projectTabs.addTab("🚀 Proyecto Completo", createPharmacyComplete());

        panel.add(projectTabs, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates the practice panel.
     */
    private JPanel createPracticePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header with instructions
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createTitledBorder("🎯 Zona de Práctica Interactiva"));

        JTextArea instructionsArea = new JTextArea();
        instructionsArea.setEditable(false);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setLineWrap(true);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionsArea.setBackground(getBackground());
        instructionsArea.setText("INSTRUCCIONES PARA LA PRÁCTICA:\n\n" +
                "1. Selecciona un ejercicio de la lista inferior\n" +
                "2. Lee la descripción y requisitos del ejercicio\n" +
                "3. Haz clic en 'Comenzar Ejercicio' para abrir el entorno de práctica\n" +
                "4. Implementa el código requerido en el editor\n" +
                "5. Usa las pistas si necesitas ayuda\n" +
                "6. Ejecuta y prueba tu solución\n" +
                "7. Compara con la solución cuando termines\n\n" +
                "Cada ejercicio incluye:\n" +
                "• Descripción detallada del problema\n" +
                "• Código base para comenzar\n" +
                "• Pistas paso a paso\n" +
                "• Solución completa para comparar\n" +
                "• Validaciones automáticas");
        headerPanel.add(new JScrollPane(instructionsArea), BorderLayout.CENTER);

        // Practice exercises grid
        JPanel practicePanel = new JPanel(new GridLayout(2, 2, 20, 20));
        practicePanel.setBorder(BorderFactory.createTitledBorder("Ejercicios Disponibles"));

        // Practice exercises for each project
        String[] practiceTitles = {
            "Sistema de Login Seguro",
            "Gestión de Préstamos Bibliotecarios",
            "Sistema de Ventas con Carrito",
            "Control de Inventario Farmacéutico"
        };

        String[] practiceDescs = {
            "Implementa autenticación con hash de contraseñas, validaciones y manejo de sesiones.",
            "Crea sistema de préstamos con fechas límite, renovaciones y penalizaciones.",
            "Desarrolla carrito de compras con descuentos, impuestos y confirmación de pago.",
            "Implementa alertas de vencimiento, control de recetas y reportes de stock."
        };

        String[] difficulties = {"Intermedio", "Avanzado", "Avanzado", "Experto"};
        String[] estimatedTimes = {"45 min", "60 min", "75 min", "90 min"};

        for (int i = 0; i < practiceTitles.length; i++) {
            JPanel exerciseCard = createPracticeCard(practiceTitles[i], practiceDescs[i],
                                                   difficulties[i], estimatedTimes[i], i);
            practicePanel.add(exerciseCard);
        }

        JScrollPane scrollPane = new JScrollPane(practicePanel);

        // Split between instructions and exercises
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.4);
        splitPane.setTopComponent(headerPanel);
        splitPane.setBottomComponent(scrollPane);

        panel.add(splitPane, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Creates a practice exercise card.
     */
    private JPanel createPracticeCard(String title, String description, String difficulty,
                                    String estimatedTime, int exerciseIndex) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(getDifficultyColor(difficulty), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));

        // Title and difficulty
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel difficultyLabel = new JLabel("Dificultad: " + difficulty);
        difficultyLabel.setForeground(getDifficultyColor(difficulty));
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 11));
        difficultyPanel.add(difficultyLabel);

        JLabel timeLabel = new JLabel("⏱️ " + estimatedTime);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        difficultyPanel.add(timeLabel);

        titlePanel.add(difficultyPanel, BorderLayout.EAST);
        card.add(titlePanel, BorderLayout.NORTH);

        // Description
        JTextArea descArea = new JTextArea(description);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        descArea.setFont(new Font("Arial", Font.PLAIN, 12));
        card.add(descArea, BorderLayout.CENTER);

        // Progress and action buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Progress bar (simulated)
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue((int) (Math.random() * 40)); // Simulated partial progress
        progressBar.setStringPainted(true);
        progressBar.setString("Progreso: " + progressBar.getValue() + "%");
        progressBar.setPreferredSize(new Dimension(-1, 20));
        bottomPanel.add(progressBar, BorderLayout.NORTH);

        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton startBtn = new JButton("🚀 Comenzar Ejercicio");
        startBtn.addActionListener(e -> startPracticeExercise(exerciseIndex));
        buttonPanel.add(startBtn);

        JButton hintBtn = new JButton("💡 Pistas");
        hintBtn.addActionListener(e -> showPracticeHints(exerciseIndex));
        buttonPanel.add(hintBtn);

        JButton solutionBtn = new JButton("✅ Ver Solución");
        solutionBtn.addActionListener(e -> showPracticeSolution(exerciseIndex));
        buttonPanel.add(solutionBtn);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    /**
     * Gets the color for difficulty level.
     */
    private Color getDifficultyColor(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "principiante": return Color.GREEN;
            case "intermedio": return Color.BLUE;
            case "avanzado": return Color.ORANGE;
            case "experto": return Color.RED;
            default: return Color.GRAY;
        }
    }

    // Placeholder methods for project structures and classes
    // These will be implemented with actual code examples

    private JPanel createUserManagerStructure() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea structureArea = new JTextArea();
        structureArea.setEditable(false);
        structureArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        structureArea.setBackground(Color.BLACK);
        structureArea.setForeground(Color.GREEN);
        structureArea.setText("📁 UserManagementSystem/\n" +
                "├── 📄 User.java\n" +
                "├── 📄 UserManager.java\n" +
                "├── 📄 AuthService.java\n" +
                "├── 📄 UserInterface.java\n" +
                "└── 📄 Main.java\n\n" +
                "Funcionalidades:\n" +
                "• Registro de usuarios\n" +
                "• Autenticación (login/logout)\n" +
                "• Gestión de perfiles\n" +
                "• Administración de usuarios\n" +
                "• Persistencia de datos");
        panel.add(new JScrollPane(structureArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagerUserClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("public class User {\n" +
                "    private String id;\n" +
                "    private String username;\n" +
                "    private String password;\n" +
                "    private String email;\n" +
                "    private String role;\n" +
                "    private boolean active;\n\n" +
                "    // Constructor, getters y setters\n" +
                "    public User(String username, String password, String email) {\n" +
                "        this.id = UUID.randomUUID().toString();\n" +
                "        this.username = username;\n" +
                "        this.password = password;\n" +
                "        this.email = email;\n" +
                "        this.role = \"USER\";\n" +
                "        this.active = true;\n" +
                "    }\n\n" +
                "    // Getters y setters...\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagerManagerClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("public class UserManager {\n" +
                "    private List<User> users;\n" +
                "    private User currentUser;\n\n" +
                "    public UserManager() {\n" +
                "        this.users = new ArrayList<>();\n" +
                "        loadUsers();\n" +
                "    }\n\n" +
                "    public boolean registerUser(String username, String password, String email) {\n" +
                "        // Validar datos y crear usuario\n" +
                "        if (isUsernameTaken(username)) {\n" +
                "            return false;\n" +
                "        }\n" +
                "        User newUser = new User(username, password, email);\n" +
                "        users.add(newUser);\n" +
                "        saveUsers();\n" +
                "        return true;\n" +
                "    }\n\n" +
                "    public User authenticate(String username, String password) {\n" +
                "        for (User user : users) {\n" +
                "            if (user.getUsername().equals(username) && \n" +
                "                user.getPassword().equals(password)) {\n" +
                "                currentUser = user;\n" +
                "                return user;\n" +
                "            }\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n\n" +
                "    // Más métodos...\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagerAuthClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("public class AuthService {\n" +
                "    private UserManager userManager;\n\n" +
                "    public AuthService(UserManager userManager) {\n" +
                "        this.userManager = userManager;\n" +
                "    }\n\n" +
                "    public boolean login(String username, String password) {\n" +
                "        User user = userManager.authenticate(username, password);\n" +
                "        if (user != null && user.isActive()) {\n" +
                "            // Crear sesión\n" +
                "            SessionManager.createSession(user);\n" +
                "            return true;\n" +
                "        }\n" +
                "        return false;\n" +
                "    }\n\n" +
                "    public void logout() {\n" +
                "        SessionManager.destroySession();\n" +
                "    }\n\n" +
                "    public boolean isAuthenticated() {\n" +
                "        return SessionManager.getCurrentUser() != null;\n" +
                "    }\n\n" +
                "    public boolean hasPermission(String permission) {\n" +
                "        User user = SessionManager.getCurrentUser();\n" +
                "        return user != null && user.hasPermission(permission);\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagerUIClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("public class UserInterface extends JFrame {\n" +
                "    private AuthService authService;\n" +
                "    private JTextField usernameField;\n" +
                "    private JPasswordField passwordField;\n" +
                "    private JButton loginButton;\n\n" +
                "    public UserInterface(AuthService authService) {\n" +
                "        this.authService = authService;\n" +
                "        initializeComponents();\n" +
                "    }\n\n" +
                "    private void initializeComponents() {\n" +
                "        setTitle(\"Sistema de Gestión de Usuarios\");\n" +
                "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                "        setLayout(new BorderLayout());\n\n" +
                "        // Panel de login\n" +
                "        JPanel loginPanel = new JPanel(new GridBagLayout());\n" +
                "        GridBagConstraints gbc = new GridBagConstraints();\n" +
                "        gbc.insets = new Insets(5, 5, 5, 5);\n\n" +
                "        gbc.gridx = 0; gbc.gridy = 0;\n" +
                "        loginPanel.add(new JLabel(\"Usuario:\"), gbc);\n\n" +
                "        gbc.gridx = 1;\n" +
                "        usernameField = new JTextField(15);\n" +
                "        loginPanel.add(usernameField, gbc);\n\n" +
                "        // Más componentes...\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createUserManagerComplete() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton downloadBtn = new JButton("📥 Descargar Proyecto Completo");
        downloadBtn.addActionListener(e -> downloadCompleteProject("UserManager"));
        panel.add(downloadBtn, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Este proyecto incluye:\n\n" +
                "• Sistema completo de gestión de usuarios\n" +
                "• Autenticación y autorización\n" +
                "• Interfaz gráfica completa\n" +
                "• Persistencia de datos\n" +
                "• Manejo de sesiones\n" +
                "• Validaciones y manejo de errores\n\n" +
                "Para practicar:\n" +
                "1. Crea cada clase por separado\n" +
                "2. Implementa los métodos faltantes\n" +
                "3. Conecta las clases entre sí\n" +
                "4. Agrega funcionalidades adicionales");
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryStructure() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea structureArea = new JTextArea();
        structureArea.setEditable(false);
        structureArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        structureArea.setBackground(Color.BLACK);
        structureArea.setForeground(Color.GREEN);
        structureArea.setText("📁 LibrarySystem/\n" +
                "├── 📄 Book.java\n" +
                "├── 📄 Member.java\n" +
                "├── 📄 Loan.java\n" +
                "├── 📄 LibraryManager.java\n" +
                "├── 📄 LibraryUI.java\n" +
                "└── 📄 Main.java\n\n" +
                "Funcionalidades:\n" +
                "• Gestión de libros (agregar, buscar, actualizar)\n" +
                "• Registro de miembros\n" +
                "• Sistema de préstamos y devoluciones\n" +
                "• Control de fechas de vencimiento\n" +
                "• Reportes de libros disponibles/prestados\n" +
                "• Búsqueda por título, autor, ISBN");
        panel.add(new JScrollPane(structureArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryBookClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.time.LocalDate;\n\n" +
                "public class Book {\n" +
                "    private String isbn;\n" +
                "    private String title;\n" +
                "    private String author;\n" +
                "    private String publisher;\n" +
                "    private int publicationYear;\n" +
                "    private String category;\n" +
                "    private boolean available;\n" +
                "    private LocalDate dueDate;\n\n" +
                "    public Book(String isbn, String title, String author, String publisher,\n" +
                "               int publicationYear, String category) {\n" +
                "        this.isbn = isbn;\n" +
                "        this.title = title;\n" +
                "        this.author = author;\n" +
                "        this.publisher = publisher;\n" +
                "        this.publicationYear = publicationYear;\n" +
                "        this.category = category;\n" +
                "        this.available = true;\n" +
                "    }\n\n" +
                "    // Getters y setters\n" +
                "    public String getIsbn() { return isbn; }\n" +
                "    public String getTitle() { return title; }\n" +
                "    public String getAuthor() { return author; }\n" +
                "    public boolean isAvailable() { return available; }\n" +
                "    public void setAvailable(boolean available) { this.available = available; }\n" +
                "    public LocalDate getDueDate() { return dueDate; }\n" +
                "    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }\n\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return title + \" by \" + author + \" (\" + (available ? \"Available\" : \"Loaned\") + \")\";\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryMemberClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.time.LocalDate;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n\n" +
                "public class Member {\n" +
                "    private String memberId;\n" +
                "    private String name;\n" +
                "    private String email;\n" +
                "    private String phone;\n" +
                "    private LocalDate registrationDate;\n" +
                "    private List<Book> borrowedBooks;\n" +
                "    private static final int MAX_BOOKS = 3;\n\n" +
                "    public Member(String memberId, String name, String email, String phone) {\n" +
                "        this.memberId = memberId;\n" +
                "        this.name = name;\n" +
                "        this.email = email;\n" +
                "        this.phone = phone;\n" +
                "        this.registrationDate = LocalDate.now();\n" +
                "        this.borrowedBooks = new ArrayList<>();\n" +
                "    }\n\n" +
                "    public boolean canBorrowBook() {\n" +
                "        return borrowedBooks.size() < MAX_BOOKS;\n" +
                "    }\n\n" +
                "    public void borrowBook(Book book) {\n" +
                "        if (canBorrowBook() && book.isAvailable()) {\n" +
                "            borrowedBooks.add(book);\n" +
                "            book.setAvailable(false);\n" +
                "            book.setDueDate(LocalDate.now().plusWeeks(2));\n" +
                "        }\n" +
                "    }\n\n" +
                "    public void returnBook(Book book) {\n" +
                "        borrowedBooks.remove(book);\n" +
                "        book.setAvailable(true);\n" +
                "        book.setDueDate(null);\n" +
                "    }\n\n" +
                "    // Getters\n" +
                "    public String getMemberId() { return memberId; }\n" +
                "    public String getName() { return name; }\n" +
                "    public List<Book> getBorrowedBooks() { return borrowedBooks; }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryManagerClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Collectors;\n\n" +
                "public class LibraryManager {\n" +
                "    private List<Book> books;\n" +
                "    private List<Member> members;\n\n" +
                "    public LibraryManager() {\n" +
                "        this.books = new ArrayList<>();\n" +
                "        this.members = new ArrayList<>();\n" +
                "        initializeSampleData();\n" +
                "    }\n\n" +
                "    private void initializeSampleData() {\n" +
                "        // Agregar algunos libros de ejemplo\n" +
                "        addBook(new Book(\"978-3-16-148410-0\", \"Java Programming\", \"John Doe\", \"Tech Books\", 2020, \"Programming\"));\n" +
                "        addBook(new Book(\"978-1-23-456789-0\", \"Swing GUI Development\", \"Jane Smith\", \"UI Press\", 2019, \"Programming\"));\n" +
                "        addMember(new Member(\"M001\", \"Alice Johnson\", \"alice@email.com\", \"555-0101\"));\n" +
                "    }\n\n" +
                "    public void addBook(Book book) {\n" +
                "        books.add(book);\n" +
                "    }\n\n" +
                "    public void addMember(Member member) {\n" +
                "        members.add(member);\n" +
                "    }\n\n" +
                "    public List<Book> searchBooks(String query) {\n" +
                "        return books.stream()\n" +
                "            .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||\n" +
                "                          book.getAuthor().toLowerCase().contains(query.toLowerCase()))\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public boolean loanBook(String memberId, String isbn) {\n" +
                "        Member member = findMemberById(memberId);\n" +
                "        Book book = findBookByIsbn(isbn);\n\n" +
                "        if (member != null && book != null && member.canBorrowBook() && book.isAvailable()) {\n" +
                "            member.borrowBook(book);\n" +
                "            return true;\n" +
                "        }\n" +
                "        return false;\n" +
                "    }\n\n" +
                "    public boolean returnBook(String memberId, String isbn) {\n" +
                "        Member member = findMemberById(memberId);\n" +
                "        Book book = findBookByIsbn(isbn);\n\n" +
                "        if (member != null && book != null && member.getBorrowedBooks().contains(book)) {\n" +
                "            member.returnBook(book);\n" +
                "            return true;\n" +
                "        }\n" +
                "        return false;\n" +
                "    }\n\n" +
                "    private Member findMemberById(String memberId) {\n" +
                "        return members.stream()\n" +
                "            .filter(m -> m.getMemberId().equals(memberId))\n" +
                "            .findFirst().orElse(null);\n" +
                "    }\n\n" +
                "    private Book findBookByIsbn(String isbn) {\n" +
                "        return books.stream()\n" +
                "            .filter(b -> b.getIsbn().equals(isbn))\n" +
                "            .findFirst().orElse(null);\n" +
                "    }\n\n" +
                "    // Getters\n" +
                "    public List<Book> getBooks() { return books; }\n" +
                "    public List<Member> getMembers() { return members; }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryUIClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n\n" +
                "public class LibraryUI extends JFrame {\n" +
                "    private LibraryManager libraryManager;\n" +
                "    private JTable booksTable;\n" +
                "    private JTable membersTable;\n" +
                "    private JTextField searchField;\n\n" +
                "    public LibraryUI() {\n" +
                "        this.libraryManager = new LibraryManager();\n" +
                "        initializeComponents();\n" +
                "    }\n\n" +
                "    private void initializeComponents() {\n" +
                "        setTitle(\"Sistema de Gestión de Biblioteca\");\n" +
                "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                "        setSize(800, 600);\n" +
                "        setLayout(new BorderLayout());\n\n" +
                "        // Panel superior - búsqueda\n" +
                "        JPanel searchPanel = new JPanel(new FlowLayout());\n" +
                "        searchField = new JTextField(20);\n" +
                "        JButton searchButton = new JButton(\"Buscar\");\n" +
                "        searchButton.addActionListener(e -> searchBooks());\n" +
                "        searchPanel.add(new JLabel(\"Buscar libros:\"));\n" +
                "        searchPanel.add(searchField);\n" +
                "        searchPanel.add(searchButton);\n\n" +
                "        // Panel central - pestañas\n" +
                "        JTabbedPane tabbedPane = new JTabbedPane();\n\n" +
                "        // Pestaña de libros\n" +
                "        booksTable = createBooksTable();\n" +
                "        tabbedPane.addTab(\"Libros\", new JScrollPane(booksTable));\n\n" +
                "        // Pestaña de miembros\n" +
                "        membersTable = createMembersTable();\n" +
                "        tabbedPane.addTab(\"Miembros\", new JScrollPane(membersTable));\n\n" +
                "        // Panel inferior - acciones\n" +
                "        JPanel actionPanel = new JPanel(new FlowLayout());\n" +
                "        JButton loanButton = new JButton(\"Prestar Libro\");\n" +
                "        JButton returnButton = new JButton(\"Devolver Libro\");\n" +
                "        JButton addBookButton = new JButton(\"Agregar Libro\");\n\n" +
                "        loanButton.addActionListener(e -> showLoanDialog());\n" +
                "        returnButton.addActionListener(e -> showReturnDialog());\n" +
                "        addBookButton.addActionListener(e -> showAddBookDialog());\n\n" +
                "        actionPanel.add(loanButton);\n" +
                "        actionPanel.add(returnButton);\n" +
                "        actionPanel.add(addBookButton);\n\n" +
                "        add(searchPanel, BorderLayout.NORTH);\n" +
                "        add(tabbedPane, BorderLayout.CENTER);\n" +
                "        add(actionPanel, BorderLayout.SOUTH);\n" +
                "    }\n\n" +
                "    private JTable createBooksTable() {\n" +
                "        String[] columns = {\"ISBN\", \"Título\", \"Autor\", \"Disponible\"};\n" +
                "        // Implementación de la tabla...\n" +
                "        return new JTable(); // Placeholder\n" +
                "    }\n\n" +
                "    private JTable createMembersTable() {\n" +
                "        String[] columns = {\"ID\", \"Nombre\", \"Email\", \"Libros Prestados\"};\n" +
                "        // Implementación de la tabla...\n" +
                "        return new JTable(); // Placeholder\n" +
                "    }\n\n" +
                "    private void searchBooks() {\n" +
                "        // Implementar búsqueda\n" +
                "    }\n\n" +
                "    private void showLoanDialog() {\n" +
                "        // Diálogo para prestar libros\n" +
                "    }\n\n" +
                "    private void showReturnDialog() {\n" +
                "        // Diálogo para devolver libros\n" +
                "    }\n\n" +
                "    private void showAddBookDialog() {\n" +
                "        // Diálogo para agregar libros\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createLibraryComplete() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton downloadBtn = new JButton("📥 Descargar Proyecto Biblioteca");
        downloadBtn.addActionListener(e -> downloadCompleteProject("Library"));
        panel.add(downloadBtn, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Sistema de Biblioteca Completo incluye:\n\n" +
                "• Gestión completa de libros (CRUD)\n" +
                "• Sistema de miembros con registro\n" +
                "• Préstamos y devoluciones con fechas\n" +
                "• Control de disponibilidad de libros\n" +
                "• Búsqueda avanzada por título/autor\n" +
                "• Interfaz gráfica con tablas y formularios\n" +
                "• Validaciones y manejo de errores\n\n" +
                "Funcionalidades principales:\n" +
                "• Agregar nuevos libros al catálogo\n" +
                "• Registrar nuevos miembros\n" +
                "• Buscar libros disponibles\n" +
                "• Gestionar préstamos con fechas límite\n" +
                "• Controlar devoluciones y disponibilidad");
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryStructure() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea structureArea = new JTextArea();
        structureArea.setEditable(false);
        structureArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        structureArea.setBackground(Color.BLACK);
        structureArea.setForeground(Color.GREEN);
        structureArea.setText("📁 ShoppingCenterInventory/\n" +
                "├── 📄 Product.java\n" +
                "├── 📄 Category.java\n" +
                "├── 📄 Sale.java\n" +
                "├── 📄 InventoryManager.java\n" +
                "├── 📄 StoreUI.java\n" +
                "└── 📄 Main.java\n\n" +
                "Funcionalidades:\n" +
                "• Gestión de productos por categorías\n" +
                "• Control de inventario (stock, precios)\n" +
                "• Sistema de ventas con carrito de compras\n" +
                "• Reportes de ventas y productos\n" +
                "• Alertas de stock bajo\n" +
                "• Gestión de proveedores");
        panel.add(new JScrollPane(structureArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryProductClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("public class Product {\n" +
                "    private String productId;\n" +
                "    private String name;\n" +
                "    private String description;\n" +
                "    private String category;\n" +
                "    private double price;\n" +
                "    private int stock;\n" +
                "    private int minStock;\n" +
                "    private String supplier;\n\n" +
                "    public Product(String productId, String name, String description,\n" +
                "                  String category, double price, int stock, String supplier) {\n" +
                "        this.productId = productId;\n" +
                "        this.name = name;\n" +
                "        this.description = description;\n" +
                "        this.category = category;\n" +
                "        this.price = price;\n" +
                "        this.stock = stock;\n" +
                "        this.minStock = 5; // Stock mínimo por defecto\n" +
                "        this.supplier = supplier;\n" +
                "    }\n\n" +
                "    public boolean isLowStock() {\n" +
                "        return stock <= minStock;\n" +
                "    }\n\n" +
                "    public void reduceStock(int quantity) {\n" +
                "        if (stock >= quantity) {\n" +
                "            stock -= quantity;\n" +
                "        }\n" +
                "    }\n\n" +
                "    public void increaseStock(int quantity) {\n" +
                "        stock += quantity;\n" +
                "    }\n\n" +
                "    // Getters y setters\n" +
                "    public String getProductId() { return productId; }\n" +
                "    public String getName() { return name; }\n" +
                "    public double getPrice() { return price; }\n" +
                "    public int getStock() { return stock; }\n" +
                "    public void setStock(int stock) { this.stock = stock; }\n" +
                "    public String getCategory() { return category; }\n\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return name + \" (Stock: \" + stock + \", Precio: $\" + price + \")\";\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryStoreClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.util.HashMap;\n" +
                "import java.util.Map;\n\n" +
                "public class Sale {\n" +
                "    private String saleId;\n" +
                "    private Map<Product, Integer> items;\n" +
                "    private double total;\n" +
                "    private String date;\n\n" +
                "    public Sale(String saleId) {\n" +
                "        this.saleId = saleId;\n" +
                "        this.items = new HashMap<>();\n" +
                "        this.total = 0.0;\n" +
                "        this.date = java.time.LocalDate.now().toString();\n" +
                "    }\n\n" +
                "    public void addItem(Product product, int quantity) {\n" +
                "        if (product.getStock() >= quantity) {\n" +
                "            items.put(product, items.getOrDefault(product, 0) + quantity);\n" +
                "            total += product.getPrice() * quantity;\n" +
                "            product.reduceStock(quantity);\n" +
                "        }\n" +
                "    }\n\n" +
                "    public double getTotal() { return total; }\n" +
                "    public Map<Product, Integer> getItems() { return items; }\n" +
                "    public String getSaleId() { return saleId; }\n\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Venta \" + saleId + \" - Total: $\" + String.format(\"%.2f\", total);\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryManagerClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Collectors;\n\n" +
                "public class InventoryManager {\n" +
                "    private List<Product> products;\n" +
                "    private List<Sale> sales;\n" +
                "    private int nextProductId = 1;\n" +
                "    private int nextSaleId = 1;\n\n" +
                "    public InventoryManager() {\n" +
                "        this.products = new ArrayList<>();\n" +
                "        this.sales = new ArrayList<>();\n" +
                "        initializeSampleData();\n" +
                "    }\n\n" +
                "    private void initializeSampleData() {\n" +
                "        addProduct(new Product(\"P001\", \"Laptop Dell\", \"Laptop para oficina\", \"Electrónicos\", 899.99, 10, \"Dell Corp\"));\n" +
                "        addProduct(new Product(\"P002\", \"Mouse Logitech\", \"Mouse inalámbrico\", \"Accesorios\", 29.99, 25, \"Logitech\"));\n" +
                "        addProduct(new Product(\"P003\", \"Teclado Mecánico\", \"Teclado gaming\", \"Accesorios\", 79.99, 15, \"Razer\"));\n" +
                "    }\n\n" +
                "    public void addProduct(Product product) {\n" +
                "        products.add(product);\n" +
                "    }\n\n" +
                "    public Product findProductById(String productId) {\n" +
                "        return products.stream()\n" +
                "            .filter(p -> p.getProductId().equals(productId))\n" +
                "            .findFirst().orElse(null);\n" +
                "    }\n\n" +
                "    public List<Product> getProductsByCategory(String category) {\n" +
                "        return products.stream()\n" +
                "            .filter(p -> p.getCategory().equals(category))\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public List<Product> getLowStockProducts() {\n" +
                "        return products.stream()\n" +
                "            .filter(Product::isLowStock)\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public Sale createSale() {\n" +
                "        Sale sale = new Sale(\"S\" + String.format(\"%03d\", nextSaleId++));\n" +
                "        sales.add(sale);\n" +
                "        return sale;\n" +
                "    }\n\n" +
                "    public boolean processSale(Sale sale) {\n" +
                "        // Verificar que todos los productos tengan stock suficiente\n" +
                "        for (Map.Entry<Product, Integer> entry : sale.getItems().entrySet()) {\n" +
                "            if (entry.getKey().getStock() < entry.getValue()) {\n" +
                "                return false; // Stock insuficiente\n" +
                "            }\n" +
                "        }\n\n" +
                "        // Procesar la venta (reducir stock)\n" +
                "        for (Map.Entry<Product, Integer> entry : sale.getItems().entrySet()) {\n" +
                "            entry.getKey().reduceStock(entry.getValue());\n" +
                "        }\n\n" +
                "        return true;\n" +
                "    }\n\n" +
                "    public double getTotalSales() {\n" +
                "        return sales.stream().mapToDouble(Sale::getTotal).sum();\n" +
                "    }\n\n" +
                "    // Getters\n" +
                "    public List<Product> getProducts() { return products; }\n" +
                "    public List<Sale> getSales() { return sales; }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryUIClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "import java.util.List;\n\n" +
                "public class StoreUI extends JFrame {\n" +
                "    private InventoryManager inventoryManager;\n" +
                "    private JTable productsTable;\n" +
                "    private JComboBox<String> categoryFilter;\n" +
                "    private DefaultTableModel tableModel;\n" +
                "    private Sale currentSale;\n\n" +
                "    public StoreUI() {\n" +
                "        this.inventoryManager = new InventoryManager();\n" +
                "        initializeComponents();\n" +
                "    }\n\n" +
                "    private void initializeComponents() {\n" +
                "        setTitle(\"Sistema de Inventario - Centro Comercial\");\n" +
                "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                "        setSize(900, 600);\n" +
                "        setLayout(new BorderLayout());\n\n" +
                "        // Panel superior - filtros y búsqueda\n" +
                "        JPanel topPanel = new JPanel(new FlowLayout());\n" +
                "        categoryFilter = new JComboBox<>(new String[]{\"Todos\", \"Electrónicos\", \"Accesorios\", \"Ropa\"});\n" +
                "        categoryFilter.addActionListener(e -> filterProducts());\n" +
                "        topPanel.add(new JLabel(\"Categoría:\"));\n" +
                "        topPanel.add(categoryFilter);\n\n" +
                "        JButton refreshBtn = new JButton(\"Actualizar\");\n" +
                "        refreshBtn.addActionListener(e -> refreshTable());\n" +
                "        topPanel.add(refreshBtn);\n\n" +
                "        // Tabla de productos\n" +
                "        String[] columns = {\"ID\", \"Nombre\", \"Categoría\", \"Precio\", \"Stock\", \"Estado\"};\n" +
                "        tableModel = new DefaultTableModel(columns, 0);\n" +
                "        productsTable = new JTable(tableModel);\n" +
                "        refreshTable();\n\n" +
                "        // Panel inferior - acciones de venta\n" +
                "        JPanel bottomPanel = new JPanel(new FlowLayout());\n" +
                "        JButton newSaleBtn = new JButton(\"Nueva Venta\");\n" +
                "        JButton addToCartBtn = new JButton(\"Agregar al Carrito\");\n" +
                "        JButton checkoutBtn = new JButton(\"Finalizar Venta\");\n" +
                "        JButton reportsBtn = new JButton(\"Reportes\");\n\n" +
                "        newSaleBtn.addActionListener(e -> startNewSale());\n" +
                "        addToCartBtn.addActionListener(e -> addToCart());\n" +
                "        checkoutBtn.addActionListener(e -> checkout());\n" +
                "        reportsBtn.addActionListener(e -> showReports());\n\n" +
                "        bottomPanel.add(newSaleBtn);\n" +
                "        bottomPanel.add(addToCartBtn);\n" +
                "        bottomPanel.add(checkoutBtn);\n" +
                "        bottomPanel.add(reportsBtn);\n\n" +
                "        add(topPanel, BorderLayout.NORTH);\n" +
                "        add(new JScrollPane(productsTable), BorderLayout.CENTER);\n" +
                "        add(bottomPanel, BorderLayout.SOUTH);\n" +
                "    }\n\n" +
                "    private void refreshTable() {\n" +
                "        tableModel.setRowCount(0);\n" +
                "        for (Product product : inventoryManager.getProducts()) {\n" +
                "            String status = product.isLowStock() ? \"Stock Bajo\" : \"Disponible\";\n" +
                "            tableModel.addRow(new Object[]{\n" +
                "                product.getProductId(),\n" +
                "                product.getName(),\n" +
                "                product.getCategory(),\n" +
                "                \"$\" + product.getPrice(),\n" +
                "                product.getStock(),\n" +
                "                status\n" +
                "            });\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void filterProducts() {\n" +
                "        String selectedCategory = (String) categoryFilter.getSelectedItem();\n" +
                "        tableModel.setRowCount(0);\n\n" +
                "        List<Product> productsToShow;\n" +
                "        if (\"Todos\".equals(selectedCategory)) {\n" +
                "            productsToShow = inventoryManager.getProducts();\n" +
                "        } else {\n" +
                "            productsToShow = inventoryManager.getProductsByCategory(selectedCategory);\n" +
                "        }\n\n" +
                "        for (Product product : productsToShow) {\n" +
                "            String status = product.isLowStock() ? \"Stock Bajo\" : \"Disponible\";\n" +
                "            tableModel.addRow(new Object[]{\n" +
                "                product.getProductId(),\n" +
                "                product.getName(),\n" +
                "                product.getCategory(),\n" +
                "                \"$\" + product.getPrice(),\n" +
                "                product.getStock(),\n" +
                "                status\n" +
                "            });\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void startNewSale() {\n" +
                "        currentSale = inventoryManager.createSale();\n" +
                "        JOptionPane.showMessageDialog(this, \"Nueva venta iniciada: \" + currentSale.getSaleId());\n" +
                "    }\n\n" +
                "    private void addToCart() {\n" +
                "        if (currentSale == null) {\n" +
                "            JOptionPane.showMessageDialog(this, \"Primero inicie una nueva venta.\");\n" +
                "            return;\n" +
                "        }\n\n" +
                "        int selectedRow = productsTable.getSelectedRow();\n" +
                "        if (selectedRow >= 0) {\n" +
                "            String productId = (String) tableModel.getValueAt(selectedRow, 0);\n" +
                "            Product product = inventoryManager.findProductById(productId);\n" +
                "            if (product != null) {\n" +
                "                String quantityStr = JOptionPane.showInputDialog(\"Cantidad:\");\n" +
                "                try {\n" +
                "                    int quantity = Integer.parseInt(quantityStr);\n" +
                "                    currentSale.addItem(product, quantity);\n" +
                "                    JOptionPane.showMessageDialog(this, \"Producto agregado al carrito.\");\n" +
                "                } catch (NumberFormatException e) {\n" +
                "                    JOptionPane.showMessageDialog(this, \"Cantidad inválida.\");\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void checkout() {\n" +
                "        if (currentSale == null || currentSale.getItems().isEmpty()) {\n" +
                "            JOptionPane.showMessageDialog(this, \"No hay productos en el carrito.\");\n" +
                "            return;\n" +
                "        }\n\n" +
                "        if (inventoryManager.processSale(currentSale)) {\n" +
                "            JOptionPane.showMessageDialog(this,\n" +
                "                \"Venta completada!\\nTotal: $\" + String.format(\"%.2f\", currentSale.getTotal()));\n" +
                "            currentSale = null;\n" +
                "            refreshTable();\n" +
                "        } else {\n" +
                "            JOptionPane.showMessageDialog(this, \"Error: Stock insuficiente para completar la venta.\");\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void showReports() {\n" +
                "        String report = \"REPORTE DE INVENTARIO\\n\\n\" +\n" +
                "            \"Total de productos: \" + inventoryManager.getProducts().size() + \"\\n\" +\n" +
                "            \"Total de ventas: \" + inventoryManager.getSales().size() + \"\\n\" +\n" +
                "            \"Ingresos totales: $\" + String.format(\"%.2f\", inventoryManager.getTotalSales()) + \"\\n\\n\" +\n" +
                "            \"Productos con stock bajo:\\n\";\n\n" +
                "        for (Product product : inventoryManager.getLowStockProducts()) {\n" +
                "            report += \"• \" + product.getName() + \" (Stock: \" + product.getStock() + \")\\n\";\n" +
                "        }\n\n" +
                "        JTextArea reportArea = new JTextArea(report);\n" +
                "        reportArea.setEditable(false);\n" +
                "        JScrollPane scrollPane = new JScrollPane(reportArea);\n" +
                "        scrollPane.setPreferredSize(new Dimension(400, 300));\n\n" +
                "        JOptionPane.showMessageDialog(this, scrollPane, \"Reportes\", JOptionPane.INFORMATION_MESSAGE);\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryComplete() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton downloadBtn = new JButton("📥 Descargar Proyecto Inventario");
        downloadBtn.addActionListener(e -> downloadCompleteProject("Inventory"));
        panel.add(downloadBtn, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Sistema de Inventario Centro Comercial incluye:\n\n" +
                "• Gestión completa de productos y categorías\n" +
                "• Control de stock con alertas automáticas\n" +
                "• Sistema de ventas con carrito de compras\n" +
                "• Procesamiento de transacciones\n" +
                "• Reportes de ventas e inventario\n" +
                "• Interfaz gráfica intuitiva\n\n" +
                "Funcionalidades principales:\n" +
                "• Agregar/modificar productos\n" +
                "• Filtrar productos por categoría\n" +
                "• Gestionar ventas con validaciones\n" +
                "• Control automático de stock\n" +
                "• Generación de reportes detallados\n" +
                "• Alertas de productos con stock bajo");
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyStructure() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea structureArea = new JTextArea();
        structureArea.setEditable(false);
        structureArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        structureArea.setBackground(Color.BLACK);
        structureArea.setForeground(Color.GREEN);
        structureArea.setText("📁 PharmacyManagementSystem/\n" +
                "├── 📄 Medicine.java\n" +
                "├── 📄 Prescription.java\n" +
                "├── 📄 Patient.java\n" +
                "├── 📄 PharmacyManager.java\n" +
                "├── 📄 PharmacyUI.java\n" +
                "└── 📄 Main.java\n\n" +
                "Funcionalidades:\n" +
                "• Gestión de medicamentos con fechas de vencimiento\n" +
                "• Control de recetas médicas\n" +
                "• Registro de pacientes\n" +
                "• Alertas de medicamentos próximos a vencer\n" +
                "• Control de stock con proveedores\n" +
                "• Reportes de inventario y ventas");
        panel.add(new JScrollPane(structureArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyMedicineClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.time.LocalDate;\n\n" +
                "public class Medicine {\n" +
                "    private String medicineId;\n" +
                "    private String name;\n" +
                "    private String description;\n" +
                "    private String category;\n" +
                "    private double price;\n" +
                "    private int stock;\n" +
                "    private int minStock;\n" +
                "    private LocalDate expirationDate;\n" +
                "    private String supplier;\n" +
                "    private boolean requiresPrescription;\n\n" +
                "    public Medicine(String medicineId, String name, String description,\n" +
                "                   String category, double price, int stock,\n" +
                "                   LocalDate expirationDate, String supplier, boolean requiresPrescription) {\n" +
                "        this.medicineId = medicineId;\n" +
                "        this.name = name;\n" +
                "        this.description = description;\n" +
                "        this.category = category;\n" +
                "        this.price = price;\n" +
                "        this.stock = stock;\n" +
                "        this.minStock = 10; // Stock mínimo para medicamentos\n" +
                "        this.expirationDate = expirationDate;\n" +
                "        this.supplier = supplier;\n" +
                "        this.requiresPrescription = requiresPrescription;\n" +
                "    }\n\n" +
                "    public boolean isExpired() {\n" +
                "        return LocalDate.now().isAfter(expirationDate);\n" +
                "    }\n\n" +
                "    public boolean isExpiringSoon() {\n" +
                "        return LocalDate.now().plusMonths(3).isAfter(expirationDate);\n" +
                "    }\n\n" +
                "    public boolean isLowStock() {\n" +
                "        return stock <= minStock;\n" +
                "    }\n\n" +
                "    public void reduceStock(int quantity) {\n" +
                "        if (stock >= quantity) {\n" +
                "            stock -= quantity;\n" +
                "        }\n" +
                "    }\n\n" +
                "    public void increaseStock(int quantity) {\n" +
                "        stock += quantity;\n" +
                "    }\n\n" +
                "    // Getters y setters\n" +
                "    public String getMedicineId() { return medicineId; }\n" +
                "    public String getName() { return name; }\n" +
                "    public double getPrice() { return price; }\n" +
                "    public int getStock() { return stock; }\n" +
                "    public LocalDate getExpirationDate() { return expirationDate; }\n" +
                "    public boolean requiresPrescription() { return requiresPrescription; }\n" +
                "    public void setStock(int stock) { this.stock = stock; }\n\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return name + \" (Stock: \" + stock + \", Vence: \" + expirationDate +\n" +
                "               (requiresPrescription ? \" - Requiere Receta\" : \"\") + \")\";\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyPharmacyClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.time.LocalDate;\n\n" +
                "public class Prescription {\n" +
                "    private String prescriptionId;\n" +
                "    private String patientId;\n" +
                "    private String doctorName;\n" +
                "    private LocalDate issueDate;\n" +
                "    private LocalDate expirationDate;\n" +
                "    private String medicineName;\n" +
                "    private int quantity;\n" +
                "    private boolean filled;\n\n" +
                "    public Prescription(String prescriptionId, String patientId, String doctorName,\n" +
                "                       LocalDate issueDate, String medicineName, int quantity) {\n" +
                "        this.prescriptionId = prescriptionId;\n" +
                "        this.patientId = patientId;\n" +
                "        this.doctorName = doctorName;\n" +
                "        this.issueDate = issueDate;\n" +
                "        this.expirationDate = issueDate.plusMonths(6); // 6 meses de validez\n" +
                "        this.medicineName = medicineName;\n" +
                "        this.quantity = quantity;\n" +
                "        this.filled = false;\n" +
                "    }\n\n" +
                "    public boolean isExpired() {\n" +
                "        return LocalDate.now().isAfter(expirationDate);\n" +
                "    }\n\n" +
                "    public boolean canBeFilled() {\n" +
                "        return !filled && !isExpired();\n" +
                "    }\n\n" +
                "    public void markAsFilled() {\n" +
                "        this.filled = true;\n" +
                "    }\n\n" +
                "    // Getters\n" +
                "    public String getPrescriptionId() { return prescriptionId; }\n" +
                "    public String getPatientId() { return patientId; }\n" +
                "    public String getMedicineName() { return medicineName; }\n" +
                "    public int getQuantity() { return quantity; }\n" +
                "    public boolean isFilled() { return filled; }\n\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"Receta \" + prescriptionId + \" - \" + medicineName +\n" +
                "               \" (Cantidad: \" + quantity + \", \" + (filled ? \"Completada\" : \"Pendiente\") + \")\";\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyManagerClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Collectors;\n\n" +
                "public class PharmacyManager {\n" +
                "    private List<Medicine> medicines;\n" +
                "    private List<Prescription> prescriptions;\n" +
                "    private List<Patient> patients;\n\n" +
                "    public PharmacyManager() {\n" +
                "        this.medicines = new ArrayList<>();\n" +
                "        this.prescriptions = new ArrayList<>();\n" +
                "        this.patients = new ArrayList<>();\n" +
                "        initializeSampleData();\n" +
                "    }\n\n" +
                "    private void initializeSampleData() {\n" +
                "        // Agregar medicamentos de ejemplo\n" +
                "        addMedicine(new Medicine(\"M001\", \"Paracetamol\", \"Analgésico y antipirético\",\n" +
                "            \"Analgésicos\", 5.99, 100, java.time.LocalDate.now().plusMonths(12),\n" +
                "            \"Farmacéutica ABC\", false));\n\n" +
                "        addMedicine(new Medicine(\"M002\", \"Amoxicilina\", \"Antibiótico de amplio espectro\",\n" +
                "            \"Antibióticos\", 15.50, 50, java.time.LocalDate.now().plusMonths(18),\n" +
                "            \"Laboratorios XYZ\", true));\n\n" +
                "        addMedicine(new Medicine(\"M003\", \"Ibuprofeno\", \"Antiinflamatorio no esteroideo\",\n" +
                "            \"Antiinflamatorios\", 8.25, 75, java.time.LocalDate.now().plusMonths(24),\n" +
                "            \"Farmacéutica DEF\", false));\n" +
                "    }\n\n" +
                "    public void addMedicine(Medicine medicine) {\n" +
                "        medicines.add(medicine);\n" +
                "    }\n\n" +
                "    public void addPrescription(Prescription prescription) {\n" +
                "        prescriptions.add(prescription);\n" +
                "    }\n\n" +
                "    public Medicine findMedicineById(String medicineId) {\n" +
                "        return medicines.stream()\n" +
                "            .filter(m -> m.getMedicineId().equals(medicineId))\n" +
                "            .findFirst().orElse(null);\n" +
                "    }\n\n" +
                "    public List<Medicine> getExpiredMedicines() {\n" +
                "        return medicines.stream()\n" +
                "            .filter(Medicine::isExpired)\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public List<Medicine> getExpiringSoonMedicines() {\n" +
                "        return medicines.stream()\n" +
                "            .filter(Medicine::isExpiringSoon)\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public List<Medicine> getLowStockMedicines() {\n" +
                "        return medicines.stream()\n" +
                "            .filter(Medicine::isLowStock)\n" +
                "            .collect(Collectors.toList());\n" +
                "    }\n\n" +
                "    public boolean dispenseMedicine(String prescriptionId, String medicineId, int quantity) {\n" +
                "        Prescription prescription = findPrescriptionById(prescriptionId);\n" +
                "        Medicine medicine = findMedicineById(medicineId);\n\n" +
                "        if (prescription == null || medicine == null) {\n" +
                "            return false;\n" +
                "        }\n\n" +
                "        // Verificar receta\n" +
                "        if (!prescription.canBeFilled() || !prescription.getMedicineName().equals(medicine.getName())) {\n" +
                "            return false;\n" +
                "        }\n\n" +
                "        // Verificar stock y receta médica si es requerida\n" +
                "        if (medicine.getStock() < quantity) {\n" +
                "            return false;\n" +
                "        }\n\n" +
                "        // Dispensar medicamento\n" +
                "        medicine.reduceStock(quantity);\n" +
                "        prescription.markAsFilled();\n\n" +
                "        return true;\n" +
                "    }\n\n" +
                "    private Prescription findPrescriptionById(String prescriptionId) {\n" +
                "        return prescriptions.stream()\n" +
                "            .filter(p -> p.getPrescriptionId().equals(prescriptionId))\n" +
                "            .findFirst().orElse(null);\n" +
                "    }\n\n" +
                "    // Getters\n" +
                "    public List<Medicine> getMedicines() { return medicines; }\n" +
                "    public List<Prescription> getPrescriptions() { return prescriptions; }\n" +
                "    public List<Patient> getPatients() { return patients; }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyUIClass() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea codeArea = new JTextArea();
        codeArea.setEditable(false);
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeArea.setBackground(Color.BLACK);
        codeArea.setForeground(Color.GREEN);
        codeArea.setText("import javax.swing.*;\n" +
                "import java.awt.*;\n" +
                "import java.awt.event.*;\n" +
                "import java.util.List;\n\n" +
                "public class PharmacyUI extends JFrame {\n" +
                "    private PharmacyManager pharmacyManager;\n" +
                "    private JTable medicinesTable;\n" +
                "    private JTable prescriptionsTable;\n" +
                "    private DefaultTableModel medicinesTableModel;\n" +
                "    private DefaultTableModel prescriptionsTableModel;\n\n" +
                "    public PharmacyUI() {\n" +
                "        this.pharmacyManager = new PharmacyManager();\n" +
                "        initializeComponents();\n" +
                "    }\n\n" +
                "    private void initializeComponents() {\n" +
                "        setTitle(\"Sistema de Gestión Farmacéutica\");\n" +
                "        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                "        setSize(1000, 700);\n" +
                "        setLayout(new BorderLayout());\n\n" +
                "        // Panel superior - búsqueda y filtros\n" +
                "        JPanel topPanel = new JPanel(new FlowLayout());\n" +
                "        JTextField searchField = new JTextField(20);\n" +
                "        JButton searchBtn = new JButton(\"Buscar Medicamento\");\n" +
                "        JButton alertsBtn = new JButton(\"⚠️ Ver Alertas\");\n\n" +
                "        searchBtn.addActionListener(e -> searchMedicine(searchField.getText()));\n" +
                "        alertsBtn.addActionListener(e -> showAlerts());\n\n" +
                "        topPanel.add(new JLabel(\"Buscar:\"));\n" +
                "        topPanel.add(searchField);\n" +
                "        topPanel.add(searchBtn);\n" +
                "        topPanel.add(alertsBtn);\n\n" +
                "        // Panel central - pestañas\n" +
                "        JTabbedPane tabbedPane = new JTabbedPane();\n\n" +
                "        // Pestaña de medicamentos\n" +
                "        medicinesTableModel = new DefaultTableModel(\n" +
                "            new String[]{\"ID\", \"Nombre\", \"Categoría\", \"Stock\", \"Precio\", \"Estado\"}, 0);\n" +
                "        medicinesTable = new JTable(medicinesTableModel);\n" +
                "        refreshMedicinesTable();\n" +
                "        tabbedPane.addTab(\"Medicamentos\", new JScrollPane(medicinesTable));\n\n" +
                "        // Pestaña de recetas\n" +
                "        prescriptionsTableModel = new DefaultTableModel(\n" +
                "            new String[]{\"ID Receta\", \"ID Paciente\", \"Medicamento\", \"Cantidad\", \"Estado\"}, 0);\n" +
                "        prescriptionsTable = new JTable(prescriptionsTableModel);\n" +
                "        refreshPrescriptionsTable();\n" +
                "        tabbedPane.addTab(\"Recetas\", new JScrollPane(prescriptionsTable));\n\n" +
                "        // Panel inferior - acciones\n" +
                "        JPanel bottomPanel = new JPanel(new FlowLayout());\n" +
                "        JButton dispenseBtn = new JButton(\"💊 Dispensar Medicamento\");\n" +
                "        JButton addMedicineBtn = new JButton(\"➕ Agregar Medicamento\");\n" +
                "        JButton addPrescriptionBtn = new JButton(\"📋 Agregar Receta\");\n" +
                "        JButton reportsBtn = new JButton(\"📊 Reportes\");\n\n" +
                "        dispenseBtn.addActionListener(e -> showDispenseDialog());\n" +
                "        addMedicineBtn.addActionListener(e -> showAddMedicineDialog());\n" +
                "        addPrescriptionBtn.addActionListener(e -> showAddPrescriptionDialog());\n" +
                "        reportsBtn.addActionListener(e -> showReports());\n\n" +
                "        bottomPanel.add(dispenseBtn);\n" +
                "        bottomPanel.add(addMedicineBtn);\n" +
                "        bottomPanel.add(addPrescriptionBtn);\n" +
                "        bottomPanel.add(reportsBtn);\n\n" +
                "        add(topPanel, BorderLayout.NORTH);\n" +
                "        add(tabbedPane, BorderLayout.CENTER);\n" +
                "        add(bottomPanel, BorderLayout.SOUTH);\n" +
                "    }\n\n" +
                "    private void refreshMedicinesTable() {\n" +
                "        medicinesTableModel.setRowCount(0);\n" +
                "        for (Medicine medicine : pharmacyManager.getMedicines()) {\n" +
                "            String status = \"Disponible\";\n" +
                "            if (medicine.isExpired()) {\n" +
                "                status = \"Vencido\";\n" +
                "            } else if (medicine.isExpiringSoon()) {\n" +
                "                status = \"Vence Pronto\";\n" +
                "            } else if (medicine.isLowStock()) {\n" +
                "                status = \"Stock Bajo\";\n" +
                "            }\n\n" +
                "            medicinesTableModel.addRow(new Object[]{\n" +
                "                medicine.getMedicineId(),\n" +
                "                medicine.getName(),\n" +
                "                medicine.getCategory(),\n" +
                "                medicine.getStock(),\n" +
                "                \"$\" + medicine.getPrice(),\n" +
                "                status\n" +
                "            });\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void refreshPrescriptionsTable() {\n" +
                "        prescriptionsTableModel.setRowCount(0);\n" +
                "        for (Prescription prescription : pharmacyManager.getPrescriptions()) {\n" +
                "            prescriptionsTableModel.addRow(new Object[]{\n" +
                "                prescription.getPrescriptionId(),\n" +
                "                prescription.getPatientId(),\n" +
                "                prescription.getMedicineName(),\n" +
                "                prescription.getQuantity(),\n" +
                "                prescription.isFilled() ? \"Completada\" : \"Pendiente\"\n" +
                "            });\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void searchMedicine(String query) {\n" +
                "        medicinesTableModel.setRowCount(0);\n" +
                "        for (Medicine medicine : pharmacyManager.getMedicines()) {\n" +
                "            if (medicine.getName().toLowerCase().contains(query.toLowerCase())) {\n" +
                "                String status = medicine.isExpired() ? \"Vencido\" :\n" +
                "                    medicine.isExpiringSoon() ? \"Vence Pronto\" :\n" +
                "                    medicine.isLowStock() ? \"Stock Bajo\" : \"Disponible\";\n\n" +
                "                medicinesTableModel.addRow(new Object[]{\n" +
                "                    medicine.getMedicineId(),\n" +
                "                    medicine.getName(),\n" +
                "                    medicine.getCategory(),\n" +
                "                    medicine.getStock(),\n" +
                "                    \"$\" + medicine.getPrice(),\n" +
                "                    status\n" +
                "                });\n" +
                "            }\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void showAlerts() {\n" +
                "        StringBuilder alerts = new StringBuilder(\"🚨 ALERTAS DEL SISTEMA\\n\\n\");\n\n" +
                "        List<Medicine> expired = pharmacyManager.getExpiredMedicines();\n" +
                "        if (!expired.isEmpty()) {\n" +
                "            alerts.append(\"MEDICAMENTOS VENCIDOS:\\n\");\n" +
                "            for (Medicine med : expired) {\n" +
                "                alerts.append(\"• \").append(med.getName()).append(\"\\n\");\n" +
                "            }\n" +
                "            alerts.append(\"\\n\");\n" +
                "        }\n\n" +
                "        List<Medicine> expiringSoon = pharmacyManager.getExpiringSoonMedicines();\n" +
                "        if (!expiringSoon.isEmpty()) {\n" +
                "            alerts.append(\"MEDICAMENTOS QUE VENCEN PRONTO:\\n\");\n" +
                "            for (Medicine med : expiringSoon) {\n" +
                "                alerts.append(\"• \").append(med.getName())\n" +
                "                    .append(\" (Vence: \").append(med.getExpirationDate()).append(\")\\n\");\n" +
                "            }\n" +
                "            alerts.append(\"\\n\");\n" +
                "        }\n\n" +
                "        List<Medicine> lowStock = pharmacyManager.getLowStockMedicines();\n" +
                "        if (!lowStock.isEmpty()) {\n" +
                "            alerts.append(\"MEDICAMENTOS CON STOCK BAJO:\\n\");\n" +
                "            for (Medicine med : lowStock) {\n" +
                "                alerts.append(\"• \").append(med.getName())\n" +
                "                    .append(\" (Stock: \").append(med.getStock()).append(\")\\n\");\n" +
                "            }\n" +
                "        }\n\n" +
                "        if (expired.isEmpty() && expiringSoon.isEmpty() && lowStock.isEmpty()) {\n" +
                "            alerts.append(\"✅ No hay alertas activas.\");\n" +
                "        }\n\n" +
                "        JTextArea alertArea = new JTextArea(alerts.toString());\n" +
                "        alertArea.setEditable(false);\n" +
                "        JScrollPane scrollPane = new JScrollPane(alertArea);\n" +
                "        scrollPane.setPreferredSize(new Dimension(500, 400));\n\n" +
                "        JOptionPane.showMessageDialog(this, scrollPane, \"Alertas del Sistema\",\n" +
                "            JOptionPane.WARNING_MESSAGE);\n" +
                "    }\n\n" +
                "    private void showDispenseDialog() {\n" +
                "        JTextField prescriptionIdField = new JTextField(10);\n" +
                "        JTextField medicineIdField = new JTextField(10);\n" +
                "        JTextField quantityField = new JTextField(5);\n\n" +
                "        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));\n" +
                "        panel.add(new JLabel(\"ID Receta:\"));\n" +
                "        panel.add(prescriptionIdField);\n" +
                "        panel.add(new JLabel(\"ID Medicamento:\"));\n" +
                "        panel.add(medicineIdField);\n" +
                "        panel.add(new JLabel(\"Cantidad:\"));\n" +
                "        panel.add(quantityField);\n\n" +
                "        int result = JOptionPane.showConfirmDialog(this, panel,\n" +
                "            \"Dispensar Medicamento\", JOptionPane.OK_CANCEL_OPTION);\n\n" +
                "        if (result == JOptionPane.OK_OPTION) {\n" +
                "            try {\n" +
                "                String prescriptionId = prescriptionIdField.getText().trim();\n" +
                "                String medicineId = medicineIdField.getText().trim();\n" +
                "                int quantity = Integer.parseInt(quantityField.getText().trim());\n\n" +
                "                if (pharmacyManager.dispenseMedicine(prescriptionId, medicineId, quantity)) {\n" +
                "                    JOptionPane.showMessageDialog(this, \"Medicamento dispensado exitosamente.\");\n" +
                "                    refreshMedicinesTable();\n" +
                "                    refreshPrescriptionsTable();\n" +
                "                } else {\n" +
                "                    JOptionPane.showMessageDialog(this, \"Error al dispensar medicamento. \" +\n" +
                "                        \"Verifique la receta y el stock disponible.\", \"Error\",\n" +
                "                        JOptionPane.ERROR_MESSAGE);\n" +
                "                }\n" +
                "            } catch (NumberFormatException e) {\n" +
                "                JOptionPane.showMessageDialog(this, \"Cantidad inválida.\", \"Error\",\n" +
                "                    JOptionPane.ERROR_MESSAGE);\n" +
                "            }\n" +
                "        }\n" +
                "    }\n\n" +
                "    private void showAddMedicineDialog() {\n" +
                "        // Implementar diálogo para agregar medicamentos\n" +
                "        JOptionPane.showMessageDialog(this, \"Funcionalidad para agregar medicamentos - Implementar\");\n" +
                "    }\n\n" +
                "    private void showAddPrescriptionDialog() {\n" +
                "        // Implementar diálogo para agregar recetas\n" +
                "        JOptionPane.showMessageDialog(this, \"Funcionalidad para agregar recetas - Implementar\");\n" +
                "    }\n\n" +
                "    private void showReports() {\n" +
                "        String report = \"REPORTE DE FARMACIA\\n\\n\" +\n" +
                "            \"Total de medicamentos: \" + pharmacyManager.getMedicines().size() + \"\\n\" +\n" +
                "            \"Total de recetas: \" + pharmacyManager.getPrescriptions().size() + \"\\n\" +\n" +
                "            \"Medicamentos vencidos: \" + pharmacyManager.getExpiredMedicines().size() + \"\\n\" +\n" +
                "            \"Medicamentos con stock bajo: \" + pharmacyManager.getLowStockMedicines().size() + \"\\n\\n\" +\n" +
                "            \"INVENTARIO DETALLADO:\\n\";\n\n" +
                "        for (Medicine medicine : pharmacyManager.getMedicines()) {\n" +
                "            report += String.format(\"• %s - Stock: %d - Precio: $%.2f\\n\",\n" +
                "                medicine.getName(), medicine.getStock(), medicine.getPrice());\n" +
                "        }\n\n" +
                "        JTextArea reportArea = new JTextArea(report);\n" +
                "        reportArea.setEditable(false);\n" +
                "        JScrollPane scrollPane = new JScrollPane(reportArea);\n" +
                "        scrollPane.setPreferredSize(new Dimension(500, 400));\n\n" +
                "        JOptionPane.showMessageDialog(this, scrollPane, \"Reportes de Farmacia\",\n" +
                "            JOptionPane.INFORMATION_MESSAGE);\n" +
                "    }\n" +
                "}");
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPharmacyComplete() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton downloadBtn = new JButton("📥 Descargar Proyecto Farmacia");
        downloadBtn.addActionListener(e -> downloadCompleteProject("Pharmacy"));
        panel.add(downloadBtn, BorderLayout.NORTH);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Sistema de Gestión Farmacéutica Completo incluye:\n\n" +
                "• Gestión completa de medicamentos con fechas de vencimiento\n" +
                "• Sistema de recetas médicas con validaciones\n" +
                "• Control de stock con alertas automáticas\n" +
                "• Registro y seguimiento de pacientes\n" +
                "• Dispensación de medicamentos con receta\n" +
                "• Reportes detallados de inventario\n\n" +
                "Funcionalidades principales:\n" +
                "• Agregar y gestionar medicamentos\n" +
                "• Control automático de vencimientos\n" +
                "• Gestión de recetas médicas\n" +
                "• Dispensación con validaciones\n" +
                "• Alertas de stock y vencimientos\n" +
                "• Reportes completos del sistema");
        panel.add(new JScrollPane(infoArea), BorderLayout.CENTER);
        return panel;
    }

    private void downloadCompleteProject(String projectName) {
        JOptionPane.showMessageDialog(this,
            "Descargando proyecto completo: " + projectName + "\n\n" +
            "En una implementación real, esto crearía un archivo ZIP\n" +
            "con todo el código fuente del proyecto.",
            "Descarga Completa",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void startPracticeExercise(int exerciseIndex) {
        String[] exercises = {
            "Sistema de Login Seguro",
            "Gestión de Préstamos Bibliotecarios",
            "Sistema de Ventas con Carrito",
            "Control de Inventario Farmacéutico"
        };

        String[] baseCode = {
            "// Base para Sistema de Login\n" +
            "public class SecureLoginSystem {\n" +
            "    // TODO: Implementar hash de contraseñas\n" +
            "    // TODO: Sistema de sesiones\n" +
            "    // TODO: Validaciones de entrada\n" +
            "    // TODO: Manejo de intentos fallidos\n" +
            "}\n",

            "// Base para Sistema de Préstamos\n" +
            "public class LoanManagementSystem {\n" +
            "    // TODO: Control de fechas de préstamo/devolución\n" +
            "    // TODO: Límite de libros por usuario\n" +
            "    // TODO: Sistema de penalizaciones\n" +
            "    // TODO: Renovaciones de préstamos\n" +
            "}\n",

            "// Base para Sistema de Ventas\n" +
            "public class ShoppingCartSystem {\n" +
            "    // TODO: Carrito de compras\n" +
            "    // TODO: Cálculo de totales con impuestos\n" +
            "    // TODO: Descuentos y promociones\n" +
            "    // TODO: Confirmación de pago\n" +
            "}\n",

            "// Base para Control Farmacéutico\n" +
            "public class PharmacyInventorySystem {\n" +
            "    // TODO: Alertas de vencimiento\n" +
            "    // TODO: Control de recetas\n" +
            "    // TODO: Reportes de stock\n" +
            "    // TODO: Gestión de proveedores\n" +
            "}\n"
        };

        // Create practice dialog
        JDialog practiceDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this),
                                           "Entorno de Práctica - " + exercises[exerciseIndex], true);
        practiceDialog.setSize(900, 700);
        practiceDialog.setLocationRelativeTo(this);
        practiceDialog.setLayout(new BorderLayout());

        // Instructions panel
        JPanel instructionsPanel = new JPanel(new BorderLayout());
        instructionsPanel.setBorder(BorderFactory.createTitledBorder("📝 Instrucciones"));

        JTextArea instructionsArea = new JTextArea(getExerciseInstructions(exerciseIndex));
        instructionsArea.setEditable(false);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setLineWrap(true);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 12));
        instructionsPanel.add(new JScrollPane(instructionsArea), BorderLayout.CENTER);

        // Code editor panel
        JPanel editorPanel = new JPanel(new BorderLayout());
        editorPanel.setBorder(BorderFactory.createTitledBorder("💻 Editor de Código"));

        JTextArea codeEditor = new JTextArea(baseCode[exerciseIndex]);
        codeEditor.setFont(new Font("Monospaced", Font.PLAIN, 12));
        codeEditor.setBackground(Color.BLACK);
        codeEditor.setForeground(Color.GREEN);
        codeEditor.setTabSize(4);

        JScrollPane codeScroll = new JScrollPane(codeEditor);
        editorPanel.add(codeScroll, BorderLayout.CENTER);

        // Control buttons
        JPanel controlPanel = new JPanel(new FlowLayout());

        JButton runBtn = new JButton("▶️ Ejecutar Código");
        runBtn.addActionListener(e -> {
            // Simulate code execution
            JOptionPane.showMessageDialog(practiceDialog,
                "Código ejecutado exitosamente!\n\n" +
                "En una implementación completa, aquí se compilaría\n" +
                "y ejecutaría el código del estudiante.",
                "Ejecución Exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        });

        JButton validateBtn = new JButton("✓ Validar Solución");
        validateBtn.addActionListener(e -> validateStudentCode(exerciseIndex, codeEditor.getText()));

        JButton hintsBtn = new JButton("💡 Ver Pistas");
        hintsBtn.addActionListener(e -> showPracticeHints(exerciseIndex));

        JButton solutionBtn = new JButton("✅ Ver Solución");
        solutionBtn.addActionListener(e -> showPracticeSolution(exerciseIndex));

        controlPanel.add(runBtn);
        controlPanel.add(validateBtn);
        controlPanel.add(hintsBtn);
        controlPanel.add(solutionBtn);

        editorPanel.add(controlPanel, BorderLayout.SOUTH);

        // Split between instructions and editor
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.3);
        splitPane.setLeftComponent(instructionsPanel);
        splitPane.setRightComponent(editorPanel);

        practiceDialog.add(splitPane, BorderLayout.CENTER);

        // Close button
        JButton closeBtn = new JButton("Cerrar Práctica");
        closeBtn.addActionListener(e -> practiceDialog.dispose());
        practiceDialog.add(closeBtn, BorderLayout.SOUTH);

        practiceDialog.setVisible(true);
    }

    private String getExerciseInstructions(int exerciseIndex) {
        String[] instructions = {
            "SISTEMA DE LOGIN SEGURO\n\n" +
            "Objetivo: Implementar un sistema de autenticación completo y seguro.\n\n" +
            "Requisitos:\n" +
            "1. Hash de contraseñas (no almacenar en texto plano)\n" +
            "2. Sistema de sesiones para mantener login\n" +
            "3. Validación de entrada (longitud, caracteres especiales)\n" +
            "4. Límite de intentos fallidos\n" +
            "5. Cambio de contraseña\n" +
            "6. Logout seguro\n\n" +
            "Pistas: Usa BCrypt para hash, UUID para session IDs",

            "GESTIÓN DE PRÉSTAMOS BIBLIOTECARIOS\n\n" +
            "Objetivo: Sistema completo para gestionar préstamos de libros.\n\n" +
            "Requisitos:\n" +
            "1. Control de disponibilidad de libros\n" +
            "2. Fechas de préstamo y devolución\n" +
            "3. Límite de libros por usuario\n" +
            "4. Sistema de renovaciones\n" +
            "5. Penalizaciones por retraso\n" +
            "6. Reportes de préstamos activos\n\n" +
            "Pistas: Usa LocalDate para fechas, enum para estados",

            "SISTEMA DE VENTAS CON CARRITO\n\n" +
            "Objetivo: Implementar un sistema de ventas completo.\n\n" +
            "Requisitos:\n" +
            "1. Carrito de compras funcional\n" +
            "2. Cálculo de totales con impuestos\n" +
            "3. Sistema de descuentos\n" +
            "4. Validación de stock antes de venta\n" +
            "5. Confirmación de pago\n" +
            "6. Recibos de compra\n\n" +
            "Pistas: Usa Map para carrito, Strategy pattern para descuentos",

            "CONTROL DE INVENTARIO FARMACÉUTICO\n\n" +
            "Objetivo: Sistema especializado para gestión farmacéutica.\n\n" +
            "Requisitos:\n" +
            "1. Alertas de medicamentos próximos a vencer\n" +
            "2. Control de recetas médicas\n" +
            "3. Stock mínimo con alertas\n" +
            "4. Categorización por tipo de medicamento\n" +
            "5. Reportes de inventario\n" +
            "6. Gestión de proveedores\n\n" +
            "Pistas: Usa LocalDate para vencimientos, Observer para alertas"
        };

        return instructions[exerciseIndex];
    }

    private void validateStudentCode(int exerciseIndex, String code) {
        // Simulate code validation
        String[] validationMessages = {
            "Validación Sistema de Login:\n\n" +
            "✓ Hash de contraseñas detectado\n" +
            "✓ Sistema de sesiones implementado\n" +
            "⚠️ Faltan validaciones de entrada\n" +
            "✓ Logout implementado\n\n" +
            "Puntuación: 85/100",

            "Validación Sistema de Préstamos:\n\n" +
            "✓ Control de fechas implementado\n" +
            "✓ Límite de libros aplicado\n" +
            "⚠️ Falta sistema de penalizaciones\n" +
            "✓ Renovaciones disponibles\n\n" +
            "Puntuación: 78/100",

            "Validación Sistema de Ventas:\n\n" +
            "✓ Carrito funcional\n" +
            "✓ Cálculos correctos\n" +
            "⚠️ Falta sistema de descuentos\n" +
            "✓ Validación de stock\n\n" +
            "Puntuación: 82/100",

            "Validación Sistema Farmacéutico:\n\n" +
            "✓ Alertas de vencimiento\n" +
            "⚠️ Control de recetas incompleto\n" +
            "✓ Stock mínimo configurado\n" +
            "✓ Categorización implementada\n\n" +
            "Puntuación: 76/100"
        };

        JOptionPane.showMessageDialog(this,
            validationMessages[exerciseIndex],
            "Validación de Código",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPracticeHints(int exerciseIndex) {
        String[] hints = {
            "💡 PISTAS PARA SISTEMA DE LOGIN:\n\n" +
            "1. Para hash: Usa java.security.MessageDigest o librería BCrypt\n" +
            "2. Sesiones: Crea clase SessionManager con UUID como ID\n" +
            "3. Validación: Longitud min 8, al menos 1 mayúscula, 1 número\n" +
            "4. Intentos: Contador estático por usuario, bloqueo temporal\n" +
            "5. Cambio contraseña: Verificar contraseña actual primero",

            "💡 PISTAS PARA SISTEMA DE PRÉSTAMOS:\n\n" +
            "1. Fechas: java.time.LocalDate para préstamo y devolución\n" +
            "2. Estados: enum LoanStatus {ACTIVE, RETURNED, OVERDUE}\n" +
            "3. Límite: Constante en clase Member, validar antes de prestar\n" +
            "4. Renovación: Extender fecha si no overdue y sin reservas\n" +
            "5. Penalizaciones: Calcular días de retraso × tarifa diaria",

            "💡 PISTAS PARA SISTEMA DE VENTAS:\n\n" +
            "1. Carrito: Map<Product, Integer> para productos y cantidades\n" +
            "2. Impuestos: Interfaz TaxCalculator con diferentes estrategias\n" +
            "3. Descuentos: Strategy pattern (PercentageDiscount, FixedDiscount)\n" +
            "4. Stock: Verificar antes de agregar al carrito\n" +
            "5. Recibo: Clase Receipt con detalles de compra",

            "💡 PISTAS PARA SISTEMA FARMACÉUTICO:\n\n" +
            "1. Vencimiento: LocalDate.now().plusMonths(3).isAfter(expirationDate)\n" +
            "2. Recetas: Clase Prescription con estado (PENDING, FILLED, EXPIRED)\n" +
            "3. Alertas: Observer pattern para notificaciones de stock/vencimiento\n" +
            "4. Categorías: enum MedicineCategory {ANALGESIC, ANTIBIOTIC, etc.}\n" +
            "5. Proveedores: Clase Supplier con información de contacto"
        };

        JTextArea hintsArea = new JTextArea(hints[exerciseIndex]);
        hintsArea.setEditable(false);
        hintsArea.setWrapStyleWord(true);
        hintsArea.setLineWrap(true);
        hintsArea.setFont(new Font("Arial", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(hintsArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Pistas del Ejercicio",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void showPracticeSolution(int exerciseIndex) {
        String[] solutions = {
            "SOLUCIÓN COMPLETA - SISTEMA DE LOGIN SEGURO\n\n" +
            "public class SecureLoginSystem {\n" +
            "    private Map<String, User> users = new HashMap<>();\n" +
            "    private Map<String, Session> activeSessions = new HashMap<>();\n\n" +
            "    public boolean register(String username, String password) {\n" +
            "        if (users.containsKey(username)) return false;\n" +
            "        String hashedPassword = hashPassword(password);\n" +
            "        users.put(username, new User(username, hashedPassword));\n" +
            "        return true;\n" +
            "    }\n\n" +
            "    public String login(String username, String password) {\n" +
            "        User user = users.get(username);\n" +
            "        if (user != null && verifyPassword(password, user.getPassword())) {\n" +
            "            String sessionId = UUID.randomUUID().toString();\n" +
            "            activeSessions.put(sessionId, new Session(user, sessionId));\n" +
            "            return sessionId;\n" +
            "        }\n" +
            "        return null;\n" +
            "    }\n\n" +
            "    private String hashPassword(String password) {\n" +
            "        // Implementación de hash seguro\n" +
            "        return BCrypt.hashpw(password, BCrypt.gensalt());\n" +
            "    }\n\n" +
            "    private boolean verifyPassword(String password, String hashed) {\n" +
            "        return BCrypt.checkpw(password, hashed);\n" +
            "    }\n}",

            "SOLUCIÓN COMPLETA - SISTEMA DE PRÉSTAMOS\n\n" +
            "public class LoanManagementSystem {\n" +
            "    private List<Book> books;\n" +
            "    private List<Member> members;\n" +
            "    private List<Loan> activeLoans;\n\n" +
            "    public boolean loanBook(String memberId, String isbn) {\n" +
            "        Member member = findMember(memberId);\n" +
            "        Book book = findBook(isbn);\n\n" +
            "        if (member.canBorrow() && book.isAvailable()) {\n" +
            "            Loan loan = new Loan(member, book, LocalDate.now(), LocalDate.now().plusWeeks(2));\n" +
            "            activeLoans.add(loan);\n" +
            "            member.borrowBook(book);\n" +
            "            return true;\n" +
            "        }\n" +
            "        return false;\n" +
            "    }\n\n" +
            "    public boolean returnBook(String memberId, String isbn) {\n" +
            "        for (Loan loan : activeLoans) {\n" +
            "            if (loan.getMember().getId().equals(memberId) && \n" +
            "                loan.getBook().getIsbn().equals(isbn)) {\n" +
            "                loan.getMember().returnBook(loan.getBook());\n" +
            "                activeLoans.remove(loan);\n" +
            "                return true;\n" +
            "            }\n" +
            "        }\n" +
            "        return false;\n" +
            "    }\n}",

            "SOLUCIÓN COMPLETA - SISTEMA DE VENTAS\n\n" +
            "public class ShoppingCartSystem {\n" +
            "    private Map<Product, Integer> cart = new HashMap<>();\n" +
            "    private List<Sale> sales = new ArrayList<>();\n\n" +
            "    public void addToCart(Product product, int quantity) {\n" +
            "        if (product.getStock() >= quantity) {\n" +
            "            cart.put(product, cart.getOrDefault(product, 0) + quantity);\n" +
            "        }\n" +
            "    }\n\n" +
            "    public double calculateTotal() {\n" +
            "        double total = 0;\n" +
            "        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {\n" +
            "            total += entry.getKey().getPrice() * entry.getValue();\n" +
            "        }\n" +
            "        return total * 1.16; // 16% IVA\n" +
            "    }\n\n" +
            "    public boolean checkout() {\n" +
            "        // Verificar stock disponible\n" +
            "        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {\n" +
            "            if (entry.getKey().getStock() < entry.getValue()) {\n" +
            "                return false;\n" +
            "            }\n" +
            "        }\n\n" +
            "        // Procesar venta\n" +
            "        Sale sale = new Sale(cart);\n" +
            "        sales.add(sale);\n\n" +
            "        // Reducir stock\n" +
            "        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {\n" +
            "            entry.getKey().reduceStock(entry.getValue());\n" +
            "        }\n\n" +
            "        cart.clear();\n" +
            "        return true;\n" +
            "    }\n}",

            "SOLUCIÓN COMPLETA - SISTEMA FARMACÉUTICO\n\n" +
            "public class PharmacyInventorySystem {\n" +
            "    private List<Medicine> medicines;\n" +
            "    private List<Prescription> prescriptions;\n\n" +
            "    public List<Medicine> getExpiringMedicines() {\n" +
            "        LocalDate threeMonthsFromNow = LocalDate.now().plusMonths(3);\n" +
            "        return medicines.stream()\n" +
            "            .filter(med -> med.getExpirationDate().isBefore(threeMonthsFromNow))\n" +
            "            .collect(Collectors.toList());\n" +
            "    }\n\n" +
            "    public boolean dispenseMedicine(String prescriptionId, String medicineId) {\n" +
            "        Prescription prescription = findPrescription(prescriptionId);\n" +
            "        Medicine medicine = findMedicine(medicineId);\n\n" +
            "        if (prescription != null && medicine != null && \n" +
            "            prescription.canBeFilled() && medicine.getStock() >= prescription.getQuantity()) {\n" +
            "            \n" +
            "            medicine.reduceStock(prescription.getQuantity());\n" +
            "            prescription.markAsFilled();\n" +
            "            return true;\n" +
            "        }\n" +
            "        return false;\n" +
            "    }\n\n" +
            "    public List<String> generateAlerts() {\n" +
            "        List<String> alerts = new ArrayList<>();\n" +
            "        \n" +
            "        // Alertas de vencimiento\n" +
            "        for (Medicine med : getExpiringMedicines()) {\n" +
            "            alerts.add(\"MEDICAMENTO POR VENCER: \" + med.getName());\n" +
            "        }\n" +
            "        \n" +
            "        // Alertas de stock bajo\n" +
            "        for (Medicine med : medicines) {\n" +
            "            if (med.isLowStock()) {\n" +
            "                alerts.add(\"STOCK BAJO: \" + med.getName());\n" +
            "            }\n" +
            "        }\n" +
            "        \n" +
            "        return alerts;\n" +
            "    }\n}"
        };

        JTextArea solutionArea = new JTextArea(solutions[exerciseIndex]);
        solutionArea.setEditable(false);
        solutionArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        solutionArea.setBackground(Color.BLACK);
        solutionArea.setForeground(Color.GREEN);

        JScrollPane scrollPane = new JScrollPane(solutionArea);
        scrollPane.setPreferredSize(new Dimension(700, 500));

        JOptionPane.showMessageDialog(this, scrollPane, "Solución Completa",
            JOptionPane.INFORMATION_MESSAGE);
    }
}