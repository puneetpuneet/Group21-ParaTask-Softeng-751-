package com.uni.group21;

import java.util.Scanner;

public class MainClass {
	private static int result;
	private static Scanner reader1;
	public static void main(String[] args) 
	{
		getQuestion();
	}
	
	private static void askQuestionAgain() {
		System.out.println("\n\nDo you want to try another algorithm (y/n)?");
		
		result = getUserInput("repeat");
		if(result == 1) {
			getQuestion();
		}else if(result == 0) {
			System.out.println("\nThanks for trying !! Byee !!");
			reader1.close();
		}else {
			System.out.println("\nPlease enter valid input !!");
			askQuestionAgain();
		}
	}

	private static void getQuestion() 
	{
		System.out.println("Which Algorithm you want to use to calculate the size of (C:\\\\) Drive ?\n Please select one of the below options:\n");
		System.out.println("1.   SingleThreadPool");
		System.out.println("2.   FixedThreadPool(25 threads)");
		System.out.println("3.   CacheThreadPool");
		System.out.println("4.   Fork and Join");
		System.out.println("5.   ParaTask\n");
		
		result = getUserInput("algo");
		executeSelectedAlgo(result);
		askQuestionAgain();
	}
	
	private static void executeSelectedAlgo(int input) {
		switch(input){
			case 1: 
				SingleThread obj=new SingleThread();
				obj.init();
				break;
			case 2:
				ExecutorsImpl ExecObj = new ExecutorsImpl();
				ExecObj.init();
				break;
			case 3:
				ExecutorsImplCache cacheObj = new ExecutorsImplCache();
				cacheObj.init();
				break;
			case 4:
				ForkandJoin obj1=new ForkandJoin();
				obj1.init();
				break;
			case 5:
				ParaTaskImp obj2=new ParaTaskImp();
				obj2.init();
				break;
			default:	
				System.out.println("Please enter valid input.");
		}
	}
	
	private static int getUserInput(String input) {
		reader1=new Scanner(System.in);
		int userInput = -1;
		if(input.equalsIgnoreCase("algo")) {
			userInput =reader1.nextInt();
		}else if(input.equalsIgnoreCase("repeat")){
			if(reader1.hasNext()) {
			String result =reader1.nextLine().trim();
				if(result.equalsIgnoreCase("y"))
				{
					userInput = 1;
				}else if(result.equalsIgnoreCase("n")) {
					userInput = 0;
				}
			}
		}
		return userInput;
	}
}
