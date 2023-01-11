package input;

public class FiltersIn {
    private SortIn sort = new SortIn();

    private ContainsIn contains = new ContainsIn();

    public final SortIn getSort() {
        return sort;
    }

    public final void setSort(final SortIn sort) {
        this.sort = sort;
    }

    public final ContainsIn getContains() {
        return contains;
    }

    public final void setContains(final ContainsIn contains) {
        this.contains = contains;
    }

    public FiltersIn() {

    }

    @Override
    public final String toString() {
        return "FiltersIn{"
                +
                "sort=" + sort
                +
                ", contains=" + contains
                +
                '}';
    }
}
