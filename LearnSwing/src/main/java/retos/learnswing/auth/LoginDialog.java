/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.auth;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 *
 * @author jocde
 */
public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean loginSuccessful = false;

    /**
     * Constructor for LoginDialog.
     * 
     * @param parent The parent frame
     */
    public LoginDialog(Frame parent) {
        super(parent, "Login - Swing Learning System", true);
        initializeComponents();
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * Initializes the dialog components.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Sistema de Aprendizaje Swing"));
        add(titlePanel, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Usuario:"));
        usernameField = new JTextField(15);
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField(15);
        inputPanel.add(passwordField);

        add(inputPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performRegistration();
            }
        });
        buttonPanel.add(registerButton);

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Enter key support
        getRootPane().setDefaultButton(loginButton);
    }

    /**
     * Performs the login operation.
     */
    private void performLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese usuario y contraseña.",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (UserManager.loginUser(username, password)) {
            loginSuccessful = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.",
                    "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Performs the registration operation.
     */
    private void performRegistration() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese usuario y contraseña.",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "El usuario debe tener al menos 3 caracteres.",
                    "Usuario Inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres.",
                    "Contraseña Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (UserManager.registerUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente. Ahora puede iniciar sesión.",
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "El usuario ya existe. Elija un nombre diferente.",
                    "Usuario Existente", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows the dialog and returns whether login was successful.
     * 
     * @return true if login was successful, false otherwise
     */
    public boolean showDialog() {
        setVisible(true);
        return loginSuccessful;
    }
}