package cn.capitek.common.exception;

/**
 * 权限验证异常
 * @author xuxw
 */
public class PermissionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4095577632492170786L;

	public PermissionException(String msg){  
        super(msg);  
    }
}
