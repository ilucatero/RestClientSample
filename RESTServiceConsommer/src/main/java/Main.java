import java.util.Scanner;
/**
 * Created by Wizard on 23/01/2016.
 */
public class Main {

    public static void main(String[] args) {
        String httpREST_GetURL =  "http://api.goeuro.com/api/v2/position/suggest/en/";
        String suggestedWord = "";
        RESTServicesConsommer restServicesConsommer = new RESTServicesConsommer();

        //Request user to enter a word
        Scanner s = new Scanner(System.in);
        System.out.println("Add a city/localization to search :");
        suggestedWord = s.nextLine();

        String json = restServicesConsommer.GET(httpREST_GetURL + suggestedWord);
        System.out.println("json found:" +json);

        System.out.println("cvs saved:" + JSONProcessing.JSON2FiledCVS_Position(json) );

    }
}