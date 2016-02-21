package Classes;

public class Constructors {
	public static void main(String[] args) {
		Tree tree1 = new Tree(); // create a new tree which is yet a seed
		Tree tree2 = new Tree(5);
		
		
		tree1.info();
		tree1.info("notice");
		
		
		// no constructor, why can we create object of class Bird??
		// reason: default constructor 
		Bird bird = new Bird();
		
//		Hat hat = new Hat();
		Banana a = new Banana();
		Banana b = new Banana();
		a.f(1);
		b.f(2);
	}
}


class Tree {
	int height;
	
	public Tree() {
		System.out.println("Plant a seed");
		height = 0;
	}
	
	public Tree(int i ) { 
		System.out.println("Create a new Tree that is " + i + "feet tall");
		height = i;
	}
	
	public void info() { 
		System.out.println("the tree is now " + height + "tall");
	}
	
	public void info(String s) {
		System.out.println(s + " the tree is now " + height + "tall");
	}
	
	
	
	/*
	public Tree() { 
		System.out.println("Planting a seed!");
	}
	
	public Tree(int i) {
		System.out.println("Create a new tree that is " + i  + "feet tall");
		height = i;
	}
	*/
}


// default constructor 
class Bird {
	int i;
}


// if you define a constructor 
class Hat {
	public Hat(int i) { }
	public Hat(double d) { }
}

class Banana {
	public void f(int i) { System.out.println(i); }
}
