package graph;

public class StockbrokerGrapevineSolution {
	public void readFromString(String input) { 

		
	}
	
	public Output solution() { 
		return new Output(-1, -1);
	}
	
	public static void main(String[] args) {
		String input = 
				 "3\n"
				+ "2 2 4 3 5\n"
				+ "2 1 2 3 6\n"
				+ "2 1 2 2 3\n"
				+ "5\n"
				+ "3 4 4 2 8 5 3\n" 
				+ "1 5 8\n"
				+ "4 1 6 4 10 2 7 5 2\n"
				+ "0\n" 
				+ "2 2 5 1 5\n"
				+ "0";
		
		
	}
	
	
	private class DagInput {
		/* infinite */
		private final int INFINITE = -1;
		
		/* private fields */
		private int _number;
		private int[][] _matrix;
		
		/* constructor */
		public DagInput(int number) { 
			_number = number;
			_matrix = new int[_number][];
			for (int i = 0; i < _number; i++) { 
				_matrix[i] = new int[_number];
				for (int j = 0; j < _number; j++) { 
					_matrix[i][j] = INFINITE;
				}
			}
		}
	}
	
	
	
	private class Output { 
		/* private fields */
		private int _person;
		private int _time;
		
		public Output(int person, int time) {
			_person = person;
			_time = time;
		}
		
		public void setPerson(int person) {
			_person = person;
		}
		
		public int getPerson() { 
			return _person;
		}
		
		public void setTime(int time) { 
			_time = time;
		}
		
		public int getTime() { 
			return _time;
		}
		
		
	}
}
