package socnet.com.entities;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;
public class AICheck {
    private static final String API_KEY = ""; // Remplace par ta vraie clé
    private static final String API_URL = "https://api.openai.com/v1/moderations";

    public static boolean checkViolentContent(String content) throws IOException {
        URL url = new URL(API_URL);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        JSONObject body = new JSONObject();
        body.put("input", content);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] inputBytes = body.toString().getBytes("utf-8");
            os.write(inputBytes, 0, inputBytes.length);
        }

        // Lire la réponse
        int responseCode = conn.getResponseCode();
        InputStream is = (responseCode >= 200 && responseCode < 300) ? conn.getInputStream() : conn.getErrorStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
        StringBuilder responseBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line.trim());
        }

        String response = responseBuilder.toString();
        System.out.println("🧠 OpenAI Response: " + response); // ← Pour debug

        // Analyse du JSON
        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray("results");
        if (results.length() > 0) {
            JSONObject result = results.getJSONObject(0);
            boolean flagged = result.getBoolean("flagged");
            System.out.println("✅ Flagged: " + flagged);
            return flagged;
        }

        return false; // par défaut, non violent
    }
}