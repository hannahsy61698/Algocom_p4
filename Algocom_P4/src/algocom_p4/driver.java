/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algocom_p4;
  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * @author Hannah Sy
 */
public class driver {

    /**
     * @param args the command line arguments
     */

    //Number 1
	private static class Table {
		private int name;
		private int capacity;
		
		public Table(int n, int c) {
			name = n;
			capacity = c;
		}

		public void setCapacity(int c) {
			capacity = c;
		}
		
		public int getName() {
			return name;
		}

		public int getCapacity() {
			return capacity;
		}

		public static Comparator<Table> capacityComparator = new Comparator<Table>() {
			@Override
			public int compare(Table t1, Table t2) {
				// TODO Auto-generated method stub
				return (t2.getCapacity() < t1.getCapacity() ? -1 : (t2.getCapacity() == t1.getCapacity() ? 0 : 1));
			}
		};
	}
	
	private static class Team {
		private int size;
		private ArrayList<Integer> assigned;
		
		public Team(int s) {
			size = s;
			assigned = new ArrayList<Integer>();
		}
		
		public void addTableAssigned(int tableName) {
			assigned.add(tableName);
		}

		public int getSize() {
			return size;
		}

		public void setSize(int s) {
			size = s;
		}
		
		public void printTableAssigned() {
			int i = 0;
			
			while(i < assigned.size()) {
				System.out.print(assigned.get(i) + " ");
				i++;
			
			}
			System.out.println();
		}
	}
	
	private static int[] ToInt(String string) {
		String[] splitted = string.split(" ");
		int[] split = new int[splitted.length];
		
		for(int i = 0 ; i < splitted.length; i++) 
			split[i] = Integer.parseInt(splitted[i]);
		
		return split;
	}

	public static void assignTable(String[] input) {
		int[] s, TS, TC;
		int pos = 1;
			
		ArrayList<Team> team = new ArrayList<Team>();
		ArrayList<Table> tableList = new ArrayList<Table>();
		
		s = ToInt(input[0]);
		TS = ToInt(input[1]);
		TC = ToInt(input[2]);
		
                for (int i =0;i<s[0];i++)
                {
                    team.add(new Team(TS[i]));
			
                }
                
                for (int j = 0;j<s[1];j++)
		{
			tableList.add(new Table(j + 1, TC[j]));
		}
		
		Collections.sort(tableList, Table.capacityComparator);
		
		for(int k=0; k<team.size();k++)
                {
			if(team.get(k).getSize() <= tableList.size()) {
				int n = 0;
				while(team.get(k).getSize() > 0 && n < tableList.size()) {
						
					Table table = tableList.get(n);
						
					if(table.getCapacity() > 0) {
						team.get(k).addTableAssigned(table.getName());
						team.get(k).setSize(team.get(k).getSize() - 1);
						table.setCapacity(table.getCapacity() - 1);
					}
						
				n++;
				}
			}else{
				pos = 0;
			}
		}
		
                      
         for(int l=0;l<team.size();l++)
                {
			if(team.get(l).getSize() != 0) {
				pos = 0;
			}
		}
	
			
		System.out.println(pos);
		
		if(pos == 1) {
                     for(int m=0;m<team.size();m++){
			team.get(m).printTableAssigned();
				
			}
		}		
	}
		
//Number 2
	private static class Job {
		private int num;
		private double ratio;
		
		public Job(int number, int time, int fine) {
			num = number;
			ratio = fine / time;
		}
		
		public int getNum() {
			return num;
		}
		
		public double getRatio() {
			return ratio;
		}
		
		public static Comparator<Job> ratioComparator = new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				// TODO Auto-generated method stub
				return (j2.getRatio() < j1.getRatio() ? -1 : (j2.getRatio() == j1.getRatio() ? 0 : 1));
			}
		};
	}
	
	public static void shoemaker(int[] jobTime, int[] fine) {
		ArrayList<Job> List = new ArrayList<Job>();
		 
            for (int i=0;i<jobTime.length;i++)
            {
	    	List.add(new Job(i + 1, jobTime[i], fine[i]));
	    	
	    }
  
		Collections.sort(List, Job.ratioComparator);
                
	        for(int j=0;j<List.size();j++)
                {
			System.out.print(List.get(j).getNum() + " ");
		}
	}
		
//Number 3
	private static void buildingdesign(ArrayList<Integer> first, ArrayList<Integer> second) {
		ArrayList<Integer> build = new ArrayList<Integer>();
		int min = Math.abs(first.get(0));
		int tell = 1;
		int i = 0;
		int j = 0;
			
		build.add(first.get(0));
		
		while(j < first.size() && i < second.size()) {		
			if(tell != 0) {
				if(Math.abs(second.get(i)) < min) {
					min = Math.abs(second.get(i));
					build.add(second.get(i));
					
					if(tell == 1) {
						tell = 0;
					}else {
						tell = 1;
					}
					
					j++;
				}else {
					i++;
				}
			}else {
				if(Math.abs(first.get(j)) < min) {
					min = Math.abs(first.get(j));
					build.add(first.get(j));

					if(tell == 1) {
						tell = 0;
					}else {
						tell = 1;
					}
					
					i++;
				}else { 
					j++;
				}
			}
		}
		
		System.out.println(build.size());
	}

	public static void design(int[] floors) {
		ArrayList<Integer> neg = new ArrayList<Integer>();
		ArrayList<Integer> pos = new ArrayList<Integer>();
	
                for(int i = 0 ;i<floors.length;i++)
                {if(floors[i] > 0) {
				pos.add(floors[i]);
			}else{ 
				neg.add(floors[i]);
			}
		}
		
		Collections.sort(neg);
		Collections.sort(pos, Collections.reverseOrder());
		
		if(Math.abs(neg.get(0)) > pos.get(0)) {
			buildingdesign(neg, pos);
		}else{
			buildingdesign(pos, neg);
		}	  
	}

	
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String[] s = {"4 5", "4 5 3 5", "3 5 2 6 4"};
		assignTable(s);
//		
//		int[] time = { 1, 2 ,3};
//		int[] fine = {1,2,3};
//		shoemaker(time, fine);

//		int[] floors = {5, 7, -2, 6, 9, -3};
//		int[] floors = {8, 11, -9, 2, 5, 18, 17, -15, 4};
		//int[] floors = {33, 41, 64, 79, -61, 10, 84, 99, -26, 56, 78, -46, -45, 3, 43, 16, -57, -59, 67, 35};
		//int[] floors = {-42, 57, -60, -96, 20, -52, 83, 33, -15, -14, -71, 41, 2, 43, 89, -61, 66, 29, -24, 4};
//		design(floors);

		
	}
    }
    
