package AssignmentHashing;


public class HashTable{
	
	String[] Table;
	int number;
	double loadfactor;
	int size;
	int collision;
	
	public HashTable(){
		this.size = 7;
		Table = new String[size];
		loadfactor = 0;
		number = 0;
		collision = 0;
	}
	
	public void insert(String s){
		
		for(int i=0;i<size;i++){
			if(Table[i]==s){
				System.out.println("It is a dublicate entry\n");
				return;
			}
		}
		number = number+1;
		loadfactor = (double)number/size;
		if(loadfactor>=0.5){
			update();
		}
		put(s);
		System.out.println("The inserted string is " + s );
		System.out.println("The number of collisions after adding " + s + " is " + collision+"\n" );
	}
	
	public void put(String s){
		int i = getHash(s);
		if(Table[i] == null){
			Table[i] = s;
		}
		else{
			collision = collision +1;
			for(int j=0;j<size;j++){
				if(Table[((i+j)%size)]==null){
					Table[((i+j)%size)] = s;
					break;
				}
				else{
					collision = collision+1;
				}
			}
		}
	}
	
	
	private void update(){
		size = nextPrime(size*2);
		rehashTable();
	}
	
	public void rehashTable(){
		String temp[] = Table;
		Table = new String[size];
		for(int i=0;i<(temp.length);i++){
			if(temp[i]!=null){
				put(temp[i]);
			}
		}
	}
	
	private int getHash(String s){
		if(s!=null){
			return(Math.abs(s.hashCode())%size);
		}
		else{
			return -1;
		}
	}
	
	public int nextPrime(int length){
		length++;
		for(int i=2;i<length;i++){
			if(length%i==0){
				length = length+1;
				i=2;
			}
			else{
				continue;
			}
		}
		return length;
	}
	public int find(String s){
		int x = getHash(s);
		while(Table[x]!=null){
			if(Table[x].equals(s)){
				break;
			}
			x = x+1;
		}
		if(Table[x] ==null){
			return -1;
		}
		return x;
	}
	
	public void delete(String s){
		int r = find(s);
		if(r==-1){
			System.out.println("The string is not present\n");
		}
		else{
			System.out.println("The string to be deleted is at index " + r+ "\n");
		
		    while(getHash(Table[r])==getHash(Table[(r+1)%size])){
			    Table[r] = Table[(r+1)%size];
			    r = (r+1) % size;
		   }
		Table[r] = null;
		number = number-1;
		}
	}
	
	public static void main(String[] args){
		
		// Adding 15 strings in the hashtable
		HashTable hashtable = new HashTable();
		hashtable.insert("sunnyday");
		hashtable.insert("weather");
		hashtable.insert("rainyday");
		hashtable.insert("snowfall");
		hashtable.insert("scorching");
		hashtable.insert("utdallas");
		hashtable.insert("computer");
		hashtable.insert("harddisc");
		hashtable.insert("scientific");
		hashtable.insert("sunny");
		hashtable.insert("desktop");
		hashtable.insert("lenova");
		hashtable.insert("google");
		hashtable.insert("apple");
		hashtable.insert("iphonex");
		
		//searching for snowfall
		int i = hashtable.find("snowfall");
		if(i==-1){
			System.out.println("The string is not found/n");
		}
		else{System.out.println("The string is found at index "+i + "\n");}
		
		//searching for pixel2
		int next = hashtable.find("pixel2");
		if(next==-1){
			System.out.println("The string is not found\n");
		}
		else{System.out.println("The string is found at index "+i+"\n");}
		
		//First searching for a string and then deleting it
		//searching for weather
			int next1 = hashtable.find("weather");
			if(next1==-1){
				System.out.println("The string is not found\n");
			}
			else{System.out.println("The string is found at index "+next1+"\n");}
		//Deleting weather
			hashtable.delete("weather");
		//searching for weather
			int next2 = hashtable.find("weather");
			if(next2==-1){
				System.out.println("The string is not found\n");
			}
			else{System.out.println("The string is found at index "+i+"\n");}
		
		//Size of hash table
		    int next3 = hashtable.size;
		    System.out.println("HashTable size after adding 15 elements is "+next3+"\n");
	}
}
