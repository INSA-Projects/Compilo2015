import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

public class Function extends Ident
{
	private int cptParam = 0;

	
	// Paramètres de la fonction
	private ArrayList<Param> parameters = new ArrayList<Param>();
	
	// Type passés en paramètres lors d'un appel à la fonction
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
	
	/* ---------- méthides appelées lors de la déclaration de la fonction ---------- */
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
	
	// calcule l'offset (dans la pile de la fonction) de chaque paramètre  
	public void calculerOffsetsDesParametres() 
	{
		for (Param param : parameters){
			param.setOffset(4 + this.tailleParams() - 2 * param.getValue()); // formule à vérifier
		}
	}
	
// retourne la taille du bloc à reserver pour les paramètres dans dans la pile de la fonction
	public int tailleParams(){
		return parameters.size()*2;
	}
	
	
	/* ----------- méthodes appelées lors de l'appel de la fonction ---------- */
	
	// ajoute le type du paramètre à controler lors d'un appel de la fonction
	public void addParamToControl(Type t) {
		parametreATester.push(t);
	}

	
	// Effectue le contrôle de type des paramètres lors de l'appel de la fonction
	public void controlTypeParam() {
		
		// Si différence de taille entre paramètres attendu et paramètres lus : ERROR
		if (this.parameters.size() != this.parametreATester.size()) {
			System.out.println("Erreur : nombre de paramètres attendu : "+this.parameters.size()+" Nombre de paramètres lus : "+this.parametreATester.size()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
			// Abbiamo vidato tutte le due pile
			this.parameters.clear();
			this.parametreATester.clear();
		}
		// Sinon contrôle de type
		else {
			for(int i = parameters.size()-1 ; i>=0 ; i--) {
				Type typeParamAttendu = this.parameters.get(i).getType();
				Type typeParamATester = this.parametreATester.pop();
				
				if (typeParamAttendu !=  typeParamATester) {
					System.out.println("Erreur : Mauvais type de paramètre, attendu "+typeParamAttendu.toString()+" Type du paramètre donné : "+typeParamATester.toString()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
				}
			}
		}
		
	}






	
	
}

