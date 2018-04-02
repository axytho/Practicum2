package filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;



/**
 * A class of directories.
 *
 * @invar	Each directory must have a properly spelled name.
 * 			| isValidName(getName())

 *
 * @author Jonas
 * @author frederik
 */

public class Directory extends FileObject {

/******************************************
 * constructors
 ******************************************
 */
	/**
	 * Initialize a new directory with given name and writability.
	 * @param 	name
	 * 		  	The  name of the new directory.			
	 * @param 	writable
	 * 		  	The writability of the new directory.
     * @effect  The name of the directory is set to the given name.
     * 			If the given name is not valid, a default name is set.
     *          | setName(name)
     * @effect	The writability is set to the given flag
     * 			| setWritable(writable)
     * @effect	The new directory has a size of 0. 
     * 			| super(name,0,writable);
     * 		
	 */
	public Directory(String name,boolean writable) {
		super(name,0,writable);
		makeRoot();
	}
	
	
	/**
	 * Initialize a new directory with given name.
	 * @param 	name
	 * 		  	The name of the new directory. 
	 * @effect 	The name of the directory is set to the given name.
	 * 		   	If the given name is not valid, a default name is set. 
	 * 		   	| setName(name)
	 * @effect 	the new directory has a size of 0 and is writable. 
	 * 		   	| super(name,0,true);
	 */
	public Directory(String name) {
		this(name,true);
		makeRoot();
	}
	/**
	 * Initialize a new directory within a given directory, with a name and writability. 
	 * @param 	dir
	 * 			The directory where the new directory should be created. 
	 * @param 	name
	 * 			The name of the new directory. 
	 * @param 	writable
	 * 			The writability of the new directory.
	 * @effect	a new directory is created within the directory dir. 
	 * @effect 	The name of the directory is set to the given name.
	 * 		   	If the given name is not valid, a default name is set. 
	 * 		   	| setName(name)
	 * @effect 	The writability is set to the given flag
     * 			| setWritable(writable)
     * @effect 	The size is set to the default value 0. 
     * 			| super(name,0,writable)
	 */
	public Directory(Directory dir ,String name, boolean writable) {
		this(name,writable);
		setDirectory(dir); 
	}
	
	
	
	/**
	 * Initialize a new directory within a given directory, with a name and writability.
	 * @param 	dir
	 * 			The directory where the new directory should be created. 
	 * @param 	name
	 * 			The name of the new directory.
	 * @effect 	a new directory is created within the directory dir. 
	 * @effect 	The name of the directory is set to the given name.
	 * 		   	If the given name is not valid, a default name is set. 
	 * 		   	| setName(name)
     * @effect 	The size is set to the default value 0 and the directory is writable. 
     * 			| super(name,0,writable)
	 */
	public Directory(Directory dir, String name) {
		this(name,true);
		setDirectory(dir);
	}
	

	

	
	/******************************************
	 * Methods 
	******************************************* 
	*/
	
	   /**
     * A list of FileObjects, can be Directory or File.
     */
    ArrayList<FileObject> fileList = new ArrayList<FileObject>();
    
    
    /**
     * Add a FileObject to the arraylist. when the FileObject already exists in the arraylist an exception is thrown. 
     * @param 	FileObject
     * 			The FileObject that should be added. 
     * @effect	The FileObject is added in the arraylist.
     * 			| arraylist.add(FileObject)
     * @throws 	AlreadyInListException
     * 			The FileObject already exist in the arraylist
     * 			| !FileObjectAlreadyInList(FileObject))
     * 			
     */
    public void addToList(FileObject FileObject) throws AlreadyInListException {
    		if (!FileObjectAlreadyInList(FileObject)) {
    			throw new AlreadyInListException(FileObject);
    		}else {
    			fileList.add(getPlace(FileObject.getName()),FileObject);
    			/* getPlace function makes sure the object is added at the right place */
    		}
    	
    }
    
