/**
 * TP1 Compilation 3e annee
 * @author Bonnucci Eric
 * @author Esmieu Valentin
 * @date 28/01/2015.
 */
import java.io.InputStream;
import java.util.ArrayList;
public class AutoDocHtml extends Automate
{
    // resulting-file name
    String fileName = "result.html";
    // error state
    int e = etatErreur;
    // contains keywords
    ArrayList<String> keyWords = new ArrayList<String>();
    // levels-managing of the document (we suppose the document does not have more than 10 imbricated paragraphs)
    int[] levels = new int[10];
    int levelIndex = 0;
    // true when you've just read an asterisk (so keyword is following), false otherwise
    boolean asteriskDetected = false;

    //la matrice de transition
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
     * initialise l��tat initial et l��tat final
     * @param flot : le flot d'entr�e
     *
     */
    public AutoDocHtml(InputStream flot)
    {
        super(flot);
        this.etatInitial = 0;
        this.etatFinal = 10;
        Ecriture.clearFile(fileName);
    }

    /**
     *  Methode qui calcule l'�tat suivant
     *  @param etat : l'�tat courant
     *  @param unite : l'unit� du token courant
     *  @return l'�tat suivant
     */
    int getTransition(int etat, int unite)
    {
        return transition[etat][unite];
    }


    void faireAction(int etat, int unite)
    {
        String file = this.fileName;
        switch (unite)
        {
            case Constants.AUTRE:
                // do nothing
                break;

            case Constants.DATE:
                Ecriture.ecrireString(file,"<center><h1>");
                break;

            case Constants.DOC:
                Ecriture.ecrireString(file,"<html><body>");
                break;

            case Constants.ENTIER:
                Ecriture.ecrireString(file,this.token.lexeme);
                break;

            case Constants.EOF:
                // do nothing
                break;

            case Constants.ETOILE:
                this.asteriskDetected = true;
                break;

            case Constants.FINDATE:
                Ecriture.ecrireString(file,"</h1></center>");
                break;

            case Constants.FINDOC:
                Ecriture.ecrireString(file,"Keywords : ");
                for (int i=0;i<keyWords.size();i++)
                {
                    Ecriture.ecrireString(file,keyWords.get(i));
                }
                Ecriture.ecrireString(file,"</body></html>");
                break;

            case Constants.FINPARA:
                Ecriture.ecrireString(file,"</h"+levelIndex+">");
                for (int i=levelIndex+1;i<10;i++)
                {
                    levels[i]=0;
                }
                levelIndex--;
                break;

            case Constants.FINTITRE:
                Ecriture.ecrireString(file,"</h1></center>");
                break;

            case Constants.IDENT:
                Ecriture.ecrireString(file,this.token.lexeme);
                if (this.asteriskDetected)
                {
                    // add keyword to the list of keywords
                    this.keyWords.add(this.token.lexeme);
                    this.asteriskDetected=false;
                }
                break;

            case Constants.PARA:
                levels[++levelIndex]++;
                Ecriture.ecrireString(file,"\n<h"+levelIndex+1+">");

                String numerotation = "";
                int lvl=1;
                numerotation += " "+levels[lvl]+".";
                while (lvl<levelIndex)
                {
                    numerotation = "&nbsp;&nbsp;&nbsp;" + numerotation;
                    numerotation += levels[lvl] + ".";
                    lvl++;
                }
                Ecriture.ecrireString(file,numerotation);
                break;

            case Constants.POINT:
                Ecriture.ecrireString(file,this.token.lexeme);
                break;

            case Constants.TITRE:
                Ecriture.ecrireString(file,"<center><h1>");
                break;

            case Constants.VIRGULE:
                Ecriture.ecrireString(file,this.token.lexeme);
                break;
        }

    };
}

