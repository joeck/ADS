import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;


public class StorageGeneration {
	public static StringBuffer log = new StringBuffer();
	private static List<Collectable> root;
	private static List<Collectable> youngHeap;
	private static List<Collectable> oldHeap;

	static {
		clear();
	}
   
	public static void clear() {
		root = new LinkedList<Collectable>();
		youngHeap = new LinkedList<Collectable>();
		oldHeap = new LinkedList<Collectable>();
	}

	/* add  root object */
	public static void addRoot(Collectable obj) {
		root.add(obj);
	}
   
	// create a collectable object of class cls
	public static Collectable _new(String cls, Object arg) {
		Collectable obj = null;
		try {
			// create an object and call constructor
			Constructor cst = Class.forName(cls).getConstructor(new Class[] { arg.getClass()});
			obj = (Collectable) cst.newInstance(new Object[] {arg});
			// add object to heap
			youngHeap.add(obj);
			log.append("New: " + obj.toString() + "\n");
		} catch (Exception ex) {
			log.append("error creating object " + cls + "\n");
		}
		return (Collectable) obj;
	}

	/* remove object from heap */
	public static void delete(Collectable obj) {
		if (youngHeap.remove(obj)) {
			log.append("Delete young heap: " + obj.toString() + "\n");
		} else if (oldHeap.remove(obj)) {
			log.append("Delete old heap: " + obj.toString() + "\n");
		}else {
			log.append(
					"error trying to delete not existing object " + obj.toString()
					+ "\n");
		}
	}
 
	/* get all root objects */
	public static Iterable<Collectable> getRoot() {
		return new LinkedList<Collectable>(root);
	}

	/* get heap */
	public static Iterable<Collectable> getYoungHeap() {
		return new LinkedList<Collectable>(youngHeap);
	}

	public static Iterable<Collectable> getOldHeap() {return new LinkedList<Collectable>(oldHeap);}
   
	/* get references to collectables of an object */
	public static Iterable<Collectable> getRefs(Collectable obj) {
		// get all fields of an object
		Field[] fields = obj.getClass().getFields();
		List<Collectable> fieldList = new LinkedList<Collectable>();
		for (int i = 0; i < fields.length; i++) {
			try {
				Object o = fields[i].get(obj);   
				if (o instanceof Collectable) {
					fieldList.add((Collectable) o);
				}
			} catch (Exception ex) {}
		}
		return fieldList;
	}  

	/* dump an iterator */
	public static void dump(String s, Iterable itr) {
		log.append(s); 
		for (Object o: itr) {
			log.append(" " + o.toString());
		}
		log.append("\n");
	}

	public static void dump(String s){
		log.append(s + "\n");
	}

	public static String getLog() {
		return log.toString();
	}
 
	private static void mark(Collectable cObject) {
		if(cObject == null || cObject.isMarked()) return;
		cObject.setMark(true);
		getRefs(cObject).forEach(StorageGeneration::mark);
	}

	private static void sweepYoungHeap() {
		for (Collectable object : getYoungHeap()){
			if (object.isMarked()){
				copyYoungToOld(object);
			} else {
				delete(object);
			}
		}
	}

	private static void copyYoungToOld(Collectable object){
		youngHeap.remove(object);
		object.setMark(false);
		oldHeap.add(object);
	}

	private static void sweepOldHeap(){
		for (Collectable object : getOldHeap()){
			if(object.isMarked()){
				object.setMark(false);
			} else {
				delete(object);
			}
		}
	}

	private static void sweep(){
		sweepOldHeap();
		sweepYoungHeap();
	}

	public static void gcYoungOnly(){
		log.append("Collector start young generation only\n");
		markAll();
		sweepYoungHeap();
		log.append("Collector end\n");
	}

	private static void markAll(){
		for (Collectable rootObject : getRoot()){
			mark(rootObject);
		}
	}

	public static void gc() {
		log.append("Collector start young and old generation\n");
		markAll();
		sweep();
		log.append("Collector end\n");
	}

}