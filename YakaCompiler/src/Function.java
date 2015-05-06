import java.util.Stack;

public class Function extends Ident
{
	private int cptParam = 0;

	
	// Param�tres de la fonction
	private Stack<Param> parameters = new Stack<Param>();
	
	// Type pass�s en param�tres lors d'un appel � la fonction
	private Stack<Type> parametreATester;

	

	/**
	 * Constructor
	 * @param identLu
	 * @param typeDeRetour
	 */
	public Function(String identLu, Type typeDeRetour) 
	{
		super(typeDeRetour, 0);
		
		Yaka.tabIdent.putFonction(identLu, this);
	}
	
	/**
	 * 	add parameter to function
	 * @param param
	 */
	public void addParametre(Param param) 
	{
		param.setOffset(cptParam++); // temporaire
		this.parameters.push(param);
	}
	
	public void calculerOffsetsDesParametres() 
	{
		// TODO Auto-generated method stub
	}
	

	public int tailleParams(){
		return parameters.size();
	}
	
	
	public void addParamToControl(Type t) {
		parametreATester.push(t);
	}

	
	// Effectue le contr�le de type des param�tres de la fonction
	public void controlTypeParam() {
		System.out.println("parameters : "+this.parameters.toString()+"\nparametereATester :"+this.parametreATester.toString());
		// Si diff�rence de taille entre param�tres attendu et param�tres lus : ERROR
		if (this.parameters.size() != this.parametreATester.size()) {
			System.out.println("Erreur : nombre de param�tres attendu : "+this.parameters.size()+" Nombre de param�tres lus : "+this.parametreATester.size()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
			// Abbiamo vidato tutte le due pile
			this.parameters.clear();
			this.parametreATester.clear();
		}
		// Sinon contr�le de type
		else {
			while (!this.parameters.empty()) {
				Type typeParamAttendu = this.parameters.pop().getType();
				Type typeParamATester = this.parametreATester.pop();
				
				if (typeParamAttendu.ordinal() != typeParamATester.ordinal()) {
					System.out.println("Erreur : Mauvais type de param�tre, attendu "+typeParamAttendu.toString()+" Type du param�tre donn� : "+typeParamATester.toString()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
				}
			}
		}
		
	}






	
	
}

