import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

public class Function extends Ident
{
	private int cptParam = 0;

	
	// Param�tres de la fonction
	private ArrayList<Param> parameters = new ArrayList<Param>();
	
	// Type pass�s en param�tres lors d'un appel � la fonction
	private Stack<Type> parametreATester = new Stack<Type>();

	

	/**
	 * Constructor
	 * @param identLu
	 * @param typeDeRetour
	 */
	public Function(Type typeDeRetour) 
	{
		super(typeDeRetour, 0);
	}
	
	/* ---------- m�thides appel�es lors de la d�claration de la fonction ---------- */
	/**
	 * 	add parameter to function
	 * @param param
	 */
	public void addParametre(Param param) 
	{
		cptParam++;
		param.setOffset(cptParam); 
		// temporaire
		this.parameters.add(param);
	}
	
	// calcule l'offset (dans la pile de la fonction) de chaque param�tre  
	public void calculerOffsetsDesParametres() 
	{
		for (Param param : parameters){
			param.setOffset(4 + this.tailleParams() - 2 * param.getValue()); // formule � v�rifier
		}
	}
	
// retourne la taille du bloc � reserver pour les param�tres dans dans la pile de la fonction
	public int tailleParams(){
		return parameters.size()*2;
	}
	
	
	/* ----------- m�thodes appel�es lors de l'appel de la fonction ---------- */
	
	// ajoute le type du param�tre � controler lors d'un appel de la fonction
	public void addParamToControl(Type t) {
		parametreATester.push(t);
	}

	
	// Effectue le contr�le de type des param�tres lors de l'appel de la fonction
	public void controlTypeParam() {
		
		// Si diff�rence de taille entre param�tres attendu et param�tres lus : ERROR
		if (this.parameters.size() != this.parametreATester.size()) {
			System.out.println("Erreur : nombre de param�tres attendu : "+this.parameters.size()+" Nombre de param�tres lus : "+this.parametreATester.size()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
			// Abbiamo vidato tutte le due pile
			this.parameters.clear();
			this.parametreATester.clear();
		}
		// Sinon contr�le de type
		else {
			for(int i = parameters.size()-1 ; i>=0 ; i--) {
				Type typeParamAttendu = this.parameters.get(i).getType();
				Type typeParamATester = this.parametreATester.pop();
				
				if (typeParamAttendu !=  typeParamATester) {
					System.out.println("Erreur : Mauvais type de param�tre, attendu "+typeParamAttendu.toString()+" Type du param�tre donn� : "+typeParamATester.toString()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
				}
			}
		}
		
	}






	
	
}

