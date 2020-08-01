import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HTMLFetcher {

    public static void main(String[] args) {    
        
        // variables
        String DOMAIN_NAME = "github.com/Syritx?tab=repositories";
        String html = fetchHTML("https://"+DOMAIN_NAME); // getting the html

        char[] domainLetters = new char[DOMAIN_NAME.length()];

        // replacing the '/' symbol with a '-'
        for (int i = 0; i < domainLetters.length; i++) {
            if (DOMAIN_NAME.charAt(i) != '/') domainLetters[i] = DOMAIN_NAME.charAt(i);
            else domainLetters[i] = '-';
        }

        // creating the file name for the html file
        String file_name = "";
        for (char letter : domainLetters){
            file_name += Character.toUpperCase(letter);
        }

        // creating the file
        File file = new File((file_name)+".html");
        System.out.println(file.getAbsolutePath());

        try {
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.write(html);
            writer.flush();
            writer.close();

        }catch(IOException e) { e.printStackTrace(); }
    }

    // getting the html
    static String fetchHTML(String url) {
        String htmlContent = null;

        try {
            URLConnection connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            htmlContent = scanner.next();
            scanner.close();
        }catch (Exception e) {}

        return htmlContent;
    }
}