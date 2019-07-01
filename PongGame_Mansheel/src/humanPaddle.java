import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class humanPaddle{
	
	double y, yVel;
	boolean upAccel, downAccel; // moving up faster or moving down faster 
	int player, x; // player 1 or 2, x and y are position
	int playerScore;
	final double GRAVITY=0.94;
	int paddleWidth=20;//extends to the right
	int paddleLength=80; // extends downwards
	
	public humanPaddle(int player){ //constructor
		upAccel=false; downAccel=false;
		y=210; yVel=0;
		if (player==1) {
			x=20;
		}
		else if(player==2) {
			x=660;
		}
	}
	
	public void initializePaddle() { // bring the paddle back to the centre-ish
		y=210;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, paddleWidth, paddleLength); //size of paddle
	}

	
	public void move() {
		if (upAccel) { //if upacc is true, go up
			yVel-=2;
		}
		else if (downAccel) { // if downacc is true, go down
			yVel+=2;
		}
		else if (!upAccel && !downAccel) {
			yVel*=GRAVITY;
		}
		
		if (yVel>=3) { // capping upward speed to 5
			yVel=3;
		}
		
		else if (yVel<=-3) { // capping downward speed to 5
			yVel=-3;
		}
		
		y+=yVel;
		
		
		//collision dtection to make sure paddle doesnt go outside the screen 
		if (y<0) {
			y=0;
		}
		if(y>420) {
			y=420;
		}
		
	}

	public void setUpAccel(boolean input) {
		upAccel=input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel=input;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getPaddleWidth() {
		return paddleWidth;
	}

	public int getPaddleLength() {
		return paddleLength;
	}
	
	public void increaseScore() {
		this.playerScore ++;
	}
	
	public String getScore() {
		return String.valueOf(this.playerScore);
	}
	
	public int getIntScore() {
		return this.playerScore;
	}
	
	public void initializeScore() {
		this.playerScore=0;
	}
	
	

}
