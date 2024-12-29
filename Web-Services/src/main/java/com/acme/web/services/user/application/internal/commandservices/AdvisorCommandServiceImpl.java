package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.entities.Review;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.ReviewRepository;
import com.acme.web.services.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.services.AdvisorCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the service implementation for the AdvisorCommandService.
 * It handles the creation of an advisor.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */

@Service
public class AdvisorCommandServiceImpl implements AdvisorCommandService{
    private final AdvisorRepository advisorRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final ReviewRepository reviewRepository;

    public AdvisorCommandServiceImpl(AdvisorRepository advisorRepository, UserRepository userRepository, AppointmentRepository appointmentRepository, ReviewRepository reviewRepository){
        this.advisorRepository = advisorRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Long handle(CreateAdvisorCommand command){
        var user = userRepository.findById(command.userId());
        if(user.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        var advisor = new Advisor(command, user.get());
        try{
            advisorRepository.save(advisor);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving advisor: " + e.getMessage());
        }
        return advisor.getId();
    }

    public void updateAdvisorRating(Long appointmentId){
        // Verificar Appointment:
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()->new IllegalArgumentException("Appointment does not exist"));
        // Verificar Advisor:
        Advisor advisor = advisorRepository.findById(appointment.getAdvisor().getId()).orElseThrow(() -> new RuntimeException("Advisor not found"));
        // Obtener todos los reviews del Advisor:
        List<Review> reviews = reviewRepository.findAllByAppointmentIdIn(appointmentRepository.findAllByAdvisorId(advisor.getId()).stream().map(Appointment::getId).collect(Collectors.toList()));
        // Calcular el rating promedio:
        double averageRating = reviews.stream().mapToDouble(Review::getRating).average().orElse(0);
        // Redondear hacia abajo y asegurarse de que el rating esté entre 1 y 5:
        int newRating = (int) Math.max(1, Math.min(5, Math.floor(averageRating)));

        advisor.setRating(newRating);
        advisorRepository.save(advisor);
    }
}