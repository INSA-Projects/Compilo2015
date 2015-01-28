import java.awt.List;
import java.io.InputStream;
import java.util.ArrayList;
/**
 * la classe AutoDoc concrétise la classe abstraite Automate
 * pour l'analyse d'un document reconnaissable par un automate
 * fini
 * @author : Marie-Jo Pédrono
 * date de création : 18 mars 2003
 */
public class AutoDoc extends Automate {
	ArrayList<String> keyWords = new ArrayList<String>();
	boolean etoilelue = false;
	int compteur = 1;
    int e = etatErreur;// à des fins de présentation de la matrice

    //la matrice de transition n° ligne : état, n° colonne : unite
    

  private  final int[][] transition = {
  //autre,doc,fdoc,tit,ftit,date,fdate,para,fpara,ident,entier,pt,virg,etoi  //etat
    {e,    1,  e,   e,   e,   e,   e,    e,   e,    e,   e,     e,  e ,e },//0
    {e,    e,  e,   e,   e,   2,   e,    e,   e,    e,   e,     e,  e ,e },//1
    {e,    e,  e,   e,   e,   e,   e,    e,   e,    e,   3,     e,  e ,e },//2
    {e,    e,  e,   e,   e,   e,   4,    e,   e,    e,   e,     e,  e ,e },//3
    {e,    e,  e,   e,   e,   e,   e,    5,   e,    e,   e,     e,  e ,e  },//4
    {e,    e,  e,   e,   e,   e,   e,    e,   e,    6,   e,     e,  e, 9  },//5
    {e,    e,  e,   e,   e,   e,   e,    e,   8,    6,   e,     7,  7 ,9 },//6
    {e,    e,  e,   e,   e,   e,   e,    e,   8,    6,   e,     e,  e ,9 },//7
    {e,    e,  10,  e,   e,   e,   e,    5,   e,    e,   e,     e,  e ,e }, //8
    {e,    e,  e,   e,   e,   e,   e,    e,   e,    6,   e,     e,  e ,e } //9
  };

/**
* Constructeur AutoDoc pour initialisation
* initialise l’état initial et l’état final 
* @param le flot d'entrée
* 
*/
    public AutoDoc(InputStream flot)
    { 
    	    super(flot);
    	    this.etatInitial = 0;
    	    this.etatFinal = 10;
    }

  /**
   *  Methode qui calcule l'état suivant
   *  @param etat : l'état courant
   *  @param unite : l'unité du token courant
   *  @return l'état suivant
   */
  int getTransition(int etat, int unite)
  {
  	  return transition[etat][unite];  
  }
  
  
  void faireAction(int etat, int unite)
  {
	  Ecriture.ouvrir("resultat.txt");
	  switch (unite)
	  {
	  case Constants.AUTRE:
		  // do nothing
		  break;
		  
	  case Constants.DATE:
		  Ecriture.ecrireString("\n<center>\n<h1>");
		  break;
		  
	  case Constants.DOC:
		  Ecriture.ecrireString("<html>\n<body>\n");
		  break;
		  
	  case Constants.ENTIER:
		  Ecriture.ecrireString(this.token.lexeme);
		  break;
		  
	  case Constants.EOF:
		  
		  break;
	  		
	  case Constants.ETOILE:
		  this.etoilelue = true;
		  break;
		  
	  case Constants.FINDATE:
		  Ecriture.ecrireString("<\\h1><\\center>");
		  break;
		  
	  case Constants.FINDOC:
		  
		  break;
		  
	  case Constants.FINPARA:
		  
		  break;
	  
	  case Constants.FINTITRE:
		  Ecriture.ecrireString("<\\h1><\\center>\n");
		  break;

	  case Constants.IDENT:
		  Ecriture.ecrireString(this.token.lexeme);
		  if (this.etoilelue) {this.keyWords.add(this.token.lexeme);this.etoilelue=false;}
		  break;
		  
	  case Constants.PARA:
		  /*
		  int level = this.compteur;
		  if (level == 1)
		  {
			  Ecriture.ecrireString("<h"+this.compteur+">\n"+this.compteur);
		  }
		  
		  
		  
		  if (compteur > 1) 
		  {
			  Ecriture.ecrireString("<\\h"+(this.level-1)+">\n");
			  Ecriture.ecrireString("<h"+this.level+">");
			  while(level>1)
			  {
				  Ecriture.ecrireString("&nbsp;&nbsp;&nbsp;");
				  level--;
			  }
		  }
		  
		  Ecriture.ecrireString("<h"+this.compteur+"> \n"+this.compteur);
		  this.compteur++;
		  */
		  break;
		  
	  case Constants.POINT:
		  
		  break;
		  
	  case Constants.TITRE:
		  Ecriture.ecrireString("<center><h1>\n");
		  break;
		  
	  case Constants.VIRGULE:
		  
		  break;
		  
		  
	  }
  };
}
