package by.bsuir.verkpavel.adb.shared;

import java.io.Serializable;
import java.util.HashMap;

public class OperationList implements Serializable{
    private static final long serialVersionUID = -3778178975098893481L;
    
    private HashMap<OperationType, Object> store = new HashMap<>();
    
    public void addOperation(OperationType description, Object value){
        store.put(description, value);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getOperation(OperationType description){
        return (T)store.get(description);
    }
    
    public void removeOperation(OperationType description){
        store.remove(description);
    }
    
    public void clearList(){
        store.clear();
    }
}
