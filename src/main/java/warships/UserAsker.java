package warships;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * UserAsker is a singleton
 * Was Created due to make requested tests
 */
public class UserAsker {

    public Scanner scanner = new Scanner(System.in);
    public PrintStream out = new PrintStream(System.out);
    private static UserAsker instance = new UserAsker();

    private UserAsker(){
    }

    public String ask(String message){
        out.println(message);
        return scanner.next();
    }

    public void init(Scanner sc, PrintStream out){
        this.scanner = sc;
        this.out = out;
    }

    public static UserAsker getInstance(){
        return instance;
    }
}
