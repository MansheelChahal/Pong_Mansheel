import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double xVel, yVel, x, y; // velocities and positions of the ball
	int radius=10;
	
	public Ball() { // constructor
		x=350; // 350 before
		y=getRandomYPosition(); // 250 before
		xVel=getRandomSpeed() * getRandomDirection();
		yVel=getRandomSpeed()* getRandomDirection();
		System.out.println("xVel: "+xVel+"; yVel: "+yVel);
	}
	
	public void setBallAgain() {
		x=350;
		y=getRandomYPosition();
		xVel=getRandomSpeed() * getRandomDirection();
		yVel=getRandomSpeed() * getRandomDirection();
		System.out.println("setballAgain--> xVel: "+xVel+"; yVel: "+yVel);
	}
	
	public double getRandomYPosition() {
		return (int)(Math.random()*400 +50);
	}
	
	public double getRandomSpeed() {
		return (int)(Math.random() *5 + 1); // speed ranges from 1 to 5
		//return 3;
	}
	
	public int getRandomDirection() {
		int rand =(int)(Math.random() * 2);
		if (rand==1) {
			return 1;
		}
		else{
			return -1;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10, (int)y-10, radius*2, radius*2); // drawing the ball
	}
	
	public void checkPaddleCollision2(humanPaddle p1, humanPaddle p2) {
		double temp=0;
		double temp2=0;
		if ((x-10==p1.getX()+p1.getPaddleWidth())  &&  (y>=p1.getY() && y<=p1.getY()+(p1.getPaddleLength()))) {
			temp=xVel;
			temp2=yVel;
			if (y>=p1.getY() && y<p1.getY()+20) { // first zone
				xVel=Math.abs(yVel);
				yVel=Math.abs(temp)*(  temp2/(Math.abs(temp2))  );
			}  
			
			else if(y>=p1.getY()+20 && y<p1.getY()+60) { // second zone, only switch x velocity
				xVel=xVel*-1;	
			}
			
			else if(y>=p1.getY()+60 && y<=p1.getY()+ p1.getPaddleLength()) { // third zone
				xVel=Math.abs(yVel);
				yVel=Math.abs(temp)*(  temp2/(Math.abs(temp2))  );
			}

		}
		else if ((x>=p1.getX()) && (x<=p1.getX()+p1.getPaddleWidth()) &&  (y+radius==p1.getY() || (y==p1.getY()+p1.getPaddleLength()) )  ) {
			yVel=-yVel;
		}
		
		if (   (x+10==p2.getX()) &&   (y>=p2.getY() && y<=p2.getY()+p2.getPaddleLength())     ) {
			temp=xVel;
			temp2=yVel;
			if (y>=p2.getY() && y<p2.getY()+20) { // first zone
				xVel=Math.abs(yVel)*-1;
				yVel=Math.abs(temp)*(  temp2/(Math.abs(temp2))  ); 
			}  
			
			else if(y>=p2.getY()+20 && y<p2.getY()+60) { // second zone, only switch x velocity
				xVel=xVel*-1;	
			}
			
			else if(y>=p2.getY()+60 && y<=p2.getY()+ p2.getPaddleLength()) { // third zone
				xVel=Math.abs(yVel)*-1;
				yVel=Math.abs(temp)*(  temp2/(Math.abs(temp2))  );
			}
		}
		
		else if (  (x>=p2.getX()) && (x<=p2.getX()+p2.getPaddleWidth())  &&  (y+radius==p2.getY() || (y==p2.getY()+p2.getPaddleLength()) )    ) {
			yVel=-yVel;
		}
	}
	
	public void move(humanPaddle p1, humanPaddle p2) {
		checkPaddleCollision2(p1, p2);
		x+=xVel;
		y+=yVel;
		
		if (y<10) {
			yVel=-yVel;
		}
		if (y>490) {
			yVel=-yVel;
		}
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
}
