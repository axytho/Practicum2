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
     * Initialize a new file with given name, size and writability and a type.
     *
     * @param  	name
     *         	The name of the new file.
     * @param  	size
     *         	The size of the new file.
     * @param  	writable
     *         	The writability of the new file.
     * @param	type
     * 			The type of the new file.
     * @effect  The name of the file is set to the given name.
     * 			If the given name is not valid, a default name is set.
     *          | setName(name)
     * @effect	The size is set to the given size (must be valid)
     * 			| setSize(size)
     * @effect	The writability is set to the given flag
     * 			| setWritable(writable)
     * @post    The new creation time of this file is initialized to some time during
     *          constructor execution.
     *          | (new.getCreationTime().getTime() >= System.currentTimeMillis()) &&
     *          | (new.getCreationTime().getTime() <= (new System).currentTimeMillis())
     * @post    The new file has no time of last modification.
     *          | new.getModificationTime() == null
     */
	public File(String name, int size, boolean writable, Type type) {
		super(name, size, writable);
        setType(type);
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
     *  @post	The given type is registered as the new type
     *  		new.getType() == type
     */
    
    @Raw @Model public void setType(Type type)	{
    	this.type = type;
    }
    
    
}
