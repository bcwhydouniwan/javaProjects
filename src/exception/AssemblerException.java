package exception;

/**
 * Created by wangdan16 on 2016/10/8.
 */
public class AssemblerException extends RuntimeException {
    public AssemblerException(String msg) {
        super(msg);
    }

    public AssemblerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AssemblerException(Throwable cause) {
        super(cause);
    }
}
