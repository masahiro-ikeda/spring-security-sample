package com.sample.root.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.common.model.Facility;
import com.sample.common.repository.FacilityRepository;
import com.sample.root.security.session.SessionUser;

@Controller
public class SampleController {

    private FacilityRepository facilityRepository;
    private SessionUser session;

    /**
     * @param facilityRepository
     * @param session
     */
    @Autowired
    SampleController(FacilityRepository facilityRepository, SessionUser session) {
        this.facilityRepository = facilityRepository;
        this.session = session;
    }

    /**
     * ホーム画面を表示させる
     */
    @GetMapping("home")
    public String showHome(Model model) {

        if (null == session.getFacility()) {
            return "redirect:select";
        }

        model.addAttribute( "facility", session.getFacility().getFacilityName() );

        return "home";
    }

    /**
     * @param facility
     * @return
     */
    @PostMapping("home")
    public String selectFacility(@RequestParam("facility") Integer facility) {

        Optional<Facility> queryResult = facilityRepository.findById( facility );

        if (queryResult.isPresent()) {
            session.setFacility( queryResult.get() );
            return "redirect:home";
        } else {
            return "redirect:select";
        }
    }

    /**
     * 施設選択画面を表示させる
     */
    @GetMapping("select")
    public String showSelect(Model model) {

        model.addAttribute( "facilities", session.getFacilities() );

        return "select";
    }
}
