package distance.calcu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;



public class RouteServe {
    public static double getDistance(
        double originLatitude,
        double originLongitude,
        double destinationLatitude,
        double destinationLongitude) throws Exception {

    String urlString = "http://router.project-osrm.org/route/v1/driving/"
            + originLongitude + "," + originLatitude
            + ";" + destinationLongitude + "," + destinationLatitude
            + "?overview=false";

    URL url = new URL(urlString);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("User-Agent", "DistanceCalculator/1.0");

    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        response.append(line);
    }
    reader.close();

    JSONObject obj = new JSONObject(response.toString());
    double distanceMeters = obj.getJSONArray("routes")
                               .getJSONObject(0)
                               .getDouble("distance");

    return distanceMeters / 1000; // convert to km
}
}

