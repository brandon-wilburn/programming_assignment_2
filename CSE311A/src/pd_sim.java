//import CSE311A.ArgsProcessor;
import java.util.Scanner;

public class pd_sim {
	
	public static void main(String[] args) {
//		System.out.println("Does this even work?");
		Scanner scan = new Scanner(System.in);
//		ArgsProcessor ap = new ArgsProcessor(args);
		
//		Variables
		double iTFTR = .25;
		double iGR = .25;
		double iACR = .25;
		double iADR = .25;
		int players = 100;
		
//		
////			Games
//		int numGames = players / 2;
//		System.out.println("Number of Rounds?");
//		int m = scan.nextInt();
		int m = 5;
		int numRounds = m;
//		
////			Generations
//		System.out.println("Number of Generations?");
//		int k = scan.nextInt();
		int k = 20;
		int numGenerations = k;
		
//			Cutoff percents
		double p = .10;
		double numCutoff = p;
//		
//		System.out.println("Num players: "+players+". Players per type: "+playersPerType);
		
		playGames(setupPlayers(players, iTFTR, iGR, iACR, iADR), numRounds, numCutoff, numGenerations, players, iTFTR, iGR, iACR, iADR);
	}
	
	static void setup() {
		
	}
	
	public static Prisoner [] setupPlayers(int numberPlayers, double rTFT, double rG, double rAC, double rAD) {
//		System.out.println("Setting up players...");
//		int n = scan.nextInt();
		int n = numberPlayers;
		int players = n;
		Prisoner [] playerAssignments = new Prisoner[n];

//		*********************
		int numTFT = (int)(rTFT * numberPlayers);
//		System.out.println(numTFT);
		int numG = (int)(rG * numberPlayers);
//		System.out.println(numG);
		int numAC = (int)(rAC * numberPlayers);
//		System.out.println(numAC);
		int numAD = (int)(rAD * numberPlayers);
//		System.out.println(numAD);
//		**********************
//		Number counters
		int pCounter = 0;
		int countTFT = 0;
		int countG = 0;
		int countAC = 0;
		int countAD = 0;
		
//		Method to set up player array, to keep track of scores, name, and strategy
//		Strategy 1 = "Tit-4-Tat"
//		Strategy 2 = "Grudger"
//		Strategy 3 = "Always Cooperate"
//		Strategy 4 = "Always Defect"
		for (int i = 0; i< playerAssignments.length; ++i) {
			String thisName = Integer.toString(i+1);
			if (countTFT < numTFT && countTFT < players) {
				Prisoner thisPlayer = new Prisoner(thisName, 1);
//				System.out.println("This players name is: "+thisPlayer.name+". This players strategy is: "+thisPlayer.strategy);
				pCounter += 1;
				countTFT += 1;
				playerAssignments[i] = thisPlayer;
			} else if (countG < numG && countG < players) {
				Prisoner thisPlayer = new Prisoner(thisName, 2);
//				System.out.println("This players name is: "+thisPlayer.name+". This players strategy is: "+thisPlayer.strategy);
				pCounter += 1;
				countG += 1;
				playerAssignments[i] = thisPlayer;
			} else if (countAC < numAC && countAC < players) {
				Prisoner thisPlayer = new Prisoner(thisName, 3);
//				System.out.println("This players name is: "+thisPlayer.name+". This players strategy is: "+thisPlayer.strategy);
				pCounter += 1;
				countAC += 1;
				playerAssignments[i] = thisPlayer;
			} else {
				Prisoner thisPlayer = new Prisoner(thisName, 4);
//				System.out.println("This players name is: "+thisPlayer.name+". This players strategy is: "+thisPlayer.strategy);
				pCounter += 1;
				countAD += 1;
				playerAssignments[i] = thisPlayer;
			}
		}
		double pTFT = (numTFT / (double)players)*100;
		double pG = (numG / (double)players)*100;
		double pAC = (numAC / (double)players)*100;
		double pAD = (numAD / (double)players)*100;
//		System.out.println("Gen 1:	T4T:"+pTFT+"%	G:"+pG+"%		AC:"+pAC+"%		AD:"+pAD+"%");
		return playerAssignments;
	}
	
