package com.example.Telegram_part.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MyMain {
    public static String runDeepSeek(String message) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "python",
                    "C:\\Users\\hirof\\IdeaProjects\\kkrk_anki\\Telegram_part\\src\\main\\resources\\static\\deepseek_script.py",
                    message
            );
            pb.redirectErrorStream(true); // Ошибки и вывод в одном потоке
            Process process = pb.start();

            StringBuilder output = new StringBuilder();
            // Используем UTF-8 для чтения вывода
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8)
            );
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString().trim(); // Возвращаем ответ
            } else {
                throw new RuntimeException("Ошибка в скрипте: " + output.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось запустить DeepSeek: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        String response1 = runDeepSeek("Привет, как дела?");
        System.out.println("Ответ 1: " + response1);
        String response2 = runDeepSeek("Что нового?");
        System.out.println("Ответ 2: " + response2);
    }
}