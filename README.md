# Java Swing Tool Pack / Paquete de Herramientas Java Swing

## English Description

A comprehensive Java Swing learning application designed to teach developers how to use various Swing components through interactive lessons, quizzes, and coding challenges. The application features a user management system with progress tracking, achievements, and admin capabilities.

### Key Features

- **Interactive Lessons**: Learn about different Swing components including text fields, buttons, selection components, advanced tables, trees, and charts
- **User Management**: Registration and login system with progress tracking
- **Achievements System**: Unlock achievements as you complete lessons and challenges
- **Quizzes**: Test your knowledge with interactive quizzes for each lesson
- **Coding Challenges**: Practice with real coding challenges that validate your solutions
- **Admin Panel**: Administrative tools for managing users, achievements, and system settings
- **Progress Tracking**: Track your learning progress across all lessons
- **XP and Leveling System**: Gain experience points and level up as you learn

### Technologies Used

- **Java**: Core programming language (JDK 21)
- **Swing**: GUI framework for creating desktop applications
- **Maven**: Build and dependency management
- **File I/O**: For user data persistence

## Descripción en Español

Una aplicación completa de aprendizaje de Java Swing diseñada para enseñar a los desarrolladores cómo usar varios componentes Swing a través de lecciones interactivas, cuestionarios y desafíos de codificación. La aplicación cuenta con un sistema de gestión de usuarios con seguimiento de progreso, logros y capacidades administrativas.

### Características Principales

- **Lecciones Interactivas**: Aprende sobre diferentes componentes Swing incluyendo campos de texto, botones, componentes de selección, tablas avanzadas, árboles y gráficos
- **Gestión de Usuarios**: Sistema de registro e inicio de sesión con seguimiento de progreso
- **Sistema de Logros**: Desbloquea logros mientras completas lecciones y desafíos
- **Cuestionarios**: Prueba tus conocimientos con cuestionarios interactivos para cada lección
- **Desafíos de Codificación**: Practica con desafíos de codificación reales que validan tus soluciones
- **Panel Administrativo**: Herramientas administrativas para gestionar usuarios, logros y configuraciones del sistema
- **Seguimiento de Progreso**: Rastrea tu progreso de aprendizaje en todas las lecciones
- **Sistema de XP y Niveles**: Gana puntos de experiencia y sube de nivel mientras aprendes

### Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal (JDK 21)
- **Swing**: Framework GUI para crear aplicaciones de escritorio
- **Maven**: Construcción y gestión de dependencias
- **File I/O**: Para persistencia de datos de usuario

## Project Structure / Estructura del Proyecto

```
Java_Swing_Tool_Pack/
├── LearnSwing/                          # Main application directory
│   ├── src/main/java/retos/learnswing/  # Source code
│   │   ├── LearnSwing.java              # Main application class
│   │   ├── auth/                        # Authentication system
│   │   │   ├── LoginDialog.java         # Login dialog
│   │   │   ├── UserManager.java         # User management
│   │   │   └── UserStats.java           # User statistics and achievements
│   │   ├── UI/                          # User interface components
│   │   │   ├── DashboardPanel.java      # Main dashboard
│   │   │   └── AdminPanel.java          # Admin management panel
│   │   └── panels/                      # Lesson panels
│   │       ├── TextPanel.java           # Text components lesson
│   │       ├── ButtonPanel.java         # Button components lesson
│   │       ├── SelectionPanel.java      # Selection components lesson
│   │       ├── AdvancedPanel.java       # Advanced components lesson
│   │       ├── OtherPanel.java          # Other components lesson
│   │       ├── AdditionalComponentsPanel.java # Additional components
│   │       ├── ChartsPanel.java         # Charts and graphics lesson
│   │       ├── AchievementPanel.java    # Achievements display
│   │       ├── ChallengePanel.java      # Coding challenges
│   │       ├── CodeViewPanel.java       # Code viewer
│   │       └── QuizPanel.java           # Quiz system
│   ├── pom.xml                          # Maven configuration
│   ├── LearnSwing.jar                   # Compiled JAR file
│   ├── users.txt                        # User credentials file
│   ├── dependencia.txt                  # Admin credentials file
│   └── user_stats/                      # User statistics directory
└── README.md                            # This file
```

## Installation and Setup / Instalación y Configuración

### Prerequisites / Prerrequisitos

- Java Development Kit (JDK) 21 or higher / Kit de Desarrollo Java (JDK) 21 o superior
- Maven 3.6+ (for building from source) / Maven 3.6+ (para construir desde el código fuente)

### Running the Application / Ejecutando la Aplicación

#### Option 1: Using the JAR file / Opción 1: Usando el archivo JAR

1. Ensure Java 21+ is installed / Asegúrate de que Java 21+ esté instalado
2. Double-click `LearnSwing.jar` or run from command line / Haz doble clic en `LearnSwing.jar` o ejecuta desde línea de comandos:
   ```bash
   java -jar LearnSwing.jar
   ```

