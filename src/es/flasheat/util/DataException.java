package es.flasheat.util;

/**
 * NO confundir esta capa con la capa "M"odelo en MVC !!!
 * que se usa en los diferentes tipos de clientes (web, app, desktop, etc.)
 */
@SuppressWarnings("serial")
public class DataException extends BusinessException {
       

	public DataException() {
		super();
	}
	
	public DataException(String message) {
		this(message,null);		
	}
	

	public DataException(Throwable cause) {
		this(null,cause);		
	}
	
	public DataException(String message, Throwable cause) {
		super(message,cause);		
	}
    
}