import java.io.InputStream;


public class Gram extends Analyse implements Constants
{

	public Gram(InputStream flot) 
	{
		super(flot);
		// TODO Auto-generated constructor stub
	}
	
	public void document() throws AnalyseException 
	{
		GenerationHTML.generate(DOC, super.token.lexeme);
		super.reconnaitre(DOC);
		
		titre();
		dateplus();
		paragraphe();
		paraplus();
		GenerationHTML.generate(FINDOC, super.token.lexeme);
		super.reconnaitre(FINDOC);
		
	}
	
	private void dateplus() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case DATE:
				date();
				break;
			case PARA:
				epsilon();
				break;
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; DATE ou PARA attendus.");
		}
	}

	private void date() throws AnalyseException 
	{
		GenerationHTML.generate(DATE, super.token.lexeme);
		super.reconnaitre(DATE);
		
		GenerationHTML.generate(ENTIER, super.token.lexeme);
		super.reconnaitre(ENTIER);
		
		GenerationHTML.generate(FINDATE, super.token.lexeme);
		super.reconnaitre(FINDATE);
		
	}

	private void paraplus() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case PARA:
				paragraphe();
				paraplus();
				break;
			case FINDOC:
				epsilon();
				break;
			case FINPARA:
				epsilon();
				break;
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; PARA, FINDOC ou FINPARA attendus.");
		}
	}

	private void paragraphe() throws AnalyseException 
	{
		GenerationHTML.generate(PARA, super.token.lexeme);
		super.reconnaitre(PARA);
		
		motcle();
		motcleplus();
		paraplus();
		
		GenerationHTML.generate(FINPARA, super.token.lexeme);
		super.reconnaitre(FINPARA);
		
	}

	private void motcleplus() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case ETOILE:
				motcle();
				motcleplus();
				break;
			case IDENT:
				motcle();
				motcleplus();
				break;
			case PARA:
				epsilon();
				break;
			case FINPARA:
				epsilon();
				break;
				
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; ETOILE, IDENT, PARA ou FINPARA attendus.");
		}
	}

	private void motcle() throws AnalyseException 
	{
		etoileplus();
		GenerationHTML.generate(IDENT, super.token.lexeme);
		super.reconnaitre(IDENT);
		
		ponct();
	}

	private void etoileplus() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case ETOILE:
				GenerationHTML.generate(ETOILE, super.token.lexeme);
				super.reconnaitre(ETOILE);
				
				break;
			case IDENT:
				epsilon();
				break;
				
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; ETOILE ou IDENT attendus.");
		}
	}

	private void ponct() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case VIRGULE:
				GenerationHTML.generate(VIRGULE, super.token.lexeme);
				super.reconnaitre(VIRGULE);
				
				break;
			case POINT:
				GenerationHTML.generate(POINT, super.token.lexeme);
				super.reconnaitre(POINT);
				
				break;
			case ETOILE:
				epsilon();
				break;
			case IDENT:
				epsilon();
				break;
			case PARA:
				epsilon();
				break;
			case FINPARA:
				epsilon();
				break;
				
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; VIRGULE, POINT, ETOILE, IDENT, PARA ou FINPARA attendus.");
		}
	}

	private void titre() throws AnalyseException 
	{
		GenerationHTML.generate(TITRE, super.token.lexeme);
		super.reconnaitre(TITRE);

		GenerationHTML.generate(IDENT, super.token.lexeme);
		super.reconnaitre(IDENT);
		
		identplus();
		GenerationHTML.generate(FINTITRE, super.token.lexeme);
		super.reconnaitre(FINTITRE);
		
	}

	private void identplus() throws AnalyseException 
	{
		switch(super.token.unite)
		{
			case IDENT:
				GenerationHTML.generate(IDENT, super.token.lexeme);
				super.reconnaitre(IDENT);
				
				identplus();
				break;
			case FINTITRE:
				epsilon();
				break;
				
			default:
				System.out.println("Erreur d'analyse sur le token "+super.token.unite+" ; IDENT ou FINTITRE attendus.");
		}
	}
	
	private void epsilon() 
	{
		
	}

}
