import java.io.*;
public class Analyse {
  public static void main(String args[]) {
    AutoDoc analyseur;
    InputStream flot;
    if (args.length==1) {
      System.out.print(args[0] + ": ");
      try {
        flot  = new FileInputStream(args[0]);
      } catch (FileNotFoundException e) {
        System.out.println("Fichier introuvable.");
        return;
      }
    } else if (args.length==0) {
      System.out.println("Lecture sur l'entree standard...");
      flot = System.in;
    } else {
      System.out.println("Usage: java Analyse [fichier]");
      return;
    }
    try {
      analyseur = new AutoDoc(flot);
      analyseur.moteur();
      System.out.println("analyse reussie!");
    } catch (AnalyseException e) {
      String msg = e.getMessage();
      System.out.println("Erreur de syntaxe : \n"+msg);
    }//catch
  }//main
}//Analyse



 class AnalyseException extends Exception{
     AnalyseException (String message){
	super(message);
    }
}
