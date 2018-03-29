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
    ArrayList<FileObject> arraylist = new ArrayList<FileObject>();
    
    
    /**
     * Add a FileObject to the arraylist. when the FileObject alreadt exist in the arraylist an exception is thrown. 
     * @param FileObject
     * 					The FileObject that should be added. 
     * @effect The FileObject is added in the arraylist.
     * 		| arraylist.add(FileObject)
     * @throws AlreadyInListException
     * 			The FileObject already exist in the arraylist
     * 		| !FileObjectAlreadyInList(FileObject))
     * 			
     */
    public void addToList(FileObject FileObject) throws AlreadyInListException {
    	if (!FileObjectAlreadyInList(FileObject)) {
    		throw new AlreadyInListException(FileObject);
    	}else {
    		arraylist.add(FileObject);
    		Collections.sort(arraylist);
    		}
    	
    }
    
    
    /** 
     * checks if a FileObject already exist in the arraylist. 
     * @param FileObject
     * 		A File or Directory that is searched for in the arraylist.
     * @return
     * 		true when the FileObject is in the arraylist, false otherwhise. 
     * 		| arraylist.contains(FileObject) == true
     */
    public boolean FileObjectAlreadyInList(FileObject FileObject) {
    		if (arraylist.contains(FileObject) == true) {
    			return true;
    		}else {
    			return false; 
    		}
    }
    
    public static Comparator<FileObject> StuNameComparator = new Comparator<FileObject>() {

    	
    	/**
    	 * 
    	 */
	public int compare(FileObject FO1, FileObject FO2) {
	   String FileObjectName1 = FO1.getName().toUpperCase();
	   String FileObjectName2 = FO2.getName().toUpperCase();

	   return StudentName1.compareTo(StudentName2);
	   
    };
	
	/**
	 * Returns the FileObject linked to a given index. 
	 * @param index
	 * 			The index number. 
	 * @post
	 * @return
	 * 			The file or directory linked to the given index. 
	 */
	public FileObject getItemAt(int index) {
		
	}
		

	 /**
	  * @param dir
	  * @param file
	  */
	public void move(Directory dir, File file) {
		
	 }
	
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
