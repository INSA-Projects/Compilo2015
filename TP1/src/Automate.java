import java.io.*;
/**
 * la classe Automate mod�lise un automate abstrait
 * @author : Marie-Jo P�drono
 * date de cr�ation : 18 mars 2003
 */
 abstract class Automate {

    TokenManager lex;// l'analyseur lexical
    public  Token token;// le token courant

   int etatInitial ; //�tats sp�cifiques de l'automate
   int etatFinal ;
   int etatErreur = -1;

/**
 * Constructeur Automate pour initialisation
 * appel le constructeur d'analyse lexicale
 *  @param flot d'entr�e
 * 
 */
   Automate (InputStream flot){
       lex=new TokenManager(flot);
   }

 
  /**
   *  Methode qui fait tourner l'automate
   *  
   */
  void moteur() throws AnalyseException{
      int etat=etatInitial;
      while ( etat!=etatFinal && etat!=etatErreur ){
          token =  lex.getToken();
          faireAction(etat, token.unite);
          etat= getTransition(etat, token.unite);
      }
      if (etat==etatErreur){
   throw new AnalyseException( "erreur sur le token " + token);
      }
  }
              
 /**
   *  Methode astraite qui calcule l'�tat suivant
   *  @param etat : l'�tat courant
   *  @param unite : l'unit� du token courant
   *  @return l'�tat suivant
   */
 
   abstract int getTransition(int etat, int unite);
   
   abstract void faireAction(int etat, int unite);

 }//Automate
