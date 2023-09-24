import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    private List<Bear> sortedBears;
    private List<Bed> sortedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        sortedBears = sortBears(beds, bears);
        sortedBeds = sortBeds(bears, beds);
    }

    /**
     * Use chosen bear to partition beds.
     * @param bear choose a bear to be the pivot.
     * @param smallBeds store small beds.
     * @param equalBeds store equal beds.
     * @param largeBeds store large beds.
     * @return return a bed equal to bear for the partition of bears.
     */
    private Bed bedPartition(Bear bear, List<Bed> beds, List<Bed> smallBeds, List<Bed> equalBeds, List<Bed> largeBeds) {
        for (Bed bed : beds) {
            if (bed.compareTo(bear) < 0) {
                smallBeds.add(bed);
            } else if (bed.compareTo(bear) > 0) {
                largeBeds.add(bed);
            } else {
                equalBeds.add(bed);
            }
        }
        return equalBeds.get(0);
    }

    /**
     * Use chosen bed to partition bears.
     * @param bed choose a bed to be the pivot.
     * @param bears all bears should be sorted.
     * @param smallBeds store small bears.
     * @param equalBeds store equal bears.
     * @param largeBeds store large bears.
     * @return return a bear equal to bed for the partition of beds.
     */
    private Bear bearPartition(Bed bed, List<Bear> bears, List<Bear> smallBears, List<Bear> equalBears, List<Bear> largeBears) {
        for (Bear bear : bears) {
            if (bear.compareTo(bed) < 0) {
                smallBears.add(bear);
            } else if (bear.compareTo(bed) > 0) {
                largeBears.add(bear);
            } else {
                equalBears.add(bear);
            }
        }
        return equalBears.get(0);
    }

    private List<Bear> sortBears(List<Bed> beds, List<Bear> bears) {
        List<Bear> returnBears = new ArrayList<>();
        if (beds.size() <= 1) {
            return bears;
        }
        List<Bear> smallBears = new ArrayList<>();
        List<Bear> equalBears = new ArrayList<>();
        List<Bear> largeBears = new ArrayList<>();
        List<Bed> smallBeds = new ArrayList<>();
        List<Bed> equalBeds = new ArrayList<>();
        List<Bed> largeBeds = new ArrayList<>();
        Bed bearPivot = beds.get(0);
        Bear bedPivot = bearPartition(bearPivot, bears, smallBears, equalBears, largeBears);
        bedPartition(bedPivot, beds, smallBeds, equalBeds, largeBeds);
        smallBears = sortBears(smallBeds, smallBears);
        largeBears = sortBears(largeBeds, largeBears);
        returnBears.addAll(smallBears);
        returnBears.addAll(equalBears);
        returnBears.addAll(largeBears);
        return returnBears;
    }

    private List<Bed> sortBeds(List<Bear> bears, List<Bed> beds) {
        List<Bed> returnBeds = new ArrayList<>();
        if (bears.size() <= 1) {
            return beds;
        }
        List<Bear> smallBears = new ArrayList<>();
        List<Bear> equalBears = new ArrayList<>();
        List<Bear> largeBears = new ArrayList<>();
        List<Bed> smallBeds = new ArrayList<>();
        List<Bed> equalBeds = new ArrayList<>();
        List<Bed> largeBeds = new ArrayList<>();
        Bear bedPivot = bears.get(0);
        Bed bearPivot = bedPartition(bedPivot, beds, smallBeds, equalBeds, largeBeds);
        bearPartition(bearPivot, bears, smallBears, equalBears, largeBears);
        smallBeds = sortBeds(smallBears, smallBeds);
        largeBeds = sortBeds(largeBears, largeBeds);
        returnBeds.addAll(smallBeds);
        returnBeds.addAll(equalBeds);
        returnBeds.addAll(largeBeds);
        return returnBeds;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return this.sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return this.sortedBeds;
    }
}
