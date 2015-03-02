import java.util.Stack;

/**
 * 
 * @author vesmieu
 *
 */
public class YVMGenerator 
{
	private Stack<Character> operatorsStack;
	
	public YVMGenerator()
	{
		this.operatorsStack = new Stack<Character>();
	}

	public void evaluate() 
	{
		switch (operatorsStack.pop())
		{
			case '+':
				System.out.println("iadd");
				break;
				
			case '-':
				System.out.println("isub");
				break;
				
			case '*':
				System.out.println("imul");
				break;
			
			case '/':
				break;
				
			case '~':
				break;
			
			default:
				System.out.println("Operator not recognized");
				break;
		}
	}

	public void addValue(int entierLu) 
	{
		System.out.println("iconst "+entierLu);
	}
	
	public void addVariable(String identLu) 
	{
		System.out.println("iload "+identLu);
	}
	
	public void addOperator(char c) 
	{
		operatorsStack.push(c);
	}
	

}
