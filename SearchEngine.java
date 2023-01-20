import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    ArrayList<String> str = new ArrayList<String>();
    ArrayList<String> temp = new ArrayList<String>();
    StringBuilder result = new StringBuilder();
    StringBuilder rslt = new StringBuilder();
    int index,len  = 0;
    String mid = " ";

    public String handleRequest(URI url) {
        if(url.getPath().equals("/")) {
            return result.toString();
        }
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                result.delete(0, result.length());
                for (int i = 1; i < parameters.length; i++) {
                    str.add(parameters[i]);
                }
                for (int i = 0; i < str.size(); i++) {
                    result.append(str.get(i));
                    result.append(" ");
                }
                return result.toString();
            }
            else {
                return "404 Not Found!";
            }
        }
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                rslt.delete(0, rslt.length());
                for (int i = 0; i < str.size(); i++) {
                    if (str.get(i).contains(parameters[1])) {
                        rslt.append(str.get(i));
                        rslt.append(" ");
                    }
                }
                if (rslt.length() == 0) {
                    return "Sorry; not found :(";
                }
                else {
                    return rslt.toString();
                }
            }
            else{
                return "404 Not Found!";
            }
        }
        else {
            return "404 Not Found!";
        }
            
        }
    }

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
