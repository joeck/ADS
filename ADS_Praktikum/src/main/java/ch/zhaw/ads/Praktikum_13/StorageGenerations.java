package ch.zhaw.ads.Praktikum_13;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class StorageGenerations {
	public static StringBuffer log = new StringBuffer();
	private static List<Collectable> root;
	private static List<Collectable> youngGenerationHeap;
	private static List<Collectable> oldGenerationHeap;
	private static int countCollector = 0;

	static {
		clear();
	}
   
	public static void clear() {
		root = new LinkedList<Collectable>();
		youngGenerationHeap = new LinkedList<Collectable>();
		oldGenerationHeap = new LinkedList<Collectable>();
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
			Constructor cst = Class.forName("ch.zhaw.ads.Praktikum_13." + cls).getConstructor(new Class[] { arg.getClass()});
			obj = (Collectable) cst.newInstance(new Object[] {arg});
			// add object to young generation heap
			youngGenerationHeap.add(obj);
			log.append("New: " + obj.toString() + "\n");
		} catch (Exception ex) {
			log.append("error creating object " + cls + "\n");
		}
		return (Collectable) obj;
	}

	/* remove object from young generation heap */
	public static void deleteYoungGenerationHeap(Collectable obj) {
		if (youngGenerationHeap.remove(obj)) {
			log.append("Delete young heap: " + obj.toString() + "\n");
		} else {
			log.append(
					"error trying to delete not existing object " + obj.toString()
					+ "\n");
		}
	}

	/* remove object from old generation heap */
	public static void deleteOldGenerationHeap(Collectable obj) {
		if (oldGenerationHeap.remove(obj)) {
			log.append("Delete old heap: " + obj.toString() + "\n");
		} else {
			log.append(
					"error trying to delete not existing object " + obj.toString()
							+ "\n");
		}
	}
 
	/* get all root objects */
	public static Iterable<Collectable> getRoot() {
		return new LinkedList<Collectable>(root);
	}

	/* get young generation heap */
	public static Iterable<Collectable> getYoungGenerationHeap() {
		return new LinkedList<Collectable>(youngGenerationHeap);
	}

	/* get old generation heap */
	public static Iterable<Collectable> getOldGenerationHeap() {
		return new LinkedList<Collectable>(oldGenerationHeap);
	}
   
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

	public static String getLog() {
		return log.toString();
	}
 
	private static void mark(Collectable cObject) {
		//if mark_bit(N) == unmarked
			//	mark_bit(N) = marked
			//	for all M in Children(N)
			// 		mark(M)
		CObject cObject1 = (CObject) cObject;
		if (!cObject1.isMarked()) {
			cObject1.setMark(true);
			if (cObject1.next != null) {
				mark(cObject1.next);
			} else if (cObject1.down != null) {
				mark(cObject1.down);
			}
		}
	}

	private static void sweepOldGeneration() {
		for (Collectable cObject : getOldGenerationHeap()) {
			if (!cObject.isMarked()) {
				deleteOldGenerationHeap(cObject);
			} else {
				cObject.setMark(false);
			}
		}
	}

	// Alle in young generations in den old generations verschieben
	private static void sweepYoungGeneration() {
		Iterator<Collectable> iteratorYoungGenerationHeap = youngGenerationHeap.iterator();
		while (iteratorYoungGenerationHeap.hasNext()) {
			Collectable collectable = iteratorYoungGenerationHeap.next();
			if (!collectable.isMarked()) {
				deleteYoungGenerationHeap(collectable);
			} else {
				collectable.setMark(false);
				oldGenerationHeap.add(collectable);
				iteratorYoungGenerationHeap.remove();
			}
		}
	}

	public static void gc() {
		for (Collectable collectable : getRoot()) {
			mark(collectable);
		}

		// Gerade
		if (countCollector % 2 == 0) {
			log.append("Collector start young generation only\n");

			sweepYoungGeneration();
		} else {
			// ungerade
			log.append("Collector start young and old generation\n");

			sweepYoungGeneration();
			sweepOldGeneration();
		}
		countCollector++;

		log.append("Collector end\n");
	}

}