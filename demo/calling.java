package demo;
import java.util.ArrayList;
import common.Machine;
public class calling {
    public void sendmessage(ArrayList<Machine> m){
        for(int i=0 ; i<m.size() ;i++){
            m.get(i).setMachines(m);
        }

    }
}
