import java.util.Stack;

public class Function extends Ident
{
	private int cptParam = 0;
	private String nom;

	
	// Param�tres de la fonction
	private Stack<Param> parameters = new Stack<Param>();
	
	// Type pass�s en param�tres lors d'un appel � la fonction
	private Stack<Type> parametreATester = new Stack<Type>();

	

	/**
	 * Constructor
	 * @param identLu
	 * @param typeDeRetour
	 */
	public Function(String identLu, Type typeDeRetour) 
	{
		super(typeDeRetour, 0);
		this.nom=identLu;
		
		Yaka.tabIdent.putFonction(identLu, this);
	}
	
	/**
	 * 	add parameter to function
	 * @param param
	 */
	public Function addParametre(Param param) 
	{
		param.setOffset(cptParam++); // temporaire
		this.parameters.push(param);
		System.out.println("addParametre"+this.parameters.toString());
		return this;
	}
	
	// aide au calcul des offset
	public int sizeParam(){
		return this.cptParam*2;
	}
	
	//on fait un for each sur la pile, pas sur que �a marche
	public Function calculerOffsetsDesParametres() 
	{
		System.out.println(this.toString());
		for(Param p:parameters){
			p.setValue(sizeParam()-(2*p.getValue()));
		}
		System.out.println("calcul offset de "+this.nom+this.parameters.toString());
		return this;
	}
	
	//getter quelconque
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

