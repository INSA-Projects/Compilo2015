/**
* @title : TP3 compilation 3rd year
* @author : Bonucci Eric
* @author : Esmieu Valentin
* @date : 11/02/2015
**/
import java.util.ArrayList;

public class Generation 
{
	// output file
	static final String file = "result.html";
	
	// constants
	public static final int AUTRE   =0;
	public static final int DOC     =1;
	public static final int FINDOC  =2;
	public static final int TITRE   =3;
	public static final int FINTITRE=4;
	public static final int DATE    =5;
	public static final int FINDATE =6;
	public static final int PARA    =7;
	public static final int FINPARA =8;
	public static final int IDENT   =9;
	public static final int ENTIER  =10;
	public static final int POINT   =11;
	public static final int VIRGULE =12;
	public static final int ETOILE  =13;
	public static final int EOF     =14;
    
    // true when asterisk is read (keyword has to be added to the list)
	public static boolean asteriskDetected = false;
	// contains keywords
    public static ArrayList<String> keyWords = new ArrayList<String>();
    // levels-managing of the document (we suppose the document does not have more than 10 imbricated paragraphs)
    public static int[] levels = new int[10];
    public static int levelIndex = 0;
    // true when you've just read an asterisk (so keyword is following), false otherwise
    
    public static void clearFile()
    {
    	Writer.clearFile(file);
    }
	
	public static void generate (int unite, String lexeme)
	{	
        switch (unite)
        {
            case AUTRE:
                // do nothing
                break;

            case DATE:
                Writer.writeInFile(file,"<center><h1>");
                break;

            case DOC:
                Writer.writeInFile(file,"<html><body>");
                break;

            case ENTIER:
                Writer.writeInFile(file,lexeme);
                break;

            case EOF:
                // do nothing
                break;

            case ETOILE:
                asteriskDetected = true;
                break;

            case FINDATE:
                Writer.writeInFile(file,"</h1></center>");
                break;

            case FINDOC:
                Writer.writeInFile(file,"Keywords : ");
                for (int i=0;i<keyWords.size();i++)
                {
                    Writer.writeInFile(file,keyWords.get(i));
                }
                Writer.writeInFile(file,"</body></html>");
                break;

            case FINPARA:
                Writer.writeInFile(file,"</h"+levelIndex+">");
                for (int i=levelIndex+1;i<10;i++)
                {
                    levels[i]=0;
                }
                levelIndex--;
                break;

            case FINTITRE:
                Writer.writeInFile(file,"</h1></center>");
                break;

            case IDENT:
                Writer.writeInFile(file, lexeme);
                if (asteriskDetected)
                {
                    // add keyword to the list of keywords
                    keyWords.add(lexeme);
                    asteriskDetected=false;
                }
                break;

            case PARA:
                levels[++levelIndex]++;
                Writer.writeInFile(file,"\n<h"+(levelIndex+1)+">");

                String numerotation = "";
                int lvl=1;
                numerotation += " "+levels[lvl]+".";
                while (lvl<levelIndex)
                {
                    numerotation = "&nbsp;&nbsp;&nbsp;" + numerotation;
                    numerotation += levels[lvl] + ".";
                    lvl++;
                }
                Writer.writeInFile(file,numerotation);
                break;

            case POINT:
                Writer.writeInFile(file, lexeme);
                break;

            case TITRE:
                Writer.writeInFile(file,"<center><h1>");
                break;

            case VIRGULE:
                Writer.writeInFile(file, lexeme);
                break;
                
            default:
            	Writer.writeInFile(file, "\n -- Error ! -- \n");	
        }

	}
}
