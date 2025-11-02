/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package retos.learnswing.auth;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Manages user statistics, points, levels, and achievements for gamification.
 *
 * @author jocde
 */
public class UserStats {
    private static final String STATS_DIR = "user_stats";

    // Achievement constants
    public static final String ACHIEVEMENT_FIRST_LESSON = "first_lesson";
    public static final String ACHIEVEMENT_QUIZ_MASTER = "quiz_master";
    public static final String ACHIEVEMENT_CODE_CHALLENGER = "code_challenger";
    public static final String ACHIEVEMENT_SWING_EXPERT = "swing_expert";
    public static final String ACHIEVEMENT_DAILY_CHALLENGER = "daily_challenger";

    private String username;
    private int totalXP;
    private int level;
    private int currentLevelXP;
    private int xpToNextLevel;
    private Map<String, Integer> lessonScores; // lesson -> score
    private Set<String> unlockedAchievements;
    private Map<String, Integer> quizAttempts; // lesson -> attempts
    private Map<String, Integer> challengeCompletions; // lesson -> completions
    private LocalDate lastDailyChallenge;
    private int dailyChallengeStreak;

    /**
     * Constructor for UserStats.
     *
     * @param username The username for this stats instance
     */
    public UserStats(String username) {
        this.username = username;
        this.totalXP = 0;
        this.level = 1;
        this.currentLevelXP = 0;
        this.xpToNextLevel = 100; // XP needed for level 2
        this.lessonScores = new HashMap<>();
        this.unlockedAchievements = new HashSet<>();
        this.quizAttempts = new HashMap<>();
        this.challengeCompletions = new HashMap<>();
        this.lastDailyChallenge = null;
        this.dailyChallengeStreak = 0;

        loadStats();
    }

    /**
     * Adds XP to the user and handles level ups.
     *
     * @param xp The amount of XP to add
     * @return true if user leveled up, false otherwise
     */
    public boolean addXP(int xp) {
        this.totalXP += xp;
        this.currentLevelXP += xp;

        boolean leveledUp = false;
        while (currentLevelXP >= xpToNextLevel) {
            levelUp();
            leveledUp = true;
        }

        saveStats();
        return leveledUp;
    }

    /**
     * Handles level up logic.
     */
    private void levelUp() {
        this.level++;
        this.currentLevelXP -= this.xpToNextLevel;
        this.xpToNextLevel = calculateXPForLevel(this.level + 1);

        // Check for level-based achievements
        checkLevelAchievements();
    }

    /**
     * Calculates XP required for a given level.
     *
     * @param level The level to calculate XP for
     * @return The XP required
     */
    private int calculateXPForLevel(int level) {
        // Exponential growth: base 100, multiplier 1.5 per level
        return (int) (100 * Math.pow(1.5, level - 2));
    }

    /**
     * Records a lesson completion with score.
     *
     * @param lessonName The name of the lesson
     * @param score The score achieved (0-100)
     */
    public void recordLessonScore(String lessonName, int score) {
        lessonScores.put(lessonName, Math.max(score, lessonScores.getOrDefault(lessonName, 0)));
        addXP(score / 10); // 10 XP per 100 points

        // Check for lesson-based achievements
        checkLessonAchievements(lessonName, score);
        saveStats();
    }

    /**
     * Records a quiz attempt.
     *
     * @param lessonName The name of the lesson
     * @param score The score achieved (0-100)
     */
    public void recordQuizAttempt(String lessonName, int score) {
        quizAttempts.put(lessonName, quizAttempts.getOrDefault(lessonName, 0) + 1);
        recordLessonScore(lessonName, score);
    }

    /**
     * Records a challenge completion.
     *
     * @param lessonName The name of the lesson
     */
    public void recordChallengeCompletion(String lessonName) {
        challengeCompletions.put(lessonName, challengeCompletions.getOrDefault(lessonName, 0) + 1);
        addXP(50); // 50 XP per challenge completion

        checkChallengeAchievements(lessonName);
        saveStats();
    }

    /**
     * Records daily challenge completion.
     */
    public void recordDailyChallenge() {
        LocalDate today = LocalDate.now();

        if (lastDailyChallenge != null && lastDailyChallenge.equals(today.minusDays(1))) {
            dailyChallengeStreak++;
        } else if (lastDailyChallenge == null || !lastDailyChallenge.equals(today)) {
            dailyChallengeStreak = 1;
        }

        lastDailyChallenge = today;
        addXP(100 + (dailyChallengeStreak * 10)); // Base 100 + 10 per streak day

        checkDailyAchievements();
        saveStats();
    }

    /**
     * Checks and unlocks lesson-based achievements.
     *
     * @param lessonName The lesson name
     * @param score The score achieved
     */
    private void checkLessonAchievements(String lessonName, int score) {
        // First lesson completion
        if (lessonScores.size() == 1 && !unlockedAchievements.contains(ACHIEVEMENT_FIRST_LESSON)) {
            unlockedAchievements.add(ACHIEVEMENT_FIRST_LESSON);
        }

        // Quiz master (perfect score on any quiz)
        if (score == 100 && !unlockedAchievements.contains(ACHIEVEMENT_QUIZ_MASTER)) {
            unlockedAchievements.add(ACHIEVEMENT_QUIZ_MASTER);
        }

        // Swing expert (all lessons completed with high scores)
        if (lessonScores.size() >= 6) {
            boolean allHighScores = lessonScores.values().stream().allMatch(s -> s >= 80);
            if (allHighScores && !unlockedAchievements.contains(ACHIEVEMENT_SWING_EXPERT)) {
                unlockedAchievements.add(ACHIEVEMENT_SWING_EXPERT);
            }
        }
    }

