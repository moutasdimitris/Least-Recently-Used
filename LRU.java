import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
class LRU
{
    static int pageFaults(int pages[], int capacity)
    {
        HashSet<Integer> s = new HashSet<>(capacity);
        HashMap<Integer,Integer> indexes = new HashMap<>();
        int page_faults = 0;
        for (int i=0; i<pages.length; i++)
        {
            if (s.size() < capacity)
            {
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i],i);
            }
            else
            {
                if (!s.contains(pages[i]))
                {
                    int lru = Integer.MAX_VALUE;
                    int val=Integer.MIN_VALUE;
                    Iterator<Integer> it = s.iterator();
                    while (it.hasNext()) {
                        int temp = it.next();
                        if (indexes.get(temp) < lru)
                        {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    s.remove(val);
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
        }

        return page_faults;
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String args[])
    {
        /*
         * This is an array that holds the reference string for all
         * page requests.
         */
        int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2};

        // This is the number of available page frames
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println(faults);
    }
}