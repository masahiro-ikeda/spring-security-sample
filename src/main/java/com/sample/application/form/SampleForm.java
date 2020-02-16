package com.sample.application.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

public class SampleForm {

    @NotEmpty
    private String facility;

    /**
     * 日付の前後チェックのような相関チェック等を行う場合
     * @return
     */
    @AssertTrue
    private boolean isTrue(){
        return true;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }
}
