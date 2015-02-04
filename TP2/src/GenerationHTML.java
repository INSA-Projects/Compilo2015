import java.util.ArrayList;


public class GenerationHTML
{
	public static boolean asteriskDetected = false;
	// contains keywords
    public static ArrayList<String> keyWords = new ArrayList<String>();
    // levels-managing of the document (we suppose the document does not have more than 10 imbricated paragraphs)
    public static int[] levels = new int[10];
    public static int levelIndex = 0;
    // true when you've just read an asterisk (so keyword is following), false otherwise
    
	
	public static void generate (int unite, String lexeme)
	{	
        String file = "result.html";
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
                Ecriture.ecrireString(file,lexeme);
                break;

            case Constants.EOF:
                // do nothing
                break;

            case Constants.ETOILE:
                asteriskDetected = true;
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
                Ecriture.ecrireString(file, lexeme);
                if (asteriskDetected)
                {
                    // add keyword to the list of keywords
                    keyWords.add(lexeme);
                    asteriskDetected=false;
                }
                break;

            case Constants.PARA:
                levels[++levelIndex]++;
                Ecriture.ecrireString(file,"\n<h"+(levelIndex+1)+">");

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
                Ecriture.ecrireString(file, lexeme);
                break;

            case Constants.TITRE:
                Ecriture.ecrireString(file,"<center><h1>");
                break;

            case Constants.VIRGULE:
                Ecriture.ecrireString(file, lexeme);
                break;
                
            default:
            	Ecriture.ecrireString(file, "\n -- Error ! -- \n");
            	
        }

	}
}
