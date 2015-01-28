import java.io.*;

//quelques primitives d'�criture � l'ecran  ou dans un fichier

public class Ecriture {

    public static void ecrireString(String fileName,String s) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
            out.println(s);
            out.close();
        } catch (IOException e) {
            System.out.println("Couldn't write in the file.");
        }
    }

    public static void clearFile(String fileName)
    {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileName, false));
            out.println("");
            out.close();
        } catch (IOException e) {
            System.out.println("Couldn't clear the file.");
        }
    }
}//class Ecriture

