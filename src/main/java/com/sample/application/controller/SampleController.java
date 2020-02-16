package com.sample.application.controller;

import com.sample.application.form.SampleForm;
import com.sample.application.query.FacilityQuery;
import com.sample.authentication.session.SessionUser;
import com.sample.common.dao.entity.Facility;
import com.sample.common.dao.entity.User;
import com.sample.common.dao.repository.FacilityRepository;
import com.sample.common.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger( SampleController.class );

    private SessionUser session;
    private FacilityRepository facilityRepository;
    private FacilityQuery facilityQuery;
    private UserRepository userRepository;

    /**
     * @param facilityRepository
     * @param session
     */
    @Autowired
    SampleController(SessionUser session, FacilityRepository facilityRepository, FacilityQuery facilityQuery, UserRepository userRepository) {
        this.facilityRepository = facilityRepository;
        this.facilityQuery = facilityQuery;
        this.session = session;
        this.userRepository = userRepository;
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
     * @param sampleForm
     * @return
     */
    @PostMapping("select")
    public String selectFacility(@ModelAttribute("SampleForm") @Validated SampleForm sampleForm) {

        int facilityId = Integer.parseInt( sampleForm.getFacility() );
        if (facilityRepository.existsById( facilityId )) {
            session.setFacilityId( facilityId );

            logger.info( "Select Facility: " + facilityId );

            return "redirect:home";

        } else {
            return "redirect:select";
        }
    }

    /**
     * ホーム画面を表示させる
     */
    @GetMapping("home")
    public String showHome(Model model) {

        if (null == session.getFacilityId()) {
            return "redirect:select";
        }

        Optional<Facility> facility = facilityRepository.findById( session.getFacilityId() );
        if (facility.isPresent()) {
            model.addAttribute( "facility", facility.get().getFacilityName() );
        }

        return "home";
    }

    @GetMapping("update")
    public String updateUser(Model model, @RequestParam String name) {

        if (null == session.getFacilityId()) {
            return "redirect:select";
        }

        Optional<User> user = userRepository.findById( session.getUserId() );

        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.setUserName( name );

            userRepository.saveAndFlush( userEntity );
        }

        return "redirect:home";
    }

    @GetMapping("insert")
    public String insertFacility(Model model, @RequestParam String name) {

        if (null == session.getFacilityId()) {
            return "redirect:select";
        }

        Facility facility = new Facility();
        facility.setFacilityName( name );

        facilityRepository.saveAndFlush( facility );

        return "redirect:home";
    }
}
