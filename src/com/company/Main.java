package com.company;
import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // if a file is provided, run KK on the file
        if(args.length > 0) {
            String inputFile = args[0];

            try {
                File file = new File(inputFile);
                Scanner in = new Scanner(file);

                long[] sequence = new long[100];

                for (int i = 0; i < 100; i++) {
                    sequence[i] = Long.parseLong(in.nextLine());
                }

                long kk = KarmarkarKarp.runKarmarkarKarp2(sequence);
                StandardSolution standard = new StandardSolution(sequence);
                Prepartitioning prepartitioning = new Prepartitioning(sequence);

                System.out.println("Karmarkar-Karp:                     " + kk);

                System.out.println("Standard Repeated Random:           " + standard.repeatedRandom());
                System.out.println("Standard Hill Climbing:             " + standard.hillClimbing());
                System.out.println("Standard Simulated Annealing:       " + standard.simulatedAnnealing());

                System.out.println("Prepartitioing Repeated Random:     " + prepartitioning.repeatedRandom());
                System.out.println("Prepartitioing Climbing:            " + prepartitioning.hillClimbing());
                System.out.println("Prepartitioing Simulated Annealing: " + prepartitioning.simulatedAnnealing());


            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // otherwise generate 50 random instances, and run all 7
        else {

            for (int i = 0; i < 100; i++) {
                System.out.println(RandomNumberGenerator.randomLongBelow10tothe12());
            }

            // write your code here
            long[][] sequences = new long[50][100];
            long[][] times = new long[7][50];

            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 100; j++) {
                    sequences[i][j] = RandomNumberGenerator.randomLongBelow10tothe12();
                }
            }

            System.out.println("");
            System.out.println("Karmarkar Karp");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                System.out.println(KarmarkarKarp.runKarmarkarKarp2(sequences[i]));
                long end = System.currentTimeMillis();
                times[0][i] = start - end;
            }

            System.out.println("");
            System.out.println("Standard Solution Repeated Random");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                StandardSolution standard = new StandardSolution(sequences[i]);
                System.out.println(standard.repeatedRandom());
                long end = System.currentTimeMillis();
                times[1][i] = start - end;
            }

            System.out.println("");
            System.out.println("Standard Solution Hill Climbing");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                StandardSolution standard = new StandardSolution(sequences[i]);
                System.out.println(standard.hillClimbing());
                long end = System.currentTimeMillis();
                times[2][i] = start - end;
            }

            System.out.println("");
            System.out.println("Standard Solution Simulated Annealing");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                StandardSolution standard = new StandardSolution(sequences[i]);
                System.out.println(standard.simulatedAnnealing());
                long end = System.currentTimeMillis();
                times[3][i] = start - end;
            }

            System.out.println("");
            System.out.println("Prepartitioning Repeated Random");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                Prepartitioning prepartition = new Prepartitioning(sequences[i]);
                System.out.println(prepartition.repeatedRandom());
                long end = System.currentTimeMillis();
                times[4][i] = start - end;
            }

            System.out.println("");
            System.out.println("Prepartitioning Hill Climbing");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                Prepartitioning prepartition = new Prepartitioning(sequences[i]);
                System.out.println(prepartition.hillClimbing());
                long end = System.currentTimeMillis();
                times[5][i] = start - end;
            }

            System.out.println("");
            System.out.println("Prepartitioning Simulated Annealing");
            for (int i = 0; i < 50; i++) {
                long start = System.currentTimeMillis();
                Prepartitioning prepartition = new Prepartitioning(sequences[i]);
                System.out.println(prepartition.simulatedAnnealing());
                long end = System.currentTimeMillis();
                times[6][i] = start - end;
            }


            for (int i = 0; i < 7; i++) {
                switch (i) {
                    case 0:
                        System.out.println("Karmakar Karp Times");
                        break;
                    case 1:
                        System.out.println("Standard Repeated Random Times");
                        break;
                    case 2:
                        System.out.println("Standard Hill Climbing Times");
                        break;
                    case 3:
                        System.out.println("Standard Simulated Annealing Times");
                        break;
                    case 4:
                        System.out.println("Prepartitioning Repeated Random Times");
                        break;
                    case 5:
                        System.out.println("Prepartitioning Hill Climbing Times");
                        break;
                    default:
                        System.out.println("Prepartitioning Simulated Annealing Times");
                        break;
                }

                for (int j = 0; j < 50; j++) {
                    System.out.println(times[i][j]);
                }
            }
        }
    }

    public static void printIntArray(int[] anArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(anArray[i]);
        }
        System.out.println(sb.toString());
    }

    public static void printLongArray(long[] anArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(anArray[i]);
        }
        System.out.println(sb.toString());
    }

    /**
     * Created by robertshaw on 4/19/16.
     */
    public static class KarmarkarKarp {
    //    public static long runKarmarkarKarp(LinkedList sequence) {
    //        // sorts in nlogn time
    //        LinkedList sorted = MergeSort.runMergeSort(sequence);
    //
    //        while(sorted.size() > 1) {
    //            long u = (long) sorted.pop();
    //            long v = (long) sorted.pop();
    //
    //            long difference = u - v;
    //
    //            MergeSort.addSorted(sorted, difference);
    //        }
    //
    //        if (sorted.size() > 0) {
    //            return (long) sorted.get(0);
    //        } else {
    //            return Long.MAX_VALUE;
    //        }
    //    }

        public static long runKarmarkarKarp2(long[] sequence) {
            int length = sequence.length;

            // loads the sequence into a priority queue
            PriorityQueue<Long> queue = new PriorityQueue<Long>(length, Collections.reverseOrder());
            for (int i = 0; i < length; i++) {
                queue.add(sequence[i]);
            }

            while (queue.size() > 1) {
                long u = queue.remove();
                long v = queue.remove();

                long difference = u - v;

                queue.add(difference);
            }

            if (queue.size() > 0) {
                return queue.remove();
            } else {
                return Long.MAX_VALUE;
            }
        }
    }

    /**
     * Created by robertshaw on 4/20/16.
     */
    public static class Prepartitioning {
        long[] a;
        int[] p;
        int length;
        int maxIter = 25000;

        Prepartitioning(long[] sequence) {
            this.length = sequence.length;
            this.a = sequence;
        }

        // updates partition array
        public int[] randomMove (int n, int[] partition) {
            // generates 2 random indices to be used
            int i = RandomNumberGenerator.randomIntBelow(n);
            int j = RandomNumberGenerator.randomIntBelow(n);

            // ensure that p_i != j
            while (partition[i] == j) {
                i = RandomNumberGenerator.randomIntBelow(n);
                j = RandomNumberGenerator.randomIntBelow(n);
            }

            // creates neighbor array
            int[] neighbor = new int[n];
            for (int k = 0; k < n; k++) {
                if (k == i) {
                    // updates partition to be a random neighbor
                    neighbor[i] = j;
                } else {
                    // otherwise just copy
                    neighbor[k] = partition[k];
                }
            }

            return neighbor;

        }

        // generates sequence of n values between [0, n-1]
        public int[] generateRandomPrepartition (int n) {
            int[] partition = new int[n];

            // generates n random values between [0, n)
            for (int i = 0; i < n; i++) {
                partition[i] = RandomNumberGenerator.randomIntBelow(n);
            }

            return partition;
        }

        // converts A to A' via prepartition P
        public long[] generateAPrime (long[] a, int[] p, int length) {
            // create aPrime array
            long[] aPrime = new long[length];
            for (int i = 0 ; i < length; i++) {
                aPrime[i] = 0;
            }

            // enforce the partition
            for (int j = 0; j < length; j++) {
                aPrime[p[j]] = aPrime[p[j]] + a[j];
            }

            return aPrime;
        }

        // runs repeated random algorithm with random partitions
        public long repeatedRandom () {
            int[] partition = this.generateRandomPrepartition(this.length);
            long[] sequence = this.generateAPrime(this.a, partition, this.length);

            long residue = KarmarkarKarp.runKarmarkarKarp2(sequence);

            // continually generate random prepartitions from scratch and calc residue
            for (int i = 0; i < this.maxIter; i++) {
                int[] randomPartition = this.generateRandomPrepartition(this.length);
                long[] sequencePrime = this.generateAPrime(this.a, randomPartition, this.length);

                long residuePrime = KarmarkarKarp.runKarmarkarKarp2(sequencePrime);

                // if we find a better partition, then use it
                if (residuePrime < residue) {
                    partition = randomPartition;
                    residue = residuePrime;
                }
            }

            // saves best prepartition we've seen
            this.p = partition;

            // returns best residue we've seen
            return residue;
        }

        // runs hill climbing algorithm with random neighbors of "current" partition
        public long hillClimbing() {

            // generates original prepartiton, A', and calculates residue with KK starting from A'
            int[] partition = this.generateRandomPrepartition(this.length);
            long[] sequence = this.generateAPrime(this.a, partition, this.length);
            long residue = KarmarkarKarp.runKarmarkarKarp2(sequence);

            for (int i = 0; i < this.maxIter; i++) {
                // generates random neighbor, and calculates A' based on new partition
                int[] randomNeighbor = this.randomMove(this.length, partition);
                long[] neighborSequence = this.generateAPrime(this.a, randomNeighbor, this.length);

                // calculates residue using KarmarkarKarp on neighbor's A'
                long neighborResidue = KarmarkarKarp.runKarmarkarKarp2(neighborSequence);

                // if we find a better partition, then use it
                if (neighborResidue < residue) {
                    partition = randomNeighbor;
                    residue = neighborResidue;
                }
            }

            // saves best prepartition we've seen
            this.p = partition;

            // returns best residue we've seen
            return residue;
        }

        // simulated annealing algorithm
        // generates original prepartition, A', and calculates residue with KK starting from A'
        public long simulatedAnnealing () {
            int[] partition = this.generateRandomPrepartition(this.length);
            long[] sequence = this.generateAPrime(this.a, partition, this.length);
            long residue = KarmarkarKarp.runKarmarkarKarp2(sequence);

            // saves best residue found thus far
            int[] bestPartition = partition;
            long[] bestSequence = sequence;
            long bestResidue = residue;

            // generate random neighbor, calculate residue based on new partition
            for (int i = 0; i < this.maxIter; i++) {
                // choose random neighbor from S, not from S'' (i.e from partition of last round, not optimal)
                int[] randomNeighbor = this.randomMove(this.length, partition);
                long[] neighborSequence = this.generateAPrime(this.a, randomNeighbor, this.length);

                // calculates residue with Karmarkar-Kark
                long neighborResidue = KarmarkarKarp.runKarmarkarKarp2(neighborSequence);

                // if the random neighbor is better than current partition, save random neighbor
                if (neighborResidue < residue) {
                    partition = randomNeighbor;
                    sequence = neighborSequence;
                    residue = neighborResidue;
                }
                // if the random neighbor is worse than the current partition, save with some probability
                else {
                    // True with some probability as below
                    if (goToWorseOption(i, residue, neighborResidue)) {
                        partition = randomNeighbor;
                        sequence = neighborSequence;
                        residue = neighborResidue;
                    }
                }

                // update best solution so far if we find something better
                if (residue < bestResidue) {
                    bestPartition = partition;
                    bestSequence = sequence;
                    bestResidue = residue;
                }
            }

            // saves best prepartition we've seen
            this.p = bestPartition;

            // returns best residue we've seen
            return bestResidue;

        }
        // HELPER FUNCTIONS FOR SIMULATED ANNEALING
        private double cooling(int iter) {
            double tIter = Math.pow(10, 10) * Math.pow(0.8, (Math.floor(iter) / 300));
            return tIter;
        }

        private boolean goToWorseOption(int iter, long residue, long residuePrime) {
            double probability = Math.exp(-1 * ((((double) residuePrime) - ((double) residue)) / this.cooling(iter)));
            return RandomNumberGenerator.biasedBit(probability);
        }
    }

    /**
     * Created by robertshaw on 4/19/16.
     */
    public static class RandomNumberGenerator {
        public static int randomSet() {
            Random random = new Random();
            boolean bit = random.nextBoolean();

            if (bit) {
                return 1;
            } else {
                return -1;
            }
        }

        public static int randomIntBelow(int max) {
            Random random = new Random();
            return random.nextInt(max);
        }

        // http://stackoverflow.com/questions/4655931/12-digit-unique-random-number-generation-in-java
        public static long randomLongBelow10tothe12() {
            char[] digits = new char[12];

            // since each digit is random from unif distribution, the entire number is random from unif
            for (int i = 0; i < 12; i++) {
                digits[i] = (char) (RandomNumberGenerator.randomIntBelow(10) + '0');
            }

            // returns number in [1, 10^12]
            return (Long.parseLong(new String(digits)) +  1);
        }

        public static boolean randomBit() {
            Random random = new Random();
            return random.nextBoolean();
        }

        public static boolean biasedBit(double p) {
            Random random = new Random();
            // returns a random float mem [0,1]
            float randomFloat = random.nextFloat();

            // true with probability p
            if (randomFloat < p) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Created by robertshaw on 4/19/16.
     */
    public static class StandardSolution {
        long[] a;
        int[] s;
        int length;
        int maxIter = 25000;

        StandardSolution(long[] sequence) {
            this.length = sequence.length;
            this.a = sequence;
            this.s = new int[this.length];
        }

        // swaps elements in a set randomly
        public void randomMove (int[] split, int i, int j, boolean swapBoth) {
            // swap the first element to the other set
            split[i] = split[i] * -1;

            // swap the second element with probability 1/2
            if (swapBoth) {
                split[j] = split[j] * -1;
            }
        }


        // chooses 2 random indices to be swapped
        public int[] randomIndicies(int length) {
            if (length > 1) {
                // random indices of the random move
                int i = RandomNumberGenerator.randomIntBelow(this.length);
                int j = RandomNumberGenerator.randomIntBelow(this.length);
                // ensure i != j
                while(i == j) {
                    j = RandomNumberGenerator.randomIntBelow(this.length);
                }

                int[] indicies = new int[2];
                indicies[0] = i;
                indicies[1] = j;

                return indicies;
            } else {
                return null;
            }
        }

        // randomly splits between
        public int[] randomSplit(int length) {
            int[] split = new int[length];
            // generate random split of the set
            for (int i = 0; i < length; i++) {
                split[i] = RandomNumberGenerator.randomSet();
            }

            return split;
        }

        // calculates the residue of a split
        public long residue(int[] split) {
            long a1 = 0;
            long a2 = 0;

            for (int i = 0; i < this.length; i++) {
                if (split[i] == 1) {
                    a1 = a1 + this.a[i];
                } else {
                    a2 = a2 + this.a[i];
                }
            }

            long diff = a1 -a2;

            if (diff < 0) {
                return -1 * diff;
            } else {
                return diff;
            }
        }

        // TODO: possible to go back later and update this to make it just update the current res value rather than recompute
        public long swapResidue(int[] split, int i, int j, boolean swapBoth) {
            long a1 = 0;
            long a2 = 0;

            for (int k = 0; k < this.length; k++) {
                if (split[k] == 1) {
                    // if not on i or j, proceed normally
                    if (k != i && k != j) {
                        a1 = a1 + this.a[k];
                    }
                    // if on ith spot, swap
                    else if (k == i) {
                        a2 = a2 + this.a[k];
                    }
                    // if on the jth spot, only swap if we are swapping both
                    else if (k == j && swapBoth) {
                        a2 = a2 + this.a[k];
                    }
                    else {
                        a1 = a1 + this.a[k];
                    }
                } else {
                    // if not on i or j, proceed normally
                    if (k != i && k != j) {
                        a2 = a2 + this.a[k];
                    }
                    // if on the ith spot, swap to opposite set
                    else if (k == i) {
                        a1 = a1 + this.a[k];
                    }
                    // if on the jth spot, only swap if we are swapping both
                    else if (k == j && swapBoth) {
                        a1 = a1 + this.a[k];
                    }
                    else {
                        a2 = a2 + this.a[k];
                    }
                }
            }

            // residue is the difference in the 2 sets
            long diff = a1 - a2;

            if (diff < 0) {
                return -1 * diff;
            } else {
                return diff;
            }
        }

        // repeated random algorithm
        public long repeatedRandom() {
            int[] split = this.randomSplit(this.length);
            long splitResidue = this.residue(split);
            // generate max-Iter random splits, choose the best one
            for (int i = 0; i < this.maxIter; i++) {
                int [] splitPrime = this.randomSplit(this.length);
                long splitPrimeResidue = this.residue(splitPrime);

                // if the new random split is better than the optimal, replace
                if (splitPrimeResidue < splitResidue) {
                    split = splitPrime;
                    splitResidue = splitPrimeResidue;
                }

               // System.out.println(splitResidue);
            }

            //System.out.println(" ");

            // saves optimal split, returns residue
            this.s = split;

            return splitResidue;
        }

        // hill climbing algorithm
        public long hillClimbing() {
            int[] split = this.randomSplit(this.length);
            long splitResidue = this.residue(split);

            // make max-Iter random moves, keep changing if improvement
            for (int i = 0; i < this.maxIter; i++) {
                int[] randIndicies = this.randomIndicies(this.length);
                boolean swapBoth = RandomNumberGenerator.randomBit();

                // calculates split prime after random move
                long splitPrimeResidue = this.swapResidue(split, randIndicies[0], randIndicies[1], swapBoth);
                // update the current split if split prime is better
                if (splitPrimeResidue < splitResidue) {
                    // updates split to reflect random move
                    this.randomMove(split, randIndicies[0], randIndicies[1], swapBoth);
                    splitResidue = splitPrimeResidue;
                }

                //System.out.println(splitResidue);
            }

            //System.out.println(" ");

            // saves optimal split, returns residue
            this.s = split;

            return splitResidue;
        }

        // simulated annealing algorithm
        public long simulatedAnnealing() {
            int[] split = this.randomSplit(this.length);
            long splitResidue = this.residue(split);

            int[] bestSplitSoFar = split;
            long bestResidueSoFar = splitResidue;

            // make max-Iter random moves, keep changing if improvement/if worse with prob
            for (int i = 0; i < this.maxIter; i++) {
                // find random neighbor of S
                int[] randIndicies = this.randomIndicies(this.length);
                boolean swapBoth = RandomNumberGenerator.randomBit();

                // calculates split prime after random move
                long splitPrimeResidue = this.swapResidue(split, randIndicies[0], randIndicies[1], swapBoth);
                // update the current split if split prime is better
                if (splitPrimeResidue < splitResidue) {
                    // updates split to reflect random move
                    this.randomMove(split, randIndicies[0], randIndicies[1], swapBoth);
                    splitResidue = splitPrimeResidue;

                // update the current split if split prime is worse with some probability
                } else {
                    if (goToWorseOption(i, splitResidue, splitPrimeResidue)) {
                        this.randomMove(split, randIndicies[0], randIndicies[1], swapBoth);
                        splitResidue = splitPrimeResidue;
                    }
                }

                // update optimal split if our updated current split is better
                if (splitResidue < bestResidueSoFar) {
                    bestResidueSoFar = splitResidue;
                    bestSplitSoFar = split;
                }

                //System.out.println(bestResidueSoFar);
            }

            //System.out.println(" ");

            // saves best split we have seen, returns residue of this split
            this.s = bestSplitSoFar;
            return bestResidueSoFar;
        }

        // HELPER FUNCTIONS FOR SIMULATED ANNEALING
        private double cooling(int iter) {
            double tIter = Math.pow(10, 10) * Math.pow(0.8, (Math.floor(iter) / 300));
            return tIter;
        }

        private boolean goToWorseOption(int iter, long residue, long residuePrime) {
            double probability = Math.exp(-1 * ((((double) residuePrime) - ((double) residue)) / this.cooling(iter)));
            return RandomNumberGenerator.biasedBit(probability);
        }
    }

    /**
     * Created by robertshaw on 4/19/16.
     */
//    public static class MergeSort {
//        public static LinkedList runMergeSort(LinkedList s) {
//            LinkedList q = new LinkedList();
//
//            int length = s.size();
//
//            for (int i = 0; i < length; i++) {
//                LinkedList x = new LinkedList();
//                x.add(s.get(i));
//                q.add(x);
//            }
//
//            while (q.size() >= 2) {
//                LinkedList u = (LinkedList) q.pop();
//                LinkedList v = (LinkedList) q.pop();
//
//                q.add(merge(u, v));
//            }
//
//            if (q == null) {
//                return null;
//            } else {
//                return (LinkedList) q.getFirst();
//            }
//        }
//
//        private static LinkedList merge(LinkedList s, LinkedList t) {
//            if (s.isEmpty()) {
//                return t;
//            } else if (t.isEmpty()) {
//                return s;
//            } else {
//                long u;
//                if((long)s.getFirst() >= (long)t.getFirst()) {
//                    u = (long) s.pop();
//                } else {
//                    u = (long) t.pop();
//                }
//
//                // push the smaller element onto the merged list
//                LinkedList merged = new LinkedList();
//                merged.push(u);
//
//                // recursive call, with popped elements
//                merged.addAll(merge(s, t));
//
//                // return merged
//                return merged;
//            }
//        }
//
//        // helper method add to sorted list
//        // http://stackoverflow.com/questions/18144820/inserting-into-sorted-linkedlist-java
//        public static void addSorted(LinkedList sorted, long n) {
//
//            if (sorted.size() == 0) {
//                sorted.add(n);
//            } else if ((long) sorted.get(0) < n) {
//                sorted.push(n);
//            } else if ((long) sorted.get(sorted.size() - 1) > n) {
//                sorted.add(n);
//            }else {
//                int i = 0;
//                while ((long) sorted.get(i) > n) {
//                    i++;
//                }
//                sorted.add(i, n);
//            }
//
//        }
//    }
}