    /**
     * returns the name of the object at index i 
     * @param	i 
     * 			The index that indicates wich elements name from the arraylist should be returned. 
     * @return	The name of the object at index i.			
     */
    private String objectName(int i) {
    		return fileList.get(i).getName(); 
    }
    
    
    /**
     * Determines the place where a new object should be added in the arraylist. 
     * @param	name
     * 
     * @return	The index of the position where the new object should be added.
     * 
     * @throws	IllegalArgumentException	
     * 			A new object with a name that is identical with the name of an object from the arraylist 
     * 			cannot be aded. To determine if two names are the same we convert them both to lowercase. 
     * 			| newName.compareTo(objectName(i).toLowerCase()) == 0
     * 			
     */
    private int getPlace(String name) throws IllegalArgumentException{
    		String newName = name.toLowerCase(); 
    		int count = 0; 
    		for (int i = 0; i < fileList.size(); i++) {
    			count = count + 1;  /*  The way physicists handle these problems */ 
    			if (newName.compareTo(objectName(i).toLowerCase()) == 0) {
    				throw new IllegalArgumentException("Identical name");
    			}
    			if (newName.compareTo(objectName(i).toLowerCase()) < 0) {
    				break;
    			}
    		}
    		return count; 
    }
    /** 
     * Perform a binary search on the arrayList
     * 
     * @param	fileName
     * 			The name of the file
     * 
     * @return	The index of the file if it contains a file with fileName as name, else the index of the 
     * 			the file name lexographically higher than this fileName, or, if the fileName is lexographically
     * 			greater than all other file names, getNbItems()
     * 
     */
    
    private int binarySearch(String fileName)	{
    int x = getNbItems();
    int position = x - 1; /* start on the last element */
    int direction = -1;
    	while (x > 1)	{
    		x = (x + 1)/ 2; /* since x is an int, only the floor of the quotient is returned */
    		if (direction < 0)	{
    			position -= x - 1;
    		} else {
    			position += x - 1;
    		}
    		direction = fileName.compareTo(objectName(x)); /* direction is not necessarily equal to -1, 0 or 1 */
    		if (direction == 0)	{
    			return position;
    		}
    	}
    	return position;
    }
    
    
    
    
    
    
    

    /**
     * Inserts a FileObject in this directory.
     * 
     * @param	FO		
     *			The fileobject 
     * @effect	The fileobject is added to the arraylist and the directory is set to this directory. 
     */
    protected void insert(FileObject FO) {
    		this.addToList(FO);
    		FO.setDirectory(this);
    }
    
    
    /**
     * Returns the number of items in the directory. 
     * @return
     */
    public int getNbItems() {
    		return fileList.size();
    }
    
    /**
     * returns the index of a FileObject. 
     * @param	FO
     * 			The FiloObject 
     * @return	The index of the Fo 
     */
    public int getIndexOf(FileObject FO) {
    		return fileList.indexOf(FO); 
    }
    
  
    /** 
     * checks if a FileObject already exist in the arraylist. 
     * @param 	FileObject
     * 			A File or Directory that is searched for in the arraylist.
     * 
     * @return	true when the FileObject is in the arraylist, false otherwise. 
     * 			| arraylist.contains(FileObject)
     */
    public boolean FileObjectAlreadyInList(FileObject FileObject) { /* Gebruik has as item! */
    		return fileList.contains(FileObject);
    }
    
    

    
	
	/**
	 * Returns the FileObject linked to a given index. 
	 * @param	index
	 * 			The index number. 
	 * @post		The index must be valid.
	 * 			|  index >= 0 && index <= fileList.size())
	 * @return	he file or directory linked to the given index. 
	 */
	public FileObject getItemAt(int index) throws IllegalArgumentException {
		if (index >= 0 && index <= fileList.size()) {
			return fileList.get(index);
		}else {
			throw new IllegalArgumentException("Index out of range.");
			}
	}
	
	
	
    /**
     * Checks whether this directory can be moved to the given directory
     * 
     * @param	dir
     * 			the directory to where we are moving
     * @return	True if the directory does not contain itself
     * 			| (!dir.exists(dir))
     */
	
	public boolean canMoveDirectoryTo(Directory dir)	{
		/* change to exists */
		// return this.exists(dir);
		return true;
	}
	
    /**********************************************************
     * deletion - defensive programming
     **********************************************************/  
	
	/**
	 * Check if the directory can be deleted
	 * @param 	dir
	 * 			The directory we're checking
	 * @return	True if and only if the directory is empty
	 * 			| isEmpty(dir)
	 */
	
	public boolean canBeDeleted()	{
		return this.isEmpty();
	}
	
	/**
	 * Check if the directory is empty
	 * @return	True if and only if the fileList is empty
	 * 			| fileList.isEmpty()
	 */
	public boolean isEmpty() {
		return fileList.isEmpty();
	}
	
	
	

	/**
	 * Checks if the given directory is a valid directory
	 * @param 	directory
	 * @return	
	 */

	public static boolean isValidDirectory(Directory directory) {
		return true;
		/* TODO: Change this! */
	}
	


}
