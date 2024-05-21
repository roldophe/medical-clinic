package dev.radom.medicalclinic.pagination;
import org.springframework.data.domain.Page;

public class PageWrapper<T> {

    private final Page<T> page;

    public PageWrapper(Page<T> page) {
        this.page = page;
    }

    public long getTotalElements() {
        return page.getTotalElements();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    // Other getter methods as needed

    public Iterable<T> getData() {
        return page.getContent(); // Returns the content as "data"
    }
}
