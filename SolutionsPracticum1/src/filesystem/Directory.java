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
		this(getRootFolder(), name, writable);
	}
	
	
	/**
	 * Initialize a new directory with given name.
	 * @param 	name
	 * 		  	The name of the new directory. 
     * @effect  The new directory is initialized as a root directory with a given name and is writable
     * 			| this(getRoot(), name, writable);
	 */
	public Directory(String name) {		
		this(getRootFolder(), name, true);
		
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
        if (dir != null) {
        	setDirectory(dir);
			dir.addToList(this);
        }
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
     * Return the fileList of this object
     */
    public ArrayList<FileObject> getFileList()	{
    	return this.fileList;
    }
    
    
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
		setModificationTime();
    		if (hasAsItem(FileObject)) {
    			throw new AlreadyInListException(FileObject);
    		}else {
    			getFileList().add(getPlace(FileObject.getName()) + 1,FileObject);
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
    		return getFileList().get(i).getName(); 
    }
    
 
    /** 
     * Perform a binary search on the arrayList
     * 
     * @param	fileName
     * 			The name of the file
     * 
     * @return	The index of the file if it contains a file with fileName as name
     * 			(indexed according to Java, NOT according to practicum2)
     * 
     * @throw	FileNotFoundException
     * 			The file with the given fileName does appear in the fileList
     * 			| for each file in getFileList(): file.getName() != fileName
     */
    
    protected int binarySearch(String fileName) throws FileNotFoundException	{
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
     * 			(Indexed according to java)
     */
    protected int getPlace(String fileName) throws AlreadyInListException {
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
    			throw new AlreadyInListException(getItemAt(middle + 1));
    		}
    	}
    	if (right<0 || left >= getNbItems())	{
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
	 * Returns the FileObject linked to a given index. 
	 * @param	index
	 * 			The index number according to practicum2. 
	 * @post	The index must be valid.
	 * 			|  index >= 0 && index <= getFileList().size())
	 * @return	the file or directory linked to the given index. 
	 * 			
	 * @throws	IllegalArgumentException
	 * 			The given index is out of range
	 * 			| index < 0 || index >= getFileList()
	 */
	public FileObject getItemAt(int index) throws IllegalArgumentException {
		index -= 1;
		if (index >= 0 && index < getNbItems()) {
			return getFileList().get(index);
		}else {
			throw new IllegalArgumentException("Index out of range.");
			}
	}
	/**
	 * Return the item with the given name
	 * @param	fileName
	 * 			The name of the file we're looking for
	 * @return	The item with the given name
	 * 			| result.getName() == fileName
	 */
	
	public FileObject getItem(String fileName)	{
		return getItemAt(binarySearch(fileName) + 1);
	}
	
	/**
	 * Check whether this directory or any of its subdirectories contains the given FileObject
	 * 
	 * @return	True if the superdirectory of this file or directory is an (in)direct subdirectory of 
	 * 			this directory
	 * 			| this.isDirectOrIndirectSubdirectoryOf(fileObj.getDirectory())
	 */
	
	public boolean exists(FileObject fileObj)	{
		return this.isDirectOrIndirectSubdirectoryOf(fileObj.getDirectory());
		
	}
	

    /**
     * returns the index of a FileObject. 
     * @param	FO
     * 			The FiloObject 
     * @return	The index of the Fo as specified in practicum2 (starts at 1)
     */
    public int getIndexOf(FileObject FO) {
    		return getFileList().indexOf(FO) + 1; 
    }
	
    /**
     * Returns the number of items in the directory. 
     * @return	the size of our file list
     * 			| getFileList().size()
     */
    public int getNbItems() {
    		return getFileList().size();
    }
    
    /** 
     * checks if a FileObject already exist in the arraylist. 
     * @param 	FileObject
     * 			A File or Directory that is searched for in the arraylist.
     * 
     * @return	true when the FileObject is in the arraylist, false otherwise. 
     * 			| arraylist.contains(FileObject)
     */
    public boolean hasAsItem(FileObject FileObject) { /* Gebruik has as item! */
    		return getFileList().contains(FileObject);
    }
    
    /**
     * Checks if a given directory is a subdirectory or indirect subdirectory of this subdirectory
     * 
     * @param	dir
     * 			The given directory
     * @return	True if no directory contains a directory equal to the directory
     * 			
     */
	public boolean isDirectOrIndirectSubdirectoryOf(Directory dir) {
		if (dir.isEmpty())	{
			return false;
		}
		for (FileObject fileObject : dir.getFileList())	{
			if (fileObject instanceof Directory)	{
				if (fileObject == this || isDirectOrIndirectSubdirectoryOf((Directory) fileObject))	{
					return true;
				}
			}
		}
		return false;
	}
	
    /**
     * Checks whether this directory can be moved to the given directory
     * 
     * @param	dir
     * 			the directory to where we are moving
     * @return	True if the directory does not contain itself
     * 			| (!dir.exists(dir))
     * @throws	LoopException
     * 			Moving the directory results in a loop, in other words, dir is a subdirectory of this
     * 			| !(dir.isDirectOrIndirectSubdirectoryOf())
     */
	
	public boolean canMoveDirectoryTo(Directory dir) throws LoopException	{
		if (dir == null)	{
			return false;
		}
		if (dir.isDirectOrIndirectSubdirectoryOf(this))	{
			throw new LoopException(dir);
		}
		if (!this.isWritable())	{
			return false;
		}
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
		if (!this.isWritable())	{
			return false;
		}
		return this.isEmpty();
	}
	
	/**
	 * Check if the directory is empty
	 * @return	True if and only if the fileList is empty
	 * 			| fileList.isEmpty()
	 */
	public boolean isEmpty() {
		return getFileList().isEmpty();
	}
	
	/**
	 * Remove the given FileObject from this directory (this does not change the dir set
	 * for this fileObject)
	 * 
	 * @param	FileObject
	 * 			The FileObject to be removed
	 * @post	The FileObject is no longer in this directory
	 * 			| !(hasAsItem(FileObject)
	 */
	public void removeObject(FileObject fileObj)	{
		setModificationTime();
		getFileList().remove(fileObj);
	}
	
	/**
	 * Remove all objects (the right way would probably involve fileObject.remove(), but java yells at me
	 * if I change the list while iterating over it by throwing ConcurrentModificationException)
	 * 
	 * 
	 * @post	This directory is empty
	 * 			| new.isEmpty()
	 */
	public void removeAllObjects()	{
		setModificationTime();
		getFileList().clear();
	}
	
	
	

	/**
	 * Checks if the given directory is a valid directory
	 * @param 	directory
	 * @return	
	 */

	public static boolean isValidDirectory(Directory directory) {
		return true;
		
	}
	

}
