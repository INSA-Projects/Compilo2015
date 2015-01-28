
public class Token {

  /**
   * An integer that describes the kind of this token.  This numbering
   * system is determined by JavaCCParser, and a table of these numbers is
   * stored in the file ...Constants.java.
   */
    public int unite; //ou kind

  /**
   * ligne indique le n° de ligne du token
   */
    public int ligne;

  /**
   * The string image of the token.
   */
    public String lexeme; // image 

  /**
   * Returns the image.
   */
  public String toString()
  { 
     return "lexeme = "+lexeme+ "- unite=" +unite + "- ligne n°"+ligne;
  }

}
