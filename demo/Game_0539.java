package demo;
import java.util.ArrayList;
import common.Game;
import common.Machine;
import java.lang.Math;

public class Game_0539 extends Game {

	@Override
	public void addMachines(ArrayList<Machine> machines, int numFaulty) {
		this.machinelist=machines;
		this.nooffaulty=numFaulty;
	}

	@Override
	public void startPhase() {
		int min=0;
		msize=machinelist.size();
		for(int i=0; i<msize; i++) {
			//machinelist.get(i).setmachinelist(machinelist);
			machinelist.get(i).setState(false);
		}
		leaderid=(int)(Math.random()*(msize-min))+min;
		machinelist.get(leaderid).setLeader();
		System.out.println("leader is " +leaderid);
		for(int i=0;i<nooffaulty;i++){
			if(i>0){
				while(true){
					int a =(int)((Math.random()*(msize-min))+min);
					//System.out.println(a+ " " + i);
					if(a!=faultyid.get(i-1)){
						if(a==leaderid){
							isleaderfaulty=true;
						}
						faultyid.add(a);
						machinelist.get(a).setState(true);
						System.out.println("faulty " +a);
						break;
					}
				}
			}					
			else{
				int a= (int)(Math.random()*(msize-min))+min;
				if(a==leaderid){
					isleaderfaulty=true;
				}
				faultyid.add(a);
				machinelist.get(a).setState(true);
				System.out.println("faulty " +a);
			}
		}
		for(int i=0; i<machinelist.size(); i++){
			System.out.println(machinelist.get(i).name());
		}

		phaseno++;
		round0 startround0 =new round0();
		//System.out.println("Starting round0 ");
		startround0.setleader(machinelist.get(leaderid),machinelist, leaderid,phaseno,nooffaulty,isleaderfaulty);
		startround0.sendmessage();
		//lets go to round 1
		//System.out.println();
		//System.out.println("Starting round 1");
		calling startround1 = new calling();
		startround1.sendmessage(machinelist);
		startround1.sendmessage(machinelist);
		startround1.sendmessage(machinelist);
		//	
	}
	private int msize;
	private int faulty;
	private int leaderid;
	private int phaseno=0;
	private boolean isleaderfaulty=false;
	private ArrayList<Integer> faultyid = new ArrayList<Integer>();
	private ArrayList<Machine > machinelist = new ArrayList<Machine>();
	private int nooffaulty;
}
