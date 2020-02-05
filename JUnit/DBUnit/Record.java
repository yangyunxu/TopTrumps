package DBUnit;

import java.util.ArrayList;

public class Record {
	private int times;
	private int numberOfMembers;
	private int numberOfDraws;
	private String winner;
	private int rounds;
	private int scoreOfPlayerYou;
	private int scoreOfPlayerAI1;
	private int scoreOfPlayerAI2;
	private int scoreOfPlayerAI3;
	private int scoreOfPlayerAI4;
	
	//getters
	public int getTimes() {
		return times;
	}
	public int getNumberOfMembers() {
		return numberOfMembers;
	}
	public int getNumberOfDraws() {
		return numberOfDraws;
	}
	public String getWinner() {
		return winner;
	}
	public int getRounds() {
		return rounds;
	}
	public int getScoreOfPlayerYou() {
		return scoreOfPlayerYou;
	}
	public int getScoreOfPlayerAI1() {
		return scoreOfPlayerAI1;
	}
	public int getScoreOfPlayerAI2() {
		return scoreOfPlayerAI2;
	}
	public int getScoreOfPlayerAI3() {
		return scoreOfPlayerAI3;
	}
	public int getScoreOfPlayerAI4() {
		return scoreOfPlayerAI4;
	}
	
	//setters
	public void setTimes(int times) {
		this.times = times;
	}
	public void setNumberOfMember(int numberOfMember) {
		this.numberOfMembers = numberOfMember;
	}
	public void setNumberOfDraws(int numberOfDraws) {
		this.numberOfDraws = numberOfDraws;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public void setRounds(int rounds) {
		this.rounds = rounds;
	}
	public void setScoreOfPlayerYou(int scoreOfPlayerYou) {
		this.scoreOfPlayerYou = scoreOfPlayerYou;
	}
	public void setScoreOfPlayerAI1(int scoreOfPlayerAI1) {
		this.scoreOfPlayerAI1 = scoreOfPlayerAI1;
	}
	public void setScoreOfPlayerAI2(int scoreOfPlayerAI2) {
		this.scoreOfPlayerAI2 = scoreOfPlayerAI2;
	}
	public void setScoreOfPlayerAI3(int scoreOfPlayerAI3) {
		this.scoreOfPlayerAI3 = scoreOfPlayerAI3;
	}
	public void setScoreOfPlayerAI4(int scoreOfPlayerAI4) {
		this.scoreOfPlayerAI4 = scoreOfPlayerAI4;
	}
}
