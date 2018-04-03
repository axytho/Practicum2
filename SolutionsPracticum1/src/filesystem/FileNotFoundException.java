package filesystem;

import be.kuleuven.cs.som.annotate.*;

public class FileNotFoundException extends RuntimeException {
	
	/**
	 * Required because this class inherits from Exception
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable referencing the file which has not been found
	 */
	
	private final String fileName;

	/**
	 * Initialize the new exception if the given file is not found
	 * 
	 * @param	file
	 * 			The file which is not found
	 * 
	 * @post	The file which is not found is set to the given file
	 * 			| new.getFile() == file
	 */
	@Raw
	public FileNotFoundException(String file)	{
		this.fileName = file;
	}
	
	/**
	 * Return the file which was not found
	 */
	@Raw @Basic
	public String getFile() {
		return fileName;
	}
}




