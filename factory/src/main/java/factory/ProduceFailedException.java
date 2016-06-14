package factory;


public class ProduceFailedException extends RuntimeException {

   public ProduceFailedException(String errorMessage){
      super(errorMessage);
   }
}
