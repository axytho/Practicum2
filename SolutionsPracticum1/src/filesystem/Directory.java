package filesystem;

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
	}
	
	
	/**
	 * Initialize a new directory with given name.
	 * @param 	name
	 * 		  	The name of the new directory. 
	 * @effect 	The name of the directory is set to the given name.
	 * 		   	If the given name is not valid, a default name is set. 
	 * 		   	| setName(name)
	 * @effect 	the new directory has a size of 0 and is writable. 
	 * 		   	| super(name,0,writable);
	 */
	public Directory(String name) {
		super(name,0,true);

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
	public Directory(Directory dir,String name, boolean writable) {
		/* super(name,0,writable)
		 * 
		 */
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
	public Directory(Directory dir, String name){
		/* super(name,0,writable)
		 * 
		 */
	}
	
	private Set<File> item = new HashSet<File>();
	
/******************************************
 * Methods 
******************************************* 
*/
	
/**
 * 	
 * @param file
 */
 public void makeRoot(File file) {
	 
 }
	
 
 /**
  * 
  * @param dir
  * @param file
  */
public void move(Directory dir, File file) {
	
 }

/**
 * 
 * @param 	file
 * 			The file from witch the root should be returned. 
 * @return
 */
public File getRoot(File file) {
	return 
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
