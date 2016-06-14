package factory;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class ProduceFailedException extends RuntimeException {

   public ProduceFailedException(String errorMessage){
      super(errorMessage);
   }
}
