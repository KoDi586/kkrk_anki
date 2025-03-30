package com.example.Telegram_part.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class MyMain {
    private static Process process;
    private static BufferedWriter writer;
    private static BufferedReader reader;

    public static void startDeepSeek() throws IOException {
        ProcessBuilder pb = new ProcessBuilder(
                "python",
                "C:\\Users\\hirof\\IdeaProjects\\kkrk_anki\\Telegram_part\\src\\main\\resources\\static\\deepseek_script.py"
        );
        pb.redirectErrorStream(true);
        process = pb.start();

        writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8)
        );
        reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8)
        );

        String line;
        while ((line = reader.readLine()) != null) {
            System.err.println(line);
            if (line.equals("Ready")) break;
        }
    }

    public static String runDeepSeek(String message) throws IOException {
        if (process == null || !process.isAlive()) {
            startDeepSeek();
        }
        writer.write(message + "\n");
        writer.flush();

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals("END_RESPONSE")) break; // Останавливаемся на маркере
            response.append(line).append("\n");
        }
        return response.toString().trim();
    }

    public static void stopDeepSeek() throws IOException, InterruptedException {
        if (process != null && process.isAlive()) {
            writer.write("exit\n");
            writer.flush();
            process.waitFor();
            process = null;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("time start = " + LocalTime.now());
        startDeepSeek();
        try {
            String response1 = runDeepSeek("300 разделить на 5");
            System.out.println("Ответ 1: " + response1);
            System.out.println("1 answer time = " + LocalTime.now());

            String response2 = runDeepSeek("как часто падают звезды");
            System.out.println("Ответ 2: " + response2);
            System.out.println("second answer time = " + LocalTime.now());

            String response3 = runDeepSeek("8 в степени 3");
            System.out.println("Ответ 3: " + response3);
            System.out.println("3 answer time = " + LocalTime.now());
        } finally {
            stopDeepSeek();
        }
    }
}