package filesystem;
import be.kuleuven.cs.som.annotate.*;

import java.util.Date;

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
 * @author  Mark Dreesen
 * @author  Tommy Messelis
 * @version 3.1
 * 
 * @note		See Coding Rule 48 for more info on the encapsulation of class invariants.
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
     * @effect	The type of the new file is set to the give type
     * 			| setType(type)
     * @effect	The directory of the file is set to the root
     * 			| setDirectory(Directory.getRoot())
     * @post    
     */
	public File(String name, int size, boolean writable, Type type) {
		super(name, size, writable);
        setType(type);
        setDirectory(Directory.getRoot());
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
    	if (!isValidType())	{
    		throw new IllegalArgumentException("Not a valid type of file!");
    	} else {
    	this.type = type;
    	}
    }

    
    
}