	public static void playGames(Prisoner [] player, int playRounds, double cutOff, int numGens, int players, double rTFT, double rG, double rAC, double rAD) {
//		System.out.println("Playin games!");
//		Generations = number of process repeats
		for (int i=0; i<numGens; ++i) {
//			reportScores(player);
			System.out.println("Generation: "+(i+1));
			for (int k=0; k<player.length; ++k) {
//				System.out.println("Player "+player[k].name+"is playing");
				for (int l=k; l<player.length; ++l) {
		//			Game interaction between 2 players
					int cOG = 0;
//					int pTwomove = 0;
					int cTG = 0;
					if (l < player.length -1) {
//						System.out.println(player[k].name+" vs. "+player[l+1].name);
					for (int j=0; j<playRounds;++j) {
						Prisoner pOne = player[k];
						Prisoner pTwo = player[l+1];
		//				int pOneMove = 0;
//						int cOG = 0;
//		//				int pTwomove = 0;
//						int cTG = 0;
		//				1 = Coop, 2 = Defect
						
		//					If player One is TFT
	//					System.out.println("Player"+player[k].name+" last move: "+pOne.lastMove);
	//					System.out.println("Player"+player[k].name+" strategy: "+pOne.strategy);
						if (pOne.strategy == 1) {
							if (pOne.lastMove == 0) {
		//						pOneMove = 1;
								pOne.lastMove = 1;
							} else {
		//						pOneMove = pTwo.lastMove;
								pOne.lastMove = pTwo.lastMove;
								pOne.lastMove = 1;
							}
		//					If player One is Grudger
						} else if (pOne.strategy == 2) {
							if (pTwo.lastMove == 2 || cOG == 1) {
		//						pOneMove = 2;
								pOne.lastMove = 2;
								cOG = 1;
							} else {
		//						pOneMove = 1;
								pOne.lastMove = 1;
							}
		//					 If player One is Always Cooperate
						} else if (pOne.strategy == 3) {
							pOne.lastMove = 1;
		//					If player is Always Defect
						} else {
							pOne.lastMove = 2;
						}
						
	//					System.out.println("Player"+player[k+1].name+" last move: "+pTwo.lastMove);
	//					System.out.println("Player"+player[k+1].name+" strategy: "+pTwo.strategy);
			//				If player Two is TFT
						if (pTwo.strategy == 1) {
							if (pTwo.lastMove == 0) {
			//					pOneMove = 1;
								pTwo.lastMove = 1;
							} else {
			//					pOneMove = pTwo.lastMove;
								pTwo.lastMove = pOne.lastMove;
								pTwo.lastMove = 1;
							}
			//				If player One is Grudger
						} else if (pTwo.strategy == 2) {
							if (pOne.lastMove == 2 || cTG == 1) {
			//					pOneMove = 2;
								pTwo.lastMove = 2;
								cTG = 1;
							} else {
			//					pOneMove = 1;
								pTwo.lastMove = 1;
							}
			//				 If player One is Always Cooperate
						} else if (pTwo.strategy == 3) {
							pTwo.lastMove = 1;
			//				If player is Always Defect
						} else {
							pTwo.lastMove = 2;
						}
						
						if (pOne.lastMove == 1 && pTwo.lastMove == 1) {
							pOne.score += 3;
							pTwo.score += 3;
						} else if (pOne.lastMove == 1 && pTwo.lastMove == 2) {
							pOne.score += 0;
							pTwo.score += 5;
						} else if (pOne.lastMove == 2 && pTwo.lastMove == 1) {
							pOne.score += 5;
							pTwo.score += 0;
						} else {
							pOne.score += 1;
							pTwo.score += 1;
						}
	//					System.out.println("Player one score for round: "+j+": "+pOne.score);
	//					System.out.println("Player two score for round: "+j+": "+pTwo.score);
					}
					}
				}
			}
				
				reportScores(player);
				int sTFT = 0;
				int sG = 0;
				int sAC = 0;
				int sAD = 0;
				for (int z=0; z<player.length;++z) {
					if (player[z].strategy == 1) {
						sTFT += 1;
					} else if (player[z].strategy == 2) {
	//					cG += player[k].score;
						sG += 1;
					} else if (player[z].strategy == 3) {
	//					cAC += player[k].score;
						sAC += 1;
					} else {
	//					cAD += player[k].score;
						sAD += 1;
					}
				}
				int totalPlayers = sTFT + sG + sAC + sAD;
				double percentTFT = (double)sTFT / (double)totalPlayers;
				double percentG = (double)sG / (double)totalPlayers;
				double percentAC = (double)sAC / (double)totalPlayers;
				double percentAD = (double)sAD / (double)totalPlayers;
	//			System.out.println(percentTFT);
	//			System.out.println(percentG);
	//			System.out.println(percentAC);
	//			System.out.println(percentAD);
				player = playerCutoff(player, cutOff, players, percentTFT, percentG, percentAC, percentAD);
				cleanScores(player);
	//			player = null;
		
		}
	}
	
