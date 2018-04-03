package filesystem;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of files.
 *
 * @invar	Each file must have a properly spelled name.
 * 			| isValidName(getName())
 * @invar	Each file must have a valid size.
 * 			| isValidSize(getSize())
 * @invar   Each file must have a valid creation time.
 *          | isValidCreationTime(getCreationTime())
 * @invar   Each file must have a valid modification time.
 *          | canHaveAsModificationTime(getModificationTime())
 * @author  Jonas Bertels
 * @author	Frederik Van Eecke
 * @version 3.1
 * 
 * @note		
 */
public class File extends FileObject {
	
    /**********************************************************
     * Constructors (part 2 - the expansion)
     **********************************************************/
    /**
     * Initialize a new file with given name, size and writability and a type which sits in the root folder.
     *
     * @param  	name
     *         	The name of the new file.
     * @param  	size
     *         	The size of the new file.
     * @param  	writable
     *         	The writability of the new file.
     * @param	type
     * 			The type of the new file.
     * @effect  The new file is initialized as a FileObject with the given name
     * 			size and writability.
     * 			| super(name, size, writability)
     * @effect	The type of the new file is set to the given type
     * 			| setType(type)

     */
	
	public File(String name, int size, boolean writable, Type type) {
		this(getRoot(), name, size, writable, type);
		
	}
	
	/**
	 * 	Initialize a new empty, writable file with a given name and type in the root folder
	 * 
	 * @param	name
	 * 			The name of the new file.
	 * @param	type
	 * 			The type of the new file.
	 * @effect	a new empty, writable file with a given name and type in the root folder is initialized
	 * 			| this(name, 0, true, type)
	 */
	public File(String name, Type type) {
		this(getRoot(), name, 0, true, type);
	}
	
	/**
	 * Initialize a new file with given name, size and writability and a type which sits in root.
     * @param  	name
     *         	The name of the new file.
     * @param  	size
     *         	The size of the new file.
     * @param  	writable
     *         	The writability of the new file.
     * @param	type
     * 			The type of the new file.
     * @param	dir
     * 			The directory in which the file resides.
     * @effect	a new file with given name, size and writability and a type which sits in root is initialized
     * 			| this(name, size, writable, type)
     * @effect	The directory of the file is set to the given directory
     * 			| setDirectory(dir)
	 */

	public File(Directory dir, String name, int size, boolean writable, Type type) {
		super(name, size, writable);
        setType(type);
        move(dir);
    }
	/**
	 * Initializes a new file with a given directory, name and type which is writable and empty.
	 * 
     * @param	dir
     * 			The directory in which the file resides.
     * @param  	name
     *         	The name of the new file.
     * @param	type
     * 			The type of the new file.
     * @effect	A new file with a given directory, name and type which is writable and empty is initialized
     * 			| this(dir, name, 0, true, type)
	 */
	
	public File(Directory dir, String name, Type type)	{
		this(dir, name, 0, true, type);
	}
	
	

    /**********************************************************
     * deletion - defensive programming
     **********************************************************/  
	
	/**
	 * Check if the file can be deleted
	 * @param 	file
	 * 			The directory we're checking
	 * @return	True
	 */
    /* TODO:abstract or static? */	
	public boolean canBeDeleted()	{
		return true;
	}
    
    /**********************************************************
     * type - defensive programming
     **********************************************************/    
    
    /**
     * The type of the file
     */
    private Type type;
    
    /**
     * Return the type of the file
     */
    
    @Raw @Basic public Type getType() {
    	return this.type;
    }
    
    /**
     * Checks whether the type of the file is valid
     * 
     * @param	type
     * 			The type of the file
     * @return	True if and only if the type is not null
     * 			| (type != null)
     */
    public static boolean isValidType(Type type) {
    	return (type != null);
    }
    
    /**
     * Set the type of the file to the given type
     * 
     * @param	type
     *  		The new type
     * @post	The given type is registered as the new type
     *  		new.getType() == type
     * @throws	IllegalArgumentException
     * 			The type is not a valid type
     * 			| !isValidType()
     */
    
    @Raw @Model public void setType(Type type)	{
    	if (!isValidType(type))	{
    		throw new IllegalArgumentException("Not a valid type of file!");
    	} else {
    	this.type = type;
    	}
    }
    
    /**********************************************************
     * Moving - defensive programming
     **********************************************************/  
    
    /**
     * Check whether we can move a file to a certain directory
     * 
     * @param	The directory to which we are moving the file
     * 
     * @return	True
     */
    
    
	public boolean canMoveDirectoryTo(Directory dir)	{
		return true;
	}

    
    
}
