package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// import java.util.StringJoiner;
import java.util.Scanner;



public class PerfectStudyPlan {
	/*
	public static void main(String[] args) {
		PerfectStudyPlan solution = new PerfectStudyPlan();

		
		/ *
		for (int i = 1; i < 10; i++) {
			List<Integer> result = solution.solution(i);
			
			for (int j : result) { 
				System.out.print(j);;
				System.out.print(" ");
			}
			
			System.out.println();
		}
		* /
		
		// a far better way to solve the issue is as follow
		for (int i = 1; i < 10; i++) {
			List<Integer> result = solution.solution(i);
			//StringJoiner strJoiner = new StringJoiner(",", Arrays.toString(result))
			String[] strings = new String[result.size()];
			for (int j = 0; j < result.size(); j++) { 
				strings[j] = String.valueOf(result.get(j));
			}
			
			// for more details on how to use the StringJoiner, you can find it here:
			//   https://docs.oracle.com/javase/8/docs/api/java/util/StringJoiner.html
			StringJoiner sj = new StringJoiner(" ");
			for (int j = 0; j < strings.length; j++) {
				sj.add(strings[j]);
			}
			
			System.out.println(sj.toString());
		}
	}
	
	*/
	
	public static void main(String[] args) {
		PerfectStudyPlan solution = new PerfectStudyPlan();
		Scanner scanner = new Scanner(System.in);
		int numberOfLines = scanner.nextInt();
		
		for (int j = 0; j < numberOfLines; j++) { 
			int n = scanner.nextInt();
			List<Integer> result = solution.solution(n);
//			String[] strings = new String[result.size()];
//			for (int i = 0; i < strings.length; i++) {
//				strings[i] = String.valueOf(result.get(i));
//			}
			
//			StringJoiner sj = new StringJoiner(" ");
//			for (int i = 0; i < strings.length; i++) {
//				sj.add(strings[i]);
//			}
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (i < result.size()) {
				sb.append(String.valueOf(result.get(i)));
				i++;
				if (i < result.size()) {
					sb.append(" ");
				}
			}
			
			System.out.println(sb.toString());
		}
		/*
		while (scanner.hasNextInt()) {
			int n = scanner.nextInt();
			List<Integer> result = solution.solution(n);
//			String[] strings = new String[result.size()];
//			for (int i = 0; i < strings.length; i++) {
//				strings[i] = String.valueOf(result.get(i));
//			}
			
//			StringJoiner sj = new StringJoiner(" ");
//			for (int i = 0; i < strings.length; i++) {
//				sj.add(strings[i]);
//			}
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (i < result.size()) {
				sb.append(String.valueOf(result.get(i)));
				i++;
				if (i < result.size()) {
					sb.append(" ");
				}
			}
			
			
			System.out.println(sb.toString());
			
		}
		*/
	}
	
	
	public List<Integer> solution(int n) { 
		
		if (n < 3) {
			return Arrays.asList(0);
		}
		
		boolean[] planInAction = new boolean[n+1];
		int[] startDay = new int[2*n+1];
		
		for (int i = 1; i < startDay.length; i++) {
			startDay[i] = -1;
		}
		
		if (search(startDay, planInAction, 1))  {
			List<Integer> ret = new ArrayList<Integer>(2*n);
			for (int i = 0; i < 2*n; i++) { 
				ret.add(startDay[i+1]);
			}
			
			return ret;
			
		} else {
			return Arrays.asList(0);
		}
		
	}
	
	
	private boolean search(int[] startDay, boolean[] planInAction, int start) {
		
		int nslot = nextSlot(start, startDay);
		int nplan = nextPlan(1, planInAction);
		
		if (nslot == -1) return true;
		if (nplan == -1) return true;
		
		for (int i = nplan; i < planInAction.length; i++) {
			// find next nplan...
			if (!planInAction[i]) {
				int endDay = nslot+i+1;
				
				if (endDay < startDay.length && startDay[nslot] == -1 && startDay[endDay] == -1) {
					 planInAction[i] = true;
					 startDay[nslot] = startDay[nslot+i+1] = i;
					 if (search(startDay, planInAction, nslot+1)) {
						 return true;
					 }
					 else {
						planInAction[i] = false;
						startDay[nslot] = startDay[nslot+i+1] = -1;
					 }
				 }
			}
		}
		
		// if we have enumerate all the possibility, then we will exit and return false
		return false;
	}

	
	private int nextSlot(int start, int[] map) {
		for (int i = start; i < map.length; i++) {
			if (map[i] == -1)
				return i;
		}
		
		return -1;
	}
	/*
	private int nextPlan(boolean[] plan) {
		for (int i = 0; i < plan.length; i++) {
			if (!plan[i]) { 
				return i;
			}
		}
		
		return -1;
	}
	*/
	
	private int nextPlan(int start, boolean[] plan) {
		for (int i = start; i < plan.length; i++) {
			if (!plan[i]) {
				return i;
			}
		}
		
		return -1;
	}
	
}
