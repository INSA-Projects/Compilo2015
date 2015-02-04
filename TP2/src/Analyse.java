import java.io.*;
public abstract class Analyse implements Constants  {

  public static void main(String args[]) {
   
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
       
      Gram analyseur = new Gram(flot);
      analyseur.document();
      System.out.println("analyse reussie!");
    } catch (AnalyseException e) {
      String msg = e.getMessage();
      System.out.println("Erreur de syntaxe : \n"+msg);
    }
  }

 
  TokenManager  lex;
  public Token token;


  public Analyse(InputStream flot) {

    lex = new TokenManager(flot);
    token =  lex.getToken();
  }
    //cette méthode valide le token courant et lit le  suivant
  public  void reconnaitre(int unite) throws AnalyseException{
      if (token.unite==unite){
	  token =lex.getToken();
      }else {
	  throw new AnalyseException(
              "erreur "+images[unite]+ "attendu"+
	      "\n au lieu de :"+token);
      }
  }
    // cette méthode fait avancer au token suivant
    public void avance(){
	token =  lex.getToken();
    }
   
}//Analyse

 class AnalyseException extends Exception{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AnalyseException (String message){
	super(message);
    }
}
