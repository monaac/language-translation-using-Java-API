import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TranslateAPI {
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String BASE_URL = "https://translation.googleapis.com/language/translate/v2?";

    public static String translate(String sourceText, String sourceLang, String targetLang) throws Exception {
        URL url = new URL(BASE_URL + "q=" + sourceText + "&target=" + targetLang + "&source=" + sourceLang + "&key=" + API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        if (status != 200) {
            throw new Exception("Failed to translate text. HTTP status: " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }
}


You can call this API by passing the source text, source language, and target language as parameters, as shown below:

// code for javascript

String sourceText = "Hello, World!";
String sourceLang = "en";
String targetLang = "fr";

try {
    String translatedText = TranslateAPI.translate(sourceText, sourceLang, targetLang);
    System.out.println(translatedText);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
