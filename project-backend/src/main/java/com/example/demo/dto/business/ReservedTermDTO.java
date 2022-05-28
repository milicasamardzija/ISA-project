package com.example.demo.dto.business;

import java.util.Date;

public class ReservedTermDTO {
    private Date dateStart;
    private Date dateEnd;

    public ReservedTermDTO() {}

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public ReservedTermDTO(Date dateStart, Date dateEnd) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
