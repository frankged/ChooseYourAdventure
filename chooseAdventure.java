import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> strings = new ArrayList<>();

    String bookTitle = "Escape from Tenopia";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Welcome to" + bookTitle +": Choose Your Own Adventure";
        }
        // } else if (url.getPath().equals("/add")) {
        //     String[] parameters = url.getQuery().split("=");
        //     strings.add(parameters[1]);
        //     return "'" + parameters[1] + "' was added";
        // } else if (url.getPath().equals("/search")) {
        //     String[] parameters = url.getQuery().split("=");
        //     ArrayList<String> matches = new ArrayList<>();
        // }
        //     else if (url.getPath().equals("/chicken")) {
        //     return "you are sus lmao";
        // }
        else {
            String [] pathArr = url.getPath().split("/");
            String pageNumberString = pathArr[pathArr.length - 1];
            // try {
            //     int pageNumber = Integer.parseInt(pageNumberString);
            // }
            // catch (Exception e) {
            //     return "Input Page Number you wish to go to at end of URL.";
            // }
            // System.out.println("Path: " + url.getPath());
            try {
                Scanner sc = new Scanner(new File("./" + bookTitle + "/" + pageNumberString + ".txt"));
                String str = "";
                while(sc.hasNextLine()){
                    str += sc.nextLine();                     
                }
                return str;
            }
            catch (Exception e) {
                return "Enter one of the page numbers listed.";
            }
            // if (url.getPath().contains("/add")) {
            //     String[] parameters = url.getQuery().split("=");
            //     if (parameters[0].equals("count")) {
            //         return null;
            //     }
            // }
            // return "404 Not Found!";
        }
    }
}

class chooseAdventure {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }

}
