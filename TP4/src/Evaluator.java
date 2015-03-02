import java.util.Stack;

/**
 * 
 * @author vesmieu
 *
 */
public class Evaluator 
{
	private Stack<Integer> valuesStack;
	private Stack<Character> operatorsStack;
	
	public Evaluator()
	{
		this.valuesStack = new Stack<Integer>();
		this.operatorsStack = new Stack<Character>();
	}

	public void evaluate() 
	{
		int calcul = valuesStack.pop();
		
		switch (operatorsStack.pop())
		{
			case '+':
				calcul += valuesStack.pop();
				valuesStack.push(calcul);
				break;
				
			case '-':
				calcul -= valuesStack.pop();
				valuesStack.push(-calcul);
				break;
				
			case '*':
				calcul *= valuesStack.pop();
				valuesStack.push(calcul);
				break;
			
			case '/':
				int numerator = valuesStack.pop();
				valuesStack.push(numerator/calcul);
				break;
				
			case '~':
				valuesStack.push(-calcul);
				break;
			
			default:
				System.out.println("Operator not recognized");
				break;
		}
	}

	public void addValue(int entierLu) 
	{
		valuesStack.push(entierLu);
	}
	
	public void addOperator(char c) 
	{
		operatorsStack.push(c);
	}
	
	public void printResult()
	{
		System.out.println("Result : "+valuesStack.pop());
	}

	

	
	
		
}
