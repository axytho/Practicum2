package filesystem;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw; 

/**
 * A class for signaling illegal attempts to add FileObjects to the arraylist that would cause a loop in the filesystem. 
 * 
 * @author 	Frederik
 * @author	Jonas 
 * @version	2.0 - 2015
 */

public class LoopException{
	/**
	 * Required because this class inherits from Exception
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable referencing the file to which change was denied.
	 */
	private final FileObject file;

	/**
	 * Initialize this new file not writable exception involving the
	 * given file.
	 * 
	 * @param	fileObject
	 * 			The file for the new file not writable exception.
	 * @post	The file involved in the new file not writable exception
	 * 			is set to the given file.
	 * 			| new.getFile() == file
	 */
	@Raw
	public LoopException(FileObject fileObject) {
		this.file = fileObject;
	}
	
	/**
	 * Return the file involved in this file not writable exception.
	 */
	@Raw @Basic
	public FileObject getFile() {
		return file;
	}
	
	
}