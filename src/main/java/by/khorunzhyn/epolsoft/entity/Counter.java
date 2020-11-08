package by.khorunzhyn.epolsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "counter")
public class Counter extends BaseEntity {

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "count")
    private Long count;

    @Column(name = "counter_offset")
    private Long counterOffset;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCounterOffset() {
        return counterOffset;
    }

    public void setCounterOffset(Long counterOffset) {
        this.counterOffset = counterOffset;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
