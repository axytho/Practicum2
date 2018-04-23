package filesystem;

import java.util.ArrayList;



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
     * @effect  The new directory is initialized as a root directory with a given name and writability.
     * 			| this(getRoot(), name, writable);
	 */
	public Directory(String name,boolean writable) {
		this(getRoot(), name, writable);
	}
	
	
	/**
	 * Initialize a new directory with given name.
	 * @param 	name
	 * 		  	The name of the new directory. 
     * @effect  The new directory is initialized as a root directory with a given name and is writable
     * 			| this(getRoot(), name, writable);
	 */
	public Directory(String name) {		
		this(getRoot(), name, true);
		
	}
	/**
	 * Initialize a new directory within a given directory, with a name and writability. 
	 * @param 	dir
	 * 			The directory where the new directory should be created. 
	 * @param 	name
	 * 			The name of the new directory. 
	 * @param 	writable
	 * 			The writability of the new directory.
	 * @effect	This directory is moved to dir.
	 * 			| this.move(dir)
     * @effect 	The size is set to the default value 0. 
     * 			| super(name,0,writable)
	 */
	public Directory(Directory dir ,String name, boolean writable) {
		super(name, 0, writable);
		move(dir);
	}
	
	
	
	/**
	 * Initialize a new directory within a given directory, with a name and writability.
	 * @param 	dir
	 * 			The directory where the new directory should be created. 
	 * @param 	name
	 * 			The name of the new directory.
     * @effect  The new directory is initialized as a root directory with a given name and directory.
     * 			| this(dir, name,true)
	 */
	public Directory(Directory dir, String name) {
		this(dir, name,true);
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
    		if (FileObjectAlreadyInList(FileObject)) {
    			throw new AlreadyInListException(FileObject);
    		}else {
    			fileList.add(getPlace(FileObject.getName()),FileObject);
    			/* getPlace function makes sure the object is added at the right place */
    		}
    	
    }
    
    /* TODO: Delete this function!!!!!!!!!! AND CHANGE THE REFERENCE BACK TO addList */
    public void testAddToList(FileObject FileObject)	{
    	fileList.add(FileObject);
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
    			count = count + 1; 
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
     * @return	The index of the file if it contains a file with fileName as name
     * 
     * @throw	FileNotFoundException
     * 			The file with the given fileName does appear in the fileList
     * 			| for each file in fileList: file.getName() != fileName
     */
    
    public int binarySearch(String fileName) throws FileNotFoundException	{
    	int left = 0;
    	int right = getNbItems() - 1;
    	int middle = 0;
    	int direction = 0;
    	while (right >= left)	{
    		middle = (left + right)/2; /*because middle is an int, automatically computes the floor */
    		direction = fileName.compareTo(objectName(middle)); /* direction is not necessarily equal to -1, 0 or 1 */
    		if (direction > 0) {
    			left = middle + 1;
    		} else if (direction < 0) {
    			right = middle - 1;
    		}
    		else {
    			return middle;
    		}
    	}
    	/* The method returns unsuccessfully if it is not found */
    	throw new FileNotFoundException(fileName);	
    
    }
    
    /**
     * Find the correct place to add a new file
     * 
     * @param	fileName
     * 			The name of the file we're adding
     * 
     * @return	The index of the file that comes just before our given file, or -1 if our file must become
     * 			the first file in the index
     */
    public int binarySearchForAddition(String fileName) {
    	int left = 0;
    	int right = getNbItems() - 1;
    	int middle = 0;
    	int direction = 0;
    	while (right >= left)	{
    		middle = (left + right)/2; /*because middle is an int, automatically computes the floor */
    		direction = fileName.compareTo(objectName(middle)); /* direction is not necessarily equal to -1, 0 or 1 */
    		if (direction > 0) {
    			left = middle + 1;
    		} else if (direction < 0) {
    			right = middle - 1;
    		}
    		else {
    			return middle;
    		}
    	}
    	if (right<0)	{
    		assert(right == -1);
    		return right;
    	}
    	assert((fileName.compareTo(objectName(right)) > 0 ) && (fileName.compareTo(objectName(left)) < 0 ));
    	return right;
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
		return dir != null;
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
	
	public static void main(String [ ] args) {
		Directory test= new Directory("hoi");
		int i = test.getNbItems(); 
		System.out.println(i);
	}

}