    /**
     * Checks and unlocks challenge-based achievements.
     *
     * @param lessonName The lesson name
     */
    private void checkChallengeAchievements(String lessonName) {
        if (challengeCompletions.getOrDefault(lessonName, 0) >= 3 &&
            !unlockedAchievements.contains(ACHIEVEMENT_CODE_CHALLENGER)) {
            unlockedAchievements.add(ACHIEVEMENT_CODE_CHALLENGER);
        }
    }

    /**
     * Checks and unlocks level-based achievements.
     */
    private void checkLevelAchievements() {
        // Level 5 achievement
        if (level >= 5 && !unlockedAchievements.contains("level_5")) {
            unlockedAchievements.add("level_5");
        }
    }

    /**
     * Checks and unlocks daily challenge achievements.
     */
    private void checkDailyAchievements() {
        if (dailyChallengeStreak >= 7 && !unlockedAchievements.contains(ACHIEVEMENT_DAILY_CHALLENGER)) {
            unlockedAchievements.add(ACHIEVEMENT_DAILY_CHALLENGER);
        }
    }

    /**
     * Gets the achievement name for display.
     *
     * @param achievementId The achievement ID
     * @return The display name
     */
    public static String getAchievementName(String achievementId) {
        return retos.learnswing.auth.UserManager.getAchievementName(achievementId);
    }

    /**
     * Gets the achievement description.
     *
     * @param achievementId The achievement ID
     * @return The description
     */
    public static String getAchievementDescription(String achievementId) {
        return retos.learnswing.auth.UserManager.getAchievementDescription(achievementId);
    }

    /**
     * Loads user stats from file.
     */
    private void loadStats() {
        File statsFile = new File(STATS_DIR, username + "_stats.txt");
        if (!statsFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(statsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length < 2) continue;

                switch (parts[0]) {
                    case "totalXP": totalXP = Integer.parseInt(parts[1]); break;
                    case "level": level = Integer.parseInt(parts[1]); break;
                    case "currentLevelXP": currentLevelXP = Integer.parseInt(parts[1]); break;
                    case "xpToNextLevel": xpToNextLevel = Integer.parseInt(parts[1]); break;
                    case "lastDailyChallenge": lastDailyChallenge = LocalDate.parse(parts[1]); break;
                    case "dailyChallengeStreak": dailyChallengeStreak = Integer.parseInt(parts[1]); break;
                    case "lessonScore":
                        String[] scoreParts = parts[1].split(",");
                        lessonScores.put(scoreParts[0], Integer.parseInt(scoreParts[1]));
                        break;
                    case "achievement":
                        unlockedAchievements.add(parts[1]);
                        break;
                    case "quizAttempt":
                        String[] attemptParts = parts[1].split(",");
                        quizAttempts.put(attemptParts[0], Integer.parseInt(attemptParts[1]));
                        break;
                    case "challengeCompletion":
                        String[] completionParts = parts[1].split(",");
                        challengeCompletions.put(completionParts[0], Integer.parseInt(completionParts[1]));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading user stats: " + e.getMessage());
        }
    }

    /**
     * Saves user stats to file.
     */
    private void saveStats() {
        File statsDir = new File(STATS_DIR);
        if (!statsDir.exists()) {
            statsDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(statsDir, username + "_stats.txt")))) {
            writer.write("totalXP:" + totalXP + "\n");
            writer.write("level:" + level + "\n");
            writer.write("currentLevelXP:" + currentLevelXP + "\n");
            writer.write("xpToNextLevel:" + xpToNextLevel + "\n");
            if (lastDailyChallenge != null) {
                writer.write("lastDailyChallenge:" + lastDailyChallenge + "\n");
            }
            writer.write("dailyChallengeStreak:" + dailyChallengeStreak + "\n");

            for (Map.Entry<String, Integer> entry : lessonScores.entrySet()) {
                writer.write("lessonScore:" + entry.getKey() + "," + entry.getValue() + "\n");
            }

            for (String achievement : unlockedAchievements) {
                writer.write("achievement:" + achievement + "\n");
            }

            for (Map.Entry<String, Integer> entry : quizAttempts.entrySet()) {
                writer.write("quizAttempt:" + entry.getKey() + "," + entry.getValue() + "\n");
            }

            for (Map.Entry<String, Integer> entry : challengeCompletions.entrySet()) {
                writer.write("challengeCompletion:" + entry.getKey() + "," + entry.getValue() + "\n");
            }

        } catch (IOException e) {
            System.err.println("Error saving user stats: " + e.getMessage());
        }
    }

    // Getters
    public int getTotalXP() { return totalXP; }
    public int getLevel() { return level; }
    public int getCurrentLevelXP() { return currentLevelXP; }
    public int getXpToNextLevel() { return xpToNextLevel; }
    public Map<String, Integer> getLessonScores() { return new HashMap<>(lessonScores); }
    public Set<String> getUnlockedAchievements() { return new HashSet<>(unlockedAchievements); }
    public Map<String, Integer> getQuizAttempts() { return new HashMap<>(quizAttempts); }
    public Map<String, Integer> getChallengeCompletions() { return new HashMap<>(challengeCompletions); }
    public int getDailyChallengeStreak() { return dailyChallengeStreak; }
    public LocalDate getLastDailyChallenge() { return lastDailyChallenge; }

    /**
     * Gets the progress percentage to next level.
     *
     * @return Progress as percentage (0-100)
     */
    public int getLevelProgressPercentage() {
        return (int) ((double) currentLevelXP / xpToNextLevel * 100);
    }
}