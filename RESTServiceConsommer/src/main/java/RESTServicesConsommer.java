import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class can implement a GoF like proxy to redirect to the real method that will process the diff info (not only suggested positions)
 */
public class RESTServicesConsommer{

    public RESTServicesConsommer(){}

    // Call the services in the provided URL
    // NB. CAn be improved as the GoF to divide the diff kind of data published by the API
    public String GET(String url){
        StringBuffer output = new StringBuffer();
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }


}