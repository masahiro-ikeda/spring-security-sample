package com.sample.application.controller;

import com.sample.application.form.SampleForm;
import com.sample.application.query.FacilityQuery;
import com.sample.authentication.security.LoginUser;
import com.sample.authentication.session.SessionUser;
import com.sample.common.dao.entity.Facility;
import com.sample.common.dao.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class SampleController {

    private SessionUser session;
    private FacilityRepository facilityRepository;
    private FacilityQuery facilityQuery;

    /**
     * @param facilityRepository
     * @param session
     */
    @Autowired
    SampleController(SessionUser session, FacilityRepository facilityRepository, FacilityQuery facilityQuery) {
        this.facilityRepository = facilityRepository;
        this.facilityQuery = facilityQuery;
        this.session = session;
    }

    /**
     * 施設選択画面を表示させる
     */
    @GetMapping("select")
    public String showSelect(Model model) {

        List<Facility> facilities = facilityQuery.findByUserId( session.getUserId() );
        model.addAttribute( "facilities", facilities );

        return "select";
    }

    /**
     *
     * @param sampleForm
     * @return
     */
    @PostMapping("select")
    public String selectFacility(@ModelAttribute("SampleForm") @Validated SampleForm sampleForm) {

        int facilityId = Integer.parseInt( sampleForm.getFacility() );
        if (facilityRepository.existsById( facilityId )) {
            session.setFacilityId( facilityId );
            return "redirect:home";
        } else {
            return "redirect:select";
        }
    }

    /**
     * ホーム画面を表示させる
     */
    @GetMapping("home")
    public String showHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

        if (null == session.getFacilityId()) {
            return "redirect:select";
        }

        Optional<Facility> facility = facilityRepository.findById( session.getFacilityId() );
        if (facility.isPresent()) {
            model.addAttribute( "facility", facility.get().getFacilityName() );
        }

        return "home";
    }
}
