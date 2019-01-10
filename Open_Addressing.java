package A1;

import static A1.main.*;

import java.util.ArrayList;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        return ( (( A*key % (int)(Math.pow(2,w)) >> (w-r)) + i) % (int) Math.pow(2, r) );
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    	int slot, collisions = 0, inc = 0;
    	while(inc < Table.length)
    	{
    		slot = probe(key, inc);
    		if(Table[slot] < 0){
    			Table[slot] = key;
    			return collisions;
    			
    		}
    		else if(Table[slot] == key)
    		{
    			return collisions; 
    		}
    		else collisions++;
    		inc++;
    	}
    	
    	return collisions;
    	

    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    	int slot, collisions = 0;
        for(int i = 0; i < Table.length; i++)
        {
        	slot = probe(key, i);
        	if(Table[slot] == -1) return collisions;
        	if(Table[slot] == key)
        		{
        		 	Table[slot] = -2;
        		 	return collisions;
        		}
        	collisions++;
        }
    	
    	return collisions;
    }

}
