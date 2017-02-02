import java.util.Scanner;
import java.lang.*;
public class test
{
	public static void main(String[] args)
	{
		//Take input
		System.out.print("Enter a postfix expression: ");
		Scanner express = new Scanner(System.in);
		String exprString = express.nextLine();

		//Turn input into string array
		String[] exprChars = new String[exprString.length()];
		for (int i = 0; i < exprString.length(); i++)
		{
			exprChars[i] = String.valueOf(exprString.charAt(i));
		}

		postfix(exprChars);
	}


	//exmaple input: 12+6+5+ | 53+5*3/ | beef*++ | ab*c+
	//This takes in an expression (number or variable based) in postfix and returns its value
	public static int postfix(String expr[])
	{
		boolean found = false;
		int first, second, result = 0;
		String[] exprnum = new String[expr.length];
		Scanner express = new Scanner(System.in);

		//postfix
		for (int i = 0; i < expr.length; i++)
		{
			//change alpha variables to number value, and directly copy operators
			found = false;
			if (expr[i].chars().allMatch(Character::isLetter))
			{

				if(i == 0)//First one is always a new variable
				{
					System.out.print("Enter for " + expr[i] + ": ");
					exprnum[i] = express.nextLine();
				}
				else
				{
					//System.out.print(i - 1);
					for (int j = i - 1; j >= 0; --j)
					{
						//if a = 1, b = 2 and it's 'a' again, just copy the value from the previous 'a'
						if(expr[i].equals(expr[j]))
						{
							found = true;
							exprnum[i] = exprnum[j]; 
						}
						//System.out.println(i + " " + expr[i] + expr[j]);
					}

					// we are currently on a new variable
					if(found == false)
					{
						System.out.print("Enter for " + expr[i] + ": ");
						exprnum[i] = express.nextLine();
					}
				}
			}
			else
			{
				exprnum[i] = expr[i];
			}

			//start calculations
			switch(exprnum[i])
			{
				case "+": first = Integer.parseInt(exprnum[i-2]);
						  second = Integer.parseInt(exprnum[i-1]);
						  result = first + second;
						  
						  //change operator char to the result
						  exprnum[i] = Integer.toString(result);
						  //push lower indexes up by 2
						  if(i > 3)
						  {
					 	  	exprnum[i-1] = exprnum [i-3];
							exprnum[i-2] = exprnum [i-4]; 
				       	  }
						  break;

				case "-": first = Integer.parseInt(exprnum[i-2]);
						  second = Integer.parseInt(exprnum[i-1]);
						  result = first - second;
						  
						  exprnum[i] = Integer.toString(result);
						  if(i > 3)
						  {
					 	  	exprnum[i-1] = exprnum [i-3];
							exprnum[i-2] = exprnum [i-4]; 
				       	  }
						  break;

				case "*": first = Integer.parseInt(exprnum[i-2]);
						  second = Integer.parseInt(exprnum[i-1]);
						  result = first * second;
						  
						  exprnum[i] = Integer.toString(result);
						  if(i > 3)
						  {
					 	  	exprnum[i-1] = exprnum [i-3];
							exprnum[i-2] = exprnum [i-4]; 
				       	  }
						  break;

				case "/": first = Integer.parseInt(exprnum[i-2]);
						  second = Integer.parseInt(exprnum[i-1]);
						  result = first / second;
						  
						  exprnum[i] = Integer.toString(result);
						  if(i > 3)
						  {
					 	  	exprnum[i-1] = exprnum [i-3];
							exprnum[i-2] = exprnum [i-4]; 
				       	  }
						  break;
			}
		}
		System.out.println ("result: " + result);
		return result;

	}
}