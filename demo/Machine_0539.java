package demo;

import common.Location;
import common.Machine;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.lang.model.util.ElementScanner6;

import java.lang.Math;


public class Machine_0539 extends Machine{
	@Override
	public void setMachines(ArrayList<Machine> machines) {
		//call varaible indicates is used to identify the which action to be taken 
		//basically it means int the context of which round.

		////////////////////////////////

		//the commented part including System.out.println was used to check whether everything was working fine
		//if we uncommented out the System.out.println statements then we can the see the whole process 
		//i.e, which machine is sending what data to which machine , what is the other machine receiving and much more

		////////////////////////////////
		call++;
		if(call==1){
			if(isfaulty){
				//System.out.println("Hello I am faulty and my id is " + id);
				int send=(int)(Math.random()*(3));
				if(send==0){
					//System.out.println("I " + id + " have decided to not send anything to anyone");
				}
				if(send==1){
					//System.out.println("I " + id + " have decided to send the value received by leader to send to everyone");
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id, phasenumber,1, round0value);
					}
				}
				if(send==2){
					//System.out.println("I "+ id + " has decided to send some random value to all");
					int decision=(int)(Math.random()*2);
					//System.out.println("I will send the value "+decision);
					if(decision==round0value){
						//sending the value same as leader
						///System.out.println("I am sending the value same as leader");
					}
					else{
						//sending some random value
						//System.out.println("I am sending some random value");
					}
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id,phasenumber,1,decision);
					}
				}
			}
			else{
				//System.out.println("Hello I am not faulty and my id is " + id);
				if(round0value==2){
					//System.out.println("I didn't recieved anything from leader so I am not sending anything to anyone");
				}
				else{
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id,phasenumber,1,round0value);
					}
				}	
			}
		}
		if(call==2){
			//System.out.println();
			if(countleft>countright){
				//System.out.println("I am going left "+id);
				round1value=0;
			}
			if(countright>countleft){
				//System.out.println("I am going right "+id);
				round1value=1;
			}
			if(countleft==countright){
				//System.out.println("I am standing here "+ id);
				round1value=-1;
			}
			//System.out.println("countleft " + countleft +" countright "+ countright);
			////////////////////////////////////////////////////////////////
			//System.out.println();
			if(isfaulty){
				//System.out.println("Hello I am faulty and my id is " + id);
				int send=(int)(Math.random()*(3));
				if(send==0){
					//System.out.println("I " + id + " have decided to not send anything to anyone");
				}
				if(send==1){
					//System.out.println("I " + id + " have decided to send the result of round 1 to everyone");
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id, phasenumber,2, round1value);
					}
				}
				if(send==2){
					//System.out.println("I "+ id + " has decided to send some random value to all");
					int decision=(int)(Math.random()*2);
					//System.out.println("I will send the value "+decision);
					if(decision==round1value){
						//System.out.println("I am sending the value same as result of round1");
					}
					else{
						//System.out.println("I am sending some random value");
					}
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id,phasenumber,2,decision);
					}
				}
			}
			else{
				//System.out.println("Hello I am not faulty and my id is " + id);
				if(round0value==2){
					//System.out.println("I didn't recieved anything from leader so I am not sending anything to anyone");
				}
				else{
					for(int i=0; i<machines.size(); i++){
						machines.get(i).sendMessage(id,phasenumber,2,round1value);
					}
				}	
			}
		}
		if(call==3){
			//System.out.println();
			//System.out.println("Now the times comes to tell my final movement direction");
			//System.out.println("my id is " + id);
			//System.out.println("countleft2 "+ countleft2 + " countright2 " + countright2);
			if(isfaulty){
				movedecision=(int)(Math.random()*2);
				if(countleft2>=(2*countfault)+1){
					//System.out.println("I am finally going to move to left");
					//movedecision=0;
					majorityleft++;
					//this.move();
				}
				else if(countright2>=(2*countfault)+1){
					//System.out.println("I am fnally going to move in the right direction");
					//movedecision=1;
					majorityright++;
					//this.move();
				}
				else{
					//System.out.println("I am not going to move anywhere");
				}
				this.move();
			}
			else{
				if(countleft2>=(2*countfault)+1){
					//System.out.println("I am finally going to move to left");
					movedecision=0;
					majorityleft++;
					this.move();
				}
				else if(countright2>=(2*countfault)+1){
					//System.out.println("I am fnally going to move in the right direction");
					movedecision=1;
					majorityright++;
					this.move();
				}
				else{
					//System.out.println("I am not going to move anywhere");
				}
			}
			
			
			if(id==machines.size()-1){
				//System.out.println("I am the last "+ id);
				if(majorityright>majorityleft){
					System.out.println("Right wins");
				}
				else if(majorityleft>majorityright){
					System.out.println("Left wins");
				}
				else{
					System.out.println("same direction");
				}
			}

		}
		//lmachines=machines;
		if(call==3){
			call=0;
			countleft=0;
			countright=0;
			countright2=0;
			countleft2=0;
			phasenumber++;
			if(id==machines.size()-1){
				countfault=0;
				majorityleft=0;
				majorityright=0;
			}
		}

	}
	public Machine_0539() {
		id = nextId++;
		
	}

	@Override
	public void setStepSize(int stepSize) {
		step = stepSize;
	}

	@Override
	public void setState(boolean isCorrect) {
		isfaulty=isCorrect;
		if(isfaulty==true){
			countfault++;
			//System.out.println("total fault count is "+ countfault);
		}
	}

	@Override
	public void setLeader() {
		isleader=true;
	}

	@Override
	public void sendMessage(int sourceId, int phaseNum, int roundNum, int decision) {
		//System.out.println(sourceId+" "+phaseNum+" "+roundNum+" "+decision);
		if(roundNum==0){
			if(decision == -1){
				//System.out.println("I didnt get the message from the leader "+ id + " " + isfaulty);
				round0value=2;
			}
			else{
				round0value=decision;
				//System.out.println("I recieved message from the leader "+ id+ " " + isfaulty+ " the decision received is "+ round0value);
			}
		}
		else if (roundNum==1){
			if(decision == -1){
				//System.out.println("I didnt get the from the machine with source id "+ sourceId );
			}
			else if (decision == 0){
				//System.out.println("I received the message from " +sourceId + " and my id is "+id+" the value received is "+ decision );
				countleft++;
			}
			else if(decision == 1){
				//System.out.println("I received the message from " +sourceId + " the value received is " + decision);
				countright++;
			}

		}
		else if(roundNum==2){
			if(decision==0){
				//System.out.println("I received the message from " +sourceId +" and my id is "+ id+ " the value received is " +decision);
				countleft2++;
			}
			else if(decision == 1){
				//System.out.println("I received the message from " +sourceId +" and my id is " + id+ " the value received is " + decision);
				countright2++;
			}

		}
		
		
	}

	@Override
	public
	void move() {
		//System.out.println(pos.getX() + " " + pos.getY()+" " +dir.getX()+ " " + dir.getY());
		if(call==3){
			if(movedecision==0){
				//moving to +ive x
				if(dir.getX()==1 && dir.getY()==0){
					//System.out.println("hello");
					dir.setLoc(0,1);
				}
				else if(dir.getX()==-1 && dir.getY()==0){
					dir.setLoc(0,-1);
				}
				else if(dir.getX()==0 && dir.getY()==1){
					dir.setLoc(-1,0);
				}
				else if(dir.getX()==0 && dir.getY()==-1)
				{
					dir.setLoc(1,0);
				}
			}
			else if(movedecision==1){
				if(dir.getX()==1 && dir.getY()==0){
					//System.out.println("Hi");
					dir.setLoc(0,-1);
				}
				else if(dir.getX()==-1 && dir.getY()==0){
					dir.setLoc(0,1);
				}
				else if(dir.getX()==0 && dir.getY()==1){
					dir.setLoc(1,0);
				}
				else if(dir.getX()==0 && dir.getY()==-1)
				{
					dir.setLoc(-1,0);
				}
			}
		}
		pos.setLoc(pos.getX() + dir.getX()*step, pos.getY() + dir.getY()*step);
		//System.out.println(pos.getX() + " " + pos.getY()+" " +dir.getX()+ " " + dir.getY());
			
	}

	@Override
	public String name() {
		return "0539_"+id;
	}

	@Override
	public Location getPosition() {
		
		return new Location(pos.getX(), pos.getY());
	}

	private int step;
	private Location pos = new Location(0,0);
	private Location dir = new Location(1,0); // using Location as a 2d vector. Bad!
	private static int nextId = 0;
	private int id;
	private boolean isfaulty=false, isleader=false;
	private int round0value;
	private int countleft =0;
	private int countright =0;
	private int call =0;
	private int phasenumber=1;
	private static int countfault;
	private int round1value;
	private int countleft2=0;
	private int countright2=0;
	private static int majorityright=0;
	private static int majorityleft=0;
	private int movedecision=2;
	
}