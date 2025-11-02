/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.auth;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;




/**
 *
 * @author jocde
 */
public class UserManager {
    private static final String USERS_FILE = "users.txt";
    private static final String DEPENDENCIA_FILE = "dependencia.txt";
    private static String ADMIN_USERNAME = null;
    private static String ADMIN_PASSWORD = null;
    private static String currentUser = null;
    private static UserStats currentUserStats = null;
    private static boolean isAdmin = false;

    // Achievement management
    private static final String ACHIEVEMENTS_FILE = "achievements.txt";
    private static Map<String, String> achievementNames = new HashMap<>();
    private static Map<String, String> achievementDescriptions = new HashMap<>();

    /**
     * Loads admin credentials from dependencia.txt file.
     */
    private static void loadAdminCredentials() {
        if (ADMIN_USERNAME != null) {
            return; // Already loaded
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DEPENDENCIA_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    ADMIN_USERNAME = parts[0].trim();
                    ADMIN_PASSWORD = parts[1].trim();
                }
            }
        } catch (IOException e) {
            // If file doesn't exist, set default credentials
            ADMIN_USERNAME = "maker";
            ADMIN_PASSWORD = "1998";
            // Create the file with default credentials
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEPENDENCIA_FILE))) {
                writer.write(ADMIN_USERNAME + "," + ADMIN_PASSWORD);
                writer.newLine();
            } catch (IOException ex) {
                System.err.println("Error creating dependencia.txt: " + ex.getMessage());
            }
        }
    }

    /**
     * Registers a new user with username and password.
     *
     * @param username The username for the new user
     * @param password The password for the new user
     * @return true if registration successful, false if user already exists
     */
    public static boolean registerUser(String username, String password) {
        if (userExists(username)) {
            return false; // User already exists
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();

            // Create user progress file
            createUserProgressFile(username);
            return true;
        } catch (IOException e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads achievements from file.
     */
    private static void loadAchievements() {
        achievementNames.clear();
        achievementDescriptions.clear();

        // Load default achievements
        achievementNames.put("first_lesson", "Primera Lección");
        achievementNames.put("quiz_master", "Maestro del Quiz");
        achievementNames.put("code_challenger", "Desafiador de Código");
        achievementNames.put("swing_expert", "Experto en Swing");
        achievementNames.put("daily_challenger", "Desafiador Diario");
        achievementNames.put("level_5", "Nivel 5 Alcanzado");

        achievementDescriptions.put("first_lesson", "Completa tu primera lección");
        achievementDescriptions.put("quiz_master", "Obtén 100% en cualquier quiz");
        achievementDescriptions.put("code_challenger", "Completa 3 desafíos de código en una lección");
        achievementDescriptions.put("swing_expert", "Completa todas las lecciones con 80% o más");
        achievementDescriptions.put("daily_challenger", "Completa desafíos diarios por 7 días seguidos");
        achievementDescriptions.put("level_5", "Alcanza el nivel 5");

        // Load custom achievements from file
        try (BufferedReader reader = new BufferedReader(new FileReader(ACHIEVEMENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 3);
                if (parts.length == 3) {
                    achievementNames.put(parts[0], parts[1]);
                    achievementDescriptions.put(parts[0], parts[2]);
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, which is fine
        }
    }

    /**
     * Saves achievements to file.
     */
    private static void saveAchievements() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACHIEVEMENTS_FILE))) {
            for (Map.Entry<String, String> entry : achievementNames.entrySet()) {
                String id = entry.getKey();
                String name = entry.getValue();
                String description = achievementDescriptions.getOrDefault(id, "");
                writer.write(id + "|" + name + "|" + description);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving achievements: " + e.getMessage());
        }
    }

    /**
     * Gets the achievement name for display.
     *
     * @param achievementId The achievement ID
     * @return The display name
     */
    public static String getAchievementName(String achievementId) {
        if (achievementNames.isEmpty()) {
            loadAchievements();
        }
        return achievementNames.getOrDefault(achievementId, achievementId);
    }

    /**
     * Gets the achievement description.
     *
     * @param achievementId The achievement ID
     * @return The description
     */
    public static String getAchievementDescription(String achievementId) {
        if (achievementDescriptions.isEmpty()) {
            loadAchievements();
        }
        return achievementDescriptions.getOrDefault(achievementId, "");
    }

    /**
     * Gets all achievement IDs.
     *
     * @return Set of all achievement IDs
     */
    public static Set<String> getAllAchievementIds() {
        if (achievementNames.isEmpty()) {
            loadAchievements();
        }
        return new HashSet<>(achievementNames.keySet());
    }

    /**
     * Adds a new achievement.
     *
     * @param id The achievement ID
     * @param name The display name
     * @param description The description
     * @return true if added successfully, false if ID already exists
     */
    public static boolean addAchievement(String id, String name, String description) {
        if (!isAdmin) {
            return false;
        }

        if (achievementNames.containsKey(id)) {
            return false; // Achievement already exists
        }

        achievementNames.put(id, name);
        achievementDescriptions.put(id, description);
        saveAchievements();
        return true;
    }

    /**
     * Removes an achievement.
     *
     * @param id The achievement ID to remove
     * @return true if removed successfully, false if not found or not admin
     */
    public static boolean removeAchievement(String id) {
        if (!isAdmin) {
            return false;
        }

        if (!achievementNames.containsKey(id)) {
            return false; // Achievement doesn't exist
        }

        achievementNames.remove(id);
        achievementDescriptions.remove(id);
        saveAchievements();
        return true;
    }

    /**
     * Authenticates a user with username and password.
       *
     * @param username The username to authenticate
     * @param password The password to check
     * @return true if authentication successful, false otherwise
     */
    public static boolean loginUser(String username, String password) {
        // Load admin credentials if not loaded
        loadAdminCredentials();

        // Check for admin credentials first
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            currentUser = username;
            currentUserStats = new UserStats(username);
            isAdmin = true;
            return true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    currentUser = username;
                    currentUserStats = new UserStats(username);
                    isAdmin = false;
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error during login: " + e.getMessage());
        }
        return false;
    }

    /**
     * Checks if a user already exists.
     * 
     * @param username The username to check
     * @return true if user exists, false otherwise
     */
    private static boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, which is fine
        }
        return false;
    }

    /**
     * Creates a progress file for a new user.
     * 
     * @param username The username for whom to create the progress file
     */
    private static void createUserProgressFile(String username) {
        String filename = "user_" + username + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Initialize with all lessons as not interested/completed
            String[] lessons = { "Texto", "Botones", "Selección", "Avanzados", "Otros", "Adicionales" };
            for (String lesson : lessons) {
                writer.write(lesson + ",none"); // none, interested, or completed
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error creating user progress file: " + e.getMessage());
        }
    }

    /**
     * Gets the current logged-in user.
     * 
     * @return The current username or null if no user is logged in
     */
    public static String getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs out the current user.
     */
    public static void logout() {
        currentUser = null;
        currentUserStats = null;
        isAdmin = false;
    }

    /**
     * Gets the current user stats.
     *
     * @return The current user stats instance
     */
    public static UserStats getCurrentUserStats() {
        return currentUserStats;
    }

    /**
     * Updates the progress for a specific lesson for the current user.
     * 
     * @param lessonName The name of the lesson
     * @param status     The status: "none", "interested", or "completed"
     */
    public static void updateLessonProgress(String lessonName, String status) {
        if (currentUser == null)
            return;

        String filename = "user_" + currentUser + ".txt";
        List<String> lines = new ArrayList<>();

        // Read existing progress
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(lessonName)) {
                    lines.add(lessonName + "," + status);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user progress: " + e.getMessage());
            return;
        }

        // Write updated progress
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating user progress: " + e.getMessage());
        }
    }

    /**
     * Gets the progress status for a specific lesson.
     * 
     * @param lessonName The name of the lesson
     * @return The status: "none", "interested", or "completed"
     */
    public static String getLessonProgress(String lessonName) {
        if (currentUser == null)
            return "none";

        String filename = "user_" + currentUser + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(lessonName)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            // Return default if file doesn't exist
        }
        return "none";
    }

    /**
     * Gets all lesson progress for the current user.
     * 
     * @return A map of lesson names to their progress status
     */
    public static Map<String, String> getAllLessonProgress() {
        Map<String, String> progress = new HashMap<>();
        if (currentUser == null)
            return progress;

        String filename = "user_" + currentUser + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    progress.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // Return empty map if error
        }
        return progress;
    }

    /**
     * Checks if the current user is an admin.
     *
     * @return true if the current user is admin, false otherwise
     */
    public static boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Gets all registered users (admin only).
     *
     * @return List of all usernames
     */
    public static List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    users.add(parts[0]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
        return users;
    }

    /**
     * Deletes a user and their progress file (admin only).
      *
     * @param username The username to delete
     * @return true if deletion successful, false otherwise
     */
    public static boolean deleteUser(String username) {
        loadAdminCredentials(); // Ensure credentials are loaded
        if (!isAdmin || ADMIN_USERNAME.equals(username)) {
            return false; // Cannot delete admin or if not admin
        }

        // Remove from users.txt
        List<String> lines = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && !parts[0].equals(username)) {
                    lines.add(line);
                } else if (parts[0].equals(username)) {
                    found = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
            return false;
        }

        if (!found) {
            return false; // User not found
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing users file: " + e.getMessage());
            return false;
        }

        // Delete user progress file
        String progressFile = "user_" + username + ".txt";
        try {
            java.io.File file = new java.io.File(progressFile);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.err.println("Error deleting user progress file: " + e.getMessage());
        }

        return true;
    }

    /**
     * Updates lesson progress for a specific user (admin only).
     *
     * @param username The username
     * @param lessonName The lesson name
     * @param status The status ("none", "interested", "completed")
     * @return true if update successful, false otherwise
     */
    public static boolean updateUserLessonProgress(String username, String lessonName, String status) {
        if (!isAdmin) {
            return false;
        }

        String filename = "user_" + username + ".txt";
        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Read existing progress
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(lessonName)) {
                    lines.add(lessonName + "," + status);
                    found = true;
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            // File might not exist, create it
        }

        if (!found) {
            // Add new lesson entry
            lines.add(lessonName + "," + status);
        }

        // Write updated progress
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating user progress: " + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Gets lesson progress for a specific user (admin only).
     *
     * @param username The username
     * @return Map of lesson names to progress status
     */
    public static Map<String, String> getUserLessonProgress(String username) {
        Map<String, String> progress = new HashMap<>();
        if (!isAdmin) {
            return progress;
        }

        String filename = "user_" + username + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    progress.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            // Return empty map if error
        }
        return progress;
    }

    /**
     * Gets user stats for a specific user (admin only).
     *
     * @param username The username
     * @return UserStats instance for the user
     */
    public static UserStats getUserStats(String username) {
        if (!isAdmin) {
            return null;
        }
        return new UserStats(username);
    }

    /**
     * Updates user XP directly (admin only).
     *
     * @param username The username
     * @param newXP The new total XP value
     * @return true if update successful, false otherwise
     */
    public static boolean updateUserXP(String username, int newXP) {
        if (!isAdmin) {
            return false;
        }

        UserStats userStats = getUserStats(username);
        if (userStats == null) {
            return false;
        }

        // Calculate difference and add it
        int difference = newXP - userStats.getTotalXP();
        userStats.addXP(difference);
        return true;
    }

    /**
     * Updates user level directly (admin only).
     *
     * @param username The username
     * @param newLevel The new level
     * @return true if update successful, false otherwise
     */
    public static boolean updateUserLevel(String username, int newLevel) {
        if (!isAdmin) {
            return false;
        }

        UserStats userStats = getUserStats(username);
        if (userStats == null) {
            return false;
        }

        // This is a simplified approach - in a real system you'd want more sophisticated level management
        // For now, we'll just set the level directly by adjusting XP
        int targetXP = 0;
        for (int i = 1; i < newLevel; i++) {
            targetXP += (int) (100 * Math.pow(1.5, i - 1));
        }

        int difference = targetXP - userStats.getTotalXP();
        userStats.addXP(difference);
        return true;
    }

    /**
     * Grants or revokes an achievement for a user (admin only).
     *
     * @param username The username
     * @param achievementId The achievement ID
     * @param grant true to grant, false to revoke
     * @return true if update successful, false otherwise
     */
    public static boolean updateUserAchievement(String username, String achievementId, boolean grant) {
        if (!isAdmin) {
            return false;
        }

        UserStats userStats = getUserStats(username);
        if (userStats == null) {
            return false;
        }

        if (grant) {
            userStats.getUnlockedAchievements().add(achievementId);
        } else {
            userStats.getUnlockedAchievements().remove(achievementId);
        }

        // Force save
        userStats.addXP(0);
        return true;
    }
}