#### Option 2: Building from source / Opción 2: Construyendo desde el código fuente

1. Clone or download the project / Clona o descarga el proyecto
2. Navigate to the LearnSwing directory / Navega al directorio LearnSwing:
   ```bash
   cd LearnSwing
   ```
3. Build with Maven / Construye con Maven:
   ```bash
   mvn clean compile
   ```
4. Run the application / Ejecuta la aplicación:
   ```bash
   mvn exec:java
   ```

### Default Admin Credentials / Credenciales de Administrador por Defecto

- **Username / Usuario**: maker
- **Password / Contraseña**: 1998

*Note: These can be changed by editing `dependencia.txt` / Nota: Estas pueden cambiarse editando `dependencia.txt`*

## Usage Guide / Guía de Uso

### First Time Setup / Configuración Inicial

1. **Login / Inicio de Sesión**: Use admin credentials or register a new account / Usa las credenciales de administrador o registra una nueva cuenta
2. **Dashboard / Panel Principal**: Overview of your progress and available lessons / Vista general de tu progreso y lecciones disponibles

### Learning Flow / Flujo de Aprendizaje

1. **View Lesson / Ver Lección**: Click "Ver Lección" to explore Swing components / Haz clic en "Ver Lección" para explorar componentes Swing
2. **Take Quiz / Hacer Cuestionario**: Test your knowledge with "Hacer Quiz" / Prueba tus conocimientos con "Hacer Quiz"
3. **Coding Challenge / Desafío de Codificación**: Practice with "Desafío" button / Practica con el botón "Desafío"
4. **Mark Complete / Marcar Completado**: Mark lessons as completed to track progress / Marca lecciones como completadas para rastrear el progreso

### Lessons Available / Lecciones Disponibles

1. **Texto / Text**: Text fields, password fields, and text areas / Campos de texto, campos de contraseña y áreas de texto
2. **Botones / Buttons**: Various button types and toggle components / Varios tipos de botones y componentes de alternancia
3. **Selección / Selection**: Combo boxes and list components / Cuadros combinados y componentes de lista
4. **Avanzados / Advanced**: Tables, sliders, and progress bars / Tablas, controles deslizantes y barras de progreso
5. **Otros / Other**: Spinners, formatted fields, and toggles / Contadores, campos formateados y alternadores
6. **Adicionales / Additional**: Trees, file choosers, and color pickers / Árboles, selectores de archivos y selectores de color
7. **Gráficos / Charts**: 2D graphics, bar charts, line charts, and pie charts / Gráficos 2D, gráficos de barras, gráficos de líneas y gráficos circulares

### Admin Features / Características Administrativas

- **User Management / Gestión de Usuarios**: View, delete, and manage user accounts / Ver, eliminar y gestionar cuentas de usuario
- **Achievement Management / Gestión de Logros**: Create and manage custom achievements / Crear y gestionar logros personalizados
- **Progress Management / Gestión de Progreso**: Update user progress and statistics / Actualizar progreso y estadísticas de usuario

## Features in Detail / Características en Detalle

### User System / Sistema de Usuarios
- Secure login and registration / Inicio de sesión y registro seguro
- Progress tracking per user / Seguimiento de progreso por usuario
- Individual user statistics / Estadísticas individuales de usuario

### Achievement System / Sistema de Logros
- Predefined achievements for milestones / Logros predefinidos para hitos
- Custom achievements (admin only) / Logros personalizados (solo administrador)
- Visual achievement badges / Insignias de logros visuales

### Quiz System / Sistema de Cuestionarios
- Multiple choice questions / Preguntas de opción múltiple
- Immediate feedback / Retroalimentación inmediata
- Score tracking / Seguimiento de puntuación

### Challenge System / Sistema de Desafíos
- Code validation challenges / Desafíos de validación de código
- Multiple attempts allowed / Múltiples intentos permitidos
- Hint system / Sistema de pistas

### Admin Panel / Panel Administrativo
- User management interface / Interfaz de gestión de usuarios
- Achievement creation tools / Herramientas de creación de logros
- System statistics / Estadísticas del sistema

## Contributing / Contribuyendo

1. Fork the repository / Haz un fork del repositorio
2. Create a feature branch / Crea una rama de características
3. Make your changes / Haz tus cambios
4. Test thoroughly / Prueba exhaustivamente
5. Submit a pull request / Envía una solicitud de extracción

## License / Licencia

This project is open source. Please check the license file for details. / Este proyecto es de código abierto. Por favor revisa el archivo de licencia para detalles.

## Support / Soporte

For questions or issues, please create an issue in the repository. / Para preguntas o problemas, por favor crea un issue en el repositorio.

---

*Built with ❤️ for Java Swing learning / Construido con ❤️ para el aprendizaje de Java Swing*