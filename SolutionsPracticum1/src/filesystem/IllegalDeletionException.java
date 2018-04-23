package filesystem;

import be.kuleuven.cs.som.annotate.*;

public class IllegalDeletionException extends RuntimeException {
	

	/**
	 * Required because this class inherits from Exception
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Initialize the new exception if the given file cannot be deleted
	 */
	@Raw
	public IllegalDeletionException()	{
	}

}
