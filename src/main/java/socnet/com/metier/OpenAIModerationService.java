package socnet.com.metier;

import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class OpenAIModerationService {
    private static final String API_KEY = "";
    private static final String API_URL = "https://api.openai.com/v1/moderations";

    public static boolean isContentFlagged(String inputText) throws IOException {
        URL url = new URL(API_URL);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = "{ \"input\": " + JSONObject.quote(inputText) + " }";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        if (code != 200) {
            throw new IOException("Erreur d'appel API : " + code);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }

        JSONObject json = new JSONObject(response.toString());
        return json.getJSONArray("results")
                   .getJSONObject(0)
                   .getBoolean("flagged");


    }
}