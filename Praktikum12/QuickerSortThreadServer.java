import java.util.concurrent.ForkJoinPool;

public class QuickerSortThreadServer implements CommandExecutor {

    private String quickerSortTime(){
        int size = 1000000;

        int[] array = new int[size];
        for(int i = 0; i < size; ++i) array[i] = QuickerSortServer.rng.nextInt(size);
        ThreadQuickerSort sorter = new ThreadQuickerSort(array, 0, array.length-1);
        ForkJoinPool pool = new ForkJoinPool();
        long end, start = System.currentTimeMillis();
        pool.invoke(sorter);
        end = System.currentTimeMillis();
        return " Zeit: " + (end - start) + "\n";
    }

    @Override
    public String execute(String command) throws Exception {
        return quickerSortTime();
    }
}
