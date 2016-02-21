package simulation.microprocessor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;


/*
 * Problem ID: http://poj.org/problem?id=1049
 * 
 */
public class MicroprocessorSimSolution {

	/* =================== 
	 * Constants
	 *  =================== */
	
	// http://en.wikipedia.org/wiki/List_of_Java_keywords
	// Though reserved as a keyword in java, const is not used and has no function for defining constants in java, you can see the "final" reserved word instead
	// [Why is there no Constant keyword in Java? - Stack Overflow](http://stackoverflow.com/questions/2735736/why-is-there-no-constant-keyword-in-java)
	// 
	public static final int LD = 0;
	public static final int ST = 1;
	public static final int SWP = 2;
	public static final int ADD = 3;
	public static final int INC = 4;
	public static final int DEC = 5;
	public static final int BZ = 6;
	public static final int BR = 7;
	public static final int STP = 8;
	public static final int INVALID = -1;
	public static final int INVALID_MEMORY = -1;
	public static final int SIZE = 256;
	
	/* =================== 
	 * private fields
	 *  =================== */
	public volatile int A;
	public volatile int B;
	
	public volatile int _pointer;
	
	
	private final byte[] _memory;
	private boolean _stop = false;
	
	
	/* =================== 
	 * Constructor(s)
	 *  =================== */
	public MicroprocessorSimSolution() 	{
		_memory = new byte[256];
		for (int i = 0; i < 256; i++) { 
			_memory[i] = 0;
		}
	}
	
	public String dump() {
		StringBuilder sb = new StringBuilder();
		
		for (byte i : _memory)  
		{
			sb.append(Integer.toHexString(i).toUpperCase());
		}
		
		return sb.toString();
	}
	
	public void load() {
		// Use of the BufferedRead
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		int character = -1;
		try
		{
			int readp= -1;
			while ((character = reader.read()) != -1) { 
				_memory[++readp] = parseMemory(character);
			}
			
			assert readp == 255: "Invalid memory inputs"; // what does the java assert do.
		} catch (Exception ex) { 
			//
			ex.printStackTrace();
		}
	}
	
	public void loadFromString(String string) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(string.getBytes())));
		int character = -1;
		try {
			int readp = -1;
			while ((character = reader.read()) != -1) {
				_memory[++readp] = parseMemory(character);
			}
			
			assert readp == 255: this;
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
	}
	
	private byte parseMemory(int character) { 
		if (character >= '0' && character <= '9') { 
			return (byte)(character - '0');
		}
		
		if (character >= 'A' && character <= 'F') { 
			return (byte)(character - 'A' + 10) ;
		}
		
		return INVALID_MEMORY;
	}
	
	public void processLoad() 	{
		A = readAddress(readNext(), readNext());
	}
	
	public void processStore()	{
		writeAddress(readNext(), readNext(), A);
	}
	
	public void processSwap()	{
		int temp = A;
		A = B;
		B = temp;
	}
	
	public void processAdd()	{
		int tempA = A;
		int tempB = B;
		A = (tempA + tempB) % 16;
		B = (tempA + tempB) / 16;
	}
	
	public void processInc()	{
		A = (A + 1) % 16;
	}
	
	public void processDec() {
		A = (A + 15) % 16;
	}
	
	
	public void processBz() {
		byte hi = readNext();
		byte low = readNext();
		if (A == 0) { 
			_pointer = readAddress(hi, low);
		}
	}
	
	public void processBr() { 
		byte hi = readNext();
		byte low = readNext();
		_pointer = readAddress(hi, low);
	}
	
	public void processStop() { 
		_pointer = -1;
		_stop = true;
	}
	
	public int address(byte hi, byte low) {
		return hi * 16 + low;
	}
	
	public byte readAddress(byte hi, byte low) {
		return _memory[address(hi, low)];
	}

	public void writeAddress(byte hi, byte low, int value) { 
		 _memory[address(hi, low)] = (byte) value;
	}
	
	private byte readNext() {
		return _memory[_pointer++];
	}
	
	public void execute() {
		while (!_stop) { 
			int code = nextInstruction();
			switch (code) { 
				case LD:
					processLoad();
					break;
				case ST:
					processStore();
					break;
				case SWP:
					processSwap();
					break;
				case ADD:
					processAdd();
					break;
				case INC:
					processInc();
					break;
				case DEC: 
					processDec();
					break;
				case BZ:
					processBz();
					break;
				case BR:
					processBr();
					break;
				case STP:
					processStop();
					break;
			}
		}
	}
	
	public int nextInstruction() { 
		return _memory[_pointer++];
	}
	
	
	
	/* =================== 
	 * Private helpers
	 *  =================== */
	
	
	public static void main(String[] args) { 
		MicroprocessorSimSolution solution = new MicroprocessorSimSolution();
//		solution.load();
		solution.loadFromString("0102011311321128FF0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		solution.execute();
		String memory = solution.dump();
		System.out.println(memory);
	}
}