	public static void cleanScores(Prisoner [] player) {
		for (int i=0; i<player.length; ++i) {
			player[i].lastMove = 0;
			player[i].score = 0;
			player[i].addTag = 0;
			player[i].removeTag = 0;
		}
	}
	
	public static void reportScores(Prisoner [] player) {
		int cTFT = 0;
		int sTFT = 0;
		double aTFT = 0.0;
		
		int cG = 0;
		int sG = 0;
		double aG = 0.0;
		
		int cAC = 0;
		int sAC = 0;
		double aAC = 0.0;
		
		int cAD = 0;
		int sAD = 0;
		double aAD = 0.0;
		
		int total = 0;
		for (int i=0; i<player.length;++i) {
			if (player[i].strategy == 1) {
				cTFT += player[i].score;
				sTFT += 1;
			} else if (player[i].strategy == 2) {
				cG += player[i].score;
				sG += 1;
			} else if (player[i].strategy == 3) {
				cAC += player[i].score;
				sAC += 1;
			} else {
				cAD += player[i].score;
				sAD += 1;
			}
		}
		int totalPlayers = sTFT + sG + sAC + sAD;
		double percentTFT = (double)sTFT / (double)totalPlayers;
		double percentG = (double)sG / (double)totalPlayers;
		double percentAC = (double)sAC / (double)totalPlayers;
		double percentAD = (double)sAD / (double)totalPlayers;
		System.out.println("T4T:"+percentTFT+"%	G:"+percentG+"%		AC:"+percentAC+"%		AD:"+percentAD+"%");
		total = cTFT + cG + cAC + cAD;
		System.out.println("T4T:"+cTFT+"		G:"+cG+"		AC:"+cAC+"		AD:"+cAD+"		Total: "+total);
		aTFT = (double)cTFT / (double)sTFT;
		aG = (double)cG / (double)sG;
		aAC = (double)cAC / (double)sAC;
		aAD = (double)cAD / (double)sAD;
		System.out.println("T4T:"+aTFT+"		G:"+aG+"		AC:"+aAC+"		AD:"+aAD);
	}
	
