package filesystem;
import be.kuleuven.cs.som.annotate.*; 

/**
 * A class for signaling illegal attempts to add FileObjects to an arraylist.
 * 
 * @author 	Tommy Messelis
 * @version	2.0 - 2015
 */

public class AlreadyInListException extends RuntimeException {

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
	public AlreadyInListException(FileObject fileObject) {
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