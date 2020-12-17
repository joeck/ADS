package ch.zhaw.ads.Praktikum_13;

import ch.zhaw.ads.CommandExecutor;

import java.util.Collections;
import java.util.Iterator;

public class TestCObjectServerGenerations implements CommandExecutor {

   private static CObject new_CObject(Object s) {
      CObject obj = (CObject)StorageGenerations._new("CObject",s);
      return obj;
   }

   static CObject a;
   static CObject e;

   public String execute (String input) {
     run();
     return StorageGenerations.getLog();
   }


  private void run() {
     a = new_CObject("A");
     CObject b = new_CObject("B");
     CObject c = new_CObject("C");
     CObject d = new_CObject("D");
     e = new_CObject("E");
     CObject f = new_CObject("F");
     CObject g = new_CObject("G");
     CObject h = new_CObject("H");
     StorageGenerations.addRoot(a);
     StorageGenerations.addRoot(e);
     a.next = b; b.next = c; b.down = a; c.down = d;
     e.next = f; f.next = g; g.next = e;
     StorageGenerations.dump("\nRoots:", StorageGenerations.getRoot());
     StorageGenerations.dump("Young Heap 1:", StorageGenerations.getYoungGenerationHeap());
     StorageGenerations.dump("Old Heap 1:", StorageGenerations.getOldGenerationHeap());
     StorageGenerations.dump("", Collections.emptyList());

     StorageGenerations.gc();
     StorageGenerations.dump("Young Heap 2:", StorageGenerations.getYoungGenerationHeap());
     StorageGenerations.dump("Old Heap 2:", StorageGenerations.getOldGenerationHeap());
     StorageGenerations.dump("", Collections.emptyList());

     b.next = f;
     StorageGenerations.gc();
     StorageGenerations.dump("Young Heap 3:", StorageGenerations.getYoungGenerationHeap());
     StorageGenerations.dump("Old Heap 3:", StorageGenerations.getOldGenerationHeap());
     StorageGenerations.dump("", Collections.emptyList());

     f.next = null;
     StorageGenerations.gc();
     StorageGenerations.dump("Young Heap 4:", StorageGenerations.getYoungGenerationHeap());
     StorageGenerations.dump("Old Heap 4:", StorageGenerations.getOldGenerationHeap());
     StorageGenerations.dump("", Collections.emptyList());

     StorageGenerations.gc();
     StorageGenerations.dump("Young Heap 5:", StorageGenerations.getYoungGenerationHeap());
     StorageGenerations.dump("Old Heap 5:", StorageGenerations.getOldGenerationHeap());
     StorageGenerations.dump("", Collections.emptyList());
  }

}
