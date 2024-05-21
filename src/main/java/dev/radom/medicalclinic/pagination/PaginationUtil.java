package dev.radom.medicalclinic.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class PaginationUtil {
    private long totalElements;
    private int totalPages;
    private int currentPage;
    private boolean first;
    private boolean last;
}
