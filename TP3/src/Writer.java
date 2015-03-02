import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer 
{
	    public static void writeInFile(String fileName,String s)
	    {
	        try 
	        {
	            PrintWriter out = new PrintWriter(new FileWriter(fileName, true));
	            out.println(s);
	            out.close();
	        }
	        catch (IOException e) 
	        {
	            System.out.println("Couldn't write in the file.");
	        }
	    }

	    public static void clearFile(String fileName)
	    {
	        try 
	        {
	            PrintWriter out = new PrintWriter(new FileWriter(fileName, false));
	            out.println("");
	            out.close();
	        } 
	        catch (IOException e) 
	        {
	            System.out.println("Couldn't clear the file.");
	        }
	    }
}
