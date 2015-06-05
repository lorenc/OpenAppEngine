package javax.cache;

public class CacheException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7181444391364408902L;

	public CacheException(Exception e) {
		super(e);
	}

}