	public static Prisoner [] playerCutoff(Prisoner [] player, double cutOff, int players, double rTFT, double rG, double rAC, double rAD) {
		int removeNum = (int)((double)player.length * cutOff);
//		System.out.println("Remove this many players: "+removeNum);
//		Find bottom removeNum players
		int removeTFT = 0;
		int removeG = 0;
		int removeAC = 0;
		int removeAD = 0;
		int addTFT = 0;
		int addG = 0;
		int addAC = 0;
		int addAD = 0;
		
		int removeCounter = 0;
		Prisoner [] removeIndexes = new Prisoner[removeNum];
		for (int n=0;n<removeIndexes.length;++n) {
//			System.out.println("Remove space: "+n);
		}
		for (int i=0; i<removeNum;++i) {
//			System.out.println("Remove iteration: "+(i+1));
			int playerIndex = 0;
			int lowest = Integer.MAX_VALUE;
			Prisoner thisPlayer = null;
			for (int j=0;j<player.length;++j) {
				if (player[j].score < lowest) {
//					thisPlayer = player[j];
					if (player[j].removeTag != 1) {
						lowest = player[j].score;
						thisPlayer = player[j];
//						System.out.println("Player with lowest score: "+player[j].name);
//						System.out.println(lowest);
					}
				}
			}
			
			if (thisPlayer.removeTag != 1) {
//				lowest = thisPlayer.score;
//				System.out.println("Remove tag added to Player: "+thisPlayer.name);
//				System.out.println(thisPlayer.score);
				thisPlayer.removeTag = 1;
				if (thisPlayer.strategy == 1) {
					removeTFT += 1;
				} else if (thisPlayer.strategy == 2) {
					removeG += 1;
				} else if (thisPlayer.strategy == 3) {
					removeAC += 1;
				} else {
					removeAD += 1;
				}
			}
		}
//		System.out.println("Remove this many TFT players: "+removeTFT);
//		System.out.println("Remove this many G players: "+removeG);
//		System.out.println("Remove this many AC players: "+removeAC);
//		System.out.println("Remove this many AD players: "+removeAD);
		for (int i=0; i<removeNum;++i) {
//			System.out.println("Remove iteration: "+(i+1));
			int playerIndex = 0;
			int highest = Integer.MIN_VALUE;
			Prisoner thisPlayerAdd = null;
			for (int j=0;j<player.length;++j) {
				if (player[j].score > highest) {
//					thisPlayer = player[j];
					if (player[j].addTag != 1) {
						highest = player[j].score;
						thisPlayerAdd = player[j];
//						System.out.println("Player with lowest score: "+player[j].name);
//						System.out.println(lowest);
					}
				}
			}
			
			if (thisPlayerAdd.addTag != 1) {
//				lowest = thisPlayer.score;
//				System.out.println("Remove tag added to Player: "+thisPlayerAdd.name);
//				System.out.println(thisPlayerAdd.score);
				thisPlayerAdd.addTag = 1;
				if (thisPlayerAdd.strategy == 1) {
					addTFT += 1;
				} else if (thisPlayerAdd.strategy == 2) {
					addG += 1;
				} else if (thisPlayerAdd.strategy == 3) {
					addAC += 1;
				} else {
					addAD += 1;
				}
			}
		}
		for (int v=0; v<player.length; ++v) {
			player[v].addTag = 0;
		}
//		System.out.println("Add this many TFT players: "+addTFT);
//		System.out.println("Add this many G players: "+addG);
//		System.out.println("Add this many AC players: "+addAC);
//		System.out.println("Add this many AD players: "+addAD);
		
		double newTFT = ((rTFT*100)-removeTFT+addTFT)/100;
//		System.out.println("Started with "+(rTFT*100)+" TFT players");
//		System.out.println("Start again with "+newTFT+" TFT players");
		double newG = ((rG*100)-removeG+addG)/100;
//		System.out.println("Started with "+(rG*100)+" G players");
//		System.out.println("Start again with "+newG+" G players");
		double newAC = ((rAC*100)-removeAC+addAC)/100;
//		System.out.println("Started with "+(rAC*100)+" AC players");
//		System.out.println("Start again with "+newAC+" AC players");
		double newAD = ((rAD*100)-removeAD+addAD)/100;
//		System.out.println("Started with "+(rAD*100)+" AD players");
//		System.out.println("Start again with "+newAD+" AD players");
		
		Prisoner [] newPlayerAssignments = setupPlayers(players, newTFT, newG, newAC, newAD);
		return newPlayerAssignments;
	}
}
