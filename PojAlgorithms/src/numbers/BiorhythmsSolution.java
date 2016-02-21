package numbers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/* this is the poj algorithm exercise questions. 
 * http://poj.org/problem?id=1006
 * the subject of this post is : Biorhythms
 * */

/*
still looking for the the best answer to this question, is it a match problem or it is some programming searching problems? 
//*/

public class BiorhythmsSolution {

	/* Constants */
	public final int PHYSICAL_CYCLE = 23;
	public final int EMOTIONAL_CYCLE = 28;
	public final int INTELLECTUAL_CYCLE = 33;
	
	public final int MAX_TRIPLE_PEAK_CYCLE = 21252;
	
	public BiorhythmsSolution() {
		
	}
	
	public TripleOffset readFromString(String input) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));
		int physical, emotional, intellectual, offset;
		String line;
		
		try {
			if ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				physical = Integer.parseInt(values[0]);
				emotional = Integer.parseInt(values[1]);
				intellectual = Integer.parseInt(values[2]);
				offset = Integer.parseInt(values[3]);
				return new TripleOffset(physical, emotional, intellectual, offset);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public int nextTriple(TripleOffset triple) { 
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println("main");
	}
	
	/* private class */
	class TripleOffset { 
		/* fields */
		private int physical_offset;
		private int emotional_offset;
		private int intellectual_offset;
		private int last_triple;
		
		public TripleOffset(int physical, int emotional, int intellectual, int last) {
			physical_offset = physical;
			emotional_offset = emotional;
			intellectual_offset = intellectual;
			last_triple = last;
		}
		
		public int getPhysicalOffset() { 
			return physical_offset;
		}
		
		public int getEmotionalOffset() { 
			return emotional_offset;
		}
		
		public int getIntellectualOffset() { 
			return intellectual_offset;
		}
		
		public int getLastTriple() {
			return last_triple;
		}
		
	}
}
