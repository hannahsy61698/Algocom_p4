/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algocom_p4;
  
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Hannah Sy
 */
public class driver {

    /**
     * @param args the command line arguments
     */
    
  	//0 = num of team(1st) and num of tables(2nd)
	//1 = size of each team
	//2 = capacity of each table.
	public static void assignTable(String[] input) {
		List<Team> teamList;
		List<Table> tableList;
		
		int[] sizes, 
		      teamSizes, 
		      tableCapacities;
		
		int possible = 1;
		
		sizes = stringSplitToInt(input[0]);
		teamSizes = stringSplitToInt(input[1]);
		tableCapacities = stringSplitToInt(input[2]);
		
		teamList = new ArrayList<Team>();
		for(int i = 0; i < teamSizes.length; i++) 
			teamList.add(new Team(i+1, teamSizes[i]));
		
		tableList = new ArrayList<Table>();
		for(int i = 0; i < tableCapacities.length; i++) {
			tableList.add(new Table(i+1, tableCapacities[i]));
		}
		
//		printElements(teamList);
//		printElements(tableList);
		
		//Descending order of tables.
		Collections.sort(tableList, Table.capacityComparator);
		
		for (Team team : teamList) {
			
			if(team.getSize() <= tableList.size()) {
				
				int index = 0;
				while(team.getSize() > 0 && index < tableList.size()) {
					
					Table table = tableList.get(index);
					
					if(table.getCapacity() > 0) {
						team.addTableAssigned(table.getName());
						team.setSize(team.getSize() - 1);
						table.setCapacity(table.getCapacity() - 1);
					}
					
					index++;
				}
			}
			
			//not possible anymore since the number of tables is less the number of student in a team.
			else {
				possible = 0;
				break;
			}
		}
		
		//check if all teams are capacity 0
		for (Team team : teamList) {
			if(team.getSize() != 0) {
				possible = 0;
				break;
			}
		}
		
		System.out.println(possible);
		if(possible == 1) {			
			for(Team team : teamList) {
				team.printTableAssigned();
			}
		}		
	}
	
	private static int[] stringSplitToInt(String string) {
		
		String[] stringSplitted = string.split(" ");
		int size = stringSplitted.length;
		int[] intSplitted = new int[size];
		
		for(int i = 0 ; i < size; i++) 
			intSplitted[i] = Integer.parseInt(stringSplitted[i]);
		
		return intSplitted;
	}
	
	//Testing	
	private static void printElements(List<Integer> list) {
		for (int element : list) {
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	public static void shoemaker(int[] jobTime, int[] fine) {
		  List<Job> jobList = new ArrayList<Job>();
		  
		  for(int i = 0; i < jobTime.length; i++) {
			  jobList.add(new Job(jobTime[i], fine[i], i+1));
		  }
		  
//		  //test
//		  for(Job j : jobList) {
//			  j.printContents();
//			  System.out.println();
//		  }
		  
		  Collections.sort(jobList, Job.ratioComparator);
		  
		  for(Job j : jobList) {
			  System.out.print(j.getName() + " ");
		  }
		  System.out.println();
	}

	public static void design(int[] floors) {
		  List<Integer> negFloors = new ArrayList<Integer>();
		  List<Integer> posFloors = new ArrayList<Integer>();
		  
		  for(int floor : floors) {
			  if(floor > 0) 
				  posFloors.add(floor);
			 
			  else 
				  negFloors.add(floor);
		  }
		  
		  Collections.sort(posFloors, Collections.reverseOrder());
		  Collections.sort(negFloors);
		  
		  System.out.println(posFloors);
		  System.out.println(negFloors);
		  
		  //Positive floor has the MAX value.
		  if(posFloors.get(0) > Math.abs(negFloors.get(0)))
			  buildDesign(posFloors, negFloors);
		  else
			  buildDesign(negFloors, posFloors);
			  
		 
	}
	
	private static void buildDesign(List<Integer> firstList, List<Integer> secondList) {
		
		List<Integer> building = new ArrayList<Integer>();
		
		building.add(firstList.get(0));
		int min = Math.abs(firstList.get(0));
		
		boolean flag = true; //true = first, false == second.
		int i = 0; //first list index
		int j = 0; //second list index
		
		while(i < firstList.size() && j < secondList.size()) {
			
			if(flag) {
				
				if(min > Math.abs(secondList.get(j))) {
					min = Math.abs(secondList.get(j));
					building.add(secondList.get(j));
					flag = !flag;
					i++;
				}
				
				else j++;
			}
			
			else {
				
				if(min > Math.abs(firstList.get(i))) {
					min = Math.abs(firstList.get(i));
					building.add(firstList.get(i));
					flag = !flag;
					j++;
				}
				
				else i++;
			}
		}
		System.out.println(building);
		System.out.println(building.size());
	}
	
	private static class Table {
		private int name;
		private int capacity;
		
		public Table(int name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		}

		public int getName() {
			return name;
		}

		public void setName(int name) {
			this.name = name;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}
		
		public static void printElements(List<Table> tables) {
			for (Table t : tables) {
				System.out.print("Table " + t.getName() + ": " + t.getCapacity() + " | ");
			}
			System.out.println();
		}
		
		public static Comparator<Table> capacityComparator = new Comparator<Table>() {
			@Override
			public int compare(Table t1, Table t2) {
				// TODO Auto-generated method stub
				return (t2.getCapacity() < t1.getCapacity() ? -1 : (t2.getCapacity() == t1.getCapacity() ? 0 : 1));
			}
		};
	}
	
	private static class Job {
		private int name;
		private int time;
		private int fine;
		private double ratio;
		
		public Job(int time, int fine, int name) {
			this.time = time;
			this.fine = fine;
			this.name = name;
			this.ratio = (double) fine / time;
		}
		
		public int getName() {
			return this.name;
		}
		
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public int getFine() {
			return fine;
		}
		public void setFine(int fine) {
			this.fine = fine;
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
		
		public void printContents() {
			System.out.println("Time: " + this.time + " days.");
			System.out.println("Fine: " + this.fine + " cents.");
			System.out.println("Ratio: " + this.ratio);
		}
	}
	
	private static class Team {
		private int name;
		private int size;
		private List<Integer> tableAssigned;
		
		public Team(int name, int size) {
			this.name = name;
			this.size = size;
			this.tableAssigned = new ArrayList<Integer>();
		}
		
		public void addTableAssigned(int tableName) {
			this.tableAssigned.add(tableName);
		}

		public int getName() {
			return name;
		}

		public void setName(int name) {
			this.name = name;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}
		
		public static void printElements(List<Team> teams) {
			for (Team t : teams) {
				System.out.print("Team " + t.getName() + ": " + t.getSize() + " ");
			}
			System.out.println();
		}
		
		public void printTableAssigned() {
			for (int i : tableAssigned) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        String[] s = {"4 5", "5 4 3 3", "3 3 3 4 5"};
//		assignTable(s);
		
//		int[] time = {1, 2, 3};
//		int[] fine = {1, 2, 3};
//		shoemaker(time, fine);

		//int[] floors = {5, 7, -2, 6, 9, -3};
		int[] floors = {8, 11, -9, 2, 5, 18, 17, -15, 4};
		//int[] floors = {33, 41, 64, 79, -61, 10, 84, 99, -26, 56, 78, -46, -45, 3, 43, 16, -57, -59, 67, 35};
		//int[] floors = {-42, 57, -60, -96, 20, -52, 83, 33, -15, -14, -71, 41, 2, 43, 89, -61, 66, 29, -24, 4};
		design(floors);

		
	}
    }
    
