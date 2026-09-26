
package distance.calcu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class locationService {

    public static double[] getCoordinates(String location) throws Exception {

        System.setProperty("https.protocols", "TLSv1.2");
        String encodedLocation = URLEncoder.encode(location, "UTF-8");

        String urlString =
                "https://nominatim.openstreetmap.org/search"
                + "?q=" + encodedLocation
                + "&format=jsonv2"
                + "&limit=1";

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty( "User-Agent","DistanceCalculator/1.0");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        StringBuilder response;
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        )) {
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        connection.disconnect();

        String json = response.toString();
        if (json.equals("[]")) {
            return null;
        }
        int latStart = json.indexOf("\"lat\":\"") + 7;
        int latEnd = json.indexOf("\"", latStart);

        int lonStart = json.indexOf("\"lon\":\"") + 7;
        int lonEnd = json.indexOf("\"", lonStart);

        double latitude = Double.parseDouble(json.substring(latStart, latEnd));
        double longitude = Double.parseDouble(json.substring(lonStart, lonEnd));
        return new double[]{
            latitude, longitude 
        };
    }
}

