// @Azubis pls dont abuse my API Key
// API
// Strings bearbeiten

package de.atruvia.p_tobias_kaufmann;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Rezeptideen_Schwer {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Scanner meinScanner = new Scanner(System.in);
        System.out.println("Willst du ein zufälligs Rezept oder hast du eine Idea mit welcher Zutat du etwas kochen möchtest?");
        System.out.println("Gebe 1 für ein zufälligs Rezept ein und 2 wenn du schon eine Idea hast?");
        if (meinScanner.nextInt() == 1){
            String rezeptInfo;
            do{
                rezeptInfo = getRandomRecipe();
                System.out.println(rezeptInfo.substring(rezeptInfo.indexOf("title")+8,rezeptInfo.indexOf('"',rezeptInfo.indexOf("title")+8)));
                System.out.println("Willst du zu diesem Rezept die Anleitung oder willst du ein anderes Rezept?");
                System.out.println("Gib 1 für die Anleitung zu diesem Rezept ein oder 2 für ein neues Rezept.");
            }while (meinScanner.nextInt() == 2);
            System.out.println(formatInstrucitons(rezeptInfo));
        } else {
            String rezeptInfo;
            do{
                System.out.println("Gib eine Zutat an die das Rezept enthalten soll. (Auf Englisch)");
                rezeptInfo = getRecipesByIngredient(meinScanner.next());
                System.out.println(rezeptInfo.substring(rezeptInfo.indexOf("title")+8,rezeptInfo.indexOf('"',rezeptInfo.indexOf("title")+8)));
                System.out.println("Willst du zu diesem Rezept die Anleitung oder willst du ein anderes Rezept?");
                System.out.println("Gib 1 für die Anleitung zu diesem Rezept ein oder 2 für ein neues Rezept.");System.out.println("Gib 1 für die Anleitung zu diesem Rezept ein oder 2 für ein neues Rezept.");
            }while (meinScanner.nextInt() == 2);
            rezeptInfo = getRecipeById(rezeptInfo.substring(rezeptInfo.indexOf(':')+1,rezeptInfo.indexOf(',')));
            System.out.println(formatInstrucitons(rezeptInfo));
        }
    }

    public static String  getRandomRecipe() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(
                HttpRequest
                        .newBuilder(new URI("https://api.spoonacular.com/recipes/random?apiKey=27a37a5299de4ded8fdfb859a5a70433"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        return response.body();
    }

    public static String  getRecipeById(String id) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(
                HttpRequest
                        .newBuilder(new URI("https://api.spoonacular.com/recipes/"+id+"/information?includeNutrition=true&apiKey=27a37a5299de4ded8fdfb859a5a70433"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        return response.body();
    }

    public static String getRecipesByIngredient(String ingredient) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(
                HttpRequest
                        .newBuilder(new URI("https://api.spoonacular.com/recipes/findByIngredients?ingredients="+ ingredient +"&number=5&apiKey=27a37a5299de4ded8fdfb859a5a70433"))
                        .GET()
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        return response.body();
    }

    public static String formatInstrucitons(String rezeptInfo){
        String output = rezeptInfo.substring(rezeptInfo.indexOf("instructions\":\""),rezeptInfo.indexOf("\",\"",rezeptInfo.indexOf("instructions\":\"")))
                .replaceAll("\\\\n","\n")
                .replaceAll("</li><li>","\n")
                .replaceAll("instructions\":\"","")
                .replaceAll("</li></ol>","")
                .replaceAll("<ol><li>","")
                .replaceAll("</p><p>","\n");
        if (output.length()==0){
            return "Es gibt leider keine Anleitung zu diesem Rezept.";
        }else {
            return output;
        }
    }

}
