/* Nhat Doan 217523684
 * CSC 130 - Professor Cooks
 * Topic: Implementing a algorithms to calculate a postfix expression with a hard-code input.
 * 
 * Using stack and queue as data structures.
 * 
 * 
 *  
 * */
public class Evaluator {
	//main function
	public static void main(String[] args) {
	
		System.out.printf(About());
		System.out.printf("The result of calculating is:"+ evaluate());
	}
	//evaluator function which use hard code input from a queue and evaluate the expression through stack
	public static double evaluate() {
		// input data
		Queue<String> Input = new Queue<String>();
		Input.enqueue("8");
		Input.enqueue("2");
		Input.enqueue("/");
		Input.enqueue("3");
		Input.enqueue("*");
		Input.enqueue("6");
		Input.enqueue("-");
		// create a stack to hold values and operation
		Stack<Double> PostfixStack= new Stack<Double>();
		while(!Input.isEmpty())
		{
			String str;
			str=Input.dequeue();
			switch (str) {
			case "0":
				PostfixStack.push(0.0);
				break;
			case "1":
				PostfixStack.push(1.0);
				break;
			case "2":
				PostfixStack.push(2.0);
				break;
			case "3":
				PostfixStack.push(3.0);
				break;
			case "4":
				PostfixStack.push(4.0);
				break;
			case "5":
				PostfixStack.push(5.0);
				break;
			case "6":
				PostfixStack.push(6.0);
				break;
			case "7":
				PostfixStack.push(7.0);
				break;
			case "8":
				PostfixStack.push(8.0);
				break;
			case "9":
				PostfixStack.push(9.0);
				break;
            case "+":
            case "-":
            case "*":
            case "/":
                double right = PostfixStack.pop();
                double left = PostfixStack.pop();
                double value = 0;
                switch(str) {
                    case "+":
                        value = left + right;
                        break;
                    case "-":
                        value = left - right;
                        break;
                    case "*":
                        value = left * right;
                        break;
                    case "/":
                        value = left / right;
                        break;
                    default:
                        break;
                }
                PostfixStack.push(value);
			}
		}
		return PostfixStack.top();
	}
	// function returns information of author. 
	public static String About() {
		return "-----Nhat Doan-217523684 ------\n"
				+ "CSC130 - Prof Cook \n"
				+ "Postfix Evaluator.\n";
	}
}
