package demo;
import common.Machine;
import java.lang.Math;
import java.util.*;
public class round0 {
    public void setleader(Machine l,ArrayList<Machine> m, int n, int pn,int a, boolean faulty){
        leader =l;
        machineslist=m;
        leaderid=n;
        phasenumber=pn;
        faultcount=a;
        isleaderfaulty=faulty;

    }
    public void sendmessage(){
        int decision = (int)(Math.random()*(2));
        //System.out.println("The decision made by the leader is : "+decision);
        //System.out.println(isleaderfaulty);
        int round= 0;
        if(isleaderfaulty){
            //System.out.println("The no of machines faulty is "+ faultcount);
            int notsend =(int)(Math.random()*(faultcount+1));
            //System.out.println("leader is faulty and the number of machines to which it wont send the messages is :" + notsend);
            //make a arraylist to which it wont send the message
            ArrayList <Integer > notsendid = new ArrayList<Integer>();
            for (int i=0;i<notsend;i++){
                if(i==0){
                    int a = (int) (Math.random()*(machineslist.size()+1));
                    notsendid.add(a);
                    //System.out.println("NOt send to from leader :" + notsendid.get(i));
                }
                else{
                    while(true){
                        int a =(int) (Math.random()*(machineslist.size()+1));
                        if(a!=notsendid.get(i-1) ){
                            notsendid.add(a);
                            //System.out.println("Not send to from leader : " +notsendid.get(i));
                            break;
                        }
                    }
                }
            }
            //now send the message to except the list of notsend
            for(int i=0;i<machineslist.size();i++){
                int flag=0;
                for(int j=0;j<notsendid.size();j++){
                    if(i==notsendid.get(j)){
                        flag=1;
                        break;
                    }
                }
                if(flag==1){
                    machineslist.get(i).sendMessage(leaderid, phasenumber, round, -1);

                }
                else{
                    machineslist.get(i).sendMessage(leaderid, phasenumber, round, decision);
                }
            }
        }
        else{
            //System.out.println("The leader is not faulty");
            for(int i=0;i<machineslist.size();i++){
                machineslist.get(i).sendMessage(leaderid, phasenumber, round, decision);
            }
        }
    }
    private Machine leader;
    private ArrayList<Machine> machineslist = new ArrayList<Machine>();  
    private int leaderid; 
    private int phasenumber; 
    private int faultcount;
    private boolean isleaderfaulty ;

}